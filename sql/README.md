# 数据库表目录
 | 序号 | 表名 | 表说明 | 
 | :---: | :---: | :---: | 
 | 1 | [sys_config](#sys_config) | 参数配置表 | 
 | 2 | [sys_dept](#sys_dept) | 部门表 | 
 | 3 | [sys_dict_data](#sys_dict_data) | 字典数据表 | 
 | 4 | [sys_dict_type](#sys_dict_type) | 字典类型表 | 
 | 5 | [sys_job](#sys_job) | 定时任务调度表 | 
 | 6 | [sys_job_log](#sys_job_log) | 定时任务调度日志表 | 
 | 7 | [sys_logininfor](#sys_logininfor) | 系统访问记录 | 
 | 8 | [sys_menu](#sys_menu) | 菜单权限表 | 
 | 9 | [sys_notice](#sys_notice) | 通知公告表 | 
 | 10 | [sys_oper_log](#sys_oper_log) | 操作日志记录 | 
 | 11 | [sys_post](#sys_post) | 岗位信息表 | 
 | 12 | [sys_role](#sys_role) | 角色信息表 | 
 | 13 | [sys_role_dept](#sys_role_dept) | 角色和部门关联表 | 
 | 14 | [sys_role_menu](#sys_role_menu) | 角色和菜单关联表 | 
 | 15 | [sys_user](#sys_user) | 用户信息表 | 
 | 16 | [sys_user_post](#sys_user_post) | 用户与岗位关联表 | 
 | 17 | [sys_user_role](#sys_user_role) | 用户和角色关联表 | 
 | 18 | [wx_back](#wx_back) | 微信返校申请表 | 
 | 19 | [wx_leave](#wx_leave) | 微信请假申请表 | 
 | 20 | [wx_notice](#wx_notice) | 微信的公告通知表 | 
 | 21 | [wx_temperature](#wx_temperature) | 微信体温日报表 | 
 | 22 | [wx_trip](#wx_trip) | 微信行程记录表 | 
 | 23 | [wx_user](#wx_user) | 微信用户表 | 

## <a name="sys_config">sys_config 参数配置表</a>
 | 序号 | 列名 | 数据类型 | 长度 | 小数位数 | 主键 | 自增 | 允许空 | 默认值 | 列说明 | 
 | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | 
 | 1 | config_id | int |  |  | √ | √ |  |  | 参数主键 | 
 | 2 | config_name | varchar | 100 |  |  |  | √ |  | 参数名称 | 
 | 3 | config_key | varchar | 100 |  |  |  | √ |  | 参数键名 | 
 | 4 | config_value | varchar | 500 |  |  |  | √ |  | 参数键值 | 
 | 5 | config_type | char | 1 |  |  |  | √ | N | 系统内置（Y是 N否） | 
 | 6 | create_by | varchar | 64 |  |  |  | √ |  | 创建者 | 
 | 7 | create_time | datetime |  |  |  |  | √ |  | 创建时间 | 
 | 8 | update_by | varchar | 64 |  |  |  | √ |  | 更新者 | 
 | 9 | update_time | datetime |  |  |  |  | √ |  | 更新时间 | 
 | 10 | remark | varchar | 500 |  |  |  | √ |  | 备注 | 

## <a name="sys_dept">sys_dept 部门表</a>
 | 序号 | 列名 | 数据类型 | 长度 | 小数位数 | 主键 | 自增 | 允许空 | 默认值 | 列说明 | 
 | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | 
 | 1 | dept_id | bigint |  |  | √ | √ |  |  | 部门id | 
 | 2 | parent_id | bigint |  |  |  |  | √ | 0 | 父部门id | 
 | 3 | ancestors | varchar | 50 |  |  |  | √ |  | 祖级列表 | 
 | 4 | dept_name | varchar | 30 |  |  |  | √ |  | 部门名称 | 
 | 5 | order_num | int |  |  |  |  | √ | 0 | 显示顺序 | 
 | 6 | leader | varchar | 20 |  |  |  | √ |  | 负责人 | 
 | 7 | phone | varchar | 11 |  |  |  | √ |  | 联系电话 | 
 | 8 | email | varchar | 50 |  |  |  | √ |  | 邮箱 | 
 | 9 | status | char | 1 |  |  |  | √ | 0 | 部门状态（0正常 1停用） | 
 | 10 | del_flag | char | 1 |  |  |  | √ | 0 | 删除标志（0代表存在 2代表删除） | 
 | 11 | create_by | varchar | 64 |  |  |  | √ |  | 创建者 | 
 | 12 | create_time | datetime |  |  |  |  | √ |  | 创建时间 | 
 | 13 | update_by | varchar | 64 |  |  |  | √ |  | 更新者 | 
 | 14 | update_time | datetime |  |  |  |  | √ |  | 更新时间 | 

## <a name="sys_dict_data">sys_dict_data 字典数据表</a>
 | 序号 | 列名 | 数据类型 | 长度 | 小数位数 | 主键 | 自增 | 允许空 | 默认值 | 列说明 | 
 | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | 
 | 1 | dict_code | bigint |  |  | √ | √ |  |  | 字典编码 | 
 | 2 | dict_sort | int |  |  |  |  | √ | 0 | 字典排序 | 
 | 3 | dict_label | varchar | 100 |  |  |  | √ |  | 字典标签 | 
 | 4 | dict_value | varchar | 100 |  |  |  | √ |  | 字典键值 | 
 | 5 | dict_type | varchar | 100 |  |  |  | √ |  | 字典类型 | 
 | 6 | css_class | varchar | 100 |  |  |  | √ |  | 样式属性（其他样式扩展） | 
 | 7 | list_class | varchar | 100 |  |  |  | √ |  | 表格回显样式 | 
 | 8 | is_default | char | 1 |  |  |  | √ | N | 是否默认（Y是 N否） | 
 | 9 | status | char | 1 |  |  |  | √ | 0 | 状态（0正常 1停用） | 
 | 10 | create_by | varchar | 64 |  |  |  | √ |  | 创建者 | 
 | 11 | create_time | datetime |  |  |  |  | √ |  | 创建时间 | 
 | 12 | update_by | varchar | 64 |  |  |  | √ |  | 更新者 | 
 | 13 | update_time | datetime |  |  |  |  | √ |  | 更新时间 | 
 | 14 | remark | varchar | 500 |  |  |  | √ |  | 备注 | 

## <a name="sys_dict_type">sys_dict_type 字典类型表</a>
 | 序号 | 列名 | 数据类型 | 长度 | 小数位数 | 主键 | 自增 | 允许空 | 默认值 | 列说明 | 
 | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | 
 | 1 | dict_id | bigint |  |  | √ | √ |  |  | 字典主键 | 
 | 2 | dict_name | varchar | 100 |  |  |  | √ |  | 字典名称 | 
 | 3 | dict_type | varchar | 100 |  |  |  | √ |  | 字典类型 | 
 | 4 | status | char | 1 |  |  |  | √ | 0 | 状态（0正常 1停用） | 
 | 5 | create_by | varchar | 64 |  |  |  | √ |  | 创建者 | 
 | 6 | create_time | datetime |  |  |  |  | √ |  | 创建时间 | 
 | 7 | update_by | varchar | 64 |  |  |  | √ |  | 更新者 | 
 | 8 | update_time | datetime |  |  |  |  | √ |  | 更新时间 | 
 | 9 | remark | varchar | 500 |  |  |  | √ |  | 备注 | 

## <a name="sys_job">sys_job 定时任务调度表</a>
 | 序号 | 列名 | 数据类型 | 长度 | 小数位数 | 主键 | 自增 | 允许空 | 默认值 | 列说明 | 
 | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | 
 | 1 | job_id | bigint |  |  | √ | √ |  |  | 任务ID | 
 | 2 | job_name | varchar | 64 |  | √ |  |  |  | 任务名称 | 
 | 3 | job_group | varchar | 64 |  | √ |  |  | DEFAULT | 任务组名 | 
 | 4 | invoke_target | varchar | 500 |  |  |  |  |  | 调用目标字符串 | 
 | 5 | cron_expression | varchar | 255 |  |  |  | √ |  | cron执行表达式 | 
 | 6 | misfire_policy | varchar | 20 |  |  |  | √ | 3 | 计划执行错误策略（1立即执行 2执行一次 3放弃执行） | 
 | 7 | concurrent | char | 1 |  |  |  | √ | 1 | 是否并发执行（0允许 1禁止） | 
 | 8 | status | char | 1 |  |  |  | √ | 0 | 状态（0正常 1暂停） | 
 | 9 | create_by | varchar | 64 |  |  |  | √ |  | 创建者 | 
 | 10 | create_time | datetime |  |  |  |  | √ |  | 创建时间 | 
 | 11 | update_by | varchar | 64 |  |  |  | √ |  | 更新者 | 
 | 12 | update_time | datetime |  |  |  |  | √ |  | 更新时间 | 
 | 13 | remark | varchar | 500 |  |  |  | √ |  | 备注信息 | 

## <a name="sys_job_log">sys_job_log 定时任务调度日志表</a>
 | 序号 | 列名 | 数据类型 | 长度 | 小数位数 | 主键 | 自增 | 允许空 | 默认值 | 列说明 | 
 | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | 
 | 1 | job_log_id | bigint |  |  | √ | √ |  |  | 任务日志ID | 
 | 2 | job_name | varchar | 64 |  |  |  |  |  | 任务名称 | 
 | 3 | job_group | varchar | 64 |  |  |  |  |  | 任务组名 | 
 | 4 | invoke_target | varchar | 500 |  |  |  |  |  | 调用目标字符串 | 
 | 5 | job_message | varchar | 500 |  |  |  | √ |  | 日志信息 | 
 | 6 | status | char | 1 |  |  |  | √ | 0 | 执行状态（0正常 1失败） | 
 | 7 | exception_info | varchar | 2000 |  |  |  | √ |  | 异常信息 | 
 | 8 | create_time | datetime |  |  |  |  | √ |  | 创建时间 | 

## <a name="sys_logininfor">sys_logininfor 系统访问记录</a>
 | 序号 | 列名 | 数据类型 | 长度 | 小数位数 | 主键 | 自增 | 允许空 | 默认值 | 列说明 | 
 | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | 
 | 1 | info_id | bigint |  |  | √ | √ |  |  | 访问ID | 
 | 2 | user_name | varchar | 50 |  |  |  | √ |  | 用户账号 | 
 | 3 | ipaddr | varchar | 128 |  |  |  | √ |  | 登录IP地址 | 
 | 4 | login_location | varchar | 255 |  |  |  | √ |  | 登录地点 | 
 | 5 | browser | varchar | 50 |  |  |  | √ |  | 浏览器类型 | 
 | 6 | os | varchar | 50 |  |  |  | √ |  | 操作系统 | 
 | 7 | status | char | 1 |  |  |  | √ | 0 | 登录状态（0成功 1失败） | 
 | 8 | msg | varchar | 255 |  |  |  | √ |  | 提示消息 | 
 | 9 | login_time | datetime |  |  |  |  | √ |  | 访问时间 | 

## <a name="sys_menu">sys_menu 菜单权限表</a>
 | 序号 | 列名 | 数据类型 | 长度 | 小数位数 | 主键 | 自增 | 允许空 | 默认值 | 列说明 | 
 | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | 
 | 1 | menu_id | bigint |  |  | √ | √ |  |  | 菜单ID | 
 | 2 | menu_name | varchar | 50 |  |  |  |  |  | 菜单名称 | 
 | 3 | parent_id | bigint |  |  |  |  | √ | 0 | 父菜单ID | 
 | 4 | order_num | int |  |  |  |  | √ | 0 | 显示顺序 | 
 | 5 | path | varchar | 200 |  |  |  | √ |  | 路由地址 | 
 | 6 | component | varchar | 255 |  |  |  | √ |  | 组件路径 | 
 | 7 | is_frame | int |  |  |  |  | √ | 1 | 是否为外链（0是 1否） | 
 | 8 | is_cache | int |  |  |  |  | √ | 0 | 是否缓存（0缓存 1不缓存） | 
 | 9 | menu_type | char | 1 |  |  |  | √ |  | 菜单类型（M目录 C菜单 F按钮） | 
 | 10 | visible | char | 1 |  |  |  | √ | 0 | 菜单状态（0显示 1隐藏） | 
 | 11 | status | char | 1 |  |  |  | √ | 0 | 菜单状态（0正常 1停用） | 
 | 12 | perms | varchar | 100 |  |  |  | √ |  | 权限标识 | 
 | 13 | icon | varchar | 100 |  |  |  | √ | # | 菜单图标 | 
 | 14 | create_by | varchar | 64 |  |  |  | √ |  | 创建者 | 
 | 15 | create_time | datetime |  |  |  |  | √ |  | 创建时间 | 
 | 16 | update_by | varchar | 64 |  |  |  | √ |  | 更新者 | 
 | 17 | update_time | datetime |  |  |  |  | √ |  | 更新时间 | 
 | 18 | remark | varchar | 500 |  |  |  | √ |  | 备注 | 

## <a name="sys_notice">sys_notice 通知公告表</a>
 | 序号 | 列名 | 数据类型 | 长度 | 小数位数 | 主键 | 自增 | 允许空 | 默认值 | 列说明 | 
 | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | 
 | 1 | notice_id | int |  |  | √ | √ |  |  | 公告ID | 
 | 2 | notice_title | varchar | 50 |  |  |  |  |  | 公告标题 | 
 | 3 | notice_type | char | 1 |  |  |  |  |  | 公告类型（1通知 2公告） | 
 | 4 | notice_content | longblob | 4294967295 |  |  |  | √ |  | 公告内容 | 
 | 5 | status | char | 1 |  |  |  | √ | 0 | 公告状态（0正常 1关闭） | 
 | 6 | create_by | varchar | 64 |  |  |  | √ |  | 创建者 | 
 | 7 | create_time | datetime |  |  |  |  | √ |  | 创建时间 | 
 | 8 | update_by | varchar | 64 |  |  |  | √ |  | 更新者 | 
 | 9 | update_time | datetime |  |  |  |  | √ |  | 更新时间 | 
 | 10 | remark | varchar | 255 |  |  |  | √ |  | 备注 | 

## <a name="sys_oper_log">sys_oper_log 操作日志记录</a>
 | 序号 | 列名 | 数据类型 | 长度 | 小数位数 | 主键 | 自增 | 允许空 | 默认值 | 列说明 | 
 | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | 
 | 1 | oper_id | bigint |  |  | √ | √ |  |  | 日志主键 | 
 | 2 | title | varchar | 50 |  |  |  | √ |  | 模块标题 | 
 | 3 | business_type | int |  |  |  |  | √ | 0 | 业务类型（0其它 1新增 2修改 3删除） | 
 | 4 | method | varchar | 100 |  |  |  | √ |  | 方法名称 | 
 | 5 | request_method | varchar | 10 |  |  |  | √ |  | 请求方式 | 
 | 6 | operator_type | int |  |  |  |  | √ | 0 | 操作类别（0其它 1后台用户 2手机端用户） | 
 | 7 | oper_name | varchar | 50 |  |  |  | √ |  | 操作人员 | 
 | 8 | dept_name | varchar | 50 |  |  |  | √ |  | 部门名称 | 
 | 9 | oper_url | varchar | 255 |  |  |  | √ |  | 请求URL | 
 | 10 | oper_ip | varchar | 128 |  |  |  | √ |  | 主机地址 | 
 | 11 | oper_location | varchar | 255 |  |  |  | √ |  | 操作地点 | 
 | 12 | oper_param | varchar | 2000 |  |  |  | √ |  | 请求参数 | 
 | 13 | json_result | varchar | 2000 |  |  |  | √ |  | 返回参数 | 
 | 14 | status | int |  |  |  |  | √ | 0 | 操作状态（0正常 1异常） | 
 | 15 | error_msg | varchar | 2000 |  |  |  | √ |  | 错误消息 | 
 | 16 | oper_time | datetime |  |  |  |  | √ |  | 操作时间 | 

## <a name="sys_post">sys_post 岗位信息表</a>
 | 序号 | 列名 | 数据类型 | 长度 | 小数位数 | 主键 | 自增 | 允许空 | 默认值 | 列说明 | 
 | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | 
 | 1 | post_id | bigint |  |  | √ | √ |  |  | 岗位ID | 
 | 2 | post_code | varchar | 64 |  |  |  |  |  | 岗位编码 | 
 | 3 | post_name | varchar | 50 |  |  |  |  |  | 岗位名称 | 
 | 4 | post_sort | int |  |  |  |  |  |  | 显示顺序 | 
 | 5 | status | char | 1 |  |  |  |  |  | 状态（0正常 1停用） | 
 | 6 | create_by | varchar | 64 |  |  |  | √ |  | 创建者 | 
 | 7 | create_time | datetime |  |  |  |  | √ |  | 创建时间 | 
 | 8 | update_by | varchar | 64 |  |  |  | √ |  | 更新者 | 
 | 9 | update_time | datetime |  |  |  |  | √ |  | 更新时间 | 
 | 10 | remark | varchar | 500 |  |  |  | √ |  | 备注 | 

## <a name="sys_role">sys_role 角色信息表</a>
 | 序号 | 列名 | 数据类型 | 长度 | 小数位数 | 主键 | 自增 | 允许空 | 默认值 | 列说明 | 
 | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | 
 | 1 | role_id | bigint |  |  | √ | √ |  |  | 角色ID | 
 | 2 | role_name | varchar | 30 |  |  |  |  |  | 角色名称 | 
 | 3 | role_key | varchar | 100 |  |  |  |  |  | 角色权限字符串 | 
 | 4 | role_sort | int |  |  |  |  |  |  | 显示顺序 | 
 | 5 | data_scope | char | 1 |  |  |  | √ | 1 | 数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限） | 
 | 6 | menu_check_strictly | tinyint |  | 0 |  |  | √ | 1 | 菜单树选择项是否关联显示 | 
 | 7 | dept_check_strictly | tinyint |  | 0 |  |  | √ | 1 | 部门树选择项是否关联显示 | 
 | 8 | status | char | 1 |  |  |  |  |  | 角色状态（0正常 1停用） | 
 | 9 | del_flag | char | 1 |  |  |  | √ | 0 | 删除标志（0代表存在 2代表删除） | 
 | 10 | create_by | varchar | 64 |  |  |  | √ |  | 创建者 | 
 | 11 | create_time | datetime |  |  |  |  | √ |  | 创建时间 | 
 | 12 | update_by | varchar | 64 |  |  |  | √ |  | 更新者 | 
 | 13 | update_time | datetime |  |  |  |  | √ |  | 更新时间 | 
 | 14 | remark | varchar | 500 |  |  |  | √ |  | 备注 | 

## <a name="sys_role_dept">sys_role_dept 角色和部门关联表</a>
 | 序号 | 列名 | 数据类型 | 长度 | 小数位数 | 主键 | 自增 | 允许空 | 默认值 | 列说明 | 
 | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | 
 | 1 | role_id | bigint |  |  | √ |  |  |  | 角色ID | 
 | 2 | dept_id | bigint |  |  | √ |  |  |  | 部门ID | 

## <a name="sys_role_menu">sys_role_menu 角色和菜单关联表</a>
 | 序号 | 列名 | 数据类型 | 长度 | 小数位数 | 主键 | 自增 | 允许空 | 默认值 | 列说明 | 
 | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | 
 | 1 | role_id | bigint |  |  | √ |  |  |  | 角色ID | 
 | 2 | menu_id | bigint |  |  | √ |  |  |  | 菜单ID | 

## <a name="sys_user">sys_user 用户信息表</a>
 | 序号 | 列名 | 数据类型 | 长度 | 小数位数 | 主键 | 自增 | 允许空 | 默认值 | 列说明 | 
 | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | 
 | 1 | user_id | bigint |  |  | √ | √ |  |  | 用户ID | 
 | 2 | dept_id | bigint |  |  |  |  | √ |  | 部门ID | 
 | 3 | user_name | varchar | 30 |  |  |  |  |  | 用户账号 | 
 | 4 | nick_name | varchar | 30 |  |  |  |  |  | 用户昵称 | 
 | 5 | user_type | varchar | 2 |  |  |  | √ | 00 | 用户类型（00系统用户） | 
 | 6 | email | varchar | 50 |  |  |  | √ |  | 用户邮箱 | 
 | 7 | phonenumber | varchar | 11 |  |  |  | √ |  | 手机号码 | 
 | 8 | sex | char | 1 |  |  |  | √ | 0 | 用户性别（0男 1女 2未知） | 
 | 9 | avatar | varchar | 100 |  |  |  | √ |  | 头像地址 | 
 | 10 | password | varchar | 100 |  |  |  | √ |  | 密码 | 
 | 11 | status | char | 1 |  |  |  | √ | 0 | 帐号状态（0正常 1停用） | 
 | 12 | del_flag | char | 1 |  |  |  | √ | 0 | 删除标志（0代表存在 2代表删除） | 
 | 13 | login_ip | varchar | 128 |  |  |  | √ |  | 最后登录IP | 
 | 14 | login_date | datetime |  |  |  |  | √ |  | 最后登录时间 | 
 | 15 | create_by | varchar | 64 |  |  |  | √ |  | 创建者 | 
 | 16 | create_time | datetime |  |  |  |  | √ |  | 创建时间 | 
 | 17 | update_by | varchar | 64 |  |  |  | √ |  | 更新者 | 
 | 18 | update_time | datetime |  |  |  |  | √ |  | 更新时间 | 
 | 19 | remark | varchar | 500 |  |  |  | √ |  | 备注 | 

## <a name="sys_user_post">sys_user_post 用户与岗位关联表</a>
 | 序号 | 列名 | 数据类型 | 长度 | 小数位数 | 主键 | 自增 | 允许空 | 默认值 | 列说明 | 
 | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | 
 | 1 | user_id | bigint |  |  | √ |  |  |  | 用户ID | 
 | 2 | post_id | bigint |  |  | √ |  |  |  | 岗位ID | 

## <a name="sys_user_role">sys_user_role 用户和角色关联表</a>
 | 序号 | 列名 | 数据类型 | 长度 | 小数位数 | 主键 | 自增 | 允许空 | 默认值 | 列说明 | 
 | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | 
 | 1 | user_id | bigint |  |  | √ |  |  |  | 用户ID | 
 | 2 | role_id | bigint |  |  | √ |  |  |  | 角色ID | 

## <a name="wx_back">wx_back 微信返校申请表</a>
 | 序号 | 列名 | 数据类型 | 长度 | 小数位数 | 主键 | 自增 | 允许空 | 默认值 | 列说明 | 
 | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | 
 | 1 | id | bigint |  |  | √ | √ |  |  | 编号 | 
 | 2 | open_id | varchar | 28 |  |  |  |  |  | 微信用户编号 | 
 | 3 | from_place | varchar | 100 |  |  |  |  |  | 起始地 | 
 | 4 | from_time | datetime |  |  |  |  |  |  | 出发时间 | 
 | 5 | status | int |  |  |  |  | √ |  | 状态 | 
 | 6 | check_status | int |  |  |  |  | √ |  | 审核状态 | 
 | 7 | check_by | varchar | 50 |  |  |  | √ |  | 审核人 | 
 | 8 | check_time | datetime |  |  |  |  | √ |  | 审核时间 | 
 | 9 | create_by | varchar | 50 |  |  |  | √ |  | 创建人 | 
 | 10 | create_time | datetime |  |  |  |  | √ |  | 创建时间 | 
 | 11 | update_by | varchar | 50 |  |  |  | √ |  | 更新人 | 
 | 12 | update_time | datetime |  |  |  |  | √ |  | 更新时间 | 
 | 13 | remark | varchar | 255 |  |  |  | √ |  | 备注 | 

## <a name="wx_leave">wx_leave 微信请假申请表</a>
 | 序号 | 列名 | 数据类型 | 长度 | 小数位数 | 主键 | 自增 | 允许空 | 默认值 | 列说明 | 
 | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | 
 | 1 | id | bigint |  |  | √ | √ |  |  | 编号 | 
 | 2 | open_id | varchar | 28 |  |  |  |  |  | 微信用户编号 | 
 | 3 | from_time | datetime |  |  |  |  |  |  | 出发时间 | 
 | 4 | to_time | datetime |  |  |  |  |  |  | 到达时间 | 
 | 5 | status | int |  |  |  |  | √ |  | 状态 | 
 | 6 | check_status | int |  |  |  |  | √ |  | 审核状态 | 
 | 7 | check_by | varchar | 50 |  |  |  | √ |  | 审核人 | 
 | 8 | check_time | datetime |  |  |  |  | √ |  | 审核时间 | 
 | 9 | create_by | varchar | 50 |  |  |  | √ |  | 创建人 | 
 | 10 | create_time | datetime |  |  |  |  | √ |  | 创建时间 | 
 | 11 | update_by | varchar | 50 |  |  |  | √ |  | 更新人 | 
 | 12 | update_time | datetime |  |  |  |  | √ |  | 更新时间 | 
 | 13 | remark | varchar | 255 |  |  |  | √ |  | 备注 | 

## <a name="wx_notice">wx_notice 微信的公告通知表</a>
 | 序号 | 列名 | 数据类型 | 长度 | 小数位数 | 主键 | 自增 | 允许空 | 默认值 | 列说明 | 
 | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | 
 | 1 | id | bigint |  |  | √ | √ |  |  | 编号 | 
 | 2 | status | int |  |  |  |  | √ |  | 状态 | 
 | 3 | title | varchar | 255 |  |  |  |  |  | 标题 | 
 | 4 | content | varchar | 255 |  |  |  |  |  | 内容 | 
 | 5 | create_by | varchar | 50 |  |  |  | √ |  | 创建人 | 
 | 6 | create_time | datetime |  |  |  |  | √ |  | 创建时间 | 
 | 7 | update_by | varchar | 50 |  |  |  | √ |  | 更新人 | 
 | 8 | update_time | datetime |  |  |  |  | √ |  | 更新时间 | 
 | 9 | remark | varchar | 255 |  |  |  | √ |  | 备注 | 

## <a name="wx_temperature">wx_temperature 微信体温日报表</a>
 | 序号 | 列名 | 数据类型 | 长度 | 小数位数 | 主键 | 自增 | 允许空 | 默认值 | 列说明 | 
 | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | 
 | 1 | id | bigint |  |  | √ | √ |  |  | 编号 | 
 | 2 | open_id | varchar | 28 |  |  |  |  |  | 微信用户编号 | 
 | 3 | temperature | float | 10 | 1 |  |  |  |  | 体温 | 
 | 4 | location | varchar | 255 |  |  |  |  |  | 地点 | 
 | 5 | status | int |  |  |  |  | √ |  | 状态 | 
 | 6 | create_by | varchar | 50 |  |  |  | √ |  | 创建人 | 
 | 7 | create_time | datetime |  |  |  |  | √ |  | 创建时间 | 
 | 8 | update_by | varchar | 50 |  |  |  | √ |  | 更新人 | 
 | 9 | update_time | datetime |  |  |  |  | √ |  | 更新时间 | 
 | 10 | remark | varchar | 255 |  |  |  | √ |  | 备注 | 

## <a name="wx_trip">wx_trip 微信行程记录表</a>
 | 序号 | 列名 | 数据类型 | 长度 | 小数位数 | 主键 | 自增 | 允许空 | 默认值 | 列说明 | 
 | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | 
 | 1 | id | bigint |  |  | √ | √ |  |  | 编号 | 
 | 2 | open_id | varchar | 28 |  |  |  |  |  | 微信用户编号 | 
 | 3 | from_place | varchar | 100 |  |  |  |  |  | 起始地 | 
 | 4 | to_place | varchar | 100 |  |  |  |  |  | 目的地 | 
 | 5 | from_time | datetime |  |  |  |  |  |  | 出发时间 | 
 | 6 | to_time | datetime |  |  |  |  |  |  | 到达时间 | 
 | 7 | status | int |  |  |  |  | √ |  | 状态 | 
 | 8 | create_by | varchar | 50 |  |  |  | √ |  | 创建人 | 
 | 9 | create_time | datetime |  |  |  |  | √ |  | 创建时间 | 
 | 10 | update_by | varchar | 50 |  |  |  | √ |  | 更新人 | 
 | 11 | update_time | datetime |  |  |  |  | √ |  | 更新时间 | 
 | 12 | remark | varchar | 255 |  |  |  | √ |  | 备注 | 

## <a name="wx_user">wx_user 微信用户表</a>
 | 序号 | 列名 | 数据类型 | 长度 | 小数位数 | 主键 | 自增 | 允许空 | 默认值 | 列说明 | 
 | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | 
 | 1 | open_id | varchar | 28 |  | √ |  |  |  | 小程序用户openid | 
 | 2 | dept_id | bigint |  |  |  |  | √ |  | 部门ID | 
 | 3 | union_id | varchar | 255 |  |  |  | √ |  |  | 
 | 4 | avatar_url | varchar | 255 |  |  |  | √ |  | 用户头像 | 
 | 5 | nick_name | varchar | 100 |  |  |  |  |  | 用户昵称 | 
 | 6 | gender | int |  |  |  |  | √ | 0 | 性别   0 男  1  女  2 人妖 | 
 | 7 | country | varchar | 100 |  |  |  | √ |  | 所在国家 | 
 | 8 | province | varchar | 100 |  |  |  | √ |  | 省份 | 
 | 9 | city | varchar | 100 |  |  |  | √ |  | 城市 | 
 | 10 | name | varchar | 100 |  |  |  | √ |  | 姓名 | 
 | 11 | number | int |  |  |  |  | √ |  | 学号 | 
 | 12 | mobile | varchar | 11 |  |  |  | √ |  | 手机号码 | 
 | 13 | emergency_contact | varchar | 50 |  |  |  | √ |  | 紧急联系人 | 
 | 14 | emergency_contact_mobile | varchar | 11 |  |  |  | √ |  | 紧急联系人手机号码 | 
 | 15 | location | varchar | 255 |  |  |  | √ |  | 现居住地 | 
 | 16 | status | int |  |  |  |  | √ |  | 状态  0：禁用   1：正常 | 
 | 17 | create_by | varchar | 50 |  |  |  | √ |  | 创建人 | 
 | 18 | create_time | datetime |  |  |  |  | √ |  | 创建时间 | 
 | 19 | update_by | varchar | 50 |  |  |  | √ |  | 更新人 | 
 | 20 | update_time | datetime |  |  |  |  | √ |  | 更新时间 | 
