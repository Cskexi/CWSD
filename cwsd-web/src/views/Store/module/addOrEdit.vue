<template>
    <el-dialog :title="title" :visible="visible" :before-close="close">

        <el-form :model="form" ref="form">
            <el-form-item label="商店名称" prop="name">
                <el-input v-model="form.name" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="地址" prop="address">
                <el-input v-model="form.address" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="电话" prop="telephone">
                <el-input v-model="form.telephone" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="简介" prop="info">
                <el-input v-model="form.info" autocomplete="off"></el-input>
            </el-form-item>
        </el-form>

        <div slot="footer" class="dialog-footer">
            <el-button @click="close">取 消</el-button>
            <el-button type="primary" @click="submit">确 定</el-button>
        </div>

    </el-dialog>
</template>    

<script>
import { storeAddOrUpdate } from "@/api/modules/store"
import { setStore, removeStore, getStore } from "@/lib/storage"
export default {
    name: "addOrEdit",
    props: {
        visible: {
            type: Boolean,
            default: false
        },
        title: {
            type: String,
            default: "新增"
        },
        defaultFormDate: {
            type: Object,
        },
    },
    data() {
        return {
            flag: false,
            form: {
                name: '',
                address: '',
                telephone: '',
                userId: '',
                info: ''
            },
            userId: "",
        };
    },
    mounted() {

        if (JSON.stringify(this.defaultFormDate) == "{}") {
            this.flag = false
        } else {
            this.form = JSON.parse(JSON.stringify(this.defaultFormDate))
            //console.log(this.form)
            this.flag = true;
        }
        this.userId = getStore("userId")
        this.form.userId = this.userId;


    },
    methods: {
        close() {
            this.flag = false;
            this.$emit("close", this.flag)
        },
        submit() {
            this.$refs.form.validate(valid => {
                if (valid) {
                    if (this.form.userId == null) {
                        console.log("当前为登录");
                    }
                    storeAddOrUpdate({
                        ...this.form
                    }).then(res => {
                        this.flag = true
                        this.$emit("close", this.flag)
                    })
                        .catch(err => {
                            this.flag = false
                            this.$emit("close", this.flag)
                        })
                }

            })

        }
    }
};
</script>