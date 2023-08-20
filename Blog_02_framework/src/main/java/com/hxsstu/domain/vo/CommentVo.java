package com.hxsstu.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * ClassName: CommentVo
 * Package: com.hxsstu.domain.vo
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/20-18:08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentVo {
    private Long id;
    private Long articleId;
    private Long rootId;
    private String content;
    private Long toCommentUserId;
    private String toCommentUserName;
    private Long toCommentId;
    private Long createBy;
    private Date createTime;
    private String username;
    private List<CommentVo> children;

}
