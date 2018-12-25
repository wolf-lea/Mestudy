package com.fly.demo.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

/**
 * 
 * user表对应的User实体
 * 
 * @author 00fly
 * @version [版本号, 2018-11-06]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class User
{
    // id
    private Long id;
    
    // name
    @NotBlank(message = "姓名不能为空")
    @Length(min = 2, max = 10, message = "长度必须在{min}-{max}之间")
    private String name;
    
    // age
    @NotNull(message = "年龄不能为空")
    @Range(min = 18, max = 60, message = "年龄必须在{min}-{max}之间")
    private Integer age;
    
    public Long getId()
    {
        return id;
    }
    
    public void setId(Long id)
    {
        this.id = id;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public void setAge(Integer age)
    {
        this.age = age;
    }
    
    public Integer getAge()
    {
        return this.age;
    }
}
