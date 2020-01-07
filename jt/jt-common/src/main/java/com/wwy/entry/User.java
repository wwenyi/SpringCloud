package com.wwy.entry;

import javax.validation.constraints.NotNull;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 
 * @author wwy
 * @date 2019年11月29日
 * @version v0.0.1
 *分销商信息实体类
 */
//使用mybatisplus指定表明
//@TableName("user")
@Data
@ToString
@ApiModel("用户信息")
public class User {
	/**
	 * 分销商id
	 */
//使用mybatisplus指定主键
//@TableId(value="user_id",type = IdType.AUTO)
@ApiModelProperty("主键")
private Long userId;
/**
 * 分销商姓名
 */
@ApiModelProperty("用户真实姓名")
private String userName;
/**
 * 用户昵称
 */
@ApiModelProperty("用户昵称")
private String nickName;
/**
 * 密码
 */
@ApiModelProperty("密码")
private String passWord;
/**
 * 头像
 */
@ApiModelProperty("头像")
private String portrait;
/**
 * 电话1
 */
@ApiModelProperty("电话")
private String tel;
/**
 * 电话2
 */
@ApiModelProperty("电话2")
private String tel2;
/**
 * 分销商地址
 */
@ApiModelProperty("地址")
private String addr;
/**
 * 分销商微信号
 */
@ApiModelProperty("微信")
private String weixin;
/**
 * 分销商邮箱
 */
@ApiModelProperty("邮箱")
private String emil;
/**
 * 创建时间
 */
@ApiModelProperty("创建时间")
private String createTime;
/**
 * 分销商是否通过审核
 * 审核1.未审核0
 */
@ApiModelProperty("是否审核")
private Integer examine=0;
/**
 * 分销商是否删除
 * 删除1.未删除0
 */
@ApiModelProperty("是否删除")
private Integer delete=0;
/**
 * 分销商是否被禁用
 * 禁用1，未禁用0
 */
@ApiModelProperty("是否禁用")
private Integer prohibit=0;
/**
 * 角色标识
 * 1商家2分销商
 */
@ApiModelProperty("角色标识")
@NotNull
private Integer role;

}
