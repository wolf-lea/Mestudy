package com.tecsun.sisp.fakamanagement.modules.service.impl.store;

import com.tecsun.sisp.fakamanagement.common.JsonHelper;
import com.tecsun.sisp.fakamanagement.common.Page;
import com.tecsun.sisp.fakamanagement.modules.entity.param.store.StorageBean;
import com.tecsun.sisp.fakamanagement.modules.entity.result.common.DistinctAndBankVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.store.StorageInfoVo;
import com.tecsun.sisp.fakamanagement.modules.entity.result.store.StorageVo;
import com.tecsun.sisp.fakamanagement.modules.service.BaseService;
import jxl.Workbook;
import jxl.write.*;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service("storageServiceImpl")
public class StorageServiceImpl extends BaseService {
	
	//mapper文件namespace属性值
	private final static String NAMESPACE="com.tecsun.sisp.fakamanagement.modules.service.impl.store.StorageServiceImpl.";

	private final static String packageName="com.tecsun.sisp.fakamanagement.modules.service.impl.loginuser.LoginUserServiceImpl.";

	//日志
	public final static Logger logger = Logger.getLogger(DispatchServiceImpl.class);

	//（未）入库信息查询
	public Page<StorageVo> queryStorage(Page<StorageVo> page,StorageBean bean) {
		//设置查询的页数页码
		bean.setPage(page);

        //（德生宝入库，按盒入柜）当身份证不为空时，通过单张卡查询库存信息
        if (bean.getIdcard() != null && !"".equals(bean.getIdcard())) {
            logger.info("开始查询库存信息，身份证号为："+bean.getIdcard());
            StorageBean info = this.getFakaSqlSessionTemplate().selectOne(NAMESPACE + "queryStorageByPerson", bean);
            logger.info("查询库存信息成功===");
            if (info != null) {
                bean.setBatchNo(info.getBatchNo());
                bean.setBin(info.getBin());
                bean.setBox(info.getBox());
            }else {
                return page;
            }
        }

		//查询数据库
        logger.info("查询入库信息===");
		List<StorageVo> list = this.getFakaSqlSessionTemplate().selectList(NAMESPACE + "queryStorage", bean);
		//当查询方式为空的时候需要显示银行跟区域 所以需要转换  当其他方式时 不显示 为节省查询时间 不加转换
		//if(StringUtils.isBlank(bean.getMethod())){
        if (bean.getMethod() != null && !"".equals(bean.getMethod())){
			if(list != null && list.size() > 0) {
                logger.info("转换银行、区域、网点名称、入库状态===");
				//查询银行信息
				List<DistinctAndBankVO> bank = this.getUserSqlSessionTemplate().selectList(packageName + "queryBankInfo");
				//查询区域信息
//			List<DistinctAndBankVO> distinct = this.getUserSqlSessionTemplate().selectList(packageName + "queryAllDistinct");
				//进行编码转换
				for (int i = 0; i < list.size(); i++) {
					if (bank != null && bank.size() > 0) {
						//将查询结果的银行编码转换为中文
						for (int j = 0; j < bank.size(); j++) {
							if (list.get(i).getBank().equals(bank.get(j).getCode())) {
								list.get(i).setBank(bank.get(j).getName());
								break;
							}
						}
					}
					//将区域转换中文（由于一次性查询所有区域耗时太久，故分开转换）
					DistinctAndBankVO vo = new DistinctAndBankVO();
					vo.setCode(list.get(i).getQy());
					List<DistinctAndBankVO> distinct = this.getUserSqlSessionTemplate().selectList(packageName+"queryDistinctByCode", vo);
					list.get(i).setQy(distinct.get(0).getName());
//				if (distinct != null && distinct.size() > 0) {
//					for (int k = 0; k < distinct.size(); k++) {
//						if (list.get(i).getQy().equals(distinct.get(k).getCode())) {
//							list.get(i).setQy(distinct.get(k).getName());
//							break;
//						}
//					}
//				}
                    //翻译入库网点名称
                    vo.setCode(list.get(i).getRkwd());
                    List<DistinctAndBankVO> rkwd = this.getUserSqlSessionTemplate().selectList(packageName+"queryFkwdByCode", vo);
                    if (rkwd.size()>0) {
                        list.get(i).setRkwd(rkwd.get(0).getName());
                    }

                    //翻译入库状态
                    if ("01".equals(list.get(i).getStatus())){
                        list.get(i).setStatus("已入库");
                    }else {
                        list.get(i).setStatus("未入库");
                    }

                }
			}
		}
		page.setData(list);
		return page;
	}

