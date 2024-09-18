<template>
    <el-dialog :title="title" :visible="visible" :before-close="close">

        <el-form :model="form" ref="form" :rules="rules">
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
            rules: {
                name: [
                    { required: true, message: '请输入商店名称', trigger: 'blur' },
                    { min: 2, max: 50, message: '长度在 2 到 50 个字符', trigger: 'blur' }
                ],
                address: [
                { required: true, message: '请填写商店地址', trigger: 'blur' },
                { validator: this.validateAddress, trigger: 'blur' }
            ],
                telephone: [
                    { required: true, message: '请输入电话号码', trigger: 'blur' },
                    { pattern: /^1[3456789]\d{9}$/, message: '请输入正确的手机号码', trigger: 'blur' }
                ]
            },
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
        validateAddress(rule, value, callback) {
        if (!value) {
            return callback(new Error('地址不能为空'));
        }

        // 假设地址的最大长度为255个字符
        if (value.length > 255) {
            return callback(new Error('地址长度不能超过255个字符'));
        }

        // 假设地址至少包含街道名称和门牌号码
        if (!value.includes('街') && !value.includes('路')) {
            return callback(new Error('地址中应包含街道名称'));
        }

        // 可以添加更多复杂的验证规则，如正则匹配邮政编码等
        callback();
    },
        close() {
            this.flag = false;
            this.$emit("close", this.flag)
        },
        submit() {

            this.$refs.form.validate(valid => {
                if (valid) {
                    storeAddOrUpdate({
                        ...this.form
                    }).then(res => {
                        this.$emit("close", true);
                    })
                    .catch(err => {
                        console.error('提交失败', err);
                        this.$emit("close", false);
                    });
                } else {
                    console.log('错误的表单信息');
                    return false;
                }
            });
        }
    }
};
</script>