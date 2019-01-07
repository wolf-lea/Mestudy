package com.tecsun.sisp.fakamanagement.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;

import com.tecsun.sisp.fakamanagement.common.Result;
import com.tecsun.sisp.fakamanagement.common.utils.StringUtils;
import com.tecsun.sisp.fakamanagement.modules.controller.BaseController;

import javax.validation.Valid;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by linzetian on 2016/11/10.
 * Description:controller层校验切面类
 * 主要用户校验数据
 */
@Component
@Aspect
public class ValidAspect {

    /**
     * 含ResponseBody标识的方法进入切面编程
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("@annotation(org.springframework.web.bind.annotation.ResponseBody)")
    public Object doValid(ProceedingJoinPoint pjp) throws Throwable{
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        if(!Result.class.equals(method.getReturnType())){
            pjp.proceed();
        }
        Object[] args = pjp.getArgs();
        Annotation[][] annotations = method.getParameterAnnotations();
        for(int i = 0; i < annotations.length; i++){
            if(!hasValidAnnotation(annotations[i])){
                continue;
            }
            if(!(i < annotations.length-1 && args[i+1] instanceof BindingResult)){
                //验证对象后面没有跟bindingResult,事实上如果没有应该到不了这一步
                continue;
            }
            BindingResult result = (BindingResult) args[i+1];
            Result rt = getError(result);
            if(BaseController.RESULT_MESSAGE_ERROR.equals(rt.getStatusCode())){
                return rt;
            }
        }
        return pjp.proceed();
    }

    /**
     * 判断是否包含Valid 和Validated标签
     * @param annotations
     * @return
     */
    private boolean hasValidAnnotation(Annotation[] annotations){
        if(annotations == null){
            return false;
        }
        for(Annotation annotation : annotations){
            if(annotation instanceof Valid
                    || annotation instanceof Validated){
                return true;
            }
        }
        return false;
    }

    /**
     * 获取校验失败的错误信息
     * @param result 校验的结果
     * @return 校验通过 “” 校验失败：错误信息
     */
    private static Result getError(BindingResult result){
        try {
            StringBuilder errorMg = new StringBuilder();
            if(result.hasErrors()){
                for(ObjectError error : result.getAllErrors()){
                    String fieldName =   ((FieldError) error).getField();
                    errorMg.append(fieldName+error.getDefaultMessage()+",");
                }
                String errors = StringUtils.trimEnd(errorMg.toString(), ",");
                return new Result().error(errors);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result().ok("校验通过");
    }
}
