package com.youo.bookmanage.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youo.bookmanage.system.entity.Part;
import com.youo.bookmanage.system.mapper.PartMapper;
import com.youo.bookmanage.system.service.PartService;
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
public class PartServiceImpl extends ServiceImpl<PartMapper, Part> implements PartService {

    @Resource
    private PartMapper partMapper;
    @Override
    public List<Part> findRoleAndCount() {
        List<Part> roleAndCount = partMapper.findRoleAndCount();
        return roleAndCount;


    }
}
