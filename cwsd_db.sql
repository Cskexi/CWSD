/*
 Navicat Premium Data Transfer

 Source Server         : 1
 Source Server Type    : MySQL
 Source Server Version : 80034
 Source Host           : localhost:3306
 Source Schema         : cwsd_db

 Target Server Type    : MySQL
 Target Server Version : 80034
 File Encoding         : 65001

 Date: 27/06/2024 10:08:45
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
-- Table structure for tb_address
-- ----------------------------
DROP TABLE IF EXISTS `tb_address`;
CREATE TABLE `tb_address`  (
  `id` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '主键UUID',
  `user_id` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `address` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '地址',
  `create_time` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '创建时间 YYYY-MM-DD hh:mm:ss',
  `del_flag` tinyint NULL DEFAULT NULL COMMENT '是否删除 1-正常 0-已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '地址表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_address
-- ----------------------------

-- ----------------------------
-- Table structure for tb_cartltems
-- ----------------------------
DROP TABLE IF EXISTS `tb_cartltems`;
CREATE TABLE `tb_cartltems`  (
  `id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键UUID',
  `user_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id，关联user表',
  `product_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '物品id，关联goods表',
  `create_time` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建时间 YYYY-MM-DD hh:mm:ss',
  `del_flag` tinyint(1) NULL DEFAULT 1 COMMENT '是否删除 1-正常 0-已删除',
  `number` int NULL DEFAULT NULL COMMENT '物品数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_cartltems
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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典表 ' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_categories
-- ----------------------------
INSERT INTO `tb_categories` VALUES ('1d5f89c550ea46a1767cebea84c31ffd', '小猫', '2024-06-27 09:50:41', 1, '49468015a68a3370ae065a12d2b5e6ac', NULL);
INSERT INTO `tb_categories` VALUES ('49468015a68a3370ae065a12d2b5e6ac', '猫', '2024-06-27 09:48:46', 1, NULL, NULL);
INSERT INTO `tb_categories` VALUES ('59cdc14599cc397f0fb49263730f1cd0', '大猫', '2024-06-27 09:52:00', 1, '49468015a68a3370ae065a12d2b5e6ac', '49468015a68a3370ae065a12d2b5e6ac');
INSERT INTO `tb_categories` VALUES ('9ad6361b701d4d095c2ec72a23ad3e82', '小小狗', '2024-06-27 09:53:27', 1, 'bd2d8fb5b9f69985370b5b3818f3af87', 'fcd869c75f579f168c373c7943c25f70');
INSERT INTO `tb_categories` VALUES ('bd2d8fb5b9f69985370b5b3818f3af87', '小狗', '2024-06-27 09:52:25', 1, 'fcd869c75f579f168c373c7943c25f70', 'fcd869c75f579f168c373c7943c25f70');
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
-- Table structure for tb_goods
-- ----------------------------
DROP TABLE IF EXISTS `tb_goods`;
CREATE TABLE `tb_goods`  (
  `id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键UUID',
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品名字',
  `pic` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品图片',
  `create_time` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建时间 YYYY-MM-DD hh:mm:ss',
  `del_flag` tinyint(1) NULL DEFAULT 1 COMMENT '是否删除 1-正常 0-已删除',
  `store_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属商店id,关联tb_store表',
  `price` double(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `inventory` int NULL DEFAULT 0 COMMENT '库存',
  `state` tinyint(1) NULL DEFAULT NULL COMMENT '状态 1-正常 0-下架',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_goods
-- ----------------------------
INSERT INTO `tb_goods` VALUES ('161bb795ef86bec678e40e63d50e7e0b', '【旗舰店】雀巢怡运高钙牛奶粉', '/img/goods/undefined/PixPin_2023-12-24_19-43-23.png', '2023-12-24 19:44:26', 1, '3056fd8e71efe7b25953503315e1371f', 99.00, 234, NULL);
INSERT INTO `tb_goods` VALUES ('16851a1b70312944c12e580f107562dc', '雀巢怡养健心鱼油中老年牛奶粉', '/img/goods/16851a1b70312944c12e580f107562dc/PixPin_2023-12-24_19-42-58.png', '2023-12-24 19:42:55', 1, '3056fd8e71efe7b25953503315e1371f', 139.00, 123, NULL);
INSERT INTO `tb_goods` VALUES ('1dca6b49e45db370429d878fae427432', '小米米家加湿器2', '/img/goods/1dca6b49e45db370429d878fae427432/PixPin_2023-12-24_19-20-38.png', '2023-12-24 19:23:43', 1, '63af69462dbc21ca35140aacaef9a3c4', 129.00, 111, NULL);
INSERT INTO `tb_goods` VALUES ('308b85a23f476e4c08e6cd93a6037357', '小米双肩包小背包男女运动包', '/img/goods/undefined/PixPin_2023-12-24_19-25-27.png', '2023-12-24 19:25:50', 1, '63af69462dbc21ca35140aacaef9a3c4', 39.00, 1212, NULL);
INSERT INTO `tb_goods` VALUES ('34397debbb8d28799795aedd8297e2c5', '小米米家声波电动牙刷', '/img/goods/undefined/PixPin_2023-12-24_19-19-25.png', '2023-12-24 19:19:56', 1, '63af69462dbc21ca35140aacaef9a3c4', 59.90, 10000, NULL);
INSERT INTO `tb_goods` VALUES ('5f4751eb612fbbf02a0db4e000a9dfd8', '小米充电宝10000毫安大容量22.5W', '/img/goods/undefined/PixPin_2023-12-24_19-14-52.png', '2023-12-24 19:15:40', 1, '63af69462dbc21ca35140aacaef9a3c4', 89.00, 1000, NULL);
INSERT INTO `tb_goods` VALUES ('b1c7ba2d1fe86778e9f63f51fd7071ec', '浴巾纯棉', '/img/goods/b1c7ba2d1fe86778e9f63f51fd7071ec/PixPin_2023-12-24_19-40-45.png', '2023-12-24 19:40:52', 1, '3a2884bf6706ae06ad25f7809eee77bd', 87.20, 1111, NULL);
INSERT INTO `tb_goods` VALUES ('bcd211c310f67be100af087083cd53a1', '无印良品被芯', '/img/goods/bcd211c310f67be100af087083cd53a1/PixPin_2023-12-24_19-39-52.png', '2023-12-24 19:39:45', 1, '3a2884bf6706ae06ad25f7809eee77bd', 213.00, 1111, NULL);

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
-- Table structure for tb_order_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_item`;
CREATE TABLE `tb_order_item`  (
  `id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键UUID',
  `order_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '编号id，关联order表',
  `goods_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '物品id，关联goods表',
  `create_time` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建时间 YYYY-MM-DD hh:mm:ss',
  `del_flag` tinyint(1) NULL DEFAULT 1 COMMENT '是否删除 1-正常 0-已删除',
  `number` int NULL DEFAULT NULL COMMENT '物品数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_order_item
-- ----------------------------
INSERT INTO `tb_order_item` VALUES ('15e7510c920125d7deb5cce3f9136bdc', 'c7a7b68d583b1cb98286c1b751ae7620', '34397debbb8d28799795aedd8297e2c5', '2023-12-24 20:30:32', 1, 4);
INSERT INTO `tb_order_item` VALUES ('43b5fe049af1b50f259739156b195333', '7392dc211f8b23fa0a3bd893d3570aa3', '161bb795ef86bec678e40e63d50e7e0b', '2023-12-24 20:29:44', 1, 4);
INSERT INTO `tb_order_item` VALUES ('49aa6a1632aca090e8cd2543b9411a65', '85498b421f6e948e0f6c20158ed9ecbd', '161bb795ef86bec678e40e63d50e7e0b', '2023-12-24 20:00:27', 1, 1);
INSERT INTO `tb_order_item` VALUES ('4f41564acd2eac7b1dad4efed379e517', '61c9c4672f68ca43330cbc45d28ef361', '5f4751eb612fbbf02a0db4e000a9dfd8', '2023-12-26 17:03:10', 1, 4);
INSERT INTO `tb_order_item` VALUES ('66f0701a7203eed3bf7504601bb6cfdb', 'aedfeec7e651150dbaabac84d12275b7', '161bb795ef86bec678e40e63d50e7e0b', '2023-12-24 19:52:03', 1, 3);
INSERT INTO `tb_order_item` VALUES ('b5d8f5cb57384266d75aa7ff42ec9fee', '7f4c7a0e1c51bda077f1f5b7d0f38c40', '161bb795ef86bec678e40e63d50e7e0b', '2023-12-24 20:02:16', 1, 1);
INSERT INTO `tb_order_item` VALUES ('caa2c9827fb1589770fbdb8f0076b2b9', '41c291bf0eddf6bce8543691e30555df', '16851a1b70312944c12e580f107562dc', '2023-12-24 20:29:44', 1, 4);
INSERT INTO `tb_order_item` VALUES ('de38cefd1672e49d612dc92bdb0b7314', 'f54c2137a9fe90160912c1b642c1e715', '161bb795ef86bec678e40e63d50e7e0b', '2023-12-24 19:57:46', 1, 2);

-- ----------------------------
-- Table structure for tb_order_items
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_items`;
CREATE TABLE `tb_order_items`  (
  `id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键UUID',
  `orders_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '编号id，关联order表',
  `product_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '物品id，关联goods表',
  `create_time` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建时间 YYYY-MM-DD hh:mm:ss',
  `del_flag` tinyint(1) NULL DEFAULT 1 COMMENT '是否删除 1-正常 0-已删除',
  `number` int NULL DEFAULT NULL COMMENT '物品数量',
  `price_at_order` int NULL DEFAULT NULL COMMENT '订单时的价格',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_order_items
-- ----------------------------

-- ----------------------------
-- Table structure for tb_orders
-- ----------------------------
DROP TABLE IF EXISTS `tb_orders`;
CREATE TABLE `tb_orders`  (
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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_orders
-- ----------------------------

-- ----------------------------
-- Table structure for tb_pet
-- ----------------------------
DROP TABLE IF EXISTS `tb_pet`;
CREATE TABLE `tb_pet`  (
  `id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键UUID',
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品名字',
  `create_time` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建时间 YYYY-MM-DD hh:mm:ss',
  `del_flag` tinyint(1) NULL DEFAULT 1 COMMENT '是否删除 1-正常 0-已删除',
  `category_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关联tb_商品类别表',
  `price` double(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `species` tinyint NULL DEFAULT 0 COMMENT '宠物种类',
  `age` int NULL DEFAULT NULL COMMENT '年龄',
  `gender` tinyint(1) NULL DEFAULT NULL COMMENT '性别',
  `products` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品表id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_pet
-- ----------------------------

-- ----------------------------
-- Table structure for tb_product_mages
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_mages`;
CREATE TABLE `tb_product_mages`  (
  `id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键UUID',
  `product_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '物品id，关联goods表',
  `create_time` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建时间 YYYY-MM-DD hh:mm:ss',
  `del_flag` tinyint(1) NULL DEFAULT 1 COMMENT '是否删除 1-正常 0-已删除',
  `path` int NULL DEFAULT NULL COMMENT '路径',
  `sortOrder` int NULL DEFAULT NULL COMMENT '排序序号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_product_mages
-- ----------------------------

-- ----------------------------
-- Table structure for tb_product_videos
-- ----------------------------
DROP TABLE IF EXISTS `tb_product_videos`;
CREATE TABLE `tb_product_videos`  (
  `id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键UUID',
  `product_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '物品id，关联goods表',
  `create_time` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建时间 YYYY-MM-DD hh:mm:ss',
  `del_flag` tinyint(1) NULL DEFAULT 1 COMMENT '是否删除 1-正常 0-已删除',
  `path` int NULL DEFAULT NULL COMMENT '路径',
  `description` int NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_product_videos
-- ----------------------------

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
  `state` tinyint(1) NULL DEFAULT NULL COMMENT '状态 1-正常 0-下架',
  `category_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品类别表ID',
  `pet_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '宠物表id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_products
-- ----------------------------

-- ----------------------------
-- Table structure for tb_reviews
-- ----------------------------
DROP TABLE IF EXISTS `tb_reviews`;
CREATE TABLE `tb_reviews`  (
  `id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键UUID',
  `user_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '编号id，关联order表',
  `product_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '物品id，关联goods表',
  `create_time` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建时间 YYYY-MM-DD hh:mm:ss',
  `del_flag` tinyint(1) NULL DEFAULT 1 COMMENT '是否删除 1-正常 0-已删除',
  `number` int NULL DEFAULT NULL COMMENT '评论',
  `rating` int NULL DEFAULT NULL COMMENT '评分',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_reviews
-- ----------------------------

-- ----------------------------
-- Table structure for tb_serve
-- ----------------------------
DROP TABLE IF EXISTS `tb_serve`;
CREATE TABLE `tb_serve`  (
  `id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键UUID',
  `name` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '服务名字',
  `create_time` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建时间 YYYY-MM-DD hh:mm:ss',
  `del_flag` tinyint(1) NULL DEFAULT 1 COMMENT '是否删除 1-正常 0-已删除',
  `price` double(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `duration` tinyint NULL DEFAULT 0 COMMENT '持续时间',
  `category_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品种类表',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '服务表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_serve
-- ----------------------------

-- ----------------------------
-- Table structure for tb_shopcart
-- ----------------------------
DROP TABLE IF EXISTS `tb_shopcart`;
CREATE TABLE `tb_shopcart`  (
  `id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键UUID',
  `user_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id，关联user表',
  `goods_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '物品id，关联goods表',
  `create_time` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建时间 YYYY-MM-DD hh:mm:ss',
  `del_flag` tinyint(1) NULL DEFAULT 1 COMMENT '是否删除 1-正常 0-已删除',
  `number` int NULL DEFAULT NULL COMMENT '物品数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_shopcart
-- ----------------------------
INSERT INTO `tb_shopcart` VALUES ('00ec743ae05ed748c12390872b074025', '34544f9ebc675eb4a1e70f489ee8ce4d', '161bb795ef86bec678e40e63d50e7e0b', '2023-12-24 19:50:45', 0, 3);
INSERT INTO `tb_shopcart` VALUES ('264bf14d6f38f8a1c0fdb0a7fd01f18f', '34544f9ebc675eb4a1e70f489ee8ce4d', '161bb795ef86bec678e40e63d50e7e0b', '2023-12-24 20:02:11', 0, 1);
INSERT INTO `tb_shopcart` VALUES ('2e29c7a45e6da58d96acbd39022c5637', '34544f9ebc675eb4a1e70f489ee8ce4d', '161bb795ef86bec678e40e63d50e7e0b', '2023-12-24 20:00:23', 0, 1);
INSERT INTO `tb_shopcart` VALUES ('472c70135c629f9a1a4ae0cd5b3444c7', '34544f9ebc675eb4a1e70f489ee8ce4d', '16851a1b70312944c12e580f107562dc', '2023-12-24 20:29:28', 0, 4);
INSERT INTO `tb_shopcart` VALUES ('6e148584c96d39682f5e868a8c731f2e', '34544f9ebc675eb4a1e70f489ee8ce4d', '5f4751eb612fbbf02a0db4e000a9dfd8', '2023-12-26 17:02:43', 0, 4);
INSERT INTO `tb_shopcart` VALUES ('7a3e8aa01f1a6562cab4fb45952fc052', '34544f9ebc675eb4a1e70f489ee8ce4d', '161bb795ef86bec678e40e63d50e7e0b', '2023-12-24 20:29:25', 0, 4);
INSERT INTO `tb_shopcart` VALUES ('b3563e527f6e122f4cab2e16671e1c0a', '34544f9ebc675eb4a1e70f489ee8ce4d', '34397debbb8d28799795aedd8297e2c5', '2023-12-24 20:30:29', 0, 4);
INSERT INTO `tb_shopcart` VALUES ('cea87bab551593ca4505987604df0eba', '34544f9ebc675eb4a1e70f489ee8ce4d', '161bb795ef86bec678e40e63d50e7e0b', '2023-12-24 19:57:25', 0, 2);

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
  `user_id` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商店所属管理员,所属用户关联tb_user表',
  `telephone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商店表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_store
-- ----------------------------
INSERT INTO `tb_store` VALUES ('3056fd8e71efe7b25953503315e1371f', '雀巢', '12', '2023-12-24 19:42:38', 1, '8f04b962b124fe6abc6d3ff21ef1c9bd', '12');
INSERT INTO `tb_store` VALUES ('3a2884bf6706ae06ad25f7809eee77bd', '无印良品', '上海', '2023-12-24 19:38:21', 1, '8f04b962b124fe6abc6d3ff21ef1c9bd', '123455');
INSERT INTO `tb_store` VALUES ('63af69462dbc21ca35140aacaef9a3c4', '小米官方旗舰店', '中国', '2023-12-24 19:11:36', 1, '8b2b5d7e02ddf8968b7ce1074985707d', '1111111');

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1daeb9bae45bbbbbf5ebbc150626c16b', 'admin', 'fd6b1a9c3b162fbcb5549c8b5decb5f8', '2023-12-24 19:05:43', 0, 1, 'bd3b6013-16bb-4578-8c5e-929388cda8fb', 0.00, '/img/user/1daeb9bae45bbbbbf5ebbc150626c16b/OIP-C.jpg', '18259767697', 2, 1, NULL);
INSERT INTO `tb_user` VALUES ('34544f9ebc675eb4a1e70f489ee8ce4d', 'user3', 'efaec4eec4b8ecc40b32fe87de0ae6a3', '2023-12-24 19:45:29', 0, 1, '5237860f-fbd0-4dbd-9967-40246734e199', 0.00, '/img/user/34544f9ebc675eb4a1e70f489ee8ce4d/03 (1).png', NULL, 2, 0, NULL);
INSERT INTO `tb_user` VALUES ('8b2b5d7e02ddf8968b7ce1074985707d', 'user1', '86ede3fe00533a2e7b37ff6d85f5c4aa', '2023-12-24 19:02:17', 1, 1, '620d6540-2006-4d60-97b7-0cd89ca426af', 0.00, NULL, NULL, 2, 0, NULL);
INSERT INTO `tb_user` VALUES ('8f04b962b124fe6abc6d3ff21ef1c9bd', 'user2', 'a807e583ff15c385df4652571421ba91', '2023-12-24 19:31:18', 1, 1, 'bca4efd1-f408-4e67-a1f9-2a3c0192ff6a', 0.00, NULL, NULL, 2, 0, NULL);

SET FOREIGN_KEY_CHECKS = 1;
