package com.luren.wechat.service.impl;

import com.luren.wechat.entity.WxUser;
import com.luren.wechat.repository.WxUserRepository;
import com.luren.wechat.service.IWxUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * 用户接口实现类
 *
 * @author LHH
 */


@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class WxUserServiceImpl implements IWxUserService {

    private final WxUserRepository wxUserRepository;

    public WxUserServiceImpl(WxUserRepository wxUserRepository) {
        this.wxUserRepository = wxUserRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public WxUser create(WxUser user) {
        return wxUserRepository.save(user);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public WxUser update(WxUser user) {
        return wxUserRepository.save(user);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public WxUser findByOpenId(String openId) {
        return wxUserRepository.findByOpenId(openId);
    }

}
