package com.hxsstu.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ClassName: RoleUpadteDto
 * Package: com.hxsstu.domain.dto
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/22-19:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleUpdateDto {
    private Long id;
    private String remark;
    private String roleKey;
    private String roleName;
    private Integer roleSort;
    private String status;
    private List<String> menuIds;

}
