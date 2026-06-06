<template>
  <el-dialog
    :model-value="visible"
    :title="isEdit ? '编辑科室' : '新增科室'"
    width="500px"
    @close="handleClose"
  >
    <el-form
      ref="formRef"
      :model="form"
      :rules="rules"
      label-width="80px"
    >
      <el-form-item label="名称" prop="name">
        <el-input v-model="form.name" placeholder="请输入科室名称" />
      </el-form-item>
      <el-form-item label="电话" prop="phone">
        <el-input v-model="form.phone" placeholder="请输入联系电话" />
      </el-form-item>
      <el-form-item label="位置" prop="location">
        <el-input v-model="form.location" placeholder="请输入科室位置" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="handleClose">取消</el-button>
      <el-button type="primary" :loading="submitLoading" @click="handleSubmit">确定</el-button>
    </template>
  </el-dialog>
</template>

<script setup>
import { ref, reactive, watch, computed } from 'vue'
import { ElMessage } from 'element-plus'
import { create, update } from '../api/department'

const props = defineProps({
  visible: Boolean,
  data: Object
})

const emit = defineEmits(['update:visible', 'success'])

const formRef = ref(null)
const submitLoading = ref(false)

const isEdit = computed(() => props.data && props.data.id)

const form = reactive({
  id: null,
  name: '',
  phone: '',
  location: ''
})

const rules = {
  name: [
    { required: true, message: '请输入科室名称', trigger: 'blur' }
  ]
}

watch(() => props.visible, (val) => {
  if (val) {
    if (props.data && props.data.id) {
      Object.assign(form, props.data)
    } else {
      form.id = null
      form.name = ''
      form.phone = ''
      form.location = ''
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
</script>
