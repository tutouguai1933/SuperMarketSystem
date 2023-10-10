<template>
  <div>
    <el-card style="width: 40%; margin: 10% auto 0px auto ">
      <el-form ref="form" :model="form" label-width="80px" :rules="rules">
        <el-form-item label="原密码" prop="oldPass">
          <el-input v-model="form.oldPass" show-password @blur="checkOldPass"></el-input>
        </el-form-item>
        <el-form-item label="新密码" prop="newPass">
          <el-input v-model="form.newPass" show-password></el-input>
        </el-form-item>
        <el-form-item label="确认新密码" prop="confirmPass">
          <el-input v-model="form.confirmPass" show-password></el-input>
        </el-form-item>
      </el-form>

      <div style="text-align: center">
        <el-button type="primary" @click="changePass">保存</el-button>
      </div>
    </el-card>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "Password",
  created() {
    let userStr = sessionStorage.getItem("user")

    this.user = JSON.parse(userStr)

    if(!this.user){
      this.$router.push('/login')
    }
  },
  data() {
    return {
      form: {
        oldPass: '',
        newPass: '',
        confirmPass: ''
      },
      rules: {
        oldPass: [
          {required: true, message: '请输入密码', trigger: 'blur'},
        ],
        newPass: [
          {required: true, message: '请输入新密码', trigger: 'blur'},
        ],
        confirmPass: [
          {required: true, message: '请输入确认新密码', trigger: 'blur'},
        ],
      },
    }
  },
  methods: {
    checkOldPass(){
      if (this.form.oldPass!=this.user.userPassword){
        this.$message.error('旧密码输入错误')
        return
      }
    },
    changePass() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          if (this.form.oldPass!=this.user.userPassword){
            this.$message.error('旧密码输入错误')
            return
          }
          console.log(this.form)
          if (this.form.newPass != this.form.confirmPass) {
            this.$message.error('2次输入新密码必须一致')
            return
          }
          request.post("/user/updatePwd", {
            id:this.user.id,
            userPassword:this.form.newPass
          }).then(res => {
            if (res.code === '0') {
              this.$message.success('修改成功')
              this.$router.push("/login")
            } else {
              this.$message.error(res.msg)
            }
          })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
