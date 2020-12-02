package com.youo.bookmanage.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youo.bookmanage.system.entity.Partmenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author pt
 * @since 2020-11-13
 */
@Mapper
public interface PartmenuMapper extends BaseMapper<Partmenu> {
    List<Partmenu> findAll();

}
