<template>
  <el-menu :default-openeds="['1', '2']">
    <el-menu-item index="1">
      <router-link to="/home">首页</router-link>
    </el-menu-item>
    <el-menu-item index="1">
      <router-link to="/dashboard">传感器设备</router-link>
    </el-menu-item>
    <el-menu-item index="1">
      <router-link to="/message">信息管理</router-link>
    </el-menu-item>
    <el-menu-item index="2" v-if="user.type == 0">
      <router-link to="/permission">用户管理</router-link>
    </el-menu-item>
    <el-menu-item index="1">
      <router-link to="/monitor">现场监控</router-link>
    </el-menu-item>
    <el-menu-item index="1" v-if="user.type != 1">
      <router-link to="/dictype">字典管理</router-link>
    </el-menu-item>
    <el-menu-item index="1">
      <router-link to="/userInfo">个人信息管理</router-link>
    </el-menu-item>
    <el-menu-item index="1">
      <router-link to="/help">操作指南</router-link>
    </el-menu-item>
    <!-- <el-submenu index="1">
        <template slot="title">考勤管理</template>
        <el-menu-item-group>
          <el-menu-item index="1-1"> <router-link to="/attendance">出勤人员统计</router-link></el-menu-item>
          <el-menu-item index="1-1"><router-link to="/attendanceApply">请假申请</router-link></el-menu-item>
          <el-menu-item index="1-2"><router-link to="/attendanceVet">请假单审核</router-link></el-menu-item>
          <el-menu-item index="1-2"><router-link to="/myAttendance">我的申请</router-link></el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-submenu index="2">
        <template slot="title">项目任务管理</template>
        <el-menu-item-group>
          <el-menu-item index="2-1">项目管理</el-menu-item>
          <el-menu-item index="2-2">任务管理</el-menu-item>
        </el-menu-item-group>
      </el-submenu> -->
  </el-menu>
</template>

<script>

import { getStore, setStore } from "@/utils/storage"
import { getByToken, update } from "@/api/modules/user"
import { removeStore } from '@/utils/storage'
export default {

  data() {
    return {
      // user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
      user: {}
    };
  },
  mounted() {
    this.loadUserData()
  },
  methods: {
    logOut() {
      removeStore('user')
      this.$router.push('/login')  // 登录成功，跳转到主页
    },
    loadUserData() {
      let token = getStore("token")
      if (token) {
        console.log(token)
        getByToken({ token: token }).then(result => {
          this.user = result.data
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



<style lang="scss" scoped>
a {
  text-decoration: none;
  color: #000;
}

.router-link-active {
  text-decoration: none;
}
</style>
