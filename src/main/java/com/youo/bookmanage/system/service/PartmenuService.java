package com.youo.bookmanage.system.service;

import com.youo.bookmanage.system.entity.Partmenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pt
 * @since 2020-11-13
 */
public interface PartmenuService extends IService<Partmenu> {
    List<Partmenu> findAll();

}
