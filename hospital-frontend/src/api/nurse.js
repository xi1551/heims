import request from './request'

export function getPage(params) {
  return request({
    url: '/api/nurses',
    method: 'get',
    params
  })
}

export function getById(id) {
  return request({
    url: `/api/nurses/${id}`,
    method: 'get'
  })
}

export function create(data) {
  return request({
    url: '/api/nurses',
    method: 'post',
    data
  })
}

export function update(data) {
  return request({
    url: '/api/nurses',
    method: 'put',
    data
  })
}

export function remove(id) {
  return request({
    url: `/api/nurses/${id}`,
    method: 'delete'
  })
}
