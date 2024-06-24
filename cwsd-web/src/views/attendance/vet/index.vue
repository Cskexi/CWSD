<template>
    <div>

        <el-form ref="searchForm" :model="searchForm" :inline="true" class="formItem" label-width="80px">
          <el-col :xl="6" :md="8" :sm="24">
            <el-input placeholder="名称" v-model="searchForm.name" clearable> </el-input>
          </el-col>
  
          <el-col :xl="6 || 24" :md="8 || 24" :sm="24">
            <el-form-item>
              <div :style="{ float: 'right', overflow: 'hidden' } || {}">
                <el-button icon="el-icon-search" type="primary" @click="search">搜索</el-button>
              </div>
            </el-form-item>
          </el-col>
          <el-col :xl="6 || 24" :md="8 || 24" :sm="24">
            <el-form-item>
                <el-select v-model="searchForm.type" placeholder="请选择">
      <el-option
        v-for="item in options"
        :key="item.value"
        :label="item.label"
        :value="item.value">
      </el-option>
    </el-select>
            </el-form-item>
          </el-col>
          <el-col :xl="6 || 24" :md="8 || 24" :sm="24">
            <el-form-item>
                <el-select v-model="searchForm.type" placeholder="请选择">
      <el-option
        v-for="item in options"
        :key="item.value"
        :label="item.label"
        :value="item.value">
      </el-option>
    </el-select>
            </el-form-item>
          </el-col>
        </el-form>


      
    <el-table :data="myApply" style="width: 100%" @selection-change="handleSelectionChange">
        <el-table-column label="申请人" prop="userId"></el-table-column>
      <el-table-column label="起始时间" prop="start"></el-table-column>
      <el-table-column label="结束时间" prop="end"></el-table-column>
      <el-table-column label="请假类型" prop="typeName"></el-table-column>
      <el-table-column label="请假原因" prop="reason"></el-table-column>
      <el-table-column label="审核状态" prop="stateName"></el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button size="mini" type="primary" @click=" edit">审核</el-button>
        </template>
      </el-table-column>
    </el-table>

    <div class="pagination-container">
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
        :current-page="searchForm.pageNum" :page-sizes="[10, 20, 50]" :page-size="searchForm.pageSize"
        layout="total, sizes, prev, pager, next, jumper" :total="total">
      </el-pagination>
    </div>
    <addOrEdit v-if="visible" :defaultFormDate="obj" :title="title" :visible="visible" @close="closeFather"></addOrEdit>
    </div>

</template>

<script>
  import { applyMyPage } from "@/api/modules/apply"
  import { getByToken } from "@/api/modules/user"
  import { getStore } from "@/utils/storage"
  import addOrEdit from "./AddOrEdit"
export default {
  components: { addOrEdit },
    data() {
      return {

        visible: false,
      obj: {},
      title: "修改",

        total: 0,
    searchForm: {
        pageNum: 1,
        pageSize: 10,
        userId: "",
        name:"",
        type:0,
        state:0,
      },
      myApply:[],
      }
    },
    mounted() {
        this.loadUserData()
    },

    methods:{
      edit() {
      this.obj = this.user;
      this.title = "审核"
      this.visible = true;
    },
     setText(lst) {
      lst.forEach(item => {
        switch (item.type) {
          case 0:
            item.typeName = "病假"
            break
          case 1:
            item.typeName = "事假"
            break
          case 2:
            item.typeName = "年假"
            break
            case 3:
            item.typeName = "其他"
            break
          default:
            console.error("Unhandled type:", item.type);
        }
        switch (item.state) {
          case 0:
            item.stateName = "待审核"
            break
          case 1:
            item.stateName = "审核通过"
            break
          case 2:
            item.stateName = "审核不通过"
            break
          default:
            console.error("Unhandled state:", item.state);
        }
      })
      return lst
    },

    loadApplyData() {
        applyMyPage({ ...this.searchForm })
        .then(result => {
            this.myApply = result.data.records;
            this.myApply =this.setText(this.myApply);
          //this.UserData = result.data.records;
          //this.UserData = this.setText(result.data.records);
          //this.total = result.data.total;
          //console.log(result.data.records)
       //  console.log(this.UserData)
        }).catch(err => {
          console.log("error:" + err)
        })
    },


    loadUserData() {
      let token = getStore("token")
      if (token) {
        getByToken({ token: token }).then(result => {
            this.searchForm.userId= result.data.id
            console.log( this.searchForm.userId)
            this.loadApplyData()
        }).catch(err => {
          console.log("error:" + err)
        })
      }
      else {
        console.log("token为空")
      }
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
    }
}
</script>