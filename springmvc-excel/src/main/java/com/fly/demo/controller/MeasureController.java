package com.fly.demo.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fly.common.PaginationSupport;
import com.fly.demo.entity.Measure;
import com.fly.demo.service.MeasureService;

/**
 * 
 * MeasureController
 * 
 * @author 00fly
 * @version [版本号, 2018-11-06]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping(value = "/measure")
public class MeasureController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MeasureController.class);
    
    @Autowired
    MeasureService measureService;
    
    /**
     * String 日期转换为 Date
     * 
     * @param dataBinder
     * @see [类、类#方法、类#成员]
     */
    @InitBinder
    public void dataBinder(WebDataBinder dataBinder)
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(df, true));
    }
    
    /**
     * 新增/更新数据
     * 
     * @param measure
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@Valid @ModelAttribute("item") Measure measure, Errors errors, Model model)
    {
        if (errors.hasErrors())
        {
            StringBuilder errorMsg = new StringBuilder();
            for (ObjectError error : errors.getAllErrors())
            {
                errorMsg.append(error.getDefaultMessage()).append(" ");
            }
            if (errorMsg.length() > 0)
            {
                LOGGER.info("errors message={}", errorMsg);
            }
            model.addAttribute("page", measureService.queryForPagination(null, 1, 10));
            return "/measure/show";
        }
        measureService.saveOrUpdate(measure);
        return "redirect:/measure/list";
    }
    
    /**
     * 删除数据
     * 
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id)
    {
        measureService.deleteById(id);
        return "redirect:/measure/list";
    }
    
    /**
     * 列表展示
     * 
     * @param model
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
    public String list(@RequestParam(defaultValue = "1") int pageNo, Model model)
    {
        Measure measure = new Measure();
        if (RandomUtils.nextInt(1, 10) > 1)
        {
            measure.setNo(RandomStringUtils.randomNumeric(5));
            measure.setAs(RandomUtils.nextDouble(0, 1000));
            measure.setCr(RandomUtils.nextDouble(0, 1000));
            measure.setCd(RandomUtils.nextDouble(0, 1000));
            measure.setCu(RandomUtils.nextDouble(0, 1000));
            measure.setPb(RandomUtils.nextDouble(0, 1000));
            measure.setZn(RandomUtils.nextDouble(0, 1000));
            measure.setNi(RandomUtils.nextDouble(0, 1000));
            measure.setHg(RandomUtils.nextDouble(0, 1000));
            measure.setSn(RandomUtils.nextDouble(0, 1000));
            measure.setCo(RandomUtils.nextDouble(0, 1000));
            measure.setAg(RandomUtils.nextDouble(0, 1000));
            measure.setSb(RandomUtils.nextDouble(0, 1000));
            measure.setBa(RandomUtils.nextDouble(0, 1000));
            measure.setMg(RandomUtils.nextDouble(0, 1000));
            measure.setTi(RandomUtils.nextDouble(0, 1000));
            measure.setW(RandomUtils.nextDouble(0, 1000));
            measure.setAl(RandomUtils.nextDouble(0, 1000));
            measure.setTh(RandomUtils.nextDouble(0, 1000));
            measure.setSr(RandomUtils.nextDouble(0, 1000));
            measure.setCs(RandomUtils.nextDouble(0, 1000));
        }
        PaginationSupport<Measure> page = measureService.queryForPagination(null, pageNo, 10);
        model.addAttribute("item", measure);
        model.addAttribute("page", page);
        return "/measure/show";
    }
    
    /**
     * 编辑数据
     * 
     * @param id
     * @param model
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable Long id, Model model)
    {
        model.addAttribute("item", measureService.queryById(id));
        model.addAttribute("page", measureService.queryForPagination(null, 1, 10));
        return "/measure/show";
    }
}
