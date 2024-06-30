import{get,post,postJson} from "@/api/axios"

export function orderItemAddOrUpdate(params){
    return postJson('orderItem/save',params)
}

export function shopcartList(params){
    return post('Shopcart/list',params)
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

export function shopcartDeleteByIds(params){
    return post('Shopcart/deleteByIds',params)
}
