<template>
  <div class="page-container">
    <div class="page-header">
      <span class="title">医生管理</span>
      <el-button type="primary" :icon="Plus" @click="handleAdd">新增医生</el-button>
    </div>

    <div class="search-bar">
      <el-input
        v-model="queryParams.name"
        placeholder="请输入姓名"
        clearable
        style="width: 200px"
        @keyup.enter="handleSearch"
      />
      <el-select
        v-model="queryParams.departmentId"
        placeholder="选择科室"
        clearable
        style="width: 200px"
      >
        <el-option
          v-for="item in departmentOptions"
          :key="item.id"
          :label="item.name"
          :value="item.id"
        />
      </el-select>
      <el-button type="primary" :icon="Search" @click="handleSearch">搜索</el-button>
      <el-button :icon="Refresh" @click="handleReset">重置</el-button>
    </div>

    <el-table
      :data="tableData"
      border
      stripe
      v-loading="loading"
      style="width: 100%"
    >
      <el-table-column prop="jobNumber" label="工号" width="120" align="center" />
      <el-table-column prop="name" label="姓名" min-width="100" />
      <el-table-column prop="gender" label="性别" width="80" align="center" />
      <el-table-column prop="title" label="职称" min-width="120" />
      <el-table-column prop="departmentName" label="所属科室" min-width="120" />
      <el-table-column prop="phone" label="手机号" min-width="130" />
      <el-table-column label="操作" width="180" align="center" fixed="right">
        <template #default="{ row }">
          <el-button type="primary" link :icon="Edit" @click="handleEdit(row)">编辑</el-button>
          <el-button type="danger" link :icon="Delete" @click="handleDelete(row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <div class="pagination-wrapper">
      <el-pagination
        v-model:current-page="queryParams.page"
        v-model:page-size="queryParams.pageSize"
        :page-sizes="[10, 20, 50, 100]"
        :total="total"
        layout="total, sizes, prev, pager, next, jumper"
        @size-change="fetchData"
        @current-change="fetchData"
      />
    </div>

    <DoctorForm
      v-model:visible="formVisible"
      :data="currentRow"
      @success="fetchData"
    />
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Plus, Edit, Delete, Search, Refresh } from '@element-plus/icons-vue'
import { getPage, remove } from '../api/doctor'
import { getList as getDepartments } from '../api/department'
import DoctorForm from '../components/DoctorForm.vue'

const loading = ref(false)
const tableData = ref([])
const total = ref(0)
const formVisible = ref(false)
const currentRow = ref(null)
const departmentOptions = ref([])

const queryParams = reactive({
  page: 1,
  pageSize: 10,
  name: '',
  departmentId: ''
})

const fetchDepartments = async () => {
  try {
    const res = await getDepartments()
    departmentOptions.value = res.data || []
  } catch (error) {
    // 错误已在拦截器中处理
  }
}

const fetchData = async () => {
  loading.value = true
  try {
    const params = { ...queryParams }
    if (!params.name) delete params.name
    if (!params.departmentId) delete params.departmentId
    const res = await getPage(params)
    tableData.value = res.data ? res.data.records || res.data.list || [] : []
    total.value = res.data ? res.data.total || 0 : 0
  } catch (error) {
    // 错误已在拦截器中处理
  } finally {
    loading.value = false
  }
}

const handleSearch = () => {
  queryParams.page = 1
  fetchData()
}

const handleReset = () => {
  queryParams.name = ''
  queryParams.departmentId = ''
  queryParams.page = 1
  fetchData()
}

const handleAdd = () => {
  currentRow.value = null
  formVisible.value = true
}

const handleEdit = (row) => {
  currentRow.value = { ...row }
  formVisible.value = true
}

const handleDelete = (row) => {
  ElMessageBox.confirm(`确定要删除医生"${row.name}"吗？`, '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    try {
      await remove(row.id)
      ElMessage.success('删除成功')
      fetchData()
    } catch (error) {
      // 错误已在拦截器中处理
    }
  }).catch(() => {})
}

onMounted(() => {
  fetchDepartments()
  fetchData()
})
</script>
