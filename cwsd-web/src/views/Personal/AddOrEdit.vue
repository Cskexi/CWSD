<template>
    <el-dialog
        :title="title"
        :visible="visible"
        :before-close="close"
        append-to-body
    >
        <el-form :model="form" ref="form">
            <el-form-item label="性别" prop="sex">
                <el-input v-model="form.sex" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="电话" prop="telephone">
                <el-input
                    v-model="form.telephone"
                    autocomplete="off"
                ></el-input>
            </el-form-item>
        </el-form>
        <!-- 上传 -->
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

        <div class="image-list">
            <el-image
                v-for="(file, index) of filesList"
                :key="index"
                :src="file.url"
                fit="cover"
            ></el-image>
            <i
                class="iconfont icon-delete"
                v-for="(file, index) of filesList"
                :key="index"
                @click="remove(index)"
            ></i>
            <i
                class="iconfont icon-search"
                v-for="(file, index) of filesList"
                :key="index"
            ></i>
        </div>
        <!-- 到这里 -->

        <div slot="footer" class="dialog-footer">
            <el-button @click="close">取 消</el-button>
            <el-button type="primary" @click="submit">确 定</el-button>
        </div>
    </el-dialog>
</template>    

<script>
import {
    getByToken,
    userList,
    userPage,
    userDeleteByIds,
    userStateChangeById,
    userTypeChangeById,
    userAddOrUpdate
} from '@/api/modules/user'
import { upload } from '@/api/modules/upLoad' //使用

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
            imageUrl: '',
            flag: false,
            form: {
                id: '',
                username: '',
                ballance: '',
                telephone: '',
                sex: '',
                pic: ''
            }
        }
    },
    mounted() {
        this.form = this.defaultFormDate
    },
    methods: {
        debug() {
            console.log(this.filesList)
        },
        remove(index) {
            this.$refs.uploadRef.uploadFiles.splice(index, 1)
            this.filesList = this.$refs.uploadRef.uploadFiles
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
            formData.append('type', 'user')
            // 发送自定义请求
            upload(formData)
                .then((response) => {
                    // 处理上传成功的响应
                    this.form.pic =
                        '/img/user' +
                        '/' +
                        this.form.id +
                        '/' +
                        options['file'].name
                    console.log(this.form.pic)
                    console.log(response)
                    userAddOrUpdate({
                        ...this.form
                    })
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
        //提交
        submit() {
            this.$refs.form.validate((valid) => {
                if (valid) {
                    userAddOrUpdate({
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
<style scoped>
.image-list {
    display: flex;
    flex-wrap: wrap;
}

.image-list el-image {
    width: 180px;
    height: 120px;
    margin: 3px;
    border: 1px solid #d1dbe5;
}

.iconfont {
    position: absolute;
    width: 30px;
    height: 30px;
    background: rgba(0, 0, 0, 0.3);
    color: #fff;
    line-height: 30px;
    text-align: center;
    cursor: pointer;
}

.icon-search {
    left: 3px;
}

.icon-delete {
    right: 3px;
    bottom: 3px;
}
</style>