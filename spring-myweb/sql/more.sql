/*
Navicat MySQL Data Transfer

Source Server         : local-mysql
Source Server Version : 50730
Source Host           : localhost:3306
Source Database       : more

Target Server Type    : MYSQL
Target Server Version : 50730
File Encoding         : 65001

Date: 2020-09-17 15:16:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `author` varchar(20) NOT NULL,
  `content` varchar(255) NOT NULL,
  `is_delete` int(1) DEFAULT '0' COMMENT '逻辑删除',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('10', '1', '1', '1', '1', '2020-08-11 08:26:37', '2020-08-11 08:42:07');
INSERT INTO `article` VALUES ('11', '1', '1', '1', '0', '2020-08-12 01:09:57', '2020-08-12 01:09:57');
