package com.luren.wechat.service;

import com.luren.wechat.common.base.TreeSelect;
import com.luren.wechat.entity.SysDept;

import java.util.List;

/**
 * @author LHH
 */
public interface ISysDeptService {
    /**
     * @param depts
     * @return SysDept
     */
    List<SysDept> buildDeptTree(List<SysDept> depts);

    /**
     *
     * @param depts
     * @return TreeSelect
     */
    List<TreeSelect> buildDeptTreeSelect(List<SysDept> depts);

    /**
     * 返回树结构
     * @return
     */
    List<TreeSelect> getDeptTree();
}
