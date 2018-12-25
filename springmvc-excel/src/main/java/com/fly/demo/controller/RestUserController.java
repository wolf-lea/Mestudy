package com.fly.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fly.demo.entity.User;
import com.fly.demo.service.UserService;

/**
 * RestUserController
 * 
 * @author 00fly
 * @version [版本号, 2018-11-06]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/rest/user")
public class RestUserController
{
    @Autowired
    UserService userService;
    
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    @ResponseBody
    public User view(@PathVariable Long id)
    {
        User user = userService.queryById(id);
        return user;
    }
    
    @RequestMapping(value = "/list/{pageNo}", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<User> list(@PathVariable Integer pageNo, @RequestParam(defaultValue = "5") int pageSize)
    {
        pageSize = Math.max(pageSize, 2);
        List<User> list = userService.queryForPagination(new User(), pageNo, pageSize).getItems();
        return list;
    }
    
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<User> all()
    {
        List<User> list = userService.queryAll();
        return list;
    }
    
}