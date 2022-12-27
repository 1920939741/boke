CREATE TABLE `t_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '����ID',
  `menu_name` varchar(32) DEFAULT NULL COMMENT '�˵�����',
  `menu_code` varchar(127) DEFAULT NULL COMMENT '�˵�code',
  `parent_id` varchar(127) DEFAULT NULL COMMENT 'Ȩ��id',
  `url` varchar(127) DEFAULT NULL COMMENT '�˵�·��',
  `level` int(1) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_t_menu_menu_code` (`menu_code`(32)) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5685 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='ϵͳ�˵�';



CREATE TABLE `t_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '����ID',
  `permission_name` varchar(127) DEFAULT NULL COMMENT 'Ȩ������',
  `permission_code` varchar(127) DEFAULT NULL COMMENT 'Ȩ��code',
  `creation_date` datetime DEFAULT NULL COMMENT '����ʱ��',
  `created_by` int(11) DEFAULT NULL COMMENT '������',
  `last_update_date` datetime DEFAULT NULL COMMENT '����޸�ʱ��',
  `last_updated_by` int(11) DEFAULT NULL COMMENT '����޸���',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_t_permission_permission_code` (`permission_code`(32)) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5795 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='Ȩ��';




CREATE TABLE `t_permission_menu` (
  `permission_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  PRIMARY KEY (`permission_id`,`menu_id`) USING BTREE,
  KEY `fk_t_user_role_t_role` (`menu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='Ȩ�޲˵�������';


CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '����ID',
  `role_name` varchar(32) DEFAULT NULL COMMENT '��ɫ����',
  `creation_date` datetime DEFAULT NULL COMMENT '����ʱ��',
  `created_by` int(11) DEFAULT NULL COMMENT '������',
  `last_update_date` datetime DEFAULT NULL COMMENT '����޸�ʱ��',
  `last_updated_by` int(11) DEFAULT NULL COMMENT '����޸���',
  `remark` varchar(255) DEFAULT NULL COMMENT '��ע',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=174 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='��ɫ';



CREATE TABLE `t_role_permission` (
  `role_id` int(11) NOT NULL DEFAULT '0',
  `permission_code` varchar(127) NOT NULL DEFAULT '0',
  PRIMARY KEY (`role_id`,`permission_code`) USING BTREE,
  KEY `fk_t_role_per_t_per` (`permission_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='��ɫȨ�޹�����';



CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '����ID',
  `name` varchar(64) DEFAULT NULL COMMENT '����',
  `username` varchar(32) DEFAULT NULL COMMENT '�˺�',
  `password` varchar(512) DEFAULT NULL COMMENT '����',
  `creation_date` datetime DEFAULT NULL COMMENT '����ʱ��',
  `created_by` int(11) DEFAULT NULL COMMENT '������',
  `last_update_date` datetime DEFAULT NULL COMMENT '����޸�ʱ��',
  `last_updated_by` int(11) DEFAULT NULL COMMENT '����޸���',
  `remark` varchar(255) DEFAULT NULL COMMENT '��ע',
  `enabled` tinyint(1) DEFAULT '1' COMMENT '�Ƿ����',
  `supervisor` varchar(255) DEFAULT NULL COMMENT '�ϼ�����',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21441 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='�û�';



CREATE TABLE `t_user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`) USING BTREE,
  KEY `fk_t_user_role_t_role` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='�û���ɫ������';
