<template>
  <el-container>
  <div class="shop-container">
    <header>
      <router-link to="/ShoppingPage">主页</router-link> | 
      <router-link to="/mainPage">管理界面</router-link> |
      <router-link to="/LoginPage">退出登录</router-link>
    </header>
    <main>
      <router-view/>
    </main>
  </div>
</el-container>
</template>
<script>
export default {
}
</script>
<style scoped>
.shop-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
}

:root {
  --primary-color: #35424a;
  --text-color: #ffffff;
  --link-hover-color: #ffd700;
}

header {
  background-color: var(--primary-color);
  color: var(--text-color);
  padding: 10px;
  text-align: center;
  font-size: 18px;
}

router-link {
  color: var(--text-color);
  margin: 0 10px;
  text-decoration: none;
  transition: color 0.3s ease;
}

router-link:hover {
  color: var(--link-hover-color);
}

main {
  margin-top: 20px;
}
</style>