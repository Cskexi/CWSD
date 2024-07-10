import { postt, get } from "@/api/axios"


export function upload(params) {
    return postt('upload', params)
}

export function deleteFile(params) {
    return get('deleteFile', params)
}

