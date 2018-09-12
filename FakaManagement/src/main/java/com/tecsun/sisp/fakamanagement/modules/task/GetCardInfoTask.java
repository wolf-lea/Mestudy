package com.tecsun.sisp.fakamanagement.modules.task;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tecsun.sisp.fakamanagement.common.Page;
import com.tecsun.sisp.fakamanagement.modules.entity.result.card.CardInfoVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.receive.SpeedOfCardVO;
import com.tecsun.sisp.fakamanagement.modules.service.impl.card.CardInfoServiceImpl;
import com.tecsun.sisp.fakamanagement.modules.service.impl.log.LogInfoServiceImpl;
import com.tecsun.sisp.fakamanagement.modules.util.PublicMethod;
import com.tecsun.sisp.fakamanagement.outerface.cardservice.CardServiceUtils;

@Component("getCardInfoTask") 
public class GetCardInfoTask {
	
	@Autowired
    private CardInfoServiceImpl cardInfoService;
	@Autowired
    private LogInfoServiceImpl logInfoService;
	
 	@Scheduled(cron = "0 0 21 * * ?")
    public void getCardInfos() {
 		System.out.println("==================开始定时任务=========================");
 		try {
 			System.out.println("开始比对银行数据=========================");
 			Integer a=cardInfoService.insertBankDatas("s");
 			System.out.println("新增数据："+a);
 			//Integer num=1;
 			List<CardInfoVO> cvos=new ArrayList<>();
 			List<CardInfoVO> vos=new ArrayList<>();
 			System.out.println("开始匹配卡管数据=========================");
 			Integer counts=cardInfoService.getBankInterfaceNum();
 			int size=2000;
 			int c=counts%size==0?counts/size:(counts/size)+1;
 			System.out.println(counts+"---->"+c);
 			for(Integer num=1;num<=c;num++){
 				cvos=new ArrayList<>();
 				vos=new ArrayList<>();
 				vos=cardInfoService.getBankInterface(new Page<CardInfoVO>(num, size),new CardInfoVO()).getData();
 				//System.out.println("查询到数据"+vos.size()+"条");
 				for (CardInfoVO vo : vos) {
 					if(null==vo.getFkwd()||vo.getFkwd().equals("")){
 						continue;
 					}
 					vo.setFkwd("012512");
 					//System.out.println("开始查询数据是否存在"+vo.getIdcard()+"----"+vo.getName());
 					CardInfoVO q1 =new CardInfoVO();q1.setIdcard(vo.getIdcard());
 					List<CardInfoVO> list=cardInfoService.queryCards(new Page<CardInfoVO>(1, 1),q1).getData();
 					if (list.size()>0) {
 						//System.out.println("数据存在");
 						continue;
 					}
 					//System.out.println("开始获取制卡进度数据"+vo.getIdcard()+"----"+vo.getName());
 					SpeedOfCardVO re = new SpeedOfCardVO();
 					String xml="";
 					try {
 						xml=CardServiceUtils.invoke("getAZ03", vo.getIdcard(), vo.getName(), null, vo.getIdcard().substring(0,4)+"00");
					} catch (Exception e) {
						//System.out.println(vo.getIdcard()+"---->"+vo.getName());
						//System.out.println(e.getMessage());
						continue;
					}
 					//System.out.println(xml);
 					Element rootElement = DocumentHelper.parseText(xml).getRootElement();
 					Element err = rootElement.element("ERR");
 					if (err != null) {
 						if (err.getText().equals("OK")) {
 							re = (SpeedOfCardVO) PublicMethod.ElementToBean(rootElement, SpeedOfCardVO.class);
 							re.setTime(rootElement.element("时间").getText());
 						} else {
 							//logInfoService.addLog(0,vo.getCardid(),0,"","制卡进度查询"+vo.getIdcard()+err.getText());
 							continue;
 						}
 					} else {
 						//logInfoService.addLog(0,vo.getCardid(),0,"","制卡进度查询"+vo.getIdcard()+"查无数据");
 						continue;
 					}
 					CardInfoVO q2 =new CardInfoVO();q2.setCardid(re.getAAZ500());
 					//System.out.println("开始查询数据是否存在"+q2.getCardid());
 					List<CardInfoVO> list1=cardInfoService.queryCards(new Page<CardInfoVO>(1, 1),q2).getData();
 					if (list1.size()>0) {
 						//System.out.println("数据存在");
 						continue;
 					}
 					if (null!=re.getAAZ500()&&!re.getAAZ500().equals("")) {
 						vo.setCardid(re.getAAZ500());
 					}else {
						continue;
					}
 					boolean f=false;
 					for (CardInfoVO cvo : cvos) {
 						if (cvo.getCardid().equals(vo.getCardid())) {
							f=true;
						}
 					}
 					if (f) {
						continue;
					}
 					if (null!=re.getGETTIME()&&!re.getGETTIME().equals("")) {
 						try {
							cardInfoService.updateBankStatus(vo.getCardid());
						} catch (Exception e) {
							System.out.println("以发卡状态修改失败"+vo.getIdcard()+"--->"+vo.getCardid());
							//System.out.println("以发卡状态修改失败"+e.getMessage());
						}
 						continue;
					}
 					if(re.getBATCHNO()!=null&&!re.getBATCHNO().equals("")){
 						vo.setBatchno(re.getBATCHNO());
 					}else{
 						//logInfoService.addLog(0,vo.getCardid(),0,"","初始导入无批次号"+vo.getIdcard());
 						continue;
 					}
 					if(re.getZXWZ()!=null&&!re.getZXWZ().equals("")){
 						String [] s=re.getZXWZ().split("-");
 						String cfwz=s[s.length-3]+"-"+s[s.length-2]+"-"+s[s.length-1];
 						vo.setOldcfwz(cfwz);
 					}else{
 						//logInfoService.addLog(0,vo.getCardid(),0,"","初始导入无装箱位置"+vo.getIdcard());
 						continue;
 					}
 					SpeedOfCardVO cre = new SpeedOfCardVO();
 					//System.out.println("开始获取卡基础信息数据"+vo.getIdcard()+"----"+vo.getName());
 					String cxml="";
 					try {
 						cxml=CardServiceUtils.invoke("getAC01", vo.getIdcard(), vo.getName(), null, vo.getIdcard().substring(0,4)+"00");
					} catch (Exception e) {
						//System.out.println(vo.getIdcard()+"--->"+vo.getName());
						//System.out.println(e.getMessage());
						continue;
					}
 					//System.out.println(cxml);
 					Element crootElement = DocumentHelper.parseText(cxml).getRootElement();
 					Element cerr = crootElement.element("ERR");
 					if (cerr != null) {
 						if (cerr.getText().equals("OK")) {
 							cre = (SpeedOfCardVO) PublicMethod.ElementToBean(crootElement, SpeedOfCardVO.class);
 							cre.setTime(crootElement.element("时间").getText());
 						} else {
 							//logInfoService.addLog(0,vo.getCardid(),0,"","卡基础信息查询"+vo.getIdcard()+cerr.getText());
 							continue;
 						}
 					} else {
 						//logInfoService.addLog(0,vo.getCardid(),0,"","卡基础信息查询"+vo.getIdcard()+"查无数据");
 						continue;
 					}
 					
 					vo.setBank(re.getAAE008());
 					vo.setReginalcode(re.getAAB301());
 					vo.setCompanycode(cre.getAAB001());
 					vo.setCompanyname(cre.getAAB004());
 					if(cre.getMOBILE()!=null&&!cre.getMOBILE().equals("")){
 						vo.setPhone(cre.getMOBILE());
 					}else if(cre.getAAE005()!=null&&!cre.getAAE005().equals("")){
 						vo.setPhone(cre.getAAE005());
 					}else {
 						//logInfoService.addLog(0,vo.getCardid(),0,"","初始导入无联系电话"+vo.getIdcard());
 						continue;
 					}
 					cvos.add(vo);
 				}
 				//num++;
 				System.out.println("可导入数据"+cvos.size()+"条");
 				if(cvos.size()>0){
 					try{
 					cardInfoService.saveCardInfo(cvos);
 					} catch (Exception e) {
 						for (CardInfoVO cardInfoVO : cvos) {
 							System.out.println(cardInfoVO.getIdcard()+"+"+cardInfoVO.getName()+"===>"+cardInfoVO.getCardid());
						}
						System.out.println(e.getMessage());
						continue;
					}
 				}
 				System.out.println(num+"次导入数据成功");
 			} //while (vos.size()>0);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }  
}
