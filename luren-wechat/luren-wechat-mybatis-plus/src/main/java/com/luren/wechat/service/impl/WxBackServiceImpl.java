package com.luren.wechat.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.luren.wechat.domain.WxBack;
import com.luren.wechat.domain.WxUser;
import com.luren.wechat.mapper.WxBackMapper;
import com.luren.wechat.service.IWxBackService;
import com.luren.wechat.service.IWxTemperatureService;
import com.luren.wechat.service.dto.WxBackDto;
import com.luren.wechat.service.dto.WxUserDto;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class WxBackServiceImpl extends ServiceImpl<WxBackMapper, WxBack> implements IWxBackService {

    @Resource
    private IWxTemperatureService iWxTemperatureService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "WxBack",key = "#wxBackDto.wxUser.openId")
    public Boolean add(WxBackDto wxBackDto) {
        WxBack wb = wxBackDto.getWxBack();
        WxUser user = wxBackDto.getWxUser();
        wb.setOpenId(user.getOpenId());
        wb.setCreateBy(user.getName());
        wb.setStatus(iWxTemperatureService.healthStatus(14, user.getOpenId()));
        wb.setCheckStatus(0);
        return save(wb);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Cacheable(value = "WxBack",key = "#wxUserDto.wxUser.openId",unless = "#result == null")
    public List<WxBack> findAllByOpenId(WxUserDto wxUserDto) {
        QueryWrapper<WxBack> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(WxBack::getOpenId, wxUserDto.getWxUser().getOpenId())
                .orderByDesc(WxBack::getCreateTime);
        return list(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = "WxBack",key = "#wxBackDto.wxUser.openId")
    public Boolean reSet(WxBackDto wxBackDto) {
       return removeById(wxBackDto.getWxBack().getId());
    }
}
