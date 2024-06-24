<template>
  <el-dialog :title="title" :visible="visible" :before-close="close" append-to-body>
    <el-form :model="form" ref="form">
      <el-form-item label="姓名" prop="telephone">
        <el-input v-model="form.name" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="性别" prop="sex">
        <el-select v-model="form.sexName" placeholder="请选择">
          <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="电话" prop="telephone">
        <el-input v-model="form.telephone" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="新密码" prop="password">
        <el-input v-model="form.password" autocomplete="off"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="close">取 消</el-button>
      <el-button type="primary" @click="submit">确 定</el-button>
    </div>
  </el-dialog>
</template>    

<script>
import { getByToken, update } from "@/api/modules/user"

export default {
  name: "addOrEdit",
  props: {
    visible: {
      type: Boolean,
      default: false
    },
    title: {
      type: String,
      default: "新增"
    },
    defaultFormDate: {
      type: Object,
    },
  },
  data() {
    return {
      flag: false,
      form: {
        id: '',
        name: '',
        password: '',
        telephone: '',
        sex: '',
      },
      options: [{
        value: '男',
        label: '男'
      }, {
        value: '女',
        label: '女'
      },],
    };
  },
  mounted() {
    this.form = this.defaultFormDate
    this.form.password = "";
  },
  methods: {
    close() {
      this.flag = false;
      this.$emit("close", this.flag)
    },
    submit() {

      this.$refs.form.validate(valid => {
        if (valid) {
          switch (this.form.sexName) {
            case '女':
              this.form.sex = "0"
              break
            case '男':
              this.form.sex = "1"
              break
            default:
              console.error("Unhandled sex:", item.type);
          }
          console.log(this.form.sexName)
          console.log(this.form.sex)
          update({
            ...this.form
          }).then(res => {
            this.flag = true
            this.$emit("close", this.flag)
          })
            .catch(err => {
              this.flag = false
              this.$emit("close", this.flag)
            })
        }
      })
    },

  }
};
</script>
<style scoped></style>