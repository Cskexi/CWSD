import { postt } from "@/api/axios"


export function upload(params) {
    return postt('upload', params)
}

export function upload2(params) {
    return postt('upload2', params)
}

