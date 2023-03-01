/*
 Navicat Premium Data Transfer

 Source Server         : db_bkb
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : 47.105.172.38:3306
 Source Schema         : db_bkb

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 28/02/2023 15:15:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for likes
-- ----------------------------
DROP TABLE IF EXISTS `likes`;
CREATE TABLE `likes`  (
  `id` bigint(20) NOT NULL DEFAULT 1 COMMENT '主键id',
  `relation_id` bigint(20) NULL DEFAULT NULL COMMENT '关联id',
  `relation_type` tinyint(4) NULL DEFAULT NULL COMMENT '点赞类型0-吐槽；1-提问；2-吐槽回复；3-提问回复',
  `likes_num` int(10) NULL DEFAULT NULL COMMENT '点赞数',
  `status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '状态（字典），1：正常；0：停用；-1：删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '点赞关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of likes
-- ----------------------------
INSERT INTO `likes` VALUES (1620675889663619074, 1620675877466583041, 0, 5, 1, '2023-02-01 14:50:17', 'oNhyU5JBlvEjxTs-FfxHGlOcoXV8', '2023-02-01 14:50:18', 'oNhyU5JBlvEjxTs-FfxHGlOcoXV8');
INSERT INTO `likes` VALUES (1620676490615107586, 1620676483291852802, 2, 2, 1, '2023-02-01 14:52:41', 'oNhyU5JBlvEjxTs-FfxHGlOcoXV8', '2023-02-01 17:05:15', 'oNhyU5JBlvEjxTs-FfxHGlOcoXV8');
INSERT INTO `likes` VALUES (1620679439093379073, 1620676903162654721, 0, 5, 1, '2023-02-01 15:04:24', 'oNhyU5JBlvEjxTs-FfxHGlOcoXV8', '2023-02-01 15:04:24', 'oNhyU5JBlvEjxTs-FfxHGlOcoXV8');
INSERT INTO `likes` VALUES (1620686512715771905, 1620676420008194050, 0, 5, 1, '2023-02-01 15:32:30', 'oNhyU5JNXarFpySwewbJltXRioNg', '2023-02-01 15:32:34', 'oNhyU5JNXarFpySwewbJltXRioNg');

-- ----------------------------
-- Table structure for ques
-- ----------------------------
DROP TABLE IF EXISTS `ques`;
CREATE TABLE `ques`  (
  `id` bigint(20) NOT NULL DEFAULT 1 COMMENT '主键id',
  `ques_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '问题标题\r\n',
  `ques_desc` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '问题描述',
  `reply_num` int(11) NULL DEFAULT NULL COMMENT '回复数',
  `likes` int(11) NULL DEFAULT NULL COMMENT '点赞数(黑/粉)',
  `status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '状态（字典），1：正常；0：停用；-1：删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '提问表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ques
-- ----------------------------
INSERT INTO `ques` VALUES (1620677271980388353, '五险一金', '0', 1, 0, 1, '2023-02-01 14:55:47', 'oNhyU5JBlvEjxTs-FfxHGlOcoXV8', '2023-02-01 17:04:56', 'oNhyU5JBlvEjxTs-FfxHGlOcoXV8');

-- ----------------------------
-- Table structure for ques_reply
-- ----------------------------
DROP TABLE IF EXISTS `ques_reply`;
CREATE TABLE `ques_reply`  (
  `id` bigint(20) NOT NULL,
  `ques_id` bigint(20) NULL DEFAULT NULL COMMENT '问题id',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '回复内容',
  `likes` int(11) NULL DEFAULT 0 COMMENT '点赞数',
  `status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '状态（字典），1：正常；0：停用；-1：删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '问题回复' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ques_reply
-- ----------------------------
INSERT INTO `ques_reply` VALUES (1620709772224409602, 1620677271980388353, '医院教育行业', 0, 1, '2023-02-01 17:04:56', 'oNhyU5JBlvEjxTs-FfxHGlOcoXV8', '2023-02-01 17:04:56', 'oNhyU5JBlvEjxTs-FfxHGlOcoXV8');

-- ----------------------------
-- Table structure for roast
-- ----------------------------
DROP TABLE IF EXISTS `roast`;
CREATE TABLE `roast`  (
  `id` bigint(20) NOT NULL DEFAULT 1 COMMENT '主键id',
  `company_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司名',
  `company_type` tinyint(255) NULL DEFAULT NULL COMMENT '公司类型(0-黑名单；1-红名单)',
  `company_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司描述',
  `views` int(11) NULL DEFAULT 0 COMMENT '浏览量',
  `comments` int(11) NULL DEFAULT 0 COMMENT '评论量',
  `likes` int(11) NULL DEFAULT NULL COMMENT '点赞数(黑/粉)',
  `heats` int(11) NULL DEFAULT 0 COMMENT '热度(浏览+1，点赞+3，评论+5)',
  `status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '状态（字典），1：正常；0：停用；-1：删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '吐槽信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roast
-- ----------------------------
INSERT INTO `roast` VALUES (1620675877466583041, '青岛江恒软件科技有限公司', 0, '老板两口子绝配，对待员工如家人般温暖', 6, 1, 5, 26, 1, '2023-02-01 14:50:15', 'oNhyU5JBlvEjxTs-FfxHGlOcoXV8', '2023-02-24 15:31:48', 'oNhyU5HZ5LOzchcawks_rvtLMaPg');
INSERT INTO `roast` VALUES (1620676259030806529, '山东青鸟软通信息技术股份有限公司', 0, '大家来面的时候一定要确认好是去哪个部门，快销部有个叫 刘志鹏 的项目经理，一定要避开这个傻逼，一天到晚不干活，天天给下面的人甩锅，坑比一个。', 3, 0, 0, 3, 1, '2023-02-01 14:51:46', 'oNhyU5JBlvEjxTs-FfxHGlOcoXV8', '2023-02-23 01:21:11', 'oNhyU5LmG7VCvaK6G6YKUGSGSmTc');
INSERT INTO `roast` VALUES (1620676420008194050, '青岛小优智能', 0, '岗位介绍薪水虚高。 技术面没问题，但是薪水给不了岗位介绍中的数字', 4, 0, 5, 19, 1, '2023-02-01 14:52:24', 'oNhyU5JBlvEjxTs-FfxHGlOcoXV8', '2023-02-25 14:23:51', 'oNhyU5HZ5LOzchcawks_rvtLMaPg');
INSERT INTO `roast` VALUES (1620676903162654721, '青岛内分泌糖尿病医院', 1, '院长很好，福利待遇好', 5, 1, 5, 25, 1, '2023-02-01 14:54:19', 'oNhyU5JBlvEjxTs-FfxHGlOcoXV8', '2023-02-24 15:33:03', 'oNhyU5JBlvEjxTs-FfxHGlOcoXV8');
INSERT INTO `roast` VALUES (1620677018359214081, '海尔', 1, '马马虎虎，反正薪水待遇保险啥的都挺好', 2, 0, 0, 2, 1, '2023-02-01 14:54:47', 'oNhyU5JBlvEjxTs-FfxHGlOcoXV8', '2023-02-23 01:21:20', 'oNhyU5LmG7VCvaK6G6YKUGSGSmTc');
INSERT INTO `roast` VALUES (1620677131949355010, '海信', 1, '就是有点加班，其余都很不错，待遇很好，报销到位', 8, 0, 0, 8, 1, '2023-02-01 14:55:14', 'oNhyU5JBlvEjxTs-FfxHGlOcoXV8', '2023-02-25 07:04:35', 'oNhyU5H6jCKwrhesojK3BRw0oJkM');

-- ----------------------------
-- Table structure for roast_reply
-- ----------------------------
DROP TABLE IF EXISTS `roast_reply`;
CREATE TABLE `roast_reply`  (
  `id` bigint(20) NOT NULL,
  `roast_id` bigint(20) NULL DEFAULT NULL COMMENT '吐槽id',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '回复内容',
  `likes` int(11) NULL DEFAULT 0 COMMENT '点赞数',
  `status` tinyint(4) NOT NULL DEFAULT 1 COMMENT '状态（字典），1：正常；0：停用；-1：删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '吐槽回复' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roast_reply
-- ----------------------------
INSERT INTO `roast_reply` VALUES (1620676483291852802, 1620675877466583041, '顶', 2, 1, '2023-02-01 14:52:39', 'oNhyU5JBlvEjxTs-FfxHGlOcoXV8', '2023-02-01 17:05:15', 'oNhyU5JBlvEjxTs-FfxHGlOcoXV8');
INSERT INTO `roast_reply` VALUES (1620709263665049601, 1620676903162654721, '真心不错', 0, 1, '2023-02-01 17:02:54', 'oNhyU5JBlvEjxTs-FfxHGlOcoXV8', '2023-02-01 17:02:54', 'oNhyU5JBlvEjxTs-FfxHGlOcoXV8');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `open_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'open_id',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '头像',
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '性别(0-未知；1-男；2-女)',
  `birthday` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生日',
  `intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简介',
  `status` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT 1 COMMENT '状态 1：正常；0：删除',
  `create_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` date NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1620675668665741313, 'oNhyU5JBlvEjxTs-FfxHGlOcoXV8', '勤劳的斑马', 'https://bkb.javadog.net/bkb/v1/attach/2023/02/06/8d1e2097-5932-4a5d-bb8c-43e19086b927.jpg', '0', NULL, NULL, 1, NULL, '2023-02-01', NULL, '2023-02-06 08:29:26');
INSERT INTO `user` VALUES (1620677709941223426, 'oNhyU5Fzer3n71E4JxhIkXdTtG2I', '谯笪咖啡豆', 'http://api.multiavatar.com/64.png', '0', NULL, NULL, 1, NULL, '2023-02-01', NULL, '2023-02-01 14:57:31');
INSERT INTO `user` VALUES (1620679692425146370, 'oNhyU5JNXarFpySwewbJltXRioNg', '乐正红牛', 'http://api.multiavatar.com/2.png', '0', NULL, NULL, 1, NULL, '2023-02-01', NULL, '2023-02-01 15:05:24');
INSERT INTO `user` VALUES (1622793646899245058, 'oNhyU5EY9SYoIJG892iVZuJKh9ZU', '淡然的银耳汤', 'http://api.multiavatar.com/7.png', '0', NULL, NULL, 1, NULL, '2023-02-07', NULL, '2023-02-07 11:05:30');
INSERT INTO `user` VALUES (1626466932950609921, 'oNhyU5O6AOraeruK-BfJVUkci6WM', '况后帆布鞋', 'http://api.multiavatar.com/21.png', '0', NULL, NULL, 1, NULL, '2023-02-17', NULL, '2023-02-17 14:21:50');
INSERT INTO `user` VALUES (1628444650063835138, 'oNhyU5LmG7VCvaK6G6YKUGSGSmTc', '害怕的自行车', 'http://api.multiavatar.com/43.png', '0', NULL, NULL, 1, NULL, '2023-02-23', NULL, '2023-02-23 01:20:34');
INSERT INTO `user` VALUES (1629021118867222530, 'oNhyU5HZ5LOzchcawks_rvtLMaPg', '幕容胡萝卜', 'http://api.multiavatar.com/93.png', '0', NULL, NULL, 1, NULL, '2023-02-24', NULL, '2023-02-24 15:31:15');
INSERT INTO `user` VALUES (1629255735285166082, 'oNhyU5H6jCKwrhesojK3BRw0oJkM', '如意的心锁', 'http://api.multiavatar.com/12.png', '0', NULL, NULL, 1, NULL, '2023-02-25', NULL, '2023-02-25 07:03:32');

SET FOREIGN_KEY_CHECKS = 1;
