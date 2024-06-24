<template>
  <div>
    <el-row style="margin-bottom: 20px;">
      <el-col :span="3">
        <el-button type="primary" @click="addMonitor">新增</el-button>

      </el-col>
      <el-col :span="3" style="float: right;">
        <el-button type="primary" @click="lookOne(0)">查看待处理消息</el-button>
      </el-col>
      <el-col :span="3" style="float: right;">
        <el-button type="primary" @click="lookOne()">查看所有消息</el-button>
      </el-col>
    </el-row>


    <el-table :data="UserData" style="width: 100%;">
      <el-table-column label="时间" prop="time" width="200"></el-table-column>
      <el-table-column label="通知说明" prop="reason"></el-table-column>
      <el-table-column label="是否处理" width="100">
        <template slot-scope="scope">
          <el-switch @change="setState(scope.row)" v-model="scope.row.state" active-color="#13ce66"
            inactive-color="#ff4949" :active-value="1" :inactive-value="0" style="margin-left: 10px"></el-switch>
        </template>
      </el-table-column>
    </el-table>

    <div class="pagination-container">
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
        :current-page="searchForm.pageNum" :page-sizes="[10, 15, 30]" :page-size="searchForm.pageSize"
        layout="total, sizes, prev, pager, next, jumper" :total="total">
      </el-pagination>
    </div>
    <addOrEdit v-if="visible" :defaultFormDate="obj" :title="title" :visible="visible" @close="closeFather"></addOrEdit>
  </div>
</template>
  
<script>
import addOrEdit from "./components/AddOrEdit"
import { userPage, userDeleteByIds, userAddOrUpdate, userAdd } from "@/api/modules/user"
import { list, save, noticePage } from "@/api/modules/notice"
import { getStore } from "@/utils/storage"

export default {
  components: {
    addOrEdit
  },
  data() {
    return {
      num: 1,
      visible: false,
      total: 10,
      UserData: [],
      selectionIds: [],
      obj: {},
      options: [{
        value: 2,
        label: '施工经理'
      }, {
        value: 1,
        label: '施工人员'
      }, {
        value: 0,
        label: '管理员'
      },],
      searchForm: {
        pageNum: 1,
        pageSize: 15,
        state: "",
      },
      state: "",
    }
  },
  mounted() {
    this.loadUserData()
  },
  methods: {
    setState(row) {
      save({
        id: row.id,
        state: row.state
      }).then(result => {
        this.$message('状态更改成功')
      }).catch(err => {
        this.$message.error(err)
      })
    },

    addMonitor() {
      this.title = "添加新通知"
      this.visible = true;
      this.obj = {};
    },
    closeFather(val) {
      this.visible = false
      if (val) {
        this.loadUserData();
      }
    },

    lookOne(a) {
      this.searchForm.state = a;
      this.loadUserData()
    },

    loadUserData() {
      noticePage({ ...this.searchForm }).then(result => {
        this.UserData = result.data.records
        this.total = result.data.total;
        console.log(this.UserData)
      }).catch(err => {
        console.log("error:" + err)
      })
    },
    handleSizeChange(val) {
      this.searchForm.pageSize = val;
      this.loadUserData()
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      this.searchForm.pageNum = val;
      this.loadUserData()
      console.log(`当前页: ${val}`);
    },
  },
}
</script>
  
  
<style scoped>
.page-title {
  font-size: 24px;
  margin-bottom: 20px;
}

.formItem {
  margin-bottom: 20px;
}

/* Style for the delete button */
.el-button--danger {
  background-color: #f56c6c;
  border-color: #f56c6c;
}

/* Style for the pagination container */
.pagination-container {
  margin-top: 20px;
  text-align: center;
}
</style>