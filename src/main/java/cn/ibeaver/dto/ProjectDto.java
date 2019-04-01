package cn.ibeaver.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author fuyitao
 * @date 2019/4/1
 */

@Data
public class ProjectDto implements Serializable {

    private static final long serialVersionUID = 8334520265808184745L;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 项目名转拼音
     */
    private String shorthand;

    /**
     * 项目描述
     */
    private String description;

    /**
     * base url
     */
    private String baseUrl;

    /**
     * 0：私有，1：公开
     */
    private Integer open = 0;

    /**
     * 所有者
     */
    private Integer owner;

    /**
     * 统一返回格式
     */
    private String returnFormat;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    private List<ModuleMapDto> moduleList;

}
