import axios from "axios"
import qs from "qs"
import { getStore } from "@/lib/storage";
axios.defaults.baseURL="/api";

axios.interceptors.response.use(res=>{
    //respose 200 正常返回
    var result = res.data;
    var code = result.statusCode; 
    switch(code){
      case '200':{
        return Promise.resolve(result)
      };
      case '400':{
        return Promise.reject(result.message);
      };
      case '401':{
        return Promise.reject("权限不足");
      };
      case '404':{
        return Promise.reject("接口不存在");
      };
      default:{
        
      }
    } 
},err=>{
    //respose 出错了
    return Promise.reject(err)
})

export function get(url,params){
    var token = getStore("token")
    return axios({
        method:'get',
        url:`${url}`,
        params:params,
        headers:{
            'token':token
        }
    })
}

export function post(url,params){
    var token = getStore("token")
    return axios({
        method:'post',
        url:`${url}`,
        params:params,
        transformRequest:[function(data){
            return qs.stringify(data,{allowDots:true})
        }], 
        headers:{
            'token':token,
            'Content-Type':'application/x-www-from-urlencoded'
        }
    })
}


export function postJson(url,params){
    var token = getStore("token")
    return axios({
        method:'post',
        url:`${url}`,
        data:params,
        headers:{
            'token':token,
            'Content-Type':'application/json'
        }
    })
}



export function postt(url,params){
    var token = getStore("token")
    return axios({
        method:'post',
        url:`${url}`,
        data:params,
        transformRequest: [(data) => data], // 不再使用 qs 库进行参数序列化，直接传递 FormData
        headers:{
            'token':token,
            'Content-Type': 'multipart/form-data',
        }
    })
}