import ajax from './ajax'

// 定义BASE_URL
// const BASE_URL = 'http://local:8081'
const BASE_URL = '/api'


export const registerUser = (data) => ajax(BASE_URL+'/registerUser',data, 'POST')

