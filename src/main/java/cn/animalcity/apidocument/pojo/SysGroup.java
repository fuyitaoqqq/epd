package cn.animalcity.apidocument.pojo;

import java.io.Serializable;

/**
 * @author: fuyitao
 * @date: 2019/03/29
 */
 
public class SysGroup implements Serializable {

    private static final long serialVersionUID = 644529613151214198L;

    private Integer id;

    /**
     * 群组名
     */
    private String groupName;

    /**
     * 该组管理员id
     */
    private Integer admin;

}
