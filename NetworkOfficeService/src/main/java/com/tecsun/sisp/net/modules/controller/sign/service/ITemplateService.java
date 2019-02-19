package com.tecsun.sisp.net.modules.controller.sign.service;

import java.util.Map;

/**
 * Created by lorn on 2018/7/16.
 */
public interface ITemplateService {

    public Map<String,String> getData(Map<String, String> map)  throws Exception ;
    public String createSignFile(String tempalteName, Map<String, String> map)  throws Exception ;
}
