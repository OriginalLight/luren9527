package com.luren.wechat.controller;

import com.luren.wechat.common.base.HttpResult;
import com.luren.wechat.service.IWxTripService;
import com.luren.wechat.service.dto.WxTripDto;
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
@RequestMapping("/wx/trip")
public class WxTripController {

    @Resource
    private IWxTripService iWxTripService;

    @PostMapping(value = "add")
    public HttpResult add(@RequestBody WxTripDto wxTripDto){
        if( iWxTripService.add(wxTripDto)){
            return HttpResult.success();
        }else {
            return HttpResult.error();
        }
    }

    @PostMapping(value = "findAllByOpenId")
    private HttpResult findAllByOpenId(@RequestBody WxUserDto wxUserDto){
        return HttpResult.success( iWxTripService.findAllByOpenId(wxUserDto));
    }
}
