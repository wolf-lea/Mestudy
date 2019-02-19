package com.tecsun.sisp.net.modules.service.impl;

import com.tecsun.sisp.net.common.Page;
import com.tecsun.sisp.net.modules.entity.request.GovernmentBean;
import com.tecsun.sisp.net.modules.entity.request.NoticeBean;
import com.tecsun.sisp.net.modules.entity.response.*;

import java.util.List;

/**
 * Created by lorn on 2018/7/26.
 */
public interface GovernmentService {
    public Page<GovernmentVO> getNoticeList4Cssp(GovernmentBean bean);
    public NoticeDetailVO getNoticeDetailById4Cssp(NoticeBean bean);
    public NoticePathVO getNoticeFileById4cssp(NoticePathVO vo);
}
