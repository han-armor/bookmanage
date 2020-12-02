package com.youo.bookmanage.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youo.bookmanage.system.entity.Book;
import com.youo.bookmanage.system.entity.Bookrecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author pt
 * @since 2020-11-12
 */

@Mapper
public interface BookrecordMapper extends BaseMapper<Bookrecord> {


    @Select("select bid, name,author\n"+
            "from book,bookrecord\n"+
            "where book.bid=bookrecord.bookid and borrid=#{id}\n"+
            "GROUP BY bookid\n"+
            "having sum(num)<>0;")
    public List<Book> findBookByUid(Integer id);


}
