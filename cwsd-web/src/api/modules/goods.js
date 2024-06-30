import{get,post,postJson} from "@/api/axios"

export function goodsAddOrUpdate(params){
    return postJson('Goods/save',params)
}

export function goodsList(params){
    return post('chapter4/goods/list',params)
}

export function goodsPage(params){
    return post('Goods/page',params)
}

export function goodsPage2(params){
    return post('Goods/page2',params)
}

export function goodsGetMessage(params){
    return post('Goods/getMessage',params)
}

export function goodsDeleteByIds(params){
    return post('Goods/deleteByIds',params)
}
