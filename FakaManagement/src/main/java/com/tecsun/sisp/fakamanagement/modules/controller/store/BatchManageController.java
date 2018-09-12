package com.tecsun.sisp.fakamanagement.modules.controller.store;

import com.tecsun.sisp.fakamanagement.common.*;
import com.tecsun.sisp.fakamanagement.common.upload.UploadUtil;
import com.tecsun.sisp.fakamanagement.modules.controller.BaseController;
import com.tecsun.sisp.fakamanagement.modules.entity.param.store.BatchManageBean;
import com.tecsun.sisp.fakamanagement.modules.entity.result.card.CardInfoVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.common.DistinctAndBankVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.store.BatchManageVO;
import com.tecsun.sisp.fakamanagement.modules.service.impl.loginuser.LoginUserServiceImpl;
import com.tecsun.sisp.fakamanagement.modules.service.impl.store.BatchManageServiceImpl;
import com.tecsun.sisp.fakamanagement.modules.task.BatchDownloadThread;
import com.tecsun.sisp.fakamanagement.modules.util.ExcelUtils;
import com.tecsun.sisp.fakamanagement.outerface.cardservice.CardServiceUtils;
import com.tecsun.sisp.fakamanagement.common.utils.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 批次管理/批次数据下载管理 2018/01/23
 */
@Controller
@RequestMapping("/faka/batchManage")
public class BatchManageController extends BaseController{
    private static final Logger logger= Logger.getLogger(BatchManageController.class);

    private static ExecutorService singleExecutor = Executors.newSingleThreadExecutor();

    @Autowired
    private BatchManageServiceImpl batchManageService;
    @Autowired
    private LoginUserServiceImpl loginUserService;


