/*
 * Created by fuyitao on 19-3-17.
 */
package cn.animalcity.apidocument.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Api
 * @Description TODO 接口相关实体类
 * @Author fuyitao
 * @Date 2019-3-17 22:54
 * @Version 1.0
 **/
@Data
@TableName(value = "api", resultMap = "apiMapperResultMap")
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Api extends AbstractProjectParent implements Serializable {

    private static final long serialVersionUID = -1873186146325734879L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 拼接给前台显示的uri
     * baseUrl/uri/uri
     */
    private String showUri;

    /**
     * 接口描述
     */
    private String description;

    /**
     * content-type
     */
    private String contentType;

    /**
     * 请求方式
     */
    private String method;

    /**
     * 请求参数
     */
    @TableField("request_param")
    private String requestParam;

    /**
     * 相应参数
     */
    @TableField("response_param")
    private String responseParam;

    /**
     * 所属项目id
     */
    private Integer projectId;

    /**
     * 所属模块id
     */
    private Integer moduleId;

    /**
     * 创建人id
     */
    private Integer createBy;

    /**
     * 创建人名字
     */
    private String createByName;

    /**
     * 修改人id
     */
    private Integer updateBy;

    /**
     * 修改人名字
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
