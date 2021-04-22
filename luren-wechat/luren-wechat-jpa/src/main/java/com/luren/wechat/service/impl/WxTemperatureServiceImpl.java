package com.luren.wechat.service.impl;

import cn.hutool.core.date.DateUtil;
import com.luren.wechat.entity.WxTemperature;
import com.luren.wechat.entity.WxUser;
import com.luren.wechat.repository.WxTemperatureRepository;
import com.luren.wechat.service.IWxTemperatureService;
import com.luren.wechat.service.dto.WxTemperatureDto;
import com.luren.wechat.service.dto.WxUserDto;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.luren.wechat.common.constant.Constant.*;

/**
 * @author LHH
 */

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class WxTemperatureServiceImpl implements IWxTemperatureService {
    @Resource
    private WxTemperatureRepository wxTemperatureRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "WxTemperature",key = "#wxTemperatureDto.wxUser.openId")
    public Boolean add(WxTemperatureDto wxTemperatureDto) {
        WxTemperature wt = wxTemperatureDto.getWxTemperature();
        WxUser user = wxTemperatureDto.getWxUser();
        wt.setOpenId(user.getOpenId());
        wt.setCreateBy(user.getName());
        if(wt.getTemperature() >= MAX_TEMPERATURE || wt.getTemperature() <= MIN_TEMPERATURE){
            wt.setStatus(2);
        }else if(wt.getTemperature() >= MIN_TEMPERATURE && wt.getTemperature() <= MIN_HEALTH_TEMPERATURE){
            wt.setStatus(1);
        }else if(wt.getTemperature() >= MAX_TEMPERATURE && wt.getTemperature() <= MAX_HEALTH_TEMPERATURE){
            wt.setStatus(1);
        }else {
            wt.setStatus(0);
        }
        Date date = DateUtil.date(System.currentTimeMillis());
        wt.setCreateTime(date);
        WxTemperature wxTemperature = wxTemperatureRepository.save(wt);
        if(wxTemperature.equals(wt)){
            wxTemperatureDto.setWxTemperature(wxTemperature);
            return true;
        }else{
            return false;
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Cacheable(value = "WxTemperature",key = "#wxUserDto.wxUser.openId",unless = "#result == null")
    public List<WxTemperature> findAllByOpenId(WxUserDto wxUserDto) {
        WxUser user = wxUserDto.getWxUser();
        return wxTemperatureRepository.findAllByOpenId(user.getOpenId());

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer healthStatus(Integer days,String openId) {
        List<Integer> lists = new ArrayList<>();
        List<WxTemperature> wxTemperatures = wxTemperatureRepository.getHealthStatus(days,openId);
        for (WxTemperature wxTemperature : wxTemperatures){
            lists.add(wxTemperature.getStatus());
        }
        return Collections.max(lists);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean getToDay(WxUserDto wxUserDto) {
        WxUser user = wxUserDto.getWxUser();
        List<WxTemperature> wxTemperatures = wxTemperatureRepository.getToDay(user.getOpenId());
        return !wxTemperatures.isEmpty();
    }
}
