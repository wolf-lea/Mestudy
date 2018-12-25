package com.fly.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fly.demo.entity.Measure;
import com.fly.demo.service.MeasureService;

/**
 * RestMeasureController
 * 
 * @author 00fly
 * @version [版本号, 2018-11-06]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/rest/measure")
public class RestMeasureController
{
    @Autowired
    MeasureService measureService;
    
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Measure view(@PathVariable Long id)
    {
        Measure measure = measureService.queryById(id);
        return measure;
    }
    
    @RequestMapping(value = "/list/{pageNo}", method = RequestMethod.GET)
    @ResponseBody
    public List<Measure> list(@PathVariable Integer pageNo, @RequestParam(defaultValue = "5") int pageSize)
    {
        pageSize = Math.max(pageSize, 2);
        List<Measure> list = measureService.queryForPagination(new Measure(), pageNo, pageSize).getItems();
        return list;
    }
    
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Measure> all()
    {
        List<Measure> list = measureService.queryAll();
        return list;
    }
    
}