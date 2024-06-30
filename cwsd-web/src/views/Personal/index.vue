<template>
  <div class="user-info-container">
    <h1 class="page-title">用户信息页面</h1>

    <div class="user-details">
      <el-image class="user-avatar" :src="localImagePath" fit="cover"></el-image>

      <div class="user-info">
        <div class="info-item">
          <span class="info-label">用户名：</span>
          <span>{{ user.username }}</span>
        </div>

        <div class="info-item">
          <span class="info-label">余额：</span>
          <span>{{ user.ballance }}</span>
        </div>

        <div class="info-item">
          <span class="info-label">性别</span>
          <span>{{ user.sexName }}</span>
        </div>

        <div class="info-item">
          <span class="info-label">电话：</span>
          <span>{{ user.telephone }}</span>
        </div>
      </div>
    </div>

    <el-button class="edit-button" @click="edit">编辑</el-button>

    <addOrEdit v-if="visible" :defaultFormDate="obj" :title="title" :visible="visible" @close="closeFather"></addOrEdit>
  </div>
</template>
  
<script>
import { getByToken } from "@/api/modules/user"
import { getStore } from "@/lib/storage"
import addOrEdit from "./AddOrEdit"
export default {
  components: { addOrEdit },
  data() {
    return {
      localImagePath: '/logo.png',
      visible: false,
      obj: {},
      title: "新增",
      username: '',
      ballance: '',
      user: {
        id: '',
        username: '',
        ballance: '',
        telephone: '',
        sex: '',
        pic: '',
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
        console.log(token)
        getByToken({ token: token }).then(result => {
          this.user = result.data;
          if (this.user.sex == 0) {
            this.user.sexName="女"
          } else if (this.user.sex == 1) {
            this.user.sexName="男"
          } else if (this.user.sex == 2) {
            this.user.sexName="保密"
          }
          if (this.user.pic != "") {
            this.localImagePath = this.user.pic;
          }


        }).catch(err => {
          console.log("error:" + err)
        })
      }
      else {
        console.log("token为空")
      }


    },
    closeFather(val) {
      this.visible = false
      if (val) {
        this.loadUserData()
      }
    },
  }
}
</script>

<style scoped>
.user-info-container {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  background-color: #fff;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  border-radius: 8px;
}

.page-title {
  font-size: 24px;
  margin-bottom: 20px;
}

.user-details {
  display: flex;
  align-items: center;
}

.user-avatar {
  width: 80px;
  height: 80px;
  border-radius: 50%;
  margin-right: 20px;
}

.user-info {
  flex-grow: 1;
}

.info-item {
  margin-bottom: 10px;
}

.info-label {
  font-weight: bold;
  margin-right: 5px;
}

.edit-button {
  margin-top: 20px;
}
</style>
</template>
  