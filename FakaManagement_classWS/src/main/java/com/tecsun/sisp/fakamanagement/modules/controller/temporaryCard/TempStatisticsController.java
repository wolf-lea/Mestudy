package com.tecsun.sisp.fakamanagement.modules.controller.temporaryCard;

import com.tecsun.sisp.fakamanagement.common.*;
import com.tecsun.sisp.fakamanagement.modules.controller.BaseController;
import com.tecsun.sisp.fakamanagement.modules.entity.param.temporaryCard.CardCountBean;
import com.tecsun.sisp.fakamanagement.modules.entity.param.temporaryCard.TempStatisticsBean;
import com.tecsun.sisp.fakamanagement.modules.entity.result.card.CardInfoVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.statistics.ORGVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.temporaryCard.CardCountVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.temporaryCard.TempStatisticsVO;
import com.tecsun.sisp.fakamanagement.modules.service.impl.loginuser.LoginUserServiceImpl;
import com.tecsun.sisp.fakamanagement.modules.service.impl.temporaryCard.TempStatisticsServiceImpl;
import com.tecsun.sisp.fakamanagement.modules.util.ExcelUtils;
import org.apache.commons.lang.StringUtils;
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
import javax.validation.constraints.Max;
import javax.ws.rs.core.Context;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 临时卡统计相关接口
 * Created by chenlicong on 2018/3/21.
 */
@Controller
@RequestMapping(value = "/faka/tempStatistics")
public class TempStatisticsController extends BaseController{

    public final static Logger logger = Logger.getLogger(TempStatisticsController.class);

    @Autowired
    private TempStatisticsServiceImpl tempStatisticsService;
    @Autowired
    private LoginUserServiceImpl loginUserService;

