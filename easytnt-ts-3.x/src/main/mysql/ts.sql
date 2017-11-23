
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
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='学校信息表';

DROP TABLE IF EXISTS `ts_term`;
CREATE TABLE `ts_term` (
  `id` BIGINT(20)  NOT NULL AUTO_INCREMENT ,
  `termId` varchar(36) NOT NULL COMMENT '学期唯一标识',
  `schoolId` varchar(36) NOT NULL COMMENT '学校所属租户唯一标识',
  `name` varchar(32) NOT NULL COMMENT '学期名称',
  `yearName` varchar(32) NOT NULL COMMENT '学年名称',
  `yearStarts` SMALLINT (4) NOT NULL COMMENT '学年起始年度',
  `yearEnds` SMALLINT(4) NOT NULL COMMENT '学年结束年度',
  `seq` varchar(8) NOT NULL COMMENT '学期序号：First-上学期Second-下学期',
  `starts` DATE  COMMENT '学期开始时间',
  `ends` DATE  COMMENT '学期结束时间',
  PRIMARY KEY (`id`),
  KEY `x_ts_term_schoolId` (`schoolId`),
  KEY `x_ts_term_termId` (`termId`)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='学期信息表';

DROP TABLE IF EXISTS `ts_teacher`;
CREATE TABLE `ts_teacher` (
  `id` BIGINT(20)  NOT NULL AUTO_INCREMENT ,
  `schoolId` varchar(36) NOT NULL COMMENT '所属学校唯一标识',
  `personId` varchar(36) NOT NULL COMMENT '人员唯一标识，系统中所有人员是唯一的，如果有两个值相同，表示同一个人员',
  `identity` varchar(32) NOT NULL COMMENT '教师身份证件号',
  `identityType` SMALLINT (2) DEFAULT 1  COMMENT '教师身份证件号类型：身份证-1, 学籍号-2, 学号-3,教育云标识-4,QQ-5,微信-6,港澳台证件号-7, 考号-8,其他-99',
  `identityTypeName` varchar (8) DEFAULT '身份证'  COMMENT '教师身份证件名，与identityType保持一致',
  `gender` varchar(4) DEFAULT '无' COMMENT '教师性别',
  `periodStarts` DATE  COMMENT '入职时间，为空时表示一直在职',
  `periodEnds` DATE  COMMENT '离职时间,为空时表示一直在职',
  `courseName` varchar(8) NOT NULL COMMENT '教学课程名称',
  `subjectId` varchar(36) NOT NULL COMMENT '教学课科目唯一标识',
  PRIMARY KEY (`id`),
  KEY `x_ts_teacher_schoolId` (`schoolId`),
  KEY `x_ts_teacher_personId` (`personId`),
  KEY `x_ts_teacher_subjectId` (`subjectId`)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='教师信息表';


DROP TABLE IF EXISTS `ts_master`;
CREATE TABLE `ts_master` (
  `id` BIGINT(20)  NOT NULL AUTO_INCREMENT ,
  `schoolId` varchar(36) NOT NULL COMMENT '所属学校唯一标识',
  `personId` varchar(36) NOT NULL COMMENT '人员唯一标识，系统中所有人员是唯一的，如果有两个值相同，表示同一个人员',
  `identity` varchar(32) NOT NULL COMMENT '校领导身份证件号',
  `identityType` SMALLINT (2) DEFAULT 1  COMMENT '校领导身份证件号类型：身份证-1, 学籍号-2, 学号-3,教育云标识-4,QQ-5,微信-6,港澳台证件号-7, 考号-8,其他-99',
  `identityTypeName` varchar (8) DEFAULT '身份证'  COMMENT '校领导身份证件名，与identityType保持一致',
  `gender` varchar(4) DEFAULT '无' COMMENT '校领导性别',
  `periodStarts` DATE  COMMENT '入职时间，为空时表示一直在职',
  `periodEnds` DATE  COMMENT '离职时间,为空时表示一直在职',
  `post` varchar (8)   COMMENT '职位，如校长，教务主任等',
  PRIMARY KEY (`id`),
  KEY `x_ts_master_schoolId` (`schoolId`),
  KEY `x_ts_master_personId` (`personId`),
  KEY `x_ts_master_post` (`post`)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='校领导信息表，如校长，教务主任等';

DROP TABLE IF EXISTS `ts_grade_master`;
CREATE TABLE `ts_grade_master` (
  `id` BIGINT(20)  NOT NULL AUTO_INCREMENT ,
  `schoolId` varchar(36) NOT NULL COMMENT '所属学校唯一标识',
  `personId` varchar(36) NOT NULL COMMENT '人员唯一标识，系统中所有人员是唯一的，如果有两个值相同，表示同一个人员',
  `identity` varchar(32) NOT NULL COMMENT '校领导身份证件号',
  `identityType` SMALLINT (2) DEFAULT 1  COMMENT '校领导身份证件号类型：身份证-1, 学籍号-2, 学号-3,教育云标识-4,QQ-5,微信-6,港澳台证件号-7, 考号-8,其他-99',
  `identityTypeName` varchar (8) DEFAULT '身份证'  COMMENT '校领导身份证件名，与identityType保持一致',
  `gender` varchar(4) DEFAULT '无' COMMENT '校领导性别',
  `periodStarts` DATE  COMMENT '入职时间，为空时表示一直在职',
  `periodEnds` DATE  COMMENT '离职时间,为空时表示一直在职',
  `gradeName` varchar (8)   COMMENT '年级名称',
  `gradeLevel` varchar (8)   COMMENT '年级序列，英文1到12：One,Two,Three...Twelve',
  `yearName` varchar(32) NOT NULL COMMENT '任职时学年',
  `yearStarts` SMALLINT (4) NOT NULL COMMENT '任职时学年起始年度',
  `yearEnds` SMALLINT(4) NOT NULL COMMENT '任职时学年结束年度',
  PRIMARY KEY (`id`),
  KEY `x_ts_grade_master_schoolId` (`schoolId`),
  KEY `x_ts_grade_master_personId` (`personId`)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='年级主任信息表';

DROP TABLE IF EXISTS `ts_subject_master`;
CREATE TABLE `ts_subject_master` (
  `id` BIGINT(20)  NOT NULL AUTO_INCREMENT ,
  `schoolId` varchar(36) NOT NULL COMMENT '所属学校唯一标识',
  `personId` varchar(36) NOT NULL COMMENT '人员唯一标识，系统中所有人员是唯一的，如果有两个值相同，表示同一个人员',
  `identity` varchar(32) NOT NULL COMMENT '学科负责人身份证件号',
  `identityType` SMALLINT (2) DEFAULT 1  COMMENT '学科负责人身份证件号类型：身份证-1, 学籍号-2, 学号-3,教育云标识-4,QQ-5,微信-6,港澳台证件号-7, 考号-8,其他-99',
  `identityTypeName` varchar (8) DEFAULT '身份证'  COMMENT '教师身份证件名，与identityType保持一致',
  `gender` varchar(4) DEFAULT '无' COMMENT '学科负责人性别',
  `periodStarts` DATE  COMMENT '入职时间，为空时表示一直在职',
  `periodEnds` DATE  COMMENT '离职时间,为空时表示一直在职',
  `subjectId` varchar(36) NOT NULL COMMENT '负责学科唯一标识',
  `subjectName` varchar(16) NOT NULL COMMENT '负责学科唯一标识',
  PRIMARY KEY (`id`),
  KEY `x_ts_subject_master_schoolId` (`schoolId`),
  KEY `x_ts_subject_master_personId` (`personId`),
  KEY `x_ts_subject_master_subjectId` (`subjectId`)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='学科负责人信息表';

DROP TABLE IF EXISTS `ts_clazz_master`;
CREATE TABLE `ts_clazz_master` (
  `id` BIGINT(20)  NOT NULL AUTO_INCREMENT ,
  `schoolId` varchar(36) NOT NULL COMMENT '所属学校唯一标识',
  `personId` varchar(36) NOT NULL COMMENT '人员唯一标识，系统中所有人员是唯一的，如果有两个值相同，表示同一个人员',
  `identity` varchar(32) NOT NULL COMMENT '班主任身份证件号',
  `identityType` SMALLINT (2) DEFAULT 1  COMMENT '班主任身份证件号类型：身份证-1, 学籍号-2, 学号-3,教育云标识-4,QQ-5,微信-6,港澳台证件号-7, 考号-8,其他-99',
  `identityTypeName` varchar (8) DEFAULT '身份证'  COMMENT '教师身份证件名，与identityType保持一致',
  `gender` varchar(4) DEFAULT '无' COMMENT '学科负责人性别',
  `periodStarts` DATE  COMMENT '入职时间，为空时表示一直在职',
  `periodEnds` DATE  COMMENT '离职时间,为空时表示一直在职',
  `clazzId` varchar(36) NOT NULL COMMENT '负责班级唯一标识',
  PRIMARY KEY (`id`),
  KEY `x_ts_clazz_master_schoolId` (`schoolId`),
  KEY `x_ts_clazz_master_personId` (`personId`),
  KEY `x_ts_clazz_master_clazzId` (`clazzId`)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='班主任信息表';