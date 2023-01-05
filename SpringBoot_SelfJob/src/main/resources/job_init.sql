drop table if exists `schedule_setting`;
CREATE TABLE `schedule_setting` (
`id` int NOT NULL AUTO_INCREMENT COMMENT '任务ID',
`bean_name` varchar(255) DEFAULT NULL COMMENT 'bean名称',
`method_name` varchar(255) DEFAULT NULL COMMENT '方法名称',
`method_params` varchar(255) DEFAULT NULL COMMENT '方法参数',
`cron_expression` varchar(255) DEFAULT NULL COMMENT 'cron表达式',
`remark` varchar(255) DEFAULT NULL COMMENT '备注',
`job_status` int DEFAULT NULL COMMENT '状态(1正常 0暂停)',
`create_time` datetime DEFAULT NULL COMMENT '创建时间',
`update_time` datetime DEFAULT NULL COMMENT '修改时间',
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 插入一条无参的任务
INSERT INTO `schedule_setting`(`id`, `bean_name`, `method_name`, `method_params`, `cron_expression`, `remark`,
                               `job_status`, `create_time`, `update_time`)
                               VALUES (1, 'myTask1', 'execute', NULL, '1/5 * * * * ?', '无参', 1,
                                       '2023-01-05 17:12:35', '2023-01-05 17:12:38');
