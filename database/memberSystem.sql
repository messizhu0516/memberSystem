-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.20-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win32
-- HeidiSQL 版本:                  9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 membersystem 的数据库结构
CREATE DATABASE IF NOT EXISTS `membersystem` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `membersystem`;

-- 导出  表 membersystem.organization 结构
CREATE TABLE IF NOT EXISTS `organization` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(64) NOT NULL COMMENT '组织名',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `code` varchar(64) NOT NULL COMMENT '编号',
  `icon` varchar(32) DEFAULT NULL COMMENT '图标',
  `pid` bigint(19) DEFAULT NULL COMMENT '父级主键',
  `sort` tinyint(2) NOT NULL DEFAULT '0' COMMENT '排序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `modifier` bigint(20) DEFAULT NULL COMMENT '修改者',
  `modify_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='组织机构';

-- 正在导出表  membersystem.organization 的数据：~1 rows (大约)
DELETE FROM `organization`;
/*!40000 ALTER TABLE `organization` DISABLE KEYS */;
INSERT INTO `organization` (`id`, `name`, `address`, `code`, `icon`, `pid`, `sort`, `create_time`, `creator`, `modifier`, `modify_time`) VALUES
	(1, '系统', '系统账号所属', '01', 'fi-social-windows icon-black', NULL, 0, '2014-02-19 01:00:00', NULL, NULL, NULL);
/*!40000 ALTER TABLE `organization` ENABLE KEYS */;

-- 导出  表 membersystem.resource 结构
CREATE TABLE IF NOT EXISTS `resource` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) NOT NULL COMMENT '资源名称',
  `url` varchar(100) DEFAULT NULL COMMENT '资源路径',
  `open_mode` varchar(32) DEFAULT NULL COMMENT '打开方式 ajax,iframe',
  `description` varchar(255) DEFAULT NULL COMMENT '资源介绍',
  `icon` varchar(64) DEFAULT NULL COMMENT '资源图标',
  `pid` bigint(19) DEFAULT NULL COMMENT '父级资源id',
  `sort` tinyint(2) NOT NULL DEFAULT '0' COMMENT '排序',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态（0：启用，1：停用）',
  `opened` char(8) NOT NULL DEFAULT '1' COMMENT '打开状态',
  `resource_type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '资源类别',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `modifier` bigint(20) DEFAULT NULL COMMENT '修改者',
  `modify_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COMMENT='资源';

-- 正在导出表  membersystem.resource 的数据：~32 rows (大约)
DELETE FROM `resource`;
/*!40000 ALTER TABLE `resource` DISABLE KEYS */;
INSERT INTO `resource` (`id`, `name`, `url`, `open_mode`, `description`, `icon`, `pid`, `sort`, `status`, `opened`, `resource_type`, `create_time`, `creator`, `modifier`, `modify_time`) VALUES
	(1, '权限管理', '', NULL, '系统管理', 'fi-folder icon-black', NULL, 0, 0, '1', 0, '2014-02-19 01:00:00', NULL, NULL, NULL),
	(2, '资源管理', '/resource/manager', 'ajax', '资源管理', 'fi-database icon-black', 1, 1, 0, '1', 0, '2014-02-19 01:00:00', NULL, NULL, NULL),
	(3, '角色管理', '/role/manager', 'ajax', '角色管理', 'fi-torso-business icon-black', 1, 2, 0, '1', 0, '2014-02-19 01:00:00', NULL, NULL, NULL),
	(4, '用户管理', '/user/manager', 'ajax', '用户管理', 'fi-torsos-all icon-black', 1, 3, 0, '1', 0, '2014-02-19 01:00:00', NULL, NULL, NULL),
	(5, '企业管理', '/organization/manager', 'ajax', '部门管理', 'fi-results-demographics icon-black', 1, 4, 0, '', 0, '2014-02-19 01:00:00', NULL, NULL, NULL),
	(6, '列表', '/resource/treeGrid', 'ajax', '资源列表', 'fi-list icon-black', 2, 0, 0, '1', 1, '2014-02-19 01:00:00', NULL, NULL, NULL),
	(7, '添加', '/resource/add', 'ajax', '资源添加', 'fi-page-add icon-black', 2, 0, 0, '1', 1, '2014-02-19 01:00:00', NULL, NULL, NULL),
	(8, '编辑', '/resource/edit', 'ajax', '资源编辑', 'fi-page-edit icon-black', 2, 0, 0, '1', 1, '2014-02-19 01:00:00', NULL, NULL, NULL),
	(9, '删除', '/resource/delete', 'ajax', '资源删除', 'fi-page-delete icon-black', 2, 0, 0, '1', 1, '2014-02-19 01:00:00', NULL, NULL, NULL),
	(10, '列表', '/role/dataGrid', 'ajax', '角色列表', 'fi-list icon-black', 3, 0, 0, '1', 1, '2014-02-19 01:00:00', NULL, NULL, NULL),
	(11, '添加', '/role/add', 'ajax', '角色添加', 'fi-page-add icon-black', 3, 0, 0, '1', 1, '2014-02-19 01:00:00', NULL, NULL, NULL),
	(12, '编辑', '/role/edit', 'ajax', '角色编辑', 'fi-page-edit icon-black', 3, 0, 0, '1', 1, '2014-02-19 01:00:00', NULL, NULL, NULL),
	(13, '删除', '/role/delete', 'ajax', '角色删除', 'fi-page-delete icon-black', 3, 0, 0, '1', 1, '2014-02-19 01:00:00', NULL, NULL, NULL),
	(14, '授权', '/role/grant', 'ajax', '角色授权', 'fi-check icon-black', 3, 0, 0, '1', 1, '2014-02-19 01:00:00', NULL, NULL, NULL),
	(15, '列表', '/user/dataGrid', 'ajax', '用户列表', 'fi-list icon-black', 4, 0, 0, '1', 1, '2014-02-19 01:00:00', NULL, NULL, NULL),
	(16, '添加', '/user/add', 'ajax', '用户添加', 'fi-page-add icon-black', 4, 0, 0, '1', 1, '2014-02-19 01:00:00', NULL, NULL, NULL),
	(17, '编辑', '/user/edit', 'ajax', '用户编辑', 'fi-page-edit icon-black', 4, 0, 0, '1', 1, '2014-02-19 01:00:00', NULL, NULL, NULL),
	(18, '删除', '/user/delete', 'ajax', '用户删除', 'fi-page-delete icon-black', 4, 0, 0, '1', 1, '2014-02-19 01:00:00', NULL, NULL, NULL),
	(19, '列表', '/organization/treeGrid', 'ajax', '用户列表', 'fi-list icon-black', 5, 0, 0, '1', 1, '2014-02-19 01:00:00', NULL, NULL, NULL),
	(20, '添加', '/organization/add', 'ajax', '部门添加', 'fi-page-add icon-black', 5, 0, 0, '1', 1, '2014-02-19 01:00:00', NULL, NULL, NULL),
	(21, '编辑', '/organization/edit', 'ajax', '部门编辑', 'fi-page-edit icon-black', 5, 0, 0, '1', 1, '2014-02-19 01:00:00', NULL, NULL, NULL),
	(22, '删除', '/organization/delete', 'ajax', '部门删除', 'fi-page-delete icon-black', 5, 0, 0, '1', 1, '2014-02-19 01:00:00', NULL, NULL, NULL),
	(23, '系统监控', '', '', NULL, 'fi-folder icon-black', NULL, 3, 0, '0', 0, '2015-12-01 11:44:20', NULL, NULL, NULL),
	(24, '修改密码', '/user/editPwdPage', 'ajax', NULL, 'fi-unlock icon-black', NULL, 4, 0, '1', 1, '2015-12-07 20:23:06', NULL, NULL, NULL),
	(25, '登录日志', '/sysLog/manager', 'ajax', NULL, 'fi-info icon-black', 23, 0, 0, '1', 0, '2016-09-30 22:10:53', NULL, NULL, NULL),
	(26, 'Druid监控', '/druid', 'iframe', NULL, 'fi-monitor icon-black', 23, 0, 0, '1', 0, '2016-09-30 22:12:50', NULL, NULL, NULL),
	(27, '系统图标', '/ignore/icons.html', 'ajax', NULL, 'fi-photo icon-black', 23, 0, 0, '1', 0, '2016-12-24 15:53:47', NULL, NULL, NULL),
	(28, '数据字典', '', '', NULL, 'fi-book icon-black', NULL, 3, 0, '1', 0, '2017-05-19 15:34:59', NULL, NULL, NULL),
	(29, '字典列表', '/dict/manager', 'ajax', NULL, 'fi-list icon-black', 28, 0, 0, '1', 0, '2017-05-19 15:38:25', NULL, NULL, NULL),
	(30, '添加', '/dict/add', 'ajax', NULL, 'fi-page-add icon-black', 28, 0, 0, '', 1, '2017-06-26 16:26:11', NULL, NULL, NULL),
	(31, '编辑', '/dict/edit', 'ajax', NULL, 'fi-page-edit icon-black', 28, 0, 0, '', 1, '2017-06-26 16:26:51', NULL, NULL, NULL),
	(32, '删除', '/dict/delete', 'ajax', NULL, 'fi-page-delete icon-black', 28, 0, 0, '', 1, '2017-06-26 16:27:20', NULL, NULL, NULL);
/*!40000 ALTER TABLE `resource` ENABLE KEYS */;

-- 导出  表 membersystem.role 结构
CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(64) NOT NULL COMMENT '角色名',
  `code` varchar(64) NOT NULL COMMENT '角色编码',
  `sort` tinyint(2) NOT NULL DEFAULT '0' COMMENT '排序号',
  `description` varchar(255) DEFAULT NULL COMMENT '简介',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier` bigint(20) DEFAULT NULL COMMENT '修改者',
  `modify_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='角色';

-- 正在导出表  membersystem.role 的数据：~1 rows (大约)
DELETE FROM `role`;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`, `name`, `code`, `sort`, `description`, `status`, `creator`, `create_time`, `modifier`, `modify_time`) VALUES
	(1, 'admin', 'admin', 0, '超级管理员', 0, NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

-- 导出  表 membersystem.role_resource 结构
CREATE TABLE IF NOT EXISTS `role_resource` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_id` bigint(19) NOT NULL COMMENT '角色id',
  `resource_id` bigint(19) NOT NULL COMMENT '资源id',
  PRIMARY KEY (`id`),
  KEY `idx_role_resource_ids` (`role_id`,`resource_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色资源';

-- 正在导出表  membersystem.role_resource 的数据：~0 rows (大约)
DELETE FROM `role_resource`;
/*!40000 ALTER TABLE `role_resource` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_resource` ENABLE KEYS */;

-- 导出  表 membersystem.sys_dict 结构
CREATE TABLE IF NOT EXISTS `sys_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父级编号',
  `name` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '字典名称',
  `value` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '字典名称对应的值',
  `mark_key` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '标志key（通过此值查询同一类型的数据字典）',
  `sort` tinyint(4) NOT NULL COMMENT '排序',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier` bigint(20) DEFAULT NULL COMMENT '更新者',
  `modify_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='字典表';

-- 正在导出表  membersystem.sys_dict 的数据：~1 rows (大约)
DELETE FROM `sys_dict`;
/*!40000 ALTER TABLE `sys_dict` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_dict` ENABLE KEYS */;

-- 导出  表 membersystem.sys_log 结构
CREATE TABLE IF NOT EXISTS `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `login_name` varchar(255) DEFAULT NULL COMMENT '登陆名',
  `role_name` varchar(255) DEFAULT NULL COMMENT '角色名',
  `opt_content` varchar(1024) DEFAULT NULL COMMENT '内容',
  `client_ip` varchar(255) DEFAULT NULL COMMENT '客户端ip',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统日志';

-- 正在导出表  membersystem.sys_log 的数据：~0 rows (大约)
DELETE FROM `sys_log`;
/*!40000 ALTER TABLE `sys_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_log` ENABLE KEYS */;

-- 导出  表 membersystem.user 结构
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `login_name` varchar(64) NOT NULL COMMENT '登陆名',
  `name` varchar(64) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `salt` varchar(36) DEFAULT NULL COMMENT '密码加密盐',
  `sex` tinyint(2) NOT NULL DEFAULT '0' COMMENT '性别',
  `age` tinyint(2) DEFAULT '0' COMMENT '年龄',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `user_type` char(1) NOT NULL DEFAULT '0' COMMENT '用户类别（s：系统账号，c：企业账号）',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '用户状态',
  `organization_id` bigint(20) DEFAULT NULL COMMENT '所属机构',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `modifier` bigint(20) DEFAULT NULL COMMENT '修改者',
  `modify_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `IDx_user_login_name` (`login_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户';

-- 正在导出表  membersystem.user 的数据：~1 rows (大约)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `login_name`, `name`, `password`, `salt`, `sex`, `age`, `phone`, `user_type`, `status`, `organization_id`, `create_time`, `creator`, `modifier`, `modify_time`) VALUES
	(1, 'admin', 'admin', '47ec2dd791e31e2ef2076caf64ed9b3d', 'test', 0, 25, '18707173376', 's', 0, 1, '2015-12-06 13:14:05', NULL, NULL, NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- 导出  表 membersystem.user_role 结构
CREATE TABLE IF NOT EXISTS `user_role` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(19) NOT NULL COMMENT '用户id',
  `role_id` bigint(19) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`),
  KEY `idx_user_role_ids` (`user_id`,`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户角色';

-- 正在导出表  membersystem.user_role 的数据：~1 rows (大约)
DELETE FROM `user_role`;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` (`id`, `user_id`, `role_id`) VALUES
	(1, 1, 1);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
