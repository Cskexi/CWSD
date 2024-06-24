<template>
  <div id="app" style="height: 100%; width: 100%;">
    <router-view />
  </div>
</template>

<script>
// export default {
//   mounted() {
//     window.addEventListener('beforeunload', this.onBeforeUnload);
//   },
//   methods: {
//     onBeforeUnload() {
//       // 执行清理操作，例如清除localStorage或sessionStorage
//       localStorage.clear();
//       sessionStorage.clear();

//       // 如果使用Vuex，可以在这里提交一个action或mutation来清理状态
//       // this.$store.dispatch('clearState');

//       // 如果使用Axios，可以在这里清除Axios的拦截器或默认配置
//       // axios.interceptors.response.eject(/* 拦截器ID */);
//       // 或者重置Axios的默认配置
//       // axios.defaults.baseURL = ''; // 根据需要重置
//     }
//   },
//   // 在组件被销毁前移除事件监听器
//   beforeDestroy() {
//     window.removeEventListener('beforeunload', this.onBeforeUnload);
//   }
// };
export default {
  mounted() {
    // 使用window的addEventListener来监听浏览器关闭事件
    window.addEventListener('beforeunload', this.onBeforeUnload);
  },
  methods: {
    onBeforeUnload() {
      // 这里执行清理操作，比如清除localStorage、sessionStorage等
      localStorage.clear();
      sessionStorage.clear();

      // 如果使用Vuex，可以在这里提交一个action来清理状态
      // this.$store.dispatch('someAction');

      // 如果使用Axios，可以在这里清除Axios的拦截器或重置配置
      // axios.interceptors.response.eject(/* 拦截器ID */);
      // axios.defaults.baseURL = ''; // 根据需要重置
    }
  },
  beforeDestroy() {
    // 确保在组件销毁时移除事件监听器，避免内存泄漏
    window.removeEventListener('beforeunload', this.onBeforeUnload);
  }
};
</script>


<style lang="scss">
#body {
  background-color: aqua;
}
</style>