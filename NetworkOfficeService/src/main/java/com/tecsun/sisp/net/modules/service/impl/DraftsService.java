package com.tecsun.sisp.net.modules.service.impl;

import com.tecsun.sisp.net.common.Page;
import com.tecsun.sisp.net.modules.entity.request.*;
import com.tecsun.sisp.net.modules.entity.response.*;

import java.util.List;
import java.util.Map;

public interface DraftsService {
	
	public List<DraftsVo> queryByApplyRecord(DraftsBean vo);//查看申请记录
	public List<DraftsVo> queryByApplyRecord2(DraftsBean2 vo);//查看待办事项详细
	public Page<DraftsVo> findbySuccessMessage(DraftsBean2 vo);//办理成功件
	public DraftsVo findbySuccessDetailedMessage(DraftsBean vo);//办理成功件查询详细
	public Page<DraftsVo> findbyFailMessage(DraftsBean2 vo);//办理失败件查询
	public List<DraftsVo> findbyFailDetailedMessage(DraftsBean vo);//办理失败件查询详细
	public Page<DraftsVo> findbyCurrentMessage(DraftsBean2 vo);//查询正在办理
	public List<DraftsVo> findbyCurrentDetailedMessage(DraftsBean vo);//查询正在办理详细
	public long addDraftsMessage(DraftsBean bean); //草稿添加
	public long addOfficeMessage(OfficeVo vo);//添加办件信息
	public long deleteDraftsMessage(Map map);//删除草稿
	public long deleteOfficeMessage(Map map);//删除草稿

	public long updateDraftsMessage(DraftsListVo vo);//修改草稿
	public Page<DraftsVo> findbyDraftsMessage(DraftsBean2 vo);//草稿箱查询
	public BanjianVo findAllDraftsMessage(DraftsBean vo);//草稿箱详细
	public long commitDraft(DraftsListVo vo);//提交草稿
	public List<DraftsVo> queryByOfficeStatus(DraftsBean vo);//查询办件进度
	
	//事项查看
	public Page<ItemBean> queryByItems(ItemListBean vo);
	
	public ItemVo queryByItemDetails(ItemVo vo);
	
	//事项新增
	public long addPersonMessages(ItemVo  vo);
	//事项修改
	public long updateByItemDetails(ItemVo vo);
	//删除事项
	public long deleteItemDetails(ItemVo vo);
	public long insertAudit(AuditVo vo);//添加审核人
	public long updateTimeAndState(AuditVo  vo);//审核之后更新状态
	public List<MatterVo> queryByTmatter();//获取事项类型
	public void  insertFileName(FileBean vo); //文件添加

	public List<TcqVo> queryalltcq();  //查询所有的统筹区名称和编号

	public TcqVo queryunit(Map map); //根据统筹区编码查询单位名称和编码

	public int updateDraftstate(Map map); //审核的时候修改drafts状态

	public int additem(MatterBean bean);
	public int addmatterfile(List<MatterFileBean> list);
	public int deleteitem(Map map);
	public int deletefiles(Map map);
	public List<String> getfilecodelist(Map map);
	public int deletefilebyid(Map map);
	public List<ItemMatters> getitemsbyterm(ItemBean bean);
	public int updateitembyid(MatterBean bean);

}
