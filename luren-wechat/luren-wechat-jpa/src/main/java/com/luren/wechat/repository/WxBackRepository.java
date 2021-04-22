package com.luren.wechat.repository;

import com.luren.wechat.entity.WxBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author LHH
 */
public interface WxBackRepository extends JpaRepository<WxBack, String>, JpaSpecificationExecutor<WxBack> {
    /**
     * 根据时间返回
     * @param openId
     * @return List<WxBack>
     */
    @Query(value = "FROM WxBack WHERE openId = ?1 ORDER BY createTime DESC")
    List<WxBack> findAllByOpenId(String openId);
}