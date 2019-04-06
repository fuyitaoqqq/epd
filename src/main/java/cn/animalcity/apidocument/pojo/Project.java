/*
 * Created by fuyitao on 19-3-17.
 */
package cn.animalcity.apidocument.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Project
 * @Description TODO 项目实体类
 * @Author fuyitao
 * @Date 2019-3-17 22:45
 * @Version 1.0
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("project")
public class Project extends AbstractProjectParent implements Serializable {

    private static final long serialVersionUID = -8871894551565447839L;

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

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
     * 项目所有者姓名
     */
    private String ownerName;

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

}
