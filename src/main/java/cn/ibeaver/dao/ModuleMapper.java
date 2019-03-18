/*
 * Created by fuyitao on 19-3-18.
 */
package cn.ibeaver.dao;

import cn.ibeaver.pojo.Module;
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
public interface ModuleMapper {

	int addModule(Module module);

	int deleteModuleById(@Param("id") Integer id, @Param("projectId") Integer projectId);

	int updateModule(Module module);

	Module getModuleByIdAndProjectId(@Param("id") Integer id, @Param("projectId") Integer projectId);

	List<Module> getModulesByProjectId(Integer projectId);

}
