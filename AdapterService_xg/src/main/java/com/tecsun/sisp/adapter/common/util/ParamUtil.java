package com.tecsun.sisp.adapter.common.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by linzetian on 2017/5/23.
 */
public class ParamUtil {

    /**
     * 为入参添加分页数据
     * @param param
     * @return
     */
    public static Map<String,Object> addPage(Map<String,Object> param){
        Map<String,Object> resParam = new HashMap<>();
        Page<Map<String,Object>> page = new Page<>();
        if(param != null) {
            Object pagenoObj = param.get("pageno");
            Object pagesizeObj = param.get("pagesize");
            int pageNo = pagenoObj != null ? Integer.parseInt(pagenoObj.toString()) : 1;
            int pageSize = pagesizeObj !=null ? Integer.parseInt(pagesizeObj.toString()) : 10;
            pageNo = pageNo == 0 ? 1 : pageNo;
            pageSize = pageSize == 0 ? 10 : pageSize;
            page.setPageno(pageNo);
            page.setPagesize(pageSize);
            param.put("pageno",pageNo);
            param.put("pagesize",pageSize);
        }
        resParam.putAll(param);
        resParam.put("page",page);
        return resParam;
    }
    /**
     * 返回Page类字段
     * @param
     * @return
     */
    public static Map<String,Object> backPage(Object pageno,Object pagesize,Object count,Object data){
        Map<String,Object> page = new HashMap<>();
         page.put("pageno",pageno);
         page.put("pagesize",pagesize);
         page.put("count",count);
         page.put("data",data);
        return page;
    }

    public static String checkNullOrEmplty(Map<String,Object> map ,String ... args){
        for (String arg : args){
            Object obj = map.get(arg);
            boolean isNE = isNullOrEmplty(obj);
            if(isNE){
                return String.format("字段%s不能为空",arg);
            }
        }
        return "";
    }

    private static boolean isNullOrEmplty(Object obj){
        if(obj == null){
            return true;
        }

        if(obj instanceof CharSequence){
            return ((CharSequence)obj).length() == 0;
        }

        if(obj instanceof Map){
            return ((Map)obj).isEmpty();
        }

        if(obj instanceof Collection){
            return ((Collection)obj).isEmpty();
        }
        return false;
    }
}
