package com.luren.wechat.repository;

import com.luren.wechat.entity.WxLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author LHH
 */
public interface WxLeaveRepository extends JpaRepository<WxLeave, String>, JpaSpecificationExecutor<WxLeave> {

    /**
     * 根据时间返回
     * @param openId
     * @return List<WxLeave>
     */
    @Query(value = "FROM WxLeave WHERE openId = ?1 ORDER BY createTime DESC")
    List<WxLeave> findAllByOpenId(String openId);
}