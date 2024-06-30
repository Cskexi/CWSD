<template>
    <el-dialog :title="title" :visible="visible" :before-close="close">

        <el-form ref="form" :model="form">
            <el-form-item>
                <el-input type="textarea" :rows="2" placeholder="请输入内容" v-model="str">
                </el-input>
            </el-form-item>
        </el-form>

        <div slot="footer" class="dialog-footer">
            <el-button @click="close">取 消</el-button>
            <el-button type="primary" @click="submit">确 定</el-button>
        </div>

    </el-dialog>
</template>    

<script>
import { orderAddOrUpdate, orderList } from "@/api/modules/order"
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
        status: {
            type: Number,
            default: 0
        },
        defaultFormDate: {
            type: Object,
        },
    },
    data() {
        return {
            str: "",
            flag: false,
            form: {
                id: "",
                status: 0,
                refund: "",
                cancleReason: "",
                comment:"",
                refundAdmin:"",
            }
        };
    },
    mounted() {
        if (JSON.stringify(this.defaultFormDate) == "{}") {
            this.flag = false
        } else {
            this.form = JSON.parse(JSON.stringify(this.defaultFormDate))
//            console.log(this.form)
            this.flag = true;
        }
    },
    methods: {
        close() {
            this.flag = false;
            this.$emit("close", this.flag)
        },
        submit() {
            this.$refs.form.validate(valid => {
                if (valid) {
                    console.log(this.status);
                    this.form.status=this.status;
                    if(this.status==4){
                        this.form.comment=this.str;
                    }else if(this.status==-2){
                        this.form.refund=this.str;
                    }
                    else if(this.status==-5){
                        this.form.refundAdmin=this.str;
                    }
                    orderAddOrUpdate({
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