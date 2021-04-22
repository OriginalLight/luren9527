package com.luren.wechat.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luren.wechat.domain.WxNotice;
import com.luren.wechat.mapper.WxNoticeMapper;
import com.luren.wechat.service.IWxNoticeService;

/**
 * @author LHH
 */
@Service
public class WxNoticeServiceImpl extends ServiceImpl<WxNoticeMapper, WxNotice> implements IWxNoticeService {

}

