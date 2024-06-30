<template>
  <div>
    <h1>This is an myMixin page</h1><br>
    <span>{{ MYGL.projectName }}</span><br>
    <button @click="sayHi">点击say hi</button><br> 

    <span>{{ mxName}}</span><br>
    <button @click="mxSayHimx">点击say hi</button><br> 

    <span>{{ moduleName}}</span><br>
    <button @click="saymodule">点击say hi</button><br> 
  </div>
</template>

``
<script>
import moduleMixin from "@/views/moduleMixin"

export default {
  name: 'myMixin',
  mixins:[moduleMixin],
  data(){
    return{

    }   
  },
  methods:{
    sayHi(){
      this.MYGL.sayHi();
    },
    mxSayHimx(){
      this.mxSayHi();
    },
    saymodule(){
      this.mm();
    }
  }
}
</script>