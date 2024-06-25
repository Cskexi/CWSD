<template>
    <div>
        <div>
            <input type="text" v-model="msg"/>
            <span>{{ msg }}</span>
        </div>
        <div>
            <textarea v-model="msg2" cols="30" row="10"></textarea>
            <span>msg2：{{ msg2 }}</span>
        </div>
        <div>
            <label for="agreemnet">
                <input id="agreement" type="checkbox" v-model="isAgree">同意协议
            </label>
            <h2>isAgree：{{ isAgree }}</h2>
        </div>
        <div>
            <label for="male">
                <input type="radio" id="male" v-model="gender" value="male">男
            </label>
            <label for="female">
                <input type="radio" id="female" v-model="gender" value="female">女
            </label>
            <h2>gender:{{ gender }}</h2> 
        </div>
        <div>
            <select v-model="fruit">
                <option value="apple">苹果</option>
                <option value="orange">橘子</option>
                <option value="banana">香蕉</option>
                <option v-for="item in sexAry" value="item.value">{{ item.name }}</option>
            </select>
            <h2>fruit：{{ fruit }}</h2>
        </div>
        <div>
            <select v-model="fruits" multiple>
                <option value="apple">苹果</option>
                <option value="orange">橘子</option>
                <option value="banana">香蕉</option>
            </select>
            <h2>fruits：{{ fruits }}</h2>
        </div>
        <el-select v-model="elsv" multiple placeholder="请选择">
            <el-option
            v-for="item in els"
            :key="item.value"
            :label="item.label"
            :value="item.value">
            </el-option>
        </el-select>
        <div>
            <input type="text" v-model.lazy="msg3">
            <h2>{{ msg3 }}</h2>
        </div>
        <div>
            <input type="text" v-model.number="msg4">
            <h2>{{ msg4 }}</h2>
        </div>
        <div>
            <input type="text" v-model.trim="msg5">
            <h2>trim:{{ msg5 }}</h2>
        </div>
        <div>
            {{ message }}
            <span v-bind:title="spanTitle">我是一个span </span>
            <img v-bind:src="imgSrc" v-bind:alt="imgAlt">
            {{ new Date().getTime() }}
        </div>
        <div v-for="item in bookAry">
            {{ item.id }} -> {{ item.name }} ->{{ item.price }}
        </div>
        <div>
            <img src="../assets/logo.png" :title="myMethod()"/>
            <br/>
        </div>
        <div>
            <ul>
                <li v-for="item in items" v-bind:key="item.id">
                {{ item.text }}
                </li>
            </ul>
        </div>
        <div>
            <p>Username: {{ user.name }}</p>
            <p>Email: {{ user.email }}</p>
        </div>
        <ul>
            <li v-for="item in vifs" v-if="item.isVisible">{{ item.name }}</li>
        </ul>
        <ul>
            <li v-for="item in vifs" v-show="item.isVisible">{{ item.name }}</li>
        </ul>
    </div>
</template>

<script>
export default {
  name: 'Chapter2',
  data(){
    return{
        sexAry:[{
            name:'boy',
            value:1
        },{
            name:'girl',
            value:0
            }],
        msg:'hello',
        msg2:'a',
        isAgree:"true",
        gender:"",
        fruit:'',
        fruits:[],
        els: [{
          value: '选项1',
          label: '黄金糕'
        }, {
          value: '选项2',
          label: '双皮奶'
        }, {
          value: '选项3',
          label: '蚵仔煎'
        }, {
          value: '选项4',
          label: '龙须面'
        }, {
          value: '选项5',
          label: '北京烤鸭'
        }],
        elsv: '',
        msg3:'',
        msg4:'',
        msg5:'',
        message:'Hello Vue!',
        spanTitle:'我是span的title',
        imgAlt:'我是一个img',
        imgSrc:'https://pic2.zhimg.com/50/009f838f5a7381b01f74c6f49bee27f4_720w.png?source=54b3c3a5',
        bookAry:[{
            id:1,
            name:"vue",
            price:100
        },
        {
            id:2,
            name:"springboot",
            price:90
        }
        ],
        myMethod(){
            return "hello xmut"
        },
        items: [
            { id: 1, text: 'Item 1' },
            { id: 2, text: 'Item 2' },
            { id: 3, text: 'Item 3' },
          ],
          user: {
            name: 'John Doe',
            email: 'john.doe@example.com',
          },
          vifs:[
            {name:'one',isVisible:1},
            {name:'two',isVisible:0},
            {name:'three',isVisible:1},
          ]
    }
  }
}

</script>