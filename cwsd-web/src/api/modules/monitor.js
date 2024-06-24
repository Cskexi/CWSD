import { postJson, post } from "@/api/axios"

export function save(params) {
    return postJson('Monitor/save', params)
}

export function monitorList(params) {
    return post('Monitor/list', params)
}

export function deleteByIds(params) {
    return post('Monitor/deleteByIds', params)
}