<template>
    <div class="store-management">
        <el-form
            ref="searchForm"
            :model="searchForm"
            :inline="true"
            class="form-item"
            label-width="80px"
        >
            <el-col :xl="6" :md="8" :sm="24">
                <el-input
                    placeholder="商店名称"
                    v-model="searchForm.name"
                    clearable
                >
                </el-input>
            </el-col>

            <el-col :xl="6 || 24" :md="8 || 24" :sm="24">
                <el-form-item>
                    <div :style="{ float: 'right', overflow: 'hidden' } || {}">
                        <el-button
                            icon="el-icon-search"
                            type="primary"
                            @click="search"
                            >搜索</el-button
                        >
                    </div>
                </el-form-item>
            </el-col>

            <el-button type="primary" @click="add()">新增</el-button>
        </el-form>

        <el-Table :data="TableData" style="width: 100%">
            <el-Table-column label="名称" prop="name"></el-Table-column>

            <el-Table-column label="地址" prop="address"></el-Table-column>

            <el-Table-column
                label="联系电话"
                prop="telephone"
            ></el-Table-column>

            <el-Table-column
                label="所属管理员"
                prop="etc.user.username"
            ></el-Table-column>

            <el-Table-column label="操作" fixed="right">
                <template slot-scope="scope">
                    <el-button
                        @click="gotoBusiness(scope.row)"
                        size="mini"
                        style="margin-bottom: 10px"
                        >商品管理</el-button
                    >

                    <br />
                    <el-button
                        size="mini"
                        @click="edit(scope.row)"
                        style="margin-bottom: 10px"
                        >商店信息编辑</el-button
                    ><br />
                    <el-button
                        size="mini"
                        type="danger"
                        @click="delOne(scope.row.id)"
                        >删除</el-button
                    >
                </template>
            </el-Table-column>
        </el-Table>

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

        <addOrEdit
            v-if="visible"
            :defaultFormDate="obj"
            :title="title"
            :visible="visible"
            @close="closeFather"
        ></addOrEdit>
        <goods
            v-if="visible_mgn"
            :visible="visible_mgn"
            :storeId="storeId"
            @close="closeMgn"
        ></goods>
    </div>
</template>
  
<script>
import { getByToken } from '@/api/modules/user'
import { storePage, storeDeleteByIds } from '@/api/modules/store'
import addOrEdit from './module/addOrEdit'
import goods from './module/goods'
import { getStore } from '@/lib/storage'

export default {
    components: { addOrEdit, goods },
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
                userId: ''
            },
            TableData: [],
            selectionIds: [],
            textarea: '',
            value: ''
        }
    },

    mounted() {
        this.userId = getStore('userId')
        this.userType = getStore('userType')
        console.log(this.userType)
        this.loadUserData()
        this.loadTableData()

        this.$store.commit('setUserId')
        console.log(this.$store.getters['getUserId'])
    },
    methods: {
        gotoBusiness(a) {
            console.log(a.id)
            this.$store.commit('setStoreId', a.id)
            console.log('storeId:' + this.$store.getters['getStoreId'])
            this.$router.push('Business')
        },
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
            this.obj = {}
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

        loadUserData() {
            let token = getStore('token')
            if (token) {
                getByToken({ token: token })
                    .then((result) => {
                        this.user = result.data
                        console.log(this.user)
                    })
                    .catch((err) => {
                        console.log('error:' + err)
                    })
            } else {
                console.log('token为空')
            }
        },

        loadTableData() {
            console.log(this.userId)
            console.log(this.userType)
            // if (this.userType == 2) {
            this.searchForm.userId = this.userId
            // }

            storePage({ ...this.searchForm })
                .then((result) => {
                    this.TableData = result.data.records
                    // this.TableData = this.setText(result.data.records);
                    this.total = result.data.total
                    console.log(result)
                    //console.log(this.UserData)
                })
                .catch((err) => {
                    //console.log("error:"+err)
                })
        },
        handleSizeChange(val) {
            this.searchForm.pageSize = val
            this.loadTableData()
            console.log(`每页 ${val} 条`)
        },
        handleCurrentChange(val) {
            this.searchForm.pageNum = val
            this.loadTableData()
            console.log(`当前页: ${val}`)
        },

        someMethod() {
            this.$store.commit('setStoreId', storeId)
        }
    },
    actions: {
        setStoreIdAsync({ commit }, storeId) {
            commit('setStoreId', storeId)
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