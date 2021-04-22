package com.luren.wechat.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.luren.wechat.service.IWxMiniApi;
import com.luren.wechat.utils.WeChatUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 微信小程序Api接口实现类
 *
 * @author LHH
 */

@Service
public class WxMiniApiImpl implements IWxMiniApi {

    @Override
    public JSONObject authCode2Session(String appId, String secret, String jsCode) {

        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&secret=" + secret + "&js_code=" + jsCode + "&grant_type=authorization_code";
        String str = WeChatUtil.httpRequest(url, "GET", null);
        if (StringUtils.isEmpty(str)) {
            return null;
        } else {
            return JSONObject.parseObject(str);
        }

    }
}
