package com.luren.wechat.service;

import com.luren.wechat.entity.WxNotice;

import java.util.List;

/**
 * @author LHH
 */
public interface IWxNoticeService {
    /**
     * 返回所有的公告
     * @return List<WxNotice>
     */
    List<WxNotice> findAll();
}
