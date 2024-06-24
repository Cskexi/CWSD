<template>
  <div>
    <!-- <h1 class="page-title">用户管理</h1> -->
    <el-form ref="searchForm" :model="searchForm" :inline="true" class="formItem" label-width="80px">
      <el-col :xl="6" :md="8" :sm="24" style="margin-bottom: 20px;">
        <el-input placeholder="姓名" v-model="searchForm.name" clearable> </el-input>
      </el-col>
      <el-col :xl="6 || 24" :md="6" :sm="24" style="margin-right: 15px">
        <el-button icon="el-icon-search" type="primary" style="margin-left: 15px;" @click="search">搜索</el-button>
        <el-button type="primary" @click="delAll">批量删除</el-button>
      </el-col>
      <el-col :xl="3 || 24" :md="6" :sm="24" style="float:right">
        <el-input-number v-model="num" controls-position="right" :min="1" :max="100"></el-input-number>
        <el-button type="primary" @click="addAll(num)" style="margin-left: 15px;">批量添加</el-button>
      </el-col>
      <!-- <el-col :xl="4 || 24" :md="4 || 24" :sm="24">
      
    </el-col> -->
    </el-form>

    <el-table :data="UserData" style="width: 100%;" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column label="用户名" prop="account"></el-table-column>
      <el-table-column label="姓名" prop="name"></el-table-column>
      <el-table-column label="性别" prop="sexName"></el-table-column>
      <el-table-column label="手机号" prop="telephone"></el-table-column>
      <!-- <el-table-column label="是否可用">
        <template slot-scope="scope">
          <el-switch @change="setState(scope.row)" v-model="scope.row.state" active-color="#13ce66"
            inactive-color="#ff4949" :active-value="1" :inactive-value="0" style="margin-left: 10px"></el-switch>
        </template>
      </el-table-column> -->
      <el-table-column label="身份" prop="typeName">
        <template slot-scope="scope">
          <el-select @change="setType(scope.row)" v-model="scope.row.typeName" placeholder="scope.row.typeName"
            style="width: 105px;">
            <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"
              style="width: 96px; height:40px">
            </el-option>
          </el-select>
        </template>
      </el-table-column>

      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button size="mini" type="danger" @click="delOne(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <div class="pagination-container">
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
        :current-page="searchForm.pageNum" :page-sizes="[10, 20, 50]" :page-size="searchForm.pageSize"
        layout="total, sizes, prev, pager, next, jumper" :total="total">
      </el-pagination>
    </div>

  </div>
</template>

<script>
import { userPage, userDeleteByIds, userAddOrUpdate, userAdd } from "@/api/modules/user"
import { getStore } from "@/utils/storage"
export default {
  data() {
    return {
      num: 1,
      visible: false,
      total: 10,
      searchForm: {
        pageNum: 1,
        pageSize: 10,
        name: "",
      },
      UserData: [],
      selectionIds: [],

      options: [{
        value: 2,
        label: '监测人员'
      }, {
        value: 1,
        label: '施工人员'
      }, {
        value: 0,
        label: '管理员'
      },],
    }
  },
  mounted() {
    this.loadUserData()
  },
  methods: {

    setType(row) {
      console.log(row.typeName)
      console.log(row.id)
      console.log(1)
      userAddOrUpdate({
        id: row.id,
        type: row.typeName
      }).then(result => {
        console.log(result)
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
    addAll(num) {
      userAdd({ num: num }).then(result => {
        this.$message('添加成功')
        this.loadUserData()
      }).catch(err => {
        this.$message.error(err)
      })
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
          case '0':
            item.typeName = "管理员"
            break
          case '1':
            item.typeName = "施工人员"
            break
          case '2':
            item.typeName = "施工经理"
            break
          default:
            console.error("Unhandled type:", item.type);
        }
        switch (item.sex) {
          case "0":
            item.sexName = "女"
            break
          case "1":
            item.sexName = "男"
            break
          default:
            console.error("Unhandled type:", item.type);
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
          //console.log(result.data.records)
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