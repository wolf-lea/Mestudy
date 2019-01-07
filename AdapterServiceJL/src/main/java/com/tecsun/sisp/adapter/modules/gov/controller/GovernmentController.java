package com.tecsun.sisp.adapter.modules.gov.controller;

import com.tecsun.sisp.adapter.common.util.*;
import com.tecsun.sisp.adapter.modules.common.controller.BaseController;
import com.tecsun.sisp.adapter.modules.gov.entity.GovBean;
import com.tecsun.sisp.adapter.modules.gov.entity.GovernmentVO;
import com.tecsun.sisp.adapter.modules.so.entity.response.IncureTypeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 政务系统：新闻，通知，动态管理
 */

@Controller
@RequestMapping(value = "/adapter/gov")
public class GovernmentController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(GovernmentController.class);
    private String gov_url = Config.getInstance().get("gov_service_url");


    /**
     * 查询列表信息
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getNoticeList", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Result getCollectList(HttpServletRequest request) throws Exception {
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        Object jsonData = "";
        Page<GovernmentVO> page = new Page();
        try {
            String url = gov_url + Constants.GET_NOTICELIST+ "?" + request.getQueryString();
            jsonData = DictionaryUtil.getClientRequest(url);
            ResultPage data = JsonMapper.buildNormalMapper().fromJson(jsonData.toString(), ResultPage.class);
            if (data != null) {
                statusCode = data.getStatusCode();
                message = data.getMessage();
                page.setCount(data.getTotal());
                jsonData = data.getResult();
                if (jsonData != null) {
                    List<GovernmentVO> list = JsonHelper.jsonToList(JsonMapper.buildNormalMapper().toJson(data.getResult()), GovernmentVO.class);
                    page.setData(list);
                }
            }
        } catch (Exception e) {
        	e.printStackTrace();
            logger.error("查询列表信息出错", e);
        }
        return result(statusCode, message, page);
    }

    /**
     * 根据ID查询信息
     *
     * @param bean
     * @return
     */
    @RequestMapping(value = "/getNoticeById", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getNotieById(@RequestBody GovBean bean) throws Exception{
        if (bean.getId() == 0) return error("请输入查询id");
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        Object jsonData = "";
        try {
            String url = gov_url + Constants.GET_NOTICEBYID.replace("{id}", String.valueOf(bean.getId()));
            jsonData = DictionaryUtil.postClientRequest("", url);
            Result data = JsonMapper.buildNormalMapper().fromJson(jsonData.toString(), Result.class);
            if (data != null) {
                statusCode = data.getStatusCode();
                message = data.getMessage();
                jsonData = data.getData();
            }
        } catch (Exception e) {
            logger.error("查询详情出错", e);
        }
        return result(statusCode, message, jsonData);
    }
}
