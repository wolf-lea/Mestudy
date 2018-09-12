package com.tecsun.sisp.fakamanagement.modules.controller.card;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;


import com.sun.org.apache.bcel.internal.generic.NEW;
import com.tecsun.sisp.fakamanagement.common.utils.StringUtils;
import com.tecsun.sisp.fakamanagement.modules.entity.result.card.*;

import com.tecsun.sisp.fakamanagement.modules.entity.result.common.DistinctAndBankVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.common.LoginPassswordVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.statistics.ORGVO;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.tecsun.sisp.fakamanagement.common.Config;
import com.tecsun.sisp.fakamanagement.common.Page;
import com.tecsun.sisp.fakamanagement.common.Result;
import com.tecsun.sisp.fakamanagement.common.upload.UploadUtil;
import com.tecsun.sisp.fakamanagement.modules.controller.BaseController;
import com.tecsun.sisp.fakamanagement.modules.entity.param.card.CardChangeBean;
import com.tecsun.sisp.fakamanagement.modules.entity.param.card.CardRetentionBean;
import com.tecsun.sisp.fakamanagement.modules.entity.param.card.CardStoreBean;
import com.tecsun.sisp.fakamanagement.modules.entity.result.receive.PrintLogVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.receive.SpeedOfCardVO;
import com.tecsun.sisp.fakamanagement.modules.service.impl.card.CardInfoServiceImpl;
import com.tecsun.sisp.fakamanagement.modules.service.impl.log.LogInfoServiceImpl;
import com.tecsun.sisp.fakamanagement.modules.service.impl.loginuser.LoginUserServiceImpl;
import com.tecsun.sisp.fakamanagement.modules.util.ExcelUtils;
import com.tecsun.sisp.fakamanagement.modules.util.PublicMethod;
import com.tecsun.sisp.fakamanagement.outerface.cardservice.CardServiceUtils;

import oracle.net.aso.p;

/**
 * 
* @ClassName: CardInfoController 
* @Description: TODO(卡信息操作接口类) 
* @author huangtao
* @date 2017年7月7日 下午5:34:48 
*
 */
@Controller
@RequestMapping(value = "/faka/cardInfo")
public class CardInfoController extends BaseController{

	public final static Logger logger = Logger.getLogger(CardInfoController.class);
	
	@Autowired
    private CardInfoServiceImpl cardInfoService;
	@Autowired
	private LoginUserServiceImpl loginUserService;
	@Autowired
    private LogInfoServiceImpl logInfoService;
	
	/**
	 * 单张卡信息保存
	 * @param vo 卡信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/saveCardInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result saveCardInfo(@RequestBody CardInfoVO vo, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		String re=checkCardInfoIsNull(vo);
		if(!re.equals("")){
			return error(re);
		}
		List<CardInfoVO> list=new ArrayList<>();
		try{
            String reginalcode = loginUserService.getReginalCode(vo.getFkwd());
            if(null==reginalcode||reginalcode.equals("")){
                logger.error("获取区域编码为空");
                return error("获取区域编码为空");
            }
            String bank = loginUserService.getBankCode(vo.getFkwd());
            if(null==reginalcode||reginalcode.equals("")){
                logger.error("获取银行编码为空");
                return error("获取银行编码为空");
            }

            vo.setReginalcode(reginalcode);
            vo.setBank(bank);
            list.add(vo);
			cardInfoService.saveCardInfo(list);
			logInfoService.addLog(0,vo.getCardid(),Integer.parseInt(vo.getFkwd()),useridToName(vo.getFkwd()),"初始导入");
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("保存卡信息出错", e);
			return errorORA(e, "保存失败");
		}
		return ok("保存成功");
	}
	
	/**
	 * 判断必要数据值是否为空
	 * @param vo
	 * @return
	 */
	private String checkCardInfoIsNull(CardInfoVO vo){
		if(null==vo.getIdcard()||vo.getIdcard().equals("")){
			return "身份证号码为空";
		}
		if(null==vo.getCardid()||vo.getCardid().equals("")){
			return "社会保障卡卡号为空";
		}
		CardInfoVO temp=new CardInfoVO();
		//temp.setIdcard(vo.getIdcard());
		temp.setCardid(vo.getCardid());
		Page<CardInfoVO> page=cardInfoService.queryCards(new Page<CardInfoVO>(1, 1), temp);
		if (page.getData().size()>0) {
			return "信息已存在";
		}
		if(null==vo.getName()||vo.getName().equals("")){
			return "姓名为空";
		}
		if(null==vo.getBatchno()||vo.getBatchno().equals("")){
			return "批次号为空";
		}
		if(null==vo.getPhone()||vo.getPhone().equals("")){
			return "联系电话为空";
		}
		if(null==vo.getOldcfwz()||vo.getOldcfwz().equals("")){
			return "旧装箱位置为空";
		}
		/*vo.setOldcfwz(vo.getBatchno()+"-"+vo.getOldcfwz());*/
		String[] s=vo.getOldcfwz().split("-");
		if(s.length<3){
			return "旧装箱位置格式错误";
		}
		/*if(null==vo.getBank()||vo.getBank().equals("")){
			return "所属银行为空";
		}
		if(null==vo.getReginalcode()||vo.getReginalcode().equals("")){
			return "城市代码为空";
		}
		if(null==vo.getBankacount()||vo.getBankacount().equals("")){
			return "银行账号为空";
		}*/
		return useridToFkwd(vo);
	}
	
