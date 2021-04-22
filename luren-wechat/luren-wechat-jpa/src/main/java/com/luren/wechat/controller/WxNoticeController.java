package com.luren.wechat.controller;

import com.luren.wechat.common.base.HttpResult;
import com.luren.wechat.service.IWxNoticeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author LHH
 */
@RestController
@RequestMapping("/wx/notice")
public class WxNoticeController {

    @Resource
    private IWxNoticeService iWxNoticeService;

    @GetMapping(value = "findAll")
    public HttpResult findAll(){
        return HttpResult.success(iWxNoticeService.findAll());
    }
}
