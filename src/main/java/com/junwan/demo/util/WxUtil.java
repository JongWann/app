package com.junwan.demo.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.junwan.demo.enums.ResultEnums;
import com.junwan.demo.exception.BaseException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class WxUtil {
    private Logger logger = LoggerFactory.getLogger(WxUtil.class);

    /**
     * 获取访问token
     * @param server
     * @param appID
     * @param appsecret
     * @return
     * @throws IOException
     */
    public String getToken(String server, String appID, String appsecret) throws IOException {
        //请求接口获取token
        String url = String.format("%s/token?grant_type=client_credential&appid=%s&secret=%s",
                server, appID, appsecret);
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse httpResponse = httpClient.execute(httpGet);
        HttpEntity httpEntity = httpResponse.getEntity();
        String entity = EntityUtils.toString(httpEntity);
        logger.info("response:{}", entity);
        //解析响应内容
        JsonParser jsonParser = new JsonParser();
        JsonObject object = jsonParser.parse(entity).getAsJsonObject();
        if( httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            if ( object.get("errcode") != null ) {
                logger.error("获取token失败,错误代码{}",object.get("errcode").getAsString());
                throw new BaseException(ResultEnums.WX_TOKEN_ERR);
            }else{
                String token = object.get("access_token").getAsString();
                logger.info("获取到token={}",token);
                return token;
            }
        }
        return null;
    }

    /**
     * 获取用户列表 获取用户列表之前需要先认证，否则会返回接口未认证
     * @return
     */
    public List getUserList(String server, String token, String nextOpenId) throws IOException {
        String url = String.format("%s/user/get?access_token=%s&next_openid=%s",server,token,nextOpenId);
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse httpResponse = httpClient.execute(httpGet);
        HttpEntity httpEntity = httpResponse.getEntity();
        String entity = EntityUtils.toString(httpEntity);
        logger.info("response:{}", entity);
        return null;
    }

    public static void main(String[] args) throws IOException {
        String server = "https://sh.api.weixin.qq.com/cgi-bin";
        String appId = "wxe187b6f50a9d4fda";
        String appSecret = "0646df85a5b2998862e1114b99a7e61d";

        WxUtil wxUtil = new WxUtil();
        String token = wxUtil.getToken(server, appId, appSecret);
        wxUtil.getUserList(server, token, "");

    }


}
