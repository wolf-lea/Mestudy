package com.fly.test.restful;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSONObject;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/applicationContext.xml", "/spring-mvc.xml"})
public class RestControllerTest
{
    private static final Logger LOGGER = LoggerFactory.getLogger(RestControllerTest.class);
    
    @Autowired
    private WebApplicationContext wac;
    
    private MockMvc mockMvc;
    
    @Before
    public void setup()
    {
        mockMvc = webAppContextSetup(wac).build();
        LOGGER.info("★★★★★★★★ WebApplicationContext = {}", wac);
        int i = 1;
        for (String beanName : wac.getBeanDefinitionNames())
        {
            LOGGER.info("{}.\t{}", i, beanName);
            i++;
        }
    }
    
    /**
     * 测试 RestAPI
     * 
     * @throws Exception
     * 
     * @see [类、类#方法、类#成员]
     */
    @Test
    public void testRestAPI()
        throws Exception
    {
        // get、post
        mockMvc.perform(get("/rest/user/list/1").param("pageSize", "5")).andDo(MockMvcResultHandlers.print());
        mockMvc.perform(get("/rest/user/list/1").param("pageSize", "10")).andDo(MockMvcResultHandlers.print());
        mockMvc.perform(post("/rest/user/list/1").param("pageSize", "10")).andDo(MockMvcResultHandlers.print());
    }
    
    @Test
    public void testJsonBodyRest()
        throws Exception
    {
        // RequestBody请求1
        JsonBody jsonBody = new JsonBody();
        jsonBody.setId("1");
        jsonBody.setName("name");
        String requestJson = JSONObject.toJSONString(jsonBody);
        MockHttpServletRequestBuilder requestBuilder = post("/rest/user/list/1").contentType(MediaType.APPLICATION_JSON).content(requestJson);
        mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print());
        
        // RequestBody请求2
        mockMvc.perform(post("/common/search").contentType(MediaType.APPLICATION_JSON).content("{ \"pageNo\": \"1\", \"pageSize\": \"5\" }")).andDo(MockMvcResultHandlers.print());
    }
}

/**
 * 
 * 请求体@ResponseBody
 * 
 * @author 00fly
 * @version [版本号, 2018年11月12日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
class JsonBody
{
    private String id;
    
    private String name;
    
    public String getId()
    {
        return id;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
}
