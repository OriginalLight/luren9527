package com.luren.wechat.service.impl;

import com.luren.wechat.entity.WxNotice;
import com.luren.wechat.repository.WxNoticeRepository;
import com.luren.wechat.service.IWxNoticeService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WxNoticeServiceImpl implements IWxNoticeService {

    @Resource
    private WxNoticeRepository wxNoticeRepository;

    @Override
    @Cacheable(value = "notice")
    public List<WxNotice> findAll() {
        return wxNoticeRepository.findAllByTime();
    }
}
