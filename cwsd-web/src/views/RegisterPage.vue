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
          <el-input type="password" placeholder="password" v-model="form.password" @keyup.enter="register()">
            <template #prepend>
              <el-button icon="el-icon-lock"></el-button>
            </template>
          </el-input>
        </el-form-item>
        <div class="login-btn">
          <el-button type="primary" @click="register()">注册</el-button>
        </div>
        <div class="register-btn">
          <el-button type="text" @click="goToRegister">已有账号？去登录</el-button>
        </div>

      </el-form>

    </div>
  </div>
  <!-- <div>
    <h1>注册页面</h1>
    用户名:<input type="text" v-model="form.username"><br/>
    密码:<input type="text" v-model="form.password"><br/>
    <button @click="register">注册</button><br/>
    <router-link to="/LoginPage">登录</router-link><br/>
    <router-link to="/ShoppingPage">游客进入</router-link>
  </div> -->
</template>


<script>
import { login, register } from "@/api/modules/user";
import { setStore } from "@/lib/storage"


export default {
  name: 'routerRegister',
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
          {
            pattern: /^[a-zA-Z0-9]+$/,
            message: "用户名只能包含数字和字母",
            trigger: "blur",
          },
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          {
            min: 5,
            message: "密码至少5位",
            trigger: "blur",
          },
          {
            pattern: /^[a-zA-Z0-9]+$/,
            message: "用户名只能包含数字和字母",
            trigger: "blur",
          },
        ],
      },
    }
  },
  methods: {

    goToRegister() {
      // 处理跳转到注册页面的逻辑
      this.$router.push('/LoginPage'); // 假设你的注册页面路由为 '/register'
    },

    register() {
      register({
        ...this.form
      }).then(result => {
        this.$message.success(result.message)
        this.$router.push('/LoginPage'); // 跳转到主页
      }).catch(err => {
        this.$message.error(err)
      })

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