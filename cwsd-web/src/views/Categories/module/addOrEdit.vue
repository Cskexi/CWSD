<template>
    <el-dialog :title="title" :visible="visible" :before-close="close">
        <el-form :model="form" ref="form">
            <el-form-item label="分类名称" prop="name">
                <el-input v-model="form.name" autocomplete="off"></el-input>
            </el-form-item>

            <el-select v-model="form.parentCategoryId" placeholder="请选择">
                <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                >
                </el-option>
            </el-select>
        </el-form>

        <div slot="footer" class="dialog-footer">
            <el-button @click="close">取 消</el-button>
            <el-button type="primary" @click="submit">确 定</el-button>
        </div>
    </el-dialog>
</template>    

<script>
//import { storeAddOrUpdate } from '@/api/modules/store'
import { categoriesAddOrUpdate } from '@/api/modules/categories'
import { getStore } from '@/lib/storage'
export default {
    name: 'addOrEdit',
    props: {
        visible: {
            type: Boolean,
            default: false
        },
        title: {
            type: String,
            default: '新增'
        },
        defaultFormDate: {
            type: Array
        }
    },
    data() {
        return {
            flag: false,
            form: {
                name: ''
                //address: '',
                //telephone: '',
                //userId: ''
            },
            userId: '',
            options: [],
            value: ''
        }
    },
    mounted() {
        //console.log(this.defaultFormDate)
        if (JSON.stringify(this.defaultFormDate) == '{}') {
            this.flag = false
        } else {
            // let s = this.defaultFormDate
            let s = JSON.parse(JSON.stringify(this.defaultFormDate))
            for (let i = 0; i < s.length; i++) {
                // 为每个元素创建一个option对象
                let option = {
                    value: s[i].id, // 设置option的value属性
                    label: s[i].name // 设置option的label属性
                }
                // 将option对象添加到options数组中
                this.options.push(option)
            }
        }
        // this.userId = getStore('userId')
        // this.form.userId = this.userId
    },
    methods: {
        close() {
            this.flag = false
            this.$emit('close', this.flag)
        },
        submit() {
            this.$refs.form.validate((valid) => {
                if (valid) {
                    if (this.form.userId == null) {
                        console.log('当前为登录')
                    }
                    categoriesAddOrUpdate({
                        ...this.form
                    })
                        .then((res) => {
                            this.flag = true
                            this.$emit('close', this.flag)
                        })
                        .catch((err) => {
                            this.flag = false
                            this.$emit('close', this.flag)
                        })
                }
            })
        }
    }
}
</script>