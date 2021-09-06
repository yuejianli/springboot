-- 在 springboot 数据库里面 创建 user 表
use springboot;
CREATE TABLE `user` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(15) DEFAULT NULL,
    `sex` varchar(20) DEFAULT NULL,
    `age` int(6) DEFAULT NULL,
    `description` varchar(50) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;

--  在 springboot2 数据库里面 创建 dept 表
use springboot2;

CREATE TABLE `dept` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(200) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
