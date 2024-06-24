<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="3">
        <!-- 新增按钮 -->
        <el-button type="primary" @click="addMonitor">新增</el-button>
        <!-- <video width="320" height="240" controls>
          <source src="@/assets/test.mp4">
        </video> -->
      </el-col>
      <el-col :span="3">
        <el-select v-model="lan2" placeholder="请选择">
          <el-option v-for="item in lan" :key="item.value" :label="item.label" :value="item.value">
          </el-option>
        </el-select>
      </el-col>
      <el-form :model="form" ref="form">
        <el-col :xl="6" :md="8" :sm="24" style="margin-bottom: 20px;">
          <el-input placeholder="名字" v-model="form.name" clearable> </el-input>
        </el-col>
        <el-col :xl="6 || 24" :md="6" :sm="24" style="margin-right: 15px">
          <el-button icon="el-icon-search" type="primary" style="margin-left: 15px;" @click="search">搜索</el-button>
        </el-col>

        <el-col :span="3">
          <el-select @change="fetchVideos()" v-model="form.address" placeholder="请选择">
            <el-option v-for="item in options" :key="item.value" :label="item.name" :value="item.value">
            </el-option>
          </el-select>
        </el-col>

      </el-form>
    </el-row>

    <el-row :gutter="24">

      <el-col :span=lan2 v-for="video in videos" :key="video.id" style="text-align: center;margin-bottom: 20px;">
        <video-player :video="video" />
        <h2>{{ video.name }}<el-button type="primary" @click="editMonitor(video)" style="float: right;">编辑</el-button>
        </h2>
        <!-- <el-button type="danger" @click="deleteMonitor(video.id)">删除</el-button> -->
      </el-col>
    </el-row>
    <addOrEdit v-if="visible" :defaultFormDate="obj" :title="title" :visible="visible" @close="closeFather"></addOrEdit>
  </div>
</template>
  
<script>
import VideoPlayer from './components/VideoPlayer.vue';
import addOrEdit from "./components/AddOrEdit"
import { monitorList, deleteByIds } from "@/api/modules/monitor"
import { dictypeList } from "@/api/modules/dictype"
import { dicPage } from "@/api/modules/dic"
export default {
  components: {
    VideoPlayer,
    addOrEdit
  },
  data() {
    return {
      visible: false,
      obj: {},
      lan2: 8,
      lan: [
        {
          value: 6,
          label: '四'
        }, {
          value: 8,
          label: '三'
        }, {
          value: 12,
          label: '二'
        },
      ],
      videos: [],
      page: 1,
      loading: false,

      form: {
        name: "",
        address: ""
      },
      options: [],
      searchForm: {
        pageNum: 1,
        pageSize: 10,
        dictypeId: "",
      },
    };
  },
  mounted() {
    this.loadOptions();
    this.fetchVideos();
  },
  methods: {
    loadOptions() {
      dictypeList({ name: "地区" }).then(result => {
        this.searchForm.dictypeId = result.data[0].id;
        dicPage({
          ...this.searchForm
        }).then(result => {
          console.log(result.data.records)
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

    search() {
      console.log(this.form.name)
      this.fetchVideos();
    },
    deleteMonitor(ids) {
      deleteByIds({ ids: ids }).then(result => {
        this.fetchVideos();
      }).catch(err => {
        console.log("error:" + err)
      })
    },
    addMonitor() {
      this.title = "添加新监控"
      this.visible = true;
      this.obj = {};
    },
    editMonitor(row) {
      this.obj = row;
      this.title = "编辑监控"
      this.visible = true;
    },

    closeFather(val) {
      this.visible = false
      if (val) {
        this.fetchVideos();
      }
    },

    fetchVideos() {
      // 从后端获取视频数据，这里只是示例，实际情况需要根据后端API进行调整
      this.loading = true;

      monitorList({ ...this.form }).then(result => {
        this.videos = result.data;
        console.log(this.videos)
      }).catch(err => {
        console.log("error:" + err)
      })
      // this.videos.push(...this.generateFakeVideos(1));
      this.page++;
      this.loading = false;

    },

    onScroll() {
      if (window.innerHeight + window.scrollY >= document.body.offsetHeight) {
        if (this.loading) return;
        this.fetchVideos();
      }
    },
  },
  created() {
    window.addEventListener('scroll', this.onScroll);
  },
  beforeDestroy() {
    window.removeEventListener('scroll', this.onScroll);
  },
};
</script>
  
<style>
/* 根据需要添加样式 */
</style>