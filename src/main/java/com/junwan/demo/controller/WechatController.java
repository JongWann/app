package com.junwan.demo.controller;

import com.junwan.demo.util.WxUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("wechat")
public class WechatController {

    private Logger logger = LoggerFactory.getLogger(WechatController.class);

    @Value("${wx.token}")
    private String token;
    @Value("${wx.server}")
    private String server;
    @Value("${wx.appID}")
    private String appID;
    @Value("${wx.appsecret}")
    private String appsecret;

    /**
     * 微信认证接口
     * @param request
     * @return
     */
    @RequestMapping(value = "auth", method = RequestMethod.GET)
    public String auth(HttpServletRequest request) {

        String signature = request.getParameter("signature");
        String echostr = request.getParameter("echostr");
        String nonce = request.getParameter("nonce");
        String timestamp = request.getParameter("timestamp");
        //1.将参数进行字典排序
        String [] strs = {nonce,timestamp,token};
        List<String> list = Arrays.asList(strs);
        Collections.sort(list);
        //2.排序后拼接字符串
        StringBuffer param = new StringBuffer();
        for (String str: list) {
            param.append(str);
        }
        //3.进行sha1加密
        String sign = DigestUtils.sha1Hex(param.toString());
        if(signature.equals(sign)){
            return echostr;
        }else{
            return null;
        }
    }

    @RequestMapping(value = "token", method = RequestMethod.GET)
    public String getToken() throws IOException {
        WxUtil wxUtil = new WxUtil();
        String token = wxUtil.getToken(server, appID, appsecret);
        return token;
    }

    /**
     * 群发预览接口
     * @return
     */
    @RequestMapping(value = "messagePreview", method = RequestMethod.POST)
    public Object messagePreview() throws Exception {
        WxUtil wxutil = new WxUtil();
        String token = wxutil.getToken(server, appID, appsecret);
        String url = String.format("%s/message/mass/preview?access_token=%s",server,token);
        logger.info("url:{}",url);
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type","application/json");
        String params = "";
        StringEntity entity = new StringEntity(params, "UTF-8");
        return null;
    }
}
