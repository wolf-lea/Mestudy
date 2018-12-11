package com.tecsun.sisp.iface.server.model.service.so;


import java.util.List;

import com.tecsun.sisp.iface.common.vo.common.query.PersonNoAndPage;
import com.tecsun.sisp.iface.common.vo.common.query.SecQueryBean;
import com.tecsun.sisp.iface.common.vo.so.po.medical.MxbxxcxPO;
import com.tecsun.sisp.iface.common.vo.so.po.medical.YdanzxxcxPO;
import com.tecsun.sisp.iface.common.vo.so.po.medical.YlgrjbxxPO;
import com.tecsun.sisp.iface.common.vo.so.po.medical.YlgrjsxxcxPO;
import com.tecsun.sisp.iface.common.vo.so.po.medical.YlgrzhxxcxPO;
import com.tecsun.sisp.iface.common.vo.so.po.medical.YljfxxcxPO;

/**
 * Created by DG on 2015/10/9.
 */
public interface MedicalService {

    public Boolean smsCheckoutPersonNo(SecQueryBean bean);

    public YlgrjbxxPO smsYlgrjbxx(SecQueryBean bean);
    
    //个人医疗参保信息
    //public List<YlgrjbxxPO> smsYlgrjbxxByperonsId(SecQueryBean bean);
    //2018-9-18修改
    public YlgrjbxxPO smsYlgrjbxxByperonsId(SecQueryBean bean);

    public int smsCountYljfxxcx(SecQueryBean bean);
    //个人医疗缴费信息
    public List<YljfxxcxPO> smsYljfxxcx(SecQueryBean bean);

    public int smsCountYlgrzhxxcx(SecQueryBean bean);
    //个人医疗账户信息
    public List<YlgrzhxxcxPO> smsYlgrzhxxcx(SecQueryBean bean);

    public int smsCountYlgrjsxxcx(SecQueryBean bean);
    //个人医疗结算信息
    public List<YlgrjsxxcxPO> smsYlgrjsxxcx(SecQueryBean bean);

    public int smsCountMxbxxcx(SecQueryBean bean);

    public List<MxbxxcxPO> smsMxbxxcx(SecQueryBean bean);

    public int smsCountYdanzxxcx(SecQueryBean bean);

    public List<YdanzxxcxPO> smsYdanzxxcx(SecQueryBean bean);
    //账户余额
    public YlgrzhxxcxPO smsgrbhzhye(SecQueryBean bean);

}
