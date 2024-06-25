
<template>
    <div class="product-info">
      <h1>商品信息页面</h1>
      <div class="product-image">
        <el-image v-if="GoodsData.pic" :src="GoodsData.pic" fit="cover" style="height: 100px;"></el-image>
        <el-image v-else src="/logo.png" fit="cover"></el-image>
      </div>
      <div class="product-details">
        <div class="product-data">
          <div class="data-item">
            <span class="label">商品名字：</span>
            <span>{{ GoodsData.name }}</span>
          </div>
          <div class="data-item">
            <span class="label">价格：</span>
            <span>{{ GoodsData.price }}</span>
          </div>
          <div class="data-item">
            <span class="label">数量：</span>
            <el-input-number v-model="num" :min="1" :max="10"></el-input-number>
          </div>
        </div>
        <button @click="addToShopcart" class="add-to-cart">加入购物车</button>
      </div>
    </div>
  </template>
  
<script>
import { shopcartAddOrUpdate } from "@/api/modules/shopcart"
import {goodsGetMessage } from "@/api/modules/goods"
import {setStore,removeStore,getStore} from "@/lib/storage"

export default {
    data() {
        return {
            goodsId:"",
            GoodsData: [],
            num:1,
            searchForm: {
                userId:"",
                goodsId:"",
                number:"",
            },
        }
    },
    mounted() {
        this.goodsId = this.$route.query.goodsId
        this.loadGoodsData()
    },
    methods: {
        loadGoodsData() {
            goodsGetMessage({
                id:this.goodsId
            }).then(result => {
                this.GoodsData = result.data;
            }).catch(err => {
                console.log("error:" + err)
            })
        },
        addToShopcart(){
            this.searchForm.goodsId=this.goodsId;
            this.searchForm.number=this.num;
            this.searchForm.userId=getStore("userId");
            shopcartAddOrUpdate({
                ...this.searchForm
            }).then(result => {
                console.log(result.data)
            }).catch(err => {
                console.log("error:" + err)
            })
        }
    }

}
</script>
  

<style scoped>
.product-info {
  max-width: 800px;
  margin: 0 auto;
}

.product-image {
  margin-bottom: 20px;
}

.product-details {
  background-color: #f4f4f4;
  padding: 20px;
  border-radius: 8px;
}

.product-id {
  display: block;
  font-size: 14px;
  color: #888;
  margin-bottom: 15px;
}

.product-data {
  display: flex;
  flex-direction: column;
}

.data-item {
  margin-bottom: 10px;
}

.label {
  font-weight: bold;
  margin-right: 5px;
}

.add-to-cart {
  padding: 10px;
  background-color: #409EFF;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
  transition: background-color 0.3s;
}

.add-to-cart:hover {
  background-color: #3080e8;
}
</style>