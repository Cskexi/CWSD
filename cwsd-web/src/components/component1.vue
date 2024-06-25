<template>
    <div>
        <h1>component1</h1>
        {{ msg }}--{{ age }}--{{ obj.name }}--{{ ary.join("*") }}
        <button @click="callback">点击回调</button>
    </div>
</template>

<script>
export default {
  name: 'component1',
  props:{
    msg:{
      type:String,
      default:"hello"
    },
    age:{
      type:Number,
      default:100,
      validator:function(val){
        if(val < 100){
          console.log("smaller 100")
          return false;
        }
        return true;
      }
    },
    obj:{
      type:Object
    },
    ary:{
      typr:Array
    }
  },
  methods:{
    callback(){
      this.$emit("cb",false)//回调的函数 传的值
    }
  }
}

</script>