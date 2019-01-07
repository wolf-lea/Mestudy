package com.tecsun.sisp.adapter.modules.account.controller;

import java.io.File;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tecsun.sisp.adapter.common.util.Config;
import com.tecsun.sisp.adapter.common.util.Constants;
import com.tecsun.sisp.adapter.common.util.ImageChangeUtil;
import com.tecsun.sisp.adapter.common.util.InvokeUtil;
import com.tecsun.sisp.adapter.common.util.Page;
import com.tecsun.sisp.adapter.common.util.Result;
import com.tecsun.sisp.adapter.modules.account.entity.request.AccountBean;
import com.tecsun.sisp.adapter.modules.account.entity.response.AccountVO;
import com.tecsun.sisp.adapter.modules.account.entity.response.RoleVO;
import com.tecsun.sisp.adapter.modules.account.service.impl.AccountServiceImpl;
import com.tecsun.sisp.adapter.modules.common.controller.BaseController;
import com.tecsun.sisp.adapter.modules.common.service.impl.CommServiceImpl;
import com.tecsun.sisp.adapter.modules.so.entity.response.EIPayRecordVO;

/**
* @author  wunuanchan
* @version 
* 创建时间：2018年9月5日 下午1:50:55
* 说明：
*/
@Controller
@RequestMapping(value = "/adapter/accountPC")
public class AccountPcController extends BaseController{
	
	
	private static Logger logger = LoggerFactory.getLogger(AccountController.class);
    @Autowired
    private AccountServiceImpl accountService;
    
    @Autowired
    private CommServiceImpl commService;
    
    private String user = Config.getInstance().get("card.user");
    private String password = Config.getInstance().get("card.pwd");
    
    
    
    /**
     * 账号管理-pc端查询微信绑定/解绑列表
     *
     * @param bean
     * @return
     */

