package com.tecsun.sisp.iface.server.model.service.employment;

import com.tecsun.sisp.iface.common.vo.Result;
import com.tecsun.sisp.iface.common.vo.employment.param.queryParamBean;

/**
 * Created by pear on 2015/8/5.
 */
public interface EmpQueryService {
    Result getRegistrationInfo(queryParamBean bean);

    Result getEmploymentInfo(queryParamBean bean);

    Result getUnEmploymentInfo(queryParamBean bean);

    Result getEmployAssistInfo(queryParamBean bean);

    Result getEmployPolicyInfo(queryParamBean bean);

    Result getPublicServiceInfo(queryParamBean bean);

    Result getUnEmpInsTreatInfo(queryParamBean bean);

    Result getInspectionInfo(queryParamBean bean);

    Result getOtherMattersInfo(queryParamBean bean);
}
