/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : localhost:3307
 Source Schema         : seed_project

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 14/09/2021 18:13:44
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` bigint(19) NOT NULL COMMENT '部门id',
  `pid` bigint(19) NOT NULL COMMENT '父部门id',
  `dept_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '部门名称',
  `sort` int(4) NULL DEFAULT NULL COMMENT '显示顺序',
  `enabled` bit(1) NOT NULL COMMENT '部门状态:0正常,1停用',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_dept_name`(`dept_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` bigint(19) NOT NULL,
  `dict_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典名称',
  `dict_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典编码',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `deleted` tinyint(1) NULL DEFAULT NULL COMMENT '删除状态',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `type` int(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '字典类型0为string,1为number',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `indextable_dict_code`(`dict_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item`  (
  `id` bigint(19) NOT NULL,
  `dict_id` bigint(19) NULL DEFAULT NULL COMMENT '字典id',
  `item_text` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典项文本',
  `item_value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典项值',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `sort_order` int(10) NULL DEFAULT NULL COMMENT '排序',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态（1启用 0不启用）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_table_dict_id`(`dict_id`) USING BTREE,
  INDEX `index_table_sort_order`(`sort_order`) USING BTREE,
  INDEX `index_table_dict_status`(`status`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` bigint(19) NOT NULL,
  `log_type` int(2) NULL DEFAULT NULL COMMENT '日志类型（1登录日志，2操作日志）',
  `log_content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志内容',
  `operate_type` int(2) NULL DEFAULT NULL COMMENT '操作类型',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作用户账号',
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作用户名称',
  `ip` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP',
  `method` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求java方法',
  `request_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求路径',
  `request_param` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '请求参数',
  `request_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求类型',
  `cost_time` bigint(20) NULL DEFAULT NULL COMMENT '耗时',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_table_userid`(`user_id`) USING BTREE,
  INDEX `index_logt_ype`(`log_type`) USING BTREE,
  INDEX `index_operate_type`(`operate_type`) USING BTREE,
  INDEX `index_log_type`(`log_type`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统操作日志表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (1437257228793516033, 2, '添加用户', 2, 'admin', 'admin', '127.0.0.1', 'com.cody.seed.modules.system.controller.SysUserController.addUser()', NULL, '[{\"enabled\":true,\"nickName\":\"1\",\"password\":\"1\",\"roleIds\":\"6\",\"sex\":\"1\",\"userName\":\"1\"}]', NULL, 240, 'admin', '2021-09-13 11:30:01', NULL, NULL);
INSERT INTO `sys_log` VALUES (1437260430163099650, 2, '添加用户', 2, 'admin', 'admin', '127.0.0.1', 'com.cody.seed.modules.system.controller.SysUserController.addUser()', NULL, '[{\"enabled\":true,\"nickName\":\"2\",\"password\":\"2\",\"roleIds\":\"6\",\"sex\":\"1\",\"userName\":\"2\"}]', 'POST', 227, 'admin', '2021-09-13 11:42:44', NULL, NULL);
INSERT INTO `sys_log` VALUES (1437317336932151298, 2, '删除用户', 4, 'admin', 'admin', '127.0.0.1', 'com.cody.seed.modules.system.controller.SysUserController.deleteUser()', NULL, '  object: {\"ids\":\"1437260429844332546,1437257228403445762\"}', 'DELETE', 17, 'admin', '2021-09-13 15:28:52', NULL, NULL);

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log`  (
  `id` bigint(19) NOT NULL COMMENT '访问ID',
  `login_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录账号',
  `ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `browser` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '操作系统',
  `status` int(1) NULL DEFAULT 0 COMMENT '登录状态 0成功 1失败',
  `msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '提示消息',
  `login_time` datetime(0) NOT NULL COMMENT '访问时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统访问记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_login_log
-- ----------------------------
INSERT INTO `sys_login_log` VALUES (1437667108310310913, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', 0, '', '2021-09-14 14:38:44');
INSERT INTO `sys_login_log` VALUES (1437668664522952705, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', 0, '', '2021-09-14 14:44:55');
INSERT INTO `sys_login_log` VALUES (1437669484932911105, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', 0, '', '2021-09-14 14:48:10');
INSERT INTO `sys_login_log` VALUES (1437670738979475458, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', 0, '', '2021-09-14 14:53:09');
INSERT INTO `sys_login_log` VALUES (1437670814833463298, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', 0, '', '2021-09-14 14:53:28');
INSERT INTO `sys_login_log` VALUES (1437672554748203010, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', 0, '', '2021-09-14 15:00:22');
INSERT INTO `sys_login_log` VALUES (1437676378669993985, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', 0, '', '2021-09-14 15:15:34');
INSERT INTO `sys_login_log` VALUES (1437712367132143618, 'admin', '127.0.0.1', '内网IP', 'Chrome 9', 'Windows 10', 0, '', '2021-09-14 17:38:34');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint(19) NOT NULL COMMENT '菜单ID',
  `menu` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `is_frame` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否外链',
  `component` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件',
  `component_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件名称',
  `path` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '请求地址',
  `redirect` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '重定向',
  `pid` bigint(19) NOT NULL COMMENT '父级id',
  `type` int(1) NOT NULL COMMENT '类型:1目录,2菜单,3按钮',
  `visible` bit(1) NOT NULL COMMENT '菜单状态:显示,隐藏',
  `permission` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '权限标识',
  `cache` bit(1) NULL DEFAULT NULL COMMENT '缓存',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '菜单图标',
  `sort` int(4) NOT NULL COMMENT '排序',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_menu`(`menu`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单权限表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1318547064467148801, '查询表格', b'0', NULL, 'list.table-list', '/list', '', 0, 1, b'1', '', NULL, 'SmileOutlined', 1, 'admin', '2020-10-20 21:38:12', '', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1318547678320316418, '管理页', b'0', NULL, 'admin', '/admin', '', 0, 1, b'1', '', NULL, 'StarOutlined', 3, 'admin', '2020-10-20 21:40:39', '', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1318549151200501762, '二级管理页', b'0', NULL, 'sub-page', '/admin/sub-page', '', 1318547678320316418, 2, b'1', '', NULL, '', 1, 'admin', '2020-10-20 21:46:30', '', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1318550685359407106, '测试页面', b'0', NULL, 'test', '/test', '', 0, 1, b'1', '', NULL, 'WarningOutlined', 2, 'admin', '2020-10-20 21:52:35', '', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1318550824027291649, '测试子页面', b'0', NULL, 'sub-test', '/test/sub-test', '', 1318550685359407106, 2, b'1', '', NULL, '', 1, 'admin', '2020-10-20 21:53:09', '', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1318550980101537794, '系统管理', b'0', NULL, 'system', '/system', '', 0, 1, b'1', '', NULL, 'SettingOutlined', 5, 'admin', '2020-10-20 21:53:46', '', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1318551098947141633, '用户管理', b'0', NULL, 'system-user', '/system/user', '', 1318550980101537794, 2, b'1', '', NULL, '', 1, 'admin', '2020-10-20 21:54:14', '', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1318551201015529473, '角色管理', b'0', NULL, 'system-role', '/system/role', '', 1318550980101537794, 2, b'1', '', NULL, '', 2, 'admin', '2020-10-20 21:54:38', '', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1318551300202430466, '菜单管理', b'0', NULL, 'system-menu', '/system/menu', '', 1318550980101537794, 2, b'1', '', NULL, '', 3, 'admin', '2020-10-20 21:55:02', '', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1318551489143242754, '系统设置', b'0', NULL, 'system-globle', '/system/globle', '', 1318550980101537794, 2, b'1', '', NULL, '', 4, 'admin', '2020-10-20 21:55:47', '', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1318813923990671361, '网页链接', b'1', NULL, '网页链接', '/', '', 0, 1, b'1', '', NULL, 'IeOutlined', 4, 'admin', '2020-10-21 15:18:36', '', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1318814244045426690, 'swagger接口文档', b'1', NULL, 'swagger接口文档', 'http://localhost:8081/seed-project/swagger-ui.html', '', 1318813923990671361, 2, b'1', '', NULL, '', 1, 'admin', '2020-10-21 15:19:53', '', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1318814544424701954, 'bootstrap接口文档', b'1', NULL, 'bootstrap接口文档', 'http://localhost:8081/seed-project/doc.html', '', 1318813923990671361, 2, b'1', '', NULL, '', 2, 'admin', '2020-10-21 15:21:04', '', NULL, NULL);
INSERT INTO `sys_menu` VALUES (1437687160203923457, '日志管理', b'0', NULL, 'system-log', '/system/log', '', 1318550980101537794, 2, b'1', '', NULL, '', 5, 'admin', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(19) NOT NULL COMMENT '角色ID',
  `role_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色编码',
  `role_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `permission` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色权限字符串',
  `level` int(2) NULL DEFAULT NULL COMMENT '角色级别',
  `data_scope` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据权限',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_role_code`(`role_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'admin', '管理员角色', 'all', 1, NULL, '系统创建', '2020-02-07 14:36:37', NULL, NULL, '这是管理员角色');
INSERT INTO `sys_role` VALUES (6, 'test', '测试角色', NULL, NULL, NULL, 'admin', '2020-02-17 21:40:10', NULL, NULL, '测试角色');
INSERT INTO `sys_role` VALUES (7, '1', '测试角色2', NULL, NULL, NULL, 'guest', '2020-04-06 17:52:57', NULL, NULL, '1');

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
  `role_id` bigint(19) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(19) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`role_id`, `dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色和部门关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint(19) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(19) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 1318547064467148801);
INSERT INTO `sys_role_menu` VALUES (1, 1318547678320316418);
INSERT INTO `sys_role_menu` VALUES (1, 1318549151200501762);
INSERT INTO `sys_role_menu` VALUES (1, 1318550685359407106);
INSERT INTO `sys_role_menu` VALUES (1, 1318550824027291649);
INSERT INTO `sys_role_menu` VALUES (1, 1318550980101537794);
INSERT INTO `sys_role_menu` VALUES (1, 1318551098947141633);
INSERT INTO `sys_role_menu` VALUES (1, 1318551201015529473);
INSERT INTO `sys_role_menu` VALUES (1, 1318551300202430466);
INSERT INTO `sys_role_menu` VALUES (1, 1318551489143242754);
INSERT INTO `sys_role_menu` VALUES (1, 1318813923990671361);
INSERT INTO `sys_role_menu` VALUES (1, 1318814244045426690);
INSERT INTO `sys_role_menu` VALUES (1, 1318814544424701954);
INSERT INTO `sys_role_menu` VALUES (1, 1437687160203923457);
INSERT INTO `sys_role_menu` VALUES (6, 1318547064467148801);
INSERT INTO `sys_role_menu` VALUES (6, 1318550685359407106);
INSERT INTO `sys_role_menu` VALUES (6, 1318550824027291649);
INSERT INTO `sys_role_menu` VALUES (7, 1318547678320316418);
INSERT INTO `sys_role_menu` VALUES (7, 1318549151200501762);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(19) NOT NULL COMMENT '用户ID',
  `user_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户名称',
  `nick_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用户邮箱',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '手机号码',
  `birthday` datetime(0) NULL DEFAULT NULL COMMENT '生日',
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别 1男 2女',
  `avatar` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '密码',
  `enabled` bit(1) NULL DEFAULT b'1' COMMENT '帐号状态:1正常,0禁用',
  `dept_id` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门id',
  `job_id` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '岗位id',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `deleted` tinyint(1) NULL DEFAULT 0,
  `login_ip` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '最后登录ip',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '管理员', 'admin', 'admin', '2020-06-30 22:44:40', '1', NULL, '$2a$10$doq0XR1.fKVkldSgiu9TXuBpZ9gXweX.dzD46aOr4vofs1DI5gznK', b'1', '1', NULL, 'admin', '2020-06-20 22:54:19', NULL, NULL, NULL, 0, '', NULL);
INSERT INTO `sys_user` VALUES (1318551822766571522, 'test', '测试角色', 'test@163.com', '', NULL, '1', NULL, '$2a$10$6wWM5LgxzIFSnZNgBh0Ps..shr7f1ApQ.LWKskAh5KIY0QniDPqDa', b'1', NULL, NULL, 'admin', '2020-10-20 21:57:07', NULL, NULL, NULL, 0, '', NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint(19) NOT NULL COMMENT '用户ID',
  `role_id` bigint(19) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户和角色关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (1318551822766571522, 6);

SET FOREIGN_KEY_CHECKS = 1;
