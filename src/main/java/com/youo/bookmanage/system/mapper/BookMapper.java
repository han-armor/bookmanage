package com.youo.bookmanage.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youo.bookmanage.system.entity.Book;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author pt
 * @since 2020-11-12
 */
@Mapper
public interface BookMapper extends BaseMapper<Book> {

}
