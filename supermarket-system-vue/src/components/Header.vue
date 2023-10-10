<template>
  <div style="height: 50px; line-height: 50px; border-bottom: 1px solid #ccc; display: flex">
    <div style="width: 200px; padding-left: 30px; font-weight: bold; color: dodgerblue">超市订单管理系统</div>
    <div style="flex: 1"></div>
    <div style="width: 200px">
      <el-dropdown>
        <span class="el-dropdown-link">
          <el-avatar :size="30" :src="circleUrl" style="position: relative; top: 10px"></el-avatar>
          {{ this.user.userName }}
          <el-icon class="el-icon--right">
            <arrow-down/>
          </el-icon>
        </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="openUserInfo">个人信息</el-dropdown-item>
            <el-dropdown-item @click="$router.push('/password')">修改密码</el-dropdown-item>
            <el-dropdown-item @click="$router.push('/login')">退出系统</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
  <el-drawer v-model="drawer" title="个人信息修改" :with-header="true">
    <el-form :model="form" label-width="120px">
      <el-form-item label="用户名">
        <el-input v-model="form.userCode" style="width: 80%"></el-input>
      </el-form-item>
      <el-form-item label="姓名">
        <el-input v-model="form.userName" style="width: 80%"></el-input>
      </el-form-item>
      <el-form-item label="用户密码">
        <el-input v-model="form.userPassword" style="width: 80%"></el-input>
      </el-form-item>
      <el-form-item label="性别">
        <el-select v-model="this.form.gender" clearable placeholder="--请选择--">
          <el-option
              v-for="item in this.searchForm.genderOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="生日">
        <div class="block" style="width: 80%">
          <el-date-picker
              v-model="form.birthday"
              type="date"
              placeholder="Pick a day"
              :size="size"
              format="YYYY-MM-DD"
          />
        </div>
      </el-form-item>
      <el-form-item label="手机号码">
        <el-input v-model="form.phone" style="width: 80%"></el-input>
      </el-form-item>
      <el-form-item label="地址">
        <el-input v-model="form.address" style="width: 80%"></el-input>
      </el-form-item>
      <el-form-item label="用户角色">
        <el-select v-model="this.form.userRole" clearable placeholder="--请选择--" disabled>
          <el-option
              v-for="item in this.searchForm.userRoleOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间">
        <el-input v-model="form.creationDate" style="width: 80%"></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <div style="flex: auto">
        <el-button @click="drawer=false">退出</el-button>
        <el-button type="primary" @click="save">保存</el-button>
      </div>
    </template>
  </el-drawer>
</template>

<script>

import user from "@/views/User";
import request from "@/utils/request";

export default {
  name: "Header",
  data() {
    return {
      drawer: false,
      form: {},
      user: {},
      circleUrl: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
      searchForm: {
        userName: '',
        userRole: 0,
        userRoleOptions: [
          {
            value: 0,
            label: '--请选择--',
          }
        ],
        genderOptions: [
          {
            value: 1,
            label: '女',
          },{
            value: 2,
            label: '男',
          }
        ],
      }
    }
  },
  created() {
    let userStr = sessionStorage.getItem("user")

    this.user = JSON.parse(userStr)

    if (!this.user) {
      this.$router.push('/login')
    }

    console.log(this.user)
  },
  methods:{
    openUserInfo(){
      this.drawer = true
      this.form = this.user

      request.post("/user/getRoleList").then(res => {
        let that = this
        res.data.forEach(function (role) {
          that.searchForm.userRoleOptions.push(
              {
                value: role.id,
                label: role.roleName,
              }
          )
        })
      })

    },
    save(){
      this.form.modifyBy = this.user.id
      this.form.modifyDate = new Date().format('yyyy-MM-dd hh:mm:ss')
      request.post("user/modifyById", this.form).then(res => {
        if (res.code === '0') {
          this.$message({
            type: "success",
            message: "更新成功"
          })
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
