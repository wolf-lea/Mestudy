package com.tecsun.sisp.fakamanagement.modules.controller.receive;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tecsun.sisp.fakamanagement.modules.entity.result.receive.*;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.tecsun.sisp.fakamanagement.common.Config;
import com.tecsun.sisp.fakamanagement.common.GlobalVariable;
import com.tecsun.sisp.fakamanagement.common.Page;
import com.tecsun.sisp.fakamanagement.common.Result;
import com.tecsun.sisp.fakamanagement.common.upload.UploadUtil;
import com.tecsun.sisp.fakamanagement.common.utils.StringUtils;
import com.tecsun.sisp.fakamanagement.modules.controller.BaseController;
import com.tecsun.sisp.fakamanagement.modules.entity.param.receive.QueryCardReceiveBean;
import com.tecsun.sisp.fakamanagement.modules.entity.param.receive.QuerySpeedOfCardBean;
import com.tecsun.sisp.fakamanagement.modules.entity.param.receive.ReceivePhotoBean;
import com.tecsun.sisp.fakamanagement.modules.entity.param.receive.SaveCardReceiveBean;
import com.tecsun.sisp.fakamanagement.modules.entity.result.card.CardInfoVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.card.CardStoreVO;
import com.tecsun.sisp.fakamanagement.modules.service.impl.card.CardInfoServiceImpl;
import com.tecsun.sisp.fakamanagement.modules.service.impl.log.LogInfoServiceImpl;
import com.tecsun.sisp.fakamanagement.modules.service.impl.loginuser.LoginUserServiceImpl;
import com.tecsun.sisp.fakamanagement.modules.service.impl.receive.CardReceiveServiceImpl;
import com.tecsun.sisp.fakamanagement.modules.util.Base64Utils;
import com.tecsun.sisp.fakamanagement.modules.util.ExcelUtils;
import com.tecsun.sisp.fakamanagement.modules.util.PublicMethod;
import com.tecsun.sisp.fakamanagement.outerface.cardservice.CardServiceUtils;

/**
 * 
* @ClassName: CardReceiveController 
* @Description: TODO(领卡记录接口) 
* @author huangtao
* @date 2017年7月13日 下午2:41:45 
*
 */
@Controller
@RequestMapping(value = "/faka/cardRecevice")
public class CardReceiveController extends BaseController {
	
public final static Logger logger = Logger.getLogger(CardReceiveController.class);
	
	@Autowired
    private CardReceiveServiceImpl cardReceiveService;
	
