/*!40101 SET NAMES utf8 */;
-- 创建员工 user 表
CREATE TABLE `user` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(15) DEFAULT NULL,
    `sex` varchar(20) DEFAULT NULL,
    `age` int(6) DEFAULT NULL,
    `description` varchar(50) DEFAULT NULL,
    `account` varchar(100) DEFAULT NULL,
    `password` varchar(100) DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `user_un_account` (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 插入员工信息数据

insert into user(name,sex,age,description,account,password)
values ('两个蝴蝶飞','男',26,'一个快乐的程序员','yzl','123456'),
       ('周小欢','女',22,'一个小坏蛋','zxh','123456');