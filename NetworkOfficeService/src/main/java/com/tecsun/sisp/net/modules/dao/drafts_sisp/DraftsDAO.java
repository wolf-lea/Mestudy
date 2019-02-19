package com.tecsun.sisp.net.modules.dao.drafts_sisp;

import com.tecsun.sisp.net.common.MyBatisDao;
import com.tecsun.sisp.net.modules.entity.request.*;
import com.tecsun.sisp.net.modules.entity.response.*;

import java.util.List;
import java.util.Map;


@MyBatisDao
public interface DraftsDAO {
	
	public List<DraftsVo> queryByApplyRecord(DraftsBean vo);//查看申请记录
	public List<DraftsVo> queryByApplyRecord2(DraftsBean2 vo);//查看待办事项详细
	public List<DraftsVo> findbySuccessMessage(DraftsBean2 vo);//办理成功件查询
	public DraftsVo findbySuccessDetailedMessage(DraftsBean vo);//办理成功件查询详细
	public List<DraftsVo> findbyFailMessage(DraftsBean2 vo);//办理失败件查询
	public List<DraftsVo> findbyFailDetailedMessage(DraftsBean vo);//办理失败件查询详细
	public List<DraftsVo> findbyCurrentMessage(DraftsBean2 vo);//查询正在办理
	public List<DraftsVo> findbyCurrentDetailedMessage(DraftsBean vo);//查询正在办理详细
	public long addDraftsMessage(DraftsBean bean); //草稿添加
	public long addTmatterMessage(TmatterVo vo); //草稿事项关联添加
	public long addOfficeMessage(OfficeVo vo); //草稿事项关联添加
	public long deleteDraftsMessage(Map map);//删除草稿
	public long deleteOfficeMessage(Map map);//删除草稿
	public long deleteMatterMessage(TmatterVo vo);//删除草稿
	public long updateDraftsMessage(DraftsListVo vo);//修改草稿
	public long updateOfficeMessage(OfficeVo vo);//修改草稿
	public long updateMatterMessage(TmatterVo vo);//修改草稿
	public List<DraftsVo> findbyDraftsMessage(DraftsBean2 vo);//草稿箱查询
	public BanjianVo findAllDraftsMessage(DraftsBean vo);//草稿箱详细
	public long commitDraft(DraftsListVo vo);//提交草稿
	public List<DraftsVo> queryByOfficeStatus(DraftsBean vo);//查询办件进度
	//事项查看
	public List<ItemBean> queryByItems(ItemListBean vo);
	public ItemVo queryByItemDetails(ItemVo vo);
	public long addPersonMessages(ItemVo  vo);
	public long addInfornations(InformationVo vo);
	public long updateByItemDetails(ItemVo vo);
	public long updateInformation(InformationVo vo);
	public long deleteItemDetails(ItemVo vo);
	public long deleteInformation(InformationVo vo);
	public long insertAudit(AuditVo vo);//添加审核人
	public long updateTimeAndState(AuditVo  vo);//审核之后更新状态
	public List<MatterVo> queryByTmatter();//获取事项类型
	
	public void insertFileName(FileBean vo); //文件添加
	
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
