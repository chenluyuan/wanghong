CREATE TABLE dictionary (id BIGINT DEFAULT NULL COMMENT '主键', code VARCHAR (100) DEFAULT NULL COMMENT '字典code', name VARCHAR (100) DEFAULT NULL COMMENT '字典CN', value VARCHAR (100) DEFAULT NULL COMMENT '字典EN', status TINYINT DEFAULT NULL COMMENT '状态(活跃和不活跃)', description VARCHAR (100) DEFAULT NULL COMMENT '描述', PRIMARY KEY (id)) ENGINE=InnoDB CHARSET=utf8;
CREATE TABLE wanghong_info (id BIGINT DEFAULT NULL COMMENT '主键' AUTO_INCREMENT, name VARCHAR (100) DEFAULT NULL COMMENT '网红姓名', wxNo VARCHAR (100) DEFAULT NULL COMMENT '微信号', wxFriendNo INT DEFAULT NULL COMMENT '微信好友数', wbFriendNo INT DEFAULT NULL COMMENT '微博好友数', wbLink VARCHAR (100) DEFAULT NULL COMMENT '微博链接', area VARCHAR (100) DEFAULT NULL COMMENT '地区', school VARCHAR (100) DEFAULT NULL, level SMALLINT DEFAULT NULL COMMENT '等级', remark VARCHAR (100) DEFAULT NULL COMMENT '备注', type SMALLINT DEFAULT NULL COMMENT '类别', PRIMARY KEY (id)) ENGINE=InnoDB CHARSET=utf8;