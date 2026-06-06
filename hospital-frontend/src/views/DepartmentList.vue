<template>
  <div class="page-container">
    <div class="page-header">
      <span class="title">科室管理</span>
      <el-button type="primary" :icon="Plus" @click="handleAdd">新增科室</el-button>
    </div>

    <el-table
      :data="tableData"
      border
      stripe
      v-loading="loading"
      style="width: 100%"
    >
      <el-table-column prop="id" label="ID" width="80" align="center" />
      <el-table-column prop="name" label="科室名称" min-width="150" />
      <el-table-column prop="phone" label="联系电话" min-width="140" />
      <el-table-column prop="location" label="位置" min-width="140" />
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

    <DepartmentForm
      v-model:visible="formVisible"
      :data="currentRow"
      @success="fetchData"
    />
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Plus, Edit, Delete } from '@element-plus/icons-vue'
import { getList, remove } from '../api/department'
import DepartmentForm from '../components/DepartmentForm.vue'

const loading = ref(false)
const tableData = ref([])
const total = ref(0)
const formVisible = ref(false)
const currentRow = ref(null)

const queryParams = reactive({
  page: 1,
  pageSize: 10
})

const fetchData = async () => {
  loading.value = true
  try {
    const res = await getList()
    const list = res.data || []
    total.value = list.length
    const start = (queryParams.page - 1) * queryParams.pageSize
    const end = start + queryParams.pageSize
    tableData.value = list.slice(start, end)
  } catch (error) {
    // 错误已在拦截器中处理
  } finally {
    loading.value = false
  }
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
  ElMessageBox.confirm(`确定要删除科室"${row.name}"吗？`, '提示', {
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
  fetchData()
})
</script>
