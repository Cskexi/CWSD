<template>
    <div class="site-wrapper">
      <h2>404 Page</h2>
      <p>抱歉！您访问的页面<em>失联</em></p>
      <el-button>返回上一页</el-button>
    </div>
  </template>
  
  <script>
  export default {
    name: '404'
  }
  </script>

  <style lang="scss">
  .site-wrapper em{
    color: red;
  }
    </style>