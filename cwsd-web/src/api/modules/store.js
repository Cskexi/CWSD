import{postJson,post} from "@/api/axios"
export function storePage(params){
    return post('Store/page',params)
}

export function storeDeleteByIds(params){
    return post('Store/deleteByIds',params)
}
export function storeGetMessage(params){
    return post('Store/getMessage',params)
}
export function storeAddOrUpdate(params){
    return postJson('Store/save',params)
}


export function storeGetStoreByUserId(params){
    return post('Store/getStoreByUserId',params)
}