package com.tecsun.sisp.fakamanagement.modules.controller.replacement;

import com.tecsun.sisp.fakamanagement.common.Config;
import com.tecsun.sisp.fakamanagement.common.Constants;
import com.tecsun.sisp.fakamanagement.common.Page;
import com.tecsun.sisp.fakamanagement.common.Result;
import com.tecsun.sisp.fakamanagement.modules.controller.BaseController;
import com.tecsun.sisp.fakamanagement.modules.entity.param.replacement.ReplaceStatisticsBean;
import com.tecsun.sisp.fakamanagement.modules.entity.result.common.LoginPassswordVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.replacement.ReplaceStatisticsVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.statistics.ORGVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.temporaryCard.CardCountVO;
import com.tecsun.sisp.fakamanagement.modules.service.impl.loginuser.LoginUserServiceImpl;
import com.tecsun.sisp.fakamanagement.modules.service.impl.replacement.ReplaceStatisticsServiceImpl;
import com.tecsun.sisp.fakamanagement.modules.util.ExcelUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 预制卡统计相关接口
 * Created by chenlicong on 2018/10/24.
 */
@Controller
@RequestMapping(value = "/faka/replaceStatistics")
public class ReplaceStatisticsController extends BaseController{

    public final static Logger logger = Logger.getLogger(ReplaceStatisticsController.class);

    @Autowired
    private ReplaceStatisticsServiceImpl replacementCardService;
    @Autowired
    private LoginUserServiceImpl loginUserService;

