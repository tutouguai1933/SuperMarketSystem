const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
    transpileDependencies: true,
    devServer: {
        host:'localhost',
        port: 8090, // 端口
        proxy: {
            '/api': { //请求路径关键字
                target: 'http://localhost:8085/', //对应自己的接口
                changeOrigin: true,//是否允许跨域,在本地会创建一个虚拟服务端，然后发送请求的数据，
                // 并同时接收请求的数据，这样服务端和服务端进行数据的交互就不会有跨域问题
                ws: true,
                pathRewrite: {
                    '^/api': ''      //这里理解成用‘/api’代替target里面的地址，后面组件中我们掉接口时直接用api代替
                }
            }
        }
    },
})