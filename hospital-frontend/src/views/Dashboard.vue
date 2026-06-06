<template>
  <div class="dashboard-container">
    <div class="welcome-section">
      <h2>欢迎使用医院员工信息管理系统</h2>
      <p>在这里可以管理科室、医生和护士信息</p>
    </div>

    <el-row :gutter="20" class="stats-row">
      <el-col :span="8">
        <el-card shadow="hover" class="stat-card">
          <div class="stat-content">
            <div class="stat-info">
              <div class="stat-label">科室总数</div>
              <div class="stat-value">{{ stats.departments }}</div>
            </div>
            <el-icon :size="48" color="#409EFF"><OfficeBuilding /></el-icon>
          </div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card shadow="hover" class="stat-card">
          <div class="stat-content">
            <div class="stat-info">
              <div class="stat-label">医生总数</div>
              <div class="stat-value">{{ stats.doctors }}</div>
            </div>
            <el-icon :size="48" color="#67C23A"><User /></el-icon>
          </div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card shadow="hover" class="stat-card">
          <div class="stat-content">
            <div class="stat-info">
              <div class="stat-label">护士总数</div>
              <div class="stat-value">{{ stats.nurses }}</div>
            </div>
            <el-icon :size="48" color="#E6A23C"><Avatar /></el-icon>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getList as getDepartments } from '../api/department'
import { getPage as getDoctors } from '../api/doctor'
import { getPage as getNurses } from '../api/nurse'

const stats = ref({
  departments: 0,
  doctors: 0,
  nurses: 0
})

const fetchStats = async () => {
  try {
    const [deptRes, doctorRes, nurseRes] = await Promise.all([
      getDepartments(),
      getDoctors({ page: 1, pageSize: 1 }),
      getNurses({ page: 1, pageSize: 1 })
    ])
    stats.value.departments = deptRes.data ? deptRes.data.length : 0
    stats.value.doctors = doctorRes.data ? doctorRes.data.total || 0 : 0
    stats.value.nurses = nurseRes.data ? nurseRes.data.total || 0 : 0
  } catch (error) {
    // 错误已在拦截器中处理
  }
}

onMounted(() => {
  fetchStats()
})
</script>

<style scoped>
.dashboard-container {
  padding: 10px;
}

.welcome-section {
  margin-bottom: 30px;
}

.welcome-section h2 {
  font-size: 24px;
  color: #303133;
  margin-bottom: 8px;
}

.welcome-section p {
  font-size: 14px;
  color: #909399;
}

.stats-row {
  margin-top: 20px;
}

.stat-card {
  cursor: default;
}

.stat-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.stat-info .stat-label {
  font-size: 14px;
  color: #909399;
  margin-bottom: 8px;
}

.stat-info .stat-value {
  font-size: 32px;
  font-weight: 700;
  color: #303133;
}
</style>
