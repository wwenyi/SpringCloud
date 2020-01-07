package com.wwy.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 登录用户信息实体类
 * @author wwy
 * @date 2019年12月6日
 * @version v0.0.1
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
private Long userId;
private String nickName;
private String tel;
private Integer role;
}
