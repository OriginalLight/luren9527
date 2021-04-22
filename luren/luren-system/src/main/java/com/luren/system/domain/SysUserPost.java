package com.luren.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 用户和岗位关联 sys_user_post
 *
 * @author luren
 */

@Data
@NoArgsConstructor
@Accessors(chain = true)
@TableName("sys_user_post")
public class SysUserPost {
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 岗位ID
     */
    private Long postId;

}
