# 临时指定字符集 防中文乱码
/*!40101 SET NAMES utf8 */;
CREATE TABLE `product`
(
    `id`       int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id编号',
    `title`    varchar(255)   DEFAULT NULL COMMENT '书名',
    `author`   varchar(255)   DEFAULT NULL COMMENT '作者',
    `category` varchar(255)   DEFAULT NULL COMMENT '类别',
    `price`    decimal(10, 2) DEFAULT NULL COMMENT '价格',
    `image`    varchar(255)   DEFAULT NULL COMMENT '图片地址',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='图书产品';