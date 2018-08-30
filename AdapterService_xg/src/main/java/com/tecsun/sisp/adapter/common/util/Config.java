package com.tecsun.sisp.adapter.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by zhangqingjie on 15-5-15.
 * update by hudanmeng on 17-4-13
 * 动态读取配置文件
 */
public class Config {
    private static Logger logger = LoggerFactory.getLogger(Config.class);

    private static final String CONIFG_FILE = "/config.properties";
    private static Config instance = null;
    private Properties props = new Properties();
    //    配置文件的最后更新时间
    private long lastModified = 0;

    private Config() {
        this.init();
    }

    private void init() {
        InputStream in = null;
        try {
            in = Config.class.getResourceAsStream(CONIFG_FILE);
            this.props.load(in);
            if(props==null&&props.isEmpty())
                logger.error("config.properties  without content. ");
            else
            logger.info("config.properties :"+JsonMapper.buildNormalMapper().toJson(props));
        } catch (IOException var11) {
            if (this.logger.isErrorEnabled()) {
                this.logger.error("loading /config.properties fail!", var11);
            }
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException var10) {
                    this.logger.error("Config close fail!", var10);
                }
            }
        }
    }

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    public String get(String key) {
        return this.get(key, (String) null);
    }

    public String get(String key, String defaultValue) {
        return this.props.getProperty(key, defaultValue);
    }

    /**
     * 监听配置文件是否被更新，自动更新文件中的配置项存储到 instance 变量中。
     */
    public void todo() {
        String filename = CONIFG_FILE;
        if (this.isFileUpdated(filename)) {
            try {
                instance = new Config();
            } catch (Exception ioe) {
                logger.error("instance:" , ioe);
            }
        }
    }

    /**
     * 判断配置文件是否已被更新
     *
     * @param filename 物理文件名
     * @return 是 true 否 false
     */
    private boolean isFileUpdated(String filename) {
        File file = new File(Config.class.getClassLoader().getResource(filename).getPath());
        if (file.isFile()) {
            long lastUpdateTime = file.lastModified();
            if (lastUpdateTime > this.lastModified) {
                this.lastModified = lastUpdateTime;
                return true;
            } else {
                return false;
            }
        } else {
            logger.error("The path does not point to a file:" + filename);
            return false;
        }
    }
}
