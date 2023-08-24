package com.hxsstu.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: UpdateTagDto
 * Package: com.hxsstu.domain.dto
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/22-11:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "更新标签dto")
public class UpdateTagDto {
    private Long id;
    private String name;
    private String remark;
}
