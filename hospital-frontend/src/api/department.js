import request from './request'

export function getList() {
  return request({
    url: '/api/departments',
    method: 'get'
  })
}

export function getById(id) {
  return request({
    url: `/api/departments/${id}`,
    method: 'get'
  })
}

export function create(data) {
  return request({
    url: '/api/departments',
    method: 'post',
    data
  })
}

export function update(data) {
  return request({
    url: '/api/departments',
    method: 'put',
    data
  })
}

export function remove(id) {
  return request({
    url: `/api/departments/${id}`,
    method: 'delete'
  })
}
