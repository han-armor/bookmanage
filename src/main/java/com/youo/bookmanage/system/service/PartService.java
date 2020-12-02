package com.youo.bookmanage.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.youo.bookmanage.system.entity.Part;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pt
 * @since 2020-11-13
 */
public interface PartService extends IService<Part> {
     List<Part> findRoleAndCount();
}
