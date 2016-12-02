/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : faith

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-12-02 15:54:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PERMISSION_NAME` varchar(32) DEFAULT NULL COMMENT '权限名',
  `ROLE_ID` int(11) DEFAULT NULL COMMENT '角色ID',
  `AVAILABLE` char(1) DEFAULT NULL COMMENT '是否删除 0:删除 1：正常',
  PRIMARY KEY (`ID`),
  KEY `id` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE_NAME` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `AVAILABLE` char(1) DEFAULT NULL COMMENT '是否删除 0：删除  1：正常',
  PRIMARY KEY (`ID`),
  KEY `id` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(32) DEFAULT NULL COMMENT '用户名',
  `PASSWORD` varchar(32) DEFAULT NULL COMMENT '密码',
  `AVAILABLE` char(1) DEFAULT NULL COMMENT '是否删除 0：删除  1：正常',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) DEFAULT NULL,
  `ROLE_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';
