<template>
  <div>
    <el-header class="main-header">
      <div class="logo">后台管理系统</div>
      <div class="nav-links">
        <router-link to="/ShoppingPage">购物界面</router-link> |
        <router-link to="/LoginPage">退出登录</router-link>
      </div>
    </el-header>

    <el-container class="main-container">
      <el-aside class="main-aside">
        <h5>导航栏</h5>
        <el-menu class="el-menu-vertical-demo" default-active="1">
          <el-menu-item index="1">
            <router-link to="/mainPage/PersonalPage">个人信息</router-link>
          </el-menu-item>
          <el-menu-item index="2">
            <router-link to="/mainPage/ManageUserPage">用户管理</router-link>
          </el-menu-item>
          <el-menu-item index="2">
            <router-link to="/mainPage/ManageUserPage">商店管理</router-link>
          </el-menu-item>
          <el-menu-item index="2">
            <router-link to="/mainPage/ManageUserPage">视频管理</router-link>
          </el-menu-item>
          <el-menu-item index="2">
            <router-link to="/mainPage/ManageUserPage">订单管理</router-link>
          </el-menu-item>
          <el-menu-item index="2">
            <router-link to="/mainPage/ManageUserPage">-----完成以上的页面-------</router-link>
          </el-menu-item>
          <el-menu-item index="1">
            <router-link to="/mainPage/dictype">字典管理</router-link>
          </el-menu-item>
          <el-menu-item index="3">
            <router-link to="/mainPage/ManageStorePage">商店信息管理</router-link>
          </el-menu-item>
          <el-menu-item index="4">
            <router-link to="/mainPage/ManageOrderPage">订单信息管理(用户)</router-link>
          </el-menu-item>
          <el-menu-item index="5">
            <router-link to="/mainPage/ManageOrderPage2">订单信息管理(管理员)</router-link>
          </el-menu-item>
          <el-menu-item index="6">
            <router-link to="/mainPage/ShopcartPage">购物车管理</router-link>
          </el-menu-item>


          <el-menu-item index="2" v-if="user.type === 1">
            <router-link to="/mainPage/ManageUserPage">会员管理</router-link>
          </el-menu-item>
          <el-menu-item index="3" v-if="user.type === 2 || user.type === 1">
            <router-link to="/mainPage/ManageStorePage">商店信息管理</router-link>
          </el-menu-item>
          <el-menu-item index="4" v-if="user.type === 3">
            <router-link to="/mainPage/ManageOrderPage">订单信息管理(用户)</router-link>
          </el-menu-item>
          <el-menu-item index="5" v-if="user.type === 2">
            <router-link to="/mainPage/ManageOrderPage2">订单信息管理(管理员)</router-link>
          </el-menu-item>
          <el-menu-item index="6" v-if="user.type === 3">
            <router-link to="/mainPage/ShopcartPage">购物车管理</router-link>
          </el-menu-item>

        </el-menu>
      </el-aside>
      <el-main class="main-content">
        <router-view />
      </el-main>
    </el-container>
  </div>
</template>

  
<script>
import { getByToken } from "@/api/modules/user"
import { getStore } from "@/lib/storage"
export default {
  data() {
    return {
      user: {
        id: '',
        type: ''
      }
    }
  },

  mounted() {
    this.loadUserData()
  },
  methods: {
    edit() {
      this.obj = this.user;
      this.title = "编辑个人信息"
      this.visible = true;
    },
    loadUserData() {
      let token = getStore("token")
      if (token) {
        getByToken({ token: token }).then(result => {
          this.user = result.data;
        }).catch(err => {
          console.log("error:" + err)
        })
      }
      else {
        console.log("token为空")
      }
    },
  }
}
</script>



<style lang="scss">
.main-header {
  background-color: #409eff;
  color: #fff;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 20px;
}

.logo {
  font-size: 20px;
}

.nav-links {
  a {
    color: #fff;
    margin-left: 10px;
    text-decoration: none;

    &:hover {
      text-decoration: underline;
    }
  }
}

.main-container {
  height: calc(100vh - 60px);
}

.el-menu {
  margin: 0;
  padding: 0;
  border: 0;
}

.el-menu-item {
  margin: 0;
  padding: 0;
}

.main-aside {
  background-color: #001529;
  color: #fff;
  padding: 20px;

  h5 {
    font-size: 18px;
    margin-bottom: 20px;
  }

  .el-menu-item {
    background-color: #001529;

    a {
      color: #fff;

      &:hover {
        color: #409eff;
      }
    }
  }
}

.main-content {
  padding: 20px;
}
</style>
