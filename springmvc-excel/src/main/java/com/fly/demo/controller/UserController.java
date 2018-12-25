package com.fly.demo.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.RandomUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import com.fly.demo.entity.User;
import com.fly.demo.service.UserService;

/**
 * 
 * UserController
 * 
 * @author 00fly
 * @version [版本号, 2018-11-06]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping(value = "/user")
public class UserController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    UserService userService;
    
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
     * @param user
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@Valid @ModelAttribute("item") User user, Errors errors, Model model)
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
            model.addAttribute("page", userService.queryForPagination(null, 1, 10));
            return "/user/show";
        }
        userService.saveOrUpdate(user);
        return "redirect:/user/list";
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
        userService.deleteById(id);
        return "redirect:/user/list";
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
        User user = new User();
        if (RandomUtils.nextInt(1, 10) > 1)
        {
            user.setName(getRandomName(RandomUtils.nextInt(2, 5)));
            user.setAge(RandomUtils.nextInt(15, 65));
        }
        PaginationSupport<User> page = userService.queryForPagination(null, pageNo, 10);
        model.addAttribute("item", user);
        model.addAttribute("page", page);
        return "/user/show";
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
        model.addAttribute("item", userService.queryById(id));
        model.addAttribute("page", userService.queryForPagination(null, 1, 10));
        return "/user/show";
    }
    
    /**
     * 导出数据
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public String export(HttpServletResponse response)
    {
        try
        {
            LOGGER.info(" excel 导出");
            HSSFWorkbook wb = export(userService.queryAll());
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "inline;filename=" + URLEncoder.encode("用户列表.xls", "UTF8"));
            OutputStream ouputStream = response.getOutputStream();
            wb.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        }
        catch (IOException e)
        {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }
    
    private HSSFWorkbook export(List<User> list)
    {
        String[] excelHeader = {"ID", "姓名", "年龄"};
        HSSFWorkbook wb = new HSSFWorkbook();
        int rownum = 10000;
        for (int m = 0; m < (list.size() + rownum - 1) / rownum; m++)
        {
            HSSFSheet sheet = wb.createSheet("用户" + (m + 1));
            // 第一列单元格宽度设置为20个字符宽度
            sheet.setColumnWidth(0, 20 * 256);
            sheet.setColumnWidth(1, 20 * 256);
            sheet.setColumnWidth(2, 30 * 256);
            HSSFRow row = sheet.createRow(0);
            HSSFCellStyle style = wb.createCellStyle();
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            for (int i = 0; i < excelHeader.length; i++)
            {
                HSSFCell cell = row.createCell(i);
                cell.setCellValue(excelHeader[i]);
                cell.setCellStyle(style);
            }
            for (int i = m * rownum; i < (m + 1) * rownum && i < list.size(); i++)
            {
                row = sheet.createRow(i % rownum + 1);
                User it = list.get(i);
                row.createCell(0).setCellValue(it.getId());
                row.createCell(1).setCellValue(it.getName());
                row.createCell(2).setCellValue(it.getAge());
            }
        }
        return wb;
    }
    
    /**
     * 随机长度为length的中文字符串
     * 
     * @param length 长度
     * @return
     * @see [类、类#方法、类#成员]
     */
    private String getRandomName(int length)
    {
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < length; i++)
        {
            name.append(getRandomChinese());
        }
        return name.toString();
    }
    
    /**
     * 随机汉字
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    private char getRandomChinese()
    {
        String str = "";
        int hightPos; //
        int lowPos;
        hightPos = (176 + Math.abs(RandomUtils.nextInt(0, 39)));
        lowPos = (161 + Math.abs(RandomUtils.nextInt(0, 93)));
        byte[] b = new byte[2];
        b[0] = (Integer.valueOf(hightPos)).byteValue();
        b[1] = (Integer.valueOf(lowPos)).byteValue();
        try
        {
            str = new String(b, "GBK");
        }
        catch (UnsupportedEncodingException e)
        {
            LOGGER.error(e.getMessage(), e);
        }
        return str.charAt(0);
    }
}
