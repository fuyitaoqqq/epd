package cn.animalcity.apidocument.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author fuyitao
 * @date 2019/3/29
 */

@Data
@Component
public class SysUser implements Serializable {

    private static final long serialVersionUID = -1555932429329257100L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String loginName;

    private String password;

}
