<template>
  <div>
    <h1 class="page-title">用户管理</h1>

    <el-form ref="searchForm" :model="searchForm" :inline="true" class="formItem" label-width="80px">

      <el-col :xl="6" :md="8" :sm="24">
        <el-input placeholder="用户名称" v-model="searchForm.username" clearable> </el-input>
      </el-col>

      <el-col :xl="6 || 24" :md="8 || 24" :sm="24">
        <el-form-item>
          <div :style="{ float: 'right', overflow: 'hidden' } || {}">
            <el-button icon="el-icon-search" type="primary" @click="search">搜索</el-button>
          </div>
        </el-form-item>
      </el-col>

      <!-- <el-button type="primary" @click="delAll">批量删除</el-button> -->

    </el-form>

    <el-table :data="UserData" style="width: 100%" @selection-change="handleSelectionChange">

      <el-table-column type="selection" width="55"></el-table-column>

      <el-table-column label="用户名" prop="username"></el-table-column>

      <el-table-column label="昵称" prop="nickname"></el-table-column>

      <el-table-column label="余额" prop="ballance"></el-table-column>

      <el-table-column label="身份" prop="typeName"></el-table-column>

      <el-table-column label="电话" prop="telephone"></el-table-column>

      <!-- <el-table-column label="是否可用">
        <template slot-scope="scope">
          <el-switch @change="setState(scope.row)" v-model="scope.row.state" active-color="#13ce66"
            inactive-color="#ff4949" :active-value="1" :inactive-value="0"></el-switch>
        </template>
      </el-table-column> -->
      <!-- 
      <el-table-column label="身份" prop="typeName">
        <template slot-scope="scope">
          <el-select @change="setType(scope.row)" v-model="scope.row.type" placeholder="scope.row.typeName">
            <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </template>
      </el-table-column> -->

      <!-- <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button size="mini" type="danger" @click="delOne(scope.row.id)">删除</el-button>
        </template>
      </el-table-column> -->
    </el-table>

    <div class="pagination-container">
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
        :current-page="searchForm.pageNum" :page-sizes="[2, 5, 10, 20]" :page-size="searchForm.pageSize"
        layout="total, sizes, prev, pager, next, jumper" :total="total">
      </el-pagination>
    </div>

  </div>
</template>

<script>
import { getByToken, userList, userPage, userDeleteByIds, userStateChangeById, userTypeChangeById, userAddOrUpdate } from "@/api/modules/user"
import { getStore } from "@/lib/storage"
export default {
  data() {
    return {
      visible: false,
      total: 10,
      searchForm: {
        pageNum: 1,
        pageSize: 10,
        username: "",
      },
      UserData: [],
      selectionIds: [],

      // options: [{
      //   value: 3,
      //   label: '施工人员'
      // }, {
      //   value: 2,
      //   label: '施工经理'
      // }, {
      //   value: 1,
      //   label: '超级管理员'
      // },],
    }
  },



  mounted() {
    this.loadUserData()
  },
  methods: {

    setType(row) {
      console.log(row.type)
      console.log(row.item)
      userAddOrUpdate({
        id: row.id,
        type: row.type
      }).then(result => {
        this.$message('状态更改成功')
      }).catch(err => {
        this.$message.error(err)
      })
    },

    setState(row) {
      userAddOrUpdate({
        id: row.id,
        state: row.state
      }).then(result => {
        this.$message('状态更改成功')
      }).catch(err => {
        this.$message.error(err)
      })
    },

    edit(row) {
      this.obj = row
      this.title = row.username
      this.visible = true;
    },

    delOne(id) {
      this.del(id)
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
        userDeleteByIds({ ids: ids }).then(result => {
          this.$message('删除成功')
          this.loadUserData()
        }).catch(err => {
          this.$message.error(err)
        })
      }).catch(() => {
        console.log("cancle")
      });
    },

    search() {
      this.loadUserData()
    },

    setText(state) {
      let lst = state
      lst.forEach(item => {
        switch (item.type) {
          case 1:
            item.typeName = "超级管理员"
            break
          case 2:
            item.typeName = "商家"
            break
          case 3:
            item.typeName = "会员"
            break
        }
      })
      return lst
    },

    loadUserData() {
      userPage({ ...this.searchForm })
        .then(result => {
          //this.UserData = result.data.records;
          this.UserData = this.setText(result.data.records);
          this.total = result.data.total;
          //console.log(result)
          // console.log(this.UserData)
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
    handleSelectionChange(val) {
      console.log(val)
      this.selectionIds = [];
      val.forEach(item => {
        // console.log(item.id)
        this.selectionIds.push(item.id)
      })
      console.log(this.selectionIds.join(","))
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