package com.tecsun.sisp.net.modules.service.impl;

import com.tecsun.sisp.net.common.Page;
import com.tecsun.sisp.net.modules.dao.drafts_sisp.DraftsDAO;
import com.tecsun.sisp.net.modules.entity.request.*;
import com.tecsun.sisp.net.modules.entity.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("draftsService")
public class DraftsServiceImpl implements DraftsService{

	@Autowired
	private DraftsDAO draftsDAO;
	private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss"); // 时间格式化的格式
//	private static final Random r = new Random();
	//2018/7/30把该方法加了一个final
	public  String getBjNum(){
		String rannum = String.valueOf((int)((Math.random()*9000)+1000)); // 获取随机数
		String nowTimeStr = sDateFormat.format(new Date()).toString(); // 当前时间
		return nowTimeStr + rannum;
	}

	public long getSxbm(){
		long rannum = (int)(Math.random()*9000)+1000; // 获取随机数
		return rannum;
	}
	
	public List<DraftsVo> queryByApplyRecord(DraftsBean vo) {
		return draftsDAO.queryByApplyRecord(vo);
	}

	//办理成功件查询
	public Page<DraftsVo> findbySuccessMessage(DraftsBean2 vo){
		Page<DraftsVo> page= new Page<>(vo.getPageno(),vo.getPagesize());
		vo.setPage(page);
		List<DraftsVo> findbySuccessMessage = draftsDAO.findbySuccessMessage(vo);
		if(findbySuccessMessage!=null && findbySuccessMessage.size()>0){
			page.setData(findbySuccessMessage);
		}
		return page;
	}

	//查询办理成功件详细
	public DraftsVo findbySuccessDetailedMessage(DraftsBean vo){
		return draftsDAO.findbySuccessDetailedMessage(vo);
	}
		
	//办理失败件查询
	@Override
	public Page<DraftsVo> findbyFailMessage(DraftsBean2 vo) {
		Page<DraftsVo> page =new Page<>(vo.getPageno(),vo.getPagesize());
		vo.setPage(page);
		List<DraftsVo> findbyFailMessage = draftsDAO.findbyFailMessage(vo);
		if (findbyFailMessage!=null && findbyFailMessage.size()>0) {
			page.setData(findbyFailMessage);
		}
		return page;
	}

	//办理失败查询详细
	public List<DraftsVo> findbyFailDetailedMessage(DraftsBean vo){
		return draftsDAO.findbyFailDetailedMessage(vo);
	}

	//正在办理件
	@Override
	public Page<DraftsVo> findbyCurrentMessage(DraftsBean2 vo) {
		Page<DraftsVo> page = new Page<>(vo.getPageno(),vo.getPagesize());
		vo.setPage(page);
		List<DraftsVo> findbyCurrentMessage = draftsDAO.findbyCurrentMessage(vo);
		if(findbyCurrentMessage !=null && findbyCurrentMessage.size()>0){
			page.setData(findbyCurrentMessage);
		}
		return page;
	}

	//正在办理件详细;
	public List<DraftsVo> findbyCurrentDetailedMessage(DraftsBean vo){
		return draftsDAO.findbyCurrentDetailedMessage(vo);
	}

	//查询办件进度
	public List<DraftsVo> queryByOfficeStatus(DraftsBean vo){
		return draftsDAO.queryByOfficeStatus(vo);
	}

	//添加草稿2018/7/13
	public long addDraftsMessage(DraftsBean bean) {
		bean.settId(getBjNum());
		long rows = draftsDAO.addDraftsMessage(bean);//办件表
		return rows;
	}

	public long addOfficeMessage(OfficeVo vo){
		vo.setoId(getBjNum());
		return draftsDAO.addOfficeMessage(vo);}

	public long deleteDraftsMessage(Map map) {
		return draftsDAO.deleteDraftsMessage(map);
	}


	public long deleteOfficeMessage(Map map) {
		return draftsDAO.deleteOfficeMessage(map);
	}

	public long updateDraftsMessage(DraftsListVo vo){
		long rows =draftsDAO.updateDraftsMessage(vo);
		List<OfficeVo> offices = vo.getOffices();
		for (OfficeVo officeVo : offices) {
			draftsDAO.updateOfficeMessage(officeVo);
		}
		List<TmatterVo> tmatters = vo.getTmatters();
		for (TmatterVo tmatterVo : tmatters) {
			draftsDAO.updateMatterMessage(tmatterVo);
		}
		return rows;
	}

	//草稿箱详细(需改动)
	public BanjianVo findAllDraftsMessage(DraftsBean vo){
		return draftsDAO.findAllDraftsMessage(vo);
	}

