package com.hxsstu.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: LinkDto
 * Package: com.hxsstu.domain.dto
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/24-11:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkDto {
    private Long id;
    private String name;
    private String status;
    private String description;
    private String address;
    private String logo;
}
