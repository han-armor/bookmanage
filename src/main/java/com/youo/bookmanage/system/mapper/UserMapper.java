package com.youo.bookmanage.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youo.bookmanage.system.entity.Menu;
import com.youo.bookmanage.system.entity.User;
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
//解决无法使用@AutoWired
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select distinct menu.menu_name\n" +
            "from user,partmenu,menu\n" +
            "where user.portid = partmenu.role_id and user.portid=#{portid} and partmenu.menu_id= menu.id;")
    public List<Menu> findMenuByPortid(int portid);


}
