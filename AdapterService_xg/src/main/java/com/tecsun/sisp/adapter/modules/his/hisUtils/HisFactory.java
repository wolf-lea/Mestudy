package com.tecsun.sisp.adapter.modules.his.hisUtils;

import com.tecsun.sisp.adapter.common.util.Constants;
import org.apache.log4j.Logger;

/**
 * Created by danmeng on 2017/7/12.
 */
public class HisFactory {
    public final static Logger logger = Logger.getLogger(HisFactory.class);

    public static HisIface getInstance(String hospitalId) {
        String className = Constants.HIS_IFACE.get(hospitalId);
        try {
            HisIface hisIface = (HisIface) Class.forName(className).newInstance();
            return hisIface;
        } catch (Exception e) {
            logger.error("医院工厂类HisFactory出错：",e);
            return null;
        }
    }
}
