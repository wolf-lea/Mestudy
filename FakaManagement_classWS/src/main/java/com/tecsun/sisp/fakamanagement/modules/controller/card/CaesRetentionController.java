package com.tecsun.sisp.fakamanagement.modules.controller.card;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tecsun.sisp.fakamanagement.common.Config;
import com.tecsun.sisp.fakamanagement.common.Page;
import com.tecsun.sisp.fakamanagement.common.Result;
import com.tecsun.sisp.fakamanagement.common.utils.StringUtils;
import com.tecsun.sisp.fakamanagement.modules.controller.BaseController;
import com.tecsun.sisp.fakamanagement.modules.entity.param.card.CardCabinetBean;
import com.tecsun.sisp.fakamanagement.modules.entity.param.card.CardRetentionBean;
import com.tecsun.sisp.fakamanagement.modules.entity.param.receive.SaveCardReceiveBean;
import com.tecsun.sisp.fakamanagement.modules.entity.param.statistics.FKWDBean;
import com.tecsun.sisp.fakamanagement.modules.entity.result.card.CardInfoVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.card.CardRetentionVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.card.CardStoreVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.statistics.ORGVO;
import com.tecsun.sisp.fakamanagement.modules.service.impl.card.CardInfoServiceImpl;
import com.tecsun.sisp.fakamanagement.modules.service.impl.log.LogInfoServiceImpl;
import com.tecsun.sisp.fakamanagement.modules.service.impl.loginuser.LoginUserServiceImpl;
import com.tecsun.sisp.fakamanagement.modules.util.ExcelUtils;
import com.tecsun.sisp.fakamanagement.modules.util.PublicMethod;


@Controller
@RequestMapping(value = "/faka/cardRetention")
public class CaesRetentionController extends BaseController {
	
	public final static Logger logger = Logger.getLogger(CaesRetentionController.class);
	
