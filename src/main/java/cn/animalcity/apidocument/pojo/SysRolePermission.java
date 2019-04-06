package cn.animalcity.apidocument.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author fuyitao
 * @date 2019/3/29
 */
@Data
public class SysRolePermission implements Serializable {

    private static final long serialVersionUID = -8388101351692251226L;

    private Integer id;

    private Integer roleId;

    private Integer permissionId;

}
