package com.tecsun.sisp.adapter.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**工具类获取ApplicationContext对象
 * Created by danmeng on 2017/7/18.
 */
public class AppContext {
    private static Logger logger = LoggerFactory.getLogger(AppContext.class);
    private static final String APP_FILE = "/config/applicationContext.xml";
    private static AppContext instance = null;
    private    ApplicationContext appContext;
    private AppContext() {
        this.appContext = new ClassPathXmlApplicationContext(APP_FILE);
    }

    public static AppContext getInstance() {
        if (instance == null) {
            instance = new AppContext();
        }
        return instance;
    }
    public ApplicationContext getAppContext(){
        return appContext;
    }
    public Object getService(String service){
        return appContext.getBean(service);
    }

}
