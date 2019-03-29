package cn.ibeaver.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sun.deploy.security.ValidationState;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author fuyitao
 * @date 2019/3/29
 */

@Data
@Component
public class SysUser implements Serializable {

    private static final long serialVersionUID = -1555932429329257100L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String loginName;

    private String password;

}
