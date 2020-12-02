package com.youo.bookmanage.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youo.bookmanage.response.Result;
import com.youo.bookmanage.system.entity.Book;
import com.youo.bookmanage.system.entity.Bookrecord;
import com.youo.bookmanage.system.mapper.BookrecordMapper;
import com.youo.bookmanage.system.service.BookrecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pt
 * @since 2020-11-12
 */
@Service
public class BookrecordServiceImpl extends ServiceImpl<BookrecordMapper, Bookrecord> implements BookrecordService {

    @Resource
    private BookrecordMapper bookrecordMapper;
    @Override
    public Result findBookByUid(Integer id) {
        List<Book> list = bookrecordMapper.findBookByUid(id);

            return Result.ok().data("list",list);

    }
}
