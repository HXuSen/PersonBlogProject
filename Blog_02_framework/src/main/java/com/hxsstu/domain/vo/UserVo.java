package com.hxsstu.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * ClassName: UserVo
 * Package: com.hxsstu.domain.vo
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/22-19:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {
    private String avatar;
    private Date createTime;
    private String email;
    private Long id;
    private String nickName;
    private String phonenumber;
    private String sex;
    private String status;
    private Long updateBy;
    private Date updateTime;
    private String userName;
}
