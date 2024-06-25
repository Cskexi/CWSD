<template>
  <div>
    <h1>This is an dictype page</h1>
    <el-form ref="searchForm" :model="searchForm" :inline="true" class="formItem" label-width="80px">
      <el-col :xl="6" :md="8" :sm="24">
        <el-form-item label="类型名称" prop="name" style="vertical-align: middle;">
          <el-input placeholder="字典类型名称" v-model="searchForm.name" clearable> </el-input>
        </el-form-item>
        <el-form-item label="用户名称" prop="username" style="vertical-align: middle;">
          <el-input placeholder="用户名称" v-model="searchForm.username" clearable> </el-input>
        </el-form-item>
      </el-col>

      <el-col :xl="6 || 24" :md="8 || 24" :sm="24">
        <el-form-item>
          <div :style="{ float: 'right', overflow: 'hidden' } || {}">
            <el-button icon="el-icon-search" type="primary" @click="search">搜索</el-button>
          </div>
        </el-form-item>
      </el-col>
    </el-form>


    <el-button type="primary" @click="add">新增</el-button>
    <el-button type="primary" @click="delAll">批量删除</el-button>


    <el-table :data="tableData" style="width: 100%" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55">
      </el-table-column>
      <el-table-column label="字典类型名称" width="180" prop="name">
      </el-table-column>
      <el-table-column label="字典类型值" width="180" prop="value">
      </el-table-column>
      <el-table-column label="所属用户" width="180" prop="username">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button size="mini" @click="dicMgn(scope.row.id)">字典管理</el-button>

          <el-button size="mini" @click="edit(scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" @click="delOne(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <div class="block">
      <span class="demonstration">完整功能</span>
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
        :current-page="searchForm.pageNum" :page-sizes="[2, 5, 10, 20]" :page-size="searchForm.pageSize"
        layout="total, sizes, prev, pager, next, jumper" :total="total">
      </el-pagination>
    </div>

    <addOrEdit v-if="visible" :defaultFormDate="obj" :title="title" :visible="visible" @close="closeFather"></addOrEdit>
    <Dic v-if="visible_mgn" :visible="visible_mgn" :dictypeId="dictypeId" @close="closeMgn"></Dic>


  </div>
</template>


<script>
import { dictypePageByUsername, dictypePage, dictypeDeleteByIds } from "@/api/modules/dictype"
import addOrEdit from "./module/addOrEdit"
import Dic from "./module/dic.vue"
import { del } from "vue"
export default {
  name: '',
  components: { addOrEdit, Dic },
  data() {
    return {
      selectionIds: [],
      visible: false,
      visible_mgn: false,
      obj: {},
      title: "新增",
      total: 10,
      searchForm: {
        pageNum: 1,
        pageSize: 10,
        username: "",
        name: ''
      },
      tableData: [],
      dictypeId: "",
    }
  },
  mounted() {
    this.loadTableData()
  },
  methods: {
    dicMgn(id) {
      this.visible_mgn = true
      this.dictypeId = id
    },
    delOne(id) {
      del(id)
    },
    delAll() {
      if (this.selectionIds.length <= 0) {

        this.$message.warning("先选择记录")
        return
      }
      this.del(this.selectionIds.join(","))
    },
    del(ids) {
      this.$confirm('确定删除该记录？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        dictypeDeleteByIds({ ids: ids }).then(result => {
          this.$message('删除成功')
          this.loadTableData()
        }).catch(err => {
          this.$message.error(err)
        })
      }).catch(() => {
        console.log("cancle")
      });
    },
    add() {
      //打开对话框
      //执行操作
      this.visible = true;
      this.obj = {}
    },
    edit(row) {
      this.obj = row
      this.title = "编辑"
      this.visible = true;
    },
    loadTableData() {
      dictypePage({
        ...this.searchForm
      }).then(result => {
        console.log(result)
        this.total = result.data.total;
        this.tableData = result.data.records;
        //this.total = result.data.total
      }).catch(err => {
        console.log("error:" + err)
      })
    },
    search() {
      this.loadTableData()
    },

    handleSizeChange(val) {
      this.searchForm.pageSize = val;
      this.loadTableData()
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      this.searchForm.pageNum = val;
      this.loadTableData()
      console.log(`当前页: ${val}`);
    },
    closeFather(val) {
      this.visible = false
      if (val) {
        this.loadTableData()
      }
    },
    handleSelectionChange(val) {
      console.log(val)
      this.selectionIds = [];
      val.forEach(item => {
        // console.log(item.id)
        this.selectionIds.push(item.id)
      })
      console.log(this.selectionIds.join(","))
    },
    closeMgn(val) {
      this.visible_mgn = false
      if (val) {
        this.loadTableData()
      }
    },
  }
}
</script>