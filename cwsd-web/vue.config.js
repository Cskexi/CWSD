// const { defineConfig } = require('@vue/cli-service')
// module.exports = defineConfig({
//   transpileDependencies: true
// })

const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  devServer: {
    hot: true, // 启用完整的热模块替换
    // 其他配置...

  },
  transpileDependencies: true,
  publicPath: '/',
  outputDir: 'dist', // 打包的目录
  lintOnSave: true, // 在保存时校验格式
  productionSourceMap: false, // 生产环境是否生成 SourceMap
  // disableHostCheck: true,
  devServer: {
    allowedHosts: "all",

    open: true, // 启动服务后是否打开浏览器
    host: '0.0.0.0',
    //host: 'localhost',
    port: 8080, // 服务端口
    https: false,
    hot: "only",
    //api/book/save->
    //http://localhost:8899/spring/book/save
    proxy: {
      '/api': {
        //名字必须跟application context相同 否则404
        target: 'http://localhost:8083/',
        changeOrigin: true, //  跨域
        pathRewrite: {
          '^/api': ''
        }
      }
    }, // 设置代理

  }
})