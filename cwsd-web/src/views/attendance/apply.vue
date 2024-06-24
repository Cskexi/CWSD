<template>
    <div class="store-management">
      <h1>请假申请</h1>
      <p>请假类型</p>
      <el-select v-model="apply.type" placeholder="请选择">
      <el-option
        v-for="item in options"
        :key="item.value"
        :label="item.label"
        :value="item.value">
      </el-option>
    </el-select>
      <p>请假日期</p>
      <div class="block">
    <el-date-picker
      v-model="date"
      type="daterange"
      range-separator="至"
      start-placeholder="开始日期"
      end-placeholder="结束日期"
      value-format="yyyy-MM-dd">
    </el-date-picker>
  </div>
      <p>请假原因</p>
      <el-input
    type="textarea"
    :rows="8"
    v-model="apply.reason">
  </el-input>
  <el-button type="primary" @click="submitApply">提交</el-button>
    </div>
  </template>
    
  <script>
    import { getByToken } from "@/api/modules/user"
    import { applyAddOrUpdate } from "@/api/modules/apply"
  import { getStore } from "@/utils/storage"

  export default {
    data() {
      return {
        user:{
            id:"",
        },
        date:"",
        apply:{
            start:"",
            end:"",
            type:0,
            reason:"",
            userId:"",
        },
        options: [{
            value: 0,
            label: "事假"
          }, {
            value: 1,
            label: '病假'
          }, {
            value: 2,
            label: '年假'
          },
          {
            value: 3,
            label: '其他'
          }],
      }
    },
    mounted() {
      this.loadUserData()

    },
    methods: {
      loadUserData(){
              let token = getStore("token")
              if(token){
                  getByToken({token:token}).then(result=>{
                  this.user=result.data;
                  this.apply.userId=this.user.id;
                  }).catch(err=>{
                      console.log("error:"+err)
                  })
              }
              else{
                  console.log("token为空")
              }
          },
          submitApply(){
            this.apply.start=this.date[0];
            this.apply.end=this.date[1];
            applyAddOrUpdate({...this.apply}).then(result=>{
                this.$message('提交成功')
                  }).catch(err=>{
                    this.$message("error:"+err)
                  })
          }
    },
  }
  
  </script>
    
  
  <style scoped>
  </style>