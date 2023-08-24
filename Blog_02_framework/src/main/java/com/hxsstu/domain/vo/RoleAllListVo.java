package com.hxsstu.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * ClassName: RoleAllListVo
 * Package: com.hxsstu.domain.vo
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/22-19:36
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleAllListVo {
    private Long createBy;
    private Date createTime;
    private String delFlag;
    private Long id;
    private String remark;
    private String roleKey;
    private String roleName;
    private Integer roleSort;
    private String status;
    private Long updateBy;
    private Date updateTime;
}
