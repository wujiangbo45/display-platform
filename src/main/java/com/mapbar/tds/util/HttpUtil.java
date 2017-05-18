package com.mapbar.tds.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    static final String BOUNDARY = "----MyFormBoundarySMFEtUYQG6r5B920";

    public static String get(String url, String param, String token) throws IOException {
        Map<String, String> headers = new HashMap<>();
        if (!StringUtil.isEmpty(token)) {
            headers.put("token", token);
        }
        return get(url, param, headers);
    }

    public static String getHttps(String url, String param, String token) throws IOException {
        Map<String, String> headers = new HashMap<>();
        if (!StringUtil.isEmpty(token)) {
            headers.put("token", token);
        }
        return getHttps(url, param, headers);
    }


    public static String get(String url, String param, Map<String, String> headers) throws IOException {
        logger.info("get request url : {}, param : {}", url, param);
        URL uri = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) uri.openConnection();

        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setReadTimeout(30000);
        conn.setRequestProperty("contentType", "utf-8");
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("connection", "Keep-Alive");
        conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }

        if (!StringUtils.isEmpty(param)) {
            OutputStream os = conn.getOutputStream();
            os.write(param.getBytes("UTF-8"));
            os.close();
        }

        conn.connect();

        StringBuilder sb = new StringBuilder();
        int code = conn.getResponseCode();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String msg = null;
            while ((msg = br.readLine()) != null) {
                sb.append(msg).append("\n");
            }

            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
        } finally {
            if (br != null) {
                br.close();
            }
        }

        if (code != 200) {
            logger.error("request url [ " + url + " ] returns code [ " + code + " ]");
            throw new IOException("request url [ "
                    + url + " ] returns code [ " + code + " ], message [ " + sb.toString() + " ]");
        }

        logger.info("get request url : {}, response : {}", url, sb.toString());
        return sb.toString();
    }


    public static String getWithAll(String url, String param, String token) throws IOException {
        Map<String, String> headers = new HashMap<>();
        if (!StringUtil.isEmpty(token)) {
            headers.put("token", token);
        }
        return getWithAll(url, param, headers);
    }

    public static String getWithAll(String url, String param, Map<String, String> headers) throws IOException {
        logger.info("get request url : {}, param : {}", url, param);
        URL uri = new URL(url + param);
        HttpURLConnection conn = (HttpURLConnection) uri.openConnection();

        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setReadTimeout(30000);
        conn.setRequestProperty("contentType", "utf-8");
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("connection", "Keep-Alive");
        conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }

