<template>
    <el-dialog :title="title" :visible="visible" :before-close="close">
        <el-form :model="form" ref="form" :rules="rules">
            <el-form-item label="商品名称" prop="name">
                <el-input v-model="form.name" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="价格" prop="price">
                <el-input v-model="form.price" autocomplete="off"></el-input>
            </el-form-item>

            <el-form-item label="描述" prop="description">
                <el-input v-model="form.description" autocomplete="off"></el-input>
            </el-form-item>

            <el-form-item label="折扣" prop="discount">
                <el-input v-model="form.discount" autocomplete="off"></el-input>
            </el-form-item>

            <el-select v-model="form.categoryId" placeholder="请选择">
                <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                </el-option>
            </el-select>

            <el-form-item label="库存" prop="inventory" v-if="form.categoryId === '2168c742bb0d69bf4b696f46eb8461e8'">
                <el-input v-model="form.inventory" autocomplete="off"></el-input>
            </el-form-item>
            <!-- 上传 -->
            <div v-if="title !== '新增'">
                <el-upload ref="uploadRef" action="#" :file-list="filesList" :http-request="uploadSumit" :auto-upload="false"
                list-type="picture" :limit="2" :show-file-list="false" :on-change="handleChange">
                <el-button type="primary">图片选择</el-button>
            </el-upload>

            <el-button type="success" @click="uploadClick">图片上传</el-button>

            <div class="image-list">
                <el-image v-for="(file, index) of filesList" :key="index" :src="file.url" fit="cover"></el-image>
                <i class="iconfont icon-delete" v-for="(file, index) of filesList" :key="index" @click="remove(index)"></i>
            </div>
            </div>
            

            <!-- 到这里 -->
        </el-form>

        <div slot="footer" class="dialog-footer">
            <el-button @click="close">取 消</el-button>
            <el-button type="primary" @click="submit">确 定</el-button>
        </div>
    </el-dialog>
</template>    

<script>
import { categoriesList } from '@/api/modules/categories'
import { productsAddOrUpdate } from '@/api/modules/products'
import { setStore, removeStore, getStore } from '@/lib/storage'
import { upload } from '@/api/modules/upLoad'
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
        },
        defaultFormDate2: {
            type: Array
        }
    },
    data() {
        return {
            filesList: [], // 文件列表
            filesList2: [], // 文件列表
            imageUrl: '',
            flag: false,
            form: {
                name: '',
                price: '',
                inventory: '',
                storeId: '',
                pic: '',
                description: '',
            },
            userId: '',
            options: [],
            rules: {
                name: [
                    { required: true, message: '请输入商品名称', trigger: 'blur' },
                    { min: 2, max: 100, message: '长度在 2 到 100 个字符', trigger: 'blur' }
                ],
                price: [
                    { required: true, message: '请输入商品价格', trigger: 'blur' },
                    { validator: this.validatePrice, trigger: 'blur' }
                ]
            }
        }
    },
    mounted() {
        //新增
        if (JSON.stringify(this.defaultFormDate) == '{}') {
            this.flag = false
        } else {
            //编辑
            this.form = JSON.parse(JSON.stringify(this.defaultFormDate))
            console.log(this.form)
            this.flag = true
        }
        this.userId = getStore('userId')

        if (JSON.stringify(this.defaultFormDate2) == '{}') {
            this.flag = false
        } else {
            // let s = this.defaultFormDate
            let s = JSON.parse(JSON.stringify(this.defaultFormDate2))
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
    },
    methods: {
        validatePrice(rule, value, callback) {
            if (!value) {
                return callback(new Error('请输入商品价格'));
            }
            if (isNaN(value)) {
                return callback(new Error('价格必须是数字'));
            }
            if (value <= 0) {
                return callback(new Error('价格必须大于零'));
            }
            callback();
        },
        //变化
        handleChange(uploadFile, uploadFiles) {
            if (uploadFiles.length > 1) {
                uploadFiles.splice(0, 1)
            }
            this.filesList = this.$refs.uploadRef.uploadFiles
            // this.filesList = uploadFiles
            // console.log(uploadFile, uploadFiles);
            // uploadFiles.map(item=>{
            //     item.url = URL.createObjectURL(item.raw)
            // })
        },
        uploadClick() {
            this.$refs.uploadRef.submit()
        },
        //上传
        uploadSumit(options) {
            console.log(options)
            const formData = new FormData()
            formData.append('file', options['file'])
            formData.append('id', this.form.id)
            formData.append('type', 'products')
            // 发送自定义请求
            upload(formData)
                .then((response) => {
                    // 处理上传成功的响应
                    this.form.pic =
                        '/img/products' +
                        '/' +
                        this.form.id +
                        '/' +
                        options['file'].name
                    console.log(this.form.pic)
                    console.log(response)
                    productsAddOrUpdate({
                        ...this.form
                    })
                })
                .catch((error) => {
                    // 处理上传失败的响应
                    console.error(error)
                })
        },




        debug() {
            console.log(this.filesList)
        },
        remove(index) {
            this.$refs.uploadRef.uploadFiles.splice(index, 1)
            this.filesList = this.$refs.uploadRef.uploadFiles
        },
        //变化

        close() {
            this.flag = false
            this.$emit('close', this.flag)
        },
        submit() {
            this.$refs.form.validate((valid) => {
                if (valid) {
                    if (
                        this.form.categoryId !==
                        '2168c742bb0d69bf4b696f46eb8461e8'
                    ) {
                        this.form.inventory = 1
                    }
                    console.log(this.form)
                    this.form.storeId = getStore("storeId")
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

<style lang="scss">
.image-list {
    .el-image {
        width: 100px;
        height: 100px;
    }
}
</style>