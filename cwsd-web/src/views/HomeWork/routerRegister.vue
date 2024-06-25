<template>
  <div   >
    <h1>This is an register page</h1>
    用户名:<input type="text" v-model="form.username"><br/>
    密码:<input type="text" v-model="form.password"><br/>
    <button @click="register">注册</button>
  </div>
</template>


<script>
import { login,register } from "@/api/modules/user";
import {setStore} from "@/lib/storage"

export default {
  name: 'routerRegister',
  data(){
    return{
      form:{
        username:"",
        password:""
      }
    }
  },
  methods:{
    register(){
      register({
        ...this.form
      }).then(result=>{
        console.log(result.message)
      }).catch(err=>{
        console.log("error:"+err)
      })

    }
  }
  
}
</script>