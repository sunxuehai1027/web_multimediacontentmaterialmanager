/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : db_multimediacontentmaterialmanager

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-12-11 17:10:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_multimedia`
-- ----------------------------
DROP TABLE IF EXISTS `tb_multimedia`;
CREATE TABLE `tb_multimedia` (
  `number` int(255) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `path` varchar(255) COLLATE utf8_bin NOT NULL,
  `type` int(11) DEFAULT NULL,
  `download` int(11) DEFAULT '0',
  `click` int(11) DEFAULT '0',
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `uploaduserid` varchar(255) COLLATE utf8_bin DEFAULT '100001',
  `uploaddate` datetime DEFAULT NULL,
  `filename` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`number`)
) ENGINE=InnoDB AUTO_INCREMENT=10007 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tb_multimedia
-- ----------------------------
INSERT INTO `tb_multimedia` VALUES ('10001', '测试视频', 'gmbh.de/big_buck_bunny.mp4', '3', '0', '0', '超级好看的视频', 'jzh', '2019-12-07 20:22:28', null);
INSERT INTO `tb_multimedia` VALUES ('10002', '测试图片', 'https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3451483609,615685822&fm=26&gp=0.jpg', '2', '0', '0', '一张图片', 'jzh', '2019-12-07 21:41:53', null);
INSERT INTO `tb_multimedia` VALUES ('10006', '76666666666', '/examples/fileupload/text', '1', null, null, '75555555555', null, '2019-12-11 16:05:35', 'ef830fc3701145c188cb20fedcd996b2.log');

-- ----------------------------
-- Table structure for `tb_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `name` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '1000001',
  `pwd` varchar(255) COLLATE utf8_bin NOT NULL,
  `type` int(11) DEFAULT '0' COMMENT '0表示普通用户 2表示管理员',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('admin', '123', '1');
INSERT INTO `tb_user` VALUES ('jzh', '123', '0');
INSERT INTO `tb_user` VALUES ('www', '123', '0');
