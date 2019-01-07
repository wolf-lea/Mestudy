package com.tecsun.sisp.fakamanagement.modules.controller.temporaryCard;

import com.tecsun.sisp.fakamanagement.common.Constants;
import com.tecsun.sisp.fakamanagement.common.Page;
import com.tecsun.sisp.fakamanagement.common.Result;
import com.tecsun.sisp.fakamanagement.modules.entity.param.temporaryCard.TempCardBoxBean;
import com.tecsun.sisp.fakamanagement.modules.entity.param.temporaryCard.TempCardDetailBean;
import com.tecsun.sisp.fakamanagement.modules.entity.result.common.DistinctAndBankVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.statistics.ORGVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.temporaryCard.TempCardBoxVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.temporaryCard.TempCardDetailVO;
import com.tecsun.sisp.fakamanagement.modules.service.impl.loginuser.LoginUserServiceImpl;
import com.tecsun.sisp.fakamanagement.modules.service.impl.temporaryCard.HumanSocietyImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/faka/humanSociety")
public class HumanSocietyController {

    public final static Logger logger = Logger.getLogger(HumanSocietyController.class);

    @Autowired
    private HumanSocietyImpl humanSociety;
    @Autowired
    private LoginUserServiceImpl loginUserService;


    /**
     * 查询临时卡盒列表
     * @return
     */
    @RequestMapping(value = "/searchTempCardBox",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public Result searchTempCardBox(@RequestBody TempCardBoxBean bean,HttpServletRequest request){
        String message="操作成功";
        int total=0;
        String statusCode=Constants.RESULT_MESSAGE_SUCCESS;
        List<TempCardBoxVO> tempCardBoxVOList=new ArrayList<>();
        int pagesize=request.getParameter("pagesize")!=null?Integer.parseInt(request.getParameter("pagesize")):10;
        int pageno=request.getParameter("pageno")!=null?Integer.parseInt(request.getParameter("pageno")):1;
        Page<TempCardBoxVO> page=new Page<>(pageno,pagesize);
        try {
            //去除查询条件两端空格
            if (null!=bean.getBox() && !"".equals(bean.getBox())){
                bean.setBox(bean.getBox().trim());
            }

            page=humanSociety.searchTempCardBox(bean,page);
            if(null!=page && page.getData().size()>0){
                tempCardBoxVOList=page.getData();
                total=tempCardBoxVOList.size();
                for(int i=0;i<tempCardBoxVOList.size();i++){
                    TempCardBoxVO boxVO=tempCardBoxVOList.get(i);
                    //数据字典转换
                    //通过银行id  查询到银行名称
                    String bankName="";
                    if(null!=boxVO.getBankId() && boxVO.getBankId()!=0){
                        DistinctAndBankVO distinctAndBankVO=loginUserService.getFkwdById(boxVO.getBankId());
                        if(null!=distinctAndBankVO){
                            bankName=distinctAndBankVO.getName();
                        }
                    }
                    tempCardBoxVOList.get(i).setBankName(bankName);
                    tempCardBoxVOList.get(i).setStatus(Constants.TEMPCARD_BOX_STATUS.get(boxVO.getStatus()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            if(null!=e.getCause()){
                message=e.getCause().getMessage();
            }else{
                message=e.getMessage();
            }
            logger.error("查询临时卡盒异常："+message);
        }
        return new Result(statusCode,total,message,page);

    }

    /**
     * 新增临时卡盒
     * @return
     */

    @RequestMapping(value = "/addTempCardBox",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public Result addTempCardBox(@RequestBody TempCardBoxBean bean) {
        String message = "操作成功";
        String statusCode = Constants.RESULT_MESSAGE_SUCCESS;
        //入参的  盒号  编号起止  用户id  不能为空
        if (StringUtils.isEmpty(bean.getBox()) || StringUtils.isEmpty(bean.getCardBegin()) || StringUtils.isEmpty(bean.getCardEnd()) ||  bean.getUserId() == 0) {
            message = "盒号  编号起止  用户id  不能为空!";
            logger.error(message);
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            return new Result(statusCode, message, bean);
        }
        long  end = Long.parseLong(bean.getCardEnd());
        long begin =Long.parseLong(bean.getCardBegin());
        if((end-begin+1)>250){
            message = "临时卡编号区间不能超过250!";
            logger.error(message);
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            return new Result(statusCode, message, bean);
        }
        //判断起止编号在数据库中是否存在 比如数据库有20-50
        // 那么类似 1-40和   25-60 都是不可用的
        List<TempCardBoxVO> TempCardBoxVOS = new ArrayList<>();
        TempCardBoxVOS = humanSociety.judgeTempCardBox(bean);
        if (null != TempCardBoxVOS && TempCardBoxVOS.size() > 0) {
            message = "新增临时卡盒时，盒号不能重复，临时卡的卡编号也不能重复，请检查数据后重试！";
            logger.error(message);
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            return new Result(statusCode, message, null);
        }
//        //通过userId获取网点编码
//        ORGVO orgvo = loginUserService.getRkwd(bean.getUserId());
//        if (null == orgvo) {
//            message = "获取该用户所在的网点机构信息为空！";
//            logger.error(message);
//            statusCode = Constants.RESULT_MESSAGE_ERROR;
//            return new Result(statusCode, message, null);
//        }
        //事务一
        Result result= humanSociety.addTempCardInfoService(bean);
        //卡盒新增成功  下一步进行明细表新增和入库主表的新增/修改
        if(Constants.RESULT_MESSAGE_SUCCESS.equals(result.getStatusCode())){
            //事务二
            Result r=humanSociety.addTempCardDetailService(bean);
            if(Constants.RESULT_MESSAGE_SUCCESS.equals(r.getStatusCode())){
                return new Result(statusCode,message,null);
            }else{
                //入库主表和明细表处理出现错误   则要将已经新增的卡盒记录删除
                TempCardBoxBean boxBean= (TempCardBoxBean) result.getData();
                humanSociety.delTempCardBox(boxBean);
                message = r.getMessage();
                logger.error(message);
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                return new Result(statusCode,message,null);
            }
        }else{
            message = result.getMessage();
            logger.error(message);
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            return new Result(statusCode,message,null);
        }
    }
    /**
     * 下发临时卡盒
     * @return
     */

    @RequestMapping(value = "/deliveryTempCardBox",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public Result deliveryTempCardBox(@RequestBody TempCardBoxBean bean){
        String message="操作成功";
        String statusCode="";
        //入参的 临时卡盒id    下发银行编码  用户id  不能为空
        if(StringUtils.isEmpty(bean.getIds())|| bean.getBankId()==0  || bean.getUserId()==0){
            message="临时卡盒记录IDS   下发银行id  操作人ID  不能为空!";
            logger.error(message);
            statusCode=Constants.RESULT_MESSAGE_ERROR;
            return new Result(statusCode,message,bean);
        }
        Result result= humanSociety.deliveryTempCardBoxService(bean);
        return result;
    }
    /**
     * 查询临时卡明细列表
     * @return
     */
    @RequestMapping(value = "/searchTempCardDetail",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public Result searchTempCardDetail(@RequestBody TempCardDetailBean bean,HttpServletRequest request){
        String message="操作成功";
        String statusCode=Constants.RESULT_MESSAGE_SUCCESS;
        List<TempCardDetailVO> tempCardDetailVOS=new ArrayList<>();
        int pagesize=request.getParameter("pagesize")!=null?Integer.parseInt(request.getParameter("pagesize")):10;
        int pageno=request.getParameter("pageno")!=null?Integer.parseInt(request.getParameter("pageno")):1;
        Page<TempCardDetailVO> page=new Page<>(pageno,pagesize);
        int total=0;
        //入参的 临时卡编号起止 不能为空，方便查询，因为明细和主表没有主键关联  只能通过编号区间进行查询
        if(StringUtils.isEmpty(bean.getCardBegin()) || StringUtils.isEmpty(bean.getCardEnd())){
            message="临时卡编号起止  不能为空!";
            logger.error(message);
            statusCode=Constants.RESULT_MESSAGE_ERROR;
            return new Result(statusCode,total,message,bean);
        }
        try {
            page=humanSociety.searchTempCardDetail(bean,page);
            if(null==page && page.getCount()==0){
                message="没有查询到临时卡明细列表记录";
                logger.info(message);
                return new Result(statusCode,total,message,null);
            }
            tempCardDetailVOS=page.getData();
            total=tempCardDetailVOS.size();
        } catch (Exception e) {
            e.printStackTrace();
            if(null!=e.getCause()){
                message=e.getCause().getMessage();
            }else{
                message=e.getMessage();
            }
            logger.error("查询临时卡明细列表异常："+message);
        }
        return new Result(statusCode,total,message,page);

    }
    /**
     * 删除临时卡明细列表
     * @return
     */
    @RequestMapping(value = "/delTempCardDetail",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public Result delTempCardDetail(@RequestBody TempCardDetailBean bean){
        String message="操作成功";
        String statusCode=Constants.RESULT_MESSAGE_SUCCESS;
        List<TempCardDetailVO> tempCardDetailVOS=new ArrayList<>();

        if(StringUtils.isEmpty(bean.getIds()) || StringUtils.isEmpty(bean.getBox())){
            message="临时卡明细IDS  卡盒号 不能为空!";
            logger.error(message);
            statusCode=Constants.RESULT_MESSAGE_ERROR;
            return new Result(statusCode,message,bean);
        }
        try {
            //删除之前要判断这个卡所在的卡盒  是否已经下发  已经下发的不能做删除
            TempCardBoxBean tempCardBoxBean=new TempCardBoxBean();
            tempCardBoxBean.setBox(bean.getBox());
            Page<TempCardBoxVO> page=new Page<>(1,1);
            page=humanSociety.searchTempCardBox(tempCardBoxBean,page);
            if(page==null || page.getCount()==0){
                message = "没有查询到盒号为"+bean.getBox()+" 的卡盒记录！";
                logger.info(message);
                statusCode=Constants.RESULT_MESSAGE_ERROR;
                return new Result(statusCode,message,null);
            }
            List<TempCardBoxVO> tempCardBoxVOList=page.getData();
            TempCardBoxVO tempCardBoxVO=tempCardBoxVOList.get(0);
            if(tempCardBoxVO.getStatus().equals(Constants.TEMPCARD_BOX_STATUS_02)){
                message = "该临时卡所在的卡盒已经是下发状态，不允许删除临时卡！";
                logger.info(message);
                statusCode=Constants.RESULT_MESSAGE_ERROR;
                return new Result(statusCode,message,null);
            }
           Result r= humanSociety.delTempCardDetailService(bean,tempCardBoxVO);
           return  r;

        } catch (Exception e) {
            e.printStackTrace();
            if(null!=e.getCause()){
                message=e.getCause().getMessage();
            }else{
                message=e.getMessage();
            }
            logger.error("删除临时卡明细列表异常："+message);
        }
        return new Result(statusCode,message,tempCardDetailVOS);

    }
}
