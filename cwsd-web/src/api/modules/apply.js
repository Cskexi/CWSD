import{postJson,post} from "@/api/axios"

export function applyAddOrUpdate(params){
    return postJson('Apply/save',params)
}

export function applyMyPage(params){
    return post('Apply/myPage',params)
}
