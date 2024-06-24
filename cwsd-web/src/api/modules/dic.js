import{get,post,postJson} from "@/api/axios"

export function dicAddOrUpdate(params){
    return postJson('Dic/addOrUpdate',params)
}

export function dicList(params){
    return post('Dic/list',params)
}

export function dicPage(params){
    return post('Dic/page',params)
}

export function dicDeleteByIds(params){
    return post('Dic/deleteByIds',params)
}
