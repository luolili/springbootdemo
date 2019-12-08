package com.example.springwebclientdemo.proxy;

import com.example.springwebclientdemo.api.ApiServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
public class JDKProxyCreator implements ProxyCreator {
    @Override
    public Object createProxy(Class<?> clazz) {
        log.info("createProxy:{}", clazz);
        ServerInfo serverInfo = extractServerInfo(clazz);
        log.info("serverInfo:{}", serverInfo);
        RestHandler handler = new WebclientRestHandler();
        handler.init(serverInfo);
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{clazz}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        MethodInfo methodInfo = extractMethodInfo(method, args);
                        log.info("methodInfo:{}", methodInfo);
                        //调用rest接口
                        return handler.invokeRest(methodInfo);
                    }

                    private MethodInfo extractMethodInfo(Method method, Object[] args) {
                        MethodInfo methodInfo = new MethodInfo();
                        extractUrlAndMethod(method, methodInfo);
                        //
                        extractParamsAndBody(method, args, methodInfo);
                        return methodInfo;
                    }

                    private void extractParamsAndBody(Method method, Object[] args, MethodInfo methodInfo) {
                        Parameter[] parameters = method.getParameters();
                        Map<String, Object> params = new LinkedHashMap<>();
                        //methodInfo.setParams(params);
                        for (int i = 0; i < parameters.length; i++) {
                            //PathVariable
                            PathVariable annPath = parameters[i].getAnnotation(PathVariable.class);
                            RequestBody annBody = parameters[i].getAnnotation(RequestBody.class);
                            if (annPath != null) {
                                params.put(annPath.value(), args[i]);
                            }
                            if (annBody != null) {
                                methodInfo.setBody((Mono<?>) args[i]);
                            }

                        }
                    }

                    private void extractUrlAndMethod(Method method, MethodInfo methodInfo) {
                        Annotation[] annotations = method.getAnnotations();
                        for (Annotation ann : annotations) {
                            if (ann instanceof GetMapping) {
                                GetMapping a = (GetMapping) ann;
                                methodInfo.setUrl(a.value()[0]);
                                methodInfo.setMethod(HttpMethod.GET);
                            }
                            if (ann instanceof PostMapping) {
                                PostMapping a = (PostMapping) ann;
                                methodInfo.setUrl(a.value()[0]);
                                methodInfo.setMethod(HttpMethod.POST);
                            }
                            if (ann instanceof PutMapping) {
                                PutMapping a = (PutMapping) ann;
                                methodInfo.setUrl(a.value()[0]);
                                methodInfo.setMethod(HttpMethod.PUT);
                            }

                            if (ann instanceof DeleteMapping) {
                                DeleteMapping a = (DeleteMapping) ann;
                                methodInfo.setUrl(a.value()[0]);
                                methodInfo.setMethod(HttpMethod.DELETE);
                            }
                        }
                    }

                    private void extractReturnInfo(Method method, MethodInfo methodInfo) {
                        Annotation[] annotations = method.getAnnotations();
                        boolean assignableFrom = method.getReturnType().isAssignableFrom(Flux.class);
                        methodInfo.setReturnFlux(assignableFrom);
                        Class<?> type = extractElementType(method.getGenericReturnType());
                        methodInfo.setReturnElementType(type);
                    }

                    private Class<?> extractElementType(Type genericReturnType) {
                        Type[] typeArguments = ((ParameterizedType) genericReturnType).getActualTypeArguments();
                        return (Class<?>) typeArguments[0];

                    }
                });
    }


    private ServerInfo extractServerInfo(Class<?> clazz) {
        ServerInfo serverInfo = new ServerInfo();
        ApiServer annotation = clazz.getAnnotation(ApiServer.class);
        serverInfo.setUrl(annotation.value());
        return serverInfo;
    }
}
