package com.hxsstu.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * ClassName: RoleVo
 * Package: com.hxsstu.domain.vo
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/22-18:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleVo {
    private Long id;
    private String roleKey;
    private String roleName;
    private Integer roleSort;
    private String status;
    private Date createTime;
}
