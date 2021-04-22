package com.luren.wechat.repository;

import com.luren.wechat.entity.WxUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;



/**
 * @author LHH
 */
public interface WxUserRepository extends JpaRepository<WxUser, String>, JpaSpecificationExecutor<WxUser> {
    /**
     * 用于微信注册用户查找：根据openId查找用户
     * @param openId 微信openId
     * @return openId对应的用户
     */
    WxUser findByOpenId(String openId);




}