package com.youo.bookmanage.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.youo.bookmanage.system.entity.Menu;
import com.youo.bookmanage.system.entity.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pt
 * @since 2020-11-12
 */
public interface UserService extends IService<User> {

    void addUser(User user);

    User findUserByName(String name);

    List<Menu> findMenuByPartid(int partid);

    void deleteById(int id);

    void updateUserPsw(String name,String psw,String newPsw);



}
