/*
 Navicat Premium Data Transfer

 Source Server         : mysql@docker
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : 127.0.0.1:3306
 Source Schema         : epd

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 31/03/2019 23:05:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for api
-- ----------------------------
DROP TABLE IF EXISTS `api`;
CREATE TABLE `api`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uri` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '接口uri',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接口描述',
  `method` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '接口请求方式',
  `request_header` blob NULL COMMENT '请求头',
  `request_param` blob NULL COMMENT '请求参数',
  `request_success` blob NULL COMMENT '成功返回',
  `request_fail` blob NULL COMMENT '失败返回',
  `return_success_json` blob NULL COMMENT '成功返回json',
  `return_fail_json` blob NULL COMMENT '失败返回json',
  `project_id` int(11) NOT NULL,
  `module_id` int(11) NOT NULL,
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `api_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '接口详情' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for module
-- ----------------------------
DROP TABLE IF EXISTS `module`;
CREATE TABLE `module`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '模块名称',
  `uri` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模块uri',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模块描述',
  `api_list` blob NULL COMMENT '模块下的uri接口列表',
  `project_id` int(11) NOT NULL COMMENT '所属项目ID',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `module_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目模块' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '项目名称',
  `shorthand` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'project uuid',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目说明',
  `base_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `open` tinyint(4) NOT NULL COMMENT '是否公开 default 0 私有，1 公开',
  `owner` int(11) NOT NULL DEFAULT 0 COMMENT '项目所有者，userid',
  `return_format` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '统一返回格式',
  `create_time` datetime(0) NOT NULL COMMENT '项目创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '项目信息更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `project_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '项目实体类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES (1, 'project', 'b56872a2e7a345cfb32ed7d02168e79f', 'project desription', NULL, 0, 1, NULL, '2019-03-29 23:12:02', NULL);
INSERT INTO `project` VALUES (2, '0330测试', 'ab38091f27e546159286bb4a516c2890', '创建测试', '/test', 0, 0, NULL, '2019-03-30 03:28:24', NULL);
INSERT INTO `project` VALUES (3, '0330测试', '70d08aa62c06425db4a2da5b6b700ec0', '创建测试', '/test', 0, 0, NULL, '2019-03-30 03:30:17', NULL);
INSERT INTO `project` VALUES (4, '的机房建设会计', 'b27ce78ec91d4fb5ba1d58d2f7ea70fc', '撒打开集合', '阿迪王', 1, 0, NULL, '2019-03-30 04:45:22', NULL);

-- ----------------------------
-- Table structure for project_map
-- ----------------------------
DROP TABLE IF EXISTS `project_map`;
CREATE TABLE `project_map`  (
  `id` int(11) NOT NULL,
  `project_id` int(11) NOT NULL,
  `module_id` int(11) NOT NULL,
  `module_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `api_id` int(11) NOT NULL,
  `api_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `project_map_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of project_map
-- ----------------------------
INSERT INTO `project_map` VALUES (1, 1, 1, '登陆相关功能', 1, '登陆接口详情', '2019-03-31 05:13:04');
INSERT INTO `project_map` VALUES (2, 1, 1, '登陆相关功能', 2, '退出登陆', '2019-03-31 05:13:57');
INSERT INTO `project_map` VALUES (3, 1, 2, '商品模块', 3, '商品详情', '2019-03-31 05:14:33');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `sys_permission_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `sys_role_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `sys_role_permission_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限对应表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `sys_user_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (2, 'admin', '$2a$10$uvfq7ZJbt8UoiIUCk3OkMe9Kv41P4IxyCo9VYM.qQk7kjgQcACiIa');

-- ----------------------------
-- Table structure for sys_user_ext
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_ext`;
CREATE TABLE `sys_user_ext`  (
  `uid` int(11) NOT NULL COMMENT '对应的sys_user表中用户id',
  `login_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `group_id` int(11) NOT NULL DEFAULT 0 COMMENT '所属群组id，default 0',
  `group_admin` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否该组管理员，default 0 不是，1 是',
  `create_by` int(11) NOT NULL COMMENT '信息创建人',
  `create_time` datetime(0) NOT NULL COMMENT '信息创建时间',
  `update_by` int(11) NOT NULL COMMENT '信息修改人',
  `update_time` datetime(0) NOT NULL COMMENT '信息修改时间',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '最近登录时间'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息扩展表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `sys_user_role_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色对应表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
