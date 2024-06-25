package com.example.demo.chapter2.controller;

import com.example.demo.chapter2.dto.BookPageDto;
import com.example.demo.chapter2.entity.Book;
import com.example.demo.chapter2.service.BookService;
import com.example.demo.chapter3.entity.User;
import com.example.demo.chapter3.service.UserService;
import com.example.demo.springboot2023.utils.JwtUtil;
import com.example.demo.springboot2023.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("chapter4/Book")
@Api(tags="Book实体的Controller")
public class BookController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;

    @ApiOperation(value="book实体新增和修改")
    @RequestMapping(method = RequestMethod.POST,value = "/addOrUpdate")
    public Result addOrUpdate(@RequestBody Book book){
        Result result = new Result();

        if(bookService.exist(book)){
            result.fail("书籍名已存在");
            return result;
        }
        bookService.addOrUpdate(book);
        return result;
    }

    @ApiOperation(value="获得book的list列表信息支持name搜索")
    @RequestMapping(method = RequestMethod.POST,value = "/list")
    public Result list(String name){
        Result result = new Result();
        result.setData(bookService.list(name));
        String token = request.getHeader("token");
        Result resultToken = JwtUtil.validateToken(token);
        if("200".equals(resultToken.getStatusCode())){
            User user = userService.getById(resultToken.getData().toString());
            System.out.println("user.getUsername()="+user.getUsername());
            System.out.println("user.getPassword()="+user.getPassword());
        }else{
            result.fail(resultToken.getMessage());
        }
        return result;
    }

    @ApiOperation(value="通过ids，批量删除book记录,ids中根据;进行分割")
    @ApiImplicitParams({
            @ApiImplicitParam(name="ids",value="id",paramType = "query",required = true),
            })
    @RequestMapping(method = RequestMethod.POST,value = "/deleteByIds")
    public Result deleteByIds(String ids){
        Result result = new Result();
        bookService.deleteById(ids);
        return result;
    }
    @ApiOperation(value="获得book的分页信息，支持name搜索")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name="pageNum",value="当前页码",paramType = "query",required = true),
//            @ApiImplicitParam(name="pageSize",value="每页显示条数",paramType = "query",required = true),
//            @ApiImplicitParam(name="name",value="搜索关键字",paramType = "query")
//    })
    @RequestMapping(method = RequestMethod.POST,value = "/page")
    public Result page(@RequestBody BookPageDto bookPageDto){
        Result result = new Result();
        String token = request.getHeader("token");
        Result resultToken = JwtUtil.validateToken(token);
        if("200".equals(resultToken.getStatusCode())){
            result.setData(bookService.page(bookPageDto));
        }else{
            result.fail(resultToken.getMessage());
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/pageByUsername")
    public Result pageByUsername(Integer pageNum,Integer pageSize,String username){
        Result result = new Result();
        result.setData(bookService.pageByUsername(pageNum,pageSize,username));
        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/pageByUsername2")
    public Result pageByUsername2(Integer pageNum,Integer pageSize,String username){
        Result result = new Result();
        result.setData(bookService.pageByUsername2(pageNum,pageSize,username));
        return result;
    }
}
