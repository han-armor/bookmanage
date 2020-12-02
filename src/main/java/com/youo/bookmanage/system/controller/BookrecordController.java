package com.youo.bookmanage.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.youo.bookmanage.response.Result;
import com.youo.bookmanage.security.Role;
import com.youo.bookmanage.system.entity.Bookrecord;
import com.youo.bookmanage.system.service.BookService;
import com.youo.bookmanage.system.service.BookrecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author pt
 * @since 2020-11-12
 */
@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/system/bookrecord")
@Api(value="借还记录模块",tags="借还记录接口")
public class BookrecordController {
    @Autowired
    private BookrecordService bookrecordService;
    @Autowired
    private BookService bookService;

    @Role(roles= {"1","2"})
    @GetMapping("/borrid/{borrid}")
    @ApiOperation(value="查询用户所有借还记录",notes="传入用户id,查询其借还书记录")
    public Result findBorrBR(@PathVariable int borrid){
        QueryWrapper<Bookrecord> wrapper = new QueryWrapper<>();
        wrapper.eq("borrid",borrid);
        List<Bookrecord> brlist = bookrecordService.list(wrapper);
        return Result.ok().data("brlist",brlist);
    }

    @Role(roles="1")
    @GetMapping("/adminid/{adminid}")
    @ApiOperation(value="查询管理员所有操作记录",notes="传入管理员id,查询其操作记录")
    public Result findAdminBR(@PathVariable int adminid){
        QueryWrapper<Bookrecord> wrapper = new QueryWrapper<>();
        wrapper.eq("adminid",adminid);
        List<Bookrecord> brlist = bookrecordService.list(wrapper);
        return Result.ok().data("brlist",brlist);
    }

    @Role(roles="1")
    @GetMapping("/date/{day}")
    @ApiOperation(value="查询某天借还记录",notes="传入yyyy-MM-dd格式日期查询当天操作记录")
    public Result findDayBR(@PathVariable String day){
        QueryWrapper<Bookrecord> wrapper = new QueryWrapper<>();
        wrapper.like("date",day);
        List<Bookrecord> brlist = bookrecordService.list(wrapper);
        return Result.ok().data("brlist",brlist);
    }

    /**
     * 用户查询自己所借未还清的图书
     * @param id
     * @return
     */
//此处需做区分,因为如果遵循restful风格的话url与上一个方法同名
    @Role(roles={"1","2"})
    @GetMapping("/user/{id}")
    @ApiOperation(value="查询用户所借未还清图书",notes="根据用户id查询所借未还图书")
    public Result findBookByUid(@PathVariable Integer id){
        return bookrecordService.findBookByUid(id);
    }


}