    @RequestMapping(value = "/getPCBindList", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getRoleList(@RequestBody AccountBean bean) throws Exception {
        String statusCode = Constants.RESULT_MESSAGE_SUCCESS;
        String message = "查询成功";
        
        Page<AccountVO> page = new Page<AccountVO>(bean.getPageno(), bean.getPagesize());
		List<AccountVO> accountVOList = new ArrayList<AccountVO>();
		
        try {
        	bean.setPage(page);
           accountVOList = accountService.getPCBindList4Cssp(bean);
           for(AccountVO accountVO:accountVOList){
        	// 调用第三方接口入参
               String idCard = accountVO.getSfzh();//身份证号
               String name = accountVO.getAccountName();//姓名
               String cityCode = idCard.substring(0, 4) + "00";//所属城市编码
               // 组装入参格式
               String[] param = {user, password, idCard, name, cityCode};
               // 调用第三方接口
               Map<String, String> map = InvokeUtil.invoke("getData", param);
               // 系统按aab301+aac002进行查询，然后比对aac003姓名，返回类XML串，
               // 当<ERR>域返回“OK”时信息有效，否则<ERR>域为出错信息。
               // <VALIDTAG>域为负数时表示制卡失败，<REMARKS>中是失败原因。
               if (map.containsKey("ERR")) {
                   if (Constants.CARD_ERR_OK.equals(map.get("ERR"))) {
                      accountVO.setPhone(map.get("MOBILE"));     
               }
               }   
           }
           page.setData(accountVOList);
        } catch (Exception e) {
        	 statusCode = Constants.RESULT_MESSAGE_ERROR;
             message = "查询列表失败";
            logger.error("pc端查询微信绑定/解绑列表失败：", e);
        }
        return result(statusCode, message,page);
    }
    
    
    /**
     * 账号管理-pc端查询微信绑定信息详情
     *
     * @param bean
     * @return
     */

    @RequestMapping(value = "/getPCBindDetail", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getPCBindDetail(@RequestBody AccountBean bean) throws Exception {
        String statusCode = Constants.RESULT_MESSAGE_SUCCESS;
        String message = "查询成功";
        if (StringUtils.isBlank(bean.getAccountId())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "用户账号不能为空");
        }
       AccountVO accountVO = null;
        try {
           accountVO = accountService.getPCBindDetail4Cssp(bean);
           if(accountVO!=null){
        	   if(StringUtils.isNotEmpty(accountVO.getComparePic())){
        	   String path  = commService.getPicturePath("101");//获取图片类型为101的图片路径
        	   String filepath = path + File.separator + accountVO.getComparePic();
        	   String comparePic = ImageChangeUtil.getImageStr(filepath);//图片转BASE64
        	   accountVO.setComparePic(comparePic); 
        	   }
        	   
        	   // 调用第三方接口入参
               String idCard = accountVO.getSfzh();//身份证号
               String name = accountVO.getAccountName();//姓名
               String cityCode = idCard.substring(0, 4) + "00";//所属城市编码
               // 组装入参格式
               String[] param = {user, password, idCard, name, cityCode};
               // 调用第三方接口
               Map<String, String> map = InvokeUtil.invoke("getData", param);
               // 系统按aab301+aac002进行查询，然后比对aac003姓名，返回类XML串，
               // 当<ERR>域返回“OK”时信息有效，否则<ERR>域为出错信息。
               // <VALIDTAG>域为负数时表示制卡失败，<REMARKS>中是失败原因。
               if (map.containsKey("ERR")) {
                   if (Constants.CARD_ERR_OK.equals(map.get("ERR"))) {
                      accountVO.setCardPic(map.get("PHOTO"));
                      accountVO.setPhone(map.get("MOBILE"));
               }
               }   
           }
            
        } catch (Exception e) {
        	 statusCode = Constants.RESULT_MESSAGE_ERROR;
             message = "pc端查询微信绑定信息详情";
            logger.error("pc端查询微信绑定信息详情失败：", e);
        }
        return result(statusCode, message,accountVO);
    }
    
    
    /**
     * 账号管理-pc端微信绑定/解绑
     *
     * @param bean
     * @return
     */

    @RequestMapping(value = "/bindOrUntiePC", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result bindOrUntiePC(@RequestBody AccountBean bean) throws Exception {
        String statusCode = Constants.RESULT_MESSAGE_SUCCESS;
        String message = "查询成功";
        if (StringUtils.isBlank(bean.getAccountId())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "用户账号不能为空");
        }
        if(StringUtils.isBlank(bean.getIsSuccessBind())){
        	return this.result(Constants.RESULT_MESSAGE_PARAM, "绑定/解绑标志不能为空");
        }
        try {
      	
            	if("0".equals(bean.getIsSuccessBind())){
            	List<AccountVO> list = accountService.getAccountInfoByAccountId4Cssp(bean.getAccountId(),"1");
            	if (list != null && !list.isEmpty()) {   	
                long status = accountService.disassociateWechat4Cssp(bean);
                if (status > 0) {
                    message = "解绑成功";
                } else{
                	statusCode = Constants.RESULT_MESSAGE_ERROR;
                	message = "解绑失败";
                }
            	}else {
                	statusCode = Constants.RESULT_MESSAGE_ERROR;
                    message = "该用户不存在";
                }
              }else{
            	  List<AccountVO> list = accountService.getAccountInfoByAccountId4Cssp(bean.getAccountId(),"0");
              	  if (list != null && !list.isEmpty()) {   
            	  long status = accountService.bindWechat4Cssp(bean);
                  if (status > 0) {
                      message = "绑定成功";
                  } else{
                  	statusCode = Constants.RESULT_MESSAGE_ERROR;
                  	message = "绑定失败";
                  }
              	  }else {
                	statusCode = Constants.RESULT_MESSAGE_ERROR;
                    message = "该用户不存在";
                }
              }              
        } catch (Exception e) {
        	 statusCode = Constants.RESULT_MESSAGE_ERROR;
             message = "pc端微信绑定/解绑失败";
            logger.error("pc端微信绑定/解绑失败：", e);
        }
        return result(statusCode, message);
    }
    

}
