package com.tecsun.siboss.tsbm.common.util;

import com.tecsun.siboss.tsbm.common.bean.DictVO;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: JedisUtil
 * Description:操作redis数据库数据
 * Author： 张清洁
 * CreateTime： 2015年06月07日 16时:00分
 */
public class JedisUtil {

    protected static final Log logger = LogFactory.getLog(JedisUtil.class);

    public static void setValue(String key, String value) throws Exception {
        setValue(key, value, Integer.parseInt(Config.getInstance().get("redis_iface_expx")));
    }


    /**
     * @param key 键
     * @param value 值
     * @param outTime  单位：分钟
     * @throws Exception
     */
    public static void setValue(String key, String value, Integer outTime) throws Exception {
        JedisPool publicJedisPool = null;
        Jedis publicJedis = null;
        try {
            publicJedisPool = DataBase.getJedisPublicPool();
            publicJedis = publicJedisPool.getResource();
            publicJedis.setex(key, 60 * outTime, value);
        } catch (Exception e) {
            publicJedisPool.returnBrokenResource(publicJedis);
            throw new Exception("设置redis失败: key:" + key + ",value:" + value, e);
        } finally {
            if (publicJedis != null) {
                publicJedisPool.returnResource(publicJedis);
            }
        }
    }

    public static String getValue(String key) throws Exception {
        JedisPool publicJedisPool = null;
        Jedis publicJedis = null;
        String value="";
        try {
            publicJedisPool = DataBase.getJedisPublicPool();
            publicJedis = publicJedisPool.getResource();
            value = publicJedis.get(key);
        } catch (Exception e) {
            publicJedisPool.returnBrokenResource(publicJedis);
            throw new Exception("获取reids失败，key:" + key, e);
        } finally {
            if (publicJedis != null) {
                publicJedisPool.returnResource(publicJedis);
            }
        }
        return value;
    }

    /**
     * 退出时将token删除
     *
     * @param key
     * @throws Exception
     */
    public static void delValue(String key) throws Exception {
        JedisPool publicJedisPool = null;
        Jedis publicJedis = null;
        try {
            publicJedisPool = DataBase.getJedisPublicPool();
            publicJedis = publicJedisPool.getResource();
            publicJedis.del(key);
        } catch (Exception e) {
            publicJedisPool.returnBrokenResource(publicJedis);
            throw new Exception("删除reids失败，key:" + key, e);
        } finally {
            if (publicJedis != null) {
                publicJedisPool.returnResource(publicJedis);
            }
        }
    }

    /**
     * 根据大类和小类编码获取小类名称
     * @param groupId
     * @param code
     * @return
     * @throws Exception
     */
    public static String getDictName(String groupId,String code)throws Exception{
        try {
            if(StringUtils.isNotEmpty(groupId)&&StringUtils.isNotEmpty(code)) {
                String key= Constants.DICTIONGROUPKEY.replace("{groupId}", groupId);
                String result=JedisUtil.getValue(key);
                String[] dictionaryArray=result.split(",");
                for (String bean:dictionaryArray){
                    String[] dictbean=bean.split(":");
                    if(dictbean!=null&&dictbean.length==2) {
                        if (bean.split(":")[0].equals(code)) {
                            return bean.split(":")[1];
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("字典组转换出错,groupId:" + groupId + ",code:" + code);
            throw e;
        }
        return "";
    }

    /**
     * 根据字典组编码获取字典数据列表
     * @param groupId
     * @return
     * @throws Exception
     */
    public static List<DictVO> getDictList(String groupId)throws Exception{
        List<DictVO> list=new ArrayList<DictVO>();
        try {
            if(StringUtils.isNotBlank(groupId)){
                String key= Constants.DICTIONGROUPKEY.replace("{groupId}", groupId);
                String result=JedisUtil.getValue(key);
                if(StringUtils.isNotBlank(result)) {
                    String[] dictionaryArray = result.split(",");
                    for (String bean : dictionaryArray) {
                        String[] dictbean = bean.split(":");
                        if (dictbean != null && dictbean.length == 2) {
                            DictVO vo=new DictVO();
                            vo.setCode(dictbean[0]);
                            vo.setName(dictbean[1]);
                            list.add(vo);
                        }
                    }
                }else{
                    throw new Exception("不存在此字典组编码："+groupId);
                }
            }
        } catch (Exception e) {
            logger.error("字典组转换出错,groupId:" + groupId+","+e.getMessage());
            throw e;
        }
        return list;
    }
}
