<template>
    <el-dialog
        :title="title"
        :visible="visible"
        :before-close="close"
        append-to-body
    >
        <el-upload
            ref="uploadRef"
            action="#"
            :file-list="filesList"
            :http-request="uploadSumit"
            :auto-upload="false"
            list-type="picture"
            :limit="2"
            :show-file-list="false"
            :on-change="handleChange"
        >
            <el-button type="primary">选择</el-button>
        </el-upload>
        <el-button type="success" @click="uploadClick">上传</el-button>
        图片<br />
        <ul class="viewBox">
            <li v-for="(file, index) of filesList" :key="index">
                <el-image :src="file.url" fit="cover"></el-image>
            </li>
        </ul>
        <br />

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
import { dicAddOrUpdate } from '@/api/modules/dic'
import { goodsAddOrUpdate } from '@/api/modules/goods'
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
        }
    },
    data() {
        return {
            filesList: [],
            flag: false,
            form: {
                id: '',
                name: '',
                price: '',
                inventory: '',
                storeId: '',
                pic: ''
            }
        }
    },
    mounted() {
        // if(JSON.stringify(this.defaultFormDate)=="{}"){
        //     this.flag = false
        // }else{
        this.form = JSON.parse(JSON.stringify(this.defaultFormDate))
        //     console.log(this.form)
        //     this.flag =true;
        // }
        this.storeId = getStore('storeId')
        this.storeType = getStore('storeType')

        this.$store.commit('setStoreId')
        console.log(this.$store.getters['getStoreId'])
    },
    methods: {
        remove(index) {
            this.$refs.uploadRef.uploadFiles.splice(index, 1)
            this.filesList = this.$refs.uploadRef.uploadFiles
        },

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
        uploadSumit(options) {
            console.log(options)
            const formData = new FormData()
            formData.append('file', options['file'])
            formData.append('id', this.form.id)
            formData.append('type', 'goods')
            // 发送自定义请求
            upload(formData)
                .then((response) => {
                    // 处理上传成功的响应
                    this.form.pic =
                        '/img/goods' +
                        '/' +
                        this.form.id +
                        '/' +
                        options['file'].name
                    console.log(this.form.pic)
                    console.log(response)
                })
                .catch((error) => {
                    // 处理上传失败的响应
                    console.error(error)
                })
        },

        close() {
            this.flag = false
            this.$emit('close', this.flag)
        },
        submit() {
            this.$refs.form.validate((valid) => {
                if (valid) {
                    goodsAddOrUpdate({
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