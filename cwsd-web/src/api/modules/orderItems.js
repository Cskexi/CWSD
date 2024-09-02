import { get, post, postJson } from "@/api/axios"

export function orderItemsAddOrUpdate(params) {
    return postJson('orderItems/save', params)
}

export function productsList(params) {
    return post('chapter4/products/list', params)
}

export function orderItemsPage(params) {
    return post('orderItems/page', params)
}

export function productsPage2(params) {
    return post('products/page2', params)
}

export function productsGetMessage(params) {
    return post('products/getMessage', params)
}

export function productsDeleteByIds(params) {
    return post('products/deleteByIds', params)
}
