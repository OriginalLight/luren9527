package com.luren.wechat.repository;

import com.luren.wechat.entity.WxTrip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author LHH
 */
public interface WxTripRepository extends JpaRepository<WxTrip, String>, JpaSpecificationExecutor<WxTrip> {

    /**
     * 返回用户所有数据
     * @param openId
     * @return List<WxTrip>
     */
    @Query(value = "FROM WxTrip WHERE openId = ?1 ORDER BY createTime DESC")
    List<WxTrip> findAllByOpenId(String openId);
}