    //按批次入柜分解查询所有盒
    public List<StorageBean> selectAllBoxByBatchNo(StorageBean bean) throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        List<StorageBean> list = template.selectList(NAMESPACE + "selectAllBoxByBatchNo",bean);
        return list;
    }

	//社保卡入库
	@Transactional("faka")
	public int selectStorage(StorageBean bean) {
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
		int res = 0;
		try {
			//查询所有盒的数据
			List<StorageInfoVo> list = template.selectList(NAMESPACE + "selectStorageByBox", bean);
			if (list != null && list.size() > 0) {
                for (StorageInfoVo vo : list) {
                    vo.setCcaid(bean.getCcaid());
                    vo.setUserid(bean.getUserid());
                    vo.setFkwd(bean.getFkwd());
                }
                //把【批次明细表 CARD_BATCH_DETAIL】的数据插入到【社保卡基本信息表 CARD_INFO】
                template.insert(NAMESPACE + "insertStorageByBox", list);
                //添加【卡存放位置表 CARD_STORE】记录
                template.insert(NAMESPACE + "insertStorageStoreByBox", list);
                //修改【批次箱盒表 CARD_BATCH_BINBOX】中状态，插入入库网点和柜号
                template.update(NAMESPACE + "updateStorageByBox", bean);
                //记录操作日志【批次操作日志表 CARD_BATCH_LOG】
                template.insert(NAMESPACE + "insertStorageStoreByBoxLog", list);
            }
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error("社保卡入库异常==>入参：" + JsonHelper.javaBeanToJson(bean));
			logger.error("社保卡入库异常==>原因：" + e);
			res++;
		}
		return res;
	}

	//已入库数据统计
	public Page<StorageVo> storageStatistics(Page<StorageVo> page,StorageBean bean) throws Exception{
		//设置查询的页数页码
		bean.setPage(page);
		//查询数据库
		List<StorageVo> list = this.getFakaSqlSessionTemplate().selectList(NAMESPACE + "storageRecover", bean);
        long count = page.getCount();
		if(list != null && list.size() > 0) {
			//查询银行信息
			List<DistinctAndBankVO> bank = this.getUserSqlSessionTemplate().selectList(packageName + "queryBankInfo");
			//进行编码转换
			for (int i = 0; i < list.size(); i++) {
				if (bank != null && bank.size() > 0) {
					//将查询结果的银行编码转换为中文
					for (int j = 0; j < bank.size(); j++) {
						if (list.get(i).getBank().equals(bank.get(j).getCode())) {
							list.get(i).setBank(bank.get(j).getName());
							break;
						}
					}
				}
				//将入库网点转换中文（由于一次性查询所有区域耗时太久，故分开转换）
				DistinctAndBankVO vo = new DistinctAndBankVO();
				vo.setCode(list.get(i).getRkwd());
				List<DistinctAndBankVO> fkwd = this.getUserSqlSessionTemplate().selectList(packageName+"queryFkwdByCode", vo);
                if (fkwd.size()>0) {
                    list.get(i).setRkwd(fkwd.get(0).getName());
                }

                //查询卡数量
                /*bean.setId(list.get(i).getId());
                StorageVo cardNumVo = this.getFakaSqlSessionTemplate().selectOne(NAMESPACE + "getCardNum", bean);
                if (cardNumVo != null) {
                    list.get(i).setCardNum(cardNumVo.getCardNum());
                }*/
			}
            page.setCount(count);
			page.setData(list);
		}
		return page;
	}

	//判断追回的箱子是否存在已发放的数据
	public int storageIsGet(StorageBean bean) throws Exception {
		int res = this.getFakaSqlSessionTemplate().selectOne(NAMESPACE + "storageIsGet", bean);
		return res;
	}

	//社保卡已入库数据追回
	@Transactional("faka")
	public int storageRecover(StorageBean bean) throws Exception {
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
		int res = 0;
		//修改【批次箱盒表 CARD_BATCH_BINBOX】中入库网点和柜号
        template.update(NAMESPACE + "updateBinBoxById", bean);
        logger.info("修改【批次箱盒表 CARD_BATCH_BINBOX】中入库网点和柜号===");
		//修改【社保卡基本信息表 CARD_INFO】记录
        template.update(NAMESPACE + "updateCardInfoById", bean);
        logger.info("修改【社保卡基本信息表 CARD_INFO】记录===");
		//修改【卡存放位置表 CARD_STORE】记录
        template.update(NAMESPACE + "updateCardStoreById", bean);
        logger.info("修改【卡存放位置表 CARD_STORE】记录===");
        //查询批次号ID
        StorageBean vo = template.selectOne(NAMESPACE + "getBatchId", bean);
        logger.info("查询批次号ID===");
        bean.setBatchId(vo.getBatchId());
		//记录操作日志【批次操作日志表 CARD_BATCH_LOG】
		res = template.insert(NAMESPACE + "insertCardBatchLog", bean);
        logger.info("记录操作日志===");
		return res;
	}

	//获取筛选条件
	public List<String> storageCondition(StorageBean bean) throws Exception {
		List<String> list = this.getFakaSqlSessionTemplate().selectList(NAMESPACE + "storageCondition", bean);
		return list;
	}

	//导出已入库数据
	public boolean exportStorage(StorageBean bean,HttpServletResponse response) {
		//查询数据库
		List<StorageVo> list = this.getFakaSqlSessionTemplate().selectList(NAMESPACE + "storageRecover", bean);
		if(list != null && list.size() > 0) {
			//查询银行信息
			List<DistinctAndBankVO> bank = this.getUserSqlSessionTemplate().selectList(packageName + "queryBankInfo");
			//进行编码转换
			for (int i = 0; i < list.size(); i++) {
				if (bank != null && bank.size() > 0) {
					//将查询结果的银行编码转换为中文
					for (int j = 0; j < bank.size(); j++) {
						if (list.get(i).getBank().equals(bank.get(j).getCode())) {
							list.get(i).setBank(bank.get(j).getName());
							break;
						}
					}
				}
				//将入库网点转换中文（由于一次性查询所有区域耗时太久，故分开转换）
				DistinctAndBankVO vo = new DistinctAndBankVO();
				vo.setCode(list.get(i).getRkwd());
				List<DistinctAndBankVO> fkwd = this.getUserSqlSessionTemplate().selectList(packageName+"queryFkwdByCode", vo);
				list.get(i).setRkwd(fkwd.get(0).getName());
			}
		}
		/****生成excel文件***/
		boolean ret = new ExcelUtil().createExcel(list, response);
		return ret;
	}

	/**
	 * 创建Excel文件
	 */
	public class ExcelUtil {

		/**
		 * 生成数据文件
		 * @param list
		 * @param response
		 * @return
		 */
		public boolean createExcel(List<StorageVo> list,HttpServletResponse response){
			try {
				String fileName = String.format("入库数据%s.xls",
						new SimpleDateFormat("yyyyMMdd").format(new Date()));
				fileName = new String(fileName.getBytes("UTF-8"),"ISO8859-1");//火狐浏览器

				response.setHeader("Content-disposition", "attachement;filename=\"" + fileName + "\"");

				OutputStream output = response.getOutputStream();
				//创建工作薄
				WritableWorkbook workbook = Workbook.createWorkbook(output);
				//创建新的一页
				WritableSheet sheet = workbook.createSheet("Sheet1", 0);
				//设置表格属性
				WritableCellFormat headerFormat = new WritableCellFormat();
				//水平居中对齐
				headerFormat.setAlignment(Alignment.CENTRE);
				//竖直方向居中对齐
				headerFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
				//初始化头
				initTop(headerFormat,sheet);
				//生成数据
				create(headerFormat,sheet, list);

				//关闭
				workbook.write();
				workbook.close();
				output.close();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}
			return false;
		}

		/**
		 * 初始化文件
		 * @return
		 */
		private File initFile(String fPath){
			File file = new File(fPath);

			if(!file.isDirectory()){
				File dirs = new File(file.getParent());
				dirs.mkdirs();
			}
			return file;
		}

		/**
		 * 形成表头
		 * @param sheet
		 */
		private void initTop(WritableCellFormat headerFormat,WritableSheet sheet) throws WriteException {
			//形成表头
			sheet.addCell(new Label(0, 0, "所属银行", headerFormat));
			sheet.addCell(new Label(1, 0, "入库网点",headerFormat));
			sheet.addCell(new Label(2, 0, "批次号", headerFormat));
			sheet.addCell(new Label(3, 0, "箱号", headerFormat));
			sheet.addCell(new Label(4, 0, "盒号", headerFormat));
			sheet.addCell(new Label(5, 0, "卡数", headerFormat));
			sheet.addCell(new Label(6, 0, "入库时间", headerFormat));

			//设置表格指定列的列宽
			sheet.setColumnView(0, 15);
			sheet.setColumnView(1, 15);
			sheet.setColumnView(2, 15);
			sheet.setColumnView(3, 15);
			sheet.setColumnView(4, 15);
			sheet.setColumnView(5, 15);
			sheet.setColumnView(6, 25);
		}

		/**
		 * 生成数据文件
		 * @param list
		 * @return
		 */
		private boolean create(WritableCellFormat headerFormat,WritableSheet sheet,List<StorageVo> list) throws WriteException {
			if(list == null){
				return false;
			}

			int i=1;
			for (StorageVo info : list){
				sheet.addCell(new Label(0,i,info.getBank(),headerFormat));   	//所属银行
				sheet.addCell(new Label(1,i,info.getRkwd(),headerFormat));   	//入库网点
				sheet.addCell(new Label(2,i,info.getBatchNo(), headerFormat));	//批次号
				sheet.addCell(new Label(3,i,info.getBin(), headerFormat));   	//箱号
				sheet.addCell(new Label(4,i,info.getBox(), headerFormat));   	//盒号
				sheet.addCell(new Label(5,i,info.getCardNum(), headerFormat));  //卡数
				sheet.addCell(new Label(6,i,info.getUpdateTime().toString(),headerFormat));//入库时间
				i++;
			}
			return true;
		}
	}

}
