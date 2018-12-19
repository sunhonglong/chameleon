/*
Navicat MySQL Data Transfer

Source Server         : dev-10.247.11.9
Source Server Version : 50718
Source Host           : 10.247.11.9:3306
Source Database       : chameleon

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-12-19 17:56:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for mock_request
-- ----------------------------
DROP TABLE IF EXISTS `mock_request`;
CREATE TABLE `mock_request` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `response` varchar(5000) NOT NULL DEFAULT '',
  `response_text` text,
  `method` varchar(50) NOT NULL DEFAULT 'GET',
  `path_var1` varchar(32) NOT NULL DEFAULT '',
  `path_var2` varchar(32) NOT NULL DEFAULT '',
  `path_var3` varchar(32) NOT NULL DEFAULT '',
  `path_var4` varchar(32) NOT NULL DEFAULT '',
  `path_var5` varchar(32) NOT NULL DEFAULT '',
  `path_var6` varchar(32) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

