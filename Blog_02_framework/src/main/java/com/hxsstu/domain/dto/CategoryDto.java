package com.hxsstu.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: CategoryDto
 * Package: com.hxsstu.domain.dto
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/24-11:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private String name;
    private String status;
    private String description;

}
