<template>

<el-dialog title="管理字典" :visible="visible" :before-close="close">
    <el-form ref="searchForm" :model="searchForm" :inline="true" class="formItem" label-width="80px">
      <el-col :xl="6" :md="8" :sm="24">
        <el-form-item label="字典名称" prop="name" style="vertical-align: middle;">
          <el-input placeholder="字典名称" v-model="searchForm.name" clearable> </el-input>
        </el-form-item>
      </el-col>

      <el-col :xl="6||24" :md="8||24" :sm="24">
        <el-form-item>
          <div :style="{float:'right' ,overflow:'hidden'}||{}">
            <el-button icon="el-icon-search" type="primary" @click="search">搜索</el-button>
          </div>
        </el-form-item>
      </el-col>
    </el-form>


    <el-button type="primary" @click="add">新增</el-button>
    <el-button type="primary" @click="delAll">批量删除</el-button>


    <el-table :data="tableData" style="width: 100%" @selection-change="handleSelectionChange">
      <el-table-column
      type="selection"
      width="55">
    </el-table-column>
    <el-table-column label="字典名称" width="180" prop="name">
    </el-table-column>
    <el-table-column label="字典值" width="180" prop="value">
    </el-table-column>
    <el-table-column label="所属字典类型" width="180" prop="etc.dictype.name">
    </el-table-column>
    <el-table-column label="操作">
      <template slot-scope="scope">

        <el-button
          size="mini"
          @click="edit(scope.row)">编辑</el-button>
        <el-button size="mini" type="danger" @click="delOne(scope.row.id)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>

  <div class="block">
    <span class="demonstration">完整功能</span>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="searchForm.pageNum"
      :page-sizes="[2,5,10,20]"
      :page-size="searchForm.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total">
    </el-pagination>
  </div>
  
  <addOrEdit v-if="visible_e" :defaultFormDate="obj" :title="title" :visible="visible_e" @close="closeFather"></addOrEdit>
  </el-dialog>



</template>


<script>
  import { dicPage,dicDeleteByIds } from "@/api/modules/dic"
  import addOrEdit from "./dicAddOrEdit"
export default {
  name: 'dic_index',
  components:{addOrEdit},
  props:{
    visible:{
        type:Boolean,
        default:false
    },
    dictypeId:{
        type:String
    }
  },
  data() {
      return {
        selectionIds:[],
        visible_e:false,
        obj:{},
        title:"新增",
        total:10,

        tableData: [],
        searchForm:{
            pageNum:1,
            pageSize:10,
            name:'',
            dictypeId:"",
        },
      }
    },
    mounted(){
      this.searchForm.dictypeId = this.dictypeId
      this.loadTableData()
    },
    methods: {

        search(){
        this.loadTableData()
        },

      dicMgn(dictypeId){
      },
      delOne(id){
       del(id)
      },
      delAll(){
        if(this.selectionIds.length<=0){

          this.$message.warning("先选择记录")
          return
        }
        this.del(this.selectionIds.join(","))
      }, 
      del(ids){
        this.$confirm('确定删除该记录？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
            dicDeleteByIds({ids:ids}).then(result=>{
            this.$message('删除成功')
            this.loadTableData()
          }).catch(err=>{
            this.$message.error(err)
          })
        }).catch(() => {
          console.log("cancle")       
        });
      },
      add(){
        // //打开对话框
        // //执行操作
        this.visible_e = true;
        this.obj={dictypeId:this.dictypeId}
      },
      edit(row){
        this.obj=row
        this.title="编辑"
        this.visible_e = true;
      },
      loadTableData(){
        dicPage({
        ...this.searchForm
      }).then(result=>{
        console.log(result.data)
        this.total = result.data.total;
        this.tableData = result.data.records;
        //this.total = result.data.total
      }).catch(err=>{
        console.log("error:"+err)
      })
      },

      
      handleSizeChange(val) {
        this.searchForm.pageSize =val;
        this.loadTableData()
        console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {
        this.searchForm.pageNum =val;
        this.loadTableData()
        console.log(`当前页: ${val}`); 
      },
      close(){
       this.$emit("close",false)
      },
      handleSelectionChange(val){
        this.selectionIds=[];
        val.forEach(item=>{
            // console.log(item.id)
            this.selectionIds.push(item.id)
        })
        console.log(this.selectionIds.join(","))

      },
      closeFather(val){
        this.visible_e=false
        if(val){
          this.loadTableData()
        }
      },
    }
  }
</script>