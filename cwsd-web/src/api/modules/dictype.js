import{get,post,postJson} from "@/api/axios"

export function dictypeGetAllMap(params){
    return get('chapter4/dictype/getAllMap',params)
}

export function dictypeAddOrUpdate(params){
    return postJson('chapter4/dictype/addOrUpdate',params)
}

export function dictypeList(params){
    return post('chapter4/dictype/list',params)
}

export function dictypePage(params){
    return post('chapter4/dictype/page',params)
}
export function dictypePageByUsername(params){
    return post('chapter4/dictype/pageByUsername',params)
}

export function dictypeDeleteByIds(params){
    return post('chapter4/dictype/deleteByIds',params)
}
