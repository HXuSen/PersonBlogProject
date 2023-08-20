package com.hxsstu.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * ClassName: ArticleDetailVo
 * Package: com.hxsstu.domain.vo
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/20-15:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDetailVo {
    private Long id;
    private String title;
    private String content;
    private String summary;
    private Long categoryId;
    private String categoryName;
    private String thumbnail;
    private Long viewCount;
    private Date createTime;
}
