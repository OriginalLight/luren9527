package com.luren.wechat.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.luren.wechat.domain.WxTrip;
import com.luren.wechat.domain.WxUser;
import com.luren.wechat.service.dto.WxTemperatureDto;
import com.luren.wechat.service.dto.WxUserDto;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luren.wechat.mapper.WxTemperatureMapper;
import com.luren.wechat.domain.WxTemperature;
import com.luren.wechat.service.IWxTemperatureService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import static com.luren.wechat.common.constant.Constant.*;

/**
 * @author LHH
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class WxTemperatureServiceImpl extends ServiceImpl<WxTemperatureMapper, WxTemperature> implements IWxTemperatureService {


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
        return save(wxTemperatureDto.getWxTemperature());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Cacheable(value = "WxTemperature",key = "#wxUserDto.wxUser.openId",unless = "#result == null")
    public List<WxTemperature> findAllByOpenId(WxUserDto wxUserDto) {
        QueryWrapper<WxTemperature> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(WxTemperature::getOpenId, wxUserDto.getWxUser().getOpenId())
                .orderByDesc(WxTemperature::getCreateTime);
        return list(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer healthStatus(Integer days, String openId) {
        Date date = new Date();
        DateTime startTime = DateUtil.beginOfDay(date);
        startTime = DateUtil.offsetDay(startTime,-days);
        QueryWrapper<WxTemperature> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(WxTemperature::getOpenId,openId)
                .gt(WxTemperature::getCreateTime,startTime);
        List<WxTemperature> wxTemperatures = list(queryWrapper);
        Integer i = 0;
        for (WxTemperature wxTemperature : wxTemperatures) {
            Integer j = wxTemperature.getStatus();
            if(j>i){
                i = j;
            }
        }
        return i;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean getToDay(WxUserDto wxUserDto) {
        LocalDate createTime = LocalDate.now();
        LocalDateTime startTime = LocalDateTime.of(createTime, LocalTime.of(0, 0, 0));
        LocalDateTime endTime = LocalDateTime.of(createTime, LocalTime.of(23, 59, 59));
        QueryWrapper<WxTemperature> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .between(WxTemperature::getCreateTime,startTime,endTime)
                .eq(WxTemperature::getOpenId,wxUserDto.getWxUser().getOpenId());
        return !list(queryWrapper).isEmpty();
    }
}
