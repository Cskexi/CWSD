<template>
  <el-dialog :title="title" :visible="visible" :before-close="close" append-to-body>
    <el-form :model="form" ref="form">
      <el-form-item label="名字" prop="name">
        <el-input v-model="form.name" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="地区" prop="sex">
        <el-select v-model="form.address" placeholder="请选择">
          <el-option v-for="item in options" :key="item.value" :label="item.name" :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="视频地址" prop="streamUrl">
        <el-input v-model="form.streamUrl" autocomplete="off"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">

      <el-button type="danger" @click="deleteMonitor(form.id)" v-if="this.flag === true">删除</el-button>
      <el-button @click="close">取 消</el-button>
      <el-button type="primary" @click="submit">确 定</el-button>
    </div>
  </el-dialog>
</template>    

<script>
import { save, deleteByIds } from "@/api/modules/monitor"
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
      t: 0,
      flag: false,
      form: {
        id: '',
        name: '',
        address: '',
        streamUrl: '',
      },
      options: [],//value,lable
      searchForm: {
        pageNum: 1,
        pageSize: 10,
        dictypeId: "",
      },
    };
  },
  mounted() {
    this.loadOptions();
    if (JSON.stringify(this.defaultFormDate) == "{}") {
      this.flag = false
    } else {
      this.form = JSON.parse(JSON.stringify(this.defaultFormDate))
      this.flag = true;
    }
    console.log(this.flag)

  },
  methods: {
    deleteMonitor(ids) {
      deleteByIds({ ids: ids }).then(result => {
        this.$emit("close", this.flag)
      }).catch(err => {
        console.log("error:" + err)
      })
    },

    loadOptions() {
      dictypeList({ name: "地区" }).then(result => {
        this.searchForm.dictypeId = result.data[0].id;
        dicPage({
          ...this.searchForm
        }).then(result => {
          console.log(result.data.records)
          this.options = result.data.records;

          // this.total = result.data.total;
          // this.tableData = result.data.records;
          //this.total = result.data.total
        }).catch(err => {
          console.log("error:" + err)
        })

      }).catch(err => {
        console.log("error:" + err)
      })


    },
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