	public String useridToFkwd(CardInfoVO vo) {
		/*if(null==vo.getFkwd()||vo.getFkwd().equals("")){
			return "发卡机构为空";
		}*/
		String fkwd=loginUserService.getFkwd(vo.getFkwd());
		if(null==fkwd||fkwd.equals("")){
			return "发卡机构获取编码为空";
		}
		vo.setFkwd(fkwd);
		return "";
	}
	
	public String useridToName(String id) {
		return loginUserService.getName(id);
	}
	
	/**
	 * Excel导入卡信息
	 * @param file excel文件
	 * @param request
	 * @return
	 */
	@RequestMapping({"saveCardInfos"})
	@ResponseBody
	public Result saveCardInfos(@RequestParam("file") MultipartFile file,@RequestParam("userid")String userid, HttpServletRequest request, @Context HttpServletResponse response){
		String name="SaveCardERR_"+new SimpleDateFormat("yyyyMMddHHmmSS").format(new Date())+".xls";
		List<CardInfoVO> vos=new ArrayList<>();
		List<Map<String, String>> list=new ArrayList<>();
		 HSSFWorkbook cwb=new HSSFWorkbook();
		 HSSFSheet sheet=cwb.createSheet("错误信息");
		 String[] headers=new String[]{"姓名","社会保障号码","社保卡号","批次","单位编号","单位名称","联系地址","联系电话","所属银行","银行卡号","所属城市","装箱位置","错误信息"};
		 ExcelUtils.addHeader(headers, sheet);
		 try {
			String filepath=UploadUtil.getFile(request, "tempexcel");
			Workbook wb=ExcelUtils.getExcel(request.getSession().getServletContext().getRealPath("/") +"tempexcel/"+filepath);
			list=ExcelUtils.EecelToList(wb);
			int i=0;
			for (Map<String, String> map : list) {
				CardInfoVO vo=new CardInfoVO();
				vo.setIdcard(StringUtils.notNullToTrim(map.get("社会保障号码")));
				vo.setCardid(StringUtils.notNullToTrim(map.get("社保卡号")));
				vo.setName(StringUtils.notNullToTrim(map.get("姓名")));
				vo.setBatchno(StringUtils.notNullToTrim(map.get("批次")));
				vo.setCompanycode(StringUtils.notNullToTrim(map.get("单位编号")));
				vo.setCompanyname(StringUtils.notNullToTrim(map.get("单位名称")));
				vo.setAngent(StringUtils.notNullToTrim(map.get("姓名")));
				vo.setAddress(StringUtils.notNullToTrim(map.get("联系地址")));
				vo.setPhone(StringUtils.notNullToTrim(map.get("联系电话")));
				vo.setBank(StringUtils.notNullToTrim(map.get("所属银行")));
				vo.setBankacount(StringUtils.notNullToTrim(map.get("银行卡号")));
				vo.setReginalcode(StringUtils.notNullToTrim(map.get("所属城市")));
				String [] s=StringUtils.notNullToTrim(map.get("装箱位置")).split("-");
				if(s.length<4){
					vo.setOldcfwz("");
				}else{
					String old_c=s[s.length-3]+"-"+s[s.length-2]+"-"+s[s.length-1];
					vo.setOldcfwz(old_c);
				}
				vo.setFkwd(userid);
				String re=checkCardInfoIsNull(vo);
				if(!re.equals("")){
					 ExcelUtils.addData(i, 0, vo.getName(), sheet);
					 ExcelUtils.addData(i, 1, vo.getIdcard(), sheet);
					 ExcelUtils.addData(i, 2, vo.getCardid(), sheet);
					 ExcelUtils.addData(i, 3, vo.getBatchno(), sheet);
					 ExcelUtils.addData(i, 4, vo.getCompanycode(), sheet);
					 ExcelUtils.addData(i, 5, vo.getCompanyname(), sheet);
					 ExcelUtils.addData(i, 6, vo.getAddress(), sheet);
					 ExcelUtils.addData(i, 7, vo.getPhone(), sheet);
					 ExcelUtils.addData(i, 8, vo.getBank(), sheet);
					 ExcelUtils.addData(i, 9, vo.getBankacount(), sheet);
					 ExcelUtils.addData(i, 10, vo.getReginalcode(), sheet);
					 ExcelUtils.addData(i, 11,map.get("装箱位置") , sheet);
					 ExcelUtils.addData(i, 12,re , sheet);
					 i++;
					continue;
				}
				vos.add(vo);
				logInfoService.addLog(0,vo.getCardid(),Integer.parseInt(vo.getFkwd()),useridToName(vo.getFkwd()),"初始导入");
			}
			if(vos.size()>0){
				cardInfoService.saveCardInfo(vos);
			}
			String path=Config.getInstance().get("exp.temp.path");
			File refile=new File(path);
			if (!refile.exists()) {
				refile.mkdirs();
			}
			FileOutputStream fileOut = new FileOutputStream(path+name);  
			cwb.write(fileOut);  
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Excel导入卡信息出错", e);
			return errorORA(e, "导入失败");
		}
		 if(list.size()==vos.size()){
			 return ok("导入成功"+vos.size()+";失败"+(list.size()-vos.size()));
		 }else{
			 return ok("导入成功"+vos.size()+";失败"+(list.size()-vos.size()),name);
		 }
	}
	
