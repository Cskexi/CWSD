<template>
  <el-dialog :title="title" :visible="visible" :before-close="close" append-to-body>
    <el-form :model="form" ref="form">

      <div class="block">
        <el-date-picker v-model="form.time" type="datetime" placeholder="选择日期时间" value-format="yyyy-MM-dd HH:mm:ss">
        </el-date-picker>
      </div>

      <el-form-item label="通知" prop="reason">
        <el-input type="textarea" :rows="8" v-model="form.reason">
        </el-input>
      </el-form-item>

    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="close">取 消</el-button>
      <el-button type="primary" @click="submit">确 定</el-button>
    </div>
  </el-dialog>
</template>    

<script>
import { save, deleteByIds } from "@/api/modules/notice"
import { dictypeList } from "@/api/modules/dictype"
import { dicPage } from "@/api/modules/dic"


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
        reason: "",
        time: "",
      },
      options: [],//value,lable
    };
  },
  mounted() {
  },
  methods: {
    close() {
      this.flag = false;
      this.$emit("close", this.flag)
    },
    submit() {
      if (this.form.name == "" || this.form.address == "" || this.form.streamUrl == "") {
        this.$message.error("请填写相关内容")
      } else {
        save({ ...this.form }).then(res => {
          this.flag = true
          this.$message.success("成功" + this.title)
          this.$emit("close", this.flag)
        })
          .catch(err => {
            this.flag = false
            this.$emit("close", this.flag)
          })
      }
    }
  }
};
</script>