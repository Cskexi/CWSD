<template>
    <el-container class="goods-container">
      <el-header>
        <el-form ref="searchForm" :model="searchForm" :inline="true" class="formItem" label-width="80px">
          <el-col :xl="6" :md="8" :sm="24">
            <el-input placeholder="名称" v-model="searchForm.name" clearable> </el-input>
          </el-col>
  
          <el-col :xl="6 || 24" :md="8 || 24" :sm="24">
            <el-form-item>
              <div :style="{ float: 'right', overflow: 'hidden' } || {}">
                <el-button icon="el-icon-search" type="primary" @click="search">搜索</el-button>
              </div>
            </el-form-item>
          </el-col>
        </el-form>
      </el-header>
  
      <el-row class="goods-list">
    <el-col :span="6" v-for="(item, index) in tableData" :key="index" class="goods-item">
      <el-card :body-style="{ padding: '20px' }">
        <el-image @click="toGoods(item.id)" v-if="item.pic" :src="item.pic" fit="cover"></el-image>
        <el-image @click="toGoods(item.id)" v-else src="/logo.png" fit="cover"></el-image>

        <div class="goods-info">
          <div class="goods-name">{{ item.name }}</div>
          <div class="info-item">
          <span class="info-label">价格</span>
          <span>{{ item.price }}元</span>
        </div>
          <div class="store-name" @click="toStore(item.etc.store.id)">{{ item.etc.store.name }}</div>
        </div>
      </el-card>
    </el-col>
  </el-row>
  
      <el-footer>
        <div class="pagination">
          <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
            :current-page="searchForm.pageNum" :page-sizes="[2, 5, 10, 20]" :page-size="searchForm.pageSize"
            layout="total, sizes, prev, pager, next, jumper" :total="total">
          </el-pagination>
        </div>
      </el-footer>
    </el-container>
  </template>

<script>
import { goodsPage, goodsDeleteByIds, goodsPage2 } from "@/api/modules/goods"
import { setStore, removeStore, getStore } from "@/lib/storage"

export default {
    data() {
        return {
            total: 0,
            tableData: [],
            currentDate: new Date(),
            searchForm: {
                pageNum: 1,
                pageSize: 10,
                name: '',
                storeId: "",
            },
        };
    },
    mounted() {
        this.loadTableData()
        console.log("token:" + getStore("token"))
        console.log("Id:" + getStore("userId"))
    },
    methods: {
        search() {
            this.loadTableData()
        },
        loadTableData() {
            goodsPage2({
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
        toStore(val) {
            this.$router.push({
                path: "/ShoppingPage/StoreShopping",
                query: {
                    storeId: val,
                }
            })
        },
        toGoods(val) {
            this.$router.push({
                path: "/ShoppingPage/GoodsShopping",
                query: {
                    goodsId: val,
                }
            })
        },
    }
}
</script>
<style scoped>
.goods-container {
  background-color: white;
  padding: 20px;
}

.goods-list {
  margin-top: 20px;
}

.goods-item {
    flex: 0 0 calc(33.3333% - 20px); /* Adjust the width as needed, considering margins */
  margin-bottom: 20px;
}

.el-card {
    margin-left: 20px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.el-image {
  width: 100%;
}

.goods-info {
  margin-top: 10px;
  text-align: center;
}

.pagination {
  text-align: center;
}
.goods-name {
  font-size: 16px;
  font-weight: bold;
  margin-bottom: 5px;
}

.store-name {
  color: #409EFF;
  cursor: pointer;
}

.info-item {
  margin-bottom: 10px;
}

.info-label {
  font-weight: bold;
  margin-right: 5px;
}
</style>