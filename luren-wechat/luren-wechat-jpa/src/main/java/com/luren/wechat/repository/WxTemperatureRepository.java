package com.luren.wechat.repository;

import com.luren.wechat.entity.WxTemperature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author LHH
 */
public interface WxTemperatureRepository extends JpaRepository<WxTemperature, String>, JpaSpecificationExecutor<WxTemperature> {

    /**
     * 根据时间返回
     * @param openId
     * @return List<WxTemperature>
     */
    @Query(value = "FROM WxTemperature WHERE openId = ?1 ORDER BY createTime DESC")
    List<WxTemperature> findAllByOpenId(String openId);

    /**
     * 获得days的数据
     * @param days
     * @param openid
     * @return
     */
    @Query(value = "SELECT * FROM `wx_temperature` WHERE DATE_SUB(CURDATE(), INTERVAL ?1 DAY) <= DATE( create_time ) AND open_id = ?2" ,nativeQuery = true)
    List<WxTemperature> getHealthStatus(Integer days,String openid);

    /**
     * 获得今天的数据
     * @param openId
     * @return List<WxTemperature>
     */
    @Query(value = "SELECT * FROM wx_temperature WHERE TO_DAYS(create_time) = TO_DAYS(NOW()) AND open_id = ?1",nativeQuery = true)
    List<WxTemperature> getToDay(String openId);

}