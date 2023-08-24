package com.hxsstu.controller;

import com.hxsstu.domain.ResponseResult;
import com.hxsstu.domain.dto.AddTagDto;
import com.hxsstu.domain.dto.TagListDto;
import com.hxsstu.domain.dto.UpdateTagDto;
import com.hxsstu.domain.entity.Tag;
import com.hxsstu.domain.vo.PageVo;
import com.hxsstu.service.TagService;
import com.hxsstu.utils.BeanCopyUtils;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: TagController
 * Package: com.hxsstu.controller
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/21-15:57
 */
@RestController
@RequestMapping("/content/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, TagListDto tagListDto){

       return tagService.pageTagList(pageNum,pageSize,tagListDto);
    }

    @PostMapping
    public ResponseResult addTag(@RequestBody AddTagDto tagDto){
        Tag tag = BeanCopyUtils.copyBean(tagDto, Tag.class);
        return ResponseResult.okResult(tagService.addTag(tag));
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteTag(@PathVariable("id") Long id){
        return ResponseResult.okResult(tagService.removeById(id));
    }

    @GetMapping("/{id}")
    public ResponseResult getTag(@PathVariable("id")Long id){
        return tagService.getTagById(id);
    }

    @PutMapping
    public ResponseResult updateTag(@RequestBody UpdateTagDto tagDto){
        return tagService.updateTagById(tagDto);
    }

    @GetMapping("/listAllTag")
    public ResponseResult listAllTag(){
        return tagService.listAllTag();
    }
}
