package com.luren.wechat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.luren.wechat.domain.SysDept;
import com.luren.wechat.domain.TreeSelect;


import java.util.List;

/**
 * 部门管理 服务层
 *
 * @author luren
 */
public interface ISysDeptService extends IService<SysDept> {

    /**
     * 返回树结构
     * @return
     */
    List<TreeSelect> getDeptTree();


}
