/*
 Navicat Premium Data Transfer

 Source Server         : CskMySql
 Source Server Type    : MySQL
 Source Server Version : 80034
 Source Host           : localhost:3306
 Source Schema         : cwsd_db

 Target Server Type    : MySQL
 Target Server Version : 80034
 File Encoding         : 65001

 Date: 10/09/2024 16:50:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键UUID',
  `ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '访问的ip',
  `class_path` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '访问的路径-class',
  `method_path` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '访问的路径-method',
  `create_time` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '创建时间 YYYY-MM-DD hh:mm:ss',
  `user_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '状态 1-可用 0-不可用',
  `content` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '访问的内容',
  `method` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求方式',
  `param` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求参数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '操作日志表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('13f9203675470d5142e4d3701f033123', '192.168.236.1', 'com.example.demo.chapter1.controller.Test.testAOP', '/spring/test/testAOP', '2023-12-08 15:09:48', '', 'hello模块', 'GET', '{name=1}');
INSERT INTO `sys_log` VALUES ('46f68680516d6d933d3fa5315f32dcc3', '192.168.236.1', 'com.example.demo.chapter1.controller.Test.testAOP', '/spring/test/testAOP', '2023-12-11 17:24:46', '', 'hello模块', 'GET', '{name=1}');
INSERT INTO `sys_log` VALUES ('4ab34753b4cc4a7a8cbc34365f96e5f8', '192.168.236.1', 'com.example.demo.chapter1.controller.Test.testAOP', '/spring/test/testAOP', '2023-12-08 15:10:47', '', 'hello模块', 'GET', '{name=1}');
INSERT INTO `sys_log` VALUES ('53bc8d4e35464b1cff3ab2897c50afb9', '192.168.236.1', 'com.example.demo.chapter1.controller.Test.testAOP', '/spring/test/testAOP', '2023-12-08 15:14:07', '', 'hello模块', 'GET', '{name=1}');
INSERT INTO `sys_log` VALUES ('e156aaab9847adb6e648bdaac90a5333', '192.168.236.1', 'com.example.demo.chapter1.controller.Test.testAOP', '/spring/test/testAOP', '2023-12-08 15:11:18', '', 'hello模块', 'GET', '{name=1}');

-- ----------------------------
-- Table structure for tb_cart
-- ----------------------------
DROP TABLE IF EXISTS `tb_cart`;
CREATE TABLE `tb_cart`  (
  `id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键UUID',
  `user_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id，关联user表',
  `product_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '物品id，关联goods表',
  `create_time` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建时间 YYYY-MM-DD hh:mm:ss',
  `del_flag` tinyint(1) NULL DEFAULT 1 COMMENT '是否删除 1-正常 0-已删除',
  `number` int NULL DEFAULT NULL COMMENT '物品数量',
  `selected` tinyint NULL DEFAULT 0 COMMENT '是否勾选 1-已勾选 0-未勾选',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '购物车表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_cart
-- ----------------------------

-- ----------------------------
-- Table structure for tb_categories
-- ----------------------------
DROP TABLE IF EXISTS `tb_categories`;
CREATE TABLE `tb_categories`  (
  `id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键UUID',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '类别名称',
  `create_time` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建时间 YYYY-MM-DD hh:mm:ss',
  `del_flag` tinyint(1) NULL DEFAULT 1 COMMENT '是否删除 1-正常 0-已删除',
  `parent_category_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上一级类别',
  `first_category_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '最高级类别',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '分类表 ' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_categories
-- ----------------------------
INSERT INTO `tb_categories` VALUES ('2168c742bb0d69bf4b696f46eb8461e8', '商品', '2024-07-01 10:28:53', 1, NULL, NULL);
INSERT INTO `tb_categories` VALUES ('49468015a68a3370ae065a12d2b5e6ac', '猫', '2024-06-27 09:48:46', 1, NULL, NULL);
INSERT INTO `tb_categories` VALUES ('d599dd594d67aa69c3437f0fe6447a3f', '服务', '2024-06-27 11:48:51', 1, NULL, NULL);
INSERT INTO `tb_categories` VALUES ('fcd869c75f579f168c373c7943c25f70', '狗', '2024-06-27 09:49:19', 1, NULL, NULL);

-- ----------------------------
-- Table structure for tb_dic
-- ----------------------------
DROP TABLE IF EXISTS `tb_dic`;
CREATE TABLE `tb_dic`  (
  `id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键UUID',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典类型名称',
  `value` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典类型值',
  `create_time` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建时间 YYYY-MM-DD hh:mm:ss',
  `del_flag` tinyint(1) NULL DEFAULT 1 COMMENT '是否删除 1-正常 0-已删除',
  `dictype_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属字典类型，关联tb_dictype表',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典表 ' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_dic
-- ----------------------------
INSERT INTO `tb_dic` VALUES ('04cd7158d39bcfd0cd47f128e3f9ddb9', '可用', '0', '2023-12-01 15:11:40', 1, 'e06981833346ac0a9a8f50ee14d29cf2');
INSERT INTO `tb_dic` VALUES ('0b0f4ebe2223f0bb365e260325367a0e', '狗', '1', '2024-06-25 14:59:58', 1, '290413df819ed453f17192fb67f254ac');
INSERT INTO `tb_dic` VALUES ('1234qwer', '男', '1', '2023-11-14 15:32:32', 1, '4dfdac27303040fb8fc81f9d19131f5e');
INSERT INTO `tb_dic` VALUES ('24c8f440296c3ec85559af05dec35008', '不可用', '1', '2023-12-01 15:11:50', 1, 'e06981833346ac0a9a8f50ee14d29cf2');
INSERT INTO `tb_dic` VALUES ('61435f0f90892414c71de35d6f794057', '保密', '2', '2024-06-25 14:37:06', 1, '4dfdac27303040fb8fc81f9d19131f5e');
INSERT INTO `tb_dic` VALUES ('96c758214462ff9286b1717dc9aac3d6', '游客', '-1', '2024-06-25 14:38:15', 1, '29d43d24e7dd93460d36c7b0cbc65f25');
INSERT INTO `tb_dic` VALUES ('adae2c6bdd316302789cd893025d605f', '会员', '2', '2024-06-25 14:38:07', 1, '29d43d24e7dd93460d36c7b0cbc65f25');
INSERT INTO `tb_dic` VALUES ('b59d96221342e17e3518eb15f87a2b66', '管理员', '0', '2024-06-25 14:37:43', 1, '29d43d24e7dd93460d36c7b0cbc65f25');
INSERT INTO `tb_dic` VALUES ('bdb6d1e7a0d2b21d18e077b595faee96', '商家', '1', '2024-06-25 14:37:59', 1, '29d43d24e7dd93460d36c7b0cbc65f25');
INSERT INTO `tb_dic` VALUES ('d0bf3a8ce875975e6b0d91ae949e7838', '女', '0', '2023-11-27 17:50:31', 1, '4dfdac27303040fb8fc81f9d19131f5e');
INSERT INTO `tb_dic` VALUES ('e0f772048581e908d859cfad11531ea0', '猫', '0', '2024-06-25 14:59:41', 1, '290413df819ed453f17192fb67f254ac');

-- ----------------------------
-- Table structure for tb_dictype
-- ----------------------------
DROP TABLE IF EXISTS `tb_dictype`;
CREATE TABLE `tb_dictype`  (
  `id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键UUID',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典类型名称',
  `value` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典类型值',
  `create_time` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建时间 YYYY-MM-DD hh:mm:ss',
  `del_flag` tinyint(1) NULL DEFAULT 1 COMMENT '是否删除 1-正常 0-已删除',
  `user_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属用户关联tb_user表',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典类型表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_dictype
-- ----------------------------
INSERT INTO `tb_dictype` VALUES ('290413df819ed453f17192fb67f254ac', '宠物种类', 'petKind', '2024-06-25 14:59:13', 1, NULL);
INSERT INTO `tb_dictype` VALUES ('29d43d24e7dd93460d36c7b0cbc65f25', '用户类型', 'userType', '2023-10-13 11:41:51', 1, '6099e24153376100f26d10e4457fc431');
INSERT INTO `tb_dictype` VALUES ('4dfdac27303040fb8fc81f9d19131f5e', '性别', 'sex', '2023-10-13 11:41:52', 1, '91b5a5f1ca64f421c16bed5b6b6b732b');
INSERT INTO `tb_dictype` VALUES ('e06981833346ac0a9a8f50ee14d29cf2', '状态', 'state', '2023-11-26 20:22:53', 1, '91b5a5f1ca64f421c16bed5b6b6b732b');

-- ----------------------------
-- Table structure for tb_navigation
-- ----------------------------
DROP TABLE IF EXISTS `tb_navigation`;
CREATE TABLE `tb_navigation`  (
  `id` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '主键UUID',
  `name` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '名称',
  `image_src` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '图片路径',
  `create_time` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '创建时间 YYYY-MM-DD hh:mm:ss',
  `del_flag` tinyint NULL DEFAULT 1 COMMENT '是否删除 1-正常 0-已删除',
  `open_type` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '导航链接类型',
  `navigator_url` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '导航链接路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '地址表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_navigation
-- ----------------------------
INSERT INTO `tb_navigation` VALUES ('0d8ad56a30cb5d99a63dbc2a36087ee0', '分类2', '../../static/tab_icons/cate.png', '2024-06-30 20:02:07', 1, NULL, NULL);
INSERT INTO `tb_navigation` VALUES ('140b9ff908f80e8107e027461c2aa200', '分类', '../../static/tab_icons/cate.png', '2024-06-30 20:02:20', 1, 'switchTab', '/pages/category/main');
INSERT INTO `tb_navigation` VALUES ('447c1a78269c9af092262e917fe26fce', '分类3', '../../static/tab_icons/cate.png', '2024-06-30 20:02:05', 1, NULL, NULL);
INSERT INTO `tb_navigation` VALUES ('57159ce1ebe3fd7e8dc48086efea7304', '分类4', '../../static/tab_icons/cate.png', '2024-06-30 20:02:02', 1, NULL, NULL);

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order`  (
  `id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键UUID',
  `no` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '订单编号',
  `user_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单创建者，关联user表',
  `create_time` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建时间 YYYY-MM-DD hh:mm:ss',
  `del_flag` tinyint(1) NULL DEFAULT 1 COMMENT '是否删除 1-正常 0-已删除',
  `total_price` double(10, 2) NULL DEFAULT NULL COMMENT '订单总价',
  `status` int NULL DEFAULT 0 COMMENT '订单状态',
  `refund` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '退单理由',
  `refund_admin` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '管理员的退单审核结果',
  `cancle_reason` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单取消原因\r\n',
  `comment` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评价',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_order
-- ----------------------------
INSERT INTO `tb_order` VALUES ('41c291bf0eddf6bce8543691e30555df', 'no2023122420291704urvi', '34544f9ebc675eb4a1e70f489ee8ce4d', '2023-12-24 20:29:44', 1, 556.00, 1, '', NULL, '', NULL);
INSERT INTO `tb_order` VALUES ('61c9c4672f68ca43330cbc45d28ef361', 'no2023122617032805vgpp', '34544f9ebc675eb4a1e70f489ee8ce4d', '2023-12-26 17:03:10', 1, 356.00, 4, '', NULL, '', '123');
INSERT INTO `tb_order` VALUES ('7392dc211f8b23fa0a3bd893d3570aa3', 'no2023122420298485ofqk', '34544f9ebc675eb4a1e70f489ee8ce4d', '2023-12-24 20:29:44', 1, 396.00, 0, NULL, NULL, NULL, NULL);
INSERT INTO `tb_order` VALUES ('7f4c7a0e1c51bda077f1f5b7d0f38c40', 'no2023122420021262mesy', '34544f9ebc675eb4a1e70f489ee8ce4d', '2023-12-24 20:02:16', 1, 99.00, 4, '', NULL, '', '1111');
INSERT INTO `tb_order` VALUES ('c7a7b68d583b1cb98286c1b751ae7620', 'no2023122420307675jsrd', '34544f9ebc675eb4a1e70f489ee8ce4d', '2023-12-24 20:30:32', 1, 239.60, 4, '', NULL, '', '123');

-- ----------------------------
-- Table structure for tb_order_items
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_items`;
CREATE TABLE `tb_order_items`  (
  `id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键UUID',
  `orders_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编号id，关联order表',
  `product_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '物品id，关联products表',
  `create_time` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建时间 YYYY-MM-DD hh:mm:ss',
  `del_flag` tinyint(1) NULL DEFAULT 1 COMMENT '是否删除 1-正常 0-已删除',
  `number` int NULL DEFAULT NULL COMMENT '物品数量',
  `price_at_order` int NULL DEFAULT NULL COMMENT '订单时的价格',
  `refund_reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '退单理由',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '订单状态 0-未支付 1-已支付 2-已发货 -1取消订单 -2申请退单 -3退单成功',
  `user_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `no` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址id',
  `store_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商店id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '子订单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_order_items
-- ----------------------------
INSERT INTO `tb_order_items` VALUES ('0177ff2a93f1e92714c18024d5efafcc', NULL, '791c9aaeed17eccdb76923468a0d5490', '2024-09-10 16:04:34', 1, 6, 16, NULL, -1, '2e52970890a97024c6c01990fb040e4a', 'no2024091016042658rxuz', '广东省广州市海珠区新港中路397号', '4d4cea8b200c5e80ce93384bbc6697ed');
INSERT INTO `tb_order_items` VALUES ('033b4dc0356319c50d4f9d8c04cb1325', NULL, '32114b3f10227efd034e3890322a0769', '2024-07-11 09:47:10', 1, 4, 39, NULL, 0, '787aa81904875242219d0ee5de16c3b5', 'no2024071109471627zpho', '福建省厦门市集美区侨英街道厦门理工学院学生公寓·东区(天马路西)', '4d4cea8b200c5e80ce93384bbc6697ed');
INSERT INTO `tb_order_items` VALUES ('0ba0239e0427468ef23b0cce74a041a5', NULL, '5dd4e1f3092dc6bad70cc2e4f90617f7', '2024-08-22 14:43:31', 1, 1, 2, NULL, 0, 'e83229e6c2dd710f1c900bacdd817670', 'no2024082214434254jjzw', '广东省广州市海珠区新港中路397号', '4d4cea8b200c5e80ce93384bbc6697ed');
INSERT INTO `tb_order_items` VALUES ('130b8192d05f48f3b20134d6fbb63f28', NULL, '68a20cd1cc5999a76c6d5769efa500fa', '2024-09-10 15:48:11', 1, 2, 18, NULL, 2, 'e3610d06919a08c8b63ef2abca52042c', 'no2024091015487979ugio', '广东省广州市海珠区新港中路397号', '4d4cea8b200c5e80ce93384bbc6697ed');
INSERT INTO `tb_order_items` VALUES ('1f862cba4dc19edd500b99aa25f70b4f', NULL, '68a20cd1cc5999a76c6d5769efa500fa', '2024-09-10 15:50:35', 1, 100, 18, NULL, 0, 'a81288f044481b989d58d9ba3edb05d2', 'no2024091015506993syko', '广东省广州市海珠区新港中路397号', '4d4cea8b200c5e80ce93384bbc6697ed');
INSERT INTO `tb_order_items` VALUES ('2064939ba213564f88b466ead7da27b3', NULL, '32114b3f10227efd034e3890322a0769', '2024-09-10 14:45:19', 1, 1, 39, '99', 2, 'e3610d06919a08c8b63ef2abca52042c', 'no2024091014451863didi', '广东省广州市海珠区新港中路397号', '4d4cea8b200c5e80ce93384bbc6697ed');
INSERT INTO `tb_order_items` VALUES ('2562a28d011fda61ea8ba7e569e6a8f3', NULL, '68a20cd1cc5999a76c6d5769efa500fa', '2024-09-10 14:59:26', 1, 2, 18, NULL, 0, 'a81288f044481b989d58d9ba3edb05d2', 'no2024091014597118vtaz', '广东省广州市海珠区新港中路397号', '4d4cea8b200c5e80ce93384bbc6697ed');
INSERT INTO `tb_order_items` VALUES ('2562f7fdd119c3ef834abb44620cf8ac', NULL, '791c9aaeed17eccdb76923468a0d5490', '2024-09-10 14:59:35', 1, 1, 16, '22', -4, 'e3610d06919a08c8b63ef2abca52042c', 'no2024091014591408iroe', '广东省广州市海珠区新港中路397号', '4d4cea8b200c5e80ce93384bbc6697ed');
INSERT INTO `tb_order_items` VALUES ('278da678ead0b08f3d11b5476e9183e0', NULL, '07905d577bb86e9acfdfca4ecc853ba8', '2024-07-11 09:23:29', 1, 1, 9, NULL, 1, '220d3c190170386d0985be08754ebf45', 'no2024071109234447kqmi', '福建省厦门市集美区侨英街道厦门理工学院学生公寓·东区(天马路西)', 'be23fb1551c1977e6208c351691d315f');
INSERT INTO `tb_order_items` VALUES ('435689af988de70fb7a2b2aad7ef0169', NULL, '9b9eb6c6456b3a2a824b5cc555ba6a2a', '2024-07-11 08:24:33', 1, 1, 120, NULL, 0, '787aa81904875242219d0ee5de16c3b5', 'no2024071108246435zmog', '广东省广州市海珠区新港中路397号', '');
INSERT INTO `tb_order_items` VALUES ('4543d133cfd9b6508b9d0d92c3243be0', NULL, '791c9aaeed17eccdb76923468a0d5490', '2024-09-10 16:05:28', 1, 4, 16, NULL, -1, '2e52970890a97024c6c01990fb040e4a', 'no2024091016051713mefh', '广东省广州市海珠区新港中路397号', '4d4cea8b200c5e80ce93384bbc6697ed');
INSERT INTO `tb_order_items` VALUES ('480ce1929c6b8735a4d8b588d40451b0', NULL, '81f84f07d7d3dd71345cf4dfdced72f4', '2024-09-10 14:59:48', 1, 1, 3000, NULL, 0, 'a81288f044481b989d58d9ba3edb05d2', 'no2024091014593231lwkj', '广东省广州市海珠区新港中路397号', '50bc1bbdb07a47b3f0aff3587822b293');
INSERT INTO `tb_order_items` VALUES ('4953082390b1bfb7351c99b91b99772b', NULL, '68a20cd1cc5999a76c6d5769efa500fa', '2024-09-10 16:27:21', 1, 2, 18, NULL, 2, '2e52970890a97024c6c01990fb040e4a', 'no2024091016271701eaqh', '广东省广州市海珠区新港中路397号', '4d4cea8b200c5e80ce93384bbc6697ed');
INSERT INTO `tb_order_items` VALUES ('55bcef29f062dfbcac5fc8c8da7bd04d', NULL, '32114b3f10227efd034e3890322a0769', '2024-09-10 16:14:25', 1, 3, 39, NULL, -1, '2e52970890a97024c6c01990fb040e4a', 'no2024091016145282okmv', '广东省广州市海珠区新港中路397号', '4d4cea8b200c5e80ce93384bbc6697ed');
INSERT INTO `tb_order_items` VALUES ('6121e84062c810de43a9e24e7d010804', NULL, '5dd4e1f3092dc6bad70cc2e4f90617f7', '2024-07-11 01:13:58', 1, 1, 2, '123', -5, '787aa81904875242219d0ee5de16c3b5', 'no2024071101131062vjyz', '广东省广州市海珠区新港中路397号', '4d4cea8b200c5e80ce93384bbc6697ed');
INSERT INTO `tb_order_items` VALUES ('6965016eff7b7806d933b916d57153ba', NULL, '791c9aaeed17eccdb76923468a0d5490', '2024-09-10 15:57:12', 1, 5, 16, NULL, 0, 'e3610d06919a08c8b63ef2abca52042c', 'no2024091015571913wrxi', '广东省广州市海珠区新港中路397号', '4d4cea8b200c5e80ce93384bbc6697ed');
INSERT INTO `tb_order_items` VALUES ('6b11242cf8aa1a41968f2a1109102ebd', NULL, '68a20cd1cc5999a76c6d5769efa500fa', '2024-09-08 18:59:23', 1, 4, 18, NULL, 3, '715d5ee7b923a84db8be46f62909f0d1', 'no2024090818596740agog', '广东省广州市海珠区新港中路397号', '4d4cea8b200c5e80ce93384bbc6697ed');
INSERT INTO `tb_order_items` VALUES ('6d9b34a2ebfa8823a0dc6fbf89ffc9d2', NULL, '791c9aaeed17eccdb76923468a0d5490', '2024-09-10 16:06:35', 1, 3, 16, NULL, -1, '2e52970890a97024c6c01990fb040e4a', 'no2024091016069057magm', '广东省广州市海珠区新港中路397号', '4d4cea8b200c5e80ce93384bbc6697ed');
INSERT INTO `tb_order_items` VALUES ('706b6d9771353b99efa12226d4c596e4', NULL, '07905d577bb86e9acfdfca4ecc853ba8', '2024-08-22 14:39:20', 1, 1, 9, NULL, 0, 'f31123035b4dc0dec05e7f43612033f7', 'no2024082214398950xpma', '广东省广州市海珠区新港中路397号', 'be23fb1551c1977e6208c351691d315f');
INSERT INTO `tb_order_items` VALUES ('730042f154009f36175c6d49bcf6f78a', NULL, '791c9aaeed17eccdb76923468a0d5490', '2024-09-10 16:02:43', 1, 6, 16, NULL, -1, '2e52970890a97024c6c01990fb040e4a', 'no2024091016022263xwui', '广东省广州市海珠区新港中路397号', '4d4cea8b200c5e80ce93384bbc6697ed');
INSERT INTO `tb_order_items` VALUES ('73e7f8e1f6c96d571d4a61347dec343a', NULL, '32114b3f10227efd034e3890322a0769', '2024-09-10 16:28:56', 1, 2, 39, NULL, 0, '2e52970890a97024c6c01990fb040e4a', 'no2024091016284009dtfh', '广东省广州市海珠区新港中路397号', '4d4cea8b200c5e80ce93384bbc6697ed');
INSERT INTO `tb_order_items` VALUES ('7f685549c2f31f12dda39ad32b725681', NULL, '5dd4e1f3092dc6bad70cc2e4f90617f7', '2024-08-22 21:38:57', 1, 2, 3, '123大师傅打发·1法大师傅的爱的方式发送发的发法大师傅撒法发', -4, 'e83229e6c2dd710f1c900bacdd817670', 'no2024082221386457egap', '广东省广州市海珠区新港中路397号', '4d4cea8b200c5e80ce93384bbc6697ed');
INSERT INTO `tb_order_items` VALUES ('814140c9d343f6bbc4fedd6b15fb0427', NULL, '791c9aaeed17eccdb76923468a0d5490', '2024-09-10 16:05:59', 1, 4, 16, NULL, 2, '2e52970890a97024c6c01990fb040e4a', 'no2024091016053681xhbe', '广东省广州市海珠区新港中路397号', '4d4cea8b200c5e80ce93384bbc6697ed');
INSERT INTO `tb_order_items` VALUES ('97410812846d9a70660ea5d453fc5b3f', NULL, '791c9aaeed17eccdb76923468a0d5490', '2024-09-10 16:24:38', 1, 3, 16, '不要了', 2, '2e52970890a97024c6c01990fb040e4a', 'no2024091016242806noiu', '广东省广州市海珠区新港中路397号', '4d4cea8b200c5e80ce93384bbc6697ed');
INSERT INTO `tb_order_items` VALUES ('98b789d81bc05b85e8be72b1dd7ac523', NULL, '32114b3f10227efd034e3890322a0769', '2024-09-10 16:39:02', 1, 2, 39, NULL, 0, '2e52970890a97024c6c01990fb040e4a', 'no2024091016394268tcjd', '广东省广州市海珠区新港中路397号', '4d4cea8b200c5e80ce93384bbc6697ed');
INSERT INTO `tb_order_items` VALUES ('9e653a0597929e4e1dea1f16eb3cfc83', NULL, '32114b3f10227efd034e3890322a0769', '2024-09-10 16:13:10', 1, 2, 39, '不想要了', -4, '2e52970890a97024c6c01990fb040e4a', 'no2024091016135401pcfy', '广东省广州市海珠区新港中路397号', '4d4cea8b200c5e80ce93384bbc6697ed');
INSERT INTO `tb_order_items` VALUES ('ae2867a31cf31a98efa0caf3bed493ce', NULL, '5dd4e1f3092dc6bad70cc2e4f90617f7', '2024-09-10 14:59:26', 1, 42, 2, NULL, 0, 'a81288f044481b989d58d9ba3edb05d2', 'no2024091014591572lmim', '广东省广州市海珠区新港中路397号', NULL);
INSERT INTO `tb_order_items` VALUES ('b1e588a8c9865e9424c518286ecbb9b2', NULL, '7418d1c2ae4c2aa44231ea9652a02c08', '2024-09-10 15:08:56', 1, 1, 1999, NULL, -1, 'e3610d06919a08c8b63ef2abca52042c', 'no2024091015088633bzxz', '广东省广州市海珠区新港中路397号', '8de6dc690598aa97187579cb8b655fa9');
INSERT INTO `tb_order_items` VALUES ('b549cdb420b1afac722f5fa6d36dddff', NULL, '68a20cd1cc5999a76c6d5769efa500fa', '2024-09-10 16:32:42', 1, 62, 18, NULL, 0, 'a81288f044481b989d58d9ba3edb05d2', 'no2024091016321289zqkk', '广东省广州市海珠区新港中路397号', NULL);
INSERT INTO `tb_order_items` VALUES ('b895df0fe3b29b51505a08f05cbe84f9', NULL, '68a20cd1cc5999a76c6d5769efa500fa', '2024-09-10 15:16:44', 1, 1, 18, '1212', -5, 'e3610d06919a08c8b63ef2abca52042c', 'no2024091015168691yrxm', '广东省广州市海珠区新港中路397号', '4d4cea8b200c5e80ce93384bbc6697ed');
INSERT INTO `tb_order_items` VALUES ('bced83c576fe1ff272f5fac90c6e6085', NULL, 'acef5b0cc70e39138d584897ecede7b8', '2024-09-10 15:13:52', 1, 1, 120, NULL, -1, 'e3610d06919a08c8b63ef2abca52042c', 'no2024091015136660rkzj', '广东省广州市海珠区新港中路397号', 'be23fb1551c1977e6208c351691d315f');
INSERT INTO `tb_order_items` VALUES ('d60e61934a657ea08e64fdf108737629', NULL, '791c9aaeed17eccdb76923468a0d5490', '2024-09-10 15:58:26', 1, 6, 16, NULL, -1, '2e52970890a97024c6c01990fb040e4a', 'no2024091015582690movo', '广东省广州市海珠区新港中路397号', '4d4cea8b200c5e80ce93384bbc6697ed');
INSERT INTO `tb_order_items` VALUES ('dfd863068c7aafdd45c6cf927104cfe8', NULL, '68a20cd1cc5999a76c6d5769efa500fa', '2024-09-10 16:07:41', 1, 1, 18, '送你了', -5, '2e52970890a97024c6c01990fb040e4a', 'no2024091016079835mywe', '广东省广州市海珠区新港中路397号', '4d4cea8b200c5e80ce93384bbc6697ed');
INSERT INTO `tb_order_items` VALUES ('e8543aa9f3cb7bc896c9cc5d1fe6942b', NULL, '791c9aaeed17eccdb76923468a0d5490', '2024-09-10 16:03:11', 1, 6, 16, NULL, -1, '2e52970890a97024c6c01990fb040e4a', 'no2024091016038778rhvm', '广东省广州市海珠区新港中路397号', '4d4cea8b200c5e80ce93384bbc6697ed');
INSERT INTO `tb_order_items` VALUES ('fcac47c5ceadb1ff43e25658d3bb6ca4', NULL, '32114b3f10227efd034e3890322a0769', '2024-09-10 16:35:46', 1, 1, 39, NULL, 0, 'a81288f044481b989d58d9ba3edb05d2', 'no2024091016358794kbxt', '广东省广州市海珠区新港中路397号', '4d4cea8b200c5e80ce93384bbc6697ed');
INSERT INTO `tb_order_items` VALUES ('fd4a8d68c0185b55cb1aace5b506d4a4', NULL, '07905d577bb86e9acfdfca4ecc853ba8', '2024-07-11 09:46:33', 1, 16, 9, NULL, 1, '787aa81904875242219d0ee5de16c3b5', 'no2024071109463719mgis', '福建省厦门市集美区侨英街道厦门理工学院学生公寓·东区(天马路西)', 'be23fb1551c1977e6208c351691d315f');

-- ----------------------------
-- Table structure for tb_products
-- ----------------------------
DROP TABLE IF EXISTS `tb_products`;
CREATE TABLE `tb_products`  (
  `id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键UUID',
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品名字',
  `pic` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品图片',
  `create_time` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建时间 YYYY-MM-DD hh:mm:ss',
  `del_flag` tinyint(1) NULL DEFAULT 1 COMMENT '是否删除 1-正常 0-已删除',
  `store_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属商店id,关联tb_store表',
  `price` double(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `inventory` int NULL DEFAULT 0 COMMENT '库存',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态 1-正常 2-售罄',
  `category_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品类别表ID',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品描述',
  `sold_quantity` int NULL DEFAULT 0 COMMENT '已出售数量',
  `discount` double(11, 2) NULL DEFAULT 1.00 COMMENT '折扣',
  `view` int NULL DEFAULT 0 COMMENT '浏览次数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_products
-- ----------------------------
INSERT INTO `tb_products` VALUES ('07905d577bb86e9acfdfca4ecc853ba8', '猫咪舔舔乐', '/img/products/07905d577bb86e9acfdfca4ecc853ba8/屏幕截图 2024-07-11 003327.png', '2024-07-11 00:33:19', 1, 'be23fb1551c1977e6208c351691d315f', 9.90, 12, 1, '2168c742bb0d69bf4b696f46eb8461e8', '网红爆款猫咪薄荷球卡通可爱风猫咪舔舔乐旋转球棒棒糖零食猫玩具', 0, 1.00, 2);
INSERT INTO `tb_products` VALUES ('0e5de64009e8b081789fc6123f91d5f6', '边境牧羊犬', '/img/products/0e5de64009e8b081789fc6123f91d5f6/边牧.JPG', '2024-07-11 00:27:29', 1, '8de6dc690598aa97187579cb8b655fa9', 2500.00, 1, 1, 'fcd869c75f579f168c373c7943c25f70', '最聪明的狗狗', 0, 0.95, 2);
INSERT INTO `tb_products` VALUES ('136f53f82c9261f8b30fc437017a6cec', '宠物冰窝', '/img/products/136f53f82c9261f8b30fc437017a6cec/屏幕截图 2024-07-11 002035.png', '2024-07-11 00:19:04', 1, 'be23fb1551c1977e6208c351691d315f', 65.00, 12, 1, '2168c742bb0d69bf4b696f46eb8461e8', '猫空调房DIY宠物冰窝夏季猫咪降温神器防抓加厚可折叠防暑猫咪', 0, 1.00, 2);
INSERT INTO `tb_products` VALUES ('1efb7c7c073ea7a42876a7f3847e2d36', '宠物除毛器', '/img/products/1efb7c7c073ea7a42876a7f3847e2d36/屏幕截图 2024-07-11 002954.png', '2024-07-11 00:31:09', 1, 'be23fb1551c1977e6208c351691d315f', 12.00, 96, 1, '2168c742bb0d69bf4b696f46eb8461e8', '小蜜蜂粘毛器宠物除毛器滚筒式粘毛器毛发清理器粘毛刷猫咪衣物除毛', 0, 1.00, 1);
INSERT INTO `tb_products` VALUES ('28d28d5b3f11db69e67e4c4b3d2ff21b', '测试商品001', NULL, '2024-09-10 16:41:55', 1, '9023b9dac223cc769a056e7d9ce34b35', 1999.00, 1, 1, '49468015a68a3370ae065a12d2b5e6ac', '测试专用', 0, 1.00, 0);
INSERT INTO `tb_products` VALUES ('32114b3f10227efd034e3890322a0769', '营养猫粮', '/img/products/32114b3f10227efd034e3890322a0769/猫粮2.jpg', '2024-07-11 00:00:02', 1, '4d4cea8b200c5e80ce93384bbc6697ed', 39.90, 438, 1, '2168c742bb0d69bf4b696f46eb8461e8', '防止掉毛', 5, 1.00, 0);
INSERT INTO `tb_products` VALUES ('3fe09b8ca3651ab8fa89c9c338eb4dcb', '测试商品', NULL, '2024-09-10 15:28:57', 1, 'cf1a0eb890f2f52dddd55c48f3064c6a', 1000.00, 1, 1, '49468015a68a3370ae065a12d2b5e6ac', '测试商品，请勿拍下', 0, 1.00, 0);
INSERT INTO `tb_products` VALUES ('5dd4e1f3092dc6bad70cc2e4f90617f7', '逗猫棒', '/img/products/5dd4e1f3092dc6bad70cc2e4f90617f7/逗猫棒.jpg', '2024-07-11 00:23:49', 1, '4d4cea8b200c5e80ce93384bbc6697ed', 2.80, 59, 2, '2168c742bb0d69bf4b696f46eb8461e8', '逗猫神器', 22, 1.00, 2);
INSERT INTO `tb_products` VALUES ('68a20cd1cc5999a76c6d5769efa500fa', '通用型猫粮', '/img/products/68a20cd1cc5999a76c6d5769efa500fa/猫粮1.jpg', '2024-07-10 23:50:43', 1, '4d4cea8b200c5e80ce93384bbc6697ed', 18.80, 0, 2, '2168c742bb0d69bf4b696f46eb8461e8', '大小猫都能吃', 10, 1.00, 2);
INSERT INTO `tb_products` VALUES ('7418d1c2ae4c2aa44231ea9652a02c08', '金毛', '/img/products/undefined/金毛.JPG', '2024-07-11 00:27:01', 1, '8de6dc690598aa97187579cb8b655fa9', 1999.00, 0, 2, 'fcd869c75f579f168c373c7943c25f70', '小天使', 0, 0.99, 0);
INSERT INTO `tb_products` VALUES ('791c9aaeed17eccdb76923468a0d5490', '牛肉味冻干狗粮', '/img/products/791c9aaeed17eccdb76923468a0d5490/狗粮2.jpg', '2024-07-10 23:56:21', 1, '4d4cea8b200c5e80ce93384bbc6697ed', 16.99, 802, 1, '2168c742bb0d69bf4b696f46eb8461e8', '高钙美毛', 23, 0.85, 0);
INSERT INTO `tb_products` VALUES ('81f84f07d7d3dd71345cf4dfdced72f4', '金渐层矮脚妹妹', '/img/products/81f84f07d7d3dd71345cf4dfdced72f4/IMG_9316(20240710-234829).JPG', '2024-07-10 23:47:36', 1, '50bc1bbdb07a47b3f0aff3587822b293', 3000.00, 0, 2, '49468015a68a3370ae065a12d2b5e6ac', '金渐层矮脚猫咪', 0, 0.96, 2);
INSERT INTO `tb_products` VALUES ('a65c57138b60fc590c7de7c08990e68a', '100支装猫条', '/img/products/a65c57138b60fc590c7de7c08990e68a/猫条1.jpg', '2024-07-11 00:08:21', 0, '4d4cea8b200c5e80ce93384bbc6697ed', 25.69, 784, 1, '2168c742bb0d69bf4b696f46eb8461e8', '经典鲜肉 小鱼干 ', 0, 1.00, 0);
INSERT INTO `tb_products` VALUES ('acef5b0cc70e39138d584897ecede7b8', '半自动猫砂盆', '/img/products/undefined/屏幕截图 2024-07-11 002212.png', '2024-07-11 00:23:09', 1, 'be23fb1551c1977e6208c351691d315f', 120.50, 62, 1, '2168c742bb0d69bf4b696f46eb8461e8', '半自动猫砂盆全封闭半智能猫厕所超大号隔臭防带砂猫屎盆用品', 0, 1.00, 0);
INSERT INTO `tb_products` VALUES ('b4599dd2c6d4b2120e0388c2a72120b8', '60只装猫条', '/img/products/b4599dd2c6d4b2120e0388c2a72120b8/猫条2.jpg', '2024-07-11 00:21:10', 1, '4d4cea8b200c5e80ce93384bbc6697ed', 16.68, 786, 1, '2168c742bb0d69bf4b696f46eb8461e8', '真的太香了，，买买买', 0, 1.00, 0);
INSERT INTO `tb_products` VALUES ('b56f09e5b52864c814288df7dbb692e2', '金毛', '/img/products/undefined/金毛.JPG', '2024-07-11 00:27:00', 0, '8de6dc690598aa97187579cb8b655fa9', 1999.00, 1, 1, 'fcd869c75f579f168c373c7943c25f70', '小天使', 0, 0.99, 0);
INSERT INTO `tb_products` VALUES ('bbe89d931f5c7bb14b5316c6ee909d1b', '缅因猫（灰色）', '/img/products/bbe89d931f5c7bb14b5316c6ee909d1b/IMG_9317(20240710-235047).JPG', '2024-07-10 23:51:37', 1, '50bc1bbdb07a47b3f0aff3587822b293', 3500.00, 1, 1, '49468015a68a3370ae065a12d2b5e6ac', '灰白色的缅因猫', 0, 0.99, 0);
INSERT INTO `tb_products` VALUES ('c1694480c80e91a22e0d8689ffb5e882', '100只装猫条', '/img/products/c1694480c80e91a22e0d8689ffb5e882/猫条1.jpg', '2024-07-11 00:16:15', 1, '4d4cea8b200c5e80ce93384bbc6697ed', 25.69, 789, 1, '2168c742bb0d69bf4b696f46eb8461e8', '鲜肉小鱼干', 0, 1.00, 1);
INSERT INTO `tb_products` VALUES ('cda10c7d3a08a44d5412adc5d290f9a2', '存皓狗粮 5kg 狗狗都爱吃', '/img/products/cda10c7d3a08a44d5412adc5d290f9a2/狗粮1.jpg', '2024-07-10 23:43:31', 1, '4d4cea8b200c5e80ce93384bbc6697ed', 38.80, 999, 1, '2168c742bb0d69bf4b696f46eb8461e8', '万人种草，买买买', 0, 0.85, 0);
INSERT INTO `tb_products` VALUES ('eaa7d2a128139562ad97e3399c4c7ecd', '美国短毛猫', '/img/products/eaa7d2a128139562ad97e3399c4c7ecd/IMG_9318(20240710-235308).JPG', '2024-07-10 23:53:31', 1, '50bc1bbdb07a47b3f0aff3587822b293', 1999.00, 1, 1, '49468015a68a3370ae065a12d2b5e6ac', '美国佬', 0, 0.95, 1);
INSERT INTO `tb_products` VALUES ('f38810ccc6ad4a3b6648cf2ce45492b9', '缅因猫（白金）', '/img/products/f38810ccc6ad4a3b6648cf2ce45492b9/缅因猫.JPG', '2024-07-10 23:57:48', 1, '50bc1bbdb07a47b3f0aff3587822b293', 3699.00, 1, 1, '49468015a68a3370ae065a12d2b5e6ac', '白金色猫咪', 0, 1.00, 2);

-- ----------------------------
-- Table structure for tb_reviews
-- ----------------------------
DROP TABLE IF EXISTS `tb_reviews`;
CREATE TABLE `tb_reviews`  (
  `id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键UUID',
  `user_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id，关联user表',
  `product_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '物品id，关联goods表',
  `create_time` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建时间 YYYY-MM-DD hh:mm:ss',
  `del_flag` tinyint(1) NULL DEFAULT 1 COMMENT '是否删除 1-正常 0-已删除',
  `reviews` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评论',
  `rating` tinyint(1) NULL DEFAULT NULL COMMENT '评分1-5',
  `order_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单id,关联order_items表',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '评论表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_reviews
-- ----------------------------
INSERT INTO `tb_reviews` VALUES ('3439f5577729d3c1bbe59c7c4db5be25', 'e83229e6c2dd710f1c900bacdd817670', '5dd4e1f3092dc6bad70cc2e4f90617f7', '2024-08-22 15:58:55', 1, '2', 4, '0ba0239e0427468ef23b0cce74a041a5');
INSERT INTO `tb_reviews` VALUES ('35a7743822b38693835d2f4191d691a0', 'e83229e6c2dd710f1c900bacdd817670', '5dd4e1f3092dc6bad70cc2e4f90617f7', '2024-08-22 16:02:39', 1, '3', 3, '0ba0239e0427468ef23b0cce74a041a5');
INSERT INTO `tb_reviews` VALUES ('39337279f7c64d953352c88d9f4fde12', 'e83229e6c2dd710f1c900bacdd817670', '5dd4e1f3092dc6bad70cc2e4f90617f7', '2024-08-22 15:57:11', 1, '3', 3, '0ba0239e0427468ef23b0cce74a041a5');
INSERT INTO `tb_reviews` VALUES ('4c67332d307ca45a3d7f0e289d80f421', 'e3610d06919a08c8b63ef2abca52042c', '68a20cd1cc5999a76c6d5769efa500fa', '2024-09-10 15:32:18', 1, 'q', 3, 'b895df0fe3b29b51505a08f05cbe84f9');
INSERT INTO `tb_reviews` VALUES ('a2923efd51d81ab1bef64cd3d6f7e99b', '2e52970890a97024c6c01990fb040e4a', '68a20cd1cc5999a76c6d5769efa500fa', '2024-09-10 16:11:03', 1, '好东西', 5, 'dfd863068c7aafdd45c6cf927104cfe8');
INSERT INTO `tb_reviews` VALUES ('b8f024d060ab39e9091e3366b68771d8', 'e83229e6c2dd710f1c900bacdd817670', '5dd4e1f3092dc6bad70cc2e4f90617f7', '2024-08-22 16:00:41', 1, '2', 4, '0ba0239e0427468ef23b0cce74a041a5');
INSERT INTO `tb_reviews` VALUES ('d790011f6d6a5f6347d0586e0482ff63', 'e83229e6c2dd710f1c900bacdd817670', '5dd4e1f3092dc6bad70cc2e4f90617f7', '2024-08-22 15:58:06', 1, '5', 5, '0ba0239e0427468ef23b0cce74a041a5');

-- ----------------------------
-- Table structure for tb_store
-- ----------------------------
DROP TABLE IF EXISTS `tb_store`;
CREATE TABLE `tb_store`  (
  `id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键UUID',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商店名称',
  `address` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商店地址',
  `create_time` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建时间 YYYY-MM-DD hh:mm:ss',
  `del_flag` tinyint(1) NULL DEFAULT 1 COMMENT '是否删除 1-正常 0-已删除',
  `user_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商店所属商家,所属用户关联tb_user表',
  `telephone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `location_accuracy` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '位置精度',
  `info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '简介',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商店表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_store
-- ----------------------------
INSERT INTO `tb_store` VALUES ('4d4cea8b200c5e80ce93384bbc6697ed', '优宠之家', '福建省厦门市翔安区长远路', '2024-07-10 23:39:42', 1, '787aa81904875242219d0ee5de16c3b5', '12345678910', NULL, '这个小主很懒');
INSERT INTO `tb_store` VALUES ('50bc1bbdb07a47b3f0aff3587822b293', '猫语柠夏', '福建省厦门市同安区双溪大道799号', '2024-07-10 23:46:00', 1, '33bf8f09a3115b1669ef82d06a13b873', '18726356859', NULL, '自家繁殖的猫猫，全国发货');
INSERT INTO `tb_store` VALUES ('667c62c531473d193b2333d876cad900', '', 'asd', '2024-09-10 15:44:30', 0, '787aa81904875242219d0ee5de16c3b5', '1231', NULL, '123');
INSERT INTO `tb_store` VALUES ('8de6dc690598aa97187579cb8b655fa9', '狗语柠夏', '福建省厦门市同安区双溪大道800号', '2024-07-11 00:26:21', 1, '33bf8f09a3115b1669ef82d06a13b873', '18726390859', NULL, '自家繁殖的狗狗，全国发货');
INSERT INTO `tb_store` VALUES ('9023b9dac223cc769a056e7d9ce34b35', '测试商家', '福建省厦门市集美区灌口镇', '2024-09-10 16:31:01', 1, '787aa81904875242219d0ee5de16c3b5', '1609273790', NULL, '该商家仅用于测试，仅用于测试');
INSERT INTO `tb_store` VALUES ('b65c589dc7adb3c3966e10356e33a441', '测试商家', '福建省厦门市集美区', '2024-09-10 16:20:54', 0, '787aa81904875242219d0ee5de16c3b5', '15672893612', NULL, '该店铺用于测试');
INSERT INTO `tb_store` VALUES ('be23fb1551c1977e6208c351691d315f', '胖胖宠物商店', '北京市朝阳区建国路123号', '2024-07-10 23:46:10', 1, 'f31123035b4dc0dec05e7f43612033f7', '13012345678', NULL, '欢迎来到宠物乐园，我们提供一站式宠物护理服务和高品质宠物食品。');
INSERT INTO `tb_store` VALUES ('cf1a0eb890f2f52dddd55c48f3064c6a', '测试商家', '福建省厦门市集美区灌口镇12号', '2024-09-10 15:04:03', 0, '787aa81904875242219d0ee5de16c3b5', '19826452663', NULL, '该店铺用于测试...');
INSERT INTO `tb_store` VALUES ('fea5b7f95515a784efff96d6db0215af', '', '', '2024-09-10 15:50:42', 0, '787aa81904875242219d0ee5de16c3b5', '', NULL, '');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键UUID',
  `username` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `create_time` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建时间 YYYY-MM-DD hh:mm:ss',
  `state` tinyint(1) NULL DEFAULT 1 COMMENT '状态 1-可用 0-不可用',
  `del_flag` tinyint(1) NULL DEFAULT 1 COMMENT '是否删除 1-正常 0-已删除',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '盐',
  `ballance` double(10, 2) NULL DEFAULT 0.00 COMMENT '余额',
  `pic` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `telephone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `type` tinyint(1) NULL DEFAULT 3 COMMENT '用户类型 1-超级管理员 2-商家 3-会员',
  `sex` tinyint(1) NULL DEFAULT 0 COMMENT '性别 0-女 1-男 2-保密',
  `nickname` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮件地址',
  `ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '推荐集合',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1daeb9bae45bbbbbf5ebbc150626c16b', 'admin', 'fd6b1a9c3b162fbcb5549c8b5decb5f8', '2023-12-24 19:05:43', 0, 1, 'bd3b6013-16bb-4578-8c5e-929388cda8fb', 0.00, '/img/user/1daeb9bae45bbbbbf5ebbc150626c16b/OIP-C (1).jpg', '18259767697', 1, 1, NULL, NULL, NULL);
INSERT INTO `tb_user` VALUES ('2e52970890a97024c6c01990fb040e4a', 'test6', '80669eb10852d8cf76d72408a1a3c068', '2024-09-10 15:58:01', 1, 1, '11abbd45-93ac-40ee-8c00-7d2a2ac3e7ea', 0.00, NULL, NULL, 3, 0, NULL, NULL, NULL);
INSERT INTO `tb_user` VALUES ('33bf8f09a3115b1669ef82d06a13b873', 'user4', '0530aafa437dca69cd04c1da4233b389', '2024-07-10 23:37:35', 1, 1, 'cd515db2-35eb-4569-8630-d94152759f5f', 0.00, NULL, NULL, 2, 0, NULL, NULL, NULL);
INSERT INTO `tb_user` VALUES ('715d5ee7b923a84db8be46f62909f0d1', 'test3', '9a6546525bbc82d86c1da0266b9b53f9', '2024-08-23 09:26:09', 1, 1, '7bb7b3a3-b024-4432-ade0-670322d2d767', 0.00, NULL, NULL, 3, 0, NULL, NULL, NULL);
INSERT INTO `tb_user` VALUES ('787aa81904875242219d0ee5de16c3b5', 'user1', 'bceef0421477e5e5b2cf8f36299a13ca', '2024-07-10 23:15:30', 1, 1, 'ab697c74-45b2-410e-baf9-28c1b8b7540a', 0.00, NULL, '1234567890', 2, 0, NULL, NULL, NULL);
INSERT INTO `tb_user` VALUES ('a81288f044481b989d58d9ba3edb05d2', 'test1', '5c2e1a5b27ed3e12b8a5cf71a1d634cb', '2024-09-10 14:36:45', 1, 1, '43c672ef-6cf5-49d1-9476-f2d7ff587a48', 0.00, NULL, NULL, 3, 0, NULL, NULL, NULL);
INSERT INTO `tb_user` VALUES ('be5dc7cac568a31c851e4c257a4cd167', 'test2', 'c4787e2aaa35c9c87fe2f920ac12a19d', '2024-08-23 09:25:09', 1, 1, '40476b86-ee9b-43b9-89ae-56e4d6a5ede2', 0.00, NULL, NULL, 3, 0, NULL, NULL, NULL);
INSERT INTO `tb_user` VALUES ('e3610d06919a08c8b63ef2abca52042c', 'test4', 'df948957fb6442af37d9efe2bf616eef', '2024-09-10 14:44:45', 1, 1, 'aadc9bea-08e7-4603-9a41-78595355577f', 0.00, NULL, NULL, 3, 0, NULL, NULL, NULL);
INSERT INTO `tb_user` VALUES ('e83229e6c2dd710f1c900bacdd817670', 'test', 'bd53cf9f24e3c3933070aa9e1f0cc35b', '2024-08-22 14:42:47', 1, 1, '8b8bfb8e-2952-4f34-98da-abb9bf2a840a', 0.00, NULL, NULL, 3, 0, NULL, NULL, NULL);
INSERT INTO `tb_user` VALUES ('f31123035b4dc0dec05e7f43612033f7', 'user2', 'ff7b7bac24504bf8423df192aba89b1c', '2024-07-10 23:36:18', 1, 1, '29cb7953-2c22-497d-a6cd-1bffb86888fd', 0.00, NULL, NULL, 2, 0, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for tb_user_action
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_action`;
CREATE TABLE `tb_user_action`  (
  `id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键UUID',
  `user_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户id，关联user表',
  `product_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品id，关联product表',
  `create_time` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建时间 YYYY-MM-DD hh:mm:ss',
  `del_flag` tinyint(1) NULL DEFAULT 1 COMMENT '是否删除 1-正常 0-已删除',
  `action_type` tinyint(1) NULL DEFAULT NULL COMMENT '操作类型 0-浏览 1-收藏',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户操作记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_user_action
-- ----------------------------
INSERT INTO `tb_user_action` VALUES ('0ac9a20af3d5ef77b111d0961acb01b8', 'be5dc7cac568a31c851e4c257a4cd167', 'f38810ccc6ad4a3b6648cf2ce45492b9', '2024-08-23 09:25:36', 1, 1);
INSERT INTO `tb_user_action` VALUES ('0d9e815d745a47435e3afd8b347f1ef5', '2e52970890a97024c6c01990fb040e4a', '791c9aaeed17eccdb76923468a0d5490', '2024-09-10 16:24:32', 1, 1);
INSERT INTO `tb_user_action` VALUES ('13e9d9c9e57bd4c971d78b1d60487c7b', '715d5ee7b923a84db8be46f62909f0d1', '5dd4e1f3092dc6bad70cc2e4f90617f7', '2024-09-08 19:22:05', 1, 1);
INSERT INTO `tb_user_action` VALUES ('148597a5095cecfc1c7888537f294310', 'f31123035b4dc0dec05e7f43612033f7', '07905d577bb86e9acfdfca4ecc853ba8', '2024-08-22 14:38:53', 1, 1);
INSERT INTO `tb_user_action` VALUES ('19a4f9f9dfcc630ef75d5cbc64f1d6b2', 'e83229e6c2dd710f1c900bacdd817670', '68a20cd1cc5999a76c6d5769efa500fa', '2024-08-23 09:24:41', 1, 1);
INSERT INTO `tb_user_action` VALUES ('27e9fdf58f1436a436db772299a2f42f', 'e83229e6c2dd710f1c900bacdd817670', '1efb7c7c073ea7a42876a7f3847e2d36', '2024-08-22 21:31:27', 1, 1);
INSERT INTO `tb_user_action` VALUES ('297af0e66ae7cf604f78e14794a57713', 'be5dc7cac568a31c851e4c257a4cd167', 'eaa7d2a128139562ad97e3399c4c7ecd', '2024-08-23 09:25:34', 1, 1);
INSERT INTO `tb_user_action` VALUES ('36c573279d10a5b43832fb5a1cc6348b', 'a81288f044481b989d58d9ba3edb05d2', '32114b3f10227efd034e3890322a0769', '2024-09-10 16:34:17', 1, 1);
INSERT INTO `tb_user_action` VALUES ('4019e87dd4633cacdb3f555af436b451', '715d5ee7b923a84db8be46f62909f0d1', '0e5de64009e8b081789fc6123f91d5f6', '2024-08-23 09:26:38', 1, 1);
INSERT INTO `tb_user_action` VALUES ('439beac524538ff44ed3ea660ca5d358', 'e3610d06919a08c8b63ef2abca52042c', 'acef5b0cc70e39138d584897ecede7b8', '2024-09-10 15:14:49', 1, 1);
INSERT INTO `tb_user_action` VALUES ('4596220a65e4491367e3626708b47fd4', 'e3610d06919a08c8b63ef2abca52042c', '791c9aaeed17eccdb76923468a0d5490', '2024-09-10 15:57:04', 1, 1);
INSERT INTO `tb_user_action` VALUES ('4e8a3b7dbbb2e5cf7efcf7686533cb62', 'a81288f044481b989d58d9ba3edb05d2', '68a20cd1cc5999a76c6d5769efa500fa', '2024-09-10 16:32:45', 1, 1);
INSERT INTO `tb_user_action` VALUES ('511156278f45ce2a235abcf3459ae4d3', '2e52970890a97024c6c01990fb040e4a', '32114b3f10227efd034e3890322a0769', '2024-09-10 16:38:50', 1, 1);
INSERT INTO `tb_user_action` VALUES ('5c64c9b1ed22400df5a4f5582411d35b', '715d5ee7b923a84db8be46f62909f0d1', 'f38810ccc6ad4a3b6648cf2ce45492b9', '2024-09-08 19:12:36', 1, 1);
INSERT INTO `tb_user_action` VALUES ('67b5fc8570b4922f2d5f0ed3847a9dea', 'be5dc7cac568a31c851e4c257a4cd167', '136f53f82c9261f8b30fc437017a6cec', '2024-08-23 09:25:25', 1, 1);
INSERT INTO `tb_user_action` VALUES ('705f503ab8bfdf101f520a47525c798a', 'e83229e6c2dd710f1c900bacdd817670', '136f53f82c9261f8b30fc437017a6cec', '2024-08-23 09:24:38', 1, 1);
INSERT INTO `tb_user_action` VALUES ('796b1641f9a0ab3183d62fd416870ae2', 'be5dc7cac568a31c851e4c257a4cd167', '5dd4e1f3092dc6bad70cc2e4f90617f7', '2024-08-23 09:25:21', 1, 1);
INSERT INTO `tb_user_action` VALUES ('7a8dc7f57f5d8918270fb22ad993127e', 'e3610d06919a08c8b63ef2abca52042c', '68a20cd1cc5999a76c6d5769efa500fa', '2024-09-10 15:48:04', 1, 1);
INSERT INTO `tb_user_action` VALUES ('7b65706d8cdb44efbec8235a21ad7233', 'a81288f044481b989d58d9ba3edb05d2', '5dd4e1f3092dc6bad70cc2e4f90617f7', '2024-09-10 15:35:16', 1, 1);
INSERT INTO `tb_user_action` VALUES ('82e3529b4e9e34c6248c299a193495ae', '715d5ee7b923a84db8be46f62909f0d1', '68a20cd1cc5999a76c6d5769efa500fa', '2024-09-08 18:59:08', 1, 1);
INSERT INTO `tb_user_action` VALUES ('8768045c55786ccf84a22de233f65ff3', 'e83229e6c2dd710f1c900bacdd817670', '0e5de64009e8b081789fc6123f91d5f6', '2024-08-22 21:40:12', 1, 1);
INSERT INTO `tb_user_action` VALUES ('a087bbb1d473b35f59370a74de130745', 'e83229e6c2dd710f1c900bacdd817670', '07905d577bb86e9acfdfca4ecc853ba8', '2024-08-23 06:42:48', 1, 1);
INSERT INTO `tb_user_action` VALUES ('a606b9b1b74561911864cb0001aa1e86', 'e3610d06919a08c8b63ef2abca52042c', '7418d1c2ae4c2aa44231ea9652a02c08', '2024-09-10 15:08:52', 1, 1);
INSERT INTO `tb_user_action` VALUES ('b1b52ec4991ad363b2ec82e53580dae1', 'be5dc7cac568a31c851e4c257a4cd167', '81f84f07d7d3dd71345cf4dfdced72f4', '2024-08-23 09:25:30', 1, 1);
INSERT INTO `tb_user_action` VALUES ('b29add4ea4ce98219c2dcbda88a7c489', 'e83229e6c2dd710f1c900bacdd817670', '5dd4e1f3092dc6bad70cc2e4f90617f7', '2024-08-22 21:38:34', 1, 1);
INSERT INTO `tb_user_action` VALUES ('dc8dd36af31d710bb513c4121b73490c', '715d5ee7b923a84db8be46f62909f0d1', '81f84f07d7d3dd71345cf4dfdced72f4', '2024-09-08 19:12:08', 1, 1);
INSERT INTO `tb_user_action` VALUES ('f020504bb0163fd1099539d932dba5ba', '715d5ee7b923a84db8be46f62909f0d1', 'c1694480c80e91a22e0d8689ffb5e882', '2024-08-23 09:26:22', 1, 1);
INSERT INTO `tb_user_action` VALUES ('ff2009d8b038a0ace7ce7dbfecdd1d76', '2e52970890a97024c6c01990fb040e4a', '68a20cd1cc5999a76c6d5769efa500fa', '2024-09-10 16:27:17', 1, 1);

-- ----------------------------
-- Table structure for tb_videos
-- ----------------------------
DROP TABLE IF EXISTS `tb_videos`;
CREATE TABLE `tb_videos`  (
  `id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键UUID',
  `product_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '物品id，关联goods表',
  `create_time` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建时间 YYYY-MM-DD hh:mm:ss',
  `del_flag` tinyint(1) NULL DEFAULT 1 COMMENT '是否删除 1-正常 0-已删除',
  `video_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路径',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `views_count` int NULL DEFAULT 0 COMMENT '播放次数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '视频表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_videos
-- ----------------------------
INSERT INTO `tb_videos` VALUES ('16bd60723f36b3487ecc2ea7cf55e4ba', '0e5de64009e8b081789fc6123f91d5f6', '2024-07-11 00:30:33', 1, '/img/productsVideo/0e5de64009e8b081789fc6123f91d5f6/边牧.mp4', NULL, 1);
INSERT INTO `tb_videos` VALUES ('1cc1a32d7cf72a8392b101b66e1d63d9', '136f53f82c9261f8b30fc437017a6cec', '2024-07-11 00:28:28', 1, '/img/productsVideo/136f53f82c9261f8b30fc437017a6cec/20240706_b371e0cca0972cc4_470936969673_139895023586331_published_mp4_264_hd_taobao_compressed.mp4', NULL, 2);
INSERT INTO `tb_videos` VALUES ('7030cacc8957e08e75ace2d31b58bf3c', '7418d1c2ae4c2aa44231ea9652a02c08', '2024-07-11 00:32:27', 1, '/img/productsVideo/7418d1c2ae4c2aa44231ea9652a02c08/金毛.mp4', NULL, 0);
INSERT INTO `tb_videos` VALUES ('98e2ce13014169ccd9728f26221ce542', 'f38810ccc6ad4a3b6648cf2ce45492b9', '2024-07-11 00:24:10', 1, '/img/productsVideo/f38810ccc6ad4a3b6648cf2ce45492b9/缅因猫.mp4', NULL, 1);
INSERT INTO `tb_videos` VALUES ('a4cb040472da685f9f53eb5380473bd9', '5dd4e1f3092dc6bad70cc2e4f90617f7', '2024-07-11 00:31:26', 1, '/img/productsVideo/5dd4e1f3092dc6bad70cc2e4f90617f7/20230619_38c51bb0620f7052_416082078691_36990892944210_published_mp4_264_hd_taobao.mp4', NULL, 1);
INSERT INTO `tb_videos` VALUES ('bbdfb4dad32e5f934873b3ce0d96dfb4', 'c1694480c80e91a22e0d8689ffb5e882', '2024-07-11 00:30:05', 1, '/img/productsVideo/c1694480c80e91a22e0d8689ffb5e882/20231123_144d969cf7b3724b_438951018415_71250576326403_published_mp4_264_hd_taobao.mp4', NULL, 0);
INSERT INTO `tb_videos` VALUES ('c863cffca262e96d6b005aebfdcd3123', '07905d577bb86e9acfdfca4ecc853ba8', '2024-07-11 00:35:44', 1, '/img/productsVideo/07905d577bb86e9acfdfca4ecc853ba8/20230814_ed429dcc0ae47e17_422629836360_45262202627163_published_mp4_264_hd_taobao.mp4', NULL, 0);
INSERT INTO `tb_videos` VALUES ('db6cc13f63657fb0473bd62e35daddbb', '68a20cd1cc5999a76c6d5769efa500fa', '2024-07-11 00:32:41', 1, '/img/productsVideo/68a20cd1cc5999a76c6d5769efa500fa/20240205_4fdaf034cfdb6e89_449017041086_91519206570610_published_mp4_264_hd_taobao.mp4', NULL, 0);

-- ----------------------------
-- Table structure for tb_view_history
-- ----------------------------
DROP TABLE IF EXISTS `tb_view_history`;
CREATE TABLE `tb_view_history`  (
  `id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键UUID',
  `user_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户id，关联user表',
  `video_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '视频id，关联videos表',
  `create_time` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建时间 YYYY-MM-DD hh:mm:ss',
  `del_flag` tinyint(1) NULL DEFAULT 1 COMMENT '是否删除 1-正常 0-已删除',
  `watch_time` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户观看该视频时间记录',
  `is_recommended` tinyint(1) NULL DEFAULT NULL COMMENT '是否推荐 1-已推荐 0-未推荐',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '视频浏览记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_view_history
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
