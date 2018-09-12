package com.tecsun.sisp.fakamanagement.modules.controller.store;

import com.tecsun.sisp.fakamanagement.common.Page;
import com.tecsun.sisp.fakamanagement.common.Result;
import com.tecsun.sisp.fakamanagement.modules.controller.BaseController;
import com.tecsun.sisp.fakamanagement.modules.entity.param.store.StorageBean;
import com.tecsun.sisp.fakamanagement.modules.entity.param.store.StorageListBean;
import com.tecsun.sisp.fakamanagement.modules.entity.result.common.DistinctAndBankVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.store.StorageVo;
import com.tecsun.sisp.fakamanagement.modules.service.impl.loginuser.LoginUserServiceImpl;
import com.tecsun.sisp.fakamanagement.modules.service.impl.store.StorageServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * 社保卡入库管理
 * Created by xumaohao on 2018/1/24.
 */
@Controller
@RequestMapping(value = "/faka/cardStorage")
public class StorageController extends BaseController {
    public final static Logger logger = Logger.getLogger(StorageController.class);

    @Autowired
    StorageServiceImpl storageService;
    @Autowired
    private LoginUserServiceImpl loginUserService;

    /**
     * 查询网点对应银行及区域
     * @param bean
     * @return
     */
    public StorageBean queryInfo(StorageBean bean) {
        //通过用户ID查询发卡网点
        String fkwd = loginUserService.getFkwd(bean.getUserid());
        if(StringUtils.isBlank(fkwd)) {
            return bean;
        }
        bean.setFkwd(fkwd);
        //通过发卡网点查询银行编码
        String bank = loginUserService.getBankCode(fkwd);
        //如查出来的银行信息为空 则可能发卡网点即为银行 查询是否存在此银行
        if(StringUtils.isBlank(bank)) {
            DistinctAndBankVO vo = new DistinctAndBankVO();
            vo.setCode(fkwd);
            List<DistinctAndBankVO> list = loginUserService.queryBankByCode(vo);
            if (list != null && list.size() > 0) {
                bank = fkwd;
            }
        }
        bean.setBank(bank);
        //通过发卡网点查询区域编码
        String qy = loginUserService.getReginalCode(fkwd);
        bean.setQy(loginUserService.queryNextQY(qy));
        return bean;
    }

