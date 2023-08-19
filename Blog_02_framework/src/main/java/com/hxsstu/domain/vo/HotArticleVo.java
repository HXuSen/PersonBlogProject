package com.hxsstu.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: hotArticleVo
 * Package: com.hxsstu.domain.vo
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/19-19:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotArticleVo {
    private Long id;
    private String title;
    private Long viewCount;
}
