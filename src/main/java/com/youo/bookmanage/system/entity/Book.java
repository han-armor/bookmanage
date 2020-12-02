package com.youo.bookmanage.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
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
@ApiModel(value="Book对象", description="")
@Component
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId(value = "bid", type = IdType.ASSIGN_ID)
    @NotNull(message = "书号不能为空")
    private Integer bid;

    private String name;

    private String author;

    private Integer stock;



}
