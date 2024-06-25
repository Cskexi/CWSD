package com.example.demo.chapter3.controller;


import com.example.demo.chapter3.dto.UserLoginDto;
import com.example.demo.chapter3.entity.User;
import com.example.demo.chapter3.service.UserService;
import com.example.demo.order.entity.Order;
import com.example.demo.springboot2023.utils.JwtUtil;
import com.example.demo.springboot2023.utils.MD5Util;
import com.example.demo.springboot2023.utils.Result;
import io.jsonwebtoken.Jwt;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;

import static com.example.demo.springboot2023.utils.JwtUtil.validateToken;
@Service
@RestController
@RequestMapping("chapter3/user")
@Api(tags="User实体的Controller")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST,value="/trans")
    @ApiOperation(value = "用户交易")
    public Result trans(String ids,Double ballance) throws Exception {
        Result result = new Result();
        //第一个转账人，逗号隔开
        userService.trans(ids,ballance);
        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value="/login")
    @ApiOperation(value = "用户登录接口")
    public Result login(@RequestBody UserLoginDto userLoginDto) throws Exception {
        Result result = new Result();
        User userExit  = userService.login(userLoginDto);
        if(userExit!=null){
            if(userExit.getPassword().equals(MD5Util.getEncode(userLoginDto.getPassword(),userExit.getSalt()))){
                result.success("登录成功");
                String token = JwtUtil.generateToken(userExit.getId());
                result.setData(token);
            }else {
                result.fail("密码不正确");
            }
        }else{
            result.fail("用户名不存在");
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value="/register")
    @ApiOperation(value = "用户登录接口")
    public Result register(@RequestBody User user) throws Exception {
        Result result = new Result();
        if(userService.exit(user.getUsername())){
            result.fail("用户名"+user.getUsername()+"已存在");
            return result;
        }
        userService.register(user);
        result.success("注册成功");
        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value="/getByToken")
    @ApiOperation(value = "用户登录接口")
    public Result getByToken(String token) throws Exception {
        Result result = validateToken(token);
        String id = result.getData().toString();
        result.setData(userService.getById(id));
        result.success("通过");
        return result;
    }

    @ApiOperation(value = "分页获取记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name="username",value="搜索关键字",paramType = "query")
    })
    @RequestMapping(method = RequestMethod.POST,value = "/list")
    public Result list(String username){
        Result result = new Result();
        result.setData(userService.list(username));
        result.success("分页获取成功");
        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/page")
    public Result page(Integer pageNum,Integer pageSize,String username){
        //trace  debug info warn < error 日志等级
        Result result = new Result();
        result.setData(userService.page(pageNum,pageSize,username));
        return result;
    }

    @ApiOperation(value="通过ids，批量删除book记录,ids中根据;进行分割")
    @ApiImplicitParams({
            @ApiImplicitParam(name="ids",value="id",paramType = "query",required = true),
    })
    @RequestMapping(method = RequestMethod.POST,value = "/deleteByIds")
    public Result deleteByIds(String ids){
        Result result = new Result();
        userService.deleteById(ids);
        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/stateChangeById")
    public Result stateChangeById(String id,Integer state){
        Result result = new Result();
        userService.changeState(id,state);
        result.success("状态更改成功");
        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/typeChangeById")
    public Result typeChangeById(String id,Integer type){
        Result result = new Result();
        userService.typeState(id,type);
        result.success("身份更改成功");
        return result;
    }

    @ApiOperation(value = "保存修改Order信息")
    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public Result addOrUpdate(@RequestBody User user) {
        Result result = new Result();
        //交给业务去处理，service
        result.setData(userService.addOrUpdate(user));
        return result;
    }


}
