package com.luren.wechat.service.dto;

import com.luren.wechat.domain.WxLeave;
import com.luren.wechat.domain.WxUser;
import lombok.Getter;
import lombok.Setter;

/**
 * @author LHH
 */

@Getter
@Setter
public class WxLeaveDto {

    private WxLeave wxLeave;

    private WxUser wxUser;
}
