package cn.animalcity.apidocument.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author fuyitao
 * @date 2019/3/29
 */
@Data
public class SysRole implements Serializable {

    private static final long serialVersionUID = -2855111421311596084L;

    /**
     * id
     */
    private Integer id;

    /**
     * role name
     */
    private String name;

    /**
     * role description
     */
    private String description;

    /**
     * role parent pid
     */
    private Integer pid;

}
