package com.youo.bookmanage.system.controller;


import com.youo.bookmanage.response.Result;
import com.youo.bookmanage.security.Role;
import com.youo.bookmanage.system.entity.Part;
import com.youo.bookmanage.system.service.PartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
@RequestMapping("/system/part")
@Api(value = "角色模块",tags = "角色接口")
public class PartController {

    @Autowired
    private PartService partService;

    @Role(roles="1")
    @GetMapping("/user")
    @ApiOperation(value="查询角色的用户数量",notes="查询所有角色的用户数量")
    public Result findRoleAndUser(){
        List<Part> roleAndCount = partService.findRoleAndCount();
        return Result.ok().data("roleUser",roleAndCount);

    }

    @Role(roles="1")
    @PostMapping("")
    @ApiOperation(value = "增加角色")
    public Result addPart(@Validated @RequestBody Part part){
       Boolean save = partService.save(part);
       if(save){
           return Result.ok();
       }else{
           return Result.error();
       }

    }

    @Role(roles="1")
    @GetMapping("/{id}")
    @ApiOperation(value = "查询角色",notes="根据id查询角色")
    public Result findPartBYid(@PathVariable int id){
        Part part = partService.getById(id);
        return Result.ok().data("part",part);
    }


    @Role(roles="1")
    @GetMapping("")
    @ApiOperation(value = "查询全部角色")
    public Result findAllPart(){
        List<Part> plist = partService.list();
        return Result.ok().data("plist",plist);
    }

    @Role(roles="1")
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除角色")
    public Result deleteBYid(@PathVariable Integer id){
        Boolean remove = partService.removeById(id);
        if(remove){
            return Result.ok();
        }else{
            return Result.error();
        }
    }

}

