package com.hxsstu.domain.dto;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ClassName: RoleAddDto
 * Package: com.hxsstu.domain.dto
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/22-18:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleAddDto {
    private String roleName;
    private String roleKey;
    private Integer roleSort;
    private String status;
    private List<Long> menuIds;
    private String remark;
}
