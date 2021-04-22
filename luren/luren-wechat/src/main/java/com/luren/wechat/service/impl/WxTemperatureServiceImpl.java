package com.luren.wechat.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luren.wechat.mapper.WxTemperatureMapper;
import com.luren.wechat.domain.WxTemperature;
import com.luren.wechat.service.IWxTemperatureService;

/**
 * @author LHH
 */
@Service
public class WxTemperatureServiceImpl extends ServiceImpl<WxTemperatureMapper, WxTemperature> implements IWxTemperatureService {

}

