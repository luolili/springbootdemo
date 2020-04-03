package com.luo.util;

import com.google.gson.Gson;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

public class HttpUtil {

    private static final Gson gson = new Gson();

    public static Map<String, Object> doGet(String url) {
        Map<String, Object> map = new HashMap<>();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(5000).setSocketTimeout(5000)
                .setRedirectsEnabled(true)
                .build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                String entityString = EntityUtils.toString(httpResponse.getEntity());

                gson.fromJson(entityString, map.getClass());
            }
        } catch (Exception e) {

        } finally {
            try {
                httpClient.close();
            } catch (Exception e) {

            }
        }
        return map;
    }
}
