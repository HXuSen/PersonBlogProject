package com.hxsstu.controller;

import com.hxsstu.domain.ResponseResult;
import com.hxsstu.domain.dto.CategoryDto;
import com.hxsstu.domain.dto.LinkDto;
import com.hxsstu.domain.entity.Category;
import com.hxsstu.domain.entity.Link;
import com.hxsstu.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: LinkController
 * Package: com.hxsstu.controller
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/24-11:43
 */
@RestController
@RequestMapping("/content/link")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @GetMapping("/list")
    public ResponseResult linkList(Integer pageNum, Integer pageSize, LinkDto linkDto){
        return linkService.linkList(pageNum,pageSize,linkDto);
    }

    @PostMapping
    public ResponseResult addLink(@RequestBody LinkDto linkDto){
        return linkService.addLink(linkDto);
    }

    @GetMapping("/{id}")
    public ResponseResult getLink(@PathVariable("id")Long id){
        return linkService.getLinkById(id);
    }

    @PutMapping
    public ResponseResult updateLink(@RequestBody LinkDto linkDto){
        return linkService.updateLinkById(linkDto);
    }

    @DeleteMapping("{id}")
    public ResponseResult deleteLink(@PathVariable("id") Long id){
        linkService.removeById(id);
        return ResponseResult.okResult();
    }
}
