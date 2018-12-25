package com.fly.http;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * RestTemplateTest
 * 
 * @author 00fly
 * @version [版本号, 2018年11月6日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class RestTemplateTest
{
    private static final Logger LOGGER = LoggerFactory.getLogger(RestTemplateTest.class);
    
    private static RestTemplate restTemplate;
    
    static
    {
        // 配置proxy，connectTimeout，readTimeout等参数
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(1000);
        requestFactory.setReadTimeout(1000);
        restTemplate = new RestTemplate(requestFactory);
    }
    
    @Test
    public void testGET()
    {
        /**** url支持占位符 ****/
        String url = "http://localhost:8080/rest/user/list/{page}";
        String response = restTemplate.getForObject(url, String.class, 1);
        LOGGER.info("ResponseBody={}", response);
        
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class, 1);
        LOGGER.info("ResponseEntity={}", responseEntity);
    }
    
    @Test
    public void testPOST()
    {
        /**** url支持占位符 ****/
        String url = "http://localhost:8080/rest/user/list/{page}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("username", "user001");
        params.add("password", "123456");
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        ResponseEntity<String> responseEntity;
        
        String response = restTemplate.postForObject(url, requestEntity, String.class, 1);
        LOGGER.info("ResponseBody={}", response);
        
        responseEntity = restTemplate.postForEntity(url, requestEntity, String.class, 1);
        LOGGER.info("ResponseEntity={}", responseEntity);
    }
    
    /**
     * 演示@RequestBody请求
     */
    @Test
    public void testJsonRequestBody()
    {
        String url = "http://127.0.0.1/api/common/search";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        
        // 方式1
        SearchReq req = new SearchReq();
        req.setPageNo(1);
        req.setPageSize(2);
        HttpEntity<SearchReq> requestEntity = new HttpEntity<>(req, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
        LOGGER.info("ResponseEntity={}", responseEntity);
        
        // 方式2
        Map<String, String> map = new HashMap<>();
        map.put("pageNo", "1");
        map.put("pageSize", "10");
        HttpEntity<SearchReq> requestEntity2 = new HttpEntity<>(req, headers);
        ResponseEntity<String> responseEntity2 = restTemplate.postForEntity(url, requestEntity2, String.class);
        LOGGER.info("ResponseEntity={}", responseEntity2);
    }
    
    @Test
    public void testExchange()
    {
        /**** url支持占位符 ****/
        String url = "http://localhost:8080/rest/user/list/{page}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("username", "用户1");
        params.add("password", "123456");
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        ResponseEntity<String> responseEntity;
        
        // GET、POST
        responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class, 1);
        LOGGER.info("ResponseEntity={}", responseEntity);
        
        Map<String, Integer> vars = Collections.singletonMap("page", 1);
        responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class, vars);
        LOGGER.info("ResponseEntity={}", responseEntity);
    }
}

class SearchReq
{
    Integer pageNo = 1;
    
    Integer pageSize = 10;
    
    String keyword;
    
    public Integer getPageNo()
    {
        return pageNo;
    }
    
    public void setPageNo(Integer pageNo)
    {
        this.pageNo = pageNo;
    }
    
    public Integer getPageSize()
    {
        return pageSize;
    }
    
    public void setPageSize(Integer pageSize)
    {
        this.pageSize = pageSize;
    }
    
    public String getKeyword()
    {
        return keyword;
    }
    
    public void setKeyword(String keyword)
    {
        this.keyword = keyword;
    }
}
