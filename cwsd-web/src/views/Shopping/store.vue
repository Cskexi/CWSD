<template>
    <div class="store-info">
      <h1>商店信息页面</h1>
      <span class="store-id">商店编号: {{ storeId }}</span>
      <div class="store-details">
        <h2>商店信息:</h2>
        <p>商店名: {{ StoreData.name }}</p>
      </div>
  
      <el-container>
        <el-header>
          <el-form ref="searchForm" :model="searchForm" :inline="true" class="formItem" label-width="80px">
            <el-col :xl="6" :md="8" :sm="24">
              <el-input placeholder="商品名称" v-model="searchForm.name" clearable></el-input>
            </el-col>
            <el-col :xl="6 || 24" :md="8 || 24" :sm="24">
              <el-form-item>
                <div :style="{ float: 'right', overflow: 'hidden' }">
                  <el-button icon="el-icon-search" type="primary" @click="search">搜索</el-button>
                </div>
              </el-form-item>
            </el-col>
          </el-form>
        </el-header>
  

        

        <el-row>
          <el-row :gutter="20">
            <el-col :span="6" v-for="(item, index) in GoodsData" :key="index" class="goods-item">
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
        </el-row>
  
        <el-footer>
          <div class="block">
            <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
              :current-page="searchForm.pageNum" :page-sizes="[2, 5, 10, 20]" :page-size="searchForm.pageSize"
              layout="total, sizes, prev, pager, next, jumper" :total="total">
            </el-pagination>
          </div>
        </el-footer>
      </el-container>
    </div>
  </template>
  
<script>
import { goodsPage } from "@/api/modules/goods"
import { storeGetMessage } from "@/api/modules/store"
export default {
    data() {
        return {
            total:0,
            storeId: "",
            StoreData: [],
            GoodsData: [],
            searchForm: {
                pageNum: 1,
                pageSize: 10,
                name: '',
                storeId: "",
            },
        }
    },
    mounted() {
        this.storeId = this.$route.query.storeId
        this.searchForm.storeId = this.storeId
        this.loadStoreData()
        this.loadGoodsData()
    },
    methods: {
        search() {
            this.loadGoodsData()
        },
        loadStoreData() {
            storeGetMessage({ id: this.storeId })
                .then(result => {
                    this.StoreData = result.data;
                }).catch(err => {
                    console.log("error:" + err)
                })
        },
        loadGoodsData() {
            goodsPage({
                ...this.searchForm
            }).then(result => {
                console.log(result.data)
                this.total = result.data.total;
                this.GoodsData = result.data.records;
            }).catch(err => {
                console.log("error:" + err)
            })
        },
        handleSizeChange(val) {
            this.searchForm.pageSize = val;
            this.loadGoodsData()
            console.log(`每页 ${val} 条`);
        },
        handleCurrentChange(val) {
            this.searchForm.pageNum = val;
            this.loadGoodsData()
            console.log(`当前页: ${val}`);
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
.store-info {
  max-width: 800px;
  margin: 0 auto;
}

.store-id {
  display: block;
  font-size: 14px;
  color: #888;
  margin-bottom: 15px;
}

.store-details h2 {
  margin-bottom: 10px;
}

.product-image {
  width: 100%;
  height: 150px;
  object-fit: cover;
}

.custom-row {
  margin-bottom: 20px;
}

.info-item {
  margin-bottom: 10px;
}

.info-label {
  font-weight: bold;
  margin-right: 5px;
}
</style>