    //临时卡市人社统计
    @RequestMapping(value = "/getTempCount", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getTempCount(@RequestBody TempStatisticsBean req,HttpServletRequest request) {
        String pagesize = request.getParameter("pagesize");
        if ("".equals(pagesize)){
            pagesize = "10";
        }
        String pageno = request.getParameter("pageno");
        if ("".equals(pageno)){
            pageno = "1";
        }
        Page<TempStatisticsVO> result;
        try {
            Page<TempStatisticsVO> page = new Page<>(Integer.parseInt(pageno),Integer.parseInt(pagesize));
            logger.info("开始查询===");
            result = tempStatisticsService.getTempCount(page, req);
            if (result.getData().size()>0) {
                logger.info("查询临时卡市人社统计成功===!");
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

    //临时卡区县人社统计
    @RequestMapping(value = "/getTempDistinctCount", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getTempDistinctCount(@RequestBody TempStatisticsBean req,HttpServletRequest request) {
        String pagesize = request.getParameter("pagesize");
        if ("".equals(pagesize)){
            pagesize = "10";
        }
        String pageno = request.getParameter("pageno");
        if ("".equals(pageno)){
            pageno = "1";
        }
        Page<TempStatisticsVO> result;
        try {
            Page<TempStatisticsVO> page = new Page<>(Integer.parseInt(pageno),Integer.parseInt(pagesize));
            logger.info("开始查询===");
            result = tempStatisticsService.getTempDistinctCount(page, req);
            if (result.getData().size()>0) {
                logger.info("查询临时卡区县人社统计成功===!");
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

    //临时卡银行统计
    @RequestMapping(value = "/getTempBankCount", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getTempBankCount(@RequestBody TempStatisticsBean req,HttpServletRequest request) {
        String pagesize = request.getParameter("pagesize");
        if ("".equals(pagesize)){
            pagesize = "10";
        }
        String pageno = request.getParameter("pageno");
        if ("".equals(pageno)){
            pageno = "1";
        }
        Page<TempStatisticsVO> result;
        try {
            Page<TempStatisticsVO> page = new Page<>(Integer.parseInt(pageno),Integer.parseInt(pagesize));
            //通过userid获取入库网点ID
            ORGVO orgvo = loginUserService.getRkwd(Integer.valueOf(req.getUserid()));
            if(orgvo == null){
                return error("获取入库网点ID出错");
            }
            req.setRkwd(orgvo.getOrgid());

            logger.info("开始查询===");
            result = tempStatisticsService.getTempBankCount(page, req);
            if (result.getData().size()>0) {
                logger.info("查询临时卡银行统计成功===!");
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

    //临时卡网点统计
    @RequestMapping(value = "/getTempBranchCount", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getTempBranchCount(@RequestBody TempStatisticsBean req,HttpServletRequest request) {
        String pagesize = request.getParameter("pagesize");
        if ("".equals(pagesize)){
            pagesize = "10";
        }
        String pageno = request.getParameter("pageno");
        if ("".equals(pageno)){
            pageno = "1";
        }
        Page<TempStatisticsVO> result;
        try {
            Page<TempStatisticsVO> page = new Page<>(Integer.parseInt(pageno),Integer.parseInt(pagesize));
            //通过userid获取入库网点ID
            ORGVO orgvo = loginUserService.getRkwd(Integer.valueOf(req.getUserid()));
            if(orgvo == null){
                return error("获取入库网点ID出错");
            }
            req.setRkwd(orgvo.getOrgid());

            logger.info("开始查询===");
            result = tempStatisticsService.getTempBranchCount(page, req);
            if (result.getData().size()>0) {
                logger.info("查询临时卡网点统计成功===!");
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

    //市人社网点发卡统计
    @RequestMapping(value = "/getCardCount", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getCardCount(@RequestBody CardCountBean bean,HttpServletRequest request) {
        int pagesize=request.getParameter("pagesize")!=null?Integer.parseInt(request.getParameter("pagesize")):10;
        int pageno=request.getParameter("pageno")!=null?Integer.parseInt(request.getParameter("pageno")):1;
        Page<CardCountVO> result;
        try {
            logger.info("开始查询===");
            //通过该用户id 查询该用户下的所有网点信息   直接查询所有该网点内的数量即可
            CardCountBean cardCountBean=new CardCountBean();
            Page<CardCountVO> p = new Page<>(pageno,pagesize);
            cardCountBean.setPage(p);
            cardCountBean.setBankId(bean.getBankId());
            cardCountBean.setCountryId(bean.getCountryId());
            cardCountBean.setBranchId(bean.getBranchId());
            List<CardCountVO> orgvoList=loginUserService.queryAllOrg(cardCountBean);
            if(null!=orgvoList && orgvoList.size()>0){
                p.setData(orgvoList);
                List orgCodeList=new ArrayList();
                List orgIdList=new ArrayList();
                for(int i=0;i<orgvoList.size();i++){
                    CardCountVO o=orgvoList.get(i);
                    orgCodeList.add(0,o.getOrgCode());
                    orgIdList.add(0,o.getId());
                }
                result = tempStatisticsService.getCardCount(bean,p,orgCodeList,orgIdList);
                if (result.getData().size()>0) {
                    logger.info("查询市人社统计成功===!");
                    return ok(result.getData().size(),"查询成功！", result);
                } else {
                    logger.error("暂无数据!");
                    return error("暂无数据！");
                }
            }else{
                logger.error("暂无数据!");
                return error("暂无数据！");
            }

        } catch (Exception e) {
            logger.error("获取信息发生了异常：" + e);
            return error("获取信息失败！");
        }
    }
    //导出数据--市人社网点发卡统计
    @RequestMapping(value = "/exportCardCount", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result exportCardCount(@RequestBody CardCountBean bean,@Context HttpServletRequest request,@Context HttpServletResponse response) {
        String name="";
        int pagesize=request.getParameter("pagesize")!=null?Integer.parseInt(request.getParameter("pagesize")):10;
        int pageno=request.getParameter("pageno")!=null?Integer.parseInt(request.getParameter("pageno")):1;
        Page<CardCountVO> result;
        List<CardCountVO> list;
        try {
            HSSFWorkbook wb=new HSSFWorkbook();
            HSSFSheet sheet=wb.createSheet("数据");
            logger.info("开始查询数据===");

            //通过该用户id 查询该用户下的所有网点信息   直接查询所有该网点内的数量即可
            CardCountBean cardCountBean=new CardCountBean();
            Page<CardCountVO> p = new Page<>(pageno,pagesize);
            cardCountBean.setPage(p);
            cardCountBean.setBankId(bean.getBankId());
            cardCountBean.setCountryId(bean.getCountryId());
            cardCountBean.setBranchId(bean.getBranchId());
            List<CardCountVO> orgvoList=loginUserService.queryAllOrg(cardCountBean);
            if(null!=orgvoList && orgvoList.size()>0){
                p.setData(orgvoList);
                List orgCodeList=new ArrayList();
                List orgIdList=new ArrayList();
                for(int i=0;i<orgvoList.size();i++){
                    CardCountVO o=orgvoList.get(i);
                    orgCodeList.add(0,o.getOrgCode());
                    orgIdList.add(0,o.getId());
                }
                result = tempStatisticsService.getCardCount(bean,p,orgCodeList,orgIdList);

                list = result.getData();
                if(list.size()>65535){
                    return error("数据量过大,请添加条件导出！");
                }
                logger.info("开始导出===");
                String[] headers=new String[]{"网点","制卡成功数量","发卡成功数量","临时发卡数量","即时发卡数量"};
                ExcelUtils.addHeader(headers, sheet);
                for (int i = 0; i < list.size(); i++) {
                    ExcelUtils.addData(i, 0, list.get(i).getOrgName(), sheet);
                    ExcelUtils.addData(i, 1, String.valueOf(list.get(i).getMakeCardCount()), sheet);
                    ExcelUtils.addData(i, 2, String.valueOf(list.get(i).getGrantCardCount()), sheet);
                    ExcelUtils.addData(i, 3, String.valueOf(list.get(i).getTempGrantCardCount()), sheet);
                    ExcelUtils.addData(i, 4, String.valueOf(list.get(i).getNowGrantCardCount()), sheet);
                }
                name="branch_cardcount_"+new SimpleDateFormat("yyyyMMddHHmmSS").format(new Date())+".xls";
                String path=Config.getInstance().get("exp.temp.path");
                //path = "D:/tecsun/jilin";
                File file=new File(path);
                if (!file.exists()) {
                    file.mkdirs();
                }
                FileOutputStream fileOut = new FileOutputStream(path+name);
                wb.write(fileOut);
                fileOut.close();

            }else{
                logger.error("暂无数据!");
                return error("暂无数据！");
            }

        } catch (Exception e) {
            logger.error("导出出错！"+e);
            return error("导出出错！"+e);
        }
        logger.info("导出成功==="+name);
        return ok("导出成功！",name);
    }

    //区县人社网点发卡统计
    @RequestMapping(value = "/getCountryCount", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getCountryCount(@RequestBody CardCountBean bean,HttpServletRequest request) {
       int pagesize=request.getParameter("pagesize")!=null?Integer.parseInt(request.getParameter("pagesize")):10;
        int pageno=request.getParameter("pageno")!=null?Integer.parseInt(request.getParameter("pageno")):1;
        Page<CardCountVO> result;
        String message="操作成功";
        String statusCode=Constants.RESULT_MESSAGE_SUCCESS;
        if ( null==bean.getUserId()|| bean.getUserId() == 0) {
            message = " 用户id  不能为空!";
            logger.error(message);
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            return new Result(statusCode, message, bean);
        }
        try {
            Page<CardCountVO> page = new Page<>(pageno,pagesize);
            logger.info("开始查询===");
            //通过userId获取网点编码
            ORGVO orgvo = loginUserService.getRkwd(bean.getUserId());
            if (null == orgvo) {
                message = "获取该用户所在的网点机构信息为空！";
                logger.error(message);
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                return new Result(statusCode, message, null);
            }
            //通过该用户id 查询该用户下的所有网点信息   直接查询所有该网点内的数量即可
            CardCountBean cardCountBean=new CardCountBean();
            Page<CardCountVO> p = new Page<>(pageno,pagesize);
            cardCountBean.setPage(p);
            cardCountBean.setBankId(bean.getBankId());
            cardCountBean.setCountryId(orgvo.getDistinctId());
            cardCountBean.setBranchId(bean.getBranchId());
            List<CardCountVO> orgvoList=loginUserService.queryAllOrg(cardCountBean);
            if(null!=orgvoList && orgvoList.size()>0){
                p.setData(orgvoList);
                List orgCodeList=new ArrayList();
                List orgIdList=new ArrayList();
                for(int i=0;i<orgvoList.size();i++){
                    CardCountVO o=orgvoList.get(i);
                    orgCodeList.add(0,o.getOrgCode());
                    orgIdList.add(0,o.getId());
                }
                bean.setCountryId(orgvo.getDistinctId());
                result = tempStatisticsService.getCardCount( bean,p,orgCodeList,orgIdList);
                if (result.getData().size()>0) {
                    logger.info("查询区县人社统计成功===!");
                    return ok(result.getData().size(),"查询成功！", result);
                } else {
                    logger.error("暂无数据!");
                    return error("暂无数据！");
                }
            }
            else{
                logger.error("暂无数据!");
                return error("暂无数据！");
            }

        } catch (Exception e) {
            logger.error("获取信息发生了异常：" + e);
            return error("获取信息失败！");
        }
    }
    //导出数据--区县人社网点发卡统计
    @RequestMapping(value = "/exportCountryCount", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result exportCountryCount(@RequestBody CardCountBean bean,@Context HttpServletRequest request,@Context HttpServletResponse response) {
        String name="";
        int pagesize=request.getParameter("pagesize")!=null?Integer.parseInt(request.getParameter("pagesize")):10;
        int pageno=request.getParameter("pageno")!=null?Integer.parseInt(request.getParameter("pageno")):1;
        Page<CardCountVO> result;
        List<CardCountVO> list;

        String message="操作成功";
        String statusCode=Constants.RESULT_MESSAGE_SUCCESS;
        if ( null==bean.getUserId()|| bean.getUserId() == 0) {
            message = " 用户id  不能为空!";
            logger.error(message);
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            return new Result(statusCode, message, bean);
        }
        try {
            HSSFWorkbook wb=new HSSFWorkbook();
            HSSFSheet sheet=wb.createSheet("数据");
            logger.info("开始查询数据===");

            Page<CardCountVO> page = new Page<>(pageno,pagesize);
            logger.info("开始查询===");
            //通过userId获取网点编码
            ORGVO orgvo = loginUserService.getRkwd(bean.getUserId());
            if (null == orgvo) {
                message = "获取该用户所在的网点机构信息为空！";
                logger.error(message);
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                return new Result(statusCode, message, null);
            }
            //通过该用户id 查询该用户下的所有网点信息   直接查询所有该网点内的数量即可
            CardCountBean cardCountBean=new CardCountBean();
            Page<CardCountVO> p = new Page<>(pageno,pagesize);
            cardCountBean.setPage(p);
            cardCountBean.setBankId(bean.getBankId());
            cardCountBean.setCountryId(orgvo.getDistinctId());
            cardCountBean.setBranchId(bean.getBranchId());
            List<CardCountVO> orgvoList=loginUserService.queryAllOrg(cardCountBean);
            if(null!=orgvoList && orgvoList.size()>0){
                p.setData(orgvoList);
                List orgCodeList=new ArrayList();
                List orgIdList=new ArrayList();
                for(int i=0;i<orgvoList.size();i++){
                    CardCountVO o=orgvoList.get(i);
                    orgCodeList.add(0,o.getOrgCode());
                    orgIdList.add(0,o.getId());
                }
                bean.setCountryId(orgvo.getDistinctId());
                result = tempStatisticsService.getCardCount( bean,p,orgCodeList,orgIdList);

                list = result.getData();
                if(list.size()>65535){
                    return error("数据量过大,请添加条件导出！");
                }
                logger.info("开始导出===");
                String[] headers=new String[]{"网点","制卡成功数量","发卡成功数量","临时发卡数量","即时发卡数量"};
                ExcelUtils.addHeader(headers, sheet);
                for (int i = 0; i < list.size(); i++) {
                    ExcelUtils.addData(i, 0, list.get(i).getOrgName(), sheet);
                    ExcelUtils.addData(i, 1, String.valueOf(list.get(i).getMakeCardCount()), sheet);
                    ExcelUtils.addData(i, 2, String.valueOf(list.get(i).getGrantCardCount()), sheet);
                    ExcelUtils.addData(i, 3, String.valueOf(list.get(i).getTempGrantCardCount()), sheet);
                    ExcelUtils.addData(i, 4, String.valueOf(list.get(i).getNowGrantCardCount()), sheet);
                }
                name="branch_cardcount_"+new SimpleDateFormat("yyyyMMddHHmmSS").format(new Date())+".xls";
                String path=Config.getInstance().get("exp.temp.path");
                //path = "D:/tecsun/jilin";
                File file=new File(path);
                if (!file.exists()) {
                    file.mkdirs();
                }
                FileOutputStream fileOut = new FileOutputStream(path+name);
                wb.write(fileOut);
                fileOut.close();

            }else{
                logger.error("暂无数据!");
                return error("暂无数据！");
            }

        } catch (Exception e) {
            logger.error("导出出错！"+e);
            return error("导出出错！"+e);
        }
        logger.info("导出成功==="+name);
        return ok("导出成功！",name);
    }

    //银行网点发卡统计
    @RequestMapping(value = "/getBankCount", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getBankCount(@RequestBody CardCountBean bean, HttpServletRequest request) {
        int pagesize=request.getParameter("pagesize")!=null?Integer.parseInt(request.getParameter("pagesize")):10;
        int pageno=request.getParameter("pageno")!=null?Integer.parseInt(request.getParameter("pageno")):1;
        Page<CardCountVO> result;
        String message="操作成功";
        String statusCode=Constants.RESULT_MESSAGE_SUCCESS;
        if (null==bean.getUserId()|| bean.getUserId() == 0) {
            message = " 用户id  不能为空!";
            logger.error(message);
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            return new Result(statusCode, message, bean);
        }
        try {
            Page<CardCountVO> page = new Page<>(pageno,pagesize);
            logger.info("开始查询===");
            //通过userId获取该
            ORGVO orgvo = loginUserService.getRkwd(bean.getUserId());
            if (null == orgvo) {
                message = "获取该用户所在的网点机构信息为空！";
                logger.error(message);
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                return new Result(statusCode, message, null);
            }
            //通过该用户id 查询该用户下的所有网点信息   直接查询所有该网点内的数量即可
            CardCountBean cardCountBean=new CardCountBean();
            Page<CardCountVO> p = new Page<>(pageno,pagesize);
            cardCountBean.setPage(p);
            cardCountBean.setBankId(orgvo.getOrgid());
            cardCountBean.setCountryId(bean.getCountryId());
            cardCountBean.setBranchId(bean.getBranchId());
            List<CardCountVO> orgvoList=loginUserService.queryAllOrg(cardCountBean);
            if(null!=orgvoList && orgvoList.size()>0){
                p.setData(orgvoList);
                List orgCodeList=new ArrayList();
                List orgIdList=new ArrayList();
                for(int i=0;i<orgvoList.size();i++){
                    CardCountVO o=orgvoList.get(i);
                    orgCodeList.add(0,o.getOrgCode());
                    orgIdList.add(0,o.getId());
                }
                bean.setBankId(orgvo.getOrgid());
                result = tempStatisticsService.getCardCount(bean,p,orgCodeList,orgIdList);
                if (result.getData().size()>0) {
                    logger.info("查询银行统计成功===!");
                    return ok(result.getData().size(),"查询成功！", result);
                } else {
                    logger.error("暂无数据!");
                    return error("暂无数据！");
                }
            }else{
                logger.error("暂无数据!");
                return error("暂无数据！");
            }

        } catch (Exception e) {
            logger.error("获取信息发生了异常：" + e);
            return error("获取信息失败！");
        }
    }
    //导出数据--银行网点发卡统计
    @RequestMapping(value = "/exportBankCount", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result exportBankCount(@RequestBody CardCountBean bean,@Context HttpServletRequest request,@Context HttpServletResponse response) {
        String name="";
        int pagesize=request.getParameter("pagesize")!=null?Integer.parseInt(request.getParameter("pagesize")):10;
        int pageno=request.getParameter("pageno")!=null?Integer.parseInt(request.getParameter("pageno")):1;
        Page<CardCountVO> result;
        List<CardCountVO> list;

        String message="操作成功";
        String statusCode=Constants.RESULT_MESSAGE_SUCCESS;
        if (null==bean.getUserId()|| bean.getUserId() == 0) {
            message = " 用户id  不能为空!";
            logger.error(message);
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            return new Result(statusCode, message, bean);
        }
        try {
            HSSFWorkbook wb=new HSSFWorkbook();
            HSSFSheet sheet=wb.createSheet("数据");
            logger.info("开始查询数据===");

            Page<CardCountVO> page = new Page<>(pageno,pagesize);
            logger.info("开始查询===");
            //通过userId获取该
            ORGVO orgvo = loginUserService.getRkwd(bean.getUserId());
            if (null == orgvo) {
                message = "获取该用户所在的网点机构信息为空！";
                logger.error(message);
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                return new Result(statusCode, message, null);
            }
            //通过该用户id 查询该用户下的所有网点信息   直接查询所有该网点内的数量即可
            CardCountBean cardCountBean=new CardCountBean();
            Page<CardCountVO> p = new Page<>(pageno,pagesize);
            cardCountBean.setPage(p);
            cardCountBean.setBankId(orgvo.getOrgid());
            cardCountBean.setCountryId(bean.getCountryId());
            cardCountBean.setBranchId(bean.getBranchId());
            List<CardCountVO> orgvoList=loginUserService.queryAllOrg(cardCountBean);
            if(null!=orgvoList && orgvoList.size()>0){
                p.setData(orgvoList);
                List orgCodeList=new ArrayList();
                List orgIdList=new ArrayList();
                for(int i=0;i<orgvoList.size();i++){
                    CardCountVO o=orgvoList.get(i);
                    orgCodeList.add(0,o.getOrgCode());
                    orgIdList.add(0,o.getId());
                }
                bean.setBankId(orgvo.getOrgid());
                result = tempStatisticsService.getCardCount(bean,p,orgCodeList,orgIdList);

                list = result.getData();
                if(list.size()>65535){
                    return error("数据量过大,请添加条件导出！");
                }
                logger.info("开始导出===");
                String[] headers=new String[]{"网点","制卡成功数量","发卡成功数量","临时发卡数量","即时发卡数量"};
                ExcelUtils.addHeader(headers, sheet);
                for (int i = 0; i < list.size(); i++) {
                    ExcelUtils.addData(i, 0, list.get(i).getOrgName(), sheet);
                    ExcelUtils.addData(i, 1, String.valueOf(list.get(i).getMakeCardCount()), sheet);
                    ExcelUtils.addData(i, 2, String.valueOf(list.get(i).getGrantCardCount()), sheet);
                    ExcelUtils.addData(i, 3, String.valueOf(list.get(i).getTempGrantCardCount()), sheet);
                    ExcelUtils.addData(i, 4, String.valueOf(list.get(i).getNowGrantCardCount()), sheet);
                }
                name="branch_cardcount_"+new SimpleDateFormat("yyyyMMddHHmmSS").format(new Date())+".xls";
                String path=Config.getInstance().get("exp.temp.path");
                //path = "D:/tecsun/jilin";
                File file=new File(path);
                if (!file.exists()) {
                    file.mkdirs();
                }
                FileOutputStream fileOut = new FileOutputStream(path+name);
                wb.write(fileOut);
                fileOut.close();

            }else{
                logger.error("暂无数据!");
                return error("暂无数据！");
            }

        } catch (Exception e) {
            logger.error("导出出错！"+e);
            return error("导出出错！"+e);
        }
        logger.info("导出成功==="+name);
        return ok("导出成功！",name);
    }

    //网点统计
    @RequestMapping(value = "/getBranchCount", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getBranchCount(@RequestBody CardCountBean bean,HttpServletRequest request) {
        int pagesize=request.getParameter("pagesize")!=null?Integer.parseInt(request.getParameter("pagesize")):10;
        int pageno=request.getParameter("pageno")!=null?Integer.parseInt(request.getParameter("pageno")):1;
        Page<CardCountVO> result;
        String message="操作成功";
        String statusCode=Constants.RESULT_MESSAGE_SUCCESS;
        if ( null==bean.getUserId()|| bean.getUserId() == 0) {
            message = " 用户id  不能为空!";
            logger.error(message);
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            return new Result(statusCode, message, bean);
        }
        try {
            Page<CardCountVO> page = new Page<>(pageno,pagesize);
            logger.info("开始查询===");
            //通过userId获取网点编码
            ORGVO orgvo = loginUserService.getRkwd(bean.getUserId());
            if (null == orgvo) {
                message = "获取该用户所在的网点机构信息为空！";
                logger.error(message);
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                return new Result(statusCode, message, null);
            }
            bean.setOrgCode(orgvo.getOrgcode());
            bean.setBranchId(orgvo.getOrgid());
            result = tempStatisticsService.getBranchCount(page, bean,orgvo);
            if (result.getData().size()>0) {
                logger.info("查询网点统计成功===!");
                return ok(result.getData().size(),"查询成功！", result);
            } else {
                logger.error("暂无数据!");
                return error("暂无数据！");
            }
        } catch (Exception e) {
            logger.error("获取信息发生了异常：" + e);
            return error("获取信息失败！");
        }
    }

    //人社按区县统计制卡数量 发卡数量 临时卡发卡数量
    @RequestMapping(value = "/getCardCountByDistinct", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getCardCountByDistinct(@RequestBody CardCountBean req,HttpServletRequest request) {
        String pagesize = request.getParameter("pagesize");
        if ("".equals(pagesize)){
            pagesize = "10";
        }
        String pageno = request.getParameter("pageno");
        if ("".equals(pageno)){
            pageno = "1";
        }
        Page<CardCountVO> result;
        try {
            Page<CardCountVO> page = new Page<>(Integer.parseInt(pageno),Integer.parseInt(pagesize));
            logger.info("开始查询===");
            result = tempStatisticsService.getCardCountByDistinct(page, req);
            if (result.getData().size()>0) {
                logger.info("人社按区县统计查询成功===!");
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

    //人社按银行统计制卡数量 发卡数量 临时卡发卡数量
    @RequestMapping(value = "/getCardCountByBank", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getCardCountByBank(@RequestBody CardCountBean req,HttpServletRequest request) {
        String pagesize = request.getParameter("pagesize");
        if ("".equals(pagesize)){
            pagesize = "10";
        }
        String pageno = request.getParameter("pageno");
        if ("".equals(pageno)){
            pageno = "1";
        }
        Page<CardCountVO> result;
        try {
            Page<CardCountVO> page = new Page<>(Integer.parseInt(pageno),Integer.parseInt(pagesize));
            logger.info("开始查询===");
            result = tempStatisticsService.getCardCountByBank(page, req);
            if (result.getData().size()>0) {
                logger.info("人社按银行统计查询成功===!");
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

    //银行按区县统计制卡数量 发卡数量 临时卡发卡数量
    @RequestMapping(value = "/getCardCountByBankRs", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getCardCountByBankRs(@RequestBody CardCountBean req,HttpServletRequest request) {
        String pagesize = request.getParameter("pagesize");
        if ("".equals(pagesize)){
            pagesize = "10";
        }
        String pageno = request.getParameter("pageno");
        if ("".equals(pageno)){
            pageno = "1";
        }
        Page<CardCountVO> result;
        CardCountVO vo;
        try {
            Page<CardCountVO> page = new Page<>(Integer.parseInt(pageno),Integer.parseInt(pagesize));
            logger.info("开始查询===");
            vo = tempStatisticsService.getBankOrDistinctByUserId(req);//查询银行编码
            logger.info("银行统计查询===");
            if (vo.getBank() != null){
                req.setBank(vo.getBank());
            }
            result = tempStatisticsService.getCardCountByDistinct(page, req);
            if (result.getData().size()>0) {
                logger.info("银行按区县统计查询成功===!");
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

    //区县人社按银行统计制卡数量 发卡数量 临时卡发卡数量
    @RequestMapping(value = "/getCardCountByDistinctRs", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getCardCountByDistinctRs(@RequestBody CardCountBean req,HttpServletRequest request) {
        String pagesize = request.getParameter("pagesize");
        if ("".equals(pagesize)){
            pagesize = "10";
        }
        String pageno = request.getParameter("pageno");
        if ("".equals(pageno)){
            pageno = "1";
        }
        Page<CardCountVO> result;
        CardCountVO vo;
        try {
            Page<CardCountVO> page = new Page<>(Integer.parseInt(pageno),Integer.parseInt(pagesize));
            logger.info("开始查询===");
            vo = tempStatisticsService.getBankOrDistinctByUserId(req);//查询银行编码
            logger.info("区县人社统计查询===");
            if (vo.getDistinctId() != null){
                req.setDistinctId(vo.getDistinctId());
            }
            result = tempStatisticsService.getCardCountByBank(page, req);
            if (result.getData().size()>0) {
                logger.info("区县人社按银行统计查询成功===!");
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

    //excle数据导出--按区县统计
    @RequestMapping(value = "/exportCardCountByDistinct", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result exportCardCountByDistinct(@RequestBody CardCountBean vo, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        String name="";
        CardCountVO vo1;
        Page<CardCountVO> page;
        List<CardCountVO> list;
        try {

            HSSFWorkbook wb=new HSSFWorkbook();
            HSSFSheet sheet=wb.createSheet("数据");
            logger.info("开始查询数据===");

            if (vo.getUserId() != null){
                vo1 = tempStatisticsService.getBankOrDistinctByUserId(vo);//查询银行编码
                if (vo1.getBank() != null){
                    vo.setBank(vo1.getBank());
                }
            }

            page = tempStatisticsService.getCardCountByDistinct(new Page<CardCountVO>(1, 99999), vo);
            list = page.getData();
            if(list.size()>65535){
                return error("数据量过大,请添加条件导出！");
            }
            logger.info("开始导出===");
            String[] headers=new String[]{"区县","制卡成功数量","发卡成功数量","临时发卡数量","即时发卡数量"};
            ExcelUtils.addHeader(headers, sheet);
            for (int i = 0; i < list.size(); i++) {
                ExcelUtils.addData(i, 0, list.get(i).getName(), sheet);
                ExcelUtils.addData(i, 1, String.valueOf(list.get(i).getMakeCardCount()), sheet);
                ExcelUtils.addData(i, 2, String.valueOf(list.get(i).getGrantCardCount()), sheet);
                ExcelUtils.addData(i, 3, String.valueOf(list.get(i).getTempGrantCardCount()), sheet);
                ExcelUtils.addData(i, 4, String.valueOf(list.get(i).getNowGrantCardCount()), sheet);
            }
            name="distinct_cardcount_"+new SimpleDateFormat("yyyyMMddHHmmSS").format(new Date())+".xls";
            String path=Config.getInstance().get("exp.temp.path");
            //path = "D:/tecsun/jilin";
            File file=new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            FileOutputStream fileOut = new FileOutputStream(path+name);
            wb.write(fileOut);
            fileOut.close();

            logger.info("导出成功==="+name);
            return ok("导出成功！",name);
        } catch (Exception e) {
            logger.error("导出出错！" + e);
            return error("导出出错！" + e);
        }

    }

    //excle数据导出--按银行统计
    @RequestMapping(value = "/exportCardCountByBank", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result exportCardCountByBank(@RequestBody CardCountBean vo, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        String name="";
        CardCountVO vo1;
        Page<CardCountVO> page;
        List<CardCountVO> list;
        try {

            HSSFWorkbook wb=new HSSFWorkbook();
            HSSFSheet sheet=wb.createSheet("数据");
            logger.info("开始查询数据===");

            if (vo.getUserId() != null){
                vo1 = tempStatisticsService.getBankOrDistinctByUserId(vo);//查询银行编码
                if (vo1.getDistinctId() != null){
                    vo.setDistinctId(vo1.getDistinctId());
                }
            }

            page = tempStatisticsService.getCardCountByBank(new Page<CardCountVO>(1, 99999), vo);
            list = page.getData();
            if(list.size()>65535){
                return error("数据量过大,请添加条件导出！");
            }
            logger.info("开始导出===");
            String[] headers=new String[]{"制卡银行","制卡成功数量","发卡成功数量","临时发卡数量","即时发卡数量"};
            ExcelUtils.addHeader(headers, sheet);
            for (int i = 0; i < list.size(); i++) {
                ExcelUtils.addData(i, 0, list.get(i).getName(), sheet);
                ExcelUtils.addData(i, 1, String.valueOf(list.get(i).getMakeCardCount()), sheet);
                ExcelUtils.addData(i, 2, String.valueOf(list.get(i).getGrantCardCount()), sheet);
                ExcelUtils.addData(i, 3, String.valueOf(list.get(i).getTempGrantCardCount()), sheet);
                ExcelUtils.addData(i, 4, String.valueOf(list.get(i).getNowGrantCardCount()), sheet);
            }
            name="bank_cardcount_"+new SimpleDateFormat("yyyyMMddHHmmSS").format(new Date())+".xls";
            String path=Config.getInstance().get("exp.temp.path");
            //path = "D:/tecsun/jilin";
            File file=new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            FileOutputStream fileOut = new FileOutputStream(path+name);
            wb.write(fileOut);
            fileOut.close();

            logger.info("导出成功==="+name);
            return ok("导出成功！",name);
        } catch (Exception e) {
            logger.error("导出出错！"+e);
            return error("导出出错！" + e);
        }

    }

    //获取excle数据
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


    /*public static void main(String args[]) {

        try {

            String name = "";
            CardCountVO vo1;
            List<CardCountVO> page;
            List<CardCountVO> list;
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("数据");
            logger.info("开始查询数据===");

             tempStatisticsService.getCardCountByBank();
            list = page.getData();
            logger.info("开始导出===");
            String[] headers = new String[]{"制卡银行", "制卡成功数量", "发卡成功数量", "临时发卡数量", "即时发卡数量"};
            ExcelUtils.addHeader(headers, sheet);
            for (int i = 0; i < list.size(); i++) {
                ExcelUtils.addData(i, 0, list.get(i).getName(), sheet);
                ExcelUtils.addData(i, 1, String.valueOf(list.get(i).getMakeCardCount()), sheet);
                ExcelUtils.addData(i, 2, String.valueOf(list.get(i).getGrantCardCount()), sheet);
                ExcelUtils.addData(i, 3, String.valueOf(list.get(i).getTempGrantCardCount()), sheet);
                ExcelUtils.addData(i, 4, String.valueOf(list.get(i).getNowGrantCardCount()), sheet);
            }
            name = "bank_cardcount_" + new SimpleDateFormat("yyyyMMddHHmmSS").format(new Date()) + ".xls";
            String path = Config.getInstance().get("exp.temp.path");
            //path = "D:/tecsun/jilin";
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            FileOutputStream fileOut = new FileOutputStream(path + name);
            wb.write(fileOut);
            fileOut.close();

            logger.info("导出成功===" + name);
        }catch (Exception e){
            System.out.println("==="+e);
        }

    }*/

}
