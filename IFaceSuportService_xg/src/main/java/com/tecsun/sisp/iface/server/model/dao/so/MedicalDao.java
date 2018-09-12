package com.tecsun.sisp.iface.server.model.dao.so;

import com.tecsun.sisp.iface.common.util.MyBatisDao;
import com.tecsun.sisp.iface.common.vo.common.query.PersonNoAndPage;
import com.tecsun.sisp.iface.common.vo.common.query.SecQueryBean;
import com.tecsun.sisp.iface.common.vo.so.po.medical.*;

import java.util.List;

/**
 * Created by DG on 2015/10/14.
 */
@MyBatisDao
public interface MedicalDao {

    public String smsylGetPersonIdByPersonNo(String personNo);

    public Integer smsCheckoutPersonNo(String personNo);

    public YlgrjbxxPO smsYlgrjbxx(String personNo);

    public int smsCountYljfxxcx(String aac001);

    public List<YljfxxcxPO> smsYljfxxcx(SecQueryBean query);

    public int smsCountYlgrzhxxcx(String personNo);

    public List<YlgrzhxxcxPO> smsYlgrzhxxcx(SecQueryBean query);

    public int smsCountYlgrjsxxcx(String personNo);

    public List<YlgrjsxxcxPO> smsYlgrjsxxcx(SecQueryBean query);

    public int smsCountMxbxxcx(String personNo);

    public List<MxbxxcxPO> smsMxbxxcx(SecQueryBean query);

    public int smsCountYdanzxxcx(String personNo);

    public List<YdanzxxcxPO> smsYdanzxxcx(SecQueryBean query);
    
    //个人医疗参保信息
    public YlgrjbxxPO smsYlgrjbxxByperonsId(String personNo);
    //账户余额
    public YlgrzhxxcxPO smsgrbhzhye(SecQueryBean bean);
}
