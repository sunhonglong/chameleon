/*
Navicat MySQL Data Transfer

Source Server         : dev-10.247.11.9
Source Server Version : 50718
Source Host           : 10.247.11.9:3306
Source Database       : chameleon

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-12-19 17:56:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for mock_response_header
-- ----------------------------
DROP TABLE IF EXISTS `mock_response_header`;
CREATE TABLE `mock_response_header` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `request` int(11) DEFAULT NULL,
  `header_key` varchar(128) DEFAULT NULL,
  `header_value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

