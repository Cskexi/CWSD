import{get,post,postJson} from "@/api/axios"

export function myAxios2Get(params){
    return get('/test/get',params)
}

export function myAxios2Post(params){
    return post('/test/post',params)
}

export function myAxios2PostJson(params){
    return postJson('/test/postJson',params)
}