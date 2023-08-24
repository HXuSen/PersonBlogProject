package com.hxsstu.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: MenuSelectDto
 * Package: com.hxsstu.domain.dto
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/22-15:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuSelectDto {
    private String status;
    private String menuName;
}