	//草稿查询
	@Override
	public Page<DraftsVo> findbyDraftsMessage(DraftsBean2 vo) {
		Page<DraftsVo> page= new Page<>(vo.getPageno(),vo.getPagesize());
		vo.setPage(page);
		List<DraftsVo> findbyDraftsMessage = draftsDAO.findbyDraftsMessage(vo);
		if(findbyDraftsMessage!=null && findbyDraftsMessage.size()>0){
			page.setData(findbyDraftsMessage);
		}
		return page;
	}
	//草稿删除
//	public long deleteDraftsMessage(DraftsListVo vo){
//		long rows =draftsDAO.deleteDraftsMessage(vo);
//		List<OfficeVo> offices = vo.getOffices();
//		for (OfficeVo officeVo : offices) {
//			draftsDAO.deleteOfficeMessage(officeVo);
//		}
//		List<TmatterVo> tmatters = vo.getTmatters();
//		for (TmatterVo tmatterVo : tmatters) {
//			draftsDAO.deleteMatterMessage(tmatterVo);
//		}
//		return rows;
//	}

	//提交草稿
	public long commitDraft(DraftsListVo vo) {
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//		vo.setUpdatetime(df.format(new Date()).toString());
		return draftsDAO.commitDraft(vo);
	}

	//事项查询
	public Page <ItemBean> queryByItems(ItemListBean vo) {
		Page<ItemBean> page= new Page<>(vo.getPageno(),vo.getPagesize());
		vo.setPage(page);
		List<ItemBean> queryByItems = draftsDAO.queryByItems(vo);
		if(queryByItems !=null && queryByItems.size()>0){
			page.setData(queryByItems);
		}
		return page;
	}

	public ItemVo queryByItemDetails(ItemVo vo) {
		return draftsDAO.queryByItemDetails(vo);
	}

	//事项添加
	public long addPersonMessages(ItemVo vo){
		vo.setSxbm(getBjNum());
		long count = draftsDAO.addPersonMessages(vo);//主
		List<InformationVo> itemBeans = vo.getInformationVos();//副
		for (InformationVo informationVo : itemBeans) {
			//informationVo.setMid(vo.getId());// 先添加外键
			informationVo.setId(vo.getId());// 先添加外键
			draftsDAO.addInfornations(informationVo);
		}
		return count;
	}

	//事项修改
	public long updateByItemDetails(ItemVo vo) {
		long row = draftsDAO.updateByItemDetails(vo);
		List<InformationVo> informationVos = vo.getInformationVos();
		for (InformationVo informationVo : informationVos) {
			//informationVo.setId(vo.getId());
			draftsDAO.updateInformation(informationVo);
		}
		return row;
	}
	
	public long deleteItemDetails(ItemVo vo) {
		long row=draftsDAO.deleteItemDetails(vo);
		return row;
	}

	@Override
	public long insertAudit(AuditVo vo) {
		vo.setApplyNum(getBjNum());
		long row=draftsDAO.insertAudit(vo);
		return row;
	}

	@Override
	public long updateTimeAndState(AuditVo  vo) {
		long count =draftsDAO.updateTimeAndState(vo);
		return count;
	}

	@Override
	public List<MatterVo> queryByTmatter() {
		return draftsDAO.queryByTmatter();
	}

	@Override
	public List<DraftsVo> queryByApplyRecord2(DraftsBean2 vo) {
		return draftsDAO.queryByApplyRecord2(vo);
	}
	
	//文件添加
	public void insertFileName(FileBean vo) {
		draftsDAO.insertFileName(vo);
	}

	@Override
	public List<TcqVo> queryalltcq() {
		return draftsDAO.queryalltcq();
	}

	@Override
	public TcqVo queryunit(Map map) {
		return draftsDAO.queryunit(map);
	}

	@Override
	public int updateDraftstate(Map map){
		return draftsDAO.updateDraftstate(map);
	}

	@Override
	public int additem(MatterBean bean) {
		return draftsDAO.additem(bean);
	}

	@Override
	public int addmatterfile(List<MatterFileBean> list) {
		return draftsDAO.addmatterfile(list);
	}

	@Override
	public int deleteitem(Map map) {
		return draftsDAO.deleteitem(map);
	}

	@Override
	public int deletefiles(Map map) {
		return draftsDAO.deletefiles(map);
	}

	@Override
	public List<String> getfilecodelist(Map map) {
		return draftsDAO.getfilecodelist(map);
	}

	@Override
	public int deletefilebyid(Map map) {
		return draftsDAO.deletefilebyid(map);
	}

	@Override
	public List<ItemMatters> getitemsbyterm(ItemBean bean) {
		return draftsDAO.getitemsbyterm(bean);
	}

	@Override
	public int updateitembyid(MatterBean bean) {
		return draftsDAO.updateitembyid(bean);
	}
}
