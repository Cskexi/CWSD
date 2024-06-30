import{get,post,postJson} from "@/api/axios"

export function dicAddOrUpdate(params){
    return postJson('chapter4/dic/addOrUpdate',params)
}

export function dicList(params){
    return post('chapter4/dic/list',params)
}

export function dicPage(params){
    return post('chapter4/dic/page',params)
}

export function dicDeleteByIds(params){
    return post('chapter4/dic/deleteByIds',params)
}
