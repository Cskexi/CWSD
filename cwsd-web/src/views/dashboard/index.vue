<!-- src/components/MonitoringDashboard.vue -->
<template>
  <div class="monitoring-dashboard">
    <el-row style="margin-bottom: 20px;">
      <el-col :span="3">
        <el-button type="primary" @click="addMonitor">新增</el-button>
      </el-col>
      <el-col :span="3">
        <el-select @change="fetchVideos()" v-model="form.address" placeholder="请选择">
          <el-option v-for="item in options" :key="item.value" :label="item.name" :value="item.value">
          </el-option>
        </el-select>
      </el-col>
    </el-row>


    <el-row :gutter="24">

      <el-col :span="6" v-for="item in  equipmentData" :key="item.id">
        <el-card style="margin-bottom: 20px;">
          <div class="monitoring-item">
            <h3>{{ item.name }}</h3>
            <p class="value" :style="getTextColor(item)">{{ item.value }}{{ item.flats }}</p>
          </div>
          <el-button type="primary" @click="editMonitor(item)" style=" float:right;margin-bottom: 20px; ">编辑</el-button>
        </el-card>
      </el-col>

    </el-row>

    <addOrEdit v-if="visible" :defaultFormDate="obj" :title="title" :visible="visible" @close="closeFather"></addOrEdit>
  </div>
</template>
  
<script>
import { save } from "@/api/modules/notice"
import addOrEdit from "./components/AddOrEdit"
import { equipmentList, dataChange } from "@/api/modules/equipment"
import { dictypeList } from "@/api/modules/dictype"
import { dicPage } from "@/api/modules/dic"
export default {
  components: {
    addOrEdit
  },
  name: 'MonitoringDashboard',
  data() {
    return {
      visible: false,
      obj: {},
      form: {
        name: "",
        address: ""
      },
      equipmentData: [],
      timer: null,

      options: [],
      searchForm: {
        pageNum: 1,
        pageSize: 10,
        dictypeId: "",
      },
      noticeForm: {
        reason: "",
        equipmentId: "",
      },
    };
  },
  mounted() {
    this.loadOptions()
    this.timer = setInterval(() => this.fetchVideos(), 60000); // 每5秒更新一次数据
    this.fetchVideos()
  },
  beforeDestroy() {
    clearInterval(this.timer);
  },
  methods: {

    getTextColor(item) {
      // 假设你希望当item.value大于100时字体颜色为红色
      if (parseInt(item.value) >= parseInt(item.cordon))
        return { color: 'red' }
      else {
        return { color: 'black' };
      }
    },
    loadOptions() {
      dictypeList({ name: "地区" }).then(result => {
        this.searchForm.dictypeId = result.data[0].id;
        dicPage({
          ...this.searchForm
        }).then(result => {
          this.options = result.data.records;
          this.options.push({
            label: "全部",
            name: "全部",
            value: "",
          });
        }).catch(err => {
          console.log("error:" + err)
        })

      }).catch(err => {
        console.log("error:" + err)
      })
    },
    editMonitor(row) {
      this.obj = row;
      this.title = "编辑设备"
      this.visible = true;
    },
    addMonitor() {
      this.title = "添加新设备"
      this.visible = true;
      this.obj = {};
    },

    closeFather(val) {
      this.visible = false
      if (val) {
        this.fetchVideos();
      }
    },

    fetchVideos() {
      // 从后端获取视频数据，这里只是示例，实际情况需要根据后端API进行调整
      dataChange();
      equipmentList({ ...this.form }).then(result => {
        this.equipmentData = result.data;
        this.sendNotice(this.equipmentData);
      })
    },

    sendNotice(a) {

      a.forEach(item => {
        if (parseInt(item.value) >= parseInt(item.cordon)) {
          this.noticeForm.equipmentId = item.id;
          this.noticeForm.reason = "警告：" + "当前" + item.name + "数值已达到" + item.value + item.flats;
          save({ ...this.noticeForm }).then(result => {
            console.log(this.noticeForm)
          })
        }
      })
    }

  },


};
</script>
  
<style scoped>
.monitoring-dashboard {
  margin: 20px;
}

.monitoring-item {
  text-align: center;
}

.monitoring-item h3 {
  margin: 0;
  padding: 10px;
  background-color: #f5f7fa;
}

.monitoring-item .value {
  font-size: 24px;
  font-weight: bold;
  padding: 20px;
}
</style>