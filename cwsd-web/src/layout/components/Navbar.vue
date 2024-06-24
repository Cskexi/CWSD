<template>
  <div style="margin-top: 10px; float:right; ">
    <span style="margin-right: 10px;">{{ user.name }}</span>
    <el-button type="primary" @click="logOut()">登 出</el-button>
  </div>
</template>
  
<script>
import { getByToken, update } from "@/api/modules/user"
import { removeStore } from '@/utils/storage'
import { getStore, setStore } from "@/utils/storage"
export default {
  data() {
    return {
      // user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
      user: {
        name: ""
      }
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
          setStore('userType', this.user.type)
          console.log(this.user)
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
