import store from '@/store';
<template>
    <div class="store-management">
        1233 商品管理
        <!-- 搜索框和新增按钮 -->
        <el-form ref="searchForm" :model="searchForm" :inline="true" class="form-item" label-width="80px">
            <el-button type="primary" @click="add()">新增商品</el-button>
        </el-form>
        <!-- 商品表 -->
        <el-Table :data="productsData" style="width: 100%">
            <el-Table-column label="名称" prop="name"></el-Table-column>
            <el-table-column label="图片">
                <template slot-scope="scope">
                    <el-image v-if="scope.row.pic" class="user-avatar" :src="scope.row.pic" fit="cover"></el-image>
                </template>
            </el-table-column>
            <el-Table-column label="商品价格" prop="price"></el-Table-column>

            <el-Table-column label="库存" prop="inventory"></el-Table-column>

            <el-Table-column label="操作" fixed="right">
                <template slot-scope="scope">
                    <el-button size="mini" @click="edit(scope.row)" style="margin-bottom: 10px">商品管理</el-button><br />
                    <el-button size="mini" @click="edit2(scope.row)" style="margin-bottom: 10px">视频管理</el-button><br />
                    <el-button size="mini" type="danger" @click="delOne(scope.row.id)">删除</el-button>
                </template>
            </el-Table-column>
        </el-Table>
        <!-- 分页设置 -->
        <div class="block">
            <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                :current-page="searchForm.pageNum" :page-sizes="[2, 5, 10, 20]" :page-size="searchForm.pageSize"
                layout="total, sizes, prev, pager, next, jumper" :total="total">
            </el-pagination>
        </div>
        <!-- 新增商品 -->
        <addOrEdit v-if="visible" :defaultFormDate="obj" :defaultFormDate2="obj2" :title="title" :visible="visible"
            @close="closeFather"></addOrEdit>

        <videosAdd v-if="visible2" :defaultFormDate="obj3" :title="title" :visible="visible2" @close="closeFather2">
        </videosAdd>
    </div>
</template>
  
<script>
import { categoriesList } from '@/api/modules/categories'
import { getByToken } from '@/api/modules/user'

import { orderItemsPage } from '@/api/modules/orderItems'
import { productsDeleteByIds, productsPage, productsPage2 } from '@/api/modules/products'
import {
    storePage,
    storeDeleteByIds,
    storeGetStoreByUserId,
    storeAddOrUpdate
} from '@/api/modules/store'
import { getStore } from '@/lib/storage'
import addOrEdit from './module/addOrEdit'
import videosAdd from './module/videosAdd'
export default {
    components: { addOrEdit, videosAdd },
    data() {
        return {
            visible: false,
            visible2: false,
            visible_mgn: false,
            storeId: '',
            total: 10,
            userId: '',
            userType: 3,
            searchForm: {
                pageNum: 1,
                pageSize: 10,
                storeId: ''
            },
            TableData: [],
            selectionIds: [],
            textarea: '',
            value: '',
            productsData: [],
            categoriesData: []
        }
    },
    created() {
        //
        console.log(' ID:', this.$route.params)
    },
    mounted() {
        //console.log(this.$route.params.storeId)
        this.userId = getStore('userId')
        //console.log(this.userId)
        this.userType = getStore('userType')
        this.storeType = getStore('storeType')
        //
        this.storeId = this.$store.getters['getStoreId']
        console.log("3213:" + getStore("storeId"))
        this.searchForm.storeId = getStore("storeId")
        this.loadUserData()
        this.loadTableData()
    },
    methods: {
        dicMgn(id) {
            this.visible_mgn = true
            this.storeId = id
        },
        edit(row) {
            this.obj = row
            this.obj2 = this.categoriesData
            this.title = '编辑商店：' + row.name
            this.visible = true
        },
        edit2(row) {
            this.obj3 = row
            this.title = '编辑商店：' + row.name
            this.visible2 = true
        },
        add() {
            this.visible = true
            this.obj2 = this.categoriesData
            this.obj = { storeId: this.storeId }
            this.title = '新增'
        },
        closeFather(val) {
            this.visible = false
            if (val) {
                this.loadTableData()
            }
        },
        closeFather2(val) {
            this.visible2 = false
            if (val) {
                this.loadTableData()
            }
        },
        closeMgn(val) {
            this.visible_mgn = false
            if (val) {
                this.loadTableData()
            }
        },
        delOne(id) {
            this.del(id)
        },
        del(ids) {
            this.$confirm('确定删除该记录？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            })
                .then(() => {
                    productsDeleteByIds({ ids: ids })
                        .then((result) => {
                            this.$message('删除成功')
                            this.loadTableData()
                        })
                        .catch((err) => {
                            this.$message.error(err)
                        })
                })
                .catch(() => {
                    console.log('cancle')
                })
        },
        search() {
            this.loadTableData()
        },

        // 用户数据
        loadUserData() {
            let token = getStore('token')
            if (token) {
                getByToken({ token: token })
                    .then((result) => {
                        this.user = result.data
                        //console.log(this.user)
                    })
                    .catch((err) => {
                        console.log('error:' + err)
                    })
            } else {
                console.log('token为空')
            }
        },

        // 加载需要的数据
        loadTableData() {
            categoriesList()
                .then((result) => {
                    //this.TableData = result.data.records;
                    // this.TableData = this.setText(result.data.records);
                    //this.total = result.data.total;

                    this.categoriesData = result.data
                    console.log("categoriesData:" + this.categoriesData)
                    //console.log(this.UserData)
                })
                .catch((err) => {
                    //console.log("error:"+err)
                })
            // console.log(this.userId);
            // console.log(this.userType);
            // if (this.userType == 2) {
            // this.searchForm.storeId = this.$store.getters['getStoreId']
            // }
            this.searchForm.storeId = getStore("storeId")
            productsPage2({ ...this.searchForm })
                .then((result) => {
                    //this.TableData = result.data.records;
                    // this.TableData = this.setText(result.data.records);
                    //this.total = result.data.total;
                    console.log(result.data.records)
                    this.productsData = result.data.records

                    //console.log(this.UserData)
                })
                .catch((err) => {
                    //console.log("error:"+err)
                })
        },

        //分页
        handleSizeChange(val) {
            this.searchForm.pageSize = val
            this.loadTableData()
            console.log(`每页 ${val} 条`)
        },
        handleCurrentChange(val) {
            this.searchForm.pageNum = val
            this.loadTableData()
            console.log(`当前页: ${val}`)
        }
    }
}
</script>
  

<style scoped>
.store-management {
    padding: 20px;
}

.form-item {
    margin-bottom: 20px;
}

.action-button {
    margin-bottom: 10px;
}
</style>