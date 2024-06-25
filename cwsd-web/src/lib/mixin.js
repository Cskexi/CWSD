export default{
    data(){
        return{
            mxName:"mixinName",
            gl_sex:"sex",
            gl_state:"state"
        }
    },
    methods:{
        mxSayHi(){
            console.log("Mixin say hi")
        },
        getDicMap(map,key){
            return map[key]
            // let result =[]
            // map.forEach(item=>{
            //     if(item.key==key){
            //         result = item.value
            //     }
            // })
            // return result
        }         
    }   
}