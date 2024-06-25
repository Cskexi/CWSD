import{get,post,postJson} from "@/api/axios"

export function bookAddOrUpdate(params){
    return postJson('chapter2/book/addOrUpdate',params)
}

export function bookList(params){
    return post('chapter2/book/list',params)
}

export function bookPage(params){
    return post('chapter2/book/page',params)
}

export function bookDeleteByIds(params){
    return post('chapter2/book/deleteByIds',params)
}