	@Autowired
    private CardInfoServiceImpl cardInfoService;
	@Autowired
    private LogInfoServiceImpl logInfoService;
	@Autowired
	private LoginUserServiceImpl loginUserService;
	/**
	 * 领卡记录接口
	 * @param vo
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/addCardReceive", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result addCardReceive(@RequestBody SaveCardReceiveBean vo, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		String re="";
		String reo="";
        List<Object> cardReceiveIdList = new ArrayList<Object>();//发卡记录返回主键ID list
        CardReceiveReturnVO returnVO = new CardReceiveReturnVO();
		try {
			Integer agentid=0;
			if(null==vo.getType()||vo.getType().equals("")){
				return error("领卡类型为空");
			}
			if(null==vo.getIds()){
				return error("卡id为空");
			}
			Integer[] ids=vo.getIds();
			if(ids.length<=0){
				return error("卡id为空");
			}
			int anum=0,bnum=0,cnum=0,dnum=0;
			for (Integer id : ids) {
				CardReceiveVO crvo=new CardReceiveVO();
				crvo.setCiid(id);
				crvo.setType(vo.getType());
				if(vo.getType()==2||vo.getType()==3||vo.getType()==4){
					CardAgentVO cavo=new CardAgentVO();
					if(null!=vo.getIdcard_photo_down()&&!vo.getIdcard_photo_down().equals("")){
						cavo.setIdcard_photo_down(vo.getIdcard_photo_down()+".jpg");
					}
					if(null!=vo.getIdcard_photo_up()&&!vo.getIdcard_photo_up().equals("")){
						cavo.setIdcard_photo_up(vo.getIdcard_photo_up()+".jpg");
					}
					if(null!=vo.getSign_photo()&&!vo.getSign_photo().equals("")){
						cavo.setSign_photo(vo.getSign_photo()+".jpg");
					}
					if(null!=vo.getPhoto()&&!vo.getPhoto().equals("")){
						cavo.setPhoto(vo.getPhoto()+".jpg");
					}
					cavo.setIdcard(vo.getIdcard());
					cavo.setName(vo.getName());
					cavo.setPhone(vo.getPhone());
					//CardAgentVO avo=cardReceiveService.queryAgent(cavo);
					//if(null==avo){
						if(null==cavo.getIdcard()||cavo.getIdcard().equals("")){
							return error("代领人身份证号码为空");
						}
						if(null==cavo.getName()||cavo.getName().equals("")){
							return error("代领人姓名为空");
						}
						if(null==cavo.getPhone()||cavo.getPhone().equals("")){
							return error("代领人联系电话为空");
						}
						cardReceiveService.insertCardAgent(cavo);
						agentid=cavo.getAgentid();
					//}else{
					//	agentid=avo.getAgentid();
					//}
					crvo.setAgentid(agentid);
				}else{
					if(null!=vo.getIdcard_photo_down()&&!vo.getIdcard_photo_down().equals("")){
						crvo.setIdcard_photo_down(vo.getIdcard_photo_down()+".jpg");
					}
					if(null!=vo.getIdcard_photo_up()&&!vo.getIdcard_photo_up().equals("")){
						crvo.setIdcard_photo_up(vo.getIdcard_photo_up()+".jpg");
					}
					if(null!=vo.getSign_photo()&&!vo.getSign_photo().equals("")){
						crvo.setSign_photo(vo.getSign_photo()+".jpg");
					}
					if(null!=vo.getPhoto()&&!vo.getPhoto().equals("")){
						crvo.setPhoto(vo.getPhoto()+".jpg");
					}
					crvo.setAgentid(agentid);
				}
				QueryCardReceiveBean bean=new QueryCardReceiveBean();
				bean.setCiid(id);
				Page<CardReceiveLogVO> p=cardReceiveService.queryCardReceive(new Page<CardReceiveLogVO>(1, 1), bean);
				if(p.getData().size()>0){
					//if(ids.length>1){
						anum++;
					/*}else{
						reo="卡已领取！"+id;
					}*/
					continue;
				}
				CardStoreVO csvo=cardInfoService.queryCardStore(crvo.getCiid());
				if(null!=csvo){
					if(null!=csvo.getCsid()&&!csvo.getCsid().equals("")){
						//cardInfoService.deleteCardStore(crvo.getCiid());//删除卡位置
                        cardInfoService.updateThisCardInfoXtzxwz(crvo.getCiid());//去除当前发卡系统位置
                        cardInfoService.updateThisCardStore(crvo.getCiid());//保留卡位置信息，修改当前发卡卡序号为0，柜子id为0
						cardInfoService.updateCardStore(csvo);//修改对应盒子剩余的卡位置-1
					}
				}
				CardInfoVO cardInfoVO=new CardInfoVO();
				cardInfoVO.setId(id);
				cardInfoVO.setStatus(6);
                //通过用户id获取网点编码
                String fkwd = loginUserService.getFkwd(vo.getLoginuserid());
                if(null==fkwd||fkwd.equals("")){
                    logger.info("发卡机构获取编码为空");
                    return error("发卡机构获取编码为空");
                }
                cardInfoVO.setFkwd(fkwd);
				cardInfoService.updateUserStatus(cardInfoVO);//更新卡基础信息

			    Integer cardReceiveId = cardReceiveService.insertCardReceive(crvo);//发卡记录返回主键ID
                cardReceiveIdList.add(cardReceiveId);

				logInfoService.addLog(cardInfoVO.getId(),"",Integer.parseInt(vo.getLoginuserid()),useridToName(vo.getLoginuserid()),"卡领取");
				bnum++;
				cardInfoVO.setStatus(null);
				List<CardInfoVO> list=cardInfoService.queryCards(new Page<CardInfoVO>(1, 1),cardInfoVO).getData();
				if(list.size()>0){
					cardInfoVO=list.get(0);
				}else{continue;}
				if(null==cardInfoVO.getReginalcode()||cardInfoVO.getReginalcode().equals("")){
					//cardInfoVO.setReginalcode(loginUserService.getReginalCode(cardInfoVO.getFkwd()));
					cardInfoVO.setReginalcode(cardInfoVO.getIdcard().substring(0,4)+"00");
				}
				//String sre=activationCard(cardInfoVO);//调省卡管接口，领卡激活

                String sre="00";

				if(sre.equals("00")||sre.equals("01")){
					CardInfoVO cardInfo=new CardInfoVO();
					cardInfo.setId(id);
					cardInfo.setStatus(7);
					cardInfo.setActivetime(new Date());
                    cardInfo.setFkwd(fkwd);
					cardInfoService.updateUserStatus(cardInfo);
					cnum++;
				}else{
					re=re+sre;
					dnum++;
				}
			}
			if(bnum>0){
				reo=reo+bnum+"张卡领取成功;";
                returnVO.setStatus("1");//领卡成功
			}
			if(anum>0){
				reo=reo+anum+"张卡已领取，领取失败;";
                returnVO.setStatus("2");//领卡失败
			}
			if(cnum>0){
				reo=reo+"其中"+cnum+"张卡激活成功;";
			}
			if(dnum>0){
				reo=reo+dnum+"张卡激活失败，请从其他途径激活;";
			}

            returnVO.setReo(reo);
            returnVO.setCardReceiveIdList(cardReceiveIdList);
		} catch (Exception e) {
			e.printStackTrace();
			return errorORA(e, "领取失败！");
		}
		logger.debug(re);
		return ok("操作成功！", returnVO);
	}

	public String useridToName(String id) {
		return loginUserService.getName(id);
	}

    //调省卡管接口，激活卡片
	@RequestMapping(value = "/activation", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result activation(@RequestBody SaveCardReceiveBean vo, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		String re="";
		try {
			if(null==vo.getIds()){
				return error("卡id为空");
			}
			Integer[] ids=vo.getIds();
			if(ids.length<=0){
				return error("卡id为空");
			}
			for (Integer id : ids) {
				CardInfoVO cardInfoVO=new CardInfoVO();
				cardInfoVO.setId(id);
				cardInfoVO.setStatus(102);
				List<CardInfoVO> list=cardInfoService.queryCards(new Page<CardInfoVO>(1, 1),cardInfoVO).getData();
				if(list.size()>0){
					cardInfoVO=list.get(0);
				}else{re=re+"查无此人";continue;}
				if(null==cardInfoVO.getReginalcode()||cardInfoVO.getReginalcode().equals("")){
					cardInfoVO.setReginalcode(cardInfoVO.getIdcard().substring(0,4)+"00");
				}
				String are=activationCard(cardInfoVO);//调省卡管接口，激活卡片
				if(are.equals("00")||are.equals("01")){
					CardInfoVO cardInfo=new CardInfoVO();
					cardInfo.setId(id);
					cardInfo.setStatus(7);
					cardInfo.setActivetime(new Date());
					cardInfoService.updateUserStatus(cardInfo);
					logInfoService.addLog(cardInfo.getId(),"",Integer.parseInt(vo.getLoginuserid()),useridToName(vo.getLoginuserid()),"卡激活");
				}else{
					re=re+are;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return errorORA(e, "激活失败！");
		}
		if(re.equals("")){
			return ok("激活成功！");
		}else{
			return ok("有卡激活失败，请从其他途径激活！");
		}
	}
	
	private String activationCard(CardInfoVO cardInfoVO) throws Exception {
		String param="<操作*>领卡启用</操作*><用户名*>"+Config.getInstance().get("card_user")+"</用户名*><密码*>"+Config.getInstance().get("card_pwd")+"</密码*>"
				+ "<所属城市*>"+cardInfoVO.getReginalcode()+"</所属城市*><社保卡号*>"+cardInfoVO.getCardid()+"</社保卡号*>"
				+ "<社会保障号码*>"+cardInfoVO.getIdcard()+"</社会保障号码*><姓名*>"+cardInfoVO.getName()+"</姓名*>";
		return CardServiceUtils.invoke(param);
		//return "测试";
	}

    //插入领卡记录
	@RequestMapping(value = "/receiveCard", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result receiveCard(@RequestBody SaveCardReceiveBean vo, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		try {
			if(null==vo.getIds()){
				return error("卡id为空");
			}
			Integer[] ids=vo.getIds();
			if(ids.length<=0){
				return error("卡id为空");
			}
			for (Integer id : ids) {
				CardReceiveVO crvo=new CardReceiveVO();
				crvo.setCiid(id);
				crvo.setType(vo.getType());
				CardStoreVO csvo=cardInfoService.queryCardStore(crvo.getCiid());
				Integer resultnum=cardInfoService.deleteCardStore(crvo.getCiid());
				resultnum=cardInfoService.updateCardStore(csvo);
				CardInfoVO cardInfoVO=new CardInfoVO();
				cardInfoVO.setStatus(3);
				cardInfoVO.setId(id);
				resultnum=cardInfoService.updateUserStatus(cardInfoVO);
				logInfoService.addLog(id,"",Integer.parseInt(vo.getLoginuserid()),useridToName(vo.getLoginuserid()),"取卡操作");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return errorORA(e, "取卡记录失败！");
		}
		return ok("取卡记录成功！");
	}
	
	/**
	 * 查询领卡记录
	 * @param vo
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/queryCardReceiveLog", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result queryCardReceiveLog(@RequestBody QueryCardReceiveBean vo, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		String pagesize = org.apache.commons.lang.StringUtils.defaultIfBlank(request.getParameter("pagesize"), "10");
	    String pageno = org.apache.commons.lang.StringUtils.defaultIfBlank(request.getParameter("pageno"), "1");
		Page<CardReceiveLogVO> page=null;

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
        if (null!=vo.getCompany_code() && !"".equals(vo.getCompany_code())){
            vo.setCompany_code(vo.getCompany_code().trim());
        }

		try {
			String re=useridToFkwd(vo);
			if(!re.equals("")){
				return error(re);
			}
			page=new Page<>(Integer.parseInt(pageno),Integer.parseInt(pagesize));
			page=cardReceiveService.queryCardReceive(page,vo);
		} catch (Exception e) {
			e.printStackTrace();
			return error(e.getMessage());
		}
		if (page.getCount()<1) {
			return ok(page.getCount(),"查无数据！",page.getData());
		}else{
			return ok(page.getCount(),"查询成功！",page.getData());
		}
		//return ok(page.getCount(),"查询成功",page.getData());
	}

    //通过用户ID查询网点编码
	public String useridToFkwd(QueryCardReceiveBean vo) {
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
	
	
	@RequestMapping(value = "/saveReceivePhotos", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result saveReceivePhotos(@RequestBody ReceivePhotoBean vo, @Context HttpServletRequest request, @Context HttpServletResponse response){
		try {
			String pp=Config.getInstance().get("photo.upload.path");
			File ppFile=new File(pp);
			if (!ppFile.exists()) {
				ppFile.mkdirs();
			}
			String path=Config.getInstance().get("photo.upload.path")+vo.getPhotoname()+".jpg";
			boolean flag=Base64Utils.GenerateImage(vo.getBase64code(), path);
			if(flag){
				return ok("上传成功");
			}else{
				return error("上传失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return error("上传失败！"+e.getMessage());
		}

	}
	
	/**
	 * 保存上传文件
	 * @param file
	 * @return
	 */
	 public boolean saveFile(MultipartFile file) {  
	        // 判断文件是否为空  
	        if (!file.isEmpty()) {  
	            try {  
	                // 文件保存路径  
	                String filePath = request.getSession().getServletContext().getRealPath("/") + Config.getInstance().get("photo.upload.path")
	                        + file.getOriginalFilename();  
	                // 转存文件  
	                file.transferTo(new File(filePath));  
	                return true;  
	            } catch (Exception e) {  
	                e.printStackTrace();  
	            }  
	        }  
	        return false;  
	    }  
	 
	 @RequestMapping(value = "/addReceiveNum", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result addReceiveNum(@RequestBody SaveCardReceiveBean vo, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		 Integer receivenum=0;
		 try {
			 if(null==vo.getIdcard()||vo.getIdcard().equals("")){
				 return error("身份证为空");
			 }
			 if(null==vo.getName()||vo.getName().equals("")){
				 return error("姓名为空");
			 }
			 if(null==vo.getIds()){
				return error("卡id为空");
			}
			Integer[] ids=vo.getIds();
			if(ids.length<=0){
				return error("卡id为空");
			}
			receivenum=cardInfoService.getReceivenum(); 
			if (receivenum<0) {
				return error("获取领卡号失败!");
			}
			List<CardInfoVO> list=new ArrayList<>();
			for (Integer id : ids) {
				CardInfoVO cvo=new CardInfoVO();
				cvo.setId(id);
				cvo.setReceivenum(receivenum);
				list.add(cvo);
				logInfoService.addLog(id,"",Integer.parseInt(vo.getLoginuserid()),useridToName(vo.getLoginuserid()),"添加领卡号");
			}
			cardInfoService.updateReceiveNum(list);
			PrintLogVO pvo=new PrintLogVO();
			pvo.setIdcard(vo.getIdcard());
			pvo.setName(vo.getName());
			pvo.setReceivenum(receivenum);
			cardReceiveService.insertPrintLog(pvo);
			/*temp=cardInfoService.queryCards(new Page<CardInfoVO>(1, 1),list.get(0)).getData();
			if (temp.size()<=0) {
				return error("标记领卡号失败!");
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			return errorORA(e, "标记领卡号失败！");
		}
		return ok("标记领卡号成功！",receivenum);
	 }
	 
	@RequestMapping(value = "/getPhoto", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public void getPhoto(@Context HttpServletRequest request, @Context HttpServletResponse response) {
		 String name=request.getParameter("name");
		 if(null==name||name.indexOf(".")==-1){
			 return;
		 }
		 String path=Config.getInstance().get("photo.upload.path")+name;
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
	* Excel导入卡信息
	* @param file excel文件
	* @param request
	* @return
	*/
	@RequestMapping({"saveCardReceives"})
	@ResponseBody
		public Result saveCardReceives(@RequestParam("file") MultipartFile file,@RequestParam("loginuserid")String loginuserid, HttpServletRequest request, @Context HttpServletResponse response){
			String name="SaveReceiveERR_"+new SimpleDateFormat("yyyyMMddHHmmSS").format(new Date())+".xls";
			 HSSFWorkbook cwb=new HSSFWorkbook();
			 HSSFSheet sheet=cwb.createSheet("错误信息");
			 String[] headers=new String[]{"姓名","社会保障号码","社保卡号","批次","单位编号","单位名称","卡位置","状态","领卡时间","领卡人身份证号","领卡人","领卡人联系方式","领卡类型","错误信息"};
			 ExcelUtils.addHeader(headers, sheet);
			List<Map<String, String>> list=new ArrayList<>();
			int i=0;
			 try {
				String filepath=UploadUtil.getFile(request, "tempexcel");
				Workbook wb=ExcelUtils.getExcel(request.getSession().getServletContext().getRealPath("/") +"tempexcel/"+filepath);
				list=ExcelUtils.EecelToList(wb);
				for (Map<String, String> map : list) {
					CardInfoVO vo=new CardInfoVO();
					vo.setIdcard(StringUtils.notNullToTrim(map.get("社会保障号码")));
					vo.setCardid(StringUtils.notNullToTrim(map.get("社保卡号")));
					Page<CardInfoVO> page=cardInfoService.queryCards(new Page<CardInfoVO>(1, 1), vo);
					if(page.getCount()>0){
						vo.setName(StringUtils.notNullToTrim(map.get("姓名")));
						int agentid=0;
						vo=page.getData().get(0);
						QueryCardReceiveBean bean=new QueryCardReceiveBean();
						bean.setCiid(vo.getId());
						Page<CardReceiveLogVO> p=cardReceiveService.queryCardReceive(new Page<CardReceiveLogVO>(1, 1), bean);
						if(p.getData().size()>0){
							addErrInfos(map,i,sheet,"卡已领取");i++;
							continue;
						}
						CardReceiveVO crvo=new CardReceiveVO();
						crvo.setCiid(vo.getId());
						String type=StringUtils.notNullToTrim(map.get("领卡类型"));
						if(null==type||type.equals("")){
							addErrInfos(map,i,sheet,"领卡类型为空");i++;
							continue;
						}
						try{
						crvo.setType(Integer.valueOf(type));
						}catch(Exception e){
							addErrInfos(map,i,sheet,"领卡类型不为数字");i++;
							continue;
						}
						SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Date date=null;
						try {
							date=format.parse(StringUtils.notNullToTrim(map.get("领卡时间")));
						} catch (Exception e) {
							addErrInfos(map,i,sheet,"领卡时间格式有误 应为yyyy-MM-dd HH:mm:ss");i++;
							continue;
						}
						if (crvo.getType()==1) {
							crvo.setAgentid(0);
						}else{
							CardAgentVO cavo=new CardAgentVO();
							cavo.setIdcard(StringUtils.notNullToTrim(map.get("领卡人身份证号")));
							cavo.setName(StringUtils.notNullToTrim(map.get("领卡人")));
							cavo.setPhone(StringUtils.notNullToTrim(map.get("领卡人联系方式")));
							CardAgentVO avo=cardReceiveService.queryAgent(cavo);
							if(null==avo){
								if(null==cavo.getIdcard()||cavo.getIdcard().equals("")){
									addErrInfos(map,i,sheet,"代领人身份证号码为空");i++;
									continue;
								}
								if(null==cavo.getName()||cavo.getName().equals("")){
									addErrInfos(map,i,sheet,"代领人姓名为空");i++;
									continue;
								}
								if(null==cavo.getPhone()||cavo.getPhone().equals("")){
									addErrInfos(map,i,sheet,"代领人联系电话为空");i++;
									continue;
								}
								cardReceiveService.insertCardAgent(cavo);
								agentid=cavo.getAgentid();
							}else{
								agentid=avo.getAgentid();
							}
						}
						crvo.setAgentid(agentid);
						CardStoreVO csvo=cardInfoService.queryCardStore(crvo.getCiid());
						if(null!=csvo){
							if(null!=csvo.getCsid()&&!csvo.getCsid().equals("")){
								cardInfoService.deleteCardStore(crvo.getCiid());
								cardInfoService.updateCardStore(csvo);
							}
						}
						CardInfoVO cardInfoVO=new CardInfoVO();
						cardInfoVO.setId(vo.getId());
						cardInfoVO.setStatus(10);
						cardInfoService.updateUserStatus(cardInfoVO);
						crvo.setReceive_time(date);
						cardReceiveService.insertCardReceive(crvo);
						logInfoService.addLog(cardInfoVO.getId(),"",Integer.parseInt(loginuserid),useridToName(loginuserid),"领卡信息补录");
						/*if(null==vo.getReginalcode()||vo.getReginalcode().equals("")){
							vo.setReginalcode(vo.getIdcard().substring(0,4)+"00");
						}
						String re=activationCard(vo);
						if(re.equals("00")||re.equals("01")){
							CardInfoVO cardInfo=new CardInfoVO();
							cardInfo.setId(vo.getId());
							cardInfo.setStatus(7);
							cardInfo.setActivetime(new Date());
							cardInfoService.updateUserStatus(cardInfo);
						}else{
							addErrInfos(map,i,sheet,"领卡记录成功，激活失败"+re+"；请用其他途径激活");i++;
						}*/
					}else{
						addErrInfos(map,i,sheet,"查询不到数据");i++;
						continue;
					}
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
				logger.error("Excel导入信息出错", e);
				return error("导入失败"+e.getMessage(),name);
			}
			 if(i==0){
				 return ok("导入成功"+(list.size()-i)+"失败"+i);
			 }
			 return ok("导入成功"+(list.size()-i)+"失败"+i,name);
		}
		
		private void addErrInfos(Map<String, String> map, int i, HSSFSheet sheet, String string) {
			 ExcelUtils.addData(i, 0, map.get("姓名"), sheet);
			 ExcelUtils.addData(i, 1, map.get("社会保障号码"), sheet);
			 ExcelUtils.addData(i, 2, map.get("社保卡号"), sheet);
			 ExcelUtils.addData(i, 3, map.get("批次"), sheet);
			 ExcelUtils.addData(i, 4, map.get("单位编号"), sheet);
			 ExcelUtils.addData(i, 5, map.get("单位名称"), sheet);
			 ExcelUtils.addData(i, 6, map.get("卡位置"), sheet);
			 ExcelUtils.addData(i, 7, map.get("状态"), sheet);
			 ExcelUtils.addData(i, 8, map.get("领卡时间"), sheet);
			 ExcelUtils.addData(i, 9, map.get("领卡人身份证号"), sheet);
			 ExcelUtils.addData(i, 10, map.get("领卡人"), sheet);
			 ExcelUtils.addData(i, 11, map.get("领卡人联系方式"), sheet);
			 ExcelUtils.addData(i, 12, map.get("领卡类型"), sheet);
			 ExcelUtils.addData(i, 13, string, sheet);
		}

    /*
    * 制卡进度查询
    * */
	@RequestMapping(value = "/querySpeedOfCard", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result querySpeedOfCard(@RequestBody QuerySpeedOfCardBean vo, @Context HttpServletRequest request,
			@Context HttpServletResponse response) {
		SpeedOfCardVO re = new SpeedOfCardVO();
		try {
            //去除查询条件两端空格
            if (null!=vo.getName() && !"".equals(vo.getName())){
                vo.setName(vo.getName().trim());
            }
            if (null!=vo.getIdcard() && !"".equals(vo.getIdcard())){
                vo.setIdcard(vo.getIdcard().trim());
            }

			if (null==vo.getName()||vo.getName().equals("")) {
				return error("姓名为空");
			}
			if (null==vo.getIdcard()||vo.getIdcard().equals("")) {
				return error("身份证号码为空");
			}
			if (null==vo.getReginal()||vo.getReginal().equals("")) {
				vo.setReginal(vo.getIdcard().substring(0,4)+"00");
			}
			System.out.println("接口调用开始"+vo.getIdcard());
			String xml=CardServiceUtils.invoke("getAZ03", vo.getIdcard(), vo.getName(), null, vo.getReginal());
			System.out.println(xml);
			//String xml="<ALL><ERR>OK</ERR><时间>20140308095644</时间><AAZ500>B00000019</AAZ500><AAC002>111111198101011110</AAC002><AAC003>姓名1</AAC003><CARDTYPE>1</CARDTYPE><TRANSACTTYPE>3</TRANSACTTYPE><BHYY></BHYY><BATCHNO>1</BATCHNO><AAB301>220001</AAB301><ORGANID>22000100</ORGANID><KS>1</KS><AAE008>95566</AAE008><AAE008B>中行东山支行</AAE008B><AAE010>金融帐户1</AAE010><AAE010A></AAE010A><AAE010B>个人帐户1</AAE010B><APPLYTIME>20140308083523</APPLYTIME><BANKTIME0>20140308083753</BANKTIME0><BANKFINISHTIME0>20140308083902</BANKFINISHTIME0><INSURETIME>20140308083913</INSURETIME><INSUREFINISHTIME>20140308083929</INSUREFINISHTIME><PROVINCETIME>20140308083942</PROVINCETIME><CITYTIME>20140308083953</CITYTIME><CITYSENDTIME>20140308083953</CITYSENDTIME><CITYSENDADDR>xx路xx街xx网点</CITYSENDADDR><GETTIME>20140308084004</GETTIME><ZZTIME></ZZTIME><REMARKS></REMARKS><ZXWZ>1-2-3-1</ZXWZ><VALIDTAG>0</VALIDTAG></ALL>";
			Element rootElement = DocumentHelper.parseText(xml).getRootElement();
			Element err = rootElement.element("ERR");
			if (err != null) {
				if (err.getText().equals("OK")) {
					re = (SpeedOfCardVO) PublicMethod.ElementToBean(rootElement, SpeedOfCardVO.class);
					re.setTime(rootElement.element("时间").getText());
				} else {
					return error(err.getText());
				}
			} else {
				return error("查无数据！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return error(GlobalVariable.STRING_CARD_WEBSERVICE_ERROR);
		}
		return ok("查询成功", re);
	}

}
