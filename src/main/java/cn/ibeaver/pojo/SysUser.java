package cn.ibeaver.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sun.deploy.security.ValidationState;
import lombok.Data;

import java.io.Serializable;

/**
 * @author fuyitao
 * @date 2019/3/29
 */

@Data
public class SysUser implements Serializable {

    private static final long serialVersionUID = -1555932429329257100L;
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * password
     */
    private String password;

    /**
     * salt
     */
    private String salt;

}
