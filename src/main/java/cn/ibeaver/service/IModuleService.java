package cn.ibeaver.service;/**
 * Created by fuyitao on 19-3-18.
 */

import cn.ibeaver.pojo.Module;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName IModuleService
 * @Description TODO
 * @Author fuyitao
 * @Date 2019-3-18 11:10
 * @Version 1.0
 **/
public interface IModuleService {

	int addModule(Module module);

	int deleteModuleById(Integer id, Integer projectId);

	int updateModule(Module module);

	Module getModuleByIdAndProjectId(@Param("id") Integer id, @Param("projectId") Integer projectId);

	List<Module> getModulesByProjectId(Integer projectId);
}
