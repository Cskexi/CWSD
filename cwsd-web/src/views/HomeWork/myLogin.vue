<template>
  <div   >
    <h1>This is an Login page</h1>
    用户名:<input type="text" v-model="form.username"><br/>
    密码:<input type="text" v-model="form.password"><br/>
    <button @click="login">登录</button>
  </div>
</template>


<script>
import { login,register } from "@/api/modules/user";
import {setStore} from "@/lib/storage"

export default {
  name: 'routerLogin',
  data(){
    return{
      form:{
        username:"",
        password:""
      }
    }
  },
  methods:{
    login(){
      login({
        ...this.form
      }).then(result=>{
        let token = result.data;
        setStore("token",token)
        console.log("token:"+result.data)
      }).catch(err=>{
        console.log("error:"+err)
      })

    }
  }
  
}
</script>