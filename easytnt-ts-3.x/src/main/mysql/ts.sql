
DROP TABLE IF EXISTS `ts_school`;
CREATE TABLE `ts_school` (
  `id` BIGINT(20)  NOT NULL AUTO_INCREMENT ,
  `schoolId` varchar(36) NOT NULL COMMENT '学校唯一标识',
  `tenantId` varchar(36) NOT NULL COMMENT '学校所属租户唯一标识',
  `name` varchar(32) NOT NULL COMMENT '学校名称',
  `type` varchar(16) NOT NULL COMMENT '学校类型:Primary-小学;Middle-初中;High-高中;PrimaryToMiddlel-小学到初中;MiddleToHigh-初中到高中；All-小学到高中',
  PRIMARY KEY (`id`),
  KEY `x_ts_school_schoolId` (`schoolId`),
  KEY `x_ts_school_tenantId` (`tenantId`)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='学校信息表，记录学校的基本信息';