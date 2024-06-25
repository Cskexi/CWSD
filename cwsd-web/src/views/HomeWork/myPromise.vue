<template>
    <div class="myPromise">
        <h1>myPromise</h1>
        <!-- <button @click="onMain">Main</button>
        
        <button @click="onMainPromise">MainPromise</button> -->
        <div id="msg">{{ msg }}</div>
        <button @click="modify">修改</button>
        <input type="text" id="myText" value="xmut">
    </div>
  </template>
  
  <script>

  export default {

    name: 'myPromise',
    data(){
        return{
            msg:"success",
        }
    },
    // beforeCreate(){
    //     console.log('msg is :'+this.msg)
    //     this.show()
    // },
    destroyed(){
        console.log('destroyed')
        // clearInterval(this.timer)
         console.log(this.timer)
    },
    beforeDestroy(){
        console.log('beforeDestroy')
         console.log(this.timer)
    },
    beforeUpdate(){
        console.log('beforeUpdate')
        let msgElement = document.getElementById("msg");
            console.log(msgElement.innerText);
            console.log('msg is :'+this.msg)
        this.show()
    },
    updated(){
        console.log('updated')
        let msgElement = document.getElementById("msg");
            console.log(msgElement.innerText);
            console.log('msg is :'+this.msg)
        this.show()
    },
    // created(){
    //     console.log('msg is :'+this.msg)
    //     this.show()
    //     // let value = document.getElementById("myText").val();
    //     // console.log(value)
    // },
    // beforeMount(){
    //     console.log('beforeMount')
    //         let msgElement = document.getElementById("myText");
    //         console.log(msgElement);
    // },
    mounted(){
        console.log(' mounted')
            let msgElement = document.getElementById("myText").value;
            console.log(msgElement);
    },

    methods:{
        show(){
            console.log(this.msg)
        },
        modify(){
        this.timer = setInterval(()=>{
            this.msg = Date.now();
            console.log(this.msg);
        },100);
         },
        // onMain(){
        //     //定义一个B函数，调用A函数，同时把B函数传给A做回调
        //     const B = function(){
        //         console.log("B function")
        //     }
        //     setTimeout(()=>{
        //         this.A(B)
        //     },2000)
        // },
        // A(callback){
            
        //     console.log("A function")
        //     //做回调
        //     callback();
        // }
        onMain(){
            //定义一个B函数，调用A函数，同时把B函数传给A做回调
            const B = function(callback){
                console.log("B function")
                callback()
            }
            setTimeout(()=>{
                this.A(B)
            },2000)
        },
        A(callback){
            const C = function(callback){
                console.log("C function")
            }
            console.log("A function")
            //做回调
            callback(C);
        },
        onMainPromise(){
            this.AP()
            //B
            .then(res =>{
                console.log("AP->then")
                console.log(res)
                return this.BP()
            })
            // .catch(rej =>{
            //     console.log("AP->catch")
            // })
            //C
            .then(res =>{
                console.log("BP->then")
                this.CP()
            })
            .catch(rej =>{
                console.log("BP->catch")
            })
        },
        AP(){
            console.log("AP function")
            //返回Promise的状态
            //resolve => then 分支
            //reject => catch 分支
            return Promise.resolve("AP resolve")
        },
        BP(){
            console.log("BP function")
            return Promise.resolve("BP resolve")
        },
        CP(){
            console.log("CP function")
        }

    },

  }
  </script>
  