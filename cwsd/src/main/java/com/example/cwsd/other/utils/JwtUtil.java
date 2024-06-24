package com.example.cwsd.other.utils;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * jwt工具类
 *
 * @author zhangmingcheng
 */
@Configuration
public class JwtUtil {

    private static long EXPIRATION_TIME = 3600000 * 24; // 24 hour 过期时间

    private static String SECRET = "MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjY34DFDSSSd";// 秘钥

    private static final String USER_ID = "id";


    /**
     * 生成jwtToken
     *
     * @param id
     * @return
     */
    public static String generateToken(String id) {
        HashMap<String, Object> map = new HashMap<>();
        // you can put any data in the map
        map.put(USER_ID, id);


        String jwt = Jwts.builder().setClaims(map).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
        return jwt;
    }

    /**
     * 校验jwtToken
     *
     * @param token
     * @return
     */
   // public static Result validateToken(String token) {//验证
    public static Result validateToken(String token) {//验证

        Result result = new Result();
        //AssertUtils.assertNotNull(token, ResultType.AGAIN_LOGIN, "Missing token");

        if(StringUtils.isBlank(token)){
            result.fail("missing token");
            return result;
            //System.out.println("Missing token");
        }

        try {
            Map<String, Object> body = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
            String id = (String) (body.get(USER_ID));

            if(StringUtils.isBlank(id)){
                result.fail("Wrong token without id");
                return result;
            }


            result.setData(id);
            return result;
        }catch (Exception e){
            result.fail(e.toString());
           // System.out.println("Wrong token without id");
            return result;
        }


    }


    public static void main(String[] args) {
        String id = "6876ab1fb942fd4f27ff15c6ac4ba1fe";

        String token = generateToken(id);
        System.out.println(token);


        //token = " ";
        token = "eyJhbGciOiJIUzUxMiJ8.eyJpZCI6IjY4NzZhYjFmYjk0MmZkNGYyN2ZmMTVjNmFjNGJhMWZlIiwiZXhwIjoxNjk4ODIxMzIxfQ.lXgsKcHzJiuKS9-N7AeLMAMkZco-LHHoZXbh1enCN43Ss3JZr5WlxIAExRu2KECCrK6ko_ELgkypoTRuUtnD1B";
        Result result = validateToken(token);
        if("200".equals(result.getStatusCode())){
            System.out.println(result.getData());
        }else{
            System.out.println(result.getMessage());
        }

//        HashMap<String, Object> map = new HashMap<>();
//        // you can put any data in the map
//        map.put("name", id);
//        token = Jwts.builder().setClaims(map).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
//
//         String result = "";
//        try {
//
//            result = validateToken(token);
//            System.out.println(result);
//        }catch (Exception e){
//            System.out.println(e.toString());
//        }

    }
}