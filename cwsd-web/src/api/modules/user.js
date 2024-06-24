import{postJson,post} from "@/api/axios"

export function update(params){
    return postJson('User/update',params)
}


export function userAdd(params){
    return post('User/add',params)
}

export function userAddOrUpdate(params){
    return postJson('User/save',params)
}

export function login(params){
    return postJson('User/login',params)
}

export function register(params){
    return postJson('User/register',params)
}

export function getByToken(params){
    return post('User/getByToken',params)
}

export function userList(params){
    return post('User/list',params)
}

export function userPage(params){
    return post('User/page',params)
}

export function userDeleteByIds(params){
    return post('User/deleteByIds',params)
}
export function userStateChangeById(params){
    return post('User/stateChangeById',params)
}

export function userTypeChangeById(params){
    return post('User/typeChangeById',params)
}

