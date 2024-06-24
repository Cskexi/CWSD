import { postJson, post } from "@/api/axios"

export function save(params) {
    return postJson('Notice/save', params)
}

export function list(params) {
    return post('Notice/list', params)
}

export function noticePage(params) {
    return post('Notice/page', params)
}

export function deleteByIds(params) {
    return post('Equipment/deleteByIds', params)
}

export function dataChange(params) {
    return post('Equipment/dataChange', params)
}
