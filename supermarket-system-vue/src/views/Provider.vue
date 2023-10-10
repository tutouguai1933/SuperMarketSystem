<template>
  <div style="padding: 10px ;width: calc(100vh - 200px)">
    <!--    搜索区域-->
    <div style="margin: 10px 0">
      <span style="margin: 0px 10px 0px 10px">供应商编码:</span>
      <el-input v-model="this.searchForm.proCode" placeholder="请输入供应商编码" style="width: 20%" clearable></el-input>
      <span style="margin: 0px 10px 0px 10px">供应商名称:</span>
      <el-input v-model="this.searchForm.proName" placeholder="请输入供应商名称" style="width: 20%" clearable></el-input>
      <el-button type="primary" style="margin-left: 5px" @click="load">查询</el-button>
      <el-button type="primary" style="margin-left: 280px;padding: 0 40px " @click="add">新增供应商</el-button>
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
          width="100px"
      >
      </el-table-column>
      <el-table-column
          prop="proCode"
          label="供应商编码"
          width="120px">
      </el-table-column>
      <el-table-column
          prop="proName"
          label="供应商名称"
          width="200px">
      </el-table-column>
      <el-table-column
          prop="proDesc"
          label="描述"
          width="250px">
      </el-table-column>
      <el-table-column
          prop="proContact"
          label="联系人"
          width="100px">
      </el-table-column>
      <el-table-column
          prop="proPhone"
          label="联系电话"
          width="150px">
      </el-table-column>
      <el-table-column
          prop="proAddress"
          label="地址"
          width="200px">
      </el-table-column>
      <el-table-column
          prop="proFax"
          label="传真"
          width="150px">
      </el-table-column>
      <el-table-column
          prop="creationDateShow"
          label="创建时间"
          width="150px">
      </el-table-column>
      <el-table-column label="操作" width="240" fixed='right'>
        <template #default="scope">
          <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
          <el-popconfirm title="确定删除吗？" @confirm="handleDelete(scope.row.id)">
            <template #reference>
              <el-button size="mini" type="danger">删除</el-button>
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
          <el-form-item label="供应商编码">
            <el-input v-model="form.proCode" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="供应商名称">
            <el-input v-model="form.proName" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="描述">
            <el-input v-model="form.proDesc" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="联系人">
            <el-input v-model="form.proContact" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="联系电话">
            <el-input v-model="form.proPhone" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="地址">
            <el-input v-model="form.proAddress" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="传真">
            <el-input v-model="form.proFax" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="创建时间">
            <el-input v-model="form.creationDate" style="width: 80%" ></el-input>
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
  name: 'Provider',
  components: {},
  data() {
    return {
      user: {},
      loading: true,
      form: {},
      dialogVisible: false,
      searchForm: {
        proCode:'',
        proName: '',
      },
      currentPage: 1,
      pageSize: 5,
      total: 0,
      tableData: []
    }
  },
  created() {

    let userStr = sessionStorage.getItem("user")

    this.user = JSON.parse(userStr)

    if(!this.user){
      this.$router.push('/login')
    }

    this.load()

  },
  methods: {
    handleSelectionChange(val) {
      this.ids = val.map(v => v.id)   // [{id,name}, {id,name}] => [id,id]
    },
    load() {
      this.loading = true
      request.post("/provider/query/"+this.currentPage+'/'+this.pageSize, {
        proCode: this.searchForm.proCode,
        proName: this.searchForm.proName,
      }).then(res => {
        this.loading = false
        this.tableData = res.data.providerList
        this.total = res.data.total

        this.tableData.forEach(function (row){
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
        request.post("provider/modifyById", this.form).then(res => {
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
        request.post("/provider/add", this.form).then(res => {
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
      request.post("/provider/delProviderById/" + id).then(res => {
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
