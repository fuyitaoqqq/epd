package cn.ibeaver.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author: fuyitao
 * @date: 2019/03/31
 */

@Data
@TableName("project_map")
public class ProjectMap {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String uri;

    private Integer projectId;

    private Integer moduleId;

    private Integer apiId;

    private Integer pid;

}
