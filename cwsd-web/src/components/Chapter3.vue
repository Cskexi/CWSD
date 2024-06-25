<template>
    <div>
        <el-button :plain="true" @click="open">打开消息提示</el-button>
        <div>
            <button @click="onSumClick(1,2)" >计算和</button>
        </div>
        <div>
            <input type="text" 
            @keydown.enter="showInfo" 
            @keydown.9="showInfo"
            @keydown.37="showInfo"
            @keydown.38="showInfo"
            @keydown.39="showInfo"
            @keydown.40="showInfo">
            <h2>{{ keymsg }}</h2>
        </div>
        <div>
            <p>v-on修饰符</p>
            <div style="background-color: #008000;" v-on:click="open('div')">
                <button v-on:click="open('A')">A</button>
                <button v-on:click.stop="open('B')">B</button>
            </div>
            <div>
                <a href="http://www.baidu.com/" v-on:click.prevent>A</a>
                <br/>
                <a href="http://www.baidu.com/" v-on:click.prevent="open('B')">B</a>
            </div>
            <div v-on:click.capture="open('div1')">
                <h3>capture:</h3><br>
                <button v-on:click="open('A')">A</button>
                <div v-on:click="open('div2')">
                    <button v-on:click="open('B')">B</button>
                </div>
            </div>
            <div @click="open(1)" style="background-color: #008000;">
                <p>self</p>
                第一层
                <div @click.self="open(2)"  style="background-color: red;">
                    第二层
                    <div @click="open(3)" style="background-color: blue;">
                        第三层
                        <div @click="open(4)" style="background-color: yellow;">
                            第四层
                        </div>
                    </div>
                </div>
            </div>
            <div>
                <p>once</p>
                <button v-on:click.once="open(1)">出现1</button>
            </div>
        </div>
        <h2>过滤器</h2>
        <div>
            <input type="text" v-model="str">
            str:{{ str |strTrim| strReverse}}
        </div>
        <p>watch</p>
        <div>
            sl is:{{ sl }}<br>
            nm is:{{ nm }}<br>
            <button @click="change('op')">点击修改为op</button>
        </div>
        <p>Computed</p>
        <div>
            <input type="text" v-model="price">
            <br>
            <input type="text" v-model="number">
            <br>
            {{ total }}
        </div>
    </div>
</template>

<script>
export default {
  name: 'Chapter3',
  data(){
    return{
        count:0,
        keymsg:0,
        str:'',
        hzt:'op-name',
        sl:'suolong',
        nm:'nami',
        price:1,
        number:1,
        
    }
  },
  computed:{
    total:function(){
        return this.price*this.number;
    }
  },
  mounted(){
    this.init();
  },
  watch:{
    hzt:function(){
        this.init();
    }
  },
  methods:{
    change(value){
        this.hzt=value;
    },
    init(){
        this.sl=this.hzt+'=>'+'suolong';
        this.nm=this.hzt+'=>'+'nami';
        console.log(this.sl);
        console.log(this.nm)
    },
    open(val){
        this.$message({
            showClose:true,
            message:val,
            type:'success'
        });
    },
    onSumClick(val1,val2){
        this.$message({
            showClose:true,
            message:val1+val2,
            type:'success'
        });
    },
    showInfo(event){
        this.keymsg=event.key;
    }
  },
  filters:{
    strTrim:function(value){
        if(!value) return '';
        value=value.toString();
        return value.trim();
    },
    strReverse:function(value){
        if(!value) return '';
        value=value.toString();
        value=[...value].reverse().join("");
        return value;
    }
    
  }
}
</script>