	/**
	 * Excel导入卡信息
	 * @param file excel文件
	 * @param request
	 * @return
	 */
	@RequestMapping({"saveCardInfosByTxt"})
	@ResponseBody
	public Result saveCardInfosByTxt(@RequestParam("file") MultipartFile file,@RequestParam("userid")String userid, HttpServletRequest request, @Context HttpServletResponse response){
		String name="SaveCardERR_"+new SimpleDateFormat("yyyyMMddHHmmSS").format(new Date())+".xls";
		List<CardInfoVO> vos=new ArrayList<>();
		List<Map<String, String>> list=new ArrayList<>();
		 HSSFWorkbook cwb=new HSSFWorkbook();
		 HSSFSheet sheet=cwb.createSheet("错误信息");
		 String[] headers=new String[]{"姓名","社会保障号码","社保卡号","批次","单位编号","单位名称","联系地址","联系电话","所属银行","银行卡号","所属城市","装箱位置","错误信息"};
		 ExcelUtils.addHeader(headers, sheet);
		 try {
			String filepath=UploadUtil.getFile(request, "temptxt");
			File txtfile=new File(request.getSession().getServletContext().getRealPath("/") +"temptxt/"+filepath);
			list=TxtToList(txtfile);
			int i=0;
			for (Map<String, String> map : list) {
				CardInfoVO vo=new CardInfoVO();
				vo.setIdcard(StringUtils.notNullToTrim(map.get("社会保障号")));
				vo.setCardid(StringUtils.notNullToTrim(map.get("社保卡号")));
				vo.setName(StringUtils.notNullToTrim(map.get("姓名")));
				String res="";
				SpeedOfCardVO re = new SpeedOfCardVO();
				String xml=CardServiceUtils.invoke("getAZ03", vo.getIdcard(), vo.getName(), null, vo.getIdcard().substring(0,4)+"00");
				System.out.println(xml);
				Element rootElement = DocumentHelper.parseText(xml).getRootElement();
				Element err = rootElement.element("ERR");
				if (err != null) {
					if (err.getText().equals("OK")) {
						re = (SpeedOfCardVO) PublicMethod.ElementToBean(rootElement, SpeedOfCardVO.class);
						re.setTime(rootElement.element("时间").getText());
					} else {
						res=err.getText();
					}
				} else {
					res="查无数据！";
				}
				vo.setCompanycode(StringUtils.notNullToTrim(map.get("单位编号")));
				vo.setCompanyname(StringUtils.notNullToTrim(map.get("单位名称")));
				vo.setPhone(StringUtils.notNullToTrim(map.get("联系电话"))+","+StringUtils.notNullToTrim(map.get("联系手机")));
				vo.setAddress(StringUtils.notNullToTrim(map.get("通讯地址")));
				vo.setAngent(StringUtils.notNullToTrim(map.get("姓名")));
				if(res.equals("")){
					vo.setBatchno(re.getBATCHNO());
					vo.setBank(re.getAAE008());
					vo.setBankacount(re.getAAE010B());
					vo.setReginalcode(re.getAAB301());
					vo.setOldcfwz(re.getZXWZ());
					vo.setFkwd(userid);
					res=checkCardInfoIsNull(vo);
				}
				if(!res.equals("")){
					 ExcelUtils.addData(i, 0, vo.getName(), sheet);
					 ExcelUtils.addData(i, 1, vo.getIdcard(), sheet);
					 ExcelUtils.addData(i, 2, vo.getCardid(), sheet);
					 ExcelUtils.addData(i, 3, vo.getBatchno(), sheet);
					 ExcelUtils.addData(i, 4, vo.getCompanycode(), sheet);
					 ExcelUtils.addData(i, 5, vo.getCompanyname(), sheet);
					 ExcelUtils.addData(i, 6, vo.getAddress(), sheet);
					 ExcelUtils.addData(i, 7, vo.getPhone(), sheet);
					 ExcelUtils.addData(i, 8, vo.getBank(), sheet);
					 ExcelUtils.addData(i, 9, vo.getBankacount(), sheet);
					 ExcelUtils.addData(i, 10, vo.getReginalcode(), sheet);
					 ExcelUtils.addData(i, 11,vo.getOldcfwz() , sheet);
					 ExcelUtils.addData(i, 12,res , sheet);
					 i++;
					continue;
				}
				vos.add(vo);
				logInfoService.addLog(0,vo.getCardid(),Integer.parseInt(vo.getFkwd()),useridToName(vo.getFkwd()),"初始导入");
			}
			if(vos.size()>0){
				cardInfoService.saveCardInfo(vos);
			}
			String path=Config.getInstance().get("exp.temp.path");
			File refile=new File(path);
			if (!refile.exists()) {
				refile.mkdirs();
			}
			FileOutputStream fileOut = new FileOutputStream(path+name);  
			cwb.write(fileOut);  
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Excel导入卡信息出错", e);
			return errorORA(e, "导入失败");
		}
		 if(list.size()==vos.size()){
			 return ok("导入成功"+vos.size()+";失败"+(list.size()-vos.size()));
		 }else{
			 return ok("导入成功"+vos.size()+";失败"+(list.size()-vos.size()),name);
		 }
	}
	
