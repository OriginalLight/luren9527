package com.luren.wechat.controller;

import com.luren.wechat.common.base.HttpResult;
import com.luren.wechat.service.IWxBackService;
import com.luren.wechat.service.dto.WxBackDto;
import com.luren.wechat.service.dto.WxUserDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author LHH
 */
@RestController
@RequestMapping("/wx/back")
public class WxBackController {
    @Resource
    private IWxBackService iWxBackService;

    @PostMapping(value = "add")
    public HttpResult add(@RequestBody WxBackDto wxBackDto){
        if(iWxBackService.add(wxBackDto)){
            return HttpResult.success();
        }else {
            return HttpResult.error();
        }
    }

    @PostMapping(value = "findAllByOpenId")
    private HttpResult findAllByOpenId(@RequestBody WxUserDto wxUserDto){
        return HttpResult.success(iWxBackService.findAllByOpenId(wxUserDto));
    }

    @PostMapping(value = "reSet")
    private HttpResult reSet(@RequestBody  WxBackDto wxBackDto){
        if(iWxBackService.reSet(wxBackDto)){
            return HttpResult.success();
        }else {
            return HttpResult.error();
        }
    }
}
