package com.hxsstu.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: RoleStatusDto
 * Package: com.hxsstu.domain.dto
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/22-18:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleStatusDto {
    private Long roleId;
    private String status;
}
