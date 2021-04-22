package com.luren.wechat.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luren.wechat.domain.WxUser;
import com.luren.wechat.mapper.WxUserMapper;
import com.luren.wechat.service.IWxUserService;

/**
 * @author LHH
 */
@Service
public class WxUserServiceImpl extends ServiceImpl<WxUserMapper, WxUser> implements IWxUserService {

}

