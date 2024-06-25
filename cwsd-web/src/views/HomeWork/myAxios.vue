<template>
    <div class="myAxios">
      <h1>myAxios</h1>
      <button @click="getRequest">get-request</button><br>
      <button @click="postRequest">post-request</button><br>
      <button @click="postJsonRequest">postJson-request</button><br>
    </div>
  </template>
  
  <script>
  import axios from "axios"
  import qs from "qs"
  export default {

    name: 'myAxios', 
    data(){
      return{
      }
    },
    methods:{
      getRequest(){
        // 为给定 ID 的 user 创建请求
        axios.get('/test/get?id=123')
          .then(function (response) {
            var result = response.data; 
            var code = result.statusCode; 
            console.log(code)
            console.log(response);
          })
          .catch(function (error) {
            console.log(error);
          });
      },
      postRequest(){
        // 为给定 ID 的 user 创建请求
        axios.post('/test/post',qs.stringify({
              username:'123',
              password:'456'
        }))
          .then(function (response) {
            var result = response.data; 
            var code = result.statusCode; 
            console.log(code)
            console.log(response);
          })
          .catch(function (error) {
            console.log(error);
          });
      },
      postJsonRequest(){
        // 为给定 ID 的 user 创建请求
        axios.post('/test/postJson',{
              username:123,
              password:456
        })
          .then(function (response) {
            var result = response.data;
            var code = result.statusCode; 
            switch(code){
              case '200':{
                console.log(response);
                break;
              };
              case '400':{
                console.log("操作失败");
                break;
              };
              case '401':{
                console.log("权限不足");
                break;
              };
            }
          })
          .catch(function (error) {
            console.log(error);
          });
      }
    }
  }
  </script>