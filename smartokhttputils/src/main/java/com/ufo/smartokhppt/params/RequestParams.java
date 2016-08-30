package com.ufo.smartokhppt.params;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者：XuDiWei
 * <p/>
 * 日期：2016/2/25.21:45
 * <p/>
 * 文件描述：参数封装类
 */
public class RequestParams {
    private HashMap<String, String> paramsMap = new HashMap<>();

    public void put(String paramsKey, String paramsValue) {
        paramsMap.put(paramsKey, paramsValue);
    }

    public int getParamsSize() {
        return paramsMap.size();
    }

    public Map<String, String> getParams() {
        return paramsMap;
    }

    /**
     * 返回拼接后的参数字符串型式
     *
     * @return
     */
    public String getParamsString() {
        if (null == paramsMap || paramsMap.size() == 0) {
            return "";
        }

        StringBuffer buffer = new StringBuffer();
        buffer.append("?");

        for (Map.Entry<String, String> m : paramsMap.entrySet()) {
            buffer.append(m.getKey());
            buffer.append("=");
            buffer.append(m.getValue());
            buffer.append("&");
        }

        return buffer.deleteCharAt(buffer.length() - 1).toString();
    }
}
