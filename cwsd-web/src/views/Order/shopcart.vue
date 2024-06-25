<template>
    <div class="shopping-cart">
      <h1>购物车管理页面</h1>
  
      <el-table :data="TableData" style="width: 100%" @selection-change="handleSelectionChange">
  
        <el-table-column type="selection" width="55"></el-table-column>
  
        <el-table-column label="名称" prop="etc.goods.name"></el-table-column>
  
        <el-table-column label="单价" prop="etc.goods.price"></el-table-column>
  
        <el-table-column label="数量">
          <template slot-scope="scope">
            <el-input-number v-model="scope.row.number" @change="handleChange(scope.row)" :min="1" :max="10"></el-input-number>
          </template>
        </el-table-column>
  
        <el-table-column label="总价" :formatter="finalPriceFormatter"></el-table-column>
  
        <el-table-column label="操作" fixed="right">
          <template slot-scope="scope">
            <el-button size="mini" type="danger" @click="delOne(scope.row.id)" style="margin-bottom: 5px;">删除</el-button><br>
            <el-button size="mini" type="danger" @click="orderOne(scope.row)">下单</el-button>
          </template>
        </el-table-column>
      </el-table>
  
      <div class="cart-actions">
        <span class="total-price">总价: {{ totalPrice.toFixed(2) }}</span>
        <el-button type="danger" @click="delAll">删除</el-button>
        <el-button @click="orderAll">下单</el-button>
      </div>
    </div>
  </template>
  
<script>
import { shopcartList, shopcartDeleteByIds, shopcartAddOrUpdate } from "@/api/modules/shopcart"
import { orderAddOrUpdate } from "@/api/modules/order"
import { orderItemAddOrUpdate } from "@/api/modules/orderItem"
import { setStore, removeStore, getStore } from "@/lib/storage"

