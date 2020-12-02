package com.youo.bookmanage.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youo.bookmanage.handler.BusinessException;
import com.youo.bookmanage.response.Result;
import com.youo.bookmanage.response.ResultCode;
import com.youo.bookmanage.security.Role;
import com.youo.bookmanage.system.entity.Book;
import com.youo.bookmanage.system.entity.User;
import com.youo.bookmanage.system.service.BookService;
import com.youo.bookmanage.system.service.UserService;
import com.youo.bookmanage.system.vo.BookVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
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
 * @since 2020-11-12
 */
@CrossOrigin
@RestController
@RequestMapping("/system/book")
@Api(value="图书模块",tags="图书接口")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;
    /**
     * 通用查询图书列表
     * @return
     */
    @Role(roles={"1","2"})
    @PostMapping("/page")
    @ApiOperation(value="通用分页图书列表",notes="根据前端传递条件查询图书信息")
    public Result findUsersList(@RequestParam Integer current,
                                @RequestParam Integer size,
                                @RequestBody BookVO bookvo){
        QueryWrapper<Book> wrapper1 = getWrapper(bookvo);
        Page<Book> page = new Page<>(current,size);
        Page<Book> booksPage = bookService.page(page,wrapper1);
        long total = booksPage.getTotal();
        List<Book> records = booksPage.getRecords();
        System.out.println("dsfsfsdfss");
        return Result.ok().data("total",total).data("records",records);



    }

    /*获取查询条件构造器*/
    private QueryWrapper<Book> getWrapper(@RequestBody BookVO bookvo) {
        QueryWrapper<Book> wrapper = new QueryWrapper<Book>();
        if(bookvo!=null){
            if (!StringUtils.isEmpty(bookvo.getBookname())) {
                wrapper.eq("name",bookvo.getBookname());
            }
            if (!StringUtils.isEmpty(bookvo.getAuthor())) {
                wrapper.eq("author",bookvo.getAuthor());
            }
            if (bookvo.getStock()!=0) {
                wrapper.eq("stock",bookvo.getStock());
            }


        }
        return wrapper;
    }

    /**
     * 添加图书
     * @param book
     * @return
     */
    @Role(roles="1")
    @ApiOperation(value = "添加图书",notes = "添加图书信息")
    @PostMapping("")
    public Result addBook(@Validated Book book){

        return bookService.addBook(book);
    }


    /**
     * 删除图书
     * @param bookid
     * @return
     */
    @Role(roles="1")
    @ApiOperation(value = "删除图书",notes = "删除图书，根据图书id" )
    @DeleteMapping("/{bookid}")
//    接收请求路径中占位符的值
    public Result delete(@PathVariable int bookid){
        bookService.deleteById(bookid);
        return Result.ok();
    }




    @Role(roles="1")
    @ApiOperation(value = "借还操作", notes = "借还操作,opcode为1是借，为0是还，num出库用负数，入库用正数")
    @PutMapping("/{name}/{num}/{opcode}/{admin}/{borr}")
    public Result updateBookStock(@PathVariable String name,
                                  @PathVariable int opcode,
                                  @PathVariable int num,
                                  @PathVariable String admin,
                                  @PathVariable String borr) {
        User userByName1 = userService.findUserByName(admin);
        User userByName2 = userService.findUserByName(borr);
        if(userByName1==null||userByName2==null){
            throw new BusinessException(ResultCode.USER_ACCOUNT_NOT_EXIST.getCode(),
                    ResultCode.USER_ACCOUNT_NOT_EXIST.getMessage());
        }
        bookService.updateBookStock(name,opcode,num,userByName1.getUid(),userByName2.getUid());
        return Result.ok();

    }


    /**
     * 修改图书
     * @param book
     * @return
     */

    @Role(roles="1")
    @ApiOperation(value = "修改图书信息", notes = "修改图书信息")
    @PutMapping
    public Result updateBook(@Validated Book book) {
        bookService.updateById(book);
        return Result.ok();

    }
}

