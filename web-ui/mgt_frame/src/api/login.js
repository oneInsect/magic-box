import request from '@/utils/request'

export function login(userName, userPassword) {
  return request({
    url: '/service/users/login',
    method: 'post',
    data: {
      userName,
      userPassword
    }
  })
}

export function getInfo(token) {
  return request({
    url: '/service/users/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/user/logout',
    method: 'post'
  })
}
