package com.youo.bookmanage.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.youo.bookmanage.handler.BusinessException;
import com.youo.bookmanage.response.Result;
import com.youo.bookmanage.response.ResultCode;
import com.youo.bookmanage.security.Role;
import com.youo.bookmanage.system.entity.Partmenu;
import com.youo.bookmanage.system.service.PartmenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author pt
 * @since 2020-11-13
 */

@CrossOrigin
@RestController
@RequestMapping("/system/partmenu")
@Api(value="角色菜单模块",tags="角色菜单接口")
public class PartmenuController {

    @Autowired
    private PartmenuService partmenuService;

    @Role(roles="1")
    @ApiOperation(value="查询所有角色菜单映射",notes="查询所有角色菜单映射")
    @GetMapping("")
    public Result findAll(){
        List<Partmenu> all = partmenuService.findAll();
        return Result.ok().data("all",all);
    }

    @Role(roles="1")
    @ApiOperation(value="添加菜单",notes="给角色添加菜单")
    @PostMapping("")
    public Result addMenu(@Validated @RequestBody Partmenu partmenu){
        QueryWrapper<Partmenu> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id",partmenu.getRoleId()).eq("menu_id",partmenu.getMenuId());
        Integer count = partmenuService.count(wrapper);
        if(count==0){
            partmenuService.save(partmenu);
            return Result.ok();
        }
        throw new BusinessException(ResultCode.RECORD_ALREADY_EXISTS.getCode(),
                ResultCode.RECORD_ALREADY_EXISTS.getMessage());
    }


    @Role(roles="1")
    @ApiOperation(value="删除菜单",notes="给角色删除菜单，传入角色号和菜单号")
    @DeleteMapping("/{roleid}/{menuid}")
    public Result delelteMenu(@PathVariable  int roleid,@PathVariable int menuid){
        Map map = new HashMap();
        map.put("role_id",roleid);
        map.put("menu_id",menuid);
        partmenuService.removeByMap(map);
        return Result.ok();
    }

}

