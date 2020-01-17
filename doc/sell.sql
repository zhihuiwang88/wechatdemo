/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50721
Source Host           : 127.0.0.1:3306
Source Database       : sell

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2020-01-15 15:30:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `detail_id` varchar(32) NOT NULL,
  `order_id` varchar(32) NOT NULL,
  `product_id` varchar(32) NOT NULL,
  `product_name` varchar(64) NOT NULL COMMENT '商品名称',
  `product_price` decimal(8,2) NOT NULL COMMENT '当前价格,单位分',
  `product_quantity` int(11) NOT NULL COMMENT '数量',
  `product_icon` varchar(512) DEFAULT NULL COMMENT '小图',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`detail_id`),
  KEY `idx_order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单商品表';

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES ('121212', '101', '1230', '紫菜包', '100.00', '3', 'http://XXX.png', '2019-12-04 10:12:40', '2019-12-31 11:25:50');
INSERT INTO `order_detail` VALUES ('201912041740341575452434630', '201912061575622112056', '1230', '油条', '1.50', '5', 'http://XXX.jpg', '2019-12-04 17:40:36', '2019-12-31 11:18:34');
INSERT INTO `order_detail` VALUES ('201912041740411575452441696', '201912061575622112055', '12340', '豆浆', '2.00', '3', 'http://XXX.jpg', '2019-12-04 17:40:42', '2019-12-31 11:18:29');
INSERT INTO `order_detail` VALUES ('201912061575597902292', '201912061575597902137', '1230', '油条', '1.50', '5', 'http://XXX.jpg', '2019-12-06 10:05:02', '2019-12-06 10:05:02');
INSERT INTO `order_detail` VALUES ('201912061575597902374', '201912061575622112054', '12340', '豆浆', '2.00', '3', 'http://XXX.jpg', '2019-12-06 10:05:02', '2019-12-31 11:18:14');
INSERT INTO `order_detail` VALUES ('201912061575622112178', '201912061575622112052', '1230', '油条', '1.50', '5', 'http://XXX.jpg', '2019-12-06 16:48:32', '2019-12-06 16:48:32');
INSERT INTO `order_detail` VALUES ('201912061575622112404', '201912061575622112053', '12340', '豆浆', '2.00', '3', 'http://XXX.jpg', '2019-12-06 16:48:32', '2019-12-31 11:18:05');
INSERT INTO `order_detail` VALUES ('202001091578566460035', '202001091578566459865', '1230', '油条', '1.50', '5', 'http://XXX.jpg', '2020-01-09 18:41:00', '2020-01-09 18:41:00');
INSERT INTO `order_detail` VALUES ('202001091578566545987', '202001091578566545781', '1230', '油条', '1.50', '5', 'http://XXX.jpg', '2020-01-09 18:42:26', '2020-01-09 18:42:26');
INSERT INTO `order_detail` VALUES ('202001091578566767876', '202001091578566766865', '1230', '油条', '1.50', '5', 'http://XXX.jpg', '2020-01-09 18:46:08', '2020-01-09 18:46:08');
INSERT INTO `order_detail` VALUES ('202001101578623543092', '202001101578623542875', '1250', '豆腐脑', '2.60', '2', 'http://cdn.ibootstrap.cn/lorempixel.com/140/140/default.jpg', '2020-01-10 10:32:23', '2020-01-10 10:32:23');
INSERT INTO `order_detail` VALUES ('202001101578623626611', '202001101578623626583', '1250', '豆腐脑', '2.60', '2', 'http://cdn.ibootstrap.cn/lorempixel.com/140/140/default.jpg', '2020-01-10 10:33:46', '2020-01-10 10:33:46');
INSERT INTO `order_detail` VALUES ('202001101578623663473', '202001101578623663455', '1250', '豆腐脑', '2.60', '2', 'http://cdn.ibootstrap.cn/lorempixel.com/140/140/default.jpg', '2020-01-10 10:34:23', '2020-01-10 10:34:23');
INSERT INTO `order_detail` VALUES ('202001101578623816368', '202001101578623816362', '1250', '豆腐脑', '2.60', '2', 'http://cdn.ibootstrap.cn/lorempixel.com/140/140/default.jpg', '2020-01-10 10:36:56', '2020-01-10 10:36:56');
INSERT INTO `order_detail` VALUES ('202001101578623983558', '202001101578623983553', '1240', '包子', '2.50', '2', 'http://XXX.jpg', '2020-01-10 10:39:43', '2020-01-10 10:39:43');
INSERT INTO `order_detail` VALUES ('202001101578624016575', '202001101578624016569', '1240', '包子', '2.50', '2', 'http://XXX.jpg', '2020-01-10 10:40:16', '2020-01-10 10:40:16');
INSERT INTO `order_detail` VALUES ('202001101578624031254', '202001101578624031251', '1240', '包子', '2.50', '2', 'http://XXX.jpg', '2020-01-10 10:40:31', '2020-01-10 10:40:31');
INSERT INTO `order_detail` VALUES ('202001101578624081488', '202001101578624081484', '1240', '包子', '2.50', '2', 'http://XXX.jpg', '2020-01-10 10:41:21', '2020-01-10 10:41:21');
INSERT INTO `order_detail` VALUES ('202001101578624114354', '202001101578624114349', '1240', '包子', '2.50', '2', 'http://XXX.jpg', '2020-01-10 10:41:54', '2020-01-10 10:41:54');
INSERT INTO `order_detail` VALUES ('202001101578625035396', '202001101578625034809', '1240', '包子', '2.50', '2', 'http://XXX.jpg', '2020-01-10 10:57:15', '2020-01-10 10:57:15');
INSERT INTO `order_detail` VALUES ('202001101578625095382', '202001101578625095357', '1240', '包子', '2.50', '2', 'http://XXX.jpg', '2020-01-10 10:58:15', '2020-01-10 10:58:15');
INSERT INTO `order_detail` VALUES ('202001101578625320957', '202001101578625320889', '1240', '包子', '2.50', '2', 'http://XXX.jpg', '2020-01-10 11:02:01', '2020-01-10 11:02:01');
INSERT INTO `order_detail` VALUES ('202001101578625378853', '202001101578625378847', '1240', '包子', '2.50', '2', 'http://XXX.jpg', '2020-01-10 11:02:58', '2020-01-10 11:02:58');
INSERT INTO `order_detail` VALUES ('202001101578625430503', '202001101578625430497', '1240', '包子', '2.50', '2', 'http://XXX.jpg', '2020-01-10 11:03:50', '2020-01-10 11:03:50');
INSERT INTO `order_detail` VALUES ('202001101578625505968', '202001101578625505959', '1240', '包子', '2.50', '2', 'http://XXX.jpg', '2020-01-10 11:05:06', '2020-01-10 11:05:06');
INSERT INTO `order_detail` VALUES ('202001101578626087445', '202001101578626087437', '1240', '包子', '2.50', '2', 'http://XXX.jpg', '2020-01-10 11:14:47', '2020-01-10 11:14:47');
INSERT INTO `order_detail` VALUES ('202001101578626146597', '202001101578626146592', '1240', '包子', '2.50', '2', 'http://XXX.jpg', '2020-01-10 11:15:46', '2020-01-10 11:15:46');
INSERT INTO `order_detail` VALUES ('202001101578626164484', '202001101578626164479', '1240', '包子', '2.50', '2', 'http://XXX.jpg', '2020-01-10 11:16:04', '2020-01-10 11:16:04');
INSERT INTO `order_detail` VALUES ('202001101578626196699', '202001101578626196695', '1240', '包子', '2.50', '2', 'http://XXX.jpg', '2020-01-10 11:16:36', '2020-01-10 11:16:36');
INSERT INTO `order_detail` VALUES ('202001101578626217058', '202001101578626217039', '1240', '包子', '2.50', '2', 'http://XXX.jpg', '2020-01-10 11:16:57', '2020-01-10 11:16:57');
INSERT INTO `order_detail` VALUES ('202001101578626457628', '202001101578626457612', '1240', '包子', '2.50', '2', 'http://XXX.jpg', '2020-01-10 11:20:57', '2020-01-10 11:20:57');
INSERT INTO `order_detail` VALUES ('202001101578626814380', '202001101578626814286', '1240', '包子', '2.50', '2', 'http://XXX.jpg', '2020-01-10 11:26:54', '2020-01-10 11:26:54');
INSERT INTO `order_detail` VALUES ('202001101578626857083', '202001101578626857075', '1240', '包子', '2.50', '2', 'http://XXX.jpg', '2020-01-10 11:27:37', '2020-01-10 11:27:37');
INSERT INTO `order_detail` VALUES ('202001101578626890291', '202001101578626890280', '1240', '包子', '2.50', '2', 'http://XXX.jpg', '2020-01-10 11:28:10', '2020-01-10 11:28:10');
INSERT INTO `order_detail` VALUES ('202001101578626906720', '202001101578626906697', '1240', '包子', '2.50', '2', 'http://XXX.jpg', '2020-01-10 11:28:26', '2020-01-10 11:28:26');
INSERT INTO `order_detail` VALUES ('202001101578634489993', '202001101578634489655', '1250', '豆腐脑', '2.60', '2', 'http://cdn.ibootstrap.cn/lorempixel.com/140/140/default.jpg', '2020-01-10 13:34:50', '2020-01-10 13:34:50');
INSERT INTO `order_detail` VALUES ('202001101578635080368', '202001101578635080363', '1250', '豆腐脑', '2.60', '2', 'http://cdn.ibootstrap.cn/lorempixel.com/140/140/default.jpg', '2020-01-10 13:44:40', '2020-01-10 13:44:40');
INSERT INTO `order_detail` VALUES ('202001101578635091933', '202001101578635091921', '1250', '豆腐脑', '2.60', '2', 'http://cdn.ibootstrap.cn/lorempixel.com/140/140/default.jpg', '2020-01-10 13:44:52', '2020-01-10 13:44:52');
INSERT INTO `order_detail` VALUES ('202001101578635320724', '202001101578635320213', '1250', '豆腐脑', '2.60', '2', 'http://cdn.ibootstrap.cn/lorempixel.com/140/140/default.jpg', '2020-01-10 13:48:41', '2020-01-10 13:48:41');
INSERT INTO `order_detail` VALUES ('202001101578635352389', '202001101578635352378', '1250', '豆腐脑', '2.60', '2', 'http://cdn.ibootstrap.cn/lorempixel.com/140/140/default.jpg', '2020-01-10 13:49:12', '2020-01-10 13:49:12');
INSERT INTO `order_detail` VALUES ('202001101578635378441', '202001101578635378429', '1250', '豆腐脑', '2.60', '2', 'http://cdn.ibootstrap.cn/lorempixel.com/140/140/default.jpg', '2020-01-10 13:49:38', '2020-01-10 13:49:38');
INSERT INTO `order_detail` VALUES ('202001131578880219331', '202001131578880219251', '1250', '豆腐脑', '2.60', '2', 'http://cdn.ibootstrap.cn/lorempixel.com/140/140/default.jpg', '2020-01-13 09:50:19', '2020-01-13 09:50:19');
INSERT INTO `order_detail` VALUES ('202001131578880280217', '202001131578880280198', '1250', '豆腐脑', '2.60', '2', 'http://cdn.ibootstrap.cn/lorempixel.com/140/140/default.jpg', '2020-01-13 09:51:20', '2020-01-13 09:51:20');
INSERT INTO `order_detail` VALUES ('202001131578880349495', '202001131578880349489', '1250', '豆腐脑', '2.60', '2', 'http://cdn.ibootstrap.cn/lorempixel.com/140/140/default.jpg', '2020-01-13 09:52:29', '2020-01-13 09:52:29');
INSERT INTO `order_detail` VALUES ('202001131578880382384', '202001131578880382378', '1250', '豆腐脑', '2.60', '2', 'http://cdn.ibootstrap.cn/lorempixel.com/140/140/default.jpg', '2020-01-13 09:53:02', '2020-01-13 09:53:02');
INSERT INTO `order_detail` VALUES ('202001131578881193141', '202001131578881192951', '1250', '豆腐脑', '2.60', '2', 'http://cdn.ibootstrap.cn/lorempixel.com/140/140/default.jpg', '2020-01-13 10:06:33', '2020-01-13 10:06:33');
INSERT INTO `order_detail` VALUES ('202001131578881356695', '202001131578881356177', '1250', '豆腐脑', '2.60', '2', 'http://cdn.ibootstrap.cn/lorempixel.com/140/140/default.jpg', '2020-01-13 10:09:17', '2020-01-13 10:09:17');
INSERT INTO `order_detail` VALUES ('202001131578881370950', '202001131578881370946', '1250', '豆腐脑', '2.60', '2', 'http://cdn.ibootstrap.cn/lorempixel.com/140/140/default.jpg', '2020-01-13 10:09:30', '2020-01-13 10:09:30');
INSERT INTO `order_detail` VALUES ('202001131578881394191', '202001131578881394179', '1250', '豆腐脑', '2.60', '2', 'http://cdn.ibootstrap.cn/lorempixel.com/140/140/default.jpg', '2020-01-13 10:09:54', '2020-01-13 10:09:54');
INSERT INTO `order_detail` VALUES ('202001131578882411571', '202001131578882411451', '1250', '豆腐脑', '2.60', '2', 'http://cdn.ibootstrap.cn/lorempixel.com/140/140/default.jpg', '2020-01-13 10:26:51', '2020-01-13 10:26:51');
INSERT INTO `order_detail` VALUES ('202001141578966929849', '202001141578966929572', '1220', '薯条', '5.00', '2', 'http://.xxx.jpg', '2020-01-14 09:55:30', '2020-01-14 09:55:30');
INSERT INTO `order_detail` VALUES ('212121', '102', '1230', '萝卜混沌', '88.80', '2', 'http://XXX.png', '2019-12-04 10:13:45', '2019-12-31 11:25:52');
INSERT INTO `order_detail` VALUES ('312121', '201912061575622112057', '1250', '五香包', '66.60', '1', 'http://XXX.png', '2019-12-04 10:14:47', '2019-12-31 11:26:06');
INSERT INTO `order_detail` VALUES ('412121', '103', '201912061575622112059', '素菜包', '55.60', '1', 'http://XXX.png', '2019-12-04 10:20:15', '2020-01-09 11:06:00');

-- ----------------------------
-- Table structure for order_master
-- ----------------------------
DROP TABLE IF EXISTS `order_master`;
CREATE TABLE `order_master` (
  `order_id` varchar(32) NOT NULL,
  `buyer_name` varchar(32) NOT NULL COMMENT '买家名字',
  `buyer_phone` varchar(32) NOT NULL COMMENT '买家电话',
  `buyer_address` varchar(128) NOT NULL COMMENT '买家地址',
  `buyer_openid` varchar(64) NOT NULL COMMENT '买家微信openid',
  `order_amount` decimal(8,2) NOT NULL COMMENT '订单总金额',
  `order_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '订单状态, 默认为新下单',
  `pay_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '支付状态, 默认未支付',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`order_id`),
  KEY `idx_buyer_openid` (`buyer_openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- ----------------------------
