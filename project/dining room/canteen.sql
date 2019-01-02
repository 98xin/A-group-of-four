/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50506
Source Host           : localhost:3306
Source Database       : canteen

Target Server Type    : MYSQL
Target Server Version : 50506
File Encoding         : 65001

Date: 2018-12-10 16:05:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `assess`
-- ----------------------------
DROP TABLE IF EXISTS `assess`;
CREATE TABLE `assess` (
  `assess_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `assess_content` varchar(50) DEFAULT NULL,
  `student_id` int(10) NOT NULL,
  `food_id` int(10) NOT NULL,
  PRIMARY KEY (`assess_id`,`student_id`,`food_id`),
  KEY `student_id` (`student_id`),
  KEY `food_id` (`food_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of assess
-- ----------------------------
INSERT INTO `assess` VALUES ('1', '非常好喝！！！', '1', '1');

-- ----------------------------
-- Table structure for `attendant`
-- ----------------------------
DROP TABLE IF EXISTS `attendant`;
CREATE TABLE `attendant` (
  `attendant_id` int(10) NOT NULL AUTO_INCREMENT,
  `attendant_name` varchar(20) NOT NULL,
  `attendant_password` varchar(20) NOT NULL,
  PRIMARY KEY (`attendant_id`),
  KEY `attendant_id` (`attendant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of attendant
-- ----------------------------
INSERT INTO `attendant` VALUES ('1', '大强', '1234');
INSERT INTO `attendant` VALUES ('2', '小强', '2345');
INSERT INTO `attendant` VALUES ('3', '大美', '3456');
INSERT INTO `attendant` VALUES ('4', '小美', '4567');
INSERT INTO `attendant` VALUES ('5', '大白', '5678');
INSERT INTO `attendant` VALUES ('6', '小白', '6789');
INSERT INTO `attendant` VALUES ('7', '大黑', '7890');
INSERT INTO `attendant` VALUES ('8', '小黑', '0123');

-- ----------------------------
-- Table structure for `ballot`
-- ----------------------------
DROP TABLE IF EXISTS `ballot`;
CREATE TABLE `ballot` (
  `ballot_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ballot_num` int(10) DEFAULT NULL,
  `food_name` varchar(20) DEFAULT NULL,
  `food_id` int(10) NOT NULL,
  `ballot_time` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ballot_id`),
  KEY `food_name` (`food_name`),
  KEY `food_id` (`food_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ballot
-- ----------------------------
INSERT INTO `ballot` VALUES ('1', '122', '三杯鸡', '24', '2018-12-11');

-- ----------------------------
-- Table structure for `cart`
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `student_id` int(10) NOT NULL,
  `food_id` int(10) NOT NULL,
  PRIMARY KEY (`student_id`,`food_id`),
  KEY `food_id` (`food_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO `cart` VALUES ('1', '1');

-- ----------------------------
-- Table structure for `detail`
-- ----------------------------
DROP TABLE IF EXISTS `detail`;
CREATE TABLE `detail` (
  `detail_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `student_id` int(10) NOT NULL,
  `food_id` int(10) NOT NULL,
  `food_name` varchar(20) DEFAULT NULL,
  `order_id` int(10) NOT NULL,
  PRIMARY KEY (`detail_id`),
  KEY `student_id` (`student_id`),
  KEY `food_id` (`food_id`),
  KEY `food_name` (`food_name`),
  KEY `order_id` (`order_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of detail
-- ----------------------------
INSERT INTO `detail` VALUES ('1', '1', '1', '冰糖雪梨', '1');
INSERT INTO `detail` VALUES ('2', '1', '12', '炒饼', '1');
INSERT INTO `detail` VALUES ('3', '1', '21', '金针菇', '1');

-- ----------------------------
-- Table structure for `food`
-- ----------------------------
DROP TABLE IF EXISTS `food`;
CREATE TABLE `food` (
  `food_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `food_price` varchar(20) DEFAULT NULL,
  `food_name` varchar(20) DEFAULT NULL,
  `food_image` varchar(50) DEFAULT NULL,
  `foodtype_id` int(10) NOT NULL,
  `attendant_id` int(10) NOT NULL,
  PRIMARY KEY (`food_id`),
  KEY `foodtype_id` (`foodtype_id`),
  KEY `attendant_id` (`attendant_id`)
) ENGINE=MyISAM AUTO_INCREMENT=90 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of food
-- ----------------------------
INSERT INTO `food` VALUES ('1', '4', ' 冰糖雪梨', 'images/yinpin/btxl.jpg', '1', '1');
INSERT INTO `food` VALUES ('2', '3', '橙汁', 'images/yinpin/cz.jpg', '1', '1');
INSERT INTO `food` VALUES ('3', '2', '豆浆', 'images/yinpin/dj.jpg', '1', '1');
INSERT INTO `food` VALUES ('4', '5', '蜂蜜柚子', 'images/yinpin/fmyz.png', '1', '1');
INSERT INTO `food` VALUES ('5', '3', '可乐', 'images/yinpin/kl.jpeg', '1', '1');
INSERT INTO `food` VALUES ('6', '4', '奶茶', 'images/yinpin/nc.jpg', '1', '1');
INSERT INTO `food` VALUES ('7', '3', '柠檬水', 'images/yinpin/nms.jpg', '1', '1');
INSERT INTO `food` VALUES ('8', '3', '牛奶', 'images/yinpin/nn.jpg', '1', '1');
INSERT INTO `food` VALUES ('9', '3', '酸奶', 'images/yinpin/sn.jpg', '1', '1');
INSERT INTO `food` VALUES ('10', '4', '西瓜汁', 'images/yinpin/xgz.jpg', '1', '1');
INSERT INTO `food` VALUES ('11', '4', '芒果汁', 'images/yinpin/mgz.jpg', '1', '1');
INSERT INTO `food` VALUES ('12', '4', '炒饼', 'images/chaocai/cb.jpeg', '2', '2');
INSERT INTO `food` VALUES ('13', '4', '炒饭', 'images/chaocai/cf.jpg', '2', '2');
INSERT INTO `food` VALUES ('14', '4', '炒面', 'images/chaocai/cm.jpg', '2', '2');
INSERT INTO `food` VALUES ('15', '5', '豆干炒肉', 'images/chaocai/dgcr.jpg', '2', '2');
INSERT INTO `food` VALUES ('16', '3', '豆角', 'images/chaocai/dj.jpg', '2', '2');
INSERT INTO `food` VALUES ('17', '5', '肥牛', 'images/chaocai/fn.jpg', '2', '2');
INSERT INTO `food` VALUES ('18', '3', '干煸豆角', 'images/chaocai/gbdj.jpg', '2', '2');
INSERT INTO `food` VALUES ('19', '3', '花椰菜', 'images/chaocai/hyc.jpg', '2', '2');
INSERT INTO `food` VALUES ('20', '5', '京酱肉丝', 'images/chaocai/jjrs.jpg', '2', '2');
INSERT INTO `food` VALUES ('21', '4', '金针菇', 'images/chaocai/jzg.jpg', '2', '2');
INSERT INTO `food` VALUES ('22', '5', '梅菜扣肉', 'images/chaocai/mckr.jpeg', '2', '2');
INSERT INTO `food` VALUES ('23', '5', '肉末茄子', 'images/chaocai/rmqz.jpg', '2', '2');
INSERT INTO `food` VALUES ('24', '6', '三杯鸡', 'images/chaocai/sbj.jpg', '2', '2');
INSERT INTO `food` VALUES ('25', '3', '蒜蓉青菜', 'images/chaocai/srqc.jpeg', '2', '2');
INSERT INTO `food` VALUES ('26', '5', '糖醋里脊', 'images/chaocai/tclj.jpg', '2', '2');
INSERT INTO `food` VALUES ('27', '4', '土豆', 'images/chaocai/td.jpg', '2', '2');
INSERT INTO `food` VALUES ('28', '4', '小炒肉', 'images/chaocai/xcr.jpg', '2', '2');
INSERT INTO `food` VALUES ('29', '3', '西红柿炒鸡蛋', 'images/chaocai/xhs.jpg', '2', '2');
INSERT INTO `food` VALUES ('30', '6', '西葫虾仁', 'images/chaocai/xhxr.jpg', '2', '2');
INSERT INTO `food` VALUES ('31', '3', '西兰花', 'images/chaocai/xlh.jpg', '2', '2');
INSERT INTO `food` VALUES ('32', '6', '香酥鱼', 'images/chaocai/xsy.jpg', '2', '2');
INSERT INTO `food` VALUES ('33', '5', '鱼香肉丝', 'images/chaocai/yxrs.jpg', '2', '2');
INSERT INTO `food` VALUES ('34', '5', '猪肉炖粉条', 'images/chaocai/zrdft.jpg', '2', '2');
INSERT INTO `food` VALUES ('35', '5', '安徽板面', 'images/miantiao/ahbm.jpg', '3', '3');
INSERT INTO `food` VALUES ('36', '5', '重庆小面', 'images/miantiao/cqxm.jpg', '3', '3');
INSERT INTO `food` VALUES ('37', '5', '担担面', 'images/miantiao/ddm.jpg', '3', '3');
INSERT INTO `food` VALUES ('38', '4', '打卤面', 'images/miantiao/dlm1.jpg', '3', '3');
INSERT INTO `food` VALUES ('39', '4', '刀削面', 'images/miantiao/dxm1.jpg', '3', '3');
INSERT INTO `food` VALUES ('40', '5', '热干面', 'images/miantiao/rgm.jpg', '3', '3');
INSERT INTO `food` VALUES ('41', '5', '油泼面', 'images/miantiao/yp.jpg', '3', '3');
INSERT INTO `food` VALUES ('42', '4', '炸酱面', 'images/miantiao/zjm.jpg', '3', '3');
INSERT INTO `food` VALUES ('43', '8', '麦辣鸡腿汉堡', 'images/hanbao/hb1.jpg', '4', '4');
INSERT INTO `food` VALUES ('44', '6', '汉堡包', 'images/hanbao/hb2.jpg', '4', '4');
INSERT INTO `food` VALUES ('45', '8', '麦香鱼', 'images/hanbao/hb3.jpg', '4', '4');
INSERT INTO `food` VALUES ('46', '10', '巨无霸', 'images/hanbao/hb4.jpg', '4', '4');
INSERT INTO `food` VALUES ('47', '6', '吉士汉堡', 'images/hanbao/hb5.jpg', '4', '4');
INSERT INTO `food` VALUES ('48', '10', '珍萃麦辣鸡腿汉堡', 'images/hanbao/hb6.jpg', '4', '4');
INSERT INTO `food` VALUES ('49', '8', '鸡块', 'images/hanbao/jk.jpg', '4', '4');
INSERT INTO `food` VALUES ('50', '10', '炸鸡翅', 'images/hanbao/zjc.jpg', '4', '4');
INSERT INTO `food` VALUES ('51', '6', '薯条', 'images/hanbao/st.jpg', '4', '4');
INSERT INTO `food` VALUES ('52', '4', '八宝粥', 'images/zhou/bbz.jpg', '5', '5');
INSERT INTO `food` VALUES ('53', '3', '黑米粥', 'images/zhou/hmz.jpg', '5', '5');
INSERT INTO `food` VALUES ('54', '2', '南瓜粥', 'images/zhou/ngz.jpg', '5', '5');
INSERT INTO `food` VALUES ('55', '3', '皮蛋瘦肉粥', 'images/zhou/pdsrz.jpg', '5', '5');
INSERT INTO `food` VALUES ('56', '2', '糖粥', 'images/zhou/tz.jpg', '5', '5');
INSERT INTO `food` VALUES ('57', '4', '香菇粥', 'images/zhou/xgz.png', '5', '5');
INSERT INTO `food` VALUES ('58', '5', '虾仁粥', 'images/zhou/xrz.jpg', '5', '5');
INSERT INTO `food` VALUES ('59', '2', '玉米粥', 'images/zhou/ymz.jpg', '5', '5');
INSERT INTO `food` VALUES ('60', '2', '海带', 'images/liangcai/hd.jpg', '6', '6');
INSERT INTO `food` VALUES ('61', '2', '黄瓜', 'images/liangcai/hg.jpg', '6', '6');
INSERT INTO `food` VALUES ('62', '4', '金针菇', 'images/liangcai/jzg.jpg', '6', '6');
INSERT INTO `food` VALUES ('63', '2', '凉粉', 'images/liangcai/lf.jpg', '6', '6');
INSERT INTO `food` VALUES ('64', '2', '木耳', 'images/liangcai/mer.jpg', '6', '6');
INSERT INTO `food` VALUES ('65', '2', '土豆丝', 'images/liangcai/tds.jpg', '6', '6');
INSERT INTO `food` VALUES ('66', '4', '猪耳朵', 'images/liangcai/zed.jpg', '6', '6');
INSERT INTO `food` VALUES ('67', '6', '鸡腿', 'images/luwei/jt.jpg', '7', '7');
INSERT INTO `food` VALUES ('68', '5', '鸡爪', 'images/luwei/jz.jpg', '7', '7');
INSERT INTO `food` VALUES ('69', '5', '鸡胗', 'images/luwei/jzh.jpg', '7', '7');
INSERT INTO `food` VALUES ('70', '3', '卤蛋', 'images/luwei/ld.jpg', '7', '7');
INSERT INTO `food` VALUES ('71', '3', '藕', 'images/luwei/ou.jpg', '7', '7');
INSERT INTO `food` VALUES ('72', '5', '鸭脖', 'images/luwei/yb.jpg', '7', '7');
INSERT INTO `food` VALUES ('73', '5', '鸭翅', 'images/luwei/yc.jpg', '7', '7');
INSERT INTO `food` VALUES ('74', '5', '鸭肠', 'images/luwei/ych.jpg', '7', '7');
INSERT INTO `food` VALUES ('75', '1', '三鲜包', 'images/mianshi/b1.jpg', '8', '8');
INSERT INTO `food` VALUES ('76', '1', '猪肉大葱包', 'images/mianshi/bz1.jpg', '8', '8');
INSERT INTO `food` VALUES ('77', '1', '香菇鸡肉包', 'images/mianshi/bz2.jpg', '8', '8');
INSERT INTO `food` VALUES ('78', '1', '豆角肉包', 'images/mianshi/bz3.jpg', '8', '8');
INSERT INTO `food` VALUES ('79', '1', '韭菜鸡蛋包', 'images/mianshi/bz4.jpg', '8', '8');
INSERT INTO `food` VALUES ('80', '1', '煎饺', 'images/mianshi/jz2.jpg', '8', '8');
INSERT INTO `food` VALUES ('81', '8', '猪肉大葱饺', 'images/mianshi/sj.jpg', '8', '8');
INSERT INTO `food` VALUES ('82', '8', '萝卜鸡肉饺', 'images/mianshi/sj1.jpg', '8', '8');
INSERT INTO `food` VALUES ('83', '8', '韭菜鸡蛋饺', 'images/mianshi/sj2.jpg', '8', '8');
INSERT INTO `food` VALUES ('84', '8', '香菇鸡肉饺', 'images/mianshi/sj3.jpg', '8', '8');
INSERT INTO `food` VALUES ('85', '8', '牛肉饺', 'images/mianshi/sj4.jpg', '8', '8');
INSERT INTO `food` VALUES ('86', '8', '芹菜肉饺', 'images/mianshi/sj5.jpg', '8', '8');
INSERT INTO `food` VALUES ('87', '7', '水煎包', 'images/mianshi/sjb.jpg', '8', '8');
INSERT INTO `food` VALUES ('88', '6', '肉蒸饺', 'images/mianshi/zj.jpg', '8', '8');
INSERT INTO `food` VALUES ('89', '5', '素蒸饺', 'images/mianshi/zj1.jpg', '8', '8');

-- ----------------------------
-- Table structure for `foodtype`
-- ----------------------------
DROP TABLE IF EXISTS `foodtype`;
CREATE TABLE `foodtype` (
  `foodtype_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `foodtype_name` varchar(20) NOT NULL,
  PRIMARY KEY (`foodtype_id`),
  KEY `foodtype_id` (`foodtype_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of foodtype
-- ----------------------------
INSERT INTO `foodtype` VALUES ('1', '饮料甜品');
INSERT INTO `foodtype` VALUES ('2', '炒菜');
INSERT INTO `foodtype` VALUES ('3', '面条');
INSERT INTO `foodtype` VALUES ('4', '炸鸡汉堡');
INSERT INTO `foodtype` VALUES ('5', '粉面粥');
INSERT INTO `foodtype` VALUES ('6', '凉菜');
INSERT INTO `foodtype` VALUES ('7', '卤味');
INSERT INTO `foodtype` VALUES ('8', '面食');

-- ----------------------------
-- Table structure for `order`
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `order_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `order_tp` varchar(20) DEFAULT NULL,
  `order_statement` varchar(20) DEFAULT NULL,
  `order_time` varchar(20) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `student_id` (`student_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('1', '12', '已收货', '2018-12-02', '1');

-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `student_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `student_name` varchar(20) NOT NULL,
  `student_password` varchar(20) NOT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '2016111001', '123');
INSERT INTO `student` VALUES ('2', '2016111002', '234');
INSERT INTO `student` VALUES ('3', '2016111003', '345');
INSERT INTO `student` VALUES ('4', '2016111004', '456');
