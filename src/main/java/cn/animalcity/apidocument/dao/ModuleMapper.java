/*
 * Created by fuyitao on 19-3-18.
 */
package cn.animalcity.apidocument.dao;

import cn.animalcity.apidocument.pojo.Module;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName ModuleMapper
 * @Description TODO
 * @Author fuyitao
 * @Date 2019-3-18 10:53
 * @Version 1.0
 **/
@Component
public interface ModuleMapper extends BaseMapper<Module> {
}
