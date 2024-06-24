<template>
  <div class="store-management">
    <div class="header">
      <h1>个人信息管理</h1>
    </div>
    <div class="info">
      <p><strong>账号：</strong>{{ user.account }}</p>
      <p><strong>姓名：</strong>{{ user.name }}</p>
      <p><strong>性别：</strong>{{ user.sexName }}</p>
      <p><strong>手机号：</strong>{{ user.telephone }}</p>
      <p><strong>身份：</strong>{{ user.typeName }}</p>
    </div>
    <div class="actions">
      <el-button type="primary" @click="edit">修改</el-button>
    </div>
    <addOrEdit v-if="visible" :defaultFormDate="obj" :title="title" :visible="visible" @close="closeFather"></addOrEdit>
  </div>
</template>
    
<script>
import { getByToken, update } from "@/api/modules/user"
import { getStore } from "@/utils/storage"
import addOrEdit from "./AddOrEdit"
export default {
  components: { addOrEdit },
  data() {
    return {
      visible: false,
      obj: {},
      title: "修改",
      user: {
        id: '',
        account: '',
        name: '',
        telephone: '',
        sex: '',
        password: '',
      },
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
    closeFather(val) {
      this.visible = false
      if (val) {
        this.loadUserData()
      }
    },

    loadUserData() {
      let token = getStore("token")
      if (token) {
        console.log(token)
        getByToken({ token: token }).then(result => {
          this.user = this.setText(result.data);
          console.log(this.user)
        }).catch(err => {
          console.log("error:" + err)
        })
      }
      else {
        console.log("token为空")
      }
    },

    setText(item) {
      switch (item.type) {
        case '0':
          item.typeName = "管理员"
          break
        case '1':
          item.typeName = "施工人员"
          break
        case '2':
          item.typeName = "施工经理"
          break
        default:
          console.error("Unhandled type:", item.type);
      }
      switch (item.sex) {
        case '0':
          item.sexName = "女"
          break
        case '1':
          item.sexName = "男"
          break
        default:
          console.error("Unhandled sex:", item.type);
      }
      return item
    }

  },
}

</script>
    

<style scoped>
.store-management {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  background-color: #fff;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  border-radius: 8px;
  font-family: 'Arial', sans-serif;
}

.header {
  text-align: center;
  margin-bottom: 20px;
}

.header h1 {
  font-size: 24px;
  color: #333;
}

.info p {
  font-size: 16px;
  color: #555;
  margin: 10px 0;
}
