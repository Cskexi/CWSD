<template>
  <div class="login-wrap">
    <div class="ms-login">
      <div class="ms-title">XX商城</div>

      <el-form :model="form" :rules="rules" label-width="0px" class="ms-content">
        <el-form-item prop="username">
          <el-input v-model="form.username" placeholder="username">
            <template #prepend>
              <el-button icon="el-icon-user"></el-button>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input type="password" placeholder="password" v-model="form.password" @keyup.enter="login()">
            <template #prepend>
              <el-button icon="el-icon-lock"></el-button>
            </template>
          </el-input>
        </el-form-item>
        <div class="login-btn">
          <el-button type="primary" @click="login()">登录</el-button>
        </div>
        <div class="register-btn">
          <el-button type="text" @click="goToRegister">没有账号？去注册</el-button>
        </div>

      </el-form>

    </div>
  </div>
</template>
  
  
<script>
import { login, getByToken } from "@/api/modules/user";
import { setStore, removeStore, getStore } from "@/lib/storage"
export default {
  mounted() {
    this.removeToken()
  },
  data() {
    return {
      form: {
        username: "",
        password: ""
      },
      rules: {
        username: [
          {
            required: true,
            message: "请输入用户名",
            trigger: "blur",
          },
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
        ],
      },
    }
  },
  methods: {

    goToRegister() {
      // 处理跳转到注册页面的逻辑
      this.$router.push('/RegisterPage'); // 假设你的注册页面路由为 '/register'
    },

    toShopping() {
      removeStore("token")
      removeStore("userId")
      this.$router.push('/mainPage'); // 跳转到主页
    },

    removeToken() {
      let token = getStore("token")
      if (token) {
        console.log("token不为空")
      }
      console.log("token:" + token)
    },
    login() {
      login({
        ...this.form
      }).then(result => {
        let token = result.data;
        setStore("token", token)
        this.loadUserData();
        console.log("token:" + result.data)
        this.$router.push('/mainPage'); // 跳转到主页
        //this.$router.push('/ShoppingPage'); // 跳转到主页
      }).catch(err => {
        console.log("error:" + err)
        this.$message.error(err)
      })

    },
    loadUserData() {
      let token = getStore("token")
      if (token) {
        getByToken({ token: token }).then(result => {
          // this.$store.commit('setUser',result.data)
          // console.log(this.$store.state.user)
          setStore("userId", result.data.id)
          setStore("userType", result.data.type)
        }).catch(err => {
          //console.log("error:"+err)
          this.$message.error(err)
        })
      }
      else {
        console.log("token为空")
      }
    }
  }

}
</script>



<style scoped>
.login-wrap {
  position: relative;
  width: 100%;
  height: 100%;
  background-image: url(@/assets/img/login-bg.jpg);
  background-size: 100%;
}

.ms-title {
  width: 100%;
  line-height: 50px;
  text-align: center;
  font-size: 20px;
  color: #fff;
  border-bottom: 1px solid #ddd;
}

.ms-login {
  position: absolute;
  left: 50%;
  top: 50%;
  width: 350px;
  margin: -190px 0 0 -175px;
  border-radius: 5px;
  background: rgba(255, 255, 255, 0.3);
  overflow: hidden;
}

.ms-content {
  padding: 30px 30px;
}

.login-btn {
  text-align: center;
}

.login-btn button {
  width: 100%;
  height: 36px;
  margin-bottom: 10px;
}

.login-tips {
  font-size: 12px;
  line-height: 30px;
  color: #fff;
}

.register-btn {
  margin-top: 10px;
  text-align: center;
}
</style>