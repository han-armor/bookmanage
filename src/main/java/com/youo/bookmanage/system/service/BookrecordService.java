package com.youo.bookmanage.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.youo.bookmanage.response.Result;
import com.youo.bookmanage.system.entity.Bookrecord;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pt
 * @since 2020-11-12
 */
public interface BookrecordService extends IService<Bookrecord> {

    Result findBookByUid(Integer id);

}
