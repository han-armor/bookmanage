package com.youo.bookmanage.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author pt
 * @since 2020-11-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Bookrecord对象", description="")
public class Bookrecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "brid", type = IdType.AUTO)
    private Integer brid;

    private Integer bookid;

    private Integer adminid;

    private Integer borrid;

    private Integer opcode;

    private String date;

    private Integer num;


}
