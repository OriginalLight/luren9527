package com.luren.wechat.repository;

import com.luren.wechat.entity.SysDept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 * @author LHH
 */

public interface SysDeptRepository extends JpaRepository<SysDept, Long>, JpaSpecificationExecutor<SysDept> {

}