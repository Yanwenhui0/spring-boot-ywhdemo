/*
Navicat MySQL Data Transfer

Source Server         : local-mysql
Source Server Version : 50730
Source Host           : localhost:3306
Source Database       : quartz

Target Server Type    : MYSQL
Target Server Version : 50730
File Encoding         : 65001

Date: 2020-09-17 20:24:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `schedule_job`;
CREATE TABLE `schedule_job` (
  `job_id` varchar(32) NOT NULL,
  `job_name` varchar(50) NOT NULL,
  `cron_expression` varchar(20) DEFAULT NULL,
  `bean_name` varchar(50) DEFAULT NULL,
  `method_name` varchar(20) DEFAULT NULL,
  `status` int(1) DEFAULT '2',
  `delete_flag` int(1) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of schedule_job
-- ----------------------------
INSERT INTO `schedule_job` VALUES ('29c9dfcd7496fa28e42136316d7a5334', 'log3', '/10 * * * * ?', 'com.springboot.quartz.task.TestTask', 'oneTask', '1', '0', '2020-09-17 11:21:39', '2020-09-17 11:33:34');
INSERT INTO `schedule_job` VALUES ('bd158d214ee77a53d283376c98c7a1e0', 'log2', '/10 * * * * ?', 'com.springboot.quartz.task.TestTask', 'oneTask', '1', '0', '2020-09-17 11:18:49', '2020-09-17 11:33:34');
INSERT INTO `schedule_job` VALUES ('f29b1b61dd5bb68a70c82c93f19d9496', 'log1', '/10 * * * * ?', 'com.springboot.quartz.task.TestTask', 'oneTask', '1', '0', '2020-09-16 17:38:44', '2020-09-17 11:33:34');
