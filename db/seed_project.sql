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

 Date: 23/09/2020 22:51:13
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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典表' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统操作日志表' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统访问记录' ROW_FORMAT = Dynamic;

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

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单ID',
  `menu` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `is_frame` bit(1) NOT NULL COMMENT '是否外链',
  `component` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件',
  `component_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件名称',
  `path` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '请求地址',
  `redirect` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '重定向',
  `pid` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '父级id',
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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('10', '高级表单', b'0', 'form/advancedForm/AdvancedForm', 'AdvanceForm', '/form/advanced-form', NULL, '7', 2, b'1', '', NULL, '', 203, 'admin', '2020-01-26 19:28:14', '');
INSERT INTO `sys_menu` VALUES ('100', '删除订单', b'0', NULL, NULL, NULL, NULL, '67', 3, b'1', 'order:delete', b'1', NULL, 3, 'admin', '2020-04-07 15:02:40', NULL);
INSERT INTO `sys_menu` VALUES ('101', 'pdf小票', b'0', 'youliao/PdfBill', 'bill', '/youliao/bill', NULL, '65', 2, b'1', NULL, b'1', NULL, 4, 'admin', '2020-04-09 15:31:21', NULL);
INSERT INTO `sys_menu` VALUES ('102', 'Redis缓存', b'0', 'youliao/redis/RedisCache', 'redisCache', '/youliao/redisCache', NULL, '65', 2, b'1', NULL, b'1', NULL, 5, 'admin', '2020-04-09 15:34:32', NULL);
INSERT INTO `sys_menu` VALUES ('11', '列表页', b'0', 'PageView', 'list', '/list', '/list/table-list', '0', 1, b'1', '', NULL, 'table', 13, 'admin', '2020-01-26 19:39:26', '');
INSERT INTO `sys_menu` VALUES ('12', '查询表格', b'0', 'list/TableList', 'TableListWrapper', '/list/table-list/:pageNo([1-9]\\d*)?', NULL, '11', 2, b'1', '', NULL, '', 301, 'admin', '2020-01-26 19:40:48', '');
INSERT INTO `sys_menu` VALUES ('13', '标准列表', b'0', 'list/StandardList', 'BasicList', '/list/basic-list', NULL, '11', 2, b'1', '', NULL, '', 302, 'admin', '2020-01-26 19:41:44', '');
INSERT INTO `sys_menu` VALUES ('14', '卡片表格', b'0', 'list/CardList', 'CardList', '/list/card', NULL, '11', 2, b'1', '', NULL, '', 303, 'admin', '2020-01-26 19:42:19', '');
INSERT INTO `sys_menu` VALUES ('15', '搜索表格', b'0', 'list/search/SearchLayout', 'SearchList', '/list/search', '/list/search/article', '11', 2, b'1', '', NULL, '', 304, 'admin', '2020-01-26 19:43:05', '');
INSERT INTO `sys_menu` VALUES ('16', '搜索列表（文章）', b'0', 'list/search/Article', 'SearchArticles', '/list/search/article', NULL, '15', 2, b'1', '', NULL, '', 3401, 'admin', '2020-01-26 19:44:33', '');
INSERT INTO `sys_menu` VALUES ('17', '搜索列表（项目）', b'0', 'list/search/Projects', 'SearchProjects', '/list/search/project', NULL, '15', 2, b'1', '', NULL, '', 3402, 'admin', '2020-01-26 19:45:28', '');
INSERT INTO `sys_menu` VALUES ('18', '搜索列表（应用）', b'0', 'list/search/Applications', 'SearchApplications', '/list/search/application', NULL, '15', 2, b'1', '', NULL, '', 3403, 'admin', '2020-01-26 19:49:22', '');
INSERT INTO `sys_menu` VALUES ('19', '详情页', b'0', 'RouteView', 'profile', '/profile', '/profile/basic', '0', 1, b'1', '', NULL, 'profile', 14, 'admin', '2020-01-26 19:54:54', '');
INSERT INTO `sys_menu` VALUES ('2', '仪表盘', b'0', 'RouteView', 'dashboard', '/dashboard', '/dashboard/workplace', '0', 1, b'1', '', NULL, 'dashboard', 11, 'admin', '2020-01-26 19:17:04', '');
INSERT INTO `sys_menu` VALUES ('20', '基础详情页', b'0', 'profile/basic/Index', 'ProfileBasic', '/profile/basic', NULL, '19', 2, b'1', '', NULL, '', 401, 'admin', '2020-01-26 19:56:31', '');
INSERT INTO `sys_menu` VALUES ('21', '高级详情页', b'0', 'profile/advanced/Advanced', 'ProfileAdvanced', '/profile/advanced', NULL, '19', 2, b'1', '', NULL, '', 402, 'admin', '2020-01-26 20:03:38', '');
INSERT INTO `sys_menu` VALUES ('22', '结果页', b'0', 'PageView', 'result', '/result', '/result/success', '0', 1, b'1', '', NULL, 'check-circle-o', 15, 'admin', '2020-01-26 20:05:29', '');
INSERT INTO `sys_menu` VALUES ('23', '成功', b'0', 'result/Success', 'ResultSuccess', '/result/success', NULL, '22', 2, b'1', '', NULL, '', 501, 'admin', '2020-01-26 20:06:20', '');
INSERT INTO `sys_menu` VALUES ('24', '失败', b'0', 'result/Error', 'ResultFail', '/result/fail', NULL, '22', 2, b'1', '', NULL, '', 502, 'admin', '2020-01-26 20:06:56', '');
INSERT INTO `sys_menu` VALUES ('25', '异常页', b'0', 'RouteView', 'RouteView', '/exception', '/exception/403', '0', 1, b'1', '', NULL, 'warning', 16, 'admin', '2020-01-26 20:07:46', '');
INSERT INTO `sys_menu` VALUES ('26', '403', b'0', 'exception/403', 'Exception403', '/exception/403', NULL, '25', 2, b'1', '', NULL, '', 601, 'admin', '2020-01-26 20:09:02', '');
INSERT INTO `sys_menu` VALUES ('27', '404', b'0', 'exception/404', 'Exception404', '/exception/404', NULL, '25', 2, b'1', '', NULL, '', 602, 'admin', '2020-01-26 20:09:48', '');
INSERT INTO `sys_menu` VALUES ('28', '500', b'0', 'exception/500', 'Exception500', '/exception/500', NULL, '25', 2, b'1', '', NULL, '', 603, 'admin', '2020-01-26 20:10:14', '');
INSERT INTO `sys_menu` VALUES ('29', '个人页', b'0', 'RouteView', 'account', '/account', '/account/center', '0', 1, b'1', '', NULL, 'user', 17, 'admin', '2020-01-26 20:11:30', '');
INSERT INTO `sys_menu` VALUES ('3', '工作台', b'0', 'dashboard/Workplace', 'Workplace', '/dashboard/workplace', NULL, '2', 2, b'1', '', NULL, '', 101, 'admin', '2020-01-26 19:20:28', '');
INSERT INTO `sys_menu` VALUES ('30', '个人中心', b'0', 'account/center/Index', 'center', '/account/center', NULL, '29', 2, b'1', '', NULL, '', 701, 'admin', '2020-01-26 20:12:31', '');
INSERT INTO `sys_menu` VALUES ('31', '个人设置', b'0', 'account/settings/Index', 'settings', '/account/settings', '/account/settings/base', '29', 2, b'1', '', NULL, '', 702, 'admin', '2020-01-26 20:13:33', '');
INSERT INTO `sys_menu` VALUES ('32', '基本设置', b'0', 'account/settings/BaseSetting', 'BaseSettings', '/account/settings/base', NULL, '31', 2, b'1', '', NULL, '', 7201, 'admin', '2020-01-26 20:16:14', '');
INSERT INTO `sys_menu` VALUES ('33', '安全设置', b'0', 'account/settings/Security', 'SecuritySettings', '/account/settings/security', NULL, '31', 2, b'1', '', NULL, '', 7202, 'admin', '2020-01-26 20:16:55', '');
INSERT INTO `sys_menu` VALUES ('34', '个性化设置', b'0', 'account/settings/Custom', 'CustomSettings', '/account/settings/custom', NULL, '31', 2, b'1', '', NULL, '', 7203, 'admin', '2020-01-26 20:17:51', '');
INSERT INTO `sys_menu` VALUES ('35', '账户绑定', b'0', 'account/settings/Binding', 'BindingSettings', '/account/settings/binding', NULL, '31', 2, b'1', '', NULL, '', 7204, 'admin', '2020-01-26 20:18:22', '');
INSERT INTO `sys_menu` VALUES ('36', '新消息通知', b'0', 'account/settings/Notification', 'NotificationSettings', '/account/settings/notification', NULL, '31', 2, b'1', '', NULL, '', 7205, 'admin', '2020-01-26 20:18:59', '');
INSERT INTO `sys_menu` VALUES ('37', '其他组件', b'0', 'PageView', 'otherPage', '/other', '/other/icon-selector', '0', 1, b'1', '', NULL, 'slack', 18, 'admin', '2020-01-26 20:20:56', '');
INSERT INTO `sys_menu` VALUES ('38', 'IconSelector', b'0', 'other/IconSelectorView', 'TestIconSelect', '/other/icon-selector', NULL, '37', 2, b'1', '', NULL, '', 801, 'admin', '2020-01-26 20:22:04', '');
INSERT INTO `sys_menu` VALUES ('39', '业务布局', b'0', 'RouteView', 'other', '/other/list', '/other/list/tree-list', '37', 2, b'1', '', NULL, '', 802, 'admin', '2020-01-26 20:23:26', '');
INSERT INTO `sys_menu` VALUES ('4', '分析页', b'0', 'dashboard/Analysis', 'Analysis', '/dashboard/analysis', NULL, '2', 2, b'1', '', NULL, '', 102, 'admin', '2020-01-26 19:21:27', '');
INSERT INTO `sys_menu` VALUES ('40', '树目录表格', b'0', 'other/TreeList', 'TreeList', '/other/list/tree-list', NULL, '39', 2, b'1', '', NULL, '', 8201, 'admion', '2020-01-26 20:24:20', '');
INSERT INTO `sys_menu` VALUES ('41', '内联编辑表格', b'0', 'other/TableInnerEditList', 'EditList', '/other/list/edit-table', NULL, '39', 2, b'1', '', NULL, '', 8202, 'admin', '2020-01-26 20:24:58', '');
INSERT INTO `sys_menu` VALUES ('44', '网页链接', b'1', 'RouteView', 'iframe', '/iframe', NULL, '0', 1, b'1', NULL, b'0', 'branches', 12, 'admin', '2020-02-22 13:39:03', NULL);
INSERT INTO `sys_menu` VALUES ('45', 'swagger文档', b'1', 'IframePageView', 'swagger', 'http://132.232.43.102:8080/api/swagger-ui.html', NULL, '44', 2, b'1', NULL, b'0', NULL, 1, 'admin', '2020-02-22 13:41:32', NULL);
INSERT INTO `sys_menu` VALUES ('48', '系统大纲', b'0', 'dashboard/Guide', 'Guide', '/dashboard/guide', NULL, '2', 2, b'0', NULL, b'1', NULL, 102, 'admin', '2020-02-26 21:42:08', NULL);
INSERT INTO `sys_menu` VALUES ('49', '系统管理', b'0', 'RouteView', 'system', '/sys', '/sys/menu', '0', 1, b'1', NULL, b'1', 'setting', 11, 'admin', '2020-02-27 11:28:52', NULL);
INSERT INTO `sys_menu` VALUES ('50', '用户管理', b'0', 'system/UserList', 'user', '/sys/user', NULL, '49', 2, b'1', NULL, b'1', NULL, 1, 'admin', '2020-02-27 11:31:23', NULL);
INSERT INTO `sys_menu` VALUES ('51', '角色管理', b'0', 'system/RoleList', 'role', '/sys/role', NULL, '49', 2, b'1', NULL, b'1', NULL, 2, 'admin', '2020-02-27 11:32:01', NULL);
INSERT INTO `sys_menu` VALUES ('52', '菜单管理', b'0', 'system/PermissionList', 'menu', '/sys/menu', NULL, '49', 2, b'1', NULL, b'1', NULL, 3, 'admin', '2020-02-27 11:33:52', NULL);
INSERT INTO `sys_menu` VALUES ('53', '部门管理', b'0', 'system/DepartList', 'dept', '/sys/dept', NULL, '49', 2, b'1', NULL, b'1', NULL, 4, 'admin', '2020-02-27 11:34:42', NULL);
INSERT INTO `sys_menu` VALUES ('54', '部门用户', b'0', 'system/DepartUserList', 'userDept', '/sys/userDept', NULL, '49', 2, b'1', NULL, b'1', NULL, 5, 'admin', '2020-02-27 11:36:35', NULL);
INSERT INTO `sys_menu` VALUES ('55', '职位管理', b'0', 'system/JobList', 'job', '/sys/job', NULL, '49', 2, b'1', NULL, b'1', NULL, 6, 'admin', '2020-02-27 11:37:47', NULL);
INSERT INTO `sys_menu` VALUES ('58', '数据库监控', b'1', 'IframePageView', 'druid', 'http://132.232.43.102:8080/api/druid/login.html', NULL, '44', 2, b'1', NULL, b'0', NULL, 2, 'admin', '2020-02-27 11:42:34', NULL);
INSERT INTO `sys_menu` VALUES ('59', 'bootstrap-ui文档', b'1', 'IframePageView', 'swagger-bootstrap-ui', 'http://132.232.43.102:8080/api/doc.html', NULL, '44', 2, b'1', NULL, b'0', NULL, 3, 'admin', '2020-02-27 11:43:35', NULL);
INSERT INTO `sys_menu` VALUES ('6', '测试功能', b'0', 'dashboard/TestWork', 'TestWork', '/dashboard/test-work', NULL, '2', 2, b'1', '', NULL, '', 104, 'admin', '2020-01-26 19:23:29', '');
INSERT INTO `sys_menu` VALUES ('60', '叫号大厅', b'1', 'IframePageView', 'LED', 'http://132.232.43.102:8080/api/screen/LED', NULL, '44', 2, b'1', NULL, b'0', NULL, 4, 'admin', '2020-02-27 11:44:33', NULL);
INSERT INTO `sys_menu` VALUES ('61', '进程日志', b'1', 'IframePageView', 'log', 'http://132.232.43.102:8080/api/log/view', NULL, '44', 2, b'1', NULL, b'0', NULL, 5, 'admin', '2020-02-27 11:45:12', NULL);
INSERT INTO `sys_menu` VALUES ('62', '系统监控', b'0', 'RouteView', 'monitor', '/monitor', '/monitor/disk', '0', 1, b'1', NULL, b'0', 'dashboard', 13, 'admin', '2020-02-27 11:46:11', NULL);
INSERT INTO `sys_menu` VALUES ('63', 'Redis监控', b'0', 'youliao/monitor/Redis', 'redis', '/monitor/redis', NULL, '62', 2, b'1', NULL, b'0', NULL, 6, 'admin', '2020-02-27 11:47:39', NULL);
INSERT INTO `sys_menu` VALUES ('64', '系统日志', b'0', 'system/LogList', 'logList', '/sys/log', NULL, '49', 2, b'1', NULL, b'1', NULL, 7, 'admin', '2020-03-13 11:32:42', NULL);
INSERT INTO `sys_menu` VALUES ('65', '常见案列', b'0', 'RouteView', 'youliao', '/youliao', '/youliao/printDemo', '0', 1, b'1', NULL, b'1', 'qrcode', 12, 'admin', '2020-03-15 17:01:39', NULL);
INSERT INTO `sys_menu` VALUES ('66', 'OSS存储', b'0', 'youliao/oss/OSSFileList', 'oss', '/youliao/oss', NULL, '65', 2, b'1', NULL, b'1', NULL, 1, 'admin', '2020-03-15 17:03:06', NULL);
INSERT INTO `sys_menu` VALUES ('67', '支付列表', b'0', 'youliao/pay/PayList', 'pay', '/youliao/pay', NULL, '65', 2, b'1', NULL, b'1', NULL, 2, 'admin', '2020-03-15 17:55:44', NULL);
INSERT INTO `sys_menu` VALUES ('68', '磁盘信息', b'0', 'youliao/monitor/Disk', 'disk', '/monitor/disk', NULL, '62', 2, b'1', NULL, b'1', NULL, 2, 'guest', '2020-04-01 21:23:51', NULL);
INSERT INTO `sys_menu` VALUES ('69', 'JVM信息', b'0', 'youliao/monitor/JvmInfo', 'jvmInfo', '/monitor/jvmInfo', NULL, '62', 2, b'1', NULL, b'1', NULL, 3, 'guest', '2020-04-01 21:24:41', NULL);
INSERT INTO `sys_menu` VALUES ('7', '表单页', b'0', 'PageView', 'form', '/form', '/form/base-form', '0', 1, b'1', '', NULL, 'form', 12, 'admin', '2020-01-26 19:24:39', '');
INSERT INTO `sys_menu` VALUES ('70', '服务器信息', b'0', 'youliao/monitor/Server', 'server', '/monitor/server', NULL, '62', 2, b'1', NULL, b'1', NULL, 4, 'guest', '2020-04-01 21:25:16', NULL);
INSERT INTO `sys_menu` VALUES ('71', 'Tomcat信息', b'0', 'youliao/monitor/TomcatInfo', 'tomcat', '/monitor/tomcatInfo', NULL, '62', 2, b'1', NULL, b'1', NULL, 5, 'guest', '2020-04-01 21:26:02', NULL);
INSERT INTO `sys_menu` VALUES ('72', '新增角色', b'0', NULL, NULL, NULL, NULL, '51', 3, b'1', 'role:add', b'1', NULL, 1, 'admin', '2020-04-06 17:21:15', NULL);
INSERT INTO `sys_menu` VALUES ('73', '编辑角色', b'0', NULL, NULL, NULL, NULL, '51', 3, b'1', 'role:update', b'1', NULL, 2, 'admin', '2020-04-06 17:21:47', NULL);
INSERT INTO `sys_menu` VALUES ('74', '删除角色', b'0', NULL, NULL, NULL, NULL, '51', 3, b'1', 'role:delete', b'1', NULL, 3, 'admin', '2020-04-06 17:22:25', NULL);
INSERT INTO `sys_menu` VALUES ('75', '批量删除角色', b'0', NULL, NULL, '', NULL, '51', 3, b'1', 'role:batch;delete', b'1', NULL, 4, 'admin', '2020-04-06 17:23:25', NULL);
INSERT INTO `sys_menu` VALUES ('76', '角色授权', b'0', NULL, NULL, NULL, NULL, '51', 3, b'1', 'role:auth', b'1', NULL, 5, 'admin', '2020-04-06 17:23:48', NULL);
INSERT INTO `sys_menu` VALUES ('77', '新增用户', b'0', NULL, NULL, NULL, NULL, '50', 3, b'1', 'user:add', b'1', NULL, 1, 'admin', '2020-04-06 17:24:55', NULL);
INSERT INTO `sys_menu` VALUES ('78', '编辑用户', b'0', NULL, NULL, NULL, NULL, '50', 3, b'1', 'user:update', b'1', NULL, 2, 'admin', '2020-04-06 17:25:07', NULL);
INSERT INTO `sys_menu` VALUES ('79', '修改密码', b'0', NULL, NULL, NULL, NULL, '50', 3, b'1', 'user:password:update', b'1', NULL, 3, 'admin', '2020-04-06 17:37:38', NULL);
INSERT INTO `sys_menu` VALUES ('8', '基础表单', b'0', 'form/BasicForm', 'BaseForm', '/form/base-form', NULL, '7', 2, b'1', '', NULL, '', 201, 'admin', '2020-01-26 19:25:51', '');
INSERT INTO `sys_menu` VALUES ('80', '冻结用户', b'0', NULL, NULL, NULL, NULL, '50', 3, b'1', 'user:batch:frozen', b'1', NULL, 3, 'admin', '2020-04-06 17:38:05', NULL);
INSERT INTO `sys_menu` VALUES ('81', '删除用户', b'0', NULL, NULL, NULL, NULL, '50', 3, b'1', 'user:delete', b'1', NULL, 4, 'admin', '2020-04-06 17:38:23', NULL);
INSERT INTO `sys_menu` VALUES ('82', '批量删除用户', b'0', NULL, NULL, NULL, NULL, '50', 3, b'1', 'user:batch:delete', b'1', NULL, 5, 'admin', '2020-04-06 17:39:12', NULL);
INSERT INTO `sys_menu` VALUES ('83', '新增职位', b'0', NULL, NULL, NULL, NULL, '55', 3, b'1', 'job:add', b'1', NULL, 1, 'admin', '2020-04-06 17:39:58', NULL);
INSERT INTO `sys_menu` VALUES ('84', '编辑职位', b'0', NULL, NULL, NULL, NULL, '55', 3, b'1', 'job:update', b'1', NULL, 2, 'admin', '2020-04-06 17:40:19', NULL);
INSERT INTO `sys_menu` VALUES ('85', '删除职位', b'0', NULL, NULL, NULL, NULL, '55', 3, b'1', 'job:delete', b'1', NULL, 3, 'admin', '2020-04-06 17:40:42', NULL);
INSERT INTO `sys_menu` VALUES ('86', '批量删除职位', b'0', NULL, NULL, NULL, NULL, '55', 3, b'1', 'job:batch;delete', b'1', NULL, 4, 'admin', '2020-04-06 17:41:10', NULL);
INSERT INTO `sys_menu` VALUES ('87', '打印测试', b'0', 'youliao/PrintDemo', 'printDemo', '/youliao/printDemo', NULL, '65', 2, b'1', NULL, b'1', NULL, 3, 'admin', '2020-04-06 18:09:04', NULL);
INSERT INTO `sys_menu` VALUES ('88', '登录日志', b'0', 'system/LoginLog', 'loginLog', '/login/log', NULL, '62', 2, b'1', NULL, b'1', NULL, 1, 'admin', '2020-04-06 21:06:46', NULL);
INSERT INTO `sys_menu` VALUES ('89', '新增部门', b'0', NULL, NULL, NULL, NULL, '53', 3, b'1', 'dept:add', b'1', NULL, 1, 'admin', '2020-04-06 21:27:17', NULL);
INSERT INTO `sys_menu` VALUES ('9', '分步表单', b'0', 'form/stepForm/StepForm', 'StepForm', '/form/step-form', NULL, '7', 2, b'1', '', NULL, '', 202, 'admin', '2020-01-26 19:26:46', '');
INSERT INTO `sys_menu` VALUES ('90', '用户绑定部门', b'0', NULL, NULL, NULL, NULL, '54', 3, b'1', 'user:dept:add', b'1', NULL, 1, 'admin', '2020-04-07 14:49:50', NULL);
INSERT INTO `sys_menu` VALUES ('91', '用户部门解除关系', b'0', NULL, NULL, NULL, NULL, '54', 3, b'1', 'user:dept:delete', b'1', NULL, 2, 'admin', '2020-04-07 14:50:27', NULL);
INSERT INTO `sys_menu` VALUES ('92', '编辑部门', b'0', NULL, NULL, NULL, NULL, '53', 3, b'1', 'dept:update', b'1', NULL, 2, 'admin', '2020-04-07 14:56:20', NULL);
INSERT INTO `sys_menu` VALUES ('93', '删除部门', b'0', NULL, NULL, NULL, NULL, '53', 3, b'1', 'dept:delete', b'1', NULL, 3, 'admin', '2020-04-07 14:56:40', NULL);
INSERT INTO `sys_menu` VALUES ('94', '批量删除部门', b'0', NULL, NULL, NULL, NULL, '53', 3, b'1', 'dept:batch:delete', b'1', NULL, 4, 'admin', '2020-04-07 14:57:39', NULL);
INSERT INTO `sys_menu` VALUES ('95', '上传OSS文件', b'0', NULL, NULL, NULL, NULL, '66', 3, b'1', 'oss:upload', b'1', NULL, 1, 'admin', '2020-04-07 15:00:20', NULL);
INSERT INTO `sys_menu` VALUES ('96', '删除OSS文件', b'0', NULL, NULL, NULL, NULL, '66', 3, b'1', 'oss:delete', b'1', NULL, 3, 'admin', '2020-04-07 15:00:48', NULL);
INSERT INTO `sys_menu` VALUES ('97', '下载OSS文件', b'0', NULL, NULL, NULL, NULL, '66', 3, b'1', 'oss:downLoad', b'1', NULL, 2, 'admin', '2020-04-07 15:01:12', NULL);
INSERT INTO `sys_menu` VALUES ('98', '商品下单', b'0', NULL, NULL, NULL, NULL, '67', 3, b'1', 'order:unified', b'1', NULL, 1, 'admin', '2020-04-07 15:01:54', NULL);
INSERT INTO `sys_menu` VALUES ('99', '订单退款', b'0', NULL, NULL, NULL, NULL, '67', 3, b'1', 'order:refund', b'1', NULL, 2, 'admin', '2020-04-07 15:02:20', NULL);

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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'admin', '管理员角色', 'all', 1, NULL, '系统创建', '2020-02-07 14:36:37', '这是管理员角色');
INSERT INTO `sys_role` VALUES ('6', 'test', '测试角色', NULL, NULL, NULL, 'admin', '2020-02-17 21:40:10', '测试角色');
INSERT INTO `sys_role` VALUES ('7', '1', 'test', NULL, NULL, NULL, 'guest', '2020-04-06 17:52:57', '1');

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色ID',
  `dept_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`role_id`, `dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色和部门关联表' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '管理员', 'admin', 'admin', '2020-06-30 22:44:40', '2', NULL, '$2a$10$doq0XR1.fKVkldSgiu9TXuBpZ9gXweX.dzD46aOr4vofs1DI5gznK', b'1', '1', NULL, 'admin', '2020-06-20 22:54:19', '', NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID',
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户和角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1');

SET FOREIGN_KEY_CHECKS = 1;
