package com.youo.bookmanage.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youo.bookmanage.handler.BusinessException;
import com.youo.bookmanage.response.Result;
import com.youo.bookmanage.response.ResultCode;
import com.youo.bookmanage.system.entity.Book;
import com.youo.bookmanage.system.entity.Bookrecord;
import com.youo.bookmanage.system.mapper.BookMapper;
import com.youo.bookmanage.system.mapper.BookrecordMapper;
import com.youo.bookmanage.system.service.BookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.text.SimpleDateFormat;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pt
 * @since 2020-11-12
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

    @Resource
    private BookMapper bookMapper;
    @Resource
    private BookrecordMapper bookrecordMapper;


    @Override
    public Result addBook(Book book) {
        //判断是否增加了同一个书
        String name = book.getName();
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        wrapper.eq("name",name);
        Integer count = this.baseMapper.selectCount(wrapper);
        if(count!=0){
            return Result.error();
        }else {
            this.baseMapper.insert(book);
            return Result.ok();
        }
    }

    @Override
    public void deleteById(int bookid) {
        Book book = bookMapper.selectById(bookid);
        if (book != null) {
            bookMapper.deleteById(bookid);
        }else{
            BusinessException e = new BusinessException(ResultCode.USER_ACCOUNT_NOT_EXIST.getCode(),
                    ResultCode.USER_ACCOUNT_NOT_EXIST.getMessage());
            throw e;
        }


    }


    /**
     * 借还操作,opcode为1时借，为0是还
     * @param name
     * @param opcode
     * @param num
     * @param admin
     * @param borr
     */
    @Override
    public void updateBookStock(String name, int opcode, int num, @NotBlank int admin,@NotBlank int borr) {
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        wrapper.eq("name",name);
        Book book = this.baseMapper.selectOne(wrapper);
        Integer stock = book.getStock();
        Bookrecord bookrecord = new Bookrecord();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        String date = formatter.format(System.currentTimeMillis());
        if(book!=null){
            bookrecord.setAdminid(admin);
            bookrecord.setBorrid(borr);
            bookrecord.setBookid(book.getBid());
            if (opcode == 1) {
                if (stock >= num) {
                    book.setStock(stock - num);
                    bookMapper.updateById(book);
                    bookrecord.setNum(num);
                    bookrecord.setOpcode(1);
                    bookrecord.setDate(date);
                    bookrecordMapper.insert(bookrecord);
                } else {
                    throw new BusinessException(ResultCode.BOOK_STOCK_LACK.getCode(),
                            ResultCode.BOOK_STOCK_LACK.getMessage());
                }
            } else{
                book.setStock(stock + num);
                bookMapper.updateById(book);
                bookrecord.setNum(num);
                bookrecord.setOpcode(0);
                bookrecord.setDate(date);
                bookrecordMapper.insert(bookrecord);
            }
        }else{
            throw new BusinessException(ResultCode.NO_BOOK.getCode(),
                    ResultCode.NO_BOOK.getMessage());

        }

    }
}
