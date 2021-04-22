package com.luren.wechat.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.luren.wechat.domain.WxUser;
import com.luren.wechat.service.IWxTemperatureService;
import com.luren.wechat.service.dto.WxTripDto;
import com.luren.wechat.service.dto.WxUserDto;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luren.wechat.mapper.WxTripMapper;
import com.luren.wechat.domain.WxTrip;
import com.luren.wechat.service.IWxTripService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author LHH
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class WxTripServiceImpl extends ServiceImpl<WxTripMapper, WxTrip> implements IWxTripService {

    @Resource
    private IWxTemperatureService iWxTemperatureService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "WxTrip",key = "#wxTripDto.wxUser.openId")
    public Boolean add(WxTripDto wxTripDto) {
        WxTrip wt = wxTripDto.getWxTrip();
        WxUser user = wxTripDto.getWxUser();
        wt.setOpenId(user.getOpenId());
        wt.setCreateBy(user.getName());
        wt.setStatus(iWxTemperatureService.healthStatus(14,user.getOpenId()));
        return save(wt);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Cacheable(value = "WxTrip",key = "#wxUserDto.wxUser.openId",unless = "#result == null")
    public List<WxTrip> findAllByOpenId(WxUserDto wxUserDto) {
        QueryWrapper<WxTrip> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(WxTrip::getOpenId, wxUserDto.getWxUser().getOpenId())
                .orderByDesc(WxTrip::getCreateTime);
        return list(queryWrapper);
    }
}
