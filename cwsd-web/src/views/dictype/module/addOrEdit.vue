<template>
    <el-dialog :title="title" :visible="visible" :before-close="close">
        <el-form :model="form" :rules="rules" ref="form">
           <el-form-item label="字典类型名称" prop="name">
                <el-input v-model="form.name" autocomplete="off"></el-input>
           </el-form-item> 
           <el-form-item label="字典类型值" prop="value">
                <el-input v-model="form.value" autocomplete="off"></el-input>
           </el-form-item> 
        </el-form>

        <div slot="footer" class="dialog-footer">
            <el-button @click="close = false">取 消</el-button>
            <el-button type="primary" @click="submit">确 定</el-button>
        </div>
</el-dialog>
</template>    

<script>
import {dictypeAddOrUpdate} from "@/api/modules/dictype"

const validateTelephone = (rule,value,callback)=>{
    let reg = /^[1][3,4,5,7,8][0-9]{9}$/;
    if(!reg.test(value)){
        callback(new Error("手机号格式错误"));

    }else{
        callback();
    }
}

  export default {
    name:"addOrEdit",
    props:{
        visible:{
            type:Boolean,
            default:false
        }, 
        title:{
            type:String,
            default:"新增"
        },
        defaultFormDate:{
            type:Object,
        },
    },
    data() {
      return {
        flag:false,
        form:{
            name:'',
            value:''
        },
        rules:{
        //     name:[{required:true,message:"字典类型不能为空"},
        //     {type:"email",message:"邮箱格式不正确"}
        // ],
        //     value:[{required:true,message:"字典类型值不能为空",trigger:"blur"},
        //      {validator:validateTelephone,trigger:"blur"}],
        }
      };
    },
    mounted(){
        if(JSON.stringify(this.defaultFormDate)=="{}"){
            this.flag = false
        }else{
            this.form = JSON.parse(JSON.stringify(this.defaultFormDate))
            console.log(this.form)
            this.flag =true;
        }
    },
    methods: {
      close() {
        this.flag = false;
        this.$emit("close",this.flag)
      },
      submit(){
        this.$refs.form.validate(valid=>{
            if(valid){
                dictypeAddOrUpdate({
                ...this.form
            }).then(res=>{
                this.flag = true
                this.$emit("close",this.flag)
            })
            .catch(err=>{
                this.flag = false
                this.$emit("close",this.flag)
            })
            }
               
        })
        
      }
    }
  };
</script>