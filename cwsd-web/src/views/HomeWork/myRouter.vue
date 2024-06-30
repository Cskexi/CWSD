<template>
    <div class="myRouter">
        <h1>myRouter</h1>
        <a href="https://www.baidu.com" target="_blank">百度</a><br>
        <router-link to="/">Home</router-link><br>
        <button @click="toHome()">Home</button>
        <button @click="replaceHome()">Home</button><br>
        <router-link to="/myRouter/routerLogin">登录</router-link><br>
        <router-link to="/myRouter/routerRegister">注册</router-link><br>
        <!-- 占位符 -->
        <router-view></router-view> 
    </div>
  </template>
  
  <script>

  export default {

    name: 'myRouter',
    beforeRouteEnter(to, from, next) {
    console.log('beforeRouteEnter');
    next();
    },
    beforeRouteUpdate(to, from, next) {
    console.log('beforeRouteUpdate');
    next();
    },

    beforeRouteLeave(to, from, next) {
    console.log('beforeRouteLeave');
    next();
     },
    data(){
        return{
            msg:"success",
        }
    },
    mounted(){
         console.log(this.$route)
        // console.log(this.$router)
    },
    methods:{
        toHome(){
            // this.$router.push("/home")
            // this.$router.push({
            //     path:"/"
            // })
            this.$router.push({
                name:"home",
                // query:{
                //     type:"text",
                //     school:"xmut"
                // }
            })
        },
        replaceHome(){
            this.$router.replace("/")
        }
    }
  }
  </script>
  