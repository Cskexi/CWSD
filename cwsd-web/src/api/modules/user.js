import { postJson, post } from "@/api/axios"


export function userAddOrUpdate(params) {
    return postJson('user/save', params)
}

export function login(params) {
    return postJson('user/login', params)
}

export function register(params) {
    return postJson('user/register', params)
}

export function getByToken(params) {
    return post('user/getByToken', params)
}

export function userList(params) {
    return post('user/list', params)
}

export function userPage(params) {
    return post('user/page', params)
}

export function userDeleteByIds(params) {
    return post('user/deleteByIds', params)
}
export function userStateChangeById(params) {
    return post('user/stateChangeById', params)
}

export function userTypeChangeById(params) {
    return post('user/typeChangeById', params)
}

