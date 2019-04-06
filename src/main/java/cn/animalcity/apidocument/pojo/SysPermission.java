package cn.animalcity.apidocument.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author fuyitao
 * @date 2019/3/29
 */
@Data
public class SysPermission implements Serializable {

    private static final long serialVersionUID = 5275292727776878397L;

    /**
     * id
     */
    private Integer id;

    /**
     * permission name
     */
    private String name;

    /**
     * permission description
     */
    private String description;

    /**
     * permission url
     */
    private String url;

}