-- Records of order_master
-- ----------------------------
INSERT INTO `order_master` VALUES ('101', '李先生', '18812341111', '北京市政府', '001', '300.00', '0', '0', '2019-12-04 10:00:17', '2020-01-09 10:10:21');
INSERT INTO `order_master` VALUES ('102', '林先生', '18812342222', '北京海淀政府', '001', '177.60', '0', '0', '2019-12-04 10:01:55', '2020-01-02 10:15:54');
INSERT INTO `order_master` VALUES ('103', '刘先生', '18812344444', '北京昌平政府', '001', '122.20', '0', '0', '2019-12-04 10:17:22', '2020-01-02 10:15:55');
INSERT INTO `order_master` VALUES ('201912061575597902137', '李四', '18812343652', '北京顺义政府', '110121', '13.50', '0', '0', '2019-12-06 11:30:27', '2020-01-02 10:15:57');
INSERT INTO `order_master` VALUES ('201912061575622112052', '张三', '18812341026', '上海政府', '110122', '13.50', '0', '0', '2019-12-06 16:48:32', '2020-01-02 10:15:59');
INSERT INTO `order_master` VALUES ('201912061575622112053', '赵六', '110', '广东政府', '003', '10.25', '0', '0', '2019-12-31 10:10:54', '2019-12-31 16:05:22');
INSERT INTO `order_master` VALUES ('201912061575622112054', '李红', '111', '浙江政府', '004', '20.36', '0', '0', '2019-12-31 10:11:25', '2019-12-31 17:12:02');
INSERT INTO `order_master` VALUES ('201912061575622112055', '林奇', '222', '香港政府', '005', '10.14', '0', '0', '2019-12-31 10:11:55', '2019-12-31 10:11:55');
INSERT INTO `order_master` VALUES ('201912061575622112056', '洪七', '333', '澳门政府', '006', '5.36', '0', '0', '2019-12-31 10:12:22', '2019-12-31 10:12:22');
INSERT INTO `order_master` VALUES ('201912061575622112057', '郑琦', '444', '台湾政府', '007', '6.36', '0', '0', '2019-12-31 10:13:05', '2019-12-31 10:13:05');
INSERT INTO `order_master` VALUES ('201912061575622112058', '王思', '555', '江苏政府', '008', '9.63', '0', '0', '2019-12-31 10:14:26', '2019-12-31 10:14:26');
INSERT INTO `order_master` VALUES ('201912061575622112059', '林启发', '666', '杭州政府', '009', '63.96', '0', '0', '2019-12-31 10:14:56', '2019-12-31 10:14:56');
INSERT INTO `order_master` VALUES ('202001091578566766865', '王五', '18812341022', '上海嘉定政府', '110120', '7.50', '0', '0', '2020-01-09 18:46:08', '2020-01-09 18:46:08');
INSERT INTO `order_master` VALUES ('202001141578966929572', '科室1', '18812345555', '北京市', '1203456', '10.00', '0', '0', '2020-01-14 09:55:30', '2020-01-14 09:55:30');

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` double DEFAULT NULL,
  `name` varchar(765) DEFAULT NULL,
  `age` double DEFAULT NULL,
  `city` varchar(765) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES ('164', '就那么', '45', '北京');
INSERT INTO `person` VALUES ('171', '共同目标', '34', '北京');
INSERT INTO `person` VALUES ('172', '电饭锅', '23', '北京');
INSERT INTO `person` VALUES ('176', '团干部4', '2', '北京');
INSERT INTO `person` VALUES ('186', '快乐', '5', '北京');
INSERT INTO `person` VALUES ('196', '纺3', '2', '北京');
INSERT INTO `person` VALUES ('200', '看来有', '1', '北京');
INSERT INTO `person` VALUES ('201', '水电费', '1', '北京');
INSERT INTO `person` VALUES ('202', '急99', '2', '北京');
INSERT INTO `person` VALUES ('203', '分割789', '2', '北京');
INSERT INTO `person` VALUES ('215', '分', '23', '北京');
INSERT INTO `person` VALUES ('216', '如果56', '3', '北京');
INSERT INTO `person` VALUES ('218', 'lope', '23', '北京');
INSERT INTO `person` VALUES ('219', 'as', '2', '北京');
INSERT INTO `person` VALUES ('220', '昆明', '3', '北京');
INSERT INTO `person` VALUES ('221', '请问', '1', '北京');
INSERT INTO `person` VALUES ('222', 'VB你', '3', '北京');
INSERT INTO `person` VALUES ('224', '后面', '4', '北京');
INSERT INTO `person` VALUES ('225', '苦力怕', '4', '北京');
INSERT INTO `person` VALUES ('226', '某楼盘', '3', '北京');
INSERT INTO `person` VALUES ('228', '集合4', '4', '北京');
INSERT INTO `person` VALUES ('229', '没考虑到', '3', '北京');
INSERT INTO `person` VALUES ('231', '把你们', '3', '北京');
INSERT INTO `person` VALUES ('233', '吧', '3', '北京');
INSERT INTO `person` VALUES ('234', '电饭锅5', '2', '北京');
INSERT INTO `person` VALUES ('237', '水电费1', '3', '北京');
INSERT INTO `person` VALUES ('238', '好牛', '2', '北京');
INSERT INTO `person` VALUES ('240', '为人父', '2', '北京');
INSERT INTO `person` VALUES ('250', '方过后', '45', '就');
INSERT INTO `person` VALUES ('254', '好办3', '6', '北京');
INSERT INTO `person` VALUES ('262', '你254', '2', '北京');
INSERT INTO `person` VALUES ('273', '你我好啊', '4', '北京');
INSERT INTO `person` VALUES ('274', '祖国生日', '70', '北京');
INSERT INTO `person` VALUES ('278', '二十三', '34', '上海');
INSERT INTO `person` VALUES ('279', '二十二', '23', '上海');
INSERT INTO `person` VALUES ('285', '嗯好的', '11', '北京');
INSERT INTO `person` VALUES ('286', '二十三 是', '11', '北京');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(64) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(64) NOT NULL COMMENT '商品名称',
  `product_amount` int(64) NOT NULL COMMENT '商品数量',
  `product_status` tinyint(5) NOT NULL COMMENT '商品状态默认0,1上架0下架',
  `product_price` decimal(10,3) unsigned NOT NULL DEFAULT '1.100' COMMENT '单价',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_product_name` (`product_name`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8 COMMENT='商品表';

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('21', '紅薯5', '23', '0', '3.346', '2019-12-23 19:56:04', '2019-12-23 19:56:04');
INSERT INTO `product` VALUES ('23', '大米', '23', '0', '3.346', '2019-12-23 19:58:20', '2019-12-23 19:58:20');
INSERT INTO `product` VALUES ('24', '大米1', '23', '0', '3.346', '2019-12-23 19:59:10', '2019-12-23 19:59:10');
INSERT INTO `product` VALUES ('25', '大米2', '23', '0', '3.346', '2019-12-23 19:59:32', '2019-12-23 19:59:32');
INSERT INTO `product` VALUES ('26', '大米3', '23', '0', '3.346', '2019-12-23 20:01:12', '2019-12-23 20:01:12');
INSERT INTO `product` VALUES ('27', '香蕉5', '100', '1', '5.555', '2019-12-23 20:02:05', '2019-12-25 15:38:43');
INSERT INTO `product` VALUES ('28', '大米5', '23', '0', '3.346', '2019-12-23 20:02:36', '2019-12-23 20:02:36');
INSERT INTO `product` VALUES ('29', '香蕉2', '100', '1', '5.555', '2019-12-23 20:10:00', '2019-12-25 15:26:39');
INSERT INTO `product` VALUES ('30', '香蕉3', '100', '0', '5.555', '2019-12-24 09:36:42', '2019-12-25 15:32:16');
INSERT INTO `product` VALUES ('60', '小米6', '4', '1', '3.301', '2019-12-24 16:23:58', '2019-12-24 16:23:58');
INSERT INTO `product` VALUES ('61', '小米7', '4', '1', '3.301', '2019-12-24 16:35:25', '2019-12-24 16:35:25');
INSERT INTO `product` VALUES ('62', '小米8', '4', '1', '3.301', '2019-12-24 16:40:34', '2019-12-24 16:40:34');
INSERT INTO `product` VALUES ('63', '小米9', '4', '0', '3.301', '2019-12-24 18:32:32', '2019-12-24 18:32:32');
INSERT INTO `product` VALUES ('64', '油条11', '100', '1', '100.230', '2019-12-24 18:33:40', '2019-12-24 19:16:55');
INSERT INTO `product` VALUES ('65', '油条10', '100', '1', '100.230', '2019-12-24 18:43:00', '2019-12-24 19:14:25');
INSERT INTO `product` VALUES ('66', '油条0', '100', '1', '100.230', '2019-12-24 18:51:14', '2019-12-24 19:10:23');
INSERT INTO `product` VALUES ('67', '油条4', '4', '0', '3.000', '2019-12-24 19:36:14', '2019-12-24 19:36:14');
INSERT INTO `product` VALUES ('68', '油条1', '4', '0', '33.000', '2019-12-24 19:36:46', '2019-12-24 19:36:46');
INSERT INTO `product` VALUES ('69', '油条2', '4', '0', '33.100', '2019-12-24 19:46:36', '2019-12-24 19:46:36');
INSERT INTO `product` VALUES ('70', '油条3', '4', '0', '55.060', '2019-12-24 19:51:47', '2019-12-24 19:51:47');
INSERT INTO `product` VALUES ('71', '油条5', '4', '1', '55.070', '2019-12-24 19:52:14', '2019-12-25 09:18:24');
INSERT INTO `product` VALUES ('72', '油条6', '4', '1', '0.030', '2019-12-24 19:53:53', '2019-12-25 09:17:13');
INSERT INTO `product` VALUES ('73', '好好6', '100', '0', '5.555', '2019-12-25 15:12:48', '2019-12-25 15:12:48');
INSERT INTO `product` VALUES ('74', '香蕉0', '100', '0', '5.555', '2019-12-25 15:21:09', '2019-12-25 15:21:09');
INSERT INTO `product` VALUES ('75', '香蕉4', '100', '0', '5.555', '2019-12-25 15:36:30', '2019-12-25 15:36:30');
INSERT INTO `product` VALUES ('78', '香蕉77', '100', '1', '5.555', '2019-12-26 18:55:36', '2019-12-26 19:09:37');
INSERT INTO `product` VALUES ('79', '2香蕉', '100', '0', '5.555', '2019-12-27 10:04:45', '2019-12-27 10:04:45');
INSERT INTO `product` VALUES ('80', '3香蕉', '100', '0', '5.555', '2019-12-27 10:05:02', '2019-12-27 10:05:02');
INSERT INTO `product` VALUES ('81', '4香蕉', '100', '0', '5.555', '2019-12-27 10:07:55', '2019-12-27 10:07:55');
INSERT INTO `product` VALUES ('82', '5香蕉', '100', '0', '5.555', '2019-12-27 10:13:30', '2019-12-27 10:13:30');
INSERT INTO `product` VALUES ('83', '789香蕉', '100', '1', '6.555', '2019-12-27 10:28:21', '2019-12-27 10:29:08');

-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category` (
  `category_id` int(64) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(64) NOT NULL COMMENT '类目名字',
  `category_type` int(32) NOT NULL COMMENT '类目编号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COMMENT='类目表';

-- ----------------------------
-- Records of product_category
-- ----------------------------
INSERT INTO `product_category` VALUES ('1', '热销榜', '6', '2019-11-14 10:27:43', '2020-01-06 14:00:20');
INSERT INTO `product_category` VALUES ('2', '女生区', '17', '2019-11-28 11:00:47', '2020-01-06 13:44:41');
INSERT INTO `product_category` VALUES ('3', '男生区', '9', '2019-11-28 11:06:53', '2020-01-06 11:00:27');
INSERT INTO `product_category` VALUES ('4', '好喝的', '18', '2019-12-02 15:27:13', '2020-01-06 15:31:15');
INSERT INTO `product_category` VALUES ('5', '好吃', '5', '2019-12-02 15:56:23', '2020-01-06 13:44:11');
INSERT INTO `product_category` VALUES ('17', '好吃', '7', '2020-01-14 13:27:12', '2020-01-14 13:53:35');
INSERT INTO `product_category` VALUES ('18', '好吃不错', '8', '2020-01-14 13:27:29', '2020-01-14 13:27:29');

-- ----------------------------
-- Table structure for product_info
-- ----------------------------
DROP TABLE IF EXISTS `product_info`;
CREATE TABLE `product_info` (
  `product_id` varchar(32) NOT NULL,
  `product_name` varchar(64) NOT NULL COMMENT '商品名称',
  `product_price` decimal(8,2) NOT NULL COMMENT '单价',
  `product_stock` int(11) NOT NULL COMMENT '库存',
  `product_description` varchar(64) DEFAULT NULL COMMENT '描述',
  `product_icon` varchar(512) DEFAULT NULL COMMENT '小图',
  `product_status` tinyint(3) unsigned zerofill DEFAULT '000' COMMENT '商品状态,0正常1下架',
  `category_type` int(11) NOT NULL COMMENT '类目编号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品表';

-- ----------------------------
-- Records of product_info
-- ----------------------------
INSERT INTO `product_info` VALUES ('1220', '薯条', '1.70', '58', '吃薯条', 'http://.xxx.jpg', '000', '7', '2020-01-02 14:27:04', '2020-01-14 09:55:30');
INSERT INTO `product_info` VALUES ('1230', '油条', '1.50', '185', '营养早餐油条', 'http://XXX.jpg', '000', '9', '2019-12-02 18:58:48', '2020-01-09 18:46:08');
INSERT INTO `product_info` VALUES ('12340', '豆浆', '2.00', '109', '营养豆浆', 'http://XXX.jpg', '000', '8', '2019-12-02 19:09:49', '2020-01-02 16:13:52');
INSERT INTO `product_info` VALUES ('1240', '包子', '2.50', '8', '吃包子', 'http://XXX.jpg', '000', '9', '2020-01-02 14:26:21', '2020-01-10 11:28:26');
INSERT INTO `product_info` VALUES ('1250', '豆腐脑', '2.60', '162', '好吃豆腐脑', 'http://cdn.ibootstrap.cn/lorempixel.com/140/140/default.jpg', '000', '7', '2019-12-03 10:29:21', '2020-01-13 10:26:51');
INSERT INTO `product_info` VALUES ('202001031578034299396', '米粥', '2.35', '250', '味道不错米粥', '', '000', '6', '2020-01-03 14:51:39', '2020-01-03 14:53:19');
INSERT INTO `product_info` VALUES ('202001031578034859117', '八宝粥', '5.60', '120', '好喝养胃', '', '000', '8', '2020-01-03 15:00:59', '2020-01-03 15:00:59');

-- ----------------------------
-- Table structure for seller_info
-- ----------------------------
DROP TABLE IF EXISTS `seller_info`;
CREATE TABLE `seller_info` (
  `seller_id` varchar(32) NOT NULL,
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `openid` varchar(64) NOT NULL COMMENT '微信openid',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`seller_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='卖家信息表';

-- ----------------------------
-- Records of seller_info
-- ----------------------------
INSERT INTO `seller_info` VALUES ('202001071578363434057', '李四', 'lisi', '121', '2020-01-07 10:17:14', '2020-01-07 10:17:14');
INSERT INTO `seller_info` VALUES ('202001071578363546587', '王五', 'wangwu', '122', '2020-01-07 10:19:06', '2020-01-07 10:19:06');
INSERT INTO `seller_info` VALUES ('202001071578363860145', '赵六', 'zhaoliu', '132', '2020-01-07 10:24:23', '2020-01-07 10:24:23');

-- ----------------------------
-- Table structure for wechatauthorization
-- ----------------------------
DROP TABLE IF EXISTS `wechatauthorization`;
CREATE TABLE `wechatauthorization` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `access_token` varchar(255) NOT NULL,
  `expires_in` int(128) NOT NULL,
  `refresh_token` varchar(255) NOT NULL,
  `scope` varchar(128) NOT NULL,
  `openid` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of wechatauthorization
-- ----------------------------
