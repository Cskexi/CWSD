package com.example.demo.aop;

import com.example.demo.aop.entity.SysLog;
import com.example.demo.aop.service.SysLogService;
import com.example.demo.springboot2023.utils.DateTool;
import com.example.demo.springboot2023.utils.JwtUtil;
import com.example.demo.springboot2023.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Aspect
@Component
public class LogAspect {

    @Autowired
    private SysLogService sysLogService;
//注解
    @Pointcut("@annotation(com.example.demo.aop.OperLog)")//注解类
    public void pointcut() {
    }

    @AfterReturning("pointcut()")
    public void afterReturning(JoinPoint joinPoint) {

        // 获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes
                .resolveReference(RequestAttributes.REFERENCE_REQUEST);

        SysLog operlog = new SysLog();
        try {
            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            // 获取切入点所在的方法
            Method method = signature.getMethod();
            // 获取操作
            OperLog opLog = method.getAnnotation(OperLog.class);
            if (opLog != null) {
                String operModul = opLog.operModul();
                String operType = opLog.operType();
                log.info("operType="+operType);
                log.info("operModul="+operModul);
                operlog.setContent(operModul); // 操作模块
                operlog.setMethod(operType); //操作类型 GET POST
            }
            // 获取请求的类名
            String className = joinPoint.getTarget().getClass().getName();
            // 获取请求的方法名
            String methodName = method.getName();
            methodName = className + "." + methodName;

            operlog.setClassPath(methodName); // 请求方法

            // 请求的参数
            Map<String, String> rtnMap = converMap(request.getParameterMap());
            // 将参数所在的数组转换成json
            String params = rtnMap.toString();
                    //JSONObject.toJSONString(rtnMap);

            //获取登录者的id
           Result result = JwtUtil.validateToken(request.getHeader("token"));
           String userId = "";
           if ("200".equals(result.getStatusCode())){
               userId = result.getData().toString();
           }

            operlog.setParam(params); // 请求参数
            operlog.setUserId(userId); // 请求用户ID
            operlog.setIp(IpUtil.getIpAddress(request)); // 请求IP
            operlog.setMethodPath(request.getRequestURI());//请求URI
            operlog.setCreateTime(DateTool.getCurrTime()); // 创建时间
            sysLogService.save(operlog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 转换request 请求参数
     *
     * @param paramMap request获取的参数数组
     */
    public Map<String, String> converMap(Map<String, String[]> paramMap) {
        Map<String, String> rtnMap = new HashMap<String, String>();
        for (String key : paramMap.keySet()) {
            rtnMap.put(key, paramMap.get(key)[0]);
        }
        return rtnMap;
    }

}
