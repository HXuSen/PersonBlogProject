package com.hxsstu.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: AddTagDto
 * Package: com.hxsstu.domain.dto
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/22-10:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "添加标签dto")
public class AddTagDto {
    private String name;
    private String remark;
}
