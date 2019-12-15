package cn.edu.jxau.dbn.util.jxauApi.impl;

import cn.edu.jxau.dbn.util.jxauApi.JxauUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


@Component
public class JxauUtilImpl implements JxauUtil {


    /**
     * 试用教务处登录密码获取数据
     *
     * @param userCode 账号
     * @param password 密码
     * @return Map
     * @throws IOException
     */
    @Override
    public Map getStudentData(String userCode, String password) throws IOException {
        Map map = this.getToken();
        String tokenStr = "{UserCode:\'" + userCode + "\',Pwd:\'" + password + "\',Token:\'" + map.get("Data") + "\'}";
        //获取学生的信息
        String dataUrl = "http://jwzs.jxau.edu.cn/Authorization/GetData";
        String data = this.getInterfaceData(dataUrl, JSONObject.fromObject(tokenStr));
        return new ObjectMapper().readValue(data, Map.class);
    }


    /**
     * 获取参数
     *
     * @return Map
     * @throws IOException
     */
    private Map getToken() throws IOException {
        String url = "http://jwzs.jxau.edu.cn/Authorization/GetToken";
        String param = "{signKey:'ROwHiKBA',signValue:'v0X9Npv1fHE3zDTl'}";
        String result = getInterfaceData(url, JSONObject.fromObject(param));
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(result, Map.class);

    }


    /**
     * 获取token数据
     *
     * @param urlAddress url地址
     * @param param      参数
     * @return String
     */
    private String getInterfaceData(String urlAddress, JSONObject param) {
        if (urlAddress == null || param == null || urlAddress.equals("")) {
            throw new RuntimeException("数据异常");
        }
        //注册接口的地址
        URL url;
        InputStream input = null;
        HttpURLConnection conn = null;
        PrintWriter out = null;
        String res = "";
        try {
            url = new URL(urlAddress);
            //打开连接
            conn = (HttpURLConnection) url.openConnection();
            //设置请求方法
            conn.setRequestMethod("POST");
            //设置通用属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            //设置输入输出
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //获得输出流
            out = new PrintWriter(conn.getOutputStream());
            //发送请求数据
            out.write(param.toString());
            out.flush();
            //获得输入流
            input = conn.getInputStream();
            BufferedInputStream br = new BufferedInputStream(input);
            //读取信息  使用字节数组 防止乱码问题
            byte[] bytes = new byte[1024];
            int len = br.read(bytes, 0, bytes.length);
            /* 关闭流 */
            input.close();
            //断开连接
            conn.disconnect();
            res = new String(bytes, 0, len, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("POST请求失败");
            e.printStackTrace();
        } finally {
            //关闭流
            try {
                assert input != null;
                input.close();
                //断开连接
                conn.disconnect();
                //关闭流
                out.close();
            } catch (IOException e) {
                System.err.println("资源连接关闭失败");
                e.printStackTrace();
            }
        }
        return res;
    }


    /**
     * 这个方法使用的是身份证后六位
     *
     * @param userCode 账号
     * @param password 密码
     * @return Map
     * @throws IOException
     */
    @Override
    public Map getStudentBaseData(Integer userCode, String password) throws IOException {
        String url = "https://wlz.dev.yunwucm.com/api/Check/Login";
        Map<String, String> map = new HashMap<>();
        map.put("account", Integer.toString(userCode));
        map.put("password", password);
        map.put("appKey", "jxauapi");
        String result = this.getInterfaceData(url, JSONObject.fromObject(map));
        return new ObjectMapper().readValue(result, Map.class);
    }
}
