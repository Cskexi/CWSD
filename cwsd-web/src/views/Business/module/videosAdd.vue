<template>
    <el-dialog :title="title" :visible="visible" :before-close="close">
        <el-form :model="form" ref="form">
            <el-form-item label="商品名称" prop="name">
                <el-input v-model="form.name" autocomplete="off"></el-input>
            </el-form-item>

            <!-- 上传 -->
            <el-upload ref="uploadRef" action="#" :file-list="filesList" :http-request="uploadSumit" :auto-upload="false"
                list-type="picture" :limit="2" :show-file-list="false" :on-change="handleChange">
                <el-button type="primary">视频选择</el-button>
            </el-upload>

            <el-button type="success" @click="uploadClick">视频上传</el-button>

            <!-- <div class="image-list">
                <el-image v-for="(file, index) of filesList" :key="index" :src="file.url" fit="cover"></el-image>
                <i class="iconfont icon-delete" v-for="(file, index) of filesList" :key="index" @click="remove(index)"></i>
            </div> -->
            <!-- 到这里 -->
        </el-form>

        <div slot="footer" class="dialog-footer">
            <el-button @click="close">取 消</el-button>
            <el-button type="primary" @click="submit">确 定</el-button>
        </div>
    </el-dialog>
</template>    

<script>
import { videosAddOrUpdate } from '@/api/modules/videos'
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
    },
    data() {
        return {
            filesList: [], // 文件列表
            imageUrl: '',
            flag: false,
            form: {
                name: '',
                price: '',
                inventory: '',
                storeId: '',
                pic: ''
            },
            form2: {

            },

            userId: '',
            options: []
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
        this.form2.productId = this.form.id
    },
    methods: {
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
            formData.append('type', 'productsVideo')
            // 发送自定义请求
            upload(formData)
                .then((response) => {
                    // 处理上传成功的响应
                    this.form2.videoUrl =
                        '/img/productsVideo' +
                        '/' +
                        this.form.id +
                        '/' +
                        options['file'].name
                    videosAddOrUpdate({
                        ...this.form2
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

    }
}
</script>