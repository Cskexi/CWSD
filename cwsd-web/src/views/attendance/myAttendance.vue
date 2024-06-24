<template>
    <div>


    <el-table :data="myApply" style="width: 100%" @selection-change="handleSelectionChange">
      <el-table-column label="起始时间" prop="start"></el-table-column>
      <el-table-column label="结束时间" prop="end"></el-table-column>
      <el-table-column label="请假类型" prop="typeName"></el-table-column>
      <el-table-column label="请假原因" prop="reason"></el-table-column>
      <el-table-column label="审核状态" prop="stateName"></el-table-column>
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
  import { applyMyPage } from "@/api/modules/apply"
  import { getByToken } from "@/api/modules/user"
  import { getStore } from "@/utils/storage"
export default {
    data() {
      return {
        total: 0,
    searchForm: {
        pageNum: 1,
        pageSize: 10,
        userId: "",
      },
      myApply:[],
      }
    },
    mounted() {
        this.loadUserData()

    },

    methods:{

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