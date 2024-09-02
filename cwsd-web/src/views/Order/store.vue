<template>
    <div>
        <h1>订单管理</h1>

        <el-Table :data="TableData" style="width: 100%">

            <el-Table-column label="申请单编号" prop="no"></el-Table-column>
            <el-Table-column label="申请时间" prop="createTime"></el-Table-column>
            <el-Table-column label="名称" prop="etc.products.name"></el-Table-column>
            <el-table-column label="图片">
                <template slot-scope="scope">
                    <el-image v-if="scope.row.etc.products.pic" class="user-avatar" :src="scope.row.etc.products.pic"
                        fit="cover"></el-image>
                </template>
            </el-table-column>
            <el-table-column label="价格" prop="priceAtOrder">
                <template slot-scope="scope">
                    {{ Number(scope.row.priceAtOrder).toFixed(2) }}
                </template>
            </el-table-column>
            <el-table-column label="数量" prop="number"></el-table-column>
            <el-table-column label="总价" prop="AllPrice">
                <template slot-scope="scope">
                    {{ Number(scope.row.AllPrice).toFixed(2) }}
                </template>
            </el-table-column>

            <!-- <el-Table-column label="总价" prop="totalPrice"></el-Table-column> -->
            <el-Table-column label="状态" prop="statusName"></el-Table-column>
            <el-table-column label="退单理由" prop="refundReason">
                <template slot-scope="scope">
                    <el-popover placement="top-start" width="300" trigger="click">
                        <p>{{ scope.row.refundReason }}</p>
                        <div slot="reference" class="name-wrapper">
                            <el-button size="mini" type="text">{{ scope.row.refundReason }}</el-button>
                        </div>
                    </el-popover>
                </template>
            </el-table-column>
            <el-Table-column label="操作" fixed="right">
                <template slot-scope="scope">
                    <el-button v-if="scope.row.status === 1" size="mini" type="danger" @click="changeStatus(scope.row, 2)"
                        style="margin-bottom: 10px;">发货</el-button>
                    <el-button v-if="scope.row.status === -2 || scope.row.status === -3" size="mini" type="primary"
                        @click="changeStatus(scope.row, -4)" style="margin-bottom: 10px;">审核通过</el-button><br>
                    <el-button v-if="scope.row.status >= 3" size="mini" type="primary" @click="edit(scope.row, -5)"
                        style="margin-bottom: 10px;">退单</el-button>
                    <el-button v-if="scope.row.status === -2 || scope.row.status === -3" size="mini" type="danger"
                        @click="changeStatus(scope.row, -scope.row.status)"
                        style="margin-bottom: 10px;">审核不通过</el-button><br>
                </template>
            </el-Table-column>
        </el-Table>
        <div class="block">
            <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                :current-page="searchForm.pageNum" :page-sizes="[2, 5, 10, 20]" :page-size="searchForm.pageSize"
                layout="total, sizes, prev, pager, next, jumper" :total="total">
            </el-pagination>
        </div>
        <addOrEdit v-if="visible" :defaultFormDate="obj" :title="title" :visible="visible" :status="status"
            @close="closeFather"></addOrEdit>
    </div>
</template>
    
<script>
import { setStore, removeStore, getStore } from "@/lib/storage"
import { orderItemsPage, orderItemsAddOrUpdate } from '@/api/modules/orderItems'
import { productsNumChange } from '@/api/modules/products'
import addOrEdit from "./module/addOrEdit"
export default {
    components: { addOrEdit },
    data() {
        return {
            visible: false,
            TableData: [],
            userId: "",
            total: 10,
            orderForm: {
                id: "",
                status: 0,
                refund: "",
                cancleReason: "",
            },
            Form: {
                Id: "",
                num: ""
            },
            searchForm: {
                pageNum: 1,
                pageSize: 10,
                storeId: ''
            },
        }
    },
    mounted() {
        this.userId = getStore("userId");
        this.searchForm.storeId = getStore("storeId")
        this.loadTableData()
    },
    methods: {
        closeFather(val) {
            this.visible = false
            if (val) {
                this.loadTableData()
            }
        },
        edit(row, status) {

            this.obj = row;
            if (status == 4)
                this.title = "评价";
            else if (status == -2) {
                this.title = "退单理由";
            }
            else if (status == -5) {
                this.title = "商家退单";
            }
            this.visible = true;
            this.status = status;
        },
        changeStatus(row, status) {
            this.orderForm.id = row.id
            this.orderForm.status = status
            this.Form.Id = row.productId
            this.Form.num = row.number
            orderItemsAddOrUpdate({ ...this.orderForm }).then(result => {
                this.$message('状态变化成功')
                if (status == 2) {
                    productsNumChange({ ...this.Form })
                } else if (status == -4) {
                    this.Form.num = -this.Form.num
                    productsNumChange({ ...this.Form })
                }
                this.loadTableData()
            }).catch(err => {
                this.$message.error(err)
            })
        },
        setStatusName(state) {
            let lst = state
            lst.forEach(item => {
                item.AllPrice = item.priceAtOrder * item.number;
                switch (item.status) {
                    case 0:
                        item.statusName = "待付款"
                        break
                    case 1:
                        item.statusName = "待发货"
                        break
                    case 2:
                        item.statusName = "待收货"
                        break
                    case 3:
                        item.statusName = "待评价"
                        break
                    case 4:
                        item.statusName = "已评价"
                        break
                    case -1:
                        item.statusName = "订单被取消"
                        break
                    case -2:
                        item.statusName = "申请退单中"
                        break
                    case -3:
                        item.statusName = "申请退单中"
                        break
                    case -4:
                        item.statusName = "退单成功"
                        break
                    case -5:
                        item.statusName = "商家退单"
                        break
                }

            })
            return lst
        },
        loadTableData() {
            console.log(this.searchForm)
            orderItemsPage({ ...this.searchForm })
                .then(result => {
                    this.TableData = this.setStatusName(result.data.records)
                }).catch(err => {
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

    },
}

</script>
    