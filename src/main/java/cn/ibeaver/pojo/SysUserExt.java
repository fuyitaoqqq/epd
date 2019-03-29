package cn.ibeaver.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author fuyitao
 * @date 2019/3/29
 */
@Data
@Accessors(chain = true)
public class SysUserExt implements Serializable {

    private static final long serialVersionUID = 93838271361613822L;
    /**
     * id
     */
    private Integer uid;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 显示名
     */
    private String username;

    /**
     * 所属组id
     */
    private Integer groupId;

    /**
     * 是否该组管理员
     */
    private Integer groupAdmin;

    /**
     * 创建者id
     */
    private Integer createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新者id
     */
    private Integer updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 最近登录时间
     */
    private Date loginTime;

}
