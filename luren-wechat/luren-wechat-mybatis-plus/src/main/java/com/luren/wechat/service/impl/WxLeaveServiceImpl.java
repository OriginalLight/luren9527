package com.luren.wechat.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.luren.wechat.domain.WxUser;
import com.luren.wechat.service.IWxTemperatureService;
import com.luren.wechat.service.dto.WxLeaveDto;
import com.luren.wechat.service.dto.WxUserDto;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luren.wechat.mapper.WxLeaveMapper;
import com.luren.wechat.domain.WxLeave;
import com.luren.wechat.service.IWxLeaveService;
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
public class WxLeaveServiceImpl extends ServiceImpl<WxLeaveMapper, WxLeave> implements IWxLeaveService {

    @Resource
    private IWxTemperatureService iWxTemperatureService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "WxLeave",key = "#wxLeaveDto.wxUser.openId")
    public Boolean add(WxLeaveDto wxLeaveDto) {
        WxLeave wl = wxLeaveDto.getWxLeave();
        WxUser user = wxLeaveDto.getWxUser();
        wl.setOpenId(user.getOpenId());
        wl.setCreateBy(user.getName());
        wl.setStatus(iWxTemperatureService.healthStatus(14, user.getOpenId()));
        wl.setCheckStatus(0);
        return save(wl);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Cacheable(value = "WxLeave",key = "#wxUserDto.wxUser.openId",unless = "#result == null")
    public List<WxLeave> findAllByOpenId(WxUserDto wxUserDto) {
        QueryWrapper<WxLeave> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(WxLeave::getOpenId, wxUserDto.getWxUser().getOpenId())
                .orderByDesc(WxLeave::getCreateTime);

        return list(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "WxLeave",key = "#wxLeaveDto.wxUser.openId")
    public Boolean reSet(WxLeaveDto wxLeaveDto) {
        return removeById(wxLeaveDto.getWxLeave().getId());
    }
}
