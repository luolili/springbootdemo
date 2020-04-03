package com.luo.util;

import com.google.gson.Gson;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

public class HttpUtil {
    private static final Gson gson = new Gson();
    private static final int REQ_TIMEOUT = 5000;

    public static Map<String, Object> doGet(String url) {
        Map<String, Object> map = new HashMap<>();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(REQ_TIMEOUT).setSocketTimeout(5000)
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

    public static String doPost(String url, String data) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(REQ_TIMEOUT).setSocketTimeout(5000)
                .setRedirectsEnabled(true)
                .build();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        httpPost.setHeader("Content-Type", "tex/html;charset=UTF-8");
        if (data != null) {
            StringEntity stringEntity = new StringEntity(data, "UTF-8");
            httpPost.setEntity(stringEntity);
        }
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                String entityString = EntityUtils.toString(httpResponse.getEntity());
                return entityString;
            }
        } catch (Exception e) {

        } finally {
            try {
                httpClient.close();
            } catch (Exception e) {

            }
        }
        return null;
    }
}
