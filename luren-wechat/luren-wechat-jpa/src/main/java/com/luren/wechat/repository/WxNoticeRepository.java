package com.luren.wechat.repository;

import com.luren.wechat.entity.WxNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WxNoticeRepository extends JpaRepository<WxNotice, String>, JpaSpecificationExecutor<WxNotice> {

    @Query(value = "FROM WxNotice ORDER BY createTime DESC")
    List<WxNotice> findAllByTime();

}