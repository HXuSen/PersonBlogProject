package com.hxsstu.controller;

import com.hxsstu.domain.ResponseResult;
import com.hxsstu.domain.dto.MenuSelectDto;
import com.hxsstu.domain.entity.Menu;
import com.hxsstu.domain.vo.TreeSelectVo;
import com.hxsstu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: MenuController
 * Package: com.hxsstu.controller
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/22-15:45
 */
@RestController
@RequestMapping("/system/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    public ResponseResult menuList(MenuSelectDto menuSelectDto){
        return menuService.menuList(menuSelectDto);
    }

    @PostMapping
    public ResponseResult addMenu(@RequestBody Menu menu){
        menuService.save(menu);
        return ResponseResult.okResult();
    }

    @GetMapping("/{id}")
    public ResponseResult getMenu(@PathVariable("id")Long id){
        return menuService.getMenuById(id);
    }

    @PutMapping
    public ResponseResult updateMenu(@RequestBody Menu menu){
        return menuService.updateMenu(menu);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteMenu(@PathVariable("id")Long id){
        return menuService.deleteMenuById(id);
    }

    @GetMapping("/treeselect")
    public ResponseResult treeSelect(){
        return menuService.treeSelect();
    }

    @GetMapping("/roleMenuTreeselect/{id}")
    public ResponseResult roleMenuTreeselect(@PathVariable("id")Long id){
        return menuService.roleMenuTreeselect(id);
    }
}
