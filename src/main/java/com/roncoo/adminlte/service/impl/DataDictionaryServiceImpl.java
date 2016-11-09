/*
 * Copyright 2015-2016 RonCoo(http://www.roncoo.com) Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.roncoo.adminlte.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.roncoo.adminlte.bean.Result;
import com.roncoo.adminlte.bean.entity.RcDataDictionary;
import com.roncoo.adminlte.service.DataDictionaryService;
import com.roncoo.adminlte.service.impl.dao.DataDictionaryDao;
import com.roncoo.adminlte.util.base.Page;

/**
 * 数据字典
 * 
 * @author LYQ
 */
@Service
public class DataDictionaryServiceImpl implements DataDictionaryService {

	@Autowired
	private DataDictionaryDao dao;

	@Override
	public Result<RcDataDictionary> save(RcDataDictionary rcDataDictionary) {
		Result<RcDataDictionary> result = new Result<RcDataDictionary>();
		// 校验字段
		if(!StringUtils.hasText(rcDataDictionary.getFieldName())){
			result.setErrMsg("字段名不能为空");
			return result;
		}
		if(!StringUtils.hasText(rcDataDictionary.getFieldCode())){
			result.setErrMsg("Code不能为空");
			return result;
		}
		if(rcDataDictionary.getSort()<1){
			result.setErrMsg("排序无效");
			return result;
		}
		if (dao.insert(rcDataDictionary) > 0) {
			result.setStatus(true);
			result.setErrCode(0);
		}
		return result;
	}

	@Override
	public Result<Page<RcDataDictionary>> listForPage(int pageCurrent, int pageSize) {
		Result<Page<RcDataDictionary>> result = new Result<>();
		if(pageCurrent<1){
			result.setErrMsg("pageCurrent有误");
			return result;
		}
		if(pageSize<1){
			result.setErrMsg("pageSize有误");
			return result;
		}
		result.setResultData(dao.listForPage(pageCurrent, pageSize));
		result.setErrCode(0);
		result.setStatus(true);
		return result;
	}

	@Override
	public Result<RcDataDictionary> queryById(Long id) {
		Result<RcDataDictionary> result = new Result<>();
		if(id<1){
			result.setErrMsg("此id无效");
			return result;
		}
		result.setResultData(dao.selectById(id));
		result.setErrCode(0);
		result.setStatus(true);
		return result;
	}

	@Override
	public Result<RcDataDictionary> deleteById(Long id) {
		Result<RcDataDictionary> result = new Result<>();
		if(id<1){
			result.setErrMsg("此id无效");
			return result;
		}
		if(dao.deleteById(id)>0){
			result.setStatus(true);
			result.setErrCode(0);
		}
		return result;
	}

	@Override
	public Result<RcDataDictionary> update(RcDataDictionary rcDataDictionary) {
		Result<RcDataDictionary> result = new Result<RcDataDictionary>();
		// 校验字段
		// 校验字段
		if(!StringUtils.hasText(rcDataDictionary.getFieldName())){
			result.setErrMsg("字段名不能为空");
			return result;
		}
		if(!StringUtils.hasText(rcDataDictionary.getFieldCode())){
			result.setErrMsg("Code不能为空");
			return result;
		}
		if(rcDataDictionary.getSort()<1){
			result.setErrMsg("排序无效");
			return result;
		}
		if (dao.updateById(rcDataDictionary) > 0) {
			result.setStatus(true);
			result.setErrCode(0);
		}
		return result;
	}

}
