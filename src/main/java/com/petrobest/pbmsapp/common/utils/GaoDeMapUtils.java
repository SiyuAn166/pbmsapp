package com.petrobest.pbmsapp.common.utils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GaoDeMapUtils {

    /**
     * 高德地图请求秘钥
     */
    private static final String KEY = "03c1e11c309eb898b8a50d0a99874466";
    /**
     * 返回值类型
     */
    private static final String OUTPUT = "JSON";
    /**
     * 根据地名获取高德经纬度
     */
    private static final String GET_LNG_LAT_URL = "http://restapi.amap.com/v3/geocode/geo";
    /**
     * 根据高德经纬度获取地名
     */
    private static final String GET_ADDRESS_URL = "http://restapi.amap.com/v3/geocode/regeo";

    /**
     * 根据高德经纬度获取地址信息
     *
     * @param gdLng 高德地图经度
     * @param gdLat 高德地图纬度
     * @return
     */
    public static String getAddressByLngLat(double gdLng, double gdLat) {

        String location = gdLng + "," + gdLat;
        Map<String, String> params = new HashMap<>();
        params.put("location", location);

        // Map<String, String> result = new HashMap<>();
        try {
            // 拼装url
            String url = jointUrl(params, OUTPUT, KEY, GET_ADDRESS_URL);
            // 调用高德SDK
            return HttpClientUtils.doPost(url, params);
            // 解析Json字符串,获取城市名称
            // JSONObject jsonObject = JSON.parseObject(jsonResult);
            // String regeocode = jsonObject.getString("regeocode");
            // JSONObject regeocodeObj = JSON.parseObject(regeocode);
            // String address = regeocodeObj.getString("formatted_address");
            // 组装结果
            // result.put(location, address);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据地址信息获取高德经纬度
     *
     * @param address 地址信息
     * @return
     */
    public static String getLngLarByAddress(String address) {
        Map<String, String> params = new HashMap<>();
        params.put("address", address);

        // Map<String, String> result = new HashMap<>();
        try {
            // 拼装url
            String url = jointUrl(params, OUTPUT, KEY, GET_LNG_LAT_URL);
            // 调用高德地图SDK
            return HttpClientUtils.doPost(url, params);

            // 解析JSON字符串,取到高德经纬度
            // JSONObject jsonObject = JSON.parseObject(jsonResult);
            // JSONArray geocodes = jsonObject.getJSONArray("geocodes");
            // String geocode = JSON.toJSONString(geocodes.get(0));
            // JSONObject geocodeObj = JSON.parseObject(geocode);
            // String lonAndLat = geocodeObj.getString("location");
            // 组装结果
            // result.put(address, lonAndLat);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 拼接请求字符串
     *
     * @param params
     * @param output
     * @param key
     * @param url
     * @return
     * @throws IOException
     */
    private static String jointUrl(Map<String, String> params, String output, String key, String url) throws IOException {
        StringBuilder baseUrl = new StringBuilder();
        baseUrl.append(url);

        int index = 0;
        Set<Map.Entry<String, String>> entrys = params.entrySet();
        for (Map.Entry<String, String> param : entrys) {
            // 判断是否是第一个参数
            if (index == 0) {
                baseUrl.append("?");
            } else {
                baseUrl.append("&");
            }
            baseUrl.append(param.getKey()).append("=").append(URLEncoder.encode(param.getValue(), "utf-8"));
            index++;
        }
        baseUrl.append("&output=").append(output).append("&key=").append(key);

        return baseUrl.toString();
    }

}
