/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : seed_project

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 21/10/2020 18:58:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '部门id',
  `pid` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '父部门id',
  `dept_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '部门名称',
  `sort` int(4) NULL DEFAULT NULL COMMENT '显示顺序',
  `enabled` bit(1) NOT NULL COMMENT '部门状态:0正常,1停用',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
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
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dict_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典名称',
  `dict_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典编码',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `del_flag` int(1) NULL DEFAULT NULL COMMENT '删除状态',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
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
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `dict_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典id',
  `item_text` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典项文本',
  `item_value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典项值',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `sort_order` int(10) NULL DEFAULT NULL COMMENT '排序',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态（1启用 0不启用）',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
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
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `log_type` int(2) NULL DEFAULT NULL COMMENT '日志类型（1登录日志，2操作日志）',
  `log_content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志内容',
  `operate_type` int(2) NULL DEFAULT NULL COMMENT '操作类型',
  `userid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作用户账号',
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作用户名称',
  `ip` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP',
  `method` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求java方法',
  `request_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求路径',
  `request_param` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '请求参数',
  `request_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求类型',
  `cost_time` bigint(20) NULL DEFAULT NULL COMMENT '耗时',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_table_userid`(`userid`) USING BTREE,
  INDEX `index_logt_ype`(`log_type`) USING BTREE,
  INDEX `index_operate_type`(`operate_type`) USING BTREE,
  INDEX `index_log_type`(`log_type`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统操作日志表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '访问ID',
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
INSERT INTO `sys_login_log` VALUES ('1275076563682181122', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-06-22 22:41:57');
INSERT INTO `sys_login_log` VALUES ('1275077519748612098', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-06-22 22:45:45');
INSERT INTO `sys_login_log` VALUES ('1275460797194227714', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-06-24 00:08:45');
INSERT INTO `sys_login_log` VALUES ('1276495662555512834', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-06-26 20:40:56');
INSERT INTO `sys_login_log` VALUES ('1276516110756397057', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-06-26 22:02:12');
INSERT INTO `sys_login_log` VALUES ('1277256679749218305', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-06-28 23:04:57');
INSERT INTO `sys_login_log` VALUES ('1277615596794679297', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-06-29 22:51:10');
INSERT INTO `sys_login_log` VALUES ('1277970059132870658', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-06-30 22:19:40');
INSERT INTO `sys_login_log` VALUES ('1278336024974643202', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-07-01 22:33:53');
INSERT INTO `sys_login_log` VALUES ('1302979066406830081', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-09-07 22:36:32');
INSERT INTO `sys_login_log` VALUES ('1302983695764971522', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-09-07 22:54:56');
INSERT INTO `sys_login_log` VALUES ('1303352783213334530', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-09-08 23:21:33');
INSERT INTO `sys_login_log` VALUES ('1303697575470317570', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-09-09 22:11:38');
INSERT INTO `sys_login_log` VALUES ('1305517471980195842', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-09-14 22:43:15');
INSERT INTO `sys_login_log` VALUES ('1306231861079617537', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-09-16 22:01:59');
INSERT INTO `sys_login_log` VALUES ('1306263159601016834', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-09-17 00:06:21');
INSERT INTO `sys_login_log` VALUES ('1306589757009248257', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-09-17 21:44:08');
INSERT INTO `sys_login_log` VALUES ('1306614424151474177', '1', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-09-17 23:22:09');
INSERT INTO `sys_login_log` VALUES ('1306614462646796289', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-09-17 23:22:18');
INSERT INTO `sys_login_log` VALUES ('1306959371069272065', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-09-18 22:12:51');
INSERT INTO `sys_login_log` VALUES ('1306975944681095170', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-09-18 23:18:42');
INSERT INTO `sys_login_log` VALUES ('1306976351092412417', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-09-18 23:20:19');
INSERT INTO `sys_login_log` VALUES ('1306978356586254338', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-09-18 23:28:17');
INSERT INTO `sys_login_log` VALUES ('1306978448651227137', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-09-18 23:28:39');
INSERT INTO `sys_login_log` VALUES ('1306979701674422273', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-09-18 23:33:38');
INSERT INTO `sys_login_log` VALUES ('1310211450655182849', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-09-27 21:35:27');
INSERT INTO `sys_login_log` VALUES ('1310242330622791681', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-09-27 23:38:09');
INSERT INTO `sys_login_log` VALUES ('1310937211984920577', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-09-29 21:39:22');
INSERT INTO `sys_login_log` VALUES ('1311952238086918145', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-10-02 16:52:43');
INSERT INTO `sys_login_log` VALUES ('1311982588561395714', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-10-02 18:53:19');
INSERT INTO `sys_login_log` VALUES ('1312046757188931586', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-10-02 23:08:18');
INSERT INTO `sys_login_log` VALUES ('1312306301018202114', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-10-03 16:19:38');
INSERT INTO `sys_login_log` VALUES ('1312337416915460097', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-10-03 18:23:17');
INSERT INTO `sys_login_log` VALUES ('1312379165205561345', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-10-03 21:09:10');
INSERT INTO `sys_login_log` VALUES ('1312409539918921730', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-10-03 23:09:52');
INSERT INTO `sys_login_log` VALUES ('1312729727071592450', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-10-04 20:22:11');
INSERT INTO `sys_login_log` VALUES ('1312764095722856450', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-10-04 22:38:45');
INSERT INTO `sys_login_log` VALUES ('1312784938616135681', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-10-05 00:01:34');
INSERT INTO `sys_login_log` VALUES ('1312946656025739265', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-10-05 10:44:10');
INSERT INTO `sys_login_log` VALUES ('1313070026981625857', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-10-05 18:54:25');
INSERT INTO `sys_login_log` VALUES ('1313100427900997634', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-10-05 20:55:13');
INSERT INTO `sys_login_log` VALUES ('1313131565805719554', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-10-05 22:58:57');
INSERT INTO `sys_login_log` VALUES ('1314569701748260866', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-10-09 22:13:35');
INSERT INTO `sys_login_log` VALUES ('1316364017030365186', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-10-14 21:03:33');
INSERT INTO `sys_login_log` VALUES ('1316730219024048130', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-10-15 21:18:42');
INSERT INTO `sys_login_log` VALUES ('1316760948990754817', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-10-15 23:20:49');
INSERT INTO `sys_login_log` VALUES ('1317112825859219457', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-10-16 22:39:03');
INSERT INTO `sys_login_log` VALUES ('1317143112152465409', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-10-17 00:39:24');
INSERT INTO `sys_login_log` VALUES ('1318190407459426306', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-10-19 22:00:58');
INSERT INTO `sys_login_log` VALUES ('1318540398556291074', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-10-20 21:11:43');
INSERT INTO `sys_login_log` VALUES ('1318569348540071937', 'admin', '192.168.199.101', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-10-20 23:06:45');
INSERT INTO `sys_login_log` VALUES ('1318743507397861377', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-10-21 10:38:48');
INSERT INTO `sys_login_log` VALUES ('1318765669785624577', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-10-21 12:06:52');
INSERT INTO `sys_login_log` VALUES ('1318765758973304834', 'test', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-10-21 12:07:13');
INSERT INTO `sys_login_log` VALUES ('1318765835162836994', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-10-21 12:07:31');
INSERT INTO `sys_login_log` VALUES ('1318802019037569025', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-10-21 14:31:18');
INSERT INTO `sys_login_log` VALUES ('1318837220065251330', 'admin', '127.0.0.1', NULL, 'Chrome 8', 'Windows 10', 0, '', '2020-10-21 16:51:11');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单ID',
  `menu` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `is_frame` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否外链',
  `component` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件',
  `component_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件名称',
  `path` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '请求地址',
  `redirect` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '重定向',
  `pid` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '父级id',
  `type` int(1) NOT NULL COMMENT '类型:1目录,2菜单,3按钮',
  `visible` bit(1) NOT NULL COMMENT '菜单状态:显示,隐藏',
  `permission` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '权限标识',
  `cache` bit(1) NULL DEFAULT NULL COMMENT '缓存',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '菜单图标',
  `sort` int(4) NOT NULL COMMENT '排序',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `remark` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_menu`(`menu`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单权限表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1318547064467148801', '查询表格', b'0', NULL, 'list.table-list', '/list', '', '0', 1, b'1', '', NULL, 'SmileOutlined', 1, 'admin', '2020-10-20 21:38:12', '');
INSERT INTO `sys_menu` VALUES ('1318547678320316418', '管理页', b'0', NULL, 'admin', '/admin', '', '0', 1, b'1', '', NULL, 'StarOutlined', 3, 'admin', '2020-10-20 21:40:39', '');
INSERT INTO `sys_menu` VALUES ('1318549151200501762', '二级管理页', b'0', NULL, 'sub-page', '/admin/sub-page', '', '1318547678320316418', 2, b'1', '', NULL, '', 1, 'admin', '2020-10-20 21:46:30', '');
INSERT INTO `sys_menu` VALUES ('1318550685359407106', '测试页面', b'0', NULL, 'test', '/test', '', '0', 1, b'1', '', NULL, 'WarningOutlined', 2, 'admin', '2020-10-20 21:52:35', '');
INSERT INTO `sys_menu` VALUES ('1318550824027291649', '测试子页面', b'0', NULL, 'sub-test', '/test/sub-test', '', '1318550685359407106', 2, b'1', '', NULL, '', 1, 'admin', '2020-10-20 21:53:09', '');
INSERT INTO `sys_menu` VALUES ('1318550980101537794', '系统管理', b'0', NULL, 'system', '/system', '', '0', 1, b'1', '', NULL, 'SettingOutlined', 5, 'admin', '2020-10-20 21:53:46', '');
INSERT INTO `sys_menu` VALUES ('1318551098947141633', '用户管理', b'0', NULL, 'system-user', '/system/user', '', '1318550980101537794', 2, b'1', '', NULL, '', 1, 'admin', '2020-10-20 21:54:14', '');
INSERT INTO `sys_menu` VALUES ('1318551201015529473', '角色管理', b'0', NULL, 'system-role', '/system/role', '', '1318550980101537794', 2, b'1', '', NULL, '', 2, 'admin', '2020-10-20 21:54:38', '');
INSERT INTO `sys_menu` VALUES ('1318551300202430466', '菜单管理', b'0', NULL, 'system-menu', '/system/menu', '', '1318550980101537794', 2, b'1', '', NULL, '', 3, 'admin', '2020-10-20 21:55:02', '');
INSERT INTO `sys_menu` VALUES ('1318551489143242754', '系统设置', b'0', NULL, 'system-globle', '/system/globle', '', '1318550980101537794', 2, b'1', '', NULL, '', 4, 'admin', '2020-10-20 21:55:47', '');
INSERT INTO `sys_menu` VALUES ('1318813923990671361', '网页链接', b'1', NULL, '网页链接', '/', '', '0', 1, b'1', '', NULL, 'IeOutlined', 4, 'admin', '2020-10-21 15:18:36', '');
INSERT INTO `sys_menu` VALUES ('1318814244045426690', 'swagger接口文档', b'1', NULL, 'swagger接口文档', 'http://localhost:8081/seed-project/swagger-ui.html', '', '1318813923990671361', 2, b'1', '', NULL, '', 1, 'admin', '2020-10-21 15:19:53', '');
INSERT INTO `sys_menu` VALUES ('1318814544424701954', 'bootstrap接口文档', b'1', NULL, 'bootstrap接口文档', 'http://localhost:8081/seed-project/doc.html', '', '1318813923990671361', 2, b'1', '', NULL, '', 2, 'admin', '2020-10-21 15:21:04', '');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色ID',
  `role_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色编码',
  `role_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `permission` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色权限字符串',
  `level` int(2) NULL DEFAULT NULL COMMENT '角色级别',
  `data_scope` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据权限',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `remark` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uniq_role_code`(`role_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'admin', '管理员角色', 'all', 1, NULL, '系统创建', '2020-02-07 14:36:37', '这是管理员角色');
INSERT INTO `sys_role` VALUES ('6', 'test', '测试角色', NULL, NULL, NULL, 'admin', '2020-02-17 21:40:10', '测试角色');
INSERT INTO `sys_role` VALUES ('7', '1', '测试角色2', NULL, NULL, NULL, 'guest', '2020-04-06 17:52:57', '1');

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色ID',
  `dept_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '岗位ID',
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
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色ID',
  `menu_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1', '1318547064467148801');
INSERT INTO `sys_role_menu` VALUES ('1', '1318547678320316418');
INSERT INTO `sys_role_menu` VALUES ('1', '1318549151200501762');
INSERT INTO `sys_role_menu` VALUES ('1', '1318550685359407106');
INSERT INTO `sys_role_menu` VALUES ('1', '1318550824027291649');
INSERT INTO `sys_role_menu` VALUES ('1', '1318550980101537794');
INSERT INTO `sys_role_menu` VALUES ('1', '1318551098947141633');
INSERT INTO `sys_role_menu` VALUES ('1', '1318551201015529473');
INSERT INTO `sys_role_menu` VALUES ('1', '1318551300202430466');
INSERT INTO `sys_role_menu` VALUES ('1', '1318551489143242754');
INSERT INTO `sys_role_menu` VALUES ('1', '1318813923990671361');
INSERT INTO `sys_role_menu` VALUES ('1', '1318814244045426690');
INSERT INTO `sys_role_menu` VALUES ('1', '1318814544424701954');
INSERT INTO `sys_role_menu` VALUES ('6', '1318547064467148801');
INSERT INTO `sys_role_menu` VALUES ('6', '1318550685359407106');
INSERT INTO `sys_role_menu` VALUES ('6', '1318550824027291649');
INSERT INTO `sys_role_menu` VALUES ('7', '1318547678320316418');
INSERT INTO `sys_role_menu` VALUES ('7', '1318549151200501762');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID',
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
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `login_ip` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '最后登录ip',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `remark` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '管理员', 'admin', 'admin', '2020-06-30 22:44:40', '1', NULL, '$2a$10$doq0XR1.fKVkldSgiu9TXuBpZ9gXweX.dzD46aOr4vofs1DI5gznK', b'1', '1', NULL, 'admin', '2020-06-20 22:54:19', '', NULL, NULL);
INSERT INTO `sys_user` VALUES ('1318551822766571522', 'test', '测试角色', 'test@163.com', '', NULL, '1', NULL, '$2a$10$6wWM5LgxzIFSnZNgBh0Ps..shr7f1ApQ.LWKskAh5KIY0QniDPqDa', b'1', NULL, NULL, 'admin', '2020-10-20 21:57:07', '', NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID',
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户和角色关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');
INSERT INTO `sys_user_role` VALUES ('1318551822766571522', '6');
INSERT INTO `sys_user_role` VALUES ('1318560997441794050', '6');

SET FOREIGN_KEY_CHECKS = 1;
