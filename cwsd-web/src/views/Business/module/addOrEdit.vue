<template>
    <el-dialog :title="title" :visible="visible" :before-close="close">
        <el-form :model="form" ref="form">
            <el-form-item label="商品名称" prop="name">
                <el-input v-model="form.name" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="价格" prop="price">
                <el-input v-model="form.price" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="库存" prop="inventory">
                <el-input
                    v-model="form.inventory"
                    autocomplete="off"
                ></el-input>
            </el-form-item>
        </el-form>

        <div slot="footer" class="dialog-footer">
            <el-button @click="close">取 消</el-button>
            <el-button type="primary" @click="submit">确 定</el-button>
        </div>
    </el-dialog>
</template>    

<script>
import { productsAddOrUpdate } from '@/api/modules/products'
import { setStore, removeStore, getStore } from '@/lib/storage'
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
            type: Object
        }
    },
    data() {
        return {
            flag: false,
            form: {
                name: '',
                price: '',
                inventory: '',
                storeId: ''
            },
            userId: ''
        }
    },
    mounted() {
        if (JSON.stringify(this.defaultFormDate) == '{}') {
            this.flag = false
        } else {
            this.form = JSON.parse(JSON.stringify(this.defaultFormDate))
            //console.log(this.form)
            this.flag = true
        }
        this.userId = getStore('userId')
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
                    productsAddOrUpdate({
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