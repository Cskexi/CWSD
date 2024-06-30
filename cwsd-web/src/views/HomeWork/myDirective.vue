<template>
    <div>
        <h1>mymyDirective</h1>
        <input type="text" v-focus>
    </div>
  </template>
  
  <script>
  export default {
    name: 'myDirective',
  //   directives:{
  //   focus:{
  //     inserted:function(el,binding){
  //       console.log(binding);
  //       //指令需要完成的内容
  //       el.focus()
  //     }
  //   }
  //  }
  }
  </script>
  