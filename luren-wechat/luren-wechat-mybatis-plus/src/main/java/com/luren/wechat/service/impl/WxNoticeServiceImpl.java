package com.luren.wechat.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luren.wechat.domain.WxNotice;
import com.luren.wechat.mapper.WxNoticeMapper;
import com.luren.wechat.service.IWxNoticeService;

import java.util.List;

/**
 * @author LHH
 */
@Service
public class WxNoticeServiceImpl extends ServiceImpl<WxNoticeMapper, WxNotice> implements IWxNoticeService {

    @Override
    @Cacheable(value = "notice")
    public List<WxNotice> findAll() {
        return list();
    }
}
