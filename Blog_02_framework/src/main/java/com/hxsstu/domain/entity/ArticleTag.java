package com.hxsstu.domain.entity;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 文章标签关联表(ArticleTag)表实体类
 *
 * @author makejava
 * @since 2023-08-22 11:39:48
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("blog_article_tag")
public class ArticleTag  implements Serializable{
    public static final long serialVersionUID = 625337492348897098L;
    //文章id@TableId
    private Long articleId;
    //标签id@TableId
    private Long tagId;


}