	@Autowired
    private CardInfoServiceImpl cardInfoService;
	@Autowired
	private LoginUserServiceImpl loginUserService;
	@Autowired
    private LogInfoServiceImpl logInfoService;
	/**
	 * 标记滞留卡
	 * @param vo
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/addRetentionCard", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result addRetentionCard(@RequestBody CardRetentionBean vo, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		try {
			if(null==vo.getIds()){
				return error("卡id为空");
			}
			Integer[] ids=vo.getIds();
			if(ids.length<=0){
				return error("卡id为空");
			}
			for (Integer id : ids) {
				CardInfoVO cvo=new CardInfoVO();
				cvo.setId(id);
				if (null==cvo.getId()||cvo.getId()==0) {
					return error("卡id为空");
				}
				List<CardInfoVO> list=cardInfoService.queryCards(new Page<CardInfoVO>(1, 1),cvo).getData();
				cvo.setRetentionuser(vo.getName());
				if(list.size()>0){
					CardInfoVO civo=list.get(0);
					if (civo.getStatus()==6||civo.getStatus()==7) {
						return error(civo.getIdcard()+"卡已发放，不能登记滞留卡");
					}
					if (civo.getStatus()==5) {
						return error(civo.getIdcard()+"卡是问题卡，不能登记滞留卡");
					}
					if(civo.getRetentionnum()>2){
						cvo.setStatus(4);
						cardInfoService.updateUserStatus(cvo);
						cardInfoService.updateRetentiontime(cvo);
						logInfoService.addLog(id,"",Integer.parseInt(vo.getLoginuserid()),useridToName(vo.getLoginuserid()),"滞留卡登记");
					}else{
						cardInfoService.updateRetentionnum(cvo);
						logInfoService.addLog(id,"",Integer.parseInt(vo.getLoginuserid()),useridToName(vo.getLoginuserid()),"滞留卡标记");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return errorORA(e, "标记失败");
		}	
		return ok("标记成功！");
	}
	
	/**
	 * 记录问题卡原因
	 * @param vo
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/addProblemCard", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result addProblemCard(@RequestBody CardRetentionVO vo, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		try {
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

			if (null==vo.getCiid()||vo.getCiid()==0) {
				CardInfoVO cvo=new CardInfoVO();
				cvo.setIdcard(vo.getIdcard());
				cvo.setName(vo.getName());
				cvo.setCardid(vo.getCardid());
				Page<CardInfoVO> page=new Page<>(1, 1);
				page=cardInfoService.queryCards(page, cvo);
				if(page.getData().size()>0){
					vo.setCiid(page.getData().get(0).getId());
				}else{
					return error("人员不存在");
				}
			}
			Page<CardRetentionVO> page=new Page<>(1,1);
			page=cardInfoService.queryProblemCard(page,vo);
			if(page.getData().size()>0){
				return error("问题卡已记录！");
			}
			if (null==vo.getRemark()||vo.getRemark().equals("")) {
				return error("问题原因为空");
			}
			if (null==vo.getRetentionuser()||vo.getRetentionuser().equals("")) {
				return error("登记人为空");
			}
			cardInfoService.insertProblemCard(vo);
			CardInfoVO cvo=new CardInfoVO();
			cvo.setStatus(5);
			cvo.setId(vo.getCiid());
			cardInfoService.updateUserStatus(cvo);
			logInfoService.addLog(vo.getCiid(),"",Integer.parseInt(vo.getLoginuserid()),useridToName(vo.getLoginuserid()),"问题卡标记");
		} catch (Exception e) {
			e.printStackTrace();
			return errorORA(e, "标记失败");
		}	
		return ok("标记成功！");
	}
	
	public String useridToFkwd(CardRetentionVO vo) {
		if(null==vo.getUserid()||vo.getUserid().equals("")){
			return "发卡机构为空";
		}
		String fkwd=loginUserService.getFkwd(vo.getUserid());
		if(null==fkwd||fkwd.equals("")){
			return "发卡机构获取编码为空";
		}
		vo.setUserid(fkwd);
		return "";
	}
	
	@RequestMapping(value = "/queryProblemCard", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result queryProblemCard(@RequestBody CardRetentionVO vo, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		String pagesize = org.apache.commons.lang.StringUtils.defaultIfBlank(request.getParameter("pagesize"), "10");
	    String pageno = org.apache.commons.lang.StringUtils.defaultIfBlank(request.getParameter("pageno"), "1");
		Page<CardRetentionVO> page=null;

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
		try {
			String re=useridToFkwd(vo);
			if(!re.equals("")){
				return error(re);
			}
			page=new Page<>(Integer.parseInt(pageno),Integer.parseInt(pagesize));
			page=cardInfoService.queryProblemCard(page,vo);
		} catch (Exception e) {
			e.printStackTrace();
			return error(e.getMessage());
		}	
		if (page.getCount()<1) {
			return ok(page.getCount(),"查无数据！",page.getData());
		}else{
			return ok(page.getCount(),"查询成功！",page.getData());
		}
	}
	
	/**
	 * 问题卡处理
	 * @param vo
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/handleProblemCard", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result handleProblemCard(@RequestBody CardRetentionVO vo, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		try {
			if (null==vo.getHandle()||vo.getHandle().equals("")) {
				return error("处理方式为空");
			}
			if (null==vo.getId()||vo.getId()==0) {
				return error("id为空");
			}
			cardInfoService.handleProblemCard(vo);
			logInfoService.addLog(vo.getId(),"",Integer.parseInt(vo.getLoginuserid()),useridToName(vo.getLoginuserid()),"问题卡处理");
		} catch (Exception e) {
			e.printStackTrace();
			return errorORA(e, "处理失败");
		}	
		return ok("处理成功！");
	}
	@RequestMapping(value = "/getFKWD", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getFKWD(@RequestBody FKWDBean vo, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		List<ORGVO> vos=new ArrayList<>();
		try {
			if(null==vo.getParentid()||vo.getParentid().equals("")){
				vo.setParentid("0");
			}
			vos=loginUserService.queryAllFkwd(vo.getParentid());
		} catch (Exception e) {
			e.printStackTrace();
			return error(e.getMessage());
		}	
		return ok(vos.size(),"成功！",vos);
	}
	@RequestMapping(value = "/retentionCardChange", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result retentionCardChange(@RequestBody FKWDBean vo, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		try {
			if(null==vo.getOrgcode()||vo.getOrgcode().equals("")){
				return error("机构编码为空");
			}
			if(null==vo.getCbid()||vo.getCbid().equals("")){
				return error("盒id为空");
			}
			CardInfoVO civo=new CardInfoVO();
			civo.setCbid(vo.getCbid());
			Page<CardInfoVO> page=cardInfoService.queryCards(new Page<CardInfoVO>(1,9999), civo);
			if(page.getCount()>0){
				for (CardInfoVO cvo : page.getData()) {
					if(cvo.getStatus()!=4){
						continue;
					}
					cvo.setFkwd(vo.getOrgcode());
					cardInfoService.changeFKWD(cvo);
					CardStoreVO csvo=cardInfoService.queryCardStore(cvo.getId());
					if(null!=csvo){
						cardInfoService.deleteCardStore(cvo.getId());
						if(null!=csvo.getCsid()&&!csvo.getCsid().equals("")){
							cardInfoService.updateCardStore(csvo);
						}
					}
					logInfoService.addLog(0,"",Integer.parseInt(vo.getLoginuserid()),useridToName(vo.getLoginuserid()),"滞留卡转移"+vo.getCbid()+"--->"+vo.getOrgcode());
				}
			}else{
				return error("查询卡信息错误");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return errorORA(e, "处理失败");
		}	
		return ok("处理成功！");
	}
	
	@RequestMapping(value = "/expExcel")
	@ResponseBody
	public void expExcel(@Context HttpServletRequest request, @Context HttpServletResponse response) {
		 try {
			 Integer cbid = Integer.parseInt(request.getParameter("cbid"));
			 String loginuserid = request.getParameter("loginuserid");
			 CardInfoVO vo=new CardInfoVO();vo.setCbid(cbid);vo.setStatus(4);
			 Page<CardInfoVO> page=cardInfoService.queryCards(new Page<CardInfoVO>(1, 999), vo);
			 List<CardInfoVO> cardInfoVOs=page.getData();
			 HSSFWorkbook wb=new HSSFWorkbook();
			 HSSFSheet sheet=wb.createSheet("数据");
			 String[] headers=new String[]{"姓名","社会保障号码","社保卡号","批次","单位编号","单位名称","联系地址","联系电话","所属银行","银行卡号","所属城市","装箱位置"};
			 ExcelUtils.addHeader(headers, sheet);
			 for (int i = 0; i < cardInfoVOs.size(); i++) {
				 String zxwz=cardInfoVOs.get(i).getBatchno()+"-"+cardInfoVOs.get(i).getOldcfwz();
				 ExcelUtils.addData(i, 0, cardInfoVOs.get(i).getName(), sheet);
				 ExcelUtils.addData(i, 1, cardInfoVOs.get(i).getIdcard(), sheet);
				 ExcelUtils.addData(i, 2, cardInfoVOs.get(i).getCardid(), sheet);
				 ExcelUtils.addData(i, 3, cardInfoVOs.get(i).getBatchno(), sheet);
				 ExcelUtils.addData(i, 4, cardInfoVOs.get(i).getCompanycode(), sheet);
				 ExcelUtils.addData(i, 5, cardInfoVOs.get(i).getCompanyname(), sheet);
				 ExcelUtils.addData(i, 6, cardInfoVOs.get(i).getAddress(), sheet);
				 ExcelUtils.addData(i, 7, cardInfoVOs.get(i).getPhone(), sheet);
				 ExcelUtils.addData(i, 8, cardInfoVOs.get(i).getBank(), sheet);
				 ExcelUtils.addData(i, 9, cardInfoVOs.get(i).getBankacount(), sheet);
				 ExcelUtils.addData(i, 10, cardInfoVOs.get(i).getReginalcode(), sheet);
				 ExcelUtils.addData(i, 11,zxwz , sheet);
				 CardStoreVO csvo=cardInfoService.queryCardStore(cardInfoVOs.get(i).getId());
				if(null!=csvo){
					CardInfoVO civo=new CardInfoVO();
					civo.setId(csvo.getCiid());civo.setStatus(9);
					cardInfoService.updateUserStatus(civo);
					cardInfoService.deleteCardStore(csvo.getCiid());
					if(null!=csvo.getCsid()&&!csvo.getCsid().equals("")){
						cardInfoService.updateCardStore(csvo);
					}
					logInfoService.addLog(cardInfoVOs.get(i).getId(),"",Integer.parseInt(loginuserid),useridToName(loginuserid),"滞留卡导出");
				}
			 }
			 String name="ExpRetention_"+new SimpleDateFormat("yyyyMMddHHmmSS").format(new Date())+".xls";
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
	 }
	public String useridToName(String id) {
		return loginUserService.getName(id);
	}
}