    /**
     * 批次管理--查询批次信息
     * @param bean
     * @param req
     * @return
     */
    @RequestMapping(value="/getBatchInfo",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public Result getBatchInfo(@RequestBody BatchManageBean bean, HttpServletRequest req){
        try{
            String pageno=req.getParameter("pageno");
            String pagesize=req.getParameter("pagesize");
            int no=Integer.parseInt(String.valueOf(CommUtil.isEmptyStr(pageno)?1:pageno));
            int size=Integer.parseInt(String.valueOf(CommUtil.isEmptyStr(pagesize)?10:pagesize));
            //查询结束日期延后一天
            if(bean.getEndDate()!=null && !"".equals(bean.getEndDate())){
                String endDate=bean.getEndDate();
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
                Date d=sdf.parse(endDate);
                Calendar calendar=Calendar.getInstance();
                calendar.setTime(d);
                calendar.add(Calendar.DAY_OF_MONTH,1);
                Date d1=calendar.getTime();
                String dd=sdf.format(d1);
                bean.setEndDate(dd);
            }

            //去除查询条件两端空格
            if (null!=bean.getBatchNo() && !"".equals(bean.getBatchNo())){
                bean.setBatchNo(bean.getBatchNo().trim());
            }

            Page<BatchManageVO> page=new Page<>(no,size);
            bean.setPage(page);
            List<BatchManageVO> list=batchManageService.getBatchInfos(bean);
            return ok(page.getCount(),"查询批次成功！",list);
        }catch(Exception e){
            logger.error("查询批次异常！",e);
            return error("查询批次出现异常！");
        }
    }

    /**
     * 批次管理--增加批次
     * @param bean
     * @return
     */
    @RequestMapping(value = "/addBatchInfo",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public Result addBatchInfo(@RequestBody BatchManageBean bean){
        String batchNo=bean.getBatchNo();
        try{
            if(batchNo==null || "".equals(batchNo))
                return error("批次号为空！");
            List<BatchManageVO> list=batchManageService.getBatchInfos(bean);
            if(list!=null && list.size()>0){
                return error("批次"+batchNo+"已存在！");
            }
            String user= Config.getInstance().get("card_user");
            String pwd=Config.getInstance().get("card_pwd");
            String operation="制卡统计数据查询";
            Integer pageNo=null;
            String param=batchManageService.getallDsjkParam(operation,user,pwd,batchNo,pageNo);
            String res= CardServiceUtils.allDsjk(param);
            res="<root>"+res+"</root>";
            Map<String,String> map= Dom4JUtil.readXmlToMap(res);
            String status=map.get("ERR");
            if("OK".equals(status)){
                BatchManageVO bvo=new BatchManageVO();
                bvo.setBatchNo(map.get("批次号"));
                bvo.setBatchNum(Integer.valueOf(map.get("批次人数")));
                bvo.setBatchSum(0);//实际下载量初始为0
                bvo.setFactoryId(map.get("卡商"));
                bvo.setBank(map.get("银行"));
                boolean b=batchManageService.addBatchInfo(bvo)==1?true:false;
                if(b){
                    //插入操作批次日志表
                    batchManageService.insertLog(bean.getUserid(),bvo.getId());
                    logger.info("增加批次"+batchNo+"成功！");
                    return ok("增加批次"+batchNo+"成功！");
                }else{
                    logger.error("增加批次"+batchNo+"失败！");
                    return error("增加批次"+batchNo+"失败！");
                }
            }else {
                logger.error("增加批次"+batchNo+"-调用接口错误！"+status);
                return error("增加批次"+batchNo+"-调用接口错误！"+status);
            }
        }catch (Exception e){
            logger.error("增加批次"+batchNo+"出现异常！",e);
            return error("增加批次"+batchNo+"出现异常！");
        }
    }

    /**
     * 批次数据下载管理--查询批次明细信息
     * @param bean
     * @param req
     * @return
     */
    @RequestMapping(value="/getBatchInfoDetail",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public Result getBatchInfoDetail(@RequestBody BatchManageBean bean, HttpServletRequest req){
        try{
            String pageno=req.getParameter("pageno");
            String pagesize=req.getParameter("pagesize");
            int no=Integer.parseInt(String.valueOf(CommUtil.isEmptyStr(pageno)?1:pageno));
            int size=Integer.parseInt(String.valueOf(CommUtil.isEmptyStr(pagesize)?10:pagesize));

            //去除查询条件两端空格
            if (null!=bean.getBatchNo() && !"".equals(bean.getBatchNo())){
                bean.setBatchNo(bean.getBatchNo().trim());
            }

            Page<BatchManageVO> page=new Page<>(no,size);
            bean.setPage(page);
            List<BatchManageVO> list=batchManageService.getBatchInfoDetail(bean);
            for(BatchManageVO vo:list){
                DistinctAndBankVO dvo=new DistinctAndBankVO();
                dvo.setCode(vo.getBank());
                List<DistinctAndBankVO> dlist=loginUserService.queryBankByCode(dvo);
                if(dlist.size()>0 && dlist.get(0).getName()!=null){
                    vo.setBankName(dlist.get(0).getName());
                }

            }
            return ok(page.getCount(),"查询批次明细成功！",list);
        }catch(Exception e){
            logger.error("查询批次明细异常！",e);
            return error("查询批次明细出现异常！");
        }
    }

    /**
     * 批次数据下载管理--下载批次明细信息
     * @param bean
     * @return
     */
    @RequestMapping(value="/downloadBatchInfoDetail",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public Result downloadBatchInfoDetail(@RequestBody BatchManageBean bean){
        try{
            String userid=bean.getUserid();
            Integer[] ids=bean.getIds();
            if(ids.length<1){
                return error("入参为空！");
            }else if(ids.length==1){
                BatchManageBean iBean=new BatchManageBean();
                iBean.setId(ids[0]);
                List<BatchManageVO> list=batchManageService.getBatchInfos(iBean);
                //已分拨或下载中或已下载
                if("01".equals(list.get(0).getFbStatus()) || "01,02".contains(list.get(0).getStatus()))
                    return error("批次下载中或者已下载或者已分拨！");

                BatchManageVO bvo=new BatchManageVO();
                bvo.setId(ids[0]);
                bvo.setStatus(Constants.BATCH_STATUS_01);//下载中
                //修改批次状态为下载中
                batchManageService.updateBatchStatus(bvo);
                String batchNumLimit=Config.getInstance().get("batchNum_download_limit");
                if(list.get(0).getBatchNum()>=Integer.valueOf(batchNumLimit)){
                    //新建线程
                    singleExecutor.execute(new BatchDownloadThread(userid,ids,batchManageService));
                }else{
                    Result r= batchManageService.handleBatchInfo(ids,userid,0);
                    //更新批次下载状态--成功或者失败
                    batchManageService.updateStatusByResult(r,userid,ids[0]);
                    return r;
                }

            }else{
                boolean flag=batchManageService.validateBatchStatus(ids);
                if(!flag)
                    return error("所选批次包含正在下载中或者已下载或者已分拨的批次！");

                //批次状态改为下载中
                batchManageService.allToDownloading(ids);
                //新建线程
                singleExecutor.execute(new BatchDownloadThread(userid,ids,batchManageService));
            }
            return ok("批次下载中...");
        }catch(Exception e){
            logger.error("下载批次明细异常！",e);
            return error("下载批次明细出现异常！");
        }
    }
    /**
     * 批次数据下载管理--下载批次明细信息 并且自动分拨  仅吉林人社可以
     * @param bean
     * @return
     */
    @RequestMapping(value="/downloadAndDispatchBatch",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public Result downloadAndDispatchBatch(@RequestBody BatchManageBean bean){
        try{
            logger.info("开始下载===");
            String userid=bean.getUserid();
            Integer[] ids=bean.getIds();
            //入参校验
            if("".equals(bean.getUserid()) || "null".equals(bean.getUserid())) {
                return result("300", "用户id不能为空");
            }
            if(ids.length<1){
                return error("批次id不能为空！");
            }else if(ids.length==1){
                BatchManageBean iBean=new BatchManageBean();
                iBean.setId(ids[0]);
                List<BatchManageVO> list=batchManageService.getBatchInfos(iBean);
                //已分拨或下载中或已下载
                /*if("01".equals(list.get(0).getFbStatus()) || "01,02".contains(list.get(0).getStatus())) {
                    return error("批次下载中或者已下载或者已分拨！");
                }*/
                if("01".equals(list.get(0).getStatus())) {
                    logger.info("批次下载中===");
                    return error("批次下载中！");
                }

                BatchManageVO bvo=new BatchManageVO();
                bvo.setId(ids[0]);
                bvo.setStatus(Constants.BATCH_STATUS_01);//下载中
                //修改批次状态为下载中
                batchManageService.updateBatchStatus(bvo);
                String batchNumLimit=Config.getInstance().get("batchNum_download_limit");
                if(list.get(0).getBatchNum()>=Integer.valueOf(batchNumLimit)){
                    //新建线程
                    singleExecutor.execute(new BatchDownloadThread(userid,ids,batchManageService));
                }else{
                    Result r= batchManageService.handleBatchInfo(ids,userid,0);
                    //更新批次下载状态--成功或者失败
                    batchManageService.updateStatusByResult(r,userid,ids[0]);
                    return r;
                }

            }else{
                boolean flag=batchManageService.validateBatchStatus(ids);
                if(!flag){
                    return error("所选批次包含正在下载中或者已下载或者已分拨的批次！");
                }
                //批次状态改为下载中
                batchManageService.allToDownloading(ids);
                //新建线程
                singleExecutor.execute(new BatchDownloadThread(userid,ids,batchManageService));
            }
            return ok("批次下载中...即将开始自动分拨");
        }catch(Exception e){
            logger.error("下载批次明细异常！",e);
            return error("下载批次明细出现异常！");
        }

    }

    /**
     * Excel导入批次卡数据
     * @param file excel文件
     * @param request
     * @return
     */
    @RequestMapping({"saveCardBatchDetails"})
    @ResponseBody
    public Result saveCardBatchDetails(@RequestParam("file") MultipartFile file,@RequestParam("userid")String userid, HttpServletRequest request, @Context HttpServletResponse response){
        logger.info("开始获取excle数据===");
        String name = "SaveCardBatchDetailERR_"+new SimpleDateFormat("yyyyMMddHHmmSS").format(new Date())+".xls";
        List<BatchManageVO> list = new ArrayList<>();
        List<Map<String, String>> lists = new ArrayList<>();
        HSSFWorkbook cwb = new HSSFWorkbook();
        HSSFSheet sheet = cwb.createSheet("错误信息");
        String[] headers = new String[]{"姓名","社会保障号码","社保卡号","批次","单位编号","单位名称","联系地址","联系电话","所属银行","所属卡商","所属城市","经办机构","装箱位置","错误信息"};
        ExcelUtils.addHeader(headers, sheet);
        try {
            String filepath = UploadUtil.getFile(request, "tempexcel");
            Workbook wb = ExcelUtils.getExcel(request.getSession().getServletContext().getRealPath("/") +"tempexcel/"+filepath);
            lists = ExcelUtils.EecelToList(wb);
            logger.info("获取excle数据成功===");
            int i = 0;
            for (Map<String, String> map : lists) {
                BatchManageVO vo = new BatchManageVO();
                vo.setIdcard(StringUtils.notNullToTrim(map.get("社会保障号码")));
                vo.setCardId(StringUtils.notNullToTrim(map.get("社保卡号")));
                vo.setName(StringUtils.notNullToTrim(map.get("姓名")));
                vo.setBatchNo(StringUtils.notNullToTrim(map.get("批次")));
                vo.setCompanyCode(StringUtils.notNullToTrim(map.get("单位编号")));
                vo.setCompanyName(StringUtils.notNullToTrim(map.get("单位名称")));
                vo.setAddress(StringUtils.notNullToTrim(map.get("联系地址")));
                vo.setPhone(StringUtils.notNullToTrim(map.get("联系电话")));
                vo.setBank(StringUtils.notNullToTrim(map.get("所属银行")));
                //vo.setBankacount(StringUtils.notNullToTrim(map.get("银行卡号")));
                vo.setKs(StringUtils.notNullToTrim(map.get("所属卡商")));
                vo.setCity(StringUtils.notNullToTrim(map.get("所属城市")));
                vo.setOrgId(StringUtils.notNullToTrim(map.get("经办机构")));
                vo.setKgzxwz(StringUtils.notNullToTrim(map.get("装箱位置")));

                list.add(vo);

                String re = checkCardInfoIsNull(vo);//判断是否为空
                if(!re.equals("")){
                    ExcelUtils.addData(i, 0, vo.getName(), sheet);
                    ExcelUtils.addData(i, 1, vo.getIdcard(), sheet);
                    ExcelUtils.addData(i, 2, vo.getCardId(), sheet);
                    ExcelUtils.addData(i, 3, vo.getBatchNo(), sheet);
                    ExcelUtils.addData(i, 4, vo.getCompanyCode(), sheet);
                    ExcelUtils.addData(i, 5, vo.getCompanyName(), sheet);
                    ExcelUtils.addData(i, 6, vo.getAddress(), sheet);
                    ExcelUtils.addData(i, 7, vo.getPhone(), sheet);
                    ExcelUtils.addData(i, 8, vo.getBank(), sheet);
                    ExcelUtils.addData(i, 9, vo.getKs(), sheet);
                    ExcelUtils.addData(i, 10, vo.getCity(), sheet);
                    ExcelUtils.addData(i, 11, vo.getOrgId(), sheet);
                    ExcelUtils.addData(i, 12, vo.getKgzxwz(), sheet);
                    ExcelUtils.addData(i, 13,re , sheet);
                    i++;
                    continue;
                }

            }
            if(list.size()>0){
                logger.info("开始导入批次===");
                batchManageService.saveCardBatchDetails(list);
                logger.info("导入批次成功===");
            }
            String path = Config.getInstance().get("exp.temp.path");
            File refile = new File(path);
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
        if(lists.size() == list.size()){
            return ok("导入成功"+list.size()+";失败"+(lists.size()-list.size()));
        }else{
            return ok("导入成功"+list.size()+";失败"+(lists.size()-list.size()),name);
        }
    }

    /**
     * 处理Excel的数据 将一行转换为一个map 将map存放在list里面
     * @param wb Excel工作蒲
     * @return
     */
    /*public static List<Map<String, String>> EecelToList(Workbook wb) {
        List<Map<String, String>> list = new ArrayList<>();
        for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {//获取每个sheet
            Sheet sheet = wb.getSheetAt(numSheet);
            if (sheet == null) {
                continue;
            }
            Row headerRow = sheet.getRow(0);//获取表头
            if (headerRow == null) {
                continue;
            }
            String[] headers = new String[headerRow.getLastCellNum()];//创建一个数组存放表头 用于map的key
            for (int i = 0; i <headerRow.getLastCellNum(); i++) {
                headers[i] = ExcelUtils.getValue(headerRow.getCell(i));
            }
            for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {//获取数据行
                Map<String, String> map = new HashMap<>();
                Row row = sheet.getRow(rowNum);
                if (row == null) {
                    continue;
                }
                for (int i = 0; i <row.getLastCellNum(); i++) {
                    map.put(headers[i], ExcelUtils.getValue(row.getCell(i)));//将数据存放到map里面 key为表头的值
                }
                list.add(map);
            }
        }
        return list;
    }*/

    /**
     * 判断必要数据值是否为空
     * @param vo
     * @return
     */
    private String checkCardInfoIsNull(BatchManageVO vo){
        if(null==vo.getIdcard()||vo.getIdcard().equals("")){
            return "身份证号码为空";
        }
        if(null==vo.getCardId()||vo.getCardId().equals("")){
            return "社会保障卡卡号为空";
        }
        if(null==vo.getName()||vo.getName().equals("")){
            return "姓名为空";
        }
        if(null==vo.getBatchNo()||vo.getBatchNo().equals("")){
            return "批次号为空";
        }
        if(null==vo.getPhone()||vo.getPhone().equals("")){
            return "联系电话为空";
        }
        if(null==vo.getKgzxwz()||vo.getKgzxwz().equals("")){
            return "装箱位置为空";
        }
		if(null==vo.getBank()||vo.getBank().equals("")){
			return "所属银行为空";
		}
		if(null==vo.getCity()||vo.getCity().equals("")){
			return "城市代码为空";
		}
		if(null==vo.getOrgId()||vo.getOrgId().equals("")){
			return "经办机构为空";
		}
        return "";
    }


    public static void main(String[] args){
        String kgZxwz = "29211-5-3-1";
        String batchNo = kgZxwz.split("-")[0];
        String bin = kgZxwz.split("-")[1];
        String box = kgZxwz.split("-")[2];
        String csid = kgZxwz.split("-")[3];

        System.out.println(kgZxwz);
        System.out.println(batchNo);
        System.out.println(bin);
        System.out.println(box);
        System.out.println(csid);
    }

}
