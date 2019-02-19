package com.tecsun.sisp.net.modules.service.impl;

import com.tecsun.sisp.net.common.Page;
import com.tecsun.sisp.net.modules.dao.gov.GovQueryDao;
import com.tecsun.sisp.net.modules.entity.request.DictionaryCodeBean;
import com.tecsun.sisp.net.modules.entity.request.NoticeBean;
import com.tecsun.sisp.net.modules.entity.request.GovernmentBean;
import com.tecsun.sisp.net.modules.entity.response.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GovernmentServiceImpl  implements GovernmentService {

    public static final Logger logger = Logger.getLogger(GovernmentServiceImpl.class);

    @Autowired
    private GovQueryDao govQueryDao;

    /*查询列表*/
    @Override
    public Page<GovernmentVO> getNoticeList4Cssp(GovernmentBean bean){
        Page<GovernmentVO> page = new Page<>(bean.getPageno(),bean.getPagesize());
        bean.setPage(page);
        List<GovernmentVO> list=govQueryDao.getNoticeList(bean);
        if(list != null && list.size()>0){
            for (int i = 0 ; i < list.size();i++) {
                list.get(i).setDetailUrl(list.get(i).getDetailUrl() + "?id=" + list.get(i).getId());
            }
            page.setData(list);
        }
        return page;
    }

    /*查看详情*/
    @Override
    public NoticeDetailVO getNoticeDetailById4Cssp(NoticeBean bean) {
        return (NoticeDetailVO) govQueryDao.getNoticeDetailById(bean);
    }

    /*查看附件*/
    @Override
    public NoticePathVO getNoticeFileById4cssp(NoticePathVO vo) {
        return govQueryDao.getNoticeFileById(vo);
    }
}