	private List<Map<String, String>> TxtToList(File txtfile) throws IOException {
		List<Map<String, String>> list=new ArrayList<>();
		if (txtfile.exists()) {
			InputStreamReader read = new InputStreamReader(new FileInputStream(txtfile));
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            int num=0;
            String[] keys=new String[50];
            while((lineTxt = bufferedReader.readLine()) != null){
               String[] line= lineTxt.split(",");
               if(num==1){
            	   keys=line;
               }else if(num>1){
            	   Map<String, String> map=new HashMap<>();
            	  for (int i = 0; i < line.length; i++) {
					map.put(keys[i], line[i]);
            	  }
            	  list.add(map);
               }
               num++;
            }
            read.close();
		}
		return list;
	}

	@RequestMapping(value = "/getResult", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public void getResult(@Context HttpServletRequest request, @Context HttpServletResponse response) {
		 String name=request.getParameter("name");
		 if(null==name||name.indexOf(".")==-1){
			 return;
		 }
		 String path=Config.getInstance().get("exp.temp.path")+name;
		 try {
			 InputStream inStream = new FileInputStream(path);// 文件的存放路径
            //tomcate 6:response.setContentType("application/vnd.ms-excel,charset=UTF-8");
            response.setContentType("multipart/form-data;charset=UTF-8");//tomcate 7
            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
            byte[] b = new byte[1024];
            int len;
            while ((len = inStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);
            inStream.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	 }

	/**
	 * 单张卡入库
	 * @param vo
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/cardStore", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result cardStore(@RequestBody CardStoreBean vo, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		if(null==vo.getCardid()||vo.getCardid().equals("")){
			return error("社保卡号为空");
		}
		if(null==vo.getIdcard()||vo.getIdcard().equals("")){
			return error("身份证号为空");
		}
		if(null==vo.getCbid()||vo.getCbid().equals("")){
			return error("盒id为空");
		}
		try {
			CardInfoVO civo=new CardInfoVO();
			civo.setIdcard(vo.getIdcard());
			civo.setCardid(vo.getCardid());
			List<CardInfoVO> list=cardInfoService.queryCards(new Page<CardInfoVO>(1, 1),civo).getData();
			if(list.size()>0){
				civo=list.get(0);
				if(civo.getStatus()>2){
					return error("卡不能再次入库！");
				}
				civo.setCbid(vo.getCbid());
				civo.setStatus(2);
				String re=oneCardStore(civo);
				logInfoService.addLog(civo.getId(),vo.getCardid(),Integer.parseInt(vo.getLoginuserid()),useridToName(vo.getLoginuserid()),"单张卡入库");
				if (!re.equals("")) {
					return error(re);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("单张卡入库出错", e);
			return errorORA(e,"入库失败");
		}
		return ok("入库成功！");
	}
	
	private String oneCardStore(CardInfoVO civo) {
		try {
			CardStoreVO csvo=new CardStoreVO();
			csvo.setCiid(civo.getId());
			csvo.setCbid(civo.getCbid());
			Integer num=cardInfoService.cardStore(csvo);
			if(num==-11){
				return "卡盒已满";
			}
			cardInfoService.updateUserStatus(civo);
			return "";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("单张卡入库出错", e);
			return e.getMessage();
		}
	}
	
	/**
	 * 批量入库，根据盒id和旧存放位置进行入库
	 * @param vo
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/cardStores", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result cardStores(@RequestBody CardStoreBean vo, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		try {
			if(null==vo.getBatch_no()||vo.getBatch_no().equals("")){
				return error("批次号为空");
			}
			if(null==vo.getCfwz()||vo.getCfwz().equals("")){
				return error("存放位置为空");
			}else if(vo.getCfwz().split("-").length<2){
				return error("存放位置应为【箱号-盒号】");
			}
			if(null==vo.getCbid()||vo.getCbid().equals("")){
				return error("盒id为空");
			}
			CardBatchVO cbvo=new CardBatchVO();
			cbvo.setType(4);
			cbvo.setBatchno(vo.getBatch_no());
			cbvo.setCasenum(vo.getCfwz().split("-")[0]);
			cbvo.setBoxnum(vo.getCfwz().split("-")[1]);
            String fkwd=loginUserService.getFkwd(vo.getLoginuserid());
            if(null==fkwd||fkwd.equals("")){
                return error("发卡机构获取编码为空");
            }
            cbvo.setFkwd(fkwd);
			List<CardBatchVO> list =cardInfoService.getBactchs(cbvo);
			if (list.size()<=0) {
				return error("查询卡数量出错");
			}
			Integer cardnum = Integer.parseInt(list.get(0).getCounts());
            logger.info("卡入库数量为："+cardnum);
			CardBoxVO bvo=cardInfoService.queryBoxMaxAndCardNum(vo.getCbid());
            int cardTotal = cardnum+bvo.getCardnum();
            logger.info("卡盒卡数为："+cardTotal);
			if (cardTotal>=bvo.getMaxnum()) {
				return error("超过卡盒最大容量");
			}
			cardInfoService.cardStores(vo);
			cardInfoService.updateStatusByCardStores(vo);
			logInfoService.addLog(0,"",Integer.parseInt(vo.getLoginuserid()),useridToName(vo.getLoginuserid()),"批量入库"+vo.getCfwz()+vo.getCfwz()+"-->"+vo.getCbid());
		} catch (Exception e) {
			e.printStackTrace();
			return errorORA(e,"入库失败");
		}
		return ok("入库成功！");
	}
	
	/**
	 * 查询卡存放位置及卡信息
	 * @param vo
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/queryCardStore", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result queryCardStore(@RequestBody CardInfoVO vo, @Context HttpServletRequest request, @Context HttpServletResponse response, @Context HttpSession session) {
		String pagesize = org.apache.commons.lang.StringUtils.defaultIfBlank(request.getParameter("pagesize"), "10");
	    String pageno = org.apache.commons.lang.StringUtils.defaultIfBlank(request.getParameter("pageno"), "1");
		Page<CardInfoVO> page=null;
		try {
			page=new Page<>(Integer.parseInt(pageno),Integer.parseInt(pagesize));

            LoginPassswordVO userVo = new LoginPassswordVO();
            userVo.setUserid(vo.getFkwd());
            List<LoginPassswordVO> list = loginUserService.checkRsTypeByUserId(userVo);
            //判断是否为人社账号
            if (list.size()>0){
                vo.setFkwd("");
            }else {
                if (null != vo.getFkwd() && !vo.getFkwd().equals("")) {
                    String re = useridToFkwd(vo);//通过用户ID查询网点编码
                    if (!re.equals("")) {
                        return error(re);
                    }
                }
            }

            //去除查询条件两端空格
            if (null!=vo.getIdcard() && !"".equals(vo.getIdcard())){
                vo.setIdcard(vo.getIdcard().trim());
            }
            if (null!=vo.getCardid() && !"".equals(vo.getCardid())){
                vo.setCardid(vo.getCardid().trim());
            }
            if (null!=vo.getName() && !"".equals(vo.getName())){
                vo.setName(vo.getName().trim());
            }
            if (null!=vo.getBatchno() && !"".equals(vo.getBatchno())){
                vo.setBatchno(vo.getBatchno().trim());
            }
            if (null!=vo.getCompanycode() && !"".equals(vo.getCompanycode())){
                vo.setCompanycode(vo.getCompanycode().trim());
            }
            if (null!=vo.getCompanyname() && !"".equals(vo.getCompanyname())){
                vo.setCompanyname(vo.getCompanyname().trim());
            }

			page=cardInfoService.queryCards(page,vo);
		} catch (Exception e) {
			e.printStackTrace();
			return error(e.getMessage());
		}
		if (page.getCount()<1) {
			return ok(page.getCount(),"查无数据！",page.getData());
		}else{
			return ok(page.getCount(),"查询成功！",page.getData());
		}
		//return ok(page.getCount(), "查询成功", page.getData());
	}
    /**
     * 个人领卡--查询卡存放位置及卡信息
     * @param vo
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/queryCardStoreByPerson", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result queryCardStoreByPerson(@RequestBody CardInfoVO vo, @Context HttpServletRequest request, @Context HttpServletResponse response, @Context HttpSession session) {
        String pagesize = org.apache.commons.lang.StringUtils.defaultIfBlank(request.getParameter("pagesize"), "10");
        String pageno = org.apache.commons.lang.StringUtils.defaultIfBlank(request.getParameter("pageno"), "1");
        Page<CardInfoVO> page=null;
        try {
            page=new Page<>(Integer.parseInt(pageno),Integer.parseInt(pagesize));
            if(null!=vo.getFkwd()&&!vo.getFkwd().equals("")){
                String re=useridToFkwd(vo);
                if(!re.equals("")){
                    return error(re);
                }
            }
            page=cardInfoService.queryCardsByCompay(page,vo);
        } catch (Exception e) {
            e.printStackTrace();
            return error(e.getMessage());
        }
        if (page.getCount()<1) {
            return ok(page.getCount(),"查无数据！",page.getData());
        }else{
            return ok(page.getCount(),"查询成功！",page.getData());
        }
        //return ok(page.getCount(), "查询成功", page.getData());
    }
    /**
     * 单位领卡查询公司编码-通过公司个人信息
     * @param vo
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/queryCardStoreByCompanyPerson", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result queryCardStoreByCompanyPerson(@RequestBody CardInfoVO vo, @Context HttpServletRequest request, @Context HttpServletResponse response, @Context HttpSession session) {
        String pagesize = org.apache.commons.lang.StringUtils.defaultIfBlank(request.getParameter("pagesize"), "10");
        String pageno = org.apache.commons.lang.StringUtils.defaultIfBlank(request.getParameter("pageno"), "1");
        Page<CardInfoVO> page=null;
        try {
            page=new Page<>(Integer.parseInt(pageno),Integer.parseInt(pagesize));
			if(null!=vo.getFkwd() && !vo.getFkwd().equals("")){
				String fkwd=loginUserService.getFkwd(vo.getFkwd());
				if(null!=fkwd && !fkwd.equals("")){
					vo.setFkwd(fkwd);
					String bank=loginUserService.getBankCode(fkwd);
					vo.setFkwd(fkwd);
					vo.setBank(bank);
				}
			}
            page=cardInfoService.queryCardsByCompay(page,vo);
        } catch (Exception e) {
            e.printStackTrace();
            return error(e.getMessage());
        }
        if (page.getCount()<1) {
            return ok(page.getCount(),"查无数据！",page.getData());
        }else{
            return ok(page.getCount(),"查询成功！",page.getData());
        }
        //return ok(page.getCount(), "查询成功", page.getData());
    }
    /**
     * 单位领卡查询公司所有卡信息-通过公司编码
     * @param vo
     * @param request
     * @param response
     * @return
     */
	@RequestMapping(value = "/queryCardStoreByCompany", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result queryCardStoreByCompany(@RequestBody CardInfoVO vo, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		String pagesize = org.apache.commons.lang.StringUtils.defaultIfBlank(request.getParameter("pagesize"), "10");
	    String pageno = org.apache.commons.lang.StringUtils.defaultIfBlank(request.getParameter("pageno"), "1");
		Page<CardInfoVO> page=null;
		try {
			page=new Page<>(Integer.parseInt(pageno),Integer.parseInt(pagesize));
			if(null!=vo.getFkwd()&&!vo.getFkwd().equals("")){
				ORGVO orgvo=loginUserService.getRkwd(Integer.valueOf(vo.getFkwd()));
				if(null!=orgvo && !orgvo.getOrgcode().equals("")){
					vo.setFkwd(orgvo.getOrgcode());
					DistinctAndBankVO distinctAndBankVO=loginUserService.getFkwdById(Integer.valueOf(orgvo.getParentId()));
					if(null!= distinctAndBankVO && !distinctAndBankVO.getCode().equals("")){
						vo.setBank(distinctAndBankVO.getCode());
					}
				}
			}
			if(null!=vo.getIdcard()&&!vo.getIdcard().equals("")){
				PrintLogVO plvo=new PrintLogVO();
				plvo.setIdcard(vo.getIdcard());
				plvo.setName(vo.getName());
				Integer receivenum=cardInfoService.queryMaxReceivenum(plvo);
				vo.setIdcard("");
				vo.setName("");
				vo.setReceivenum(receivenum);
			}
			page=cardInfoService.queryCardsByCompay(page,vo);
		} catch (Exception e) {
			e.printStackTrace();
			return error(e.getMessage());
		}
		if (page.getCount()<1) {
			return ok(page.getCount(),"查无数据！",page.getData());
		}else{
			return ok(page.getCount(),"查询成功！",page.getData());
		}
		//return ok(page.getCount(), "查询成功", page.getData());
	}
	
	/**
	 * 查询批次和旧存放位置
	 * @param vo
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/queryCardBacth", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result queryCardBacth(@RequestBody CardBatchVO vo, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		List<CardBatchVO> list =new ArrayList<>();
		try {
			if (null==vo.getType()||vo.getType().equals("")) {
				return error("查询类型为空");
			}
			if(vo.getType()==2||vo.getType()==3){
				if (null==vo.getBatchno()||vo.getBatchno().equals("")) {
					return error("批次为空");
				}
				if(vo.getType()==3){
					if (null==vo.getCasenum()||vo.getCasenum().equals("")) {
						return error("箱号为空");
					}
				}
			}
			if(null==vo.getFkwd()||vo.getFkwd().equals("")){
				return error("发卡机构为空");
			}
			String fkwd=loginUserService.getFkwd(vo.getFkwd());
			if(null==fkwd||fkwd.equals("")){
				return error("发卡机构获取编码为空");
			}
			vo.setFkwd(fkwd);
			list=cardInfoService.getBactchs(vo);
		} catch (Exception e) {
			e.printStackTrace();
			return errorORA(e, "查询失败");
		}
		return ok(list.size(), "查询成功", list);
	}
	
	/**
	 * 卡转移
	 * @param vo
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/cardChanges", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result cardChanges(@RequestBody CardChangeBean vo, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		try {
			if (null==vo.getTocbid()||vo.getTocbid().equals("")) {
				return error("转移目标卡盒id为空");
			}
			if (null==vo.getFromcbid()||vo.getFromcbid().equals("")) {
				return error("转移起始卡盒id为空");
			}
			CardBoxVO tbvo=cardInfoService.queryBoxMaxAndCardNum(vo.getTocbid());
			CardBoxVO fbvo=cardInfoService.queryBoxMaxAndCardNum(vo.getFromcbid());
			if((tbvo.getCardnum()+fbvo.getCardnum())>=tbvo.getMaxnum()){
				return error("超过卡盒最大容量");
			}
			cardInfoService.changeCardStore(vo);
			logInfoService.addLog(0,"",Integer.parseInt(vo.getLoginuserid()),useridToName(vo.getLoginuserid()),"卡转移"+vo.getFromcbid()+"-->"+vo.getTocbid());
		} catch (Exception e) {
			e.printStackTrace();
			return errorORA(e, "转移失败");
		}
		return ok("转移成功！");
	}
	@RequestMapping(value = "/cardChange", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result cardChange(@RequestBody CardRetentionBean vo, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		try {
			if(null==vo.getCbid()||vo.getCbid().equals("")){
				return error("盒id为空");
			}
			if(null==vo.getIds()){
				return error("卡id为空");
			}
			Integer[] ids=vo.getIds();
			if(ids.length<=0){
				return error("卡id为空");
			}
			StringBuffer result=new StringBuffer();
			for (Integer id : ids) {
				CardStoreVO csvo=cardInfoService.queryCardStore(id);
				if (null==csvo) {
					CardInfoVO civo=new CardInfoVO();
					civo.setId(id);
					List<CardInfoVO> list=cardInfoService.queryCards(new Page<CardInfoVO>(1, 1),civo).getData();
					if(list.size()>0){
						civo=list.get(0);
						if (civo.getStatus()==3||civo.getStatus()==6||civo.getStatus()==7||civo.getStatus()==8) {
							return error(civo.getIdcard()+"卡已发放，不能转移入柜");
						}
						if (civo.getStatus()==1){
							civo.setStatus(2);
						}
						civo.setCbid(vo.getCbid());
						String re=oneCardStore(civo);
						logInfoService.addLog(civo.getId(),civo.getCardid(),Integer.parseInt(vo.getLoginuserid()),useridToName(vo.getLoginuserid()),"卡转移/入柜");
						if (!re.equals("")) {
							result.append(civo.getIdcard()+re);
						}
					}
				}else{
					csvo.setCbid(vo.getCbid());
					cardInfoService.changeStore(csvo);
					cardInfoService.updateCardStore(csvo);
					logInfoService.addLog(id,"",Integer.parseInt(vo.getLoginuserid()),useridToName(vo.getLoginuserid()),"卡转移/入柜");
				}
			}
			if(result.length()>0){
				return error(result.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return errorORA(e, "转移失败");
		}
		return ok("转移成功！");
	}
	
	@RequestMapping(value = "/updateCardInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result updateCardInfo(@RequestBody CardInfoVO vo, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		try {
			if (null==vo.getId()||vo.getId().equals("")) {
				return error("卡id为空");
			}
			cardInfoService.updateCardInfo(vo);
			logInfoService.addLog(vo.getId(),"",Integer.parseInt(vo.getLoginuserid()),useridToName(vo.getLoginuserid()),"卡信息修改");
		} catch (Exception e) {
			e.printStackTrace();
			return errorORA(e, "修改失败");
		}
		return ok("修改成功！");
	}
	@RequestMapping(value = "/queryCompanys", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result queryCompanys(@RequestBody CardInfoVO vo, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		List<CardInfoVO> companys=new ArrayList<>();
		try {
			if (null==vo.getCompanycode()||vo.getCompanycode().equals("")) {
				return ok("");
			}
			companys=cardInfoService.queryCompanys(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok(companys.size(),"",companys);
	}
	@RequestMapping(value = "/expInfos", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result ExpInfos(@RequestBody CardInfoVO vo, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		String name="";
		try {
			String re=useridToFkwd(vo);
			if(!re.equals("")){
				return error(re);
			}
			HSSFWorkbook wb=new HSSFWorkbook();
			HSSFSheet sheet=wb.createSheet("数据");
			 Page<CardInfoVO> page=cardInfoService.queryCards(new Page<CardInfoVO>(1, 99999), vo);
			 List<CardInfoVO> cardInfoVOs=page.getData();
			 if(cardInfoVOs.size()>65535){
				 return error("数据量过大,请添加条件导出！");
			 }
			 String[] headers=new String[]{"姓名","社会保障号码","社保卡号","银行卡号","批次","单位编号","单位名称","卡位置","状态","领卡时间","领卡人身份证号","领卡人","领卡人联系方式","领卡类型"};
			 ExcelUtils.addHeader(headers, sheet);
			 for (int i = 0; i < cardInfoVOs.size(); i++) {
				 ExcelUtils.addData(i, 0, cardInfoVOs.get(i).getName(), sheet);
				 ExcelUtils.addData(i, 1, cardInfoVOs.get(i).getIdcard(), sheet);
				 ExcelUtils.addData(i, 2, cardInfoVOs.get(i).getCardid(), sheet);
				 ExcelUtils.addData(i, 3, cardInfoVOs.get(i).getBankacount(), sheet);
				 ExcelUtils.addData(i, 4, cardInfoVOs.get(i).getBatchno(), sheet);
				 ExcelUtils.addData(i, 5, cardInfoVOs.get(i).getCompanycode(), sheet);
				 ExcelUtils.addData(i, 6, cardInfoVOs.get(i).getCompanyname(), sheet);
				 ExcelUtils.addData(i, 7, cardInfoVOs.get(i).getCfwz(), sheet);
				 ExcelUtils.addData(i, 8, String.valueOf(cardInfoVOs.get(i).getStatus()), sheet);
			 }
			 name="ExpCard_"+new SimpleDateFormat("yyyyMMddHHmmSS").format(new Date())+".xls";
			 String path=Config.getInstance().get("exp.temp.path");
			 File file=new File(path);
			 if (!file.exists()) {
				file.mkdirs();
			}
			 FileOutputStream fileOut = new FileOutputStream(path+name);  
			 wb.write(fileOut);  
			 fileOut.close();  
		} catch (Exception e) {
			e.printStackTrace();
			return errorORA(e, "查询出错！");
		}
		return ok("成功",name);
	}
	
	/*勾选导出代码备份
	 try {
			HSSFWorkbook wb=new HSSFWorkbook();
			HSSFSheet sheet=wb.createSheet("数据");
			String[] headers=new String[]{"姓名","社会保障号码","社保卡号","批次","单位编号","单位名称","卡位置","状态","领卡时间","领卡人身份证号","领卡人","领卡人联系方式","领卡类型"};
			ExcelUtils.addHeader(headers, sheet);
			if(null==vo.getIds()||vo.getIds().length<=0){
				ExcelUtils.addData(0, 0, "卡id为空", sheet);return;
			}
			Integer[] ids=vo.getIds();
			if(ids.length>65535){
				ExcelUtils.addData(0, 0, "数据量过大,请添加条件导出！", sheet);
				return;
			}
			int i=0;
			for (Integer id : ids) {
				CardInfoVO cvo=new CardInfoVO();cvo.setId(id);
				 Page<CardInfoVO> page=cardInfoService.queryCards(new Page<CardInfoVO>(1, 1), cvo);
				 if (page.getData().size()>0) {
					 CardInfoVO cardInfoVOs=page.getData().get(0);
					 ExcelUtils.addData(i, 0, cardInfoVOs.getName(), sheet);
					 ExcelUtils.addData(i, 1, cardInfoVOs.getIdcard(), sheet);
					 ExcelUtils.addData(i, 2, cardInfoVOs.getCardid(), sheet);
					 ExcelUtils.addData(i, 3, cardInfoVOs.getBatchno(), sheet);
					 ExcelUtils.addData(i, 4, cardInfoVOs.getCompanycode(), sheet);
					 ExcelUtils.addData(i, 5, cardInfoVOs.getCompanyname(), sheet);
					 ExcelUtils.addData(i, 6, cardInfoVOs.getCfwz(), sheet);
					 ExcelUtils.addData(i, 7, String.valueOf(cardInfoVOs.getStatus()), sheet);
					i++;
				}
			 }
			 String name="Exp_"+new SimpleDateFormat("yyyyMMddHHmmSS").format(new Date())+".xls";
			 String path=Config.getInstance().get("exp.temp.path");
			 File file=new File(path);
			 if (!file.exists()) {
				file.mkdirs();
			}
			 FileOutputStream fileOut = new FileOutputStream(path+name);  
			 wb.write(fileOut);  
			 fileOut.close();  
			 InputStream inStream = new FileInputStream(path+name);// 文件的存放路径
          //tomcate 6:response.setContentType("application/vnd.ms-excel,charset=UTF-8");
          response.setContentType("multipart/form-data;charset=UTF-8");//tomcate 7
          response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
          byte[] b = new byte[1024];
          int len;
          while ((len = inStream.read(b)) > 0)
              response.getOutputStream().write(b, 0, len);
          inStream.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
	 */

    //获取需要提醒经办人待找卡的数据
    @RequestMapping(value = "/getFindCardInfoByTime", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getFindCardInfoByTime(@RequestBody FindCardInfoVO request) {
        int count = 0;
        try {
            String fkwd=loginUserService.getFkwd(request.getUserId());
            if(null==fkwd||fkwd.equals("")){
                return error("发卡机构获取编码为空");
            }
            request.setFkwd(fkwd);
            count = cardInfoService.getFindCardInfoByTime(request);
            logger.info("查询成功!"+count);
            return ok("查询成功！", count);
        } catch (Exception e) {
            logger.error("获取信息发生了异常：" + e);
            return error("获取信息失败！");
        }
    }

}
