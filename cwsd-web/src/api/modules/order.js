import{get,post,postJson} from "@/api/axios"

export function orderAddOrUpdate(params){
    return postJson('order/save',params)
}

export function orderList(params){
    return post('order/list',params)
}

export function orderList2(params){
    return post('order/list2',params)
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
