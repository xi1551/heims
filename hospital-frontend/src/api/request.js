import axios from 'axios'
import { ElMessage } from 'element-plus'
import router from '../router'

const request = axios.create({
  baseURL: '',
  timeout: 15000
})

// 请求拦截器：添加 Authorization Bearer token
request.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('token')
    if (token) {
      config.headers.Authorization = `Bearer ${token}`
    }
    return config
  },
  (error) => {
    return Promise.reject(error)
  }
)

// 响应拦截器：处理401跳转登录
request.interceptors.response.use(
  (response) => {
    return response.data
  },
  (error) => {
    if (error.response && error.response.status === 401) {
      localStorage.removeItem('token')
      router.push('/login')
      ElMessage.error('登录已过期，请重新登录')
    } else if (error.response && error.response.data) {
      ElMessage.error(error.response.data.message || '请求失败')
    } else {
      ElMessage.error('网络异常，请稍后重试')
    }
    return Promise.reject(error)
  }
)

export default request
