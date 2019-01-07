package com.tecsun.sisp.fakamanagement.modules.controller.temporaryCard;

import com.tecsun.sisp.fakamanagement.common.Constants;
import com.tecsun.sisp.fakamanagement.common.Page;
import com.tecsun.sisp.fakamanagement.common.Result;
import com.tecsun.sisp.fakamanagement.modules.controller.BaseController;
import com.tecsun.sisp.fakamanagement.modules.entity.param.temporaryCard.BankNetBean;
import com.tecsun.sisp.fakamanagement.modules.entity.result.common.DistinctAndBankVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.statistics.ORGVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.temporaryCard.BankNetVO;
import com.tecsun.sisp.fakamanagement.modules.service.impl.loginuser.LoginUserServiceImpl;
import com.tecsun.sisp.fakamanagement.modules.service.impl.temporaryCard.BankNetServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * @Description:临时卡市银行管理
 * @author: liang
 * @date 2018/3/21 10:49
 **/
@Controller
@RequestMapping("/faka/bankNetManage")
public class BankNetManageController extends BaseController {

    public static final Logger logger= Logger.getLogger(BankNetManageController.class);

    @Autowired
    private BankNetServiceImpl bankNetService;
    @Autowired
    private LoginUserServiceImpl loginUserService;

    /**
     * 市银行临时卡接收列表
     * @param bean userid boxNO  CardNOStart  CardNOEnd
     * @param request
     * @return
     */
    @RequestMapping(value="/getBankNetBoxList",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public Result getBankNetBoxList(@RequestBody BankNetBean bean,HttpServletRequest request){
        try{
            int pagesize=request.getParameter("pagesize")!=null?Integer.parseInt(request.getParameter("pagesize")):12;
            int pageno=request.getParameter("pageno")!=null?Integer.parseInt(request.getParameter("pageno")):1;
            Page<BankNetVO> page=new Page<>(pageno,pagesize);
            ORGVO orgVO=loginUserService.getRkwd(Integer.valueOf(bean.getUserid()));
            BankNetVO queryVO=new BankNetVO();
            queryVO.setBoxNO(bean.getBoxNO());
            queryVO.setCardNOStart(bean.getCardNOStart());
            queryVO.setCardNOEnd(bean.getCardNOEnd());
            queryVO.setBank(orgVO.getOrgid());
            page=bankNetService.getBankNetBoxList(page,queryVO);
            if(page!=null && page.getCount()>0){
                for (int i = 0; i < page.getData().size(); i++) {
                    BankNetVO vo=page.getData().get(i);
                    vo.setCardNOPerBox(vo.getCardNOStart()+"-"+vo.getCardNOEnd());
                    String status=vo.getStatus();
                    if("00".equals(status)){
                        vo.setStatusValue("未下发");
                    }else if("01".equals(status)){
                        vo.setStatusValue("已接收");
                    }else{
                        vo.setStatusValue("已下发");
                    }

                    if(vo.getRkwd()!=null && !"".equals(vo.getRkwd())){
                        DistinctAndBankVO dvo=loginUserService.getFkwdById(vo.getRkwd());
                        if(dvo!=null){
                            vo.setRkwdName(dvo.getName());
                        }
                    }
                }
                logger.info("查询临时卡市银行接收列表成功！");
                return ok(page.getCount(),"查询临时卡市银行接收列表成功！",page);
            }else{
                logger.error("查询临时卡市银行接收列表为空！");
                return error("查询临时卡市银行接收列表为空！");
            }
        }catch (Exception e){
            logger.error("查询临时卡市银行接收列表异常！",e);
            return error("查询临时卡市银行接收列表异常！");
        }

    }

    /**
     * 市银行临时卡接收卡详细列表
     * @param bean  boxNO  cardNOStart  cardNOEnd
     * @param request
     * @return
     */
    @RequestMapping(value="/getBankNetCardList",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public Result getBankNetCardList(@RequestBody BankNetBean bean,HttpServletRequest request){
        try{
            int pagesize=request.getParameter("pagesize")!=null?Integer.parseInt(request.getParameter("pagesize")):12;
            int pageno=request.getParameter("pageno")!=null?Integer.parseInt(request.getParameter("pageno")):1;
            Page<BankNetVO> page=new Page<>(pageno,pagesize);
            BankNetVO queryVO=new BankNetVO();
            queryVO.setBoxNO(bean.getBoxNO());
            queryVO.setCardNOStart(bean.getCardNOStart());
            queryVO.setCardNOEnd(bean.getCardNOEnd());
            queryVO.setPage(page);
            List<BankNetVO> list=bankNetService.getBankNetCardList(queryVO);
            page.setData(list);
            if(page!=null && page.getCount()>0){
                for (int i = 0; i < page.getData().size(); i++) {
                    BankNetVO vo=page.getData().get(i);
                    vo.setStatusValue(Constants.TEMPCARD_STATUS.get(vo.getStatus()));
                    vo.setBoxNO(bean.getBoxNO());
                }
                logger.info("查询临时卡市银行卡详细列表成功！");
                return ok(page.getCount(),"查询临时卡市银行卡详细列表成功！",page);
            }else{
                logger.error("查询临时卡市银行卡详细列表为空！");
                return error("查询临时卡市银行卡详细列表为空！");
            }
        }catch (Exception e){
            logger.error("查询临时卡市银行卡详细列表异常！",e);
            return error("查询临时卡市银行卡详细列表异常！");
        }
    }

    /**
     * 市银行临时卡卡下发
     * @param bean 临时卡盒号 要下发到的网点id
     * @return
     */
    @RequestMapping(value="/issueCard",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public Result issueCard(@RequestBody BankNetBean bean){
        try{
            String[] boxNOs=bean.getBoxNOs();
            int successNum=0;
            int failNum=0;
            int duplicateNum=0;
            for (int i = 0; i < boxNOs.length; i++) {
                BankNetVO queryVO=new BankNetVO();
                queryVO.setBoxNO(boxNOs[i]);
                Page<BankNetVO> page=new Page<BankNetVO>(1,12);
                //获取临时卡盒信息
                page=bankNetService.getBankNetBoxList(page,queryVO);
                String status=page.getData().get(0).getStatus();
                if(Constants.TEMPCARD_BOX_02.equals(status)){
                    duplicateNum++;
                    continue;
                }
                Integer newRkwd=Integer.valueOf(bean.getRkwd());
                Result result=bankNetService.issueCardProcess(page.getData(),newRkwd);
                if("200".equals(result.getStatusCode())){
                    successNum++;
                }else{
                    failNum++;
                }
            }
            return ok(successNum+"盒下发成功，"+failNum+"盒下发失败，"+duplicateNum+"盒不可重复下发");
        }catch (Exception e){
            logger.error("查询临时卡市银行卡详细列表异常！",e);
            return error("查询临时卡市银行卡详细列表异常！");
        }
    }

    /**
     * 获取吉林银行所有下级银行网点
     * @return
     */
    @RequestMapping(value="/getBankNet",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public Result getBankNet(@RequestBody BankNetBean bean){
        try{
            //有用户id获取网点id
            ORGVO orgvo=loginUserService.getRkwd(Integer.valueOf(bean.getUserid()));
            //通过网店id获取下级银行网点
            List<ORGVO> dList=loginUserService.queryAllFkwd(orgvo.getOrgid());
            if(dList!=null && dList.size()>0){
                logger.info("查询银行网点列表成功！");
                return ok(dList.size(),"查询银行网点列表成功！",dList);
            }else{
                logger.error("查询银行网点列表为空！");
                return error("查询银行网点列表为空！");
            }
        }catch (Exception e){
            logger.error("查询银行网点列表异常！",e);
            return error("查询银行网点列表异常！");
        }
    }

}
