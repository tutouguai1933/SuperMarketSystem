<template>
  <div style="padding: 10px;width: calc(100vh - 200px)">
    <!--    搜索区域-->
    <div style="margin: 10px 0">
      <span style="margin: 0px 10px 0px 10px">姓名:</span>
      <el-input v-model="this.searchForm.userName" placeholder="请输入姓名" style="width: 20%" clearable></el-input>
      <span style="margin: 0px 10px 0px 10px">角色:</span>
      <el-select v-model="this.searchForm.userRole" clearable placeholder="--请选择--">
        <el-option
            v-for="item in this.searchForm.userRoleOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value"
        />
      </el-select>
      <el-button type="primary" style="margin-left: 5px" @click="load">查询</el-button>
      <el-button type="primary" style="margin-left: 430px;padding: 0 40px " @click="add" :disabled="this.editPermissionDisabled">新增用户</el-button>
    </div>
    <el-table
        v-loading="loading"
        :data="tableData"
        border
        stripe
        style="width: 100%"
        @selection-change="handleSelectionChange"
    >
      <el-table-column
          prop="id"
          label="ID"
          sortable
          fixed
          width="50px"
      >
      </el-table-column>
      <el-table-column
          prop="userCode"
          label="用户名">
      </el-table-column>
      <el-table-column
          prop="userName"
          label="姓名">
      </el-table-column>
      <el-table-column
          prop="genderShow"
          label="性别">
      </el-table-column>
      <el-table-column
          prop="birthdayShow"
          label="生日"
          width="150px">
      </el-table-column>
      <el-table-column
          prop="phone"
          label="手机号码"
          width="150px">
      </el-table-column>
      <el-table-column
          prop="address"
          label="地址">
      </el-table-column>
      <el-table-column
          prop="userRoleName"
          label="用户角色">
      </el-table-column>
      <el-table-column
          prop="creationDateShow"
          label="创建时间"
          width="150px">
      </el-table-column>
      <el-table-column label="操作" width="240">
        <template #default="scope">
          <el-button size="mini" @click="handleEdit(scope.row)" :disabled="this.editPermissionDisabled">编辑</el-button>
          <el-popconfirm title="确定删除吗？" @confirm="handleDelete(scope.row.id)" >
            <template #reference>
              <el-button size="mini" type="danger" :disabled="this.editPermissionDisabled">删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <div style="margin: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>

      <el-dialog title="提示" v-model="dialogVisible" width="30%" draggable>
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
            <el-select v-model="this.form.userRole" clearable placeholder="--请选择--">
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
          <span class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="save">确 定</el-button>
          </span>
        </template>
      </el-dialog>

    </div>
  </div>
</template>


<script>


import request from "@/utils/request";

export default {
  name: 'User',
  components: {},
  data() {
    return {
      user: {},
      loading: true,
      form: {},
      dialogVisible: false,
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
      },
      currentPage: 1,
      pageSize: 5,
      total: 0,
      tableData: [],
      editPermissionDisabled:true
    }
  },
  created() {

    let userStr = sessionStorage.getItem("user")

    this.user = JSON.parse(userStr)

    if (!this.user) {
      this.$router.push('/login')
    }

    if(this.user.userRole==1){
      this.editPermissionDisabled=false
    }

    request.post("/user/getRoleList").then(res => {//加载供应商列表
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
    this.load()


  },
  methods: {
    handleSelectionChange(val) {
      this.ids = val.map(v => v.id)   // [{id,name}, {id,name}] => [id,id]
    },
    load() {
      this.loading = true
      request.post("/user/query/" + this.currentPage + '/' + this.pageSize, {
        userName:this.searchForm.userName,
        userRole:this.searchForm.userRole
      }).then(res => {
        this.loading = false
        this.tableData = res.data.userList
        this.total = res.data.total

        this.tableData.forEach(function (row) {
          row.genderShow = row.gender === 2 ? '男' : '女'
          row.birthdayShow = (new Date(row.birthday)).format("yyyy-MM-dd  hh:mm");
          row.creationDateShow = (new Date(row.creationDate)).format("yyyy-MM-dd  hh:mm");
        })
      })
    },
    add() {
      this.dialogVisible = true
      this.form = {}
      this.form.creationDate = new Date().format('yyyy-MM-dd hh:mm:ss')
    },
    save() {
      if (this.form.id) {  // 更新
        this.form.modifyBy = this.user.id
        this.form.modifyDate = new Date().format('yyyy-MM-dd hh:mm:ss')
        request.post("user/modifyById", this.form).then(res => {
          console.log(res)
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
          this.load() // 刷新表格的数据
          this.dialogVisible = false  // 关闭弹窗
        })
      } else {  // 新增
        request.post("/user/add", this.form).then(res => {
          console.log(res)
          if (res.code === '0') {
            this.$message({
              type: "success",
              message: "新增成功"
            })
          } else {
            this.$message({
              type: "error",
              message: res.msg
            })
          }
          this.load() // 刷新表格的数据
          this.dialogVisible = false  // 关闭弹窗
        })
      }
    },
    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogVisible = true
    },
    handleDelete(id) {
      request.post("/user/delUserById/" + id).then(res => {
        if (res.code === '0') {
          this.$message({
            type: "success",
            message: "删除成功"
          })
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
        this.load()  // 删除之后重新加载表格的数据
      })
    },
    handleSizeChange(pageSize) {   // 改变当前每页的个数触发
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {  // 改变当前页码触发
      this.currentPage = pageNum
      this.load()
    }
  }
}

Date.prototype.format = function (fmt) {
  var o = {
    "M+": this.getMonth() + 1, //月份
    "d+": this.getDate(), //日
    "h+": this.getHours(), //小时
    "m+": this.getMinutes(), //分
    "s+": this.getSeconds(), //秒
    "q+": Math.floor((this.getMonth() + 3) / 3), //季度
    "S": this.getMilliseconds() //毫秒
  };
  if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
  for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
  return fmt;
}
</script>