    /**
     * （未）入库信息查询
     * @param bean
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/queryStorage", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result queryStorage(@RequestBody StorageBean bean, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        //获取路径的页数页码 默认一页十条
        String pageno = StringUtils.defaultIfBlank(request.getParameter("pageno"), "1");
        String pagesize = StringUtils.defaultIfBlank(request.getParameter("pagesize"), "10");
        Page<StorageVo> page = null;
        try {
            //去除查询条件两端空格
            if (null!=bean.getBatchNo() && !"".equals(bean.getBatchNo())){
                bean.setBatchNo(bean.getBatchNo().trim());
            }

            //根据id查询银行及区域信息
            bean = queryInfo(bean);
            //判断信息完整
            if(StringUtils.isBlank(bean.getBank())) return result("300","查无服务银行信息");
            if(bean.getQy() == null || bean.getQy().size() == 0) return result("300","查无对应区域信息");
            //配置页数页码
            page = new Page<>(Integer.parseInt(pageno), Integer.parseInt(pagesize));
            //查询数据
            page = storageService.queryStorage(page, bean);
            logger.info("查询成功===");
            return ok("查询成功", page);
        } catch (Exception e) {
            logger.error("查询失败==="+e);
    }
        return error("查询失败");
    }

    /**
     * 社保卡入库
     * @param storage
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/selectStorage", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result selectStorage(@RequestBody StorageListBean storage, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        List<StorageBean> list;
        try {
            logger.info("开始入库===");
            //将入参list拆分多次入库
            StorageBean bean = new StorageBean();
            bean.setUserid(storage.getUserid());
            bean.setMethod(storage.getMethod());
            bean.setCcaid(storage.getCcaid());

            //根据id查询银行及区域信息
            bean = queryInfo(bean);
            //判断信息完整
            if(StringUtils.isBlank(bean.getBank())) return ok("查无服务银行信息");
            if(bean.getQy() == null || bean.getQy().size() == 0) return result("300", "查无对应区域信息");

            int count = 0;
            for (int i = 0; i <  storage.getData().size(); i++) {
                //按批次入柜list数据量过大，分解为按盒入柜
                if ("02".equals(storage.getMethod())){
                    bean.setBatchNo(storage.getData().get(i).getBatchNo());
                    list = storageService.selectAllBoxByBatchNo(bean);
                    for (StorageBean boxVO : list){
                        bean.setMethod("00");//按盒入柜
                        bean.setBatchNo(boxVO.getBatchNo());
                        bean.setBin(boxVO.getBin());
                        bean.setBox(boxVO.getBox());
                        count = count + storageService.selectStorage(bean);
                    }
                }else {
                    //按箱、按盒入柜
                    bean.setBatchNo(storage.getData().get(i).getBatchNo());
                    bean.setBin(storage.getData().get(i).getBin());
                    bean.setBox(storage.getData().get(i).getBox());
                    count = count + storageService.selectStorage(bean);
                }
            }
            logger.info("入库完成===");
            if(count == 0){
                return ok("入库成功");
            } else {
                int res = storage.getData().size() - count;
                return ok("入库结果：" + res +"成功，" + count + "失败");
            }
        } catch (Exception e) {
            logger.error("入库失败==="+e);
        }

        return error("入库失败");
    }

    /**
     * 已入库数据统计
     * @param bean
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/storageStatistics", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result storageStatistics(@RequestBody StorageBean bean, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        //获取路径的页数页码 默认一页十条
        String pageno = StringUtils.defaultIfBlank(request.getParameter("pageno"), "1");
        String pagesize = StringUtils.defaultIfBlank(request.getParameter("pagesize"), "10");
        Page<StorageVo> page = null;
        try {
            //去除查询条件两端空格
            if (null!=bean.getBatchNo() && !"".equals(bean.getBatchNo())){
                bean.setBatchNo(bean.getBatchNo().trim());
            }

            //根据id查询银行及区域信息
            bean = queryInfo(bean);
            //判断信息完整
            if(StringUtils.isBlank(bean.getBank())) return ok("查无服务银行信息");
            if(bean.getQy() == null || bean.getQy().size() == 0) return result("300", "查无对应区域信息");

            //配置页数页码
            page = new Page<>(Integer.parseInt(pageno),Integer.parseInt(pagesize));
            //查询数据
            page = storageService.storageStatistics(page, bean);
            logger.info("查询成功===");
            return ok("查询成功",page);
        } catch (Exception e) {
            logger.error("查询失败==="+e);
        }
        return error("查询失败");
    }

    /**
     * 已入库数据追回
     * @param bean
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/storageRecover", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result storageRecover(@RequestBody StorageBean bean, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        try {
            logger.info("开始追回===");
            //根据id查询银行及区域信息
            bean = queryInfo(bean);
            //判断信息完整
            if(StringUtils.isBlank(bean.getBank())) return ok("查无服务银行信息");
            if(bean.getQy() == null || bean.getQy().size() == 0) return result("300","查无对应区域信息");

            //判断是否存在已发卡数据
            /*int num = storageService.storageIsGet(bean);
            if(num > 0) {
                return error("追回失败:该盒卡已在其他网点发卡");
            }*/
            //追回
            //int res = storageService.storageRecover(bean);
            if(storageService.storageRecover(bean) > 0){
                logger.info("追回成功===");
                return ok("追回成功，并已入库！");
            }
        } catch (Exception e) {
            logger.error("已入库数据追回失败==="+e);
        }
        return error("追回失败");
    }

    /**
     * 获取筛选条件
     * @param bean
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/storageCondition", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result storageCondition(@RequestBody StorageBean bean, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        //入参校验
        if(StringUtils.isBlank(bean.getUserid())) return result("300", "userid不能为空");
        if(StringUtils.isBlank(bean.getMethod())) return result("300", "查询方式不能为空");
        if(StringUtils.isBlank(bean.getBatchNo())) return result("300", "批次号不能为空");
        try {
            //去除查询条件两端空格
            if (null!=bean.getBatchNo() && !"".equals(bean.getBatchNo())){
                bean.setBatchNo(bean.getBatchNo().trim());
            }

            //根据id查询银行及区域信息
            bean = queryInfo(bean);
            //判断信息完整
            if(StringUtils.isBlank(bean.getBank())) return ok("查无服务银行信息");
            if(bean.getQy() == null || bean.getQy().size() == 0) return result("300", "查无对应区域信息");

            //查询数据
            List<String> list = storageService.storageCondition(bean);
            logger.info("查询成功===");
            return ok("查询成功", list);
        } catch (Exception e) {
            logger.error("获取帅选条件查询失败==="+e);
        }
        return error("查询失败");
    }

    /**
     * 入库数据导出
     * @param bean
     * @param response
     * @return
     */
    @RequestMapping(value = "/exportStorage", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Result exportStorage(StorageBean bean ,HttpServletResponse response){
        try {
            //根据id查询银行及区域信息
            bean = queryInfo(bean);
            //判断信息完整
            if(StringUtils.isBlank(bean.getBank())) return ok("查无服务银行信息");
            if(bean.getQy() == null || bean.getQy().size() == 0) return result("300", "查无对应区域信息");

            boolean ret = storageService.exportStorage(bean, response);
            if(ret){
                logger.info("导出成功===");
                return ok("导出成功");
            }
        } catch (Exception e) {
            logger.error("导出失败==="+e);
        }
        return error("导出失败");
    }
}
