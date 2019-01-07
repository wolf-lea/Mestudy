package com.tecsun.sisp.fakamanagement.modules.service.impl.store;

import com.tecsun.sisp.fakamanagement.common.Config;
import com.tecsun.sisp.fakamanagement.common.Constants;
import com.tecsun.sisp.fakamanagement.common.Result;
import com.tecsun.sisp.fakamanagement.modules.controller.BaseController;
import com.tecsun.sisp.fakamanagement.modules.entity.param.store.BatchManageBean;
import com.tecsun.sisp.fakamanagement.modules.entity.param.store.DispatchBean;
import com.tecsun.sisp.fakamanagement.modules.entity.result.card.CardInfoVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.log.BatchLogVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.store.BatchManageVO;
import com.tecsun.sisp.fakamanagement.modules.service.BaseService;
import com.tecsun.sisp.fakamanagement.modules.service.impl.log.LogInfoServiceImpl;
import com.tecsun.sisp.fakamanagement.outerface.cardservice.CardServiceUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Description:
 * @author: liang
 * @date 2018/1/24 14:16
 **/
@Service("batchManageService")
public class BatchManageServiceImpl extends BaseService{

    /**
     * mapper文件namespace属性值
     */
    public static final String NAMESPACE = "com.tecsun.sisp.fakamanagement.modules.service.impl.store.BatchManageServiceImpl.";

    private static final Logger logger=Logger.getLogger(BatchManageServiceImpl.class);
    @Autowired
    private LogInfoServiceImpl logInfoService;
    @Autowired
    private DispatchServiceImpl dispatchService;

    public List<BatchManageVO> getBatchInfos(BatchManageBean bean){
       return this.getFakaSqlSessionTemplate().selectList(NAMESPACE + "getBatchInfos",bean);
    }

    public int addBatchInfo(BatchManageVO vo){
        return this.getFakaSqlSessionTemplate().insert(NAMESPACE + "addBatchInfo",vo);
    }

    public List<BatchManageVO> getBatchInfoDetail(BatchManageBean bean){
        return this.getFakaSqlSessionTemplate().selectList(NAMESPACE + "getBatchInfoDetail",bean);
    }

    public int updateBatchStatus(BatchManageVO vo){
        return this.getFakaSqlSessionTemplate().update(NAMESPACE + "updateBatchStatus",vo);
    }

    public int addBatchBinBox(BatchManageVO vo){
        return this.getFakaSqlSessionTemplate().insert(NAMESPACE + "addBatchBinBox",vo);
    }

    public int addBatchDetail(BatchManageVO vo){
        return this.getFakaSqlSessionTemplate().insert(NAMESPACE + "addBatchDetail",vo);
    }

    //查询箱盒信息
    public List<BatchManageVO> getBatchidBinBoxInfo(BatchManageVO vo){
        return this.getFakaSqlSessionTemplate().selectList(NAMESPACE + "getBatchidBinBoxInfo",vo);
    }

    //根据身份证号查询卡信息
    public List<BatchManageVO> getCardDetailByIdcard(BatchManageVO vo){
        return this.getFakaSqlSessionTemplate().selectList(NAMESPACE + "getCardDetailByIdcard",vo);
    }
    public List<BatchManageVO> getCardInfoByIdcard(BatchManageVO vo){
        return this.getFakaSqlSessionTemplate().selectList(NAMESPACE + "getCardInfoByIdcard",vo);
    }