//        if (!StringUtils.isEmpty(param)) {
//            OutputStream os = conn.getOutputStream();
//            os.write(param.getBytes("UTF-8"));
//            os.close();
//        }

        conn.connect();

        StringBuilder sb = new StringBuilder();
        int code = conn.getResponseCode();
        BufferedReader br = null;
        try {
            if (code == 200) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
            }
            String msg = null;
            while ((msg = br.readLine()) != null) {
                sb.append(msg).append("\n");
            }

            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
        } finally {
            if (br != null) {
                br.close();
            }
        }


        logger.info("get request url : {}, response : {}", url, sb.toString());
        return sb.toString();
    }

    public static String getHttps(String url, String param, Map<String, String> headers) throws IOException {
        logger.info("get request url : {}, param : {}", url, param);
        URL uri = new URL(url);
        HttpsURLConnection conn = (HttpsURLConnection) uri.openConnection();

        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setReadTimeout(30000);
        conn.setRequestProperty("contentType", "utf-8");
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("connection", "Keep-Alive");
        conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }

        if (!StringUtils.isEmpty(param)) {
            OutputStream os = conn.getOutputStream();
            os.write(param.getBytes("UTF-8"));
            os.close();
        }

        conn.connect();

        StringBuilder sb = new StringBuilder();
        int code = conn.getResponseCode();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String msg = null;
            while ((msg = br.readLine()) != null) {
                sb.append(msg).append("\n");
            }

            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
        } finally {
            if (br != null) {
                br.close();
            }
        }

        if (code != 200) {
            logger.error("request url [ " + url + " ] returns code [ " + code + " ]");
            throw new IOException("request url [ "
                    + url + " ] returns code [ " + code + " ], message [ " + sb.toString() + " ]");
        }

        logger.info("get request url : {}, response : {}", url, sb.toString());
        return sb.toString();
    }

    public static InputStream getInputStream(String url, String param, Map<String, String> headers) {
        logger.info("get request url : {}, param : {}", url, param);
        InputStream result = null;
        URL uri = null;
        HttpURLConnection conn = null;

        try {
            uri = new URL(url);
            conn = (HttpURLConnection) uri.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setReadTimeout(30000);
            conn.setRequestProperty("contentType", "utf-8");
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            if (headers != null && headers.size() > 0) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    conn.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }

            if (!StringUtils.isEmpty(param)) {
                OutputStream os = conn.getOutputStream();
                os.write(param.getBytes("UTF-8"));
                os.close();
            }

            conn.connect();

            int code = conn.getResponseCode();

            if (code == 200) {
                result = conn.getInputStream();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }


    public static String post(String url, String param, String token) throws IOException {
        Map<String, String> headers = new HashMap<>();
        if (!StringUtil.isEmpty(token)) {
            headers.put("token", token);
        }
        return post(url, param, headers);
    }

    public static String mapToParams(Map<String, Object> params) {
        if (params == null || params.size() == 0)
            return "";

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            sb.append("&").append(entry.getKey()).append("=").append(String.valueOf(entry.getValue()));
        }

        if (sb.length() > 0) {
            sb.deleteCharAt(0);
            return sb.toString();
        }

        return "";
    }

    public static String post(String url, String param, Map<String, String> headers) throws IOException {
        logger.info("post request url : {}, param : {}", url, param);
        URL uri = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) uri.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setReadTimeout(30000);
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("contentType", "utf-8");
        conn.setRequestProperty("connection", "Keep-Alive");
        conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }

        if (!StringUtils.isEmpty(param)) {
            OutputStream os = conn.getOutputStream();
            os.write(param.getBytes("UTF-8"));
            os.close();
        }

        conn.connect();

        int code = conn.getResponseCode();
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String msg = null;
            while ((msg = br.readLine()) != null) {
                sb.append(msg).append("\n");
            }

            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
        } finally {
            if (br != null)
                br.close();
        }

        if (code != 200) {
            logger.error("request url [ " + url + " ] returns code [ " + code + " ]");
            throw new IOException("request url [ "
                    + url + " ] returns code [ " + code + " ], message [ " + sb.toString() + " ]");
        }

        logger.info("get request url : {}, response : {}", url, sb.toString());
        return sb.toString();
    }

    public static String postJson(String url, String json, String token) throws IOException {
        Map<String, String> headers = new HashMap<>();
        if (!StringUtil.isEmpty(token)) {
            headers.put("token", token);
        }
        return postJson(url, json, headers);
    }

    public static String postJson(String url, String json, Map<String, String> headers) throws IOException {
        logger.info("post json request url : {}, param : {}", url, json);
        URL uri = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) uri.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setReadTimeout(30000);
        conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        conn.setRequestProperty("accept", "application/json");
        conn.setRequestProperty("connection", "Keep-Alive");
        conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }

        if (!StringUtils.isEmpty(json)) {
            OutputStream os = conn.getOutputStream();
            os.write(json.getBytes("UTF-8"));
            os.close();
        }

        conn.connect();

        int code = conn.getResponseCode();
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String msg = null;
            while ((msg = br.readLine()) != null) {
                sb.append(msg).append("\n");
            }

            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
        } finally {
            if (br != null)
                br.close();
        }

        if (code != 200) {
            logger.error("request url [ " + url + " ] returns code [ " + code + " ]");
            throw new IOException("request url [ "
                    + url + " ] returns code [ " + code + " ], message [ " + sb.toString() + " ]");
        }

        logger.info("get request url : {}, response : {}", url, sb.toString());
        return sb.toString();
    }

    /**
     * 文件上传
     *
     * @param contentByte 文件内容
     * @param fileName    文件名称
     * @param mimetype    文件类型
     * @param uuid
     * @param type        类型
     * @return
     */
    public static String uploadFile(String requestUrl, byte[] contentByte, String fileName, String mimetype, String uuid, String type) {
        String httpResult = null;
        String url = null;
        Map<String, String> mapParam = new HashMap<>();
        try {
            //{uuid}/create
            String uploadUrl = requestUrl + uuid + "/" + type;
            mapParam.put("data", Base64Utils.encodeToString(contentByte));
            Map<String, String> attributesParam = new HashMap<>();
            attributesParam.put("filename", fileName);
            Map<String, String> headerpParam = new HashMap<>();
            headerpParam.put("Accept", "application/json");
            headerpParam.put("mimetype", mimetype);
            httpResult = HttpUtil.postJson(uploadUrl, JsonUtil.toJson(mapParam), headerpParam);
            Map<String, Object> mapResult = JsonUtil.toMap(httpResult);
            if (mapResult.containsKey("resultCode") && (int) mapResult.get("resultCode") == 200) {
                url = (String) mapResult.get("url");
                if (StringUtil.isEmpty(url)) {
                    httpResult = url;
                    logger.debug(fileName + " upload fail!" + httpResult);
                } else {
                    url = url + "?mimetype=" + mimetype;
                    logger.debug(fileName + " upload success!" + httpResult);
                }
            } else {
                logger.debug(fileName + " upload fail!" + httpResult);
            }
            logger.debug(JsonUtil.toJson(mapParam));
        } catch (Exception e) {
            try {
                logger.error(JsonUtil.toJson(mapParam));
            } catch (JsonProcessingException e1) {
                logger.error(fileName + " json formatter error!", e);
            }
            logger.error(fileName + " upload fail!" + httpResult, e);
        }
        return url;
    }



    public static String postJsonWithAll(String url, String json, String token) throws IOException {
        Map<String, String> headers = new HashMap<>();
        if (!StringUtil.isEmpty(token)) {
            headers.put("token", token);
        }
        return postJsonWithAll(url, json, headers);
    }

    public static String postJsonWithAll(String url, String json, Map<String, String> headers) throws IOException {
        logger.info("post json request url : {}, param : {}", url, json);
        URL uri = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) uri.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setReadTimeout(30000);
        conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("connection", "Keep-Alive");
        conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }

        if (!StringUtils.isEmpty(json)) {
            OutputStream os = conn.getOutputStream();
            os.write(json.getBytes("UTF-8"));
            os.close();
        }

        conn.connect();

        int code = conn.getResponseCode();
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            if (code == 200) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
            }
            String msg = null;
            while ((msg = br.readLine()) != null) {
                sb.append(msg).append("\n");
            }

            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
        } finally {
            if (br != null)
                br.close();
        }


        logger.info("get request url : {}, response : {}", url, sb.toString());
        return sb.toString();
    }

    public static String postWithRequest(String url, Map<String, String> param, Map<String, String> headers) throws Exception {
        logger.info("post json request url : {}, param : {}", url, JsonUtil.toJson(param));
        URL uri = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) uri.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setReadTimeout(30000);
