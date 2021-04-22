package com.luren.wechat.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luren.wechat.domain.WxUser;
import com.luren.wechat.mapper.WxUserMapper;
import com.luren.wechat.service.IWxUserService;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author LHH
 */
@Service
public class WxUserServiceImpl extends ServiceImpl<WxUserMapper, WxUser> implements IWxUserService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean create(WxUser user) {
        return save(user);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean update(WxUser user) {
        return updateById(user);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public WxUser findByOpenId(String openId) {
        return getById(openId);
    }
}
