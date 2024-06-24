<template>
  <div class="site-wrapper">
    <h2>当前待处理消息</h2>
    <el-table :data="NoticeData" style="width: 100%; margin-bottom: 20px;">
      <el-table-column label="时间" prop="time"></el-table-column>
      <el-table-column label="通知说明" prop="reason"></el-table-column>
    </el-table>
    <el-col>
      <el-button type="primary" @click="ToNotice()" style=" float:right;">去处理</el-button>
    </el-col>
    <h2>视频监控</h2>
    <el-row :gutter="24" style="text-align:center">
      <el-col :span=12 v-for="video in videos" :key="video.id">
        <video-player :video="video" />
        <h2>{{ video.name }}</h2>
      </el-col>
    </el-row>

    <h2>传感器设备</h2>

    <el-row :gutter="24">

      <el-col :span="6" v-for="item in  equipmentData" :key="item.id">
        <el-card>
          <div class="monitoring-item">
            <h3>{{ item.name }}</h3>
            <p class="value" :style="getTextColor(item)">{{ item.value }}{{ item.flats }}</p>
          </div>
        </el-card>
      </el-col>

    </el-row>
  </div>
</template>
  
<script>
import VideoPlayer from '@/views/monitor/components/VideoPlayer.vue';
import { list } from "@/api/modules/notice"
import { dictypeList } from "@/api/modules/dictype"
import { dicPage } from "@/api/modules/dic"
import { monitorList } from "@/api/modules/monitor"
import { equipmentList, dataChange } from "@/api/modules/equipment"
import { save } from "@/api/modules/notice"

export default {
  name: 'home',
  components: {
    VideoPlayer,
  },
  data() {
    return {
      NoticeData: [],
      searchForm: {
        pageNum: 1,
        pageSize: 10,
        dictypeId: "",
      },
      videos: [],
      equipmentData: [],
      noticeForm: {
        reason: "",
        equipmentId: "",
      },
      equipmentName: [],
    }
  },
  mounted() {


    this.loadMonitor()
    this.loadEquipmemt()
    this.loadNoticeData()
    this.timer = setInterval(() => this.updateData(), 60000); // 每5秒更新一次数据
  },
  beforeDestroy() {
    clearInterval(this.timer);
  },
  methods: {
    updateData() {
      this.loadEquipmemt()
      this.loadNoticeData()
    },
    getTextColor(item) {
      // 假设你希望当item.value大于100时字体颜色为红色
      if (parseInt(item.value) >= parseInt(item.cordon))
        return { color: 'red' }
      else {
        return { color: 'black' };
      }
    },
    loadEquipmemt() {
      let temp = [];
      dataChange();


      dictypeList({ name: "传感器" }).then(result => {
        this.searchForm.dictypeId = result.data[0].id;
        if (this.searchForm.dictypeId) {
          console.log(this.searchForm.dictypeId)
          dicPage({ ...this.searchForm }).then(result => {
            result.data.records.forEach(item => {
              equipmentList({ name: item.name }).then(result => {
                console.log(result)
                temp.push(result.data[0])
                var temp2 = result.data[0];
                console.log(temp2);
                if (parseInt(temp2.value) >= parseInt(temp2.cordon)) {
                  this.noticeForm.equipmentId = temp2.id;
                  this.noticeForm.reason = "警告：" + "当前" + temp2.name + "数值已达到" + temp2.value + temp2.flats;
                  save({ ...this.noticeForm }).then(result => {
                  })
                }
              })

            });
            this.equipmentData = temp;
            console.log(this.equipmentData)
          }).catch(err => { // 移除多余的 catch 块
            console.log("error:" + err)
          })

        }

      }).catch(err => {
        console.log("error:" + err)
      })
    },

    sendNotice(a) {
      a.forEach(item => {
        console.log(item)
        if (parseInt(item.value) >= parseInt(item.cordon)) {
          this.noticeForm.equipmentId = item.id;
          this.noticeForm.reason = "警告：" + "当前" + item.name + "数值已达到" + item.value + item.flats;
          save({ ...this.noticeForm }).then(result => {
            console.log(result)
          })
        }
      })
    },

    loadMonitor() {
      dictypeList({ name: "监控器" }).then(result => {
        this.searchForm.dictypeId = result.data[0].id;
        dicPage({ ...this.searchForm }).then(result => {
          result.data.records.forEach(item => {
            monitorList({ name: item.name }).then(result => {
              this.videos.push(result.data[0])
            })
          })
        }).catch(err => {
          console.log("error:" + err)
        })
      }).catch(err => {
        console.log("error:" + err)
      })
    },


    ToNotice() {
      this.$router.push('/message')  // 登录成功，跳转到主页
    },

    loadNoticeData() {
      list({ state: "0" }).then(result => {
        this.NoticeData = result.data;
      }).catch(err => {
        console.log("error:" + err)
      })
    },


    // loadEquipmemt() {
    //   // 从后端获取视频数据，这里只是示例，实际情况需要根据后端API进行调整
    //   dataChange();
    //   equipmentList({ ...this.form }).then(result => {
    //     this.equipmentData = result.data;
    //     this.sendNotice(this.equipmentData);
    //   })
    // },




  },
}
</script>

<style lang="scss">
.site-wrapper em {
  color: red;
}

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