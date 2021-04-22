package com.luren.wechat.controller;

import com.luren.wechat.common.base.HttpResult;
import com.luren.wechat.service.ISysDeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @author LHH
 */

@Slf4j
@RestController
@RequestMapping("/system/dept")
public class SysDeptController {

    @Resource
    private ISysDeptService iSysDeptService ;


    @GetMapping("/tree")
    public HttpResult tree() {
        return HttpResult.success(iSysDeptService.getDeptTree());
    }

}
