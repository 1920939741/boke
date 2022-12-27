CREATE TABLE `t_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `menu_name` varchar(32) DEFAULT NULL COMMENT '菜单名称',
  `menu_code` varchar(127) DEFAULT NULL COMMENT '菜单code',
  `parent_id` varchar(127) DEFAULT NULL COMMENT '权限id',
  `url` varchar(127) DEFAULT NULL COMMENT '菜单路径',
  `level` int(1) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_t_menu_menu_code` (`menu_code`(32)) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5685 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统菜单';



CREATE TABLE `t_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `permission_name` varchar(127) DEFAULT NULL COMMENT '权限名字',
  `permission_code` varchar(127) DEFAULT NULL COMMENT '权限code',
  `creation_date` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` int(11) DEFAULT NULL COMMENT '创建人',
  `last_update_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  `last_updated_by` int(11) DEFAULT NULL COMMENT '最后修改人',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_t_permission_permission_code` (`permission_code`(32)) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5795 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='权限';




CREATE TABLE `t_permission_menu` (
  `permission_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  PRIMARY KEY (`permission_id`,`menu_id`) USING BTREE,
  KEY `fk_t_user_role_t_role` (`menu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='权限菜单关联表';


CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_name` varchar(32) DEFAULT NULL COMMENT '角色名字',
  `creation_date` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` int(11) DEFAULT NULL COMMENT '创建人',
  `last_update_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  `last_updated_by` int(11) DEFAULT NULL COMMENT '最后修改人',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=174 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色';



CREATE TABLE `t_role_permission` (
  `role_id` int(11) NOT NULL DEFAULT '0',
  `permission_code` varchar(127) NOT NULL DEFAULT '0',
  PRIMARY KEY (`role_id`,`permission_code`) USING BTREE,
  KEY `fk_t_role_per_t_per` (`permission_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色权限关联表';



CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `username` varchar(32) DEFAULT NULL COMMENT '账号',
  `password` varchar(512) DEFAULT NULL COMMENT '密码',
  `creation_date` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` int(11) DEFAULT NULL COMMENT '创建人',
  `last_update_date` datetime DEFAULT NULL COMMENT '最后修改时间',
  `last_updated_by` int(11) DEFAULT NULL COMMENT '最后修改人',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `enabled` tinyint(1) DEFAULT '1' COMMENT '是否可用',
  `supervisor` varchar(255) DEFAULT NULL COMMENT '上级主管',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21441 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户';



CREATE TABLE `t_user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`) USING BTREE,
  KEY `fk_t_user_role_t_role` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户角色关联表';
