import { get, post, postJson } from "@/api/axios"

export function videosAddOrUpdate(params) {
    return postJson('videos/save', params)
}

export function videosList(params) {
    return get('videos/list', params)
}

export function videosPage(params) {
    return post('videos/page', params)
}

export function videosDeleteByIds(params) {
    return post('videos/deleteByIds', params)
}
