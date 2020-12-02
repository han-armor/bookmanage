package com.youo.bookmanage.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.youo.bookmanage.response.Result;
import com.youo.bookmanage.system.entity.Book;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pt
 * @since 2020-11-12
 */
public interface BookService extends IService<Book> {

    Result addBook(Book book);

    void deleteById(int bookid);

    void updateBookStock(String name, int opcode, int num, int admin, int borr);

}