    //二次入库-新增卡详细信息
    public int addCardInfoDetail(BatchManageVO vo){
        return this.getFakaSqlSessionTemplate().insert(NAMESPACE + "addCardInfoDetail",vo);
    }
    //二次入库-新增卡位置信息
    public int addCardStoreDetail(BatchManageVO vo){
        return this.getFakaSqlSessionTemplate().insert(NAMESPACE + "addCardStoreDetail",vo);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 获取调用批次信息接口的参数
     * @param operation
     * @param user
     * @param pwd
     * @param batchNo
     * @param pageNo
     * @return
     */
    public String getallDsjkParam(String operation,String user,String pwd,String batchNo,Integer pageNo){
        StringBuffer sbf=new StringBuffer();
        if(pageNo==null){
            //制卡统计数据查询
            sbf.append("<操作*>"+operation+"</操作*><用户名*>"+user+"</用户名*>" +
                    "<密码*>"+pwd+"</密码*><批次号*>"+batchNo+"</批次号*>");
        }else {
            //制卡明细数据查询(每页100条数据，页号从1开始)
            sbf.append("<操作*>"+operation+"</操作*><用户名*>"+user+"</用户名*>" +
                    "<密码*>"+pwd+"</密码*><批次号*>"+batchNo+"</批次号*><页号*>"+pageNo+"</页号*>");
        }
        return sbf.toString();
    }

    /**
     * 下载批次数据
     * @param ids
     * @param userid
     * @param i
     * @return
     */
    @Transactional("faka")
    public Result handleBatchInfo(Integer[] ids,String userid,int i){
        Result r=new Result();
        try{

            Integer batchId=ids[i];
            BatchManageBean iBean=new BatchManageBean();
            iBean.setId(batchId);
            List<BatchManageVO> list=this.getBatchInfos(iBean);

            Integer count=list.get(0).getBatchNum();
            String batchNo=list.get(0).getBatchNo();

            Integer pageCount=count/100;
            if(pageCount*100<count){
                pageCount+=1;
            }

            String user= Config.getInstance().get("card_user");
            String pwd=Config.getInstance().get("card_pwd");
            String operation="制卡明细数据查询";

            Integer successNum=0;
            BatchManageVO bvo=null;
            BatchManageVO dvo=null;
            BatchManageVO cvo=null;
            for(int pageNo=0;pageNo<pageCount;pageNo++){
                String param=this.getallDsjkParam(operation,user,pwd,batchNo,pageNo+1);
                String res= CardServiceUtils.allDsjk(param);
                String status=res.substring(res.indexOf("<ERR>")+5,res.indexOf("</ERR>"));
                if("OK".equals(status)){
                    String[] result=res.split("\n");
                    Integer preBatchBinBoxId=0;
                    for(int n=2;n<result.length;n++){
                        String[] batchInfo=result[n].split(",");
                        //序号,社保卡号,身份证号,姓名,批次号,装箱位置,单位编号,单位名称,联系电话,联系手机,通讯地址,所属城市,所属银行,经办机构,有效标志
                        if(batchInfo[5]==null || "".equals(batchInfo[5]))
                            continue;

                        //插入批次箱盒表
                        bvo=new BatchManageVO();
                        String bin=batchInfo[5].split("-")[1];
                        String box=batchInfo[5].split("-")[2];
                        bvo.setBatchId(batchId);
                        bvo.setBin(bin);
                        bvo.setBox(box);
                        List<BatchManageVO> list1=this.getBatchidBinBoxInfo(bvo);
                        //判断箱盒是否存在
                        if(list1!=null && list1.size()>0){
                            preBatchBinBoxId=list1.get(0).getId();
                            System.out.println("箱盒已存在===");

                            //如果箱盒已存在且已入库，属于二次入库，把卡信息插入card_info表
                            if ("01".equals(list1.get(0).getStatus())){
                                //判断卡信息是否存在card_info表
                                bvo.setIdcard(batchInfo[2]);
                                List<BatchManageVO> cardList = this.getCardInfoByIdcard(bvo);
                                if(cardList!=null && cardList.size()>0){
                                    System.out.println("卡信息已存在card_info表===");
                                }else {
                                    logger.info("二次入库数据，身份证号码为：" + batchInfo[2]);
                                    //插入卡详细信息
                                    cvo = new BatchManageVO();
                                    cvo.setName(batchInfo[3]);
                                    cvo.setIdcard(batchInfo[2]);
                                    cvo.setCardId(batchInfo[1]);
                                    cvo.setBatchNo(batchInfo[4]);
                                    cvo.setXtzxwz(batchInfo[5]);
                                    cvo.setCompanyCode(batchInfo[6]);
                                    cvo.setCompanyName(batchInfo[7]);
                                    cvo.setPhone(batchInfo[8]);
                                    cvo.setAddress(batchInfo[10]);
                                    cvo.setKgzxwz(batchInfo[5]);
                                    cvo.setRkwd(list1.get(0).getRkwd());
                                    cvo.setBank(batchInfo[12]);
                                    cvo.setCity(batchInfo[11]);
                                    this.addCardInfoDetail(dvo);//二次入库-新增卡详细信息
                                    //插入卡位置信息
                                    List<BatchManageVO> ciidList = this.getCardInfoByIdcard(cvo);//获取卡ID
                                    cvo.setCiid(ciidList.get(0).getId());
                                    String csid=batchInfo[5].split("-")[3]; //csid,ciid,ccid,bin,box
                                    cvo.setCsid(csid);
                                    cvo.setCcid(list1.get(0).getCcid());
                                    cvo.setBin(bin);
                                    cvo.setBox(box);
                                    this.addCardStoreDetail(dvo);//二次入库-新增卡位置信息
                                }
                            }

                        }else{
                            String qy=batchInfo[13];
                            //2202 00 00
                            if(qy.length()==8){
                                String qy1=qy.substring(0,4);
                                String qy2=qy.substring(6);
                                qy=qy1+qy2;
                            }else if(qy.length()>8){
                                qy=qy.substring(0,8);
                                String qy1=qy.substring(0,4);
                                String qy2=qy.substring(6);
                                qy=qy1+qy2;
                            }
                            bvo.setQy(qy);
                            bvo.setbStatus(Constants.BATCH_BIN_BOX_STATUS_00);//未入库
                            this.addBatchBinBox(bvo);
                            preBatchBinBoxId=bvo.getId();
                            System.out.println("插入新箱盒===");
                        }

                        //判断卡信息是否存在card_batch_detail表
                        bvo.setIdcard(batchInfo[2]);
                        List<BatchManageVO> cardList = this.getCardDetailByIdcard(bvo);
                        if(cardList!=null && cardList.size()>0){
                            //批次明细实际下载数量+1
                            successNum++;
                            System.out.println("卡信息已存在card_batch_detail表===");
                        }else {
                            //插入批次明细表
                            dvo = new BatchManageVO();
                            dvo.setBatchId(batchId);
                            dvo.setBatchBinBoxId(preBatchBinBoxId);
                            dvo.setName(batchInfo[3]);
                            dvo.setIdcard(batchInfo[2]);
                            dvo.setCardId(batchInfo[1]);
                            dvo.setBatchNo(batchInfo[4]);
                            dvo.setKgzxwz(batchInfo[5]);
                            dvo.setXtzxwz(batchInfo[5]);
                            dvo.setCompanyCode(batchInfo[6]);
                            dvo.setCompanyName(batchInfo[7]);
                            dvo.setPhone(batchInfo[8]);
                            dvo.setMobile(batchInfo[9]);
                            dvo.setAddress(batchInfo[10]);
                            dvo.setBank(batchInfo[12]);
                            dvo.setCity(batchInfo[11]);
                            dvo.setOrgId(batchInfo[13]);
                            dvo.setFlag(batchInfo[14]);
                            this.addBatchDetail(dvo);

                            //批次明细实际下载数量+1
                            successNum++;
                            System.out.println("插入卡信息===="+successNum);
                        }
                    }
                }else {
                    logger.error("下载批次明细信息-调用接口错误！"+status);
                    //插入操作批次日志表
                    this.insertLog(userid,batchId);
                    return r.error("下载批次明细信息-调用接口错误！"+status);
                }
            }

            if(successNum>0){
                r.setStatusCode(BaseController.RESULT_MESSAGE_SUCCESS);
                r.setMessage("下载批次明细信息成功！");
                r.setTotal(successNum);
            }else{
                r.setStatusCode(BaseController.RESULT_MESSAGE_ERROR);
                r.setMessage("下载批次明细信息失败！批次人数："+count+"，成功下载人数：0");
            }
            //插入操作批次日志表
            this.insertLog(userid,batchId);

            return r;

        }catch (Exception e){
            logger.error("下载批次明细信息-调用接口异常！",e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return r.error("下载批次明细信息失败，原因："+e.getMessage());
        }
    }

    /**
     * 验证批次状态--不能为已分拨或下载中或已下载
     * @param ids
     * @return
     */
    public boolean validateBatchStatus(Integer[] ids){
        boolean flag=true;
        for (int i = 0; i <ids.length ; i++) {
            BatchManageBean iBean=new BatchManageBean();
            iBean.setId(ids[i]);
            List<BatchManageVO> list=this.getBatchInfos(iBean);
            //已分拨或下载中或已下载
            if("01".equals(list.get(0).getFbStatus()) || "01,02".contains(list.get(0).getStatus())){
                flag=false;
            }
        }
        return flag;
    }

    /**
     * 修改所选批次为下载中
     * @param ids
     */
    public void allToDownloading(Integer[] ids){
        for (int i = 0; i <ids.length ; i++) {
            BatchManageBean iBean=new BatchManageBean();
            iBean.setId(ids[i]);
            List<BatchManageVO> list=this.getBatchInfos(iBean);
            BatchManageVO bvo=new BatchManageVO();
            bvo.setId(ids[i]);
            bvo.setStatus(Constants.BATCH_STATUS_01);//下载中
            this.updateBatchStatus(bvo);
        }
    }

    /**
     * 更新批次下载状态--成功或者失败
     * @param r
     * @param batchId
     */
    public void updateStatusByResult(Result r,String userid,Integer batchId){
        BatchManageVO vo=new BatchManageVO();
        vo.setId(batchId);
        if(BaseController.RESULT_MESSAGE_SUCCESS.equals(r.getStatusCode())){
            //下载成功
            vo.setStatus(Constants.BATCH_STATUS_02);
            vo.setBatchSum((int)r.getTotal());
            this.updateBatchStatus(vo);
            try {
                DispatchBean dispatchBean=new DispatchBean();
                dispatchBean.setUserid(userid);
                dispatchBean.setId(Collections.singletonList(batchId));
                int res = dispatchService.selectDispatch(dispatchBean);
                if(res > 0){
                    logger.info("自动分拨成功！");
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("自动分拨方法出现异常");
            }
            //logger.info("自动分拨失败！");

        }else {
            //下载失败
            vo.setStatus(Constants.BATCH_STATUS_03);
            vo.setMessage(r.getMessage());
            this.updateBatchStatus(vo);
        }
    }

    /**
     * 插入操作批次日志表
     */
    public void insertLog(String userid,Integer batchId){
        BatchLogVO lvo=new BatchLogVO();
        lvo.setUserId(Integer.valueOf(userid));
        lvo.setBatchId(batchId);
        lvo.setBusType(Constants.BATCH_LOG_STATUS_01);//批次下载
        logInfoService.addBatchLog(lvo);
    }


    /**
     * Excel导入批次卡数据
     * @param list
     * @return
     */
    @Transactional("faka")
    public Result saveCardBatchDetails(List<BatchManageVO> list){
        Result r=new Result();
        try{
            Integer successNum = 0;
            BatchManageBean bean = new BatchManageBean();
            for(BatchManageVO vo : list){
                //截取"_"前面的字符
                vo.setCity(vo.getCity().substring(0, vo.getCity().indexOf("_")));
                vo.setBank(vo.getBank().substring(0, vo.getBank().indexOf("_")));
                vo.setKs(vo.getKs().substring(0, vo.getKs().indexOf("_")));
                vo.setOrgId(vo.getOrgId().substring(0, vo.getOrgId().indexOf("_")));

                //插入批次
                bean.setBatchNo(vo.getBatchNo());
                List<BatchManageVO> batchList = this.getBatchInfos(bean);
                //判断批次是否存在
                if(batchList!=null && batchList.size()>0){
                    vo.setBatchId(batchList.get(0).getId());
                    System.out.println("批次已存在===");
                }else {
                    vo.setBatchNum(list.size());
                    vo.setBatchSum(list.size());
                    this.addBatchInfoByExcle(vo);//新增批次
                    vo.setBatchId(vo.getId());
                    System.out.println("插入新批次===");
                }

                //插入批次箱盒表
                String bin = vo.getKgzxwz().split("-")[1];
                String box = vo.getKgzxwz().split("-")[2];
                vo.setBin(bin);
                vo.setBox(box);
                List<BatchManageVO> list1 = this.getBatchidBinBoxInfo(vo);
                //判断箱盒是否存在
                if(list1!=null && list1.size()>0){
                    vo.setBatchBinBoxId(list1.get(0).getId());
                    System.out.println("箱盒已存在===");

                    //如果箱盒已存在且已入库，属于二次入库，把卡信息插入card_info表
                    if ("01".equals(list1.get(0).getStatus())){
                        //判断卡信息是否存在card_info表
                        List<BatchManageVO> cardList = this.getCardInfoByIdcard(vo);
                        if(cardList!=null && cardList.size()>0){
                            System.out.println("卡信息已存在card_info表===");
                        }else {
                            logger.info("二次入库数据，身份证号码为：" + vo.getIdcard());
                            //插入卡详细信息
                            vo.setXtzxwz(vo.getKgzxwz());
                            vo.setRkwd(list1.get(0).getRkwd());
                            this.addCardInfoDetail(vo);//二次入库-新增卡详细信息
                            //插入卡位置信息
                            //List<BatchManageVO> ciidList = this.getCardInfoByIdcard(cvo);//获取卡ID
                            vo.setCiid(vo.getId());
                            String csid = vo.getKgzxwz().split("-")[3];
                            vo.setCsid(csid);
                            vo.setCcid(list1.get(0).getCcid());
                            this.addCardStoreDetail(vo);//二次入库-新增卡位置信息
                        }
                    }
                }else{
                    String qy = vo.getOrgId();
                    //2202 00 00
                    if(qy.length()==8){
                        String qy1=qy.substring(0,4);
                        String qy2=qy.substring(6);
                        qy=qy1+qy2;
                    }else if(qy.length()>8){
                        qy=qy.substring(0,8);
                        String qy1=qy.substring(0,4);
                        String qy2=qy.substring(6);
                        qy=qy1+qy2;
                    }
                    vo.setQy(qy);
                    vo.setbStatus(Constants.BATCH_BIN_BOX_STATUS_00);//未入库
                    this.addBatchBinBox(vo);
                    vo.setBatchBinBoxId(vo.getId());
                    System.out.println("插入新箱盒===");
                }

                //判断卡信息是否存在card_batch_detail表
                List<BatchManageVO> cardList = this.getCardDetailByIdcard(vo);
                if(cardList!=null && cardList.size()>0){
                    //批次明细实际下载数量+1
                    successNum++;
                    System.out.println("卡信息已存在card_batch_detail表===");
                }else {
                    vo.setXtzxwz(vo.getKgzxwz());
                    vo.setMobile(vo.getPhone());
                    //插入批次明细表
                    this.addBatchDetail(vo);
                    //批次明细实际下载数量+1
                    successNum++;
                    System.out.println("插入卡信息===="+successNum);
                }
            }

            if(successNum>0){
                r.setStatusCode(BaseController.RESULT_MESSAGE_SUCCESS);
                r.setMessage("导入批次明细信息成功！");
                r.setTotal(successNum);
            }else{
                r.setStatusCode(BaseController.RESULT_MESSAGE_ERROR);
                r.setMessage("导入批次明细信息失败！批次人数："+list.size()+"，成功导入人数：0");
            }
            return r;
        }catch (Exception e){
            logger.error("导入批次明细信息-调用接口异常！",e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return r.error("导入批次明细信息失败，原因："+e.getMessage());
        }
    }

    //导入批次-新增批次信息
    public int addBatchInfoByExcle(BatchManageVO vo){
        return this.getFakaSqlSessionTemplate().insert("addBatchInfoByExcle",vo);
    }

}