//        conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
//        conn.setRequestProperty("accept", "*/*");
//        conn.setRequestProperty("connection", "Keep-Alive");
//        conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        conn.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded; charset=UTF-8");

        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        if (param != null) {
            OutputStream os = conn.getOutputStream();
            StringBuffer params = new StringBuffer();
            for(String key : param.keySet()) {
                params.append("&").append(key).append("=").append(URLEncoder.encode(param.get(key), "UTF-8"));
            }
            params.deleteCharAt(0);
            String sendInfo = params.toString();
            os.write(sendInfo.getBytes("UTF-8"));
            os.flush();
            os.close();
        }

        conn.connect();

        int code = conn.getResponseCode();
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            if (code == 200) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            } else {
                br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
            }
            String msg = null;
            while ((msg = br.readLine()) != null) {
                sb.append(msg).append("\n");
            }

            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
        } finally {
            if (br != null)
                br.close();
        }


        logger.info("get request url : {}, response : {}", url, sb.toString());
        return sb.toString();
    }

    /**
     * 获取Http请求结果
     *
     * @param url
     * @param param
     * @return
     */
    public static JSONObject postHttpResult(String url, String param) {
        System.out.println(url + param);
        JSONObject resultObject = null;
        try {
            Map headers = new HashMap();
            headers.put("Accept", "application/json");
            String resultStr = HttpUtil.postJsonWithAll(url, param, headers);
            resultObject = (JSONObject) JSONObject.parse(resultStr);
        } catch (HttpClientErrorException e) {
            logger.error("调用HTTP请求异常:" + url + " \n异常信息:" + e.getMessage());
            resultObject = (JSONObject) JSONObject.parse(e.getResponseBodyAsString());
        } finally {
            return resultObject;
        }
    }

    /**
     * 获取Http请求结果
     *
     * @param url
     * @param param
     * @return
     */
    public static JSONObject getHttpResult(String url, String param) {
        System.out.println(url + param);
        JSONObject resultObject = null;
        try {
            Map headers = new HashMap();
            headers.put("Accept", "application/json");
            String resultStr = get(url, param, headers);
            resultObject = (JSONObject) JSONObject.parse(resultStr);
        } catch (HttpClientErrorException e) {
            logger.error("调用HTTP请求异常:" + url + " \n异常信息:" + e.getMessage());
            resultObject = (JSONObject) JSONObject.parse(e.getResponseBodyAsString());
        } finally {
            return resultObject;
        }
    }


    public static String formatJson(String jsonStr) {
        if (null == jsonStr || "".equals(jsonStr)) return "";
        StringBuilder sb = new StringBuilder();
        char last = '\0';
        char current = '\0';
        int indent = 0;
        for (int i = 0; i < jsonStr.length(); i++) {
            last = current;
            current = jsonStr.charAt(i);
            switch (current) {
                case '{':
                case '[':
                    sb.append(current);
                    sb.append('\n');
                    indent++;
                    addIndentBlank(sb, indent);
                    break;
                case '}':
                case ']':
                    sb.append('\n');
                    indent--;
                    addIndentBlank(sb, indent);
                    sb.append(current);
                    break;
                case ',':
                    sb.append(current);
                    if (last != '\\') {
                        sb.append('\n');
                        addIndentBlank(sb, indent);
                    }
                    break;
                default:
                    sb.append(current);
            }
        }

        return sb.toString();
    }

    /**
     * 添加space
     * @param sb
     * @param indent
     * @author   lizhgb
     * @Date   2015-10-14 上午10:38:04
     */
    private static void addIndentBlank(StringBuilder sb, int indent) {
        for (int i = 0; i < indent; i++) {
            sb.append('\t');
        }
    }
    public static void main(String[] args) {
        String mapbar = "http://jfx.mapbar.com/api/qingqi/tocapp/test";
        String chechang = "http://jfx.qdfaw.com:8081/api/qingqi/servicestation/test";
        StringBuilder sb = new StringBuilder();
//        sb.append("SELECT\n" +
//                "a.*\n" +
//                "FROM\n" +
//                "(select \n" +
//                "count(1) cnt,\n" +
//                "wo_code\n" +
//                "FROM\n" +
//                "tbl_work_order \n" +
//                "GROUP BY wo_code) a\n" +
//                "WHERE a.cnt > 1");
//        sb.append("UPDATE tbl_work_order SET service_station_id = '340' where wo_code = '20170420122142000013'");
        sb.append("select * from tbl_work_order where wo_code = '20170502084326000002'");
//        sb.append("  delete from tbl_work_order where id = '8ae6dff15bb7560c015bc69ca46401b7';");
//        sb.append("  delete from tbl_work_order where id = '8ae6dff35af8f695015b42358df525f8';");
        Map<String, String> param = new HashMap<String, String>();
        param.put("sql", sb.toString().toLowerCase());
        try {
            Long start = new Date().getTime();
            System.out.println("接口调用START:" + start);
            JSONObject resultJson = getHttpResult(chechang, JsonUtil.toJson(param));
            Long end = new Date().getTime();
            System.out.println("接口调用END:" + end);
            System.out.println("接口耗时：" + (end - start));
                JSONArray array =  resultJson.getJSONArray("data");
                if (array != null && array.size()>0) {
                    System.out.println("检索结果：" + array.size());
                    for (Object obj: array) {
                        System.out.println(JSON.toJSONString(obj));
                    }
                }

//            String result = resultJson.toJSONString();
//            System.out.println(formatJson(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
