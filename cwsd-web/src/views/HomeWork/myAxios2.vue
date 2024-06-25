<template>
    <div class="myAxios2">
      <h1>myAxios2</h1>
      <button @click="getRequest">get-request</button><br>
      <button @click="postRequest">post-request</button><br>
      <button @click="postJsonRequest">postJson-request</button><br>
      <button @click=" bookListRequest">Book-List</button><br>
    </div>
  </template>
  
  <script>

  import { myAxios2Get,myAxios2Post,myAxios2PostJson } from "@/api/modules/myAxios2"
  import { bookList } from "@/api/modules/book"
  export default {

    name: 'myAxios2',
    data(){
      return{
      }
    },
    methods:{
      getRequest(){
        myAxios2Get({id:123}).then(result =>{
          //接送PROmise的reslove状态
        //success
        console.log(result)
        }).catch(err=>{
        //fail
        console.log("fail:"+err)
      })
      },
      postRequest(){
        myAxios2Post({username:123,password:456}).then(res =>{
        //success
        console.log(res)
        }).catch(err=>{
        //fail
        console.log("fail:"+err)
      })
      },
      postJsonRequest(){
        myAxios2PostJson({username:123,password:456}).then(res =>{
        //success
        console.log(res)
        }).catch(err=>{
        //fail
        console.log("fail:"+err)
      })
      },
      bookListRequest(){
        bookList({name:''}).then().then(res =>{ 
        //success
        console.log(res)
        }).catch(err=>{
        //fail
        console.log("fail:"+err)
      })
      }

    }
  }
  </script>