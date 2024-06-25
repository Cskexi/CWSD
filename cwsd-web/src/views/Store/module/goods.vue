<template>
    <el-dialog title="管理商品" :visible="visible" :before-close="close">
        <el-form ref="searchForm" :model="searchForm" :inline="true" class="formItem" label-width="80px">



            <el-col :xl="6" :md="8" :sm="24">
                <el-input placeholder="商品名称" v-model="searchForm.name" clearable> </el-input>
            </el-col>

            <el-col :xl="6 || 24" :md="8 || 24" :sm="24">
                <el-form-item>
                    <div :style="{ float: 'right', overflow: 'hidden' } || {}">
                        <el-button icon="el-icon-search" type="primary" @click="search">搜索</el-button>
                    </div>
                </el-form-item>
            </el-col>
        </el-form>


        <el-button type="primary" @click="add">新增</el-button>
        <el-button type="primary" @click="delAll">批量删除</el-button>


        <el-table :data="tableData" style="width: 100%" @selection-change="handleSelectionChange">

            <el-table-column type="selection" width="55">
            </el-table-column>

            <el-table-column type="图片" width="55">
                <template slot-scope="scope">
                    <el-image v-if="scope.row.pic" :src="scope.row.pic" fit="cover"></el-image>
                    <el-image v-else src="/logo.png" fit="cover"></el-image>
                </template>
            </el-table-column>


            <el-table-column label="商品名称" width="180" prop="name">
            </el-table-column>
            <el-table-column label="价格" width="180" prop="price">
            </el-table-column>
            <el-table-column label="库存" width="180" prop="inventory">
            </el-table-column>
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button @click="edit(scope.row)" style="margin-bottom: 10px;">编辑</el-button><br>
                    <el-button type="danger" @click="delOne(scope.row.id)">删除</el-button><br>
                </template>
            </el-table-column>
        </el-table>

        <div class="block">
            <span class="demonstration">完整功能</span>
            <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                :current-page="searchForm.pageNum" :page-sizes="[2, 5, 10, 20]" :page-size="searchForm.pageSize"
                layout="total, sizes, prev, pager, next, jumper" :total="total">
            </el-pagination>
        </div>

        <addOrEdit v-if="visible_e" :defaultFormDate="obj" :title="title" :visible="visible_e" @close="closeFather">
        </addOrEdit>
    </el-dialog>
</template>
    
    
<script>
import { goodsPage, goodsDeleteByIds } from "@/api/modules/goods"
import addOrEdit from "./goodsAddOrEdit"
export default {
    components: { addOrEdit },
    props: {
        visible: {
            type: Boolean,
            default: false
        },
        storeId: {
            type: String
        }
    },
    data() {
        return {
            localImagePath: '/logo.png',
            selectionIds: [],
            visible_e: false,
            obj: {},
            title: "新增",
            total: 5,

            tableData: [],
            searchForm: {
                pageNum: 1,
                pageSize: 5,
                name: '',
                storeId: "",
            },
        }
    },
    mounted() {

        this.searchForm.storeId = this.storeId
        this.loadTableData()
    },
    methods: {
        search() {
            this.loadTableData()
        },
        dicMgn(storeId) {
        },
        delOne(id) {
            this.del(id)
        },
        delAll() {
            if (this.selectionIds.length <= 0) {

                this.$message.warning("先选择记录")
                return
            }
            this.del(this.selectionIds.join(","))
        },
        del(ids) {
            this.$confirm('确定删除该记录？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                goodsDeleteByIds({ ids: ids }).then(result => {
                    this.$message('删除成功')
                    this.loadTableData()
                }).catch(err => {
                    this.$message.error(err)
                })
            }).catch(() => {
                console.log("cancle")
            });
        },
        add() {
            // //打开对话框
            // //执行操作
            this.title = "新增商品"
            this.visible_e = true;
            this.obj = { storeId: this.storeId }
        },
        edit(row) {
            this.obj = row
            this.title = "编辑商品：" + row.name
            this.visible_e = true;
        },
        loadTableData() {
            goodsPage({
                ...this.searchForm
            }).then(result => {
                console.log(result.data)
                this.total = result.data.total;
                this.tableData = result.data.records;
                //this.total = result.data.total
            }).catch(err => {
                console.log("error:" + err)
            })
        },


        handleSizeChange(val) {
            this.searchForm.pageSize = val;
            this.loadTableData()
            console.log(`每页 ${val} 条`);
        },
        handleCurrentChange(val) {
            this.searchForm.pageNum = val;
            this.loadTableData()
            console.log(`当前页: ${val}`);
        },

        close() {
            this.$emit("close", false)
        },
        handleSelectionChange(val) {
            this.selectionIds = [];
            val.forEach(item => {
                // console.log(item.id)
                this.selectionIds.push(item.id)
            })
            console.log(this.selectionIds.join(","))

        },
        closeFather(val) {
            this.visible_e = false
            if (val) {
                this.loadTableData()
            }
        },
    }
}
</script>