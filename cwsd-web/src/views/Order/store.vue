<template>
    <div>
        <h1>请假申请管理页\</h1>

        <el-Table :data="TableData" style="width: 100%">

            <el-Table-column label="申请单编号" prop="no"></el-Table-column>
            <el-Table-column label="申请时间" prop="createTime"></el-Table-column>
            <!-- <el-Table-column label="总价" prop="totalPrice"></el-Table-column> -->
            <el-Table-column label="状态" prop="statusName"></el-Table-column>

            <el-Table-column label="操作" fixed="right">
                <template slot-scope="scope">
                    <el-button v-if="scope.row.status === 1" size="mini" type="danger" @click="changeStatus(scope.row, 2)"
                        style="margin-bottom: 10px;">发货</el-button>
                    <el-button v-if="scope.row.status === -2" size="mini" type="danger" @click="changeStatus(scope.row, -3)"
                        style="margin-bottom: 10px;">审核通过</el-button><br>
                    <el-button v-if="scope.row.status != -4" size="mini" type="primary" @click="changeStatus(scope.row, -4)"
                        style="margin-bottom: 10px;">审核</el-button>
                    <el-button v-if="scope.row.status === -2" size="mini" type="danger" @click="edit(scope.row, -5)"
                        style="margin-bottom: 10px;">审核不通过</el-button><br>
                </template>
            </el-Table-column>
        </el-Table> 
    <addOrEdit>" :defaultFormDate="obj" :title="title" :visible="visible" :status="status"
                @close="closeFather"></addOrEdit>   

        </div>
</template>
    
<script>
import { shopcartList, shopcartDeleteByIds, shopcartAddOrUpdate } from "@/api/modules/shopcart"
import { orderAddOrUpdate, orderList, orderList2 } from "@/api/modules/order"
import { orderItemAddOrUpdate } from "@/api/modules/orderItem"
import { setStore, removeStore, getStore } from "@/lib/storage"
import addOrEdit from "./module/addOrEdit"
export default {
    components: { addOrEdit },
    data() {
        return {
            visible: false,
            TableData: [],
            userId: "",
            orderForm: {
                id: "",
                status: 0,
                refund: "",
                cancleReason: "",
            }
        }
    },
    mounted() {
        this.userId = getStore("userId");
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
                this.title = "审核不通过理由";
            }
            this.visible = true;
            this.status = status;
        },
        changeStatus(row, status) {
            this.orderForm.id = row.id
            this.orderForm.status = status
            orderAddOrUpdate({ ...this.orderForm }).then(result => {
                this.$message('状态变化成功')
                this.loadTableData()
            }).catch(err => {
                this.$message.error(err)
            })
        },
        setStatusName(state) {
            let lst = state
            lst.forEach(item => {
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
                        item.statusName = "已收货"
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
                        item.statusName = "退单成功"
                        break
                    case -4:
                        item.statusName = "被管理员退单"
                        break
                    case -5:
                        item.statusName = "退单审核不通过"
                        break
                }
            })
            return lst
        },
        loadTableData() {
            orderList2({ id: this.userId })
                .then(result => {
                    //this.TableData = result.data;
                    this.TableData = this.setStatusName(result.data)
                }).catch(err => {
                })
        },

    },
}

</script>
    