<template>
  <el-col :span="30">
    <el-menu
        style="width: 200px; min-height: calc(100vh - 50px)"
        :default-active="$route.path"
        class="el-menu-vertical-demo"
        router
    >
      <div style="width: 200px;padding-top: 20px;">
        <h3 style="text-align: center;margin: 0 auto;">功能区域</h3>
      </div>
      <el-divider>
        <el-icon><star-filled /></el-icon>
      </el-divider>
      <div  v-for="m in this.menuItems" :key="m.index">
        <el-menu-item :index="m.index" style="padding-left: 50px;" :disabled="m.disabled">
          <el-icon>
            <component :is="m.icon"></component>
          </el-icon>
          <span>{{m.name}}</span>
        </el-menu-item>
      </div>
    </el-menu>
  </el-col>
</template>

<script>

export default {
  name: "Aside",
  data() {
    return {
      user: {},
      menuItems:[
        {index:'bill',name:'订单管理',icon:'List',disabled:false},
        {index:'provider',name:'供应管理',icon:'Grid',disabled: false},
        {index:'user',name:'用户管理',icon:'Avatar',disabled:true},
        {index:'password',name:'密码修改',icon:'Setting',disabled: false},
        {index:'message',name:'公告通知',icon:'ChatDotSquare',disabled: false},
      ],

    }
  },
  created() {

    let userStr = sessionStorage.getItem("user")

    this.user = JSON.parse(userStr)

    if(!this.user){
      this.$router.push('/login')
    }

    if (this.user.userRole<=2){
      this.menuItems[2].disabled=false
    }

  },
  methods:{
  }
}
</script>

<style scoped>

</style>
