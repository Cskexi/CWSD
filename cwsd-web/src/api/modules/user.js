import{postJson,post} from "@/api/axios"


export function userAddOrUpdate(params){
    return postJson('chapter3/user/save',params)
}

export function login(params){
    return postJson('chapter3/user/login',params)
}

export function register(params){
    return postJson('chapter3/user/register',params)
}

export function getByToken(params){
    return post('chapter3/user/getByToken',params)
}

export function userList(params){
    return post('chapter3/user/list',params)
}

export function userPage(params){
    return post('chapter3/user/page',params)
}

export function userDeleteByIds(params){
    return post('chapter3/user/deleteByIds',params)
}
export function userStateChangeById(params){
    return post('chapter3/user/stateChangeById',params)
}

export function userTypeChangeById(params){
    return post('chapter3/user/typeChangeById',params)
}