    //预制卡发卡统计
    @RequestMapping(value = "/getReplaceCardCount", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getReplaceCardCount(@RequestBody ReplaceStatisticsBean req,HttpServletRequest request) {
        String pagesize = request.getParameter("pagesize");
        if ("".equals(pagesize)){
            pagesize = "10";
        }
        String pageno = request.getParameter("pageno");
        if ("".equals(pageno)){
            pageno = "1";
        }
        if(null==req.getUserid() || req.getUserid().equals("")){
            return error("userid不能为空");
        }
        if(req.getBeginTime()!=null){
            req.setBeginTime(req.getBeginTime()+" 00:00:00");
        }
        if(req.getEndTime()!=null){
            req.setEndTime(req.getEndTime()+" 23:59:59");
        }
        Page<ReplaceStatisticsVO> result;
        String message="操作成功";
        String statusCode= Constants.RESULT_MESSAGE_SUCCESS;
        try {
            if (req.getUserid() != null || req.getUserid() != "") {
                //网点查询统计时，通过userId获取rkwd
                List<LoginPassswordVO> list = loginUserService.checkWdTypeByUserId(req.getUserid());
                if (list.size()>0) {
                    //通过userId获取网点编码
                    ORGVO orgvo = loginUserService.getRkwd(Integer.valueOf(req.getUserid()));
                    if (null == orgvo) {
                        message = "获取该用户所在的网点机构信息为空！";
                        logger.error(message);
                        statusCode = Constants.RESULT_MESSAGE_ERROR;
                        return new Result(statusCode, message, null);
                    }
                    req.setRkwd(orgvo.getOrgcode());//网点编码
                }
                //银行查询统计时，通过userId获取bank
                List<LoginPassswordVO> list2 = loginUserService.checkYhTypeByUserId(req.getUserid());
                if (list2.size()>0) {
                    //通过userId获取网点编码
                    ORGVO orgvo = loginUserService.getRkwd(Integer.valueOf(req.getUserid()));
                    if (null == orgvo) {
                        message = "获取该用户所在的网点机构信息为空！";
                        logger.error(message);
                        statusCode = Constants.RESULT_MESSAGE_ERROR;
                        return new Result(statusCode, message, null);
                    }
                    req.setBank(orgvo.getOrgcode());//银行编码
                }
            }
            Page<ReplaceStatisticsVO> page = new Page<>(Integer.parseInt(pageno),Integer.parseInt(pagesize));
            logger.info("开始查询===");
            result = replacementCardService.getReplaceCardCount(page, req);
            if (result.getData().size()>0) {
                logger.info("查询预制卡统计成功===!");
                return ok("查询成功！", result);
            } else {
                logger.error("暂无数据!");
                return error("暂无数据！");
            }
        } catch (Exception e) {
            logger.error("获取信息发生了异常：" + e);
            return error("获取信息失败！");
        }
    }

    //导出数据--银行网点发卡统计
    @RequestMapping(value = "/exportReplaceCount", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result exportReplaceCount(@RequestBody ReplaceStatisticsBean bean,@Context HttpServletRequest request,@Context HttpServletResponse response) {
        String name="";
        List<ReplaceStatisticsVO> list;
        if(null==bean.getUserid() || bean.getUserid().equals("")){
            return error("userid不能为空");
        }
        if(bean.getBeginTime()!=null){
            bean.setBeginTime(bean.getBeginTime()+" 00:00:00");
        }
        if(bean.getEndTime()!=null){
            bean.setEndTime(bean.getEndTime()+" 23:59:59");
        }

        String message="操作成功";
        String statusCode= Constants.RESULT_MESSAGE_SUCCESS;
        try {
            if (bean.getUserid() != null || bean.getUserid() != "") {
                //网点查询统计时，通过userId获取rkwd
                List<LoginPassswordVO> list1 = loginUserService.checkWdTypeByUserId(bean.getUserid());
                if (list1.size()>0) {
                    //通过userId获取网点编码
                    ORGVO orgvo = loginUserService.getRkwd(Integer.valueOf(bean.getUserid()));
                    if (null == orgvo) {
                        message = "获取该用户所在的网点机构信息为空！";
                        logger.error(message);
                        statusCode = Constants.RESULT_MESSAGE_ERROR;
                        return new Result(statusCode, message, null);
                    }
                    bean.setRkwd(orgvo.getOrgcode());//网点编码
                }
                //银行查询统计时，通过userId获取bank
                List<LoginPassswordVO> list2 = loginUserService.checkYhTypeByUserId(bean.getUserid());
                if (list2.size()>0) {
                    //通过userId获取网点编码
                    ORGVO orgvo = loginUserService.getRkwd(Integer.valueOf(bean.getUserid()));
                    if (null == orgvo) {
                        message = "获取该用户所在的网点机构信息为空！";
                        logger.error(message);
                        statusCode = Constants.RESULT_MESSAGE_ERROR;
                        return new Result(statusCode, message, null);
                    }
                    bean.setBank(orgvo.getOrgcode());//银行编码
                }
            }

            HSSFWorkbook wb=new HSSFWorkbook();
            HSSFSheet sheet=wb.createSheet("数据");
            logger.info("开始查询数据===");

            logger.info("开始查询===");
            list = replacementCardService.getReplaceCardCountList(bean);

            if(list.size()>65535){
                return error("数据量过大,请添加条件导出！");
            }
            logger.info("开始导出===");
            String[] headers=new String[]{"网点","入库数量","发卡数量","坏卡数量","剩余数量"};
            ExcelUtils.addHeader(headers, sheet);
            for (int i = 0; i < list.size(); i++) {
                ExcelUtils.addData(i, 0, list.get(i).getName(), sheet);
                ExcelUtils.addData(i, 1, String.valueOf(list.get(i).getCardCount()), sheet);
                ExcelUtils.addData(i, 2, String.valueOf(list.get(i).getFakaCount()), sheet);
                ExcelUtils.addData(i, 3, String.valueOf(list.get(i).getBadCount()), sheet);
                ExcelUtils.addData(i, 4, String.valueOf(list.get(i).getSurplusCount()), sheet);
            }
            name="replaceCardCount_"+new SimpleDateFormat("yyyyMMddHHmmSS").format(new Date())+".xls";
            String path= Config.getInstance().get("exp.temp.path");
            //path = "D:/tecsun/jilin";
            File file=new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            FileOutputStream fileOut = new FileOutputStream(path+name);
            wb.write(fileOut);
            fileOut.close();

        } catch (Exception e) {
            logger.error("导出出错！"+e);
            return error("导出出错！"+e);
        }
        logger.info("导出成功==="+name);
        return ok("导出成功！",name);
    }



}
