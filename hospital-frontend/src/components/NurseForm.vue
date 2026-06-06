<template>
  <el-dialog
    :model-value="visible"
    :title="isEdit ? '编辑护士' : '新增护士'"
    width="550px"
    @close="handleClose"
  >
    <el-form
      ref="formRef"
      :model="form"
      :rules="rules"
      label-width="80px"
    >
      <el-form-item label="工号" prop="jobNumber">
        <el-input v-model="form.jobNumber" placeholder="请输入工号" />
      </el-form-item>
      <el-form-item label="姓名" prop="name">
        <el-input v-model="form.name" placeholder="请输入姓名" />
      </el-form-item>
      <el-form-item label="性别" prop="gender">
        <el-radio-group v-model="form.gender">
          <el-radio value="男">男</el-radio>
          <el-radio value="女">女</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="职称" prop="title">
        <el-select v-model="form.title" placeholder="请选择职称" style="width: 100%">
          <el-option label="主任护师" value="主任护师" />
          <el-option label="副主任护师" value="副主任护师" />
          <el-option label="主管护师" value="主管护师" />
          <el-option label="护师" value="护师" />
          <el-option label="护士" value="护士" />
        </el-select>
      </el-form-item>
      <el-form-item label="所属科室" prop="departmentId">
        <el-select v-model="form.departmentId" placeholder="请选择科室" style="width: 100%">
          <el-option
            v-for="item in departmentOptions"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="手机号" prop="phone">
        <el-input v-model="form.phone" placeholder="请输入手机号" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="handleClose">取消</el-button>
      <el-button type="primary" :loading="submitLoading" @click="handleSubmit">确定</el-button>
    </template>
  </el-dialog>
</template>

<script setup>
import { ref, reactive, watch, computed, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import { create, update } from '../api/nurse'
import { getList as getDepartments } from '../api/department'

const props = defineProps({
  visible: Boolean,
  data: Object
})

const emit = defineEmits(['update:visible', 'success'])

const formRef = ref(null)
const submitLoading = ref(false)
const departmentOptions = ref([])

const isEdit = computed(() => props.data && props.data.id)

const form = reactive({
  id: null,
  jobNumber: '',
  name: '',
  gender: '女',
  title: '',
  departmentId: '',
  phone: ''
})

const rules = {
  jobNumber: [
    { required: true, message: '请输入工号', trigger: 'blur' }
  ],
  name: [
    { required: true, message: '请输入姓名', trigger: 'blur' }
  ]
}

const fetchDepartments = async () => {
  try {
    const res = await getDepartments()
    departmentOptions.value = res.data || []
  } catch (error) {
    // 错误已在拦截器中处理
  }
}

watch(() => props.visible, (val) => {
  if (val) {
    if (props.data && props.data.id) {
      Object.assign(form, props.data)
    } else {
      form.id = null
      form.jobNumber = ''
      form.name = ''
      form.gender = '女'
      form.title = ''
      form.departmentId = ''
      form.phone = ''
    }
  }
})

const handleClose = () => {
  formRef.value?.resetFields()
  emit('update:visible', false)
}

const handleSubmit = () => {
  formRef.value.validate(async (valid) => {
    if (!valid) return
    submitLoading.value = true
    try {
      if (isEdit.value) {
        await update(form)
        ElMessage.success('更新成功')
      } else {
        await create(form)
        ElMessage.success('新增成功')
      }
      emit('success')
      handleClose()
    } catch (error) {
      // 错误已在拦截器中处理
    } finally {
      submitLoading.value = false
    }
  })
}

onMounted(() => {
  fetchDepartments()
})
</script>
