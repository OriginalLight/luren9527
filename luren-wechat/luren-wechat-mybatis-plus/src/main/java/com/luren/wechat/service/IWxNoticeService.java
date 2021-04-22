package com.luren.wechat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luren.wechat.domain.WxNotice;

import java.util.List;

/**
 * @author LHH
 */
public interface IWxNoticeService extends IService<WxNotice>{
    /**
     * 返回所有的公告
     * @return List<WxNotice>
     */
    List<WxNotice> findAll();

}
