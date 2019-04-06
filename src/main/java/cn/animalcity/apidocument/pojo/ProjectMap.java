package cn.animalcity.apidocument.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author: fuyitao
 * @date: 2019/03/31
 */

@Data
@TableName("project_map")
@Accessors(chain = true)
public class ProjectMap extends AbstractProjectParent implements Serializable {

    private static final long serialVersionUID = -562524645544390389L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer projectId;

    private Integer moduleId;

    private Integer apiId;

    private Integer pid;

}
