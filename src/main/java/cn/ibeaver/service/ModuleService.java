package cn.ibeaver.service;/**
 * Created by fuyitao on 19-3-18.
 */

import cn.ibeaver.dao.ModuleMapper;
import cn.ibeaver.pojo.Module;
import cn.ibeaver.pojo.ProjectMap;
import cn.ibeaver.pojo.SysUser;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName IModuleService
 * @Description TODO
 * @Author fuyitao
 * @Date 2019-3-18 11:10
 * @Version 1.0
 **/
@Service
public class ModuleService {

	@Autowired
	private ModuleMapper moduleMapper;

	@Autowired
	private ProjectMapService projectMapService;

	public int addModule(Module module, SysUser user, Integer projectId) {
		module.setCreateTime(new Date());
		module.setCreateBy(user.getId());
		module.setCreateByName(user.getLoginName());
		moduleMapper.insert(module);

		ProjectMap projectMap = new ProjectMap();
		projectMap.setModuleId(module.getId());
		projectMap.setName(module.getName());
		projectMap.setPid(projectId);
		projectMap.setUri(module.getUri());
		projectMapService.insert(projectMap);

		return 0;
	}

	public int deleteModule(Integer moduleId, Integer projectId) {
		moduleMapper.deleteById(moduleId);
		return projectMapService.delete("module", moduleId, projectId);
	}

	public int updateModule(Module module, SysUser user, Integer projectId) {
		module.setUpdateTime(new Date());
		module.setUpdateBy(user.getId());
		module.setUpdateByName(user.getLoginName());
		moduleMapper.updateById(module);

		ProjectMap projectMap = new ProjectMap();
		projectMap.setModuleId(module.getId());
		projectMap.setName(module.getName());
		projectMap.setPid(projectId);
		projectMap.setUri(module.getUri());

		return 0;
	}

	public Module getModuleById(Integer moduleId) {
		QueryWrapper<Module> wrapper = new QueryWrapper<>();
		wrapper.eq("id", moduleId);
		return moduleMapper.selectOne(wrapper);
	}

	/*int addModule(Module module);

	int deleteModuleById(Integer id, Integer projectId);

	int updateModule(Module module);

	Module getModuleByIdAndProjectId(@Param("id") Integer id, @Param("projectId") Integer projectId);

	List<Module> getModulesByProjectId(Integer projectId);*/
}
