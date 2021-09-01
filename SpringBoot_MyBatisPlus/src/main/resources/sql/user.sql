-- 创建员工 user 表
CREATE TABLE `user` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(15) DEFAULT NULL,
    `sex` varchar(20) DEFAULT NULL,
    `age` int(6) DEFAULT NULL,
    `description` varchar(50) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- 添加开始时间和修改时间字段, 用于验证自动插入

-- 修改数据库中表结构
ALTER TABLE user ADD column create_time TIMESTAMP null COMMENT '记录插入时间';

ALTER TABLE user ADD column update_time TIMESTAMP null COMMENT '记录修改时间';

-- 更新所有表中数据
UPDATE user SET create_time=NOW();

UPDATE user SET update_time=NOW();

-- 修改数据库中表结构, 添加一个字段作为标记
ALTER TABLE user ADD column flag TINYINT COMMENT '0表示删除，1表示正常';

-- 更新表中所有数据, 全部设置为未删除
UPDATE user SET flag=1;
