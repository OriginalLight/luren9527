package com.luren.wechat.controller;

import com.luren.wechat.common.base.HttpResult;
import com.luren.wechat.service.IWxTemperatureService;
import com.luren.wechat.service.dto.WxTemperatureDto;
import com.luren.wechat.service.dto.WxUserDto;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author LHH
 */
@RestController
@RequestMapping("/wx/temperature")
public class WxTemperatureController {

    @Resource
    private IWxTemperatureService iWxTemperatureService;

    @PostMapping(value = "add")
    public HttpResult add(@RequestBody WxTemperatureDto wxTemperatureDto){
        if(iWxTemperatureService.add(wxTemperatureDto)){
            return HttpResult.success();
        }else {
            return HttpResult.error();
        }
    }

    @PostMapping(value = "findAllByOpenId")
    private HttpResult findAllByOpenId(@RequestBody WxUserDto wxUserDto){
        return HttpResult.success(iWxTemperatureService.findAllByOpenId(wxUserDto));
    }

    @PostMapping(value = "toDay")
    private HttpResult toDay(@RequestBody WxUserDto wxUserDto){
        if(iWxTemperatureService.getToDay(wxUserDto)){
            return HttpResult.success();
        }else {
            return HttpResult.error();
        }
    }

}
