package com.hxsstu.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * ClassName: RoleUpdateVo1
 * Package: com.hxsstu.domain.vo
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/22-18:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleUpdateVo1 {
    private Long id;
    private String remark;
    private String roleKey;
    private String roleName;
    private Integer roleSort;
    private String status;
}
