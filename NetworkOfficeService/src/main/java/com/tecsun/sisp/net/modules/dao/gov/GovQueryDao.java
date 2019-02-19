package com.tecsun.sisp.net.modules.dao.gov;

import com.tecsun.sisp.net.common.MyBatisDao;
import com.tecsun.sisp.net.modules.entity.request.DictionaryCodeBean;
import com.tecsun.sisp.net.modules.entity.request.GovernmentBean;
import com.tecsun.sisp.net.modules.entity.request.NoticeBean;
import com.tecsun.sisp.net.modules.entity.response.*;

import java.util.List;

/**
 * Created by lorn on 2018/7/26.
 */
@MyBatisDao
public interface GovQueryDao {
    public List<GovernmentVO> getNoticeList(GovernmentBean bean);
    public NoticeDetailVO getNoticeDetailById(NoticeBean bean);
    public NoticePathVO getNoticeFileById(NoticePathVO vo);
}
