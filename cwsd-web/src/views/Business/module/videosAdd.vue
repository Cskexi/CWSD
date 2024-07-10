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
        <div class="video-list">
            <div v-for="(video, index) of videosList" :key="index">
                <video controls class="video-element">
                    <source :src="video.videoUrl">
                </video>
                <el-button type="danger" @click="deleteVideo(video)">删除</el-button>
            </div>
        </div>
        <div slot="footer" class="dialog-footer">
            <el-button @click="close">取 消</el-button>
            <el-button type="primary" @click="close">确 定</el-button>
        </div>
    </el-dialog>
</template>    

<script>
import { videosAddOrUpdate, videosList } from '@/api/modules/videos'
import { setStore, removeStore, getStore } from '@/lib/storage'
import { upload, deleteFile } from '@/api/modules/upLoad'
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
            productId: '',
            userId: '',
            options: [],
            videosList: [],
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
        this.productId = this.form.id
        this.getVideos()
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
        getVideos() {
            videosList({ productId: this.productId }).then((res) => {
                //console.log(res)
                this.videosList = res.data
            })
        },
        deleteVideo(video) {
            deleteFile({ id: video.id, url: video.videoUrl })
            this.getVideos()
            console.log('删除成功')
        },

        close() {
            this.flag = false
            this.$emit('close', this.flag)
        },

    }
}
</script>

<style scoped>
.video-element {
    border: 2px solid #ccc;
    margin-bottom: 20px;
    height: 300px;
    /* 或者使用vh单位 */
    object-fit: cover;
}

/* 使视频列表中的所有视频具有相同的高度 */
.video-list {
    display: flex;
}
</style>