<template>
  <!-- <div class="app-wrapper">
        <sidebar class="sidebar-container"/>
        <div class="main-container">
          <navbar/>
          布局
          <app-main/>
        </div>    
    </div> -->
  <el-container style="height: 100%; width: 100%;">
    <el-aside width="200px" style="background-color: white;">
      <Sidebar />
    </el-aside>
    <el-container>
      <el-header>
        <Navbar />
      </el-header>
      <el-main style=" width: 100%;"><app-main /></el-main>
    </el-container>
  </el-container>
</template>

<script>
import { AppMain, Sidebar, Navbar } from './components'

export default {
  name: 'Layout',
  components: {
    AppMain,
    Sidebar,
    Navbar,
  },
}

</script>

<style lang="scss" scoped>
.app-wrapper {
  position: relative;
  height: 100%;
  width: 100%;
}


.el-header,
.el-footer {
  background-color: #B3C0D1;
  color: #333;
}

.el-aside {
  background-color: #D3DCE6;
  color: #333;
  text-align: center;
  line-height: 200px;

}

.el-main {
  background-color: #E9EEF3;
  color: #333;
}

body>.el-container {
  margin-bottom: 40px;
}
</style>