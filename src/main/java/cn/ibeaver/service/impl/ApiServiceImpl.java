package cn.ibeaver.service.impl;/**
 * Created by fuyitao on 19-3-18.
 */

import cn.ibeaver.dao.ApiMapper;
import cn.ibeaver.dto.ResultContants;
import cn.ibeaver.pojo.Api;
import cn.ibeaver.pojo.Module;
import cn.ibeaver.service.IApiService;
import cn.ibeaver.utils.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @ClassName ApiServiceImpl
 * @Description TODO
 * @Author fuyitao
 * @Date 2019-3-18 15:45
 * @Version 1.0
 **/
@Service("apiService")
public class ApiServiceImpl implements IApiService {
	@Override
	public int addApi(Api api) {
		return 0;
	}

	@Override
	public int deleteApi(Integer id, Integer moduleId, Integer projectId) {
		return 0;
	}

	@Override
	public int updateApi(Api api) {
		return 0;
	}

	@Override
	public Api getApiById(Integer id, Integer moduleId, Integer projectId) {
		return null;
	}

	/*@Autowired
	private ApiMapper apiMapper;

	@Autowired
	private IModuleService moduleService;

	@Override
	//@Transactional
	public int addApi(Api api) {
		api.setCreateTime(new Date());

		int i = apiMapper.addApi(api);

		//apiMapper.insert(api);

		*//*Integer moduleId = api.getModuleId();
		Integer projectId = api.getProjectId();

		Module module = moduleService.getModuleByIdAndProjectId(moduleId, projectId);

		List<String> list = CommonUtil.stringToList(module.getApiList());
		list.add(api.getUri());
		String string = CommonUtil.listToString(list);

		module.setApiList(string);
		module.setUpdateTime(new Date());

		int j = moduleService.updateModule(module);

		if (i == 1 && j == 1) {
			return ResultContants.SUCCESS.getCode();
		} else {
			return ResultContants.SYS_ERR.getCode();
		}*//*
return 0;
	}

	@Override
	@Transactional
	public int deleteApi(Integer id, Integer moduleId, Integer projectId) {

		Module module = moduleService.getModuleByIdAndProjectId(moduleId, projectId);
		List<String> list = CommonUtil.stringToList(module.getApiList());

		Api api = getApiById(id, moduleId, projectId);
		list.remove(api.getUri());
		String string = CommonUtil.listToString(list);

		module.setApiList(string);
		module.setUpdateTime(new Date());

		int i = apiMapper.deleteApi(id, moduleId, projectId);
		int j = moduleService.updateModule(module);

		if (i == 1 && j == 1) {
			return ResultContants.SUCCESS.getCode();
		} else {
			return ResultContants.SYS_ERR.getCode();
		}

	}

	@Override
	public int updateApi(Api api) {

		api.setUpdateTime(new Date());
		Api getOne = getApiById(api.getId(), api.getModuleId(), api.getProjectId());

		if (!getOne.getUri().equals(api.getUri())) {
			Module module = moduleService.getModuleByIdAndProjectId(api.getModuleId(), api.getProjectId());
			List<String> list = CommonUtil.stringToList(module.getApiList());
			int index = list.indexOf(getOne.getUri());
			list.set(index, api.getUri());
			String string = CommonUtil.listToString(list);
			module.setApiList(string);
			module.setUpdateTime(new Date());
			int i = apiMapper.updateApi(api);
			int j = moduleService.updateModule(module);
			if (i == 1 && j == 1) {
			return ResultContants.SUCCESS.getCode();
			}
		}
		int i = apiMapper.updateApi(api);
		if (i == 1) {
			return ResultContants.SUCCESS.getCode();
		}
		return ResultContants.SYS_ERR.getCode();
	}

	@Override
	public Api getApiById(Integer id, Integer moduleId, Integer projectId) {
		return apiMapper.getApiById(id, moduleId, projectId);
	}*/
}
