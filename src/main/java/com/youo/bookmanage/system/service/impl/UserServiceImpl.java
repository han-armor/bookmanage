package com.youo.bookmanage.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youo.bookmanage.handler.BusinessException;
import com.youo.bookmanage.response.ResultCode;
import com.youo.bookmanage.system.entity.Menu;
import com.youo.bookmanage.system.entity.User;
import com.youo.bookmanage.system.mapper.UserMapper;
import com.youo.bookmanage.system.service.UserService;
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
@Service("UserService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;
    public void addUser(User user) {
        //判断是否增加了同一个用户
        String name = user.getName();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name",name);
        Integer count = this.baseMapper.selectCount(wrapper);
        if(count!=0){
            throw new BusinessException(ResultCode.USER_ACCOUNT_ALREADY_EXIST.getCode(),
                    ResultCode.USER_ACCOUNT_ALREADY_EXIST.getMessage());
        }

        this.baseMapper.insert(user);
    }


    @Override
    public User findUserByName(String name) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);
        User user = userMapper.selectOne(queryWrapper);
        if(user!=null)
            return user;
        throw new BusinessException(ResultCode.USER_ACCOUNT_NOT_EXIST.getCode(),
                ResultCode.USER_ACCOUNT_NOT_EXIST.getMessage());
    }


    @Override
    public List<Menu> findMenuByPartid(int partid) {
        return userMapper.findMenuByPortid(partid);
    }

    @Override
    public void deleteById(int id) {
            userMapper.deleteById(id);
    }

    @Override
    public void updateUserPsw(String name, String psw, String newPsw) {
        User user = findUserByName(name);
        if(user.getPassword().equals(psw)){
            user.setPassword(newPsw);
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("name",name);//更新的条件
            userMapper.update(user, queryWrapper);
        }else{
            throw new BusinessException(ResultCode.USER_CREDENTIALS_ERROR.getCode(),ResultCode.USER_CREDENTIALS_ERROR.getMessage());
        }
    }


}