export default {
    data() {
        return {
            visible: false,
            visible_mgn: false,
            storeId: "",
            total: 10,
            TableData: [],
            selectionIds: [],
            selectionRow: [],
            num: 1,
            userId: "",
            searchForm: {
                id: "",
                number: "",
            },
            totalPrice: 0,
            orderForm: {
                userId: "",
                totalPrice: ""
            }
        }
    },
    computed: {
        finalPrice(row) {
            return row.etc.goods.price * etc.goods.price; // 假设计算逻辑是减去折扣  
        },
    },
    mounted() {
        this.userId = getStore("userId");
        this.loadTableData()
    },
    methods: {
        finalPriceFormatter(row) {
            let finalPrice = row.etc.goods.price * row.number
            row.etc.goods.etc = finalPrice
            return finalPrice; // 假设计算逻辑是减去折扣  
        },
        handleChange(row) {
            this.searchForm.id = row.id;
            this.searchForm.number = row.number
            shopcartAddOrUpdate({
                ...this.searchForm
            }).then(result => {
                console.log(result.data)
            }).catch(err => {
                console.log("error:" + err)
            })
        },
        dicMgn(id) {
            this.visible_mgn = true
            this.storeId = id
        },
        edit(row) {
            this.obj = row
            this.title = "编辑商店：" + row.name;
            this.visible = true;
        },
        add() {
            this.visible = true;
            this.obj = {}
            this.title = "新增";
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

        // orderAll() {
        //     if (this.selectionRow.length <= 0) {
        //         this.$message.warning("先选择记录")
        //         return
        //     }
        //     this.$confirm('确定下单？', '提示', {
        //         confirmButtonText: '确定',
        //         cancelButtonText: '取消',
        //         type: 'warning'
        //     }).then(() => {
        //         this.selectionRow.forEach(item => {
        //             console.log(item)
        //             this.order(item)
        //         })
        //         this.loadTableData()
        //     }).catch(() => {
        //         console.log("cancle")
        //     });
        // },


        // orderAll 方法  
orderAll() {  
    if (this.selectionRow.length <= 0) {  
        this.$message.warning("请先选择记录");  
        return;  
    }  
  
    // 创建一个用于保存所有异步操作的数组  
    const asyncActions = this.selectionRow.map(item => {  
        return this.order(item);  
    });  
  
    // 使用 Promise.all 等待所有操作完成  
    Promise.all(asyncActions)  
        .then(() => {  
            this.loadTableData(); // 所有操作完成后，加载表格数据  
            this.$message('下单成功');  
        })  
        .catch((err) => {  
            // 如果任何操作失败，显示错误消息  
            this.$message.error(err);  
        });  
},  

// order 方法  
order(row) {  
   this.orderForm.userId= this.userId
   this.orderForm.totalPrice= row.etc.goods.etc
    return orderAddOrUpdate({  
        ...this.orderForm  
    })  
    .then((result) => {  
        return orderItemAddOrUpdate({  
            orderId: result.data,  
            goodsId: row.etc.goods.id,  
            number: row.number  
        });  
    })  
    .then(() => {  
        return shopcartDeleteByIds({ ids: row.id });  
    })  
    .catch((err) => {  
        this.$message.error(err);  
    });  
},  
  
// orderOne 方法  
orderOne(row) {  
    this.$confirm('确定下单？', '提示', {  
        confirmButtonText: '确定',  
        cancelButtonText: '取消',  
        type: 'warning'  
    })  
    .then(() => {  
        return this.order(row);  
    })  
    .then(() => {  
        this.loadTableData(); // 下单成功后，加载表格数据  
        this.$message('下单成功');  
    })  
    .catch((err) => {  
        this.$message.error(err); // 如果发生错误，显示错误消息  
    });  
},


        // order(row) {
        //     this.orderForm.userId = this.userId;
        //     this.orderForm.totalPrice = row.etc.goods.etc
        //     orderAddOrUpdate({
        //         ...this.orderForm
        //     }).then(result => {
        //         orderItemAddOrUpdate({
        //             orderId: result.data,
        //             goodsId: row.etc.goods.id,
        //             number: row.number
        //         })
        //         shopcartDeleteByIds({ ids: row.id })
        //     }).catch(err => {
        //         this.$message.error(err)
        //     })
        // },
        // orderOne(row) {
        //     this.$confirm('确定下单？', '提示', {
        //         confirmButtonText: '确定',
        //         cancelButtonText: '取消',
        //         type: 'warning'
        //     }).then(() => {
        //         this.order(row).then(result => {
        //             this.loadTableData();
        //             this.$message('下单成功')
        //         }).catch(err => {
        //             this.$message.error(err)
        //         })
        //     }).catch(() => {
        //         this.loadTableData();
        //     });
        // },
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
                shopcartDeleteByIds({ ids: ids }).then(result => {
                    this.$message('删除成功')
                    this.loadTableData()
                }).catch(err => {
                    this.$message.error(err)
                })
            }).catch(() => {
                console.log("cancle")
            });
        },

        loadTableData() {
            this.userId = getStore("userId");
            shopcartList({ id: this.userId })
                .then(result => {
                    this.TableData = result.data;
                    // this.TableData = this.setText(result.data.records);
                    console.log(result)
                    //console.log(this.UserData)
                }).catch(err => {
                })
        },

        handleSelectionChange(val) {
            this.selectionIds = [];
            this.totalPrice = 0;
            this.selectionRow = [];
            val.forEach(item => {
                this.selectionIds.push(item.id)
                this.selectionRow.push(item)
                this.totalPrice += item.etc.goods.etc
            })
            console.log(this.selectionIds.join(","))

        },


    },
}

</script>
    

<style scoped>
.shopping-cart {
  max-width: 800px;
  margin: 0 auto;
}

.cart-actions {
  margin-top: 20px;
}

.total-price {
  margin-right: 10px;
}

.el-button {
  margin-right: 10px;
}
</style>