package com.example.demo.userAction.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Goods.entity.Goods;
import com.example.demo.Store.entity.Store;
import com.example.demo.Store.service.StoreService;
import com.example.demo.chapter3.entity.User;
import com.example.demo.orderItems.entity.OrderItems;
import com.example.demo.products.entity.Products;
import com.example.demo.products.mapper.ProductsMapper;
import com.example.demo.products.service.ProductsService;
import com.example.demo.springboot2023.utils.DateTool;
import com.example.demo.springboot2023.utils.ConstantsUtils;
import com.example.demo.userAction.dto.UserActionDto;
import com.example.demo.userAction.mapper.UserActionMapper;
import com.example.demo.userAction.entity.UserAction;
import com.example.demo.userAction.service.UserActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
/**
* <p>
    * 用户操作记录表 服务层实现类
    * </p>
*
* @author Csk
* @since 2024-08-22
*/
@Service
public class UserActionServiceImpl extends ServiceImpl<UserActionMapper,UserAction> implements UserActionService {
    public final static int RECOMMENDER_NUM = 5;

    @Autowired
    private ProductsService productService;
    @Autowired
    private ProductsMapper productsMapper;

    @Override
    public UserAction exit(UserAction userAction) {
        QueryWrapper<UserAction> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserAction::getUserId,userAction.getUserId());
        queryWrapper.lambda().eq(UserAction::getProductId,userAction.getProductId());
        return this.getOne(queryWrapper);
    }
    @Override
    public Boolean addOrUpdate(UserAction userAction) {

        UserAction userAction2 = this.exit(userAction);

        if(userAction2==null){
            //add
            userAction.setCreateTime(DateTool.getCurrTime());
            this.save(userAction);
        }else{
            //update
            userAction2.setCreateTime(DateTool.getCurrTime());
            this.updateById(userAction2);
        }
        return true;
    }
    @Override
    public void deleteByIds(String ids) {
        List<String> listIds = new ArrayList<>();
        String[] aryIds = ids.split(",");
        for(String id: aryIds){
        UserAction userAction = this.getById(id);
        userAction.setDelFlag(ConstantsUtils.GL_DEL);
        this.updateById(userAction);
        }

    }
    @Override
    public List<UserAction> list(UserActionDto userActionDto) {
        QueryWrapper<UserAction> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(userActionDto.getProductId())) {
            queryWrapper.lambda().like(UserAction::getProductId, userActionDto.getProductId());
        }
        queryWrapper.lambda().eq(UserAction::getDelFlag,ConstantsUtils.GL_NORMAL);
        queryWrapper.lambda().orderByDesc(UserAction::getCreateTime);
        List<UserAction> list =this.list(queryWrapper);
        return this.list();
    }
    @Override
    public Page<UserAction> page(Integer pageNum,Integer pageSize) {
        Page<UserAction> page = new Page(pageNum,pageSize);
        LambdaQueryWrapper<UserAction> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UserAction::getDelFlag, ConstantsUtils.GL_NORMAL);
        lambdaQueryWrapper.orderByDesc(UserAction::getCreateTime);
        page = this.page(page, lambdaQueryWrapper);
        return page;
    }
    @Override
    public Page<Products> page2(UserActionDto userActionDto) {
        Page<UserAction> page = new Page(userActionDto.getPageNum(), userActionDto.getPageSize());
        LambdaQueryWrapper<UserAction> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(userActionDto.getUserId())) {
            lambdaQueryWrapper.eq(UserAction::getUserId, userActionDto.getUserId());
        }
        lambdaQueryWrapper.eq(UserAction::getDelFlag, ConstantsUtils.GL_NORMAL);
        lambdaQueryWrapper.orderByDesc(UserAction::getCreateTime);
        page = this.page(page, lambdaQueryWrapper);

