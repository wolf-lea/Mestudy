/*
Navicat MySQL Data Transfer

Source Server         : Local
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : dbutils

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2018-11-08 09:52:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customerName` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('1', 'customer001', 'NyGEkhZK@xxx.com');

-- ----------------------------
-- Table structure for t_measure
-- ----------------------------
DROP TABLE IF EXISTS `t_measure`;
CREATE TABLE `t_measure` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `no` varchar(20) DEFAULT NULL,
  `As_` float(10,5) DEFAULT NULL,
  `Cr` float(10,5) DEFAULT NULL,
  `Cd` float(10,5) DEFAULT NULL,
  `Cu` float(10,5) DEFAULT NULL,
  `Pb` float(10,5) DEFAULT NULL,
  `Zn` float(10,5) DEFAULT NULL,
  `Ni` float(10,5) DEFAULT NULL,
  `Hg` float(10,5) DEFAULT NULL,
  `Sn` float(10,5) DEFAULT NULL,
  `Co` float(10,5) DEFAULT NULL,
  `Ag` float(10,5) DEFAULT NULL,
  `Sb` float(10,5) DEFAULT NULL,
  `Ba` float(10,5) DEFAULT NULL,
  `Mg` float(10,5) DEFAULT NULL,
  `Ti` float(10,5) DEFAULT NULL,
  `W` float(10,5) DEFAULT NULL,
  `Al` float(10,5) DEFAULT NULL,
  `Th` float(10,5) DEFAULT NULL,
  `Sr` float(10,5) DEFAULT NULL,
  `Cs` float(10,5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_measure
-- ----------------------------
INSERT INTO `t_measure` VALUES ('1', '52326', '660.39618', '37.81060', '269.11520', '291.24765', '730.80164', '858.28662', '445.36926', '262.55322', '551.54779', '480.92419', '699.06421', '726.05615', '80.54485', '605.38989', '738.49719', '83.71710', '834.40735', '704.79639', '254.11414', '708.55627');
INSERT INTO `t_measure` VALUES ('2', '76125', '132.10745', '998.80884', '508.71432', '166.17819', '95.02691', '808.60443', '197.95297', '195.99493', '982.41351', '644.62842', '914.33246', '293.13535', '691.16345', '9.71389', '509.71497', '541.76501', '475.04678', '465.97290', '788.65894', '295.32108');
INSERT INTO `t_measure` VALUES ('3', '29137', '629.29169', '889.63892', '59.54028', '997.13757', '633.56372', '60.16537', '703.44629', '326.17474', '786.44415', '721.94189', '562.21161', '427.82458', '783.20050', '28.17808', '366.15619', '751.76526', '535.49506', '946.72083', '773.76910', '909.90125');
INSERT INTO `t_measure` VALUES ('4', '13905', '795.30945', '901.47375', '67.57024', '668.98749', '87.42620', '656.06171', '284.88351', '725.73077', '838.09662', '956.70941', '730.66571', '160.14281', '818.00592', '164.65881', '763.19208', '349.59851', '211.29555', '351.34219', '607.24127', '846.44244');
INSERT INTO `t_measure` VALUES ('5', '32359', '227.23686', '938.82520', '370.77792', '677.45966', '490.95511', '397.88339', '827.87720', '349.42657', '129.85066', '891.52063', '124.16942', '13.42828', '446.58554', '582.59399', '439.55057', '710.74969', '360.51645', '271.93204', '528.80109', '539.73871');
INSERT INTO `t_measure` VALUES ('6', '25634', '761.64838', '970.10968', '354.59253', '180.10989', '763.27509', '372.16891', '145.27827', '507.82492', '585.38318', '462.45004', '526.78278', '229.41928', '136.54794', '753.20654', '356.58963', '951.13165', '802.28027', '593.28668', '304.05295', '60.27342');
INSERT INTO `t_measure` VALUES ('7', '97649', '898.14661', '200.62775', '227.58719', '300.75891', '444.33105', '808.73944', '973.69788', '732.59778', '647.21826', '976.15460', '350.89236', '477.49207', '622.26025', '899.73737', '883.66357', '239.52039', '494.88443', '45.84525', '279.07120', '416.77652');
INSERT INTO `t_measure` VALUES ('8', '33975', '489.34052', '474.55322', '651.15717', '913.72095', '589.74097', '550.63654', '688.94464', '738.96747', '187.39558', '726.88153', '734.40692', '291.45044', '816.26868', '528.70728', '343.79688', '490.65244', '199.53064', '877.68542', '841.55481', '731.42822');
INSERT INTO `t_measure` VALUES ('9', '73262', '369.77444', '585.43646', '71.56656', '940.57867', '799.29565', '705.78595', '843.98596', '612.60461', '613.84229', '532.84467', '216.03130', '877.73718', '84.02659', '141.70609', '472.06815', '525.66650', '854.58527', '6.66288', '292.16290', '464.39575');
INSERT INTO `t_measure` VALUES ('10', '84515', '191.87715', '324.93005', '296.36963', '707.25146', '705.27826', '763.09296', '125.28978', '150.54854', '835.42151', '509.31256', '427.51028', '924.00287', '920.92297', '157.53490', '279.91388', '905.84186', '538.21893', '809.05206', '100.42703', '541.38080');
INSERT INTO `t_measure` VALUES ('11', '13877', '146.35683', '42.75708', '40.18117', '632.98743', '787.01099', '552.96082', '563.66895', '206.11972', '724.37866', '339.58701', '390.35693', '345.69147', '911.82709', '657.41174', '466.47137', '349.04248', '918.33148', '924.51379', '617.50482', '919.93475');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '73397', '55');
INSERT INTO `user` VALUES ('2', '00493', '30');
INSERT INTO `user` VALUES ('3', '77979', '30');
INSERT INTO `user` VALUES ('4', '28525', '93');
INSERT INTO `user` VALUES ('5', '83417', '71');
INSERT INTO `user` VALUES ('6', '62893', '16');
INSERT INTO `user` VALUES ('7', '42901', '23');
INSERT INTO `user` VALUES ('8', '78312', '97');
INSERT INTO `user` VALUES ('9', '98643', '93');
INSERT INTO `user` VALUES ('10', '20982', '28');
INSERT INTO `user` VALUES ('11', '31441', '81');
INSERT INTO `user` VALUES ('12', '23744', '69');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'username_3062461', '684261');
INSERT INTO `users` VALUES ('2', 'username_48049', '540037');
INSERT INTO `users` VALUES ('3', 'username_248082', '46629390');
INSERT INTO `users` VALUES ('4', 'username_669', '91419');
INSERT INTO `users` VALUES ('5', 'username_88', '22088834');
INSERT INTO `users` VALUES ('6', 'username_804633984', '739571');
INSERT INTO `users` VALUES ('7', 'username_31937', '676859305');
INSERT INTO `users` VALUES ('8', 'username_96881', '441819268');
INSERT INTO `users` VALUES ('9', 'username_650', '44691632');
INSERT INTO `users` VALUES ('10', 'username_25629', '377962');
INSERT INTO `users` VALUES ('11', 'username_3083498', '577565');
INSERT INTO `users` VALUES ('12', 'username_2374', '906437463');
INSERT INTO `users` VALUES ('13', 'username_526', '625298');
INSERT INTO `users` VALUES ('14', 'username_94561', '9823543');
INSERT INTO `users` VALUES ('15', 'username_016819', '27728755');
INSERT INTO `users` VALUES ('16', 'username_347180316', '88787');
INSERT INTO `users` VALUES ('17', 'username_365756880', '337848');
INSERT INTO `users` VALUES ('18', 'username_43430519', '42012');
INSERT INTO `users` VALUES ('19', 'username_434487713', '480830724');
INSERT INTO `users` VALUES ('20', 'username_74895', '036053633');
INSERT INTO `users` VALUES ('21', 'username_12460581', '469460790');
INSERT INTO `users` VALUES ('22', 'username_52', '39168');
INSERT INTO `users` VALUES ('23', 'username_6842296', '886913');
INSERT INTO `users` VALUES ('24', 'username_47215', '0018680');
