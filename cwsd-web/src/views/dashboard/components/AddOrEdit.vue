<template>
  <el-dialog :title="title" :visible="visible" :before-close="close" append-to-body>
    <el-form :model="form" ref="form" :rules="fieldRules">
      <el-form-item label="名字" prop="name">
        <el-input v-model="form.name" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="地区" prop="address">
        <el-select v-model="form.address" placeholder="请选择">
          <el-option v-for="item in options" :key="item.value" :label="item.name" :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="设备地址" prop="streamUrl">
        <el-input v-model="form.streamUrl" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="单位" prop="flats">
        <el-input v-model="form.flats" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="警戒线" prop="cordon">
        <el-input-number v-model="form.cordon" label="描述文字"></el-input-number>
      </el-form-item>
      <el-form-item label="起始">
        <el-input-number v-model="form.start" label="描述文字"></el-input-number>
      </el-form-item>
      <el-form-item label="终止">
        <el-input-number v-model="form.end" label="描述文字"></el-input-number>
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
import { save, deleteByIds } from "@/api/modules/equipment"
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
        cordon: 0,
        start: 0,
        end: 0,
        flats: '',
      },
      options: [],//value,lable
      searchForm: {
        pageNum: 1,
        pageSize: 10,
        dictypeId: "",
      },
      fieldRules: {
        name: [
          { required: true, message: '请输入名字', trigger: 'blur' },
        ],
        address: [
          { required: true, message: '请选择地区', trigger: 'blur' },
        ],
        flats: [
          { required: true, message: '请设置单位', trigger: 'blur' },
        ],
        streamUrl: [
          { required: true, message: '请设置地址', trigger: 'blur' },
        ]
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
        this.$message.success("成功删除设备")
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

      if (this.form.name == "" || this.form.address == "" || this.form.flats == "" || this.form.streamUrl == "") {
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