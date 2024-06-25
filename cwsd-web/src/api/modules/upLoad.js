import{postt} from "@/api/axios"


export function upload(params){
    return postt('upload',params)
}