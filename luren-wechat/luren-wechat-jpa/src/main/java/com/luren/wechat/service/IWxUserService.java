package com.luren.wechat.service;

import com.luren.wechat.entity.WxUser;

/**
 * 用户信息接口
 *
 * @author LHH
 */
public interface IWxUserService {

    /**
     * 增加用户
     *
     * @param user 待新增的用户
     * @return 增加成功的用户
     */
    WxUser create(WxUser user);


    /**
     * 修改用户
     *
     * @param user 待修改的用户
     * @return 修改成功的用户
     */
    WxUser update(WxUser user);



    /**
     * 用于微信注册用户查找：根据openId查找用户
     *
     * @param openId 微信openId
     * @return openId对应的用户
     */
    WxUser findByOpenId(String openId);

}
