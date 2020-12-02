package com.youo.bookmanage.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youo.bookmanage.system.entity.Partmenu;
import com.youo.bookmanage.system.mapper.PartmenuMapper;
import com.youo.bookmanage.system.service.PartmenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pt
 * @since 2020-11-13
 */
@Service
public class PartmenuServiceImpl extends ServiceImpl<PartmenuMapper, Partmenu> implements PartmenuService {

    @Resource
    private PartmenuMapper partmenuMapper;
    @Override
    public List<Partmenu> findAll() {
        return partmenuMapper.findAll();
    }
}
