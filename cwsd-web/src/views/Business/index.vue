import store from '@/store';
<template>
    <div class="store-management">
        商品管理
        <!-- 搜索框和新增按钮 -->
        <el-form
            ref="searchForm"
            :model="searchForm"
            :inline="true"
            class="form-item"
            label-width="80px"
        >
            <el-button type="primary" @click="add()">新增商品</el-button>
        </el-form>
        <!-- 商品表 -->
        <el-Table :data="productsData" style="width: 100%">
            <el-Table-column label="名称" prop="name"></el-Table-column>

            <el-Table-column label="商品价格" prop="price"></el-Table-column>

            <el-Table-column label="库存" prop="inventory"></el-Table-column>

            <el-Table-column label="操作" fixed="right">
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        @click="dicMgn(scope.row.id)"
                        style="margin-bottom: 10px"
                        >商品管理</el-button
                    ><br />
                    <!-- <el-button
                        size="mini"
                        @click="edit(scope.row)"
                        style="margin-bottom: 10px"
                        >商店信息编辑</el-button
                    ><br /> -->
                    <el-button
                        size="mini"
                        type="danger"
                        @click="delOne(scope.row.id)"
                        >删除</el-button
                    >
                </template>
            </el-Table-column>
        </el-Table>
        <!-- 分页设置 -->
        <div class="block">
            <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="searchForm.pageNum"
                :page-sizes="[2, 5, 10, 20]"
                :page-size="searchForm.pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total"
            >
            </el-pagination>
        </div>
        <!-- 新增商品 -->
        <addOrEdit
            v-if="visible"
            :defaultFormDate="obj"
            :title="title"
            :visible="visible"
            @close="closeFather"
        ></addOrEdit>
    </div>
</template>
  
<script>
import { getByToken } from '@/api/modules/user'
import { productsPage } from '@/api/modules/products'
import {
    storePage,
    storeDeleteByIds,
    storeGetStoreByUserId,
    storeAddOrUpdate
} from '@/api/modules/store'
import { getStore } from '@/lib/storage'
import addOrEdit from './module/addOrEdit'
export default {
    components: { addOrEdit },
    data() {
        return {
            visible: false,
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
            productsData: []
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
            this.title = '编辑商店：' + row.name
            this.visible = true
        },
        add() {
            this.visible = true
            this.obj = { storeId: this.storeData.storeId }
            this.title = '新增'
        },
        closeFather(val) {
            this.visible = false
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
                    storeDeleteByIds({ ids: ids })
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
            // console.log(this.userId);
            // console.log(this.userType);
            // if (this.userType == 2) {
            this.searchForm.userId = this.userId
            // }
            productsPage({ ...this.searchForm })
                .then((result) => {
                    //this.TableData = result.data.records;
                    // this.TableData = this.setText(result.data.records);
                    //this.total = result.data.total;
                    console.log(result.data.records)
                    this.porductsData = result.data.records

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