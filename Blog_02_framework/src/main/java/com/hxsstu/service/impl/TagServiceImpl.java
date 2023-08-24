package com.hxsstu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hxsstu.domain.ResponseResult;
import com.hxsstu.domain.dto.TagListDto;
import com.hxsstu.domain.dto.UpdateTagDto;
import com.hxsstu.domain.entity.Tag;
import com.hxsstu.domain.vo.PageVo;
import com.hxsstu.domain.vo.TagVo;
import com.hxsstu.enums.AppHttpCodeEnum;
import com.hxsstu.exception.SystemException;
import com.hxsstu.mapper.TagMapper;
import com.hxsstu.service.TagService;
import com.hxsstu.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 标签(Tag)表服务实现类
 *
 * @author makejava
 * @since 2023-08-21 15:54:30
 */
@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Override
    public ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(StringUtils.hasText(tagListDto.getName()),Tag::getName,tagListDto.getName());
        queryWrapper.eq(StringUtils.hasText(tagListDto.getRemark()),Tag::getRemark,tagListDto.getRemark());

        Page<Tag> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page,queryWrapper);
        List<TagVo> tagVos = BeanCopyUtils.copyBeanList(page.getRecords(), TagVo.class);
        PageVo pageVo = new PageVo(tagVos,page.getTotal());

        return ResponseResult.okResult(pageVo);
    }

    @Override
    public Object addTag(Tag tag) {
        if (!StringUtils.hasText(tag.getName())) {
            throw new SystemException(AppHttpCodeEnum.TAG_NAME_NOT_NULL);
        }
        save(tag);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getTagById(Long id) {
        Tag tag = getById(id);
        TagVo tagVo = BeanCopyUtils.copyBean(tag, TagVo.class);
        return ResponseResult.okResult(tagVo);
    }

    @Override
    public ResponseResult updateTagById(UpdateTagDto tagDto) {
        Tag tag = BeanCopyUtils.copyBean(tagDto, Tag.class);
        updateById(tag);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult listAllTag() {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.select(Tag::getId,Tag::getName);
        List<TagVo> tagVos = BeanCopyUtils.copyBeanList(list(queryWrapper), TagVo.class);
        return ResponseResult.okResult(tagVos);
    }
}

