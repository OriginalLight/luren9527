package com.luren.wechat.controller;

import com.luren.wechat.common.base.HttpResult;
import com.luren.wechat.service.IWxLeaveService;
import com.luren.wechat.service.dto.WxLeaveDto;
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
@RequestMapping("/wx/leave")
public class WxLeaveController {
    @Resource
    private IWxLeaveService iWxLeaveService;

    @PostMapping(value = "add")
    public HttpResult add(@RequestBody WxLeaveDto wxUserDto){
        if(iWxLeaveService.add(wxUserDto)){
            return HttpResult.success();
        }else {
            return HttpResult.error();
        }
    }

    @PostMapping(value = "findAllByOpenId")
    private HttpResult findAllByOpenId(@RequestBody WxUserDto wxUserDto){
        return HttpResult.success(iWxLeaveService.findAllByOpenId(wxUserDto));
    }

    @PostMapping(value = "reSet")
    private HttpResult reSet(@RequestBody WxLeaveDto wxUserDto){
        if(iWxLeaveService.reSet(wxUserDto)){
            return HttpResult.success();
        }else {
            return HttpResult.error();
        }
    }
}
