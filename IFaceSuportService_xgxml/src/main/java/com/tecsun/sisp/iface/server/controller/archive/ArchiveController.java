package com.tecsun.sisp.iface.server.controller.archive;

import com.tecsun.sisp.iface.common.util.ArchiveUtil;
import com.tecsun.sisp.iface.common.util.Constants;
import com.tecsun.sisp.iface.common.vo.*;
import com.tecsun.sisp.iface.common.vo.archive.Archive;
import com.tecsun.sisp.iface.server.controller.BaseController;
import com.tecsun.sisp.iface.server.outerface.archive.ArchiveIface;
import com.tecsun.sisp.iface.server.util.DictionaryUtil;
import com.tecsun.sisp.iface.server.util.Dom4JUtil;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 档案查询controller类；
 * 
 */
@Controller
@RequestMapping(value = "/iface/archive")
public class ArchiveController extends BaseController{
	public static final Logger logger = Logger.getLogger(ArchiveController.class);
    static ArchiveUtil archiveUtil = new ArchiveUtil();
    static ArchiveIface iface = new ArchiveIface();
    

    
    /**
     * 获取档案信息
     * @param bean
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/getArchiveInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getArchiveInfo(@RequestBody ArchiveRequstBean bean) throws Exception {
        String result = Constants.RESULT_MESSAGE_ERROR;
        String message = "查询失败";
        if(StringUtils.isBlank(bean.getAAC002()))return error("身份证号不能为空",null);
        String wsResult = "";  //iface上不做任何的业务处理
        Map<String, String> map = new HashMap<String, String>();
        List<Archive> list = new ArrayList<Archive>();
        try {
            for (int i = 1; i <= 8; i++) {
                String mxl = archiveUtil.getArchiveListXml(bean, Constants.Archive_AAE017.get(i));
                map = iface.getArchiveInfo(mxl);
                wsResult = map.get("wsResult");
                if (wsResult != null && !"".equals(wsResult)) {
                    String returnResult = Dom4JUtil.checkArchiveReturnCode(wsResult);
                    if (returnResult.equals("")) {
                        list = Dom4JUtil.readXMLToJavaBean_Archive(new Archive(), wsResult);
                        
                        result = map.get("result");
                        message = map.get("message");
                        break;
                    }
                }
            }
        } catch (Exception e) {
            result = Constants.RESULT_MESSAGE_ERROR;
            logger.error("获取【档案查询】档案信息查询getArchiveInfo失败！" + wsResult);
        }
        
      /*  if(!list.isEmpty() && list.size()>0){
        	for(int j=0;j<list.size();j++){
        		Archive  archiveVo  = new Archive();
        		System.out.println(list.get(j).getAAE017());
            	archiveVo.setAAE017(DictionaryUtil.getDictName(Constants.ARCHIVEAAE017_GROUP, list.get(j).getAAE017()));
            	System.out.println(archiveVo.getAAE017());
            	list.add(archiveVo);
            	break;
        	}
        	
        }*/
        
        if(!list.isEmpty() && list.size()>0){
    	Archive archiveVo =  list.get(0);
        archiveVo.setAAE017(DictionaryUtil.getDictName(Constants.ARCHIVEAAE017_GROUP, list.get(0).getAAE017()));
    	System.out.println(archiveVo.getAAE017());
    	   List<Archive> list1= new ArrayList<Archive>();
    	   list1.add(archiveVo);
    	 return this.result(result, message, list1);
        }else{
        	message ="返回值失败";
        	 result = Constants.RESULT_MESSAGE_ERROR;
        	 return this.result(result, message);
        }
       
    }
    

}
