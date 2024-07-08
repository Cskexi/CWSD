import { get, post, postJson } from "@/api/axios"

export function categoriesAddOrUpdate(params) {
    return postJson('categories/save', params)
}

export function categoriesList(params) {
    return get('categories/list', params)
}

export function categoriesPage(params) {
    return post('categories/page', params)
}

export function categoriesDeleteByIds(params) {
    return post('categories/deleteByIds', params)
}