//        for( UserAction userAction:page.getRecords()){//分页信息记录数
//            Products products = productService.getById(userAction.getProductId());
//            userAction.put("products",products);
//        }
        Page<Products> productsPage = new Page<>(userActionDto.getPageNum(), userActionDto.getPageSize());
        productsPage.setTotal(page.getTotal()); // 设置总记录数

        // 遍历原始的 UserAction 分页结果
        List<UserAction> records = page.getRecords();
        List<Products> productList = new ArrayList<>(records.size());

        for (UserAction userAction : records) {
            Products product = productService.getById(userAction.getProductId());
            productList.add(product);
        }

        productsPage.setRecords(productList); // 将查询到的 Products 实体添加到新的 Page 对象中

        return productsPage;

        //return page;
    }

    @Override
    public List<Products> recommendProduct(User user) {
        //第一步：读取数据库获取用户商品的关系数据
        List<UserAction> pu_list = getALLProductUsers();
        //第二步：对数据进行清洗。去重用户，并根据用户ids，查询每个用户的商品ids数量
        //01 获取关注关系表中所有用户的集合
        List<String> user_ids = new ArrayList<>();
        pu_list.forEach(i -> {
            user_ids.add(i.getUserId());
        });
        //02 去重
        List<String> uid_list = collectionDistinct2(user_ids);
        System.out.println("去重后的用户id列表："+uid_list);
        //03 查询每个用户关注的用户id集合，用于后续相似度计算
        Map<String, List<String>> maplist = getEveryOneFoUserList(uid_list, pu_list);
        //第三步：计算近邻指标系数，并获取key相似系数，value为用户关注列表集合
        //第四步：选择Jaccard相似系数选择结果集
        //对结果集按照jaccard系数进行排序
        //第五步：推荐器，取出剩余集合，并将结果集的用户id（排除当前用户已关注的）作为推荐结果
        List<String> recommendUserIdList = recommendGather(maplist, user.getId());

        //根据推荐的ids，查询需要推荐的用户信息
        User user2 = new User();
        //String ids = recommendUserIdList.toString().replace("[", "").replace("]","");
        String ids = getRecommenderIds(recommendUserIdList);
        System.out.println("限定后推荐id："+ids);
        user2.setIds(ids);

        //return baseMapper.recommendProductList(user2);

        List<String> idList = Arrays.asList(ids.split(","));

        // 使用MyBatis-Plus的批量查询
        return productsMapper.selectBatchIds(idList);

            //return baseMapper.recommendProductList(ids);
    }

    /**
     * 第一步：
     * 优化方法，只读取用户关注关系表，减少IO操作，剩下全部由程序在内存中执行
     * 由于只有ids集合，所以对内存占用有限，不用担心性能，建议使用map操作集合
     *
     * @fans_fo_id 被关注id
     * @fo_id 关注id
     */
    public List<UserAction> getALLProductUsers() {
        return baseMapper.getALLProductUsers();
    }
    /**
     * 第二步01：
     * 去重方法1
     * 从已有集合中查询关注人ids集合
     *
     * @fo_id 关注人id
     */
    private List<Integer> collectionDistinct1(List<Integer> list) {
        return list.stream().distinct().collect(Collectors.toList());
    }
    /**
     * 第二步01：
     * 去重方法2
     *
     * @param list
     * @return
     */
    public List<String> collectionDistinct2(List<String> list) {
        return new ArrayList<>(new HashSet<>(list));
    }
    public Map<String, List<String>> getEveryOneFoUserList(List<String> user_ids, List<UserAction> pu_List) {
        //新建HashMap存放每个用户的list数据
        //键为用户id，值为该用户关注用户的集合
        Map<String, List<String>> map = new HashMap<>();
        //从集合中循环遍历，并将用户按键值放入map中
        user_ids.forEach(uid -> {
            System.out.println("当前用户id:"+uid);
            List<String> product_list = new ArrayList<>();
            pu_List.forEach(item -> {
                //System.out.println("当前用户id:"+uid+"当前用户商品关系表中中的用户id:"+item.getUserId());
                //遍历每组
                if (uid.equals(item.getUserId())) {
                    //System.out.println("匹配用户id的项"+item.getProductId());
                    product_list.add(item.getProductId());
                }
            });
            System.out.println("用户浏览过的商品的ids集合：" + product_list);
            map.put(uid, product_list);
        });

        return map;
    }
    /**
     * 第三步，近邻相似度计算
     * 将当前用户的关注列表集合作为对照表集合，与其他用户的关注列表集合进行近邻相似计算
     * 参数1：map集合，键值，对应用户id及其关注列表集合ids
     * 参数2：当前用户id
     */
    public List<String> recommendGather(Map<String, List<String>> user_productlist, String current_user_id) {
        //取出当前用户的集合作为对照表
        List<String> currentList = new ArrayList<>();
        //将map集合里所有转换为一个set集合
        Set<Map.Entry<String, List<String>>> userSetList = user_productlist.entrySet();
        for (Map.Entry<String, List<String>> node : userSetList) {
            //取出当前用户的集合作为对照组
            if (current_user_id.equals(node.getKey()) ) {
                currentList = node.getValue();
            }
        }
        //移除map集合中当前用户的关注列表集合
        user_productlist.remove(current_user_id);
        System.out.println("当前用户的对照组集合为：" + currentList);
        System.out.println("移除当前对照组后，map集合为：" + user_productlist);
        //对照组开始与map集合循环计算近邻相似指标值（计算集合交集）,同时按指标大小进行从高到底排序
        //新建CN近邻指标结果集
        List<Integer> resultList = new ArrayList<>();
        //新建Jaccard指标结果集
        List<Double> jaccardResultList = new ArrayList<>();
        //新建我们需要的以指标为key，以关注集合为value的结果map
        //Map集合的key是不可重复的，但是我们考虑相似系数有重复的概率，因此集合我们使用IdentityHashMap实现类
        Map<Double, List<String>> jaccardResultUserFoList = new IdentityHashMap<>();
        //使用entry遍历其他用户关注列表集合
        Set<Map.Entry<String, List<String>>> othersUserSetList = user_productlist.entrySet();
        for (Map.Entry<String, List<String>> node : othersUserSetList) {
            resultList.add(intersectionForList_3(currentList, node.getValue()));
            //计算Jaccard近邻指标
            jaccardResultList.add(JaccardRecommend(currentList, node.getValue()));
            //将评分加入key，对应当前的关注集合为value
            jaccardResultUserFoList.put(JaccardRecommend(currentList, node.getValue()), node.getValue());
        }
        //返回近邻指标值集合
        System.out.println("CN近邻指标集合：" + resultList);
        System.out.println("Jaccard近邻指标集合：" + jaccardResultList);
        System.out.println("从算法最优角度，我们选择Jaccard作为我们的推荐算法");
        System.out.println("以Jaccard相似系数计算结果，得到对应的集合列表：");
        System.out.println(jaccardResultUserFoList);

        ///第四步：选择Jaccard相似系数选择结果集
        //对结果集按照jaccard系数进行排序
        Map<Double,List<String>> jaccardSortedResultUserFoList = sortMapDesc2(jaccardResultUserFoList);
        System.out.println("排序后结果：");
        System.out.println(jaccardSortedResultUserFoList);
        //第五步：推荐器
        System.out.println("推荐结果：");
        List<String> recommUserIdList = subUnionList(recommender(jaccardSortedResultUserFoList),currentList,current_user_id);
        System.out.println("当前用户推荐关注为："+recommUserIdList);

        return recommUserIdList;
    }



    /**
     * 方法3，求两个集合交集，（map）
     * 该方法执行效率最高
     *
     * @param arr1
     * @param arr2
     * @return 返回值取交集的个数
     */
    public Integer intersectionForList_3(List<String> arr1, List<String> arr2) {
//        long startTime = System.currentTimeMillis();

        List<Object> resultList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        arr1.forEach(a1 -> {
            map.put(a1 + "", a1);
        });

        arr2.forEach(a2 -> {
            Object obj = map.get(a2 + "");
            if (obj != null) {
                resultList.add(obj);
            }
        });

//        long endTime = System.currentTimeMillis();
//        System.out.println("取交集用时：" + (endTime-startTime)+"ms");
        return resultList.size();
    }

    /**
     * 计算两个集合的并集
     * 返回值取list的个数
     */
    public Integer getUnionSetForList(List<String> arr1, List<String> arr2) {
        HashSet<String> list1 = new HashSet<>(arr1);
        HashSet<String> list2 = new HashSet<>(arr2);
        list1.addAll(list2);
        Integer result = list1.size();
        return result;
    }

    /**
     * 计算两个集合的并集
     */
    public List<String> getUnionList(List<String> arr1, List<String> arr2) {
        HashSet<String> list1 = new HashSet<>(arr1);
        HashSet<String> list2 = new HashSet<>(arr2);
        list1.addAll(list2);
        return new ArrayList<>(list1);
    }

    /**
     * Jaccard相似系数 = 两个集合的交集/两个集合的并集
     */
    public Double JaccardRecommend(List<String> arr1, List<String> arr2) {
        double result = (double) intersectionForList_3(arr1, arr2) / (double) getUnionSetForList(arr1, arr2);

        return result;
    }

    /**
     * jaccard用户map集合按键降序排序
     * 同时将系数为0的移除
     * 这里使用camparator进行排序
     */
    public Map<Double, List<String>> sortMapDesc(Map<Double, List<String>> default_map) {

        //Collections.sort()方法
        List<Map.Entry<Double, List<String>>> list = new ArrayList<>(default_map.entrySet());
        Collections.sort(list, (a1, a2) -> a1.getKey().compareTo(a2.getKey()));

        Map<Double, List<String>> sortResult = new IdentityHashMap<>();
        for (Map.Entry<Double, List<String>> node : list) {
            sortResult.put(node.getKey(), node.getValue());
        }
        return sortResult;
    }

    /**
     * 去重排序方法
     * 这里使用camparator进行排序
     * 备用方法
     */
    public Map<Double, List<String>> sortMapDesc2(Map<Double, List<String>> default_map) {
        //创建一个排序比较器
        Comparator<Double> comparator = new Comparator<Double>() {
            public int compare(Double key1, Double key2) {
                return key2.compareTo(key1);
            }
        };
        //创建一个TreeMap,并使用比较器对键进行排序
        Map<Double, List<String>> sortResult = new TreeMap<>(comparator);
        sortResult.putAll(default_map);
        return sortResult;
    }

    /**
     * 推荐器
     * 根据Jaccard排序结果，将关注用户id的集合按照顺序放入新的List集合中，
     * 并排除当前用户及其关注的用户id，剩余id，就是对当前用户推荐的可关注用户
     * 这里可以设置推荐人数，作为参数，影响结果
     * 返回值为推荐的用户ids，使用它取数据库查询用户信息，发送给前端
     */
    public List<String> recommender(Map<Double, List<String>> map) {
        List<String> recomUserIDList = new ArrayList<>();
        for (Map.Entry<Double, List<String>> node : map.entrySet()) {
            //取value集合的并集
            recomUserIDList = getUnionList(recomUserIDList, node.getValue());
        }
        //返回结果
        return recomUserIDList;
    }

    /**
     * 移除结果集内包含参照组的id
     * 参数1：结果集
     * 参数2：排除集
     * 参数3：当前用户id
     * 返回值 处理后的结果集
     */
    public List<String> subUnionList(List<String> arr1, List<String> arr2,String current_user_id){
        //移除当前用户自己id
        arr1.remove(current_user_id);
        //移除参照组内用户id
        arr2.forEach(i->{
            arr1.remove(i);
        });
        return arr1;
    }

    /**
     * 设置推荐人数
     * 限定ids
     */
    public String getRecommenderIds(List<String> list){
        String result = "";
        Integer length = RECOMMENDER_NUM;
        if(length==null||length<=0){
            return "0";
        }
        if(list.size()==0){
            return "0";
        }
        if(list.size()>length){
            for(int i=0;i<length;i++){
                result+=list.get(i)+",";
            }
            result = result.substring(0, result.length()-1);
            return result;
        }else{
            for(int i=0;i<list.size();i++){
                result+=list.get(i)+",";
            }
            result = result.substring(0, result.length()-1);
            return result;
        }
    }

}