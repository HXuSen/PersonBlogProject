package com.hxsstu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hxsstu.domain.ResponseResult;
import com.hxsstu.domain.dto.TagListDto;
import com.hxsstu.domain.dto.UpdateTagDto;
import com.hxsstu.domain.entity.Tag;
import com.hxsstu.domain.vo.PageVo;


/**
 * 标签(Tag)表服务接口
 *
 * @author makejava
 * @since 2023-08-21 15:54:29
 */
public interface TagService extends IService<Tag> {

    ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);

    Object addTag(Tag tag);

    ResponseResult getTagById(Long id);

    ResponseResult updateTagById(UpdateTagDto tagDto);

    ResponseResult listAllTag();
}

