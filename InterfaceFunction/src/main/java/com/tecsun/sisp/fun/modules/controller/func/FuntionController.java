package com.tecsun.sisp.fun.modules.controller.func;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tecsun.sisp.fun.bean.TreeBean;
import com.tecsun.sisp.fun.common.Result;
import com.tecsun.sisp.fun.modules.controller.BaseController;
import com.tecsun.sisp.fun.modules.entity.func.AppIfaceVO;
import com.tecsun.sisp.fun.modules.service.impl.FuncServiceImpl;

@Controller
@RequestMapping(value = "/interfacefunction/app")
public class FuntionController  extends BaseController{

	private static Logger logger = LoggerFactory.getLogger(FuntionController.class);
	
	@Autowired
	private FuncServiceImpl FuncService;
	
	@RequestMapping(value = "/getAppTree", method = RequestMethod.GET)
	@ResponseBody
	public Result getAppTree(){
		try{
			List<AppIfaceVO> list = FuncService.getAppList(new AppIfaceVO());
			TreeBean treeBean = new TreeBean();
			treeBean.setText("接口功能管理");
			treeBean.setValue("0");
			treeBean.setId("-1");
			treeBean.setParentId(-1);
			treeBean.setCheckstate(0);
			treeBean.setComplete(true);
			treeBean.setIsexpand(true);
			treeBean.setShowcheck(false);
			treeBean.setHasChildren(true);
			treeBean.setChildNodes(getAppTree(0, list));
			return ok("", treeBean);
		}catch(Exception e){
			logger.error("--获取接口功能树失败--"+e);
			return error("获取接口功能树失败", null);
		}
	}
	
	private List<TreeBean> getAppTree(long parent_id , List<AppIfaceVO> apps){
		List<TreeBean> list = new ArrayList<TreeBean>();
		if(!apps.isEmpty()){
			for(AppIfaceVO app : apps){
				if(app.getParentId() == parent_id){
					TreeBean treeBean = new TreeBean();
					treeBean.setText(app.getName());
					treeBean.setValue(app.getId() + "");
					treeBean.setId(app.getId() + "");
					treeBean.setParentId(app.getParentId());
					treeBean.setCheckstate(0);
					treeBean.setComplete(true);
					treeBean.setIsexpand(false);
					treeBean.setShowcheck(true);
					treeBean.setHasChildren(true);
					treeBean.setChildNodes(getAppTree(app.getId(),apps));
					list.add(treeBean);
				}
			}
		}
		return list;
	}


	/**
	 *  新增-修改
	 * @return
	 */
	@RequestMapping(value = "/updateApp/{user_id}/{app_id}", method = RequestMethod.POST ,produces = "application/json")
	@ResponseBody
	public Result updateApp(@PathVariable String user_id , @PathVariable String app_id , @RequestBody AppIfaceVO vo){
		try{
			boolean flag = false;
			vo.setUpdateTime(new Timestamp(new Date().getTime()));
			if(vo.getId() == 0){ //新增
				vo.setCreateTime(new Timestamp(new Date().getTime()));
				flag = FuncService.insert(vo);
			}else{ //修改
				flag = FuncService.update(vo);
			}
			return ok("success",flag);
		}catch(Exception e){
			logger.error("修改接口功能失败", e);
			e.printStackTrace();
			return error("error" , null);
		}
	}
	
	/**
	 * 查看详细
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getAppById/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Result getAppById(@PathVariable String id){
		if(id.equals("0")){
			return ok("" ,"接口功能管理");
		}
		AppIfaceVO vo = new AppIfaceVO();
		vo.setId(Long.parseLong(id));
		List<AppIfaceVO> list =FuncService.getAppList(vo);
		if(!list.isEmpty()) vo = list.get(0);
		return ok("" , vo);
	}
	
	/**
	 *  判断编号或名称是否已存在
	 */
	@RequestMapping(value = "/isExits", method = RequestMethod.GET)
	@ResponseBody
	public Result isExitCodeOrName(HttpServletRequest request){
		try{
			String code = request.getParameter("code");
			String name = request.getParameter("name");
			code = new String(code.getBytes("iso8859-1"), "UTF-8");
			name = new String(name .getBytes("iso8859-1"), "UTF-8");
			String id = request.getParameter("id");
			AppIfaceVO vo = new AppIfaceVO();
			if(!isEmptyStr(id)){
				vo.setId(Long.parseLong(id));
			}
			vo.setCode(code);
			vo.setName(name);
			List<AppIfaceVO> list = FuncService.isExitCodeOrName(vo);
			if(!list.isEmpty()) return ok("","300"); else return ok("","200");
		}catch(Exception e){
			logger.error("查询 判断编号或名称是否已存在 失败", e);
			return error("查询失败" , null);
		}
	}
	
	/**
	 * 删除
	 * @param user_id
	 * @param app_id
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/deleteApp/{ids}/{user_id}/{app_id}", method = RequestMethod.DELETE ,produces = "application/json")
	@ResponseBody
	public Result deleteApp(@PathVariable String user_id,@PathVariable String app_id,@PathVariable("ids") String ids){
		try{
			String [] id = ids.split(",");
			for(int i = 0 ; i < id.length ; i++){
				FuncService.delete(Long.parseLong(id[i]));
			}
			return ok("success",null);
		}catch(Exception e){
			logger.error("删除接口功能失败", e);
			return error("",null);
		}
	}
}
