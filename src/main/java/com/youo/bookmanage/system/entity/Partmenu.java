package com.youo.bookmanage.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author pt
 * @since 2020-11-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Partmenu对象", description="")
public class Partmenu implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long roleId;

    private Long menuId;
    @TableId(value = "uid", type = IdType.AUTO)
    @NotNull(message = "主键不能为空")
    private int rmid;
    @ApiModelProperty(value = "角色名字")
    @TableField(exist = false)
    private String name;
    @ApiModelProperty(value = "菜单名字")
    @TableField(exist = false)
    private String menuname;

}
