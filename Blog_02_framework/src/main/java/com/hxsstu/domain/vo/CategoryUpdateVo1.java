package com.hxsstu.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: CategoryUpdateVo1
 * Package: com.hxsstu.domain.vo
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/24-11:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryUpdateVo1 {
    private String name;
    private String status;
    private String description;
    private Long id;
}
