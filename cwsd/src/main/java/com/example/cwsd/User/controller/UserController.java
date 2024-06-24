package com.example.cwsd.User.controller;

import com.third.zhgd.User.dto.UserLoginDto;
import com.third.zhgd.User.entity.User;
import com.third.zhgd.User.service.UserService;
import com.third.zhgd.other.utils.JwtUtil;
import com.third.zhgd.other.utils.MD5Util;
import com.third.zhgd.other.utils.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.third.zhgd.other.utils.JwtUtil.validateToken;


/**
 * <p>
 * 用户表 前端控制器,表的注释
 * </p>
 *
 * @author Csk
 * @since 2024-04-21
 */
@RestController
@RequestMapping("/User/")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(method = RequestMethod.POST,value="/update")
    @ApiOperation(value = "用户登录接口")
    public Result update(@RequestBody User user) throws Exception {
        Result result = new Result();
        User temp = userService.getById(user.getId());
        if(user.getPassword().isEmpty()){
            user.setPassword(temp.getPassword());
        }else{
            String pwdMd5 = MD5Util.getEncode(user.getPassword(),user.getSalt());
            user.setPassword(pwdMd5);
        }
        userService.updateById(user);
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
        if(userService.exit(user.getAccount())){
            result.fail("用户名"+user.getAccount()+"已存在");
            return result;
        }
        userService.register(user);
        result.success("注册成功");
        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value="/add")
    @ApiOperation(value = "添加新用户")
    public Result add(Integer num) throws Exception {
        Result result = new Result();
        userService.add(num);
        result.success("用户添加成功");
        return result;
    }

    @ApiOperation(value = "保存修改User信息")
    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public Result addOrUpdate(@RequestBody User user) {
        Result result = new Result();

        //交给业务去处理，service
        //userService.addOrUpdate(user);
        userService.updateById(user);
        return result;
    }

    @ApiOperation(value = "批量删除记录")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "ids",required = true,paramType = "query",value = "需要删除的多个id，用逗号,隔开")
    })
    @RequestMapping(method = RequestMethod.POST,value = "/deleteByIds")
    public Result deleteByIds(String ids) {
        Result result = new Result();
        userService.deleteByIds(ids);
        result.success("删除成功");
        return result;
    }


    @ApiOperation(value = "查询所有记录")
    @RequestMapping(method = RequestMethod.POST,value = "/list")
    public Result list(){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(userService.list());
        return result;
    }

    @ApiOperation(value = "分页获取记录")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNum",required = true,paramType = "query",value = "当前页码"),
        @ApiImplicitParam(name = "pageSize",required = true,paramType = "query",value = "每页记录数")
    })
    @RequestMapping(method = RequestMethod.POST,value = "/page")
    public Result page( Integer pageNum,Integer pageSize ,String name){
        Result result = new Result();
        result.success("分页获取成功");
        result.setData(userService.page(pageNum,pageSize,name));
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

}
