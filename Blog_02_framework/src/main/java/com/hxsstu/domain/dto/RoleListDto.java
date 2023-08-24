package com.hxsstu.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: RoleListDto
 * Package: com.hxsstu.domain.dto
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/22-18:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleListDto {
    private String roleName;
    private String status;
}
