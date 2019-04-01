/*
 * Created by fuyitao on 19-3-17.
 */
package cn.ibeaver.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Api
 * @Description TODO 项目模块相关实体类
 * @Author fuyitao
 * @Date 2019-3-17 22:50
 * @Version 1.0
 **/
@Data
@TableName("module")
public class Module implements Serializable {

    private static final long serialVersionUID = 404302089604762350L;
    /**
     * 主键ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 模块名
     */
    private String name;

    /**
     * 模块uri
     */
    private String uri;

    /**
     * 模块描述
     */
    private String description;

    /**
     * 所属项目ID
     */
    private Integer projectId;

    /**
     * 创建人id
     */
    private Integer createBy;

    /**
     * 创建人姓名
     */
    private String createByName;

    /**
     * 修改人id
     */
    private Integer updateBy;

    /**
     * 修改人姓名
     */
    private String updateByName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

}
