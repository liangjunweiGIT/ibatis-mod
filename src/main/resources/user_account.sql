/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50536
 Source Host           : localhost:3306
 Source Schema         : ibatis_test

 Target Server Type    : MySQL
 Target Server Version : 50536
 File Encoding         : 65001

 Date: 20/07/2018 23:49:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_account
-- ----------------------------
DROP TABLE IF EXISTS `user_account`;
CREATE TABLE `user_account`  (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `userName` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `passWord` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `groupName` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_account
-- ----------------------------
INSERT INTO `user_account` VALUES (1, '梁君玮', '123', '1');

SET FOREIGN_KEY_CHECKS = 1;
