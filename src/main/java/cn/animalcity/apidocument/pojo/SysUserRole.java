package cn.animalcity.apidocument.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author fuyitao
 * @date 2019/3/28
 */
@Data
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 2705079410387672887L;

    private Integer id;

    private Integer userId;

    private Integer roleId;

}
