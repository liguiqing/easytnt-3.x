/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.40 : Database - hx_ezp
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`hx_ezp` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `hx_ezp`;

/*Table structure for table `ps_answer_sheet` */

DROP TABLE IF EXISTS `ps_answer_sheet`;

CREATE TABLE `ps_answer_sheet` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `answer_sheet_id` varchar(36) NOT NULL COMMENT '答题卡唯一标示',
  `exam_id` varchar(36) NOT NULL COMMENT '考试唯一标识',
  `subject_id` varchar(36) NOT NULL COMMENT '科目唯一标识',
  `designer_id` varchar(36)  COMMENT '设计唯一标识',
  `paper_id` varchar(36) DEFAULT NULL COMMENT '科目用卷唯一标识',
  `name` varchar(32) NOT NULL COMMENT '名称',
  `catagory` varchar(4) DEFAULT '' COMMENT 'AB卡时值为AB',
  `sheets` tinyint(2) DEFAULT '1' COMMENT '卡数',
  `pages` tinyint(2) DEFAULT '2' COMMENT '页数',
  `ossPath` varchar(100) DEFAULT NULL COMMENT '模板地址',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `last_operator_id` varchar(36) DEFAULT NULL COMMENT '最后更新者唯一标识',
  `last_operator_name` varchar(64) DEFAULT NULL COMMENT '最后更新者姓名',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_answer_sheet_answer_sheet_id` (`answer_sheet_id`) USING BTREE,
  KEY `x_ps_answer_sheet_exam_id` (`exam_id`) USING BTREE,
  KEY `x_ps_answer_sheet_subject_id` (`subject_id`) USING BTREE,
  KEY `x_ps_answer_sheet_designer_id` (`designer_id`) USING BTREE,
  KEY `x_ps_answer_sheet_paper_id` (`paper_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8 COMMENT='阅卷-科目答题卡';

DROP TABLE IF EXISTS `ps_answer_sheet_scan_template`;

CREATE TABLE `ps_answer_sheet_scan_template` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `answer_sheet_id` varchar(36) NOT NULL COMMENT '答题卡唯一标识',
  `designer_id` varchar(36)  COMMENT '设计者唯一标识',
  `designer_name` varchar(36)  COMMENT '设计者姓名',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `content` midiumtext DEFAULT NULL COMMENT '模板原始数据',	
  `url` varchar(100) DEFAULT NULL COMMENT '模板存储地址',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `last_operator_id` varchar(36) DEFAULT NULL COMMENT '最后更新者唯一标识',
  `last_operator_name` varchar(64) DEFAULT NULL COMMENT '最后更新者姓名',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_answer_sheet_scan_template_answer_sheet_id` (`answer_sheet_id`) USING BTREE,
  KEY `x_ps_answer_sheet_scan_template_designer_id` (`designer_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8 COMMENT='阅卷-科目答题卡扫描模板';


/*Table structure for table `ps_answer_sheet_item` */

DROP TABLE IF EXISTS `ps_answer_sheet_item`;

CREATE TABLE `ps_answer_sheet_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `answer_sheet_item_id` varchar(36) NOT NULL COMMENT '答题卡题目唯一标识',
  `parent_item_id` varchar(36) DEFAULT NULL COMMENT '所属于大题唯一标识',
  `exam_id` varchar(36) NOT NULL COMMENT '考试唯一标识',
  `subject_id` varchar(36) NOT NULL COMMENT '科目唯一标识',
  `answer_sheet_id` varchar(36) NOT NULL COMMENT '答题卡唯一标识',
  `target_subject_id` varchar(36) DEFAULT NULL COMMENT '科目唯一标识,外部系统统一编码',
  `target_subject_name` varchar(16) DEFAULT NULL COMMENT '科目名称,外部系统名称',
  `origin_item_id` varchar(36) DEFAULT NULL COMMENT '库题题目唯一标识',
  `name` varchar(128) DEFAULT NULL COMMENT '题目名称',
  `catagory1` tinyint(1) DEFAULT NULL COMMENT '题目一级类型：1-客观题(KG);2-主观题(KG)',
  `catagory2` tinyint(2) DEFAULT NULL COMMENT '题目二级类型：客观题（1-单选，2-多选, 3-判断题） 主观题（4-填空题, 5-解答题,6-英语作文,7-语文作文,8-选做题）',
  `score` double(6,2) DEFAULT NULL COMMENT '满分',
  `required` tinyint(1) DEFAULT '1' COMMENT '是否必做:0-选做；1-必做',
  `num` tinyint(2) DEFAULT NULL COMMENT '为选择题时，记录扫描段设置的答案个数',
  `content` text COMMENT '参考答案',
  `st_type` tinyint(1) DEFAULT NULL COMMENT '答案类型：1-json;2-文本;3-url',
  `readonly` tinyint(1) DEFAULT NULL COMMENT '编辑状态：0-不可编辑;１-可编辑',
  `rule` tinyint(1) DEFAULT '0' COMMENT '答案组合规则：0系统默认，1自定义',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `last_operator_id` varchar(36) DEFAULT NULL COMMENT '最后更新者唯一标识',
  `last_operator_name` varchar(64) DEFAULT NULL COMMENT '最后更新者姓名',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_answer_sheet_item_exam_id` (`exam_id`) USING BTREE,
  KEY `x_ps_answer_sheet_item_subject_id` (`subject_id`) USING BTREE,
  KEY `x_ps_answer_sheet_item_answer_sheet_id` (`answer_sheet_id`) USING BTREE,
  KEY `x_ps_answer_sheet_item_answer_sheet_item_id` (`answer_sheet_item_id`) USING BTREE,
  KEY `x_ps_answer_sheet_item_target_subject_id` (`target_subject_id`) USING BTREE,
  KEY `x_ps_answer_sheet_item_parent_item_id` (`parent_item_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=670 DEFAULT CHARSET=utf8 COMMENT='阅卷-科目答题卡题目';

/*Table structure for table `ps_answer_sheet_item_editor_st` */

DROP TABLE IF EXISTS `ps_answer_sheet_item_editor_st`;

CREATE TABLE `ps_answer_sheet_item_editor_st` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `answer_sheet_edit_id` varchar(36) NOT NULL COMMENT '答题卡题目唯一标识',
  `content` text COMMENT '参考答案',
  `st_type` tinyint(1) DEFAULT '1' COMMENT '答案类型：1-json;2-文本;3-url',
  `readonly` tinyint(1) DEFAULT NULL COMMENT '编辑状态：0-不可编辑;１-可编辑',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_answer_sheet_item_st_answer_sheet_edit_id` (`answer_sheet_edit_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='阅卷-科目答题卡题编辑者录入的参考答案';

/*Table structure for table `ps_answer_sheet_item_st_edit` */

DROP TABLE IF EXISTS `ps_answer_sheet_item_st_edit`;

CREATE TABLE `ps_answer_sheet_item_st_edit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `answer_sheet_edit_id` varchar(36) NOT NULL COMMENT '答题卡唯一标识',
  `answer_sheet_id` varchar(36) NOT NULL COMMENT '答题卡唯一标识',
  `answer_sheet_item_id` varchar(36) NOT NULL COMMENT '答题卡题目唯一标识',
  `person_id` varchar(36) DEFAULT NULL COMMENT '编辑人唯一标识',
  `editor` varchar(128) DEFAULT NULL COMMENT '编辑人姓名',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_answer_sheet_item_edit_answer_sheet_id` (`answer_sheet_id`) USING BTREE,
  KEY `x_ps_answer_sheet_item_edit_answer_sheet_item_id` (`answer_sheet_item_id`) USING BTREE,
  KEY `x_ps_answer_sheet_item_edit_person_id` (`person_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='阅卷-科目答题卡题目参考答案编辑记录，如客观题答案录入';

/*Table structure for table `ps_answer_sheet_page` */

DROP TABLE IF EXISTS `ps_answer_sheet_page`;

CREATE TABLE `ps_answer_sheet_page` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `answer_sheet_id` varchar(36) NOT NULL COMMENT '答题卡唯一标识',
  `paper_type` varchar(4) DEFAULT 'A4' COMMENT '纸型：A3｜A4等',
  `sheet` tinyint(2) NOT NULL COMMENT '卡码',
  `page` tinyint(2) NOT NULL COMMENT '页码',
  `w` smallint(4) DEFAULT NULL COMMENT '答题卡页图片的宽度',
  `h` smallint(4) DEFAULT NULL COMMENT '答题卡页图片的高度',
  `img_url` varchar(256) DEFAULT NULL COMMENT '纸张对应的扫瞄图片地址',
  `roate` smallint(3) DEFAULT '0' COMMENT '视觉与原图的旋转角度 -360--360',
  `features` text COMMENT '其他特征,默认JSON',
  `exam_number_features` text COMMENT '考号信息特征,默认JSON',
  `kg_features` text COMMENT '客观题信息特征,默认JSON',
  `zg_features` text COMMENT '主观题信息特征,默认JSON',
  `zg_optional_features` text COMMENT '选做题信息特征,默认JSON',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `last_operator_id` varchar(36) DEFAULT NULL COMMENT '最后更新者唯一标识',
  `last_operator_name` varchar(64) DEFAULT NULL COMMENT '最后更新者姓名',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_answer_sheet_page_answer_sheet_id` (`answer_sheet_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=229 DEFAULT CHARSET=utf8 COMMENT='阅卷-科目答题页';

/*Table structure for table `ps_batch_scan` */

DROP TABLE IF EXISTS `ps_batch_scan`;

CREATE TABLE `ps_batch_scan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `batch_id` varchar(36) NOT NULL COMMENT '扫描批次唯一标识',
  `exam_id` varchar(36) NOT NULL COMMENT '考试唯一标识',
  `subject_id` varchar(36) NOT NULL COMMENT '科目唯一标识',
  `answer_sheet_id` varchar(36) NOT NULL COMMENT '所属科答题卡唯一标识',
  `person_id` varchar(36) NOT NULL COMMENT '扫描员唯一标识,源于外部系统',
  `scanner` varchar(36) DEFAULT NULL COMMENT '扫描员姓名',
  `name` varchar(64) DEFAULT NULL COMMENT '扫描班级/考场名称',
  `file_name` varchar(64) DEFAULT NULL COMMENT '扫描批次名称',
  `point` varchar(16) DEFAULT NULL COMMENT '按考场扫描时-考点',
  `room` varchar(16) DEFAULT NULL COMMENT '按考场扫描时-考场号',
  `expected` smallint(3) DEFAULT '-1' COMMENT '计划扫描人数,-1表示无计划扫描数',
  `actual` smallint(3) DEFAULT NULL COMMENT '实际扫描数',
  `exam_number_doubts` smallint(3) DEFAULT NULL COMMENT '本批次中考号疑似错误卡数量',
  `kg_doubts` smallint(3) DEFAULT NULL COMMENT '本批次中客观题疑似错误卡数量',
  `zg_optional_doubts` smallint(3) DEFAULT NULL COMMENT '本批次中选做题疑似错误卡数量',
  `up_loading_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '开始上传时间',
  `exam_number_doubts_done` tinyint(1) DEFAULT '0' COMMENT '本批次中考号疑似错误是扫描时已经处理过,０-未处理;１-已处理;同一扫描批次中必须全部处理完成才算完成',
  `up_loaded_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '上传完成时间',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_batch_scan_batch_id` (`batch_id`) USING BTREE,
  KEY `x_ps_batch_scan_exam_id` (`exam_id`) USING BTREE,
  KEY `x_ps_batch_scan_subject_id` (`subject_id`) USING BTREE,
  KEY `x_ps_batch_scan_answer_sheet_id` (`answer_sheet_id`) USING BTREE,
  KEY `x_ps_batch_scan_file_name` (`file_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='阅卷-扫描批次';

/*Table structure for table `ps_crypt_code` */

DROP TABLE IF EXISTS `ps_crypt_code`;

CREATE TABLE `ps_crypt_code` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `crypt_code_id` varchar(36) NOT NULL COMMENT '考生密号业务ID，唯一标识',
  `subject_id` varchar(36) NOT NULL COMMENT '科目唯一标识',
  `code` varchar(32) NOT NULL COMMENT '考生密码编码',
  `seq` bigint(8) DEFAULT NULL COMMENT '序号',
  `version` tinyint(1) DEFAULT '0' COMMENT '使用状态:0-未分配;1-已分配',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_subject_id` (`subject_id`) USING BTREE,
  KEY `x_ps_code` (`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=80231 DEFAULT CHARSET=utf8 COMMENT='阅卷-科目考生密号编码';

/*Table structure for table `ps_dict` */

DROP TABLE IF EXISTS `ps_dict`;

CREATE TABLE `ps_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dict_id` varchar(36) NOT NULL COMMENT '字典唯一标识',
  `table_name` varchar(36) NOT NULL COMMENT '表名',
  `culumn_name` varchar(36) NOT NULL COMMENT '字段名',
  `data_type` varchar(36) NOT NULL COMMENT '数据类型，与使用的数据数据库系统有关，默认是Mysql',
  `max_length` smallint(4) DEFAULT NULL COMMENT '数据大长度,-1表示未知',
  `readonly` tinyint(1) DEFAULT '0' COMMENT '是否只读，0-非;1-是',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_dict_dict_id` (`dict_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='阅卷-考试业务字典表';

/*Table structure for table `ps_dict_value` */

DROP TABLE IF EXISTS `ps_dict_value`;

CREATE TABLE `ps_dict_value` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dict_id` varchar(36) NOT NULL COMMENT '字典唯一标识',
  `field_name` varchar(36) NOT NULL COMMENT '字典名',
  `field_value` varchar(36) NOT NULL COMMENT '字典值',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_dict_value_dict_id` (`dict_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='阅卷-考试业务字典表';

/*Table structure for table `ps_exam` */

DROP TABLE IF EXISTS `ps_exam`;

CREATE TABLE `ps_exam` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `exam_id` varchar(36) NOT NULL COMMENT '考试唯一标识',
  `project_id` varchar(36) NOT NULL COMMENT '阅卷项目唯一标识',
  `target_org_id` varchar(36) NOT NULL COMMENT '考试使用机构标识，值对应表t_ps_TestedOrg.orgId,校考是学校id;联考是主办学校id;统考是区县/地市id',
  `target_org_type` tinyint(1) NOT NULL COMMENT '考试使用机构类型：1-学校;2-区县;3-地市；校考联考：1；区县级统考：2；地市级统考：3',  
  `creator_org_id` varchar(36) NOT NULL COMMENT '考试创建者机构标识;操作用户所属性机构id;学校用户是所在学校id；区县/地市用户是区县/地市id;运营人员操作时为运营商id',
  `creator_id` varchar(36) NOT NULL COMMENT '考试创建者标识',
  `name` varchar(128) NOT NULL COMMENT '考试名称',
  `grade_name` varchar(16) NOT NULL COMMENT '考试年级名称',
  `study_starts_year` smallint(4) NOT NULL COMMENT '考试年级开始学年',
  `study_ends_year` smallint(4) NOT NULL COMMENT '考试年级结束学年',
  `starts` datetime DEFAULT NULL COMMENT '考试开始时间',
  `ends` datetime DEFAULT NULL COMMENT '阅卷完成时间',
  `status` tinyint(1) DEFAULT '0' COMMENT '行进状态，1-初始;9-完成;0-作废',
  `catagory` tinyint(2) NOT NULL COMMENT '考试分类：1-周练;2-月考;3-单元测验;4-期中考试;5-期末考试;6-中考模拟考;7-高考模拟考',
  `scope` tinyint(1) DEFAULT NULL COMMENT '考试范围：1-校内考试;2-校际联考;3-区域统考;',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '最后更新时间',
  `last_operator_id` varchar(36) DEFAULT NULL COMMENT '最后更新者唯一标识',
  `last_operator_name` varchar(64) DEFAULT NULL COMMENT '最后更新者姓名',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是'
  PRIMARY KEY (`id`),
  KEY `x_ps_exam_exam_id` (`exam_id`) USING BTREE,
  KEY `x_ps_exam_org_id` (`creator_org_id`) USING BTREE,
  KEY `x_ps_exam_creator_id` (`creator_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='阅卷-考试信息';

/*Table structure for table `ps_exam_finished` */

DROP TABLE IF EXISTS `ps_exam_finished`;

CREATE TABLE `ps_exam_finished` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `exam_id` varchar(36) NOT NULL COMMENT '考试唯一标识',
  `creator_org_id` varchar(36) NOT NULL COMMENT '考试创建机构标识',
  `creator_org_name` varchar(64) DEFAULT NULL COMMENT '考试创建机构标识',
  `name` varchar(128) DEFAULT NULL COMMENT '考试名称',
  `type_name` varchar(128) DEFAULT NULL COMMENT '考试类型名称',
  `starts` datetime DEFAULT NULL COMMENT '考试开始时间',
  `ends` datetime DEFAULT NULL COMMENT '阅卷完成时间',
  `admin_ids` text COMMENT '考试管理员唯一标识;多人时以全角;分隔',
  `admin_names` text COMMENT '考试管理员;多人时以全角;分隔',
  `tested_org_ids` text COMMENT '考试参与机构唯一标识，多机构时以全角;分隔',
  `tested_org_names` text COMMENT '考试参与机构，校考是班级名称;联考统考是学校名称;多机构时以全角;分隔',
  `register_examinees` int(8) NOT NULL COMMENT '报名考生总数',
  `absent_examinees` int(8) NOT NULL COMMENT '缺考总数',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_exam_finished_exam_id` (`exam_id`) USING BTREE,
  KEY `x_ps_exam_finished_org_id` (`creator_org_id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='阅卷-考试完成表，考试状态等9时生成统计数据保存到本表';

/*Table structure for table `ps_exam_manager` */

DROP TABLE IF EXISTS `ps_exam_manager`;

CREATE TABLE `ps_exam_manager` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `exam_id` varchar(36) NOT NULL COMMENT '考试唯一标识',
  `person_id` varchar(36) NOT NULL COMMENT '负责唯一标识,源于外部系统',
  `name` varchar(128) NOT NULL COMMENT '负责人姓名',
  `role` tinyint(2) DEFAULT '1' COMMENT '管理角色：1-管理员;2-监督员;3-扫描负责人;4-识别异常处理员;5-识别多评仲裁员',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_exam_manager_exam_id` (`exam_id`) USING BTREE,
  KEY `x_ps_exam_manager_person_id` (`person_id`) USING BTREE,
  KEY `x_ps_exam_manager_role` (`role`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='阅卷-考试管理人员';

/*Table structure for table `ps_exam_mark_control_config` */

DROP TABLE IF EXISTS `ps_exam_mark_control_config`;

CREATE TABLE `ps_exam_mark_control_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `exam_id` varchar(36) NOT NULL COMMENT '考试唯一标识',
  `target` varchar(36) NOT NULL COMMENT '控制目标：Exam-考试；Subject-科目；Item-评题',
  `target_id` varchar(36) NOT NULL COMMENT '控制目标唯一标识,target=Exam时源于t_ps_exam;target=Subject时t_ps_subject;target=Item时t_ps_markItem',
  `controller` varchar(36) NOT NULL COMMENT '控制器名称,可以是包＋类名或者Spring的classId',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_exam_mark_control_config_exam_id` (`exam_id`) USING BTREE,
  KEY `x_ps_exam_mark_control_config_target_id` (`target_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='阅卷-考试阅卷控制配置，可控制的范围：考试；考试科目；评题';

/*Table structure for table `ps_exam_mark_control_strategy` */

DROP TABLE IF EXISTS `ps_exam_mark_control_strategy`;

CREATE TABLE `ps_exam_mark_control_strategy` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `exam_id` varchar(36) NOT NULL COMMENT '考试唯一标识',
  `target` varchar(36) NOT NULL COMMENT '控制目标：Exam-考试;Subject-科目;Item-评题',
  `target_id` varchar(36) NOT NULL COMMENT '控制目标唯一标识,target=Exam时源于t_ps_exam;target=Subject时t_ps_subject;target=Item时t_ps_markItem',
  `strategy` varchar(36) NOT NULL COMMENT '控制策略名称,可以是包＋类名或者Spring的classId',
  `val` varchar(128) DEFAULT NULL COMMENT '控制策略自定义值',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_exam_mark_control_strategy_exam_id` (`exam_id`) USING BTREE,
  KEY `x_ps_exam_mark_control_strategy_target_id` (`target_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='阅卷-考试阅卷控制策略，可控制的范围：考试;考试科目;评题';

/*Table structure for table `ps_exam_mark_control_strategy_custom` */

DROP TABLE IF EXISTS `ps_exam_mark_control_strategy_custom`;

CREATE TABLE `ps_exam_mark_control_strategy_custom` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `person_id` varchar(36) NOT NULL COMMENT '用户唯一标识',
  `target` varchar(36) NOT NULL COMMENT '控制目标：Exam-考试;Subject-科目;Item-评题',
  `target_id` varchar(36) DEFAULT NULL COMMENT '控制目标唯一标识,为空时表示通用控制；target=Exam时源于t_ps_exam;target=Subject时t_ps_subject;target=Item时t_ps_markItem',
  `strategy` varchar(36) NOT NULL COMMENT '控制策略名称,可以是包＋类名或者Spring的classId',
  `val` varchar(128) DEFAULT NULL COMMENT '控制策略自定义值',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_exam_mark_control_strategy_custom_person_id` (`person_id`) USING BTREE,
  KEY `x_ps_exam_mark_control_strategy_custom_target_id` (`target_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='阅卷-个人阅卷控制策略';

/*Table structure for table `ps_exam_number_mark` */

DROP TABLE IF EXISTS `ps_exam_number_mark`;

CREATE TABLE `ps_exam_number_mark` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mark_id` varchar(36) NOT NULL COMMENT '考号题唯一标识',
  `exam_id` varchar(36) NOT NULL COMMENT '考试唯一标识',
  `subject_id` varchar(36) NOT NULL COMMENT '科目唯一标识',
  `batch_id` varchar(36) DEFAULT NULL COMMENT '扫描批次唯一标识',
  `mark_item_id` varchar(36) NOT NULL COMMENT '评题唯一标识',
  `examinee_item_id` varchar(36) NOT NULL COMMENT '考生评题唯一标识',
  `school_id` varchar(36) DEFAULT NULL COMMENT '考生所在学校唯一标识',
  `clazz_id` varchar(36) DEFAULT NULL COMMENT '考生所在班级唯一标识',
  `point` varchar(16) DEFAULT NULL COMMENT '考点号',
  `room` varchar(16) DEFAULT NULL COMMENT '考场号',
  `origin` varchar(36) DEFAULT NULL COMMENT '识别原始结果',
  `origin_updator` varchar(36) DEFAULT NULL COMMENT '识别原始值修改人唯一标识，扫描时若有更新时记录',
  `exam_number` varchar(36) DEFAULT NULL COMMENT '考号',
  `fetch_seq` int(8) DEFAULT NULL COMMENT '提取顺序号，每个考试科目都重新排序',
  `required` tinyint(2) DEFAULT NULL COMMENT '必须完成的评阅次数',
  `times` tinyint(2) DEFAULT NULL COMMENT '已完成的评阅次数',
  `arbiter` tinyint(1) DEFAULT NULL COMMENT '是否需要仲裁，0-不需要;1-需要',
  `fetchsign` int(8) DEFAULT NULL COMMENT '正评提取签名',
  `crypt_code` varchar(32) NOT NULL COMMENT '密号',
  `doubt` tinyint(1) DEFAULT '0' COMMENT '机评时怀疑卷分类：0-无怀疑;1-有怀疑；若有怀疑，需要根据评卷控制策略决定exam_number是否有值',
  `doubt_done` tinyint(1) DEFAULT '0' COMMENT '机评时怀疑卷处理标志：1-已经处理;0-未处理',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_exam_number_mark_mark_id` (`mark_id`) USING BTREE,
  KEY `x_ps_exam_number_mark_exam_id` (`exam_id`) USING BTREE,
  KEY `x_ps_exam_number_mark_subject_id` (`subject_id`) USING BTREE,
  KEY `x_ps_exam_number_mark_batch_id` (`batch_id`) USING BTREE,
  KEY `x_ps_exam_number_mark_school_id` (`school_id`) USING BTREE,
  KEY `x_ps_exam_number_mark_clazz_id` (`clazz_id`) USING BTREE,
  KEY `x_ps_exam_number_mark_mark_item_id` (`mark_item_id`) USING BTREE,
  KEY `x_ps_scoring_mark_crypt_code` (`crypt_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='阅卷-考号评题';

/*Table structure for table `ps_exam_number_mark_fetcher` */

DROP TABLE IF EXISTS `ps_exam_number_mark_fetcher`;

CREATE TABLE `ps_exam_number_mark_fetcher` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `handler_id` varchar(36) NOT NULL COMMENT '考号处理唯一标识',
  `mark_id` varchar(36) NOT NULL COMMENT '考号题唯一标识',
  `marker_id` varchar(36) NOT NULL COMMENT '评卷员唯一标识，markType=Mechine时值为：Mechine',
  `mark_type` tinyint(1) NOT NULL COMMENT '评卷类类型标识：1-识别(Mechine);2-后台手工处理(Hand);',
  `fetch_times` tinyint(2) DEFAULT NULL COMMENT '取出时的评次',
  `fetch_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '提取时间',
  `submit_time` timestamp NULL DEFAULT NULL COMMENT '提交时间',
  `submit_times` tinyint(2) DEFAULT NULL COMMENT '提交时的评次',
  `exam_number` varchar(36) DEFAULT NULL COMMENT '考号',
  `marker_seq` int(8) DEFAULT NULL COMMENT '评卷人的评卷顺序号',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_exam_number_mark_fetcher_handler_id` (`handler_id`),
  KEY `x_ps_exam_number_mark_fetcher_mark_id` (`mark_id`),
  KEY `x_ps_exam_number_mark_fetcher_marker_id` (`marker_id`),
  KEY `x_ps_exam_number_mark_fetcher_mark_type` (`mark_type`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='阅卷-考号评题提取表';

/*Table structure for table `ps_exam_number_mark_handler` */

DROP TABLE IF EXISTS `ps_exam_number_mark_handler`;

CREATE TABLE `ps_exam_number_mark_handler` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `handler_id` varchar(36) NOT NULL COMMENT '考号处理唯一标识',
  `mark_id` varchar(36) NOT NULL COMMENT '考号题唯一标识',
  `marker_id` varchar(36) NOT NULL COMMENT '评卷员唯一标识，markType=Mechine时值为：Mechine',
  `mark_type` tinyint(1) NOT NULL COMMENT '评卷类类型标识：1-识别(Mechine);2-后台手工处理(Hand);',
  `fetch_times` tinyint(2) DEFAULT NULL COMMENT '取出时的评次',
  `fetch_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '提取时间',
  `submit_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '提交时间',
  `submit_times` tinyint(2) DEFAULT NULL COMMENT '提交时的评次',
  `exam_number` varchar(36) DEFAULT NULL COMMENT '考号',
  `doubt` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否怀疑.0-否;1-是',
  `marker_seq` int(8) DEFAULT NULL COMMENT '评卷人的评卷顺序号',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_exam_number_mark_handler_handler_id` (`handler_id`) USING BTREE,
  KEY `x_ps_exam_number_mark_handler_mark_id` (`mark_id`) USING BTREE,
  KEY `x_ps_exam_number_mark_handler_marker_id` (`marker_id`) USING BTREE,
  KEY `x_ps_exam_number_mark_handler_mark_type` (`mark_type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='阅卷-考号评题处理';

/*Table structure for table `ps_exam_number_repeats` */

DROP TABLE IF EXISTS `ps_exam_number_repeats`;

CREATE TABLE `ps_exam_number_repeats` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `exam_id` varchar(36) NOT NULL COMMENT '考试唯一标识',
  `subject_id` varchar(36) NOT NULL COMMENT '科目唯一标识',
  `exam_number` varchar(36) DEFAULT NULL COMMENT '重复的考号',
  `crypt_codes` text COMMENT '重复的考号答题卡密号',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_exam_number_repeats_exam_id` (`exam_id`) USING BTREE,
  KEY `x_ps_exam_number_repeats_subject_id` (`subject_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='阅卷－考号重复记录,每产生一个ExamNumberMarkHandler记录，需要计算是否有考号重复;处理完成时删除数据';

/*Table structure for table `ps_exam_total_score_list` */

DROP TABLE IF EXISTS `ps_exam_total_score_list`;

CREATE TABLE `ps_exam_total_score_list` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `exam_id` varchar(36) NOT NULL COMMENT '考试唯一标识',
  `school_id` varchar(36) DEFAULT NULL COMMENT '考生所在学校唯一标识',
  `clazz_id` varchar(36) DEFAULT NULL COMMENT '考生所在班级唯一标识',
  `examinee_person_id` varchar(36) DEFAULT NULL COMMENT '考生唯一标识',
  `exam` varchar(128) DEFAULT NULL COMMENT '考试名称',
  `exam_date` datetime DEFAULT NULL COMMENT '考试时间',
  `school` varchar(128) DEFAULT NULL COMMENT '学校',
  `grade` varchar(8) DEFAULT NULL COMMENT '年级',
  `clazz` varchar(32) DEFAULT NULL COMMENT '班级',
  `years` varchar(16) DEFAULT NULL COMMENT '学年',
  `term` varchar(32) DEFAULT NULL COMMENT '学期',
  `point` varchar(16) DEFAULT NULL COMMENT '考号',
  `room` varchar(16) DEFAULT NULL COMMENT '考场号',
  `examinee` varchar(32) NOT NULL COMMENT '考生姓名',
  `exam_number` varchar(32) DEFAULT NULL COMMENT '考号',
  `score` double(6,2) DEFAULT '-1.0' COMMENT '得分',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_exam_total_score_list_exam_id` (`exam_id`) USING BTREE,
  KEY `x_ps_exam_total_score_list_school_id` (`school_id`) USING BTREE,
  KEY `x_ps_exam_total_score_list_clazz_id` (`clazz_id`) USING BTREE,
  KEY `x_ps_exam_total_score_list_examinee_person_id` (`examinee_person_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='阅卷-考试总分成绩单';

/*Table structure for table `ps_examinee` */

DROP TABLE IF EXISTS `ps_examinee`;

CREATE TABLE `ps_examinee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `examinee_id` varchar(36) NOT NULL COMMENT '考生唯一标识',
  `exam_id` varchar(36) NOT NULL COMMENT '考试唯一标识',
  `person_id` varchar(36) NOT NULL COMMENT '考生唯一标识,源于外部系统',
  `school_id` varchar(36) DEFAULT NULL COMMENT '考生所在学校唯一标识;源于ps_TestedOrg.targetOrgId',
  `school_name` varchar(123) DEFAULT NULL COMMENT '学校名称',
  `clazz_id` varchar(36) DEFAULT NULL COMMENT '考生所在班级唯一标识;源于ps_TestedOrg.targetOrgId',
  `clazz_name` varchar(20) DEFAULT NULL COMMENT '考生所在班级名称',
  `name` varchar(128) NOT NULL COMMENT '考生姓名',
  `student_no` varchar(255) DEFAULT NULL,
  `exam_number` varchar(32) NOT NULL COMMENT '考号',
  `point` varchar(16) DEFAULT NULL COMMENT '考点号',
  `room` varchar(16) DEFAULT NULL COMMENT '考场号',
  `seat` tinyint(3) DEFAULT NULL COMMENT '座位号',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `last_operator_id` varchar(36) DEFAULT NULL COMMENT '最后更新者唯一标识',
  `last_operator_name` varchar(64) DEFAULT NULL COMMENT '最后更新者姓名',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_examinee_exam_id` (`exam_id`) USING BTREE,
  KEY `x_ps_examinee_examinee_id` (`examinee_id`) USING BTREE,
  KEY `x_ps_examinee_school_id` (`school_id`) USING BTREE,
  KEY `x_ps_examinee_clazz_id` (`clazz_id`) USING BTREE,
  KEY `x_ps_examinee_exam_number` (`exam_number`) USING BTREE,
  KEY `x_ps_examinee_room` (`room`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='阅卷-考生信息';

/*Table structure for table `ps_examinee_item` */

DROP TABLE IF EXISTS `ps_examinee_item`;

CREATE TABLE `ps_examinee_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `examinee_item_id` varchar(36) NOT NULL COMMENT '考生评题唯一标识',
  `exam_id` varchar(36) NOT NULL COMMENT '考试唯一标识',
  `subject_id` varchar(36) NOT NULL COMMENT '科目唯一标识',
  `mark_item_id` varchar(36) NOT NULL COMMENT '评题唯一标识',
  `batch_id` varchar(36) COMMENT '批次唯一标识',
  `crypt_code` varchar(32) NOT NULL COMMENT '密号',
  `sheet_key` varchar(36) NOT NULL COMMENT '扫描学生题卡识别唯一值,旧版的studentKey',
  `purpose` tinyint(1) DEFAULT '1' COMMENT '评题用途：1-主观题;2-考号;3-客观题;4-选做题分题',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_examinee_item_mark_item_id` (`mark_item_id`) USING BTREE,
  KEY `x_ps_examinee_item_subject_id` (`subject_id`) USING BTREE,
  KEY `x_ps_examinee_item_examinee_item_id` (`examinee_item_id`) USING BTREE,
  KEY `x_ps_examinee_item_crypt_code` (`crypt_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=258 DEFAULT CHARSET=utf8 COMMENT='阅卷-科目考生评题';

/*Table structure for table `ps_examinee_item_score` */

DROP TABLE IF EXISTS `ps_examinee_item_score`;

CREATE TABLE `ps_examinee_item_score` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mark_item_id` varchar(36) NOT NULL COMMENT '评题唯一标识',
  `mark_item_score_id` varchar(36) NOT NULL COMMENT '评题得分唯一标识',
  `examinee_item_id` varchar(36) DEFAULT NULL COMMENT '考生评题唯一标识',
  `score` double(6,2) DEFAULT '-1.0' COMMENT '得分',
  `score_names` varchar(1024) DEFAULT NULL COMMENT '给分明细名称',
  `scores` varchar(1024) DEFAULT NULL COMMENT '给分明细',
  `valid` tinyint(1) DEFAULT '1' COMMENT '是否有效:1-有效;2-无效',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_examinee_item_score_mark_item_id` (`mark_item_id`) USING BTREE,
  KEY `x_ps_examinee_item_score_examinee_item_id` (`examinee_item_id`) USING BTREE,
  KEY `x_ps_examinee_item_score_mark_item_score_id` (`mark_item_score_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='阅卷-考生评题得分';

/*Table structure for table `ps_examinee_item_slices` */

DROP TABLE IF EXISTS `ps_examinee_item_slices`;

CREATE TABLE `ps_examinee_item_slices` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mark_item_id` varchar(36) NOT NULL COMMENT '评题唯一标识',
  `sheet_slices_id` varchar(36) NOT NULL COMMENT '答题卡切片唯一标识',
  `examinee_item_id` varchar(36) NOT NULL COMMENT '考生评题唯一标识',
  `img_url` varchar(128) DEFAULT NULL COMMENT '纸卡对应的扫瞄图片地址',
  `page` tinyint(1) DEFAULT NULL COMMENT '页码',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_examinee_item_slices_examinee_item_id` (`examinee_item_id`) USING BTREE,
  KEY `x_ps_examinee_item_slices_mark_item_id` (`mark_item_id`) USING BTREE,
  KEY `x_ps_examinee_item_slices_sheet_slices_id` (`sheet_slices_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=283 DEFAULT CHARSET=utf8 COMMENT='阅卷-考生评题切片';

/*Table structure for table `ps_examinee_sheet` */

DROP TABLE IF EXISTS `ps_examinee_sheet`;

CREATE TABLE `ps_examinee_sheet` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sheet_id` varchar(36) NOT NULL COMMENT '答题卡唯一标识',
  `answer_sheet_id` varchar(36) NOT NULL COMMENT '所属科答题卡唯一标识',
  `exam_id` varchar(36) NOT NULL COMMENT '考试唯一标识',
  `subject_id` varchar(36) NOT NULL COMMENT '科目唯一标识',
  `batch_id` varchar(36) DEFAULT NULL COMMENT '扫描批次唯一标识',
  `sheet_key` varchar(36) DEFAULT NULL COMMENT '扫描时生成的唯一标识，在考号异常示未处理完成时作为考生卡的唯一标识',
  `catagory` varchar(4) DEFAULT NULL COMMENT 'AB卡分类，有分类时值为Ａ或者B',
  `exam_number` varchar(32) DEFAULT NULL COMMENT '考号',
  `seq` bigint(8) NOT NULL COMMENT '科目密号表密号序号',
  `crypt_code` varchar(32) NOT NULL COMMENT '密号,评阅时使用的唯一标识',
  `scoredable` tinyint(1) DEFAULT '1' COMMENT '是否出成绩单：0-不出成绩单，即在成绩单中直接记０分;1-出成绩单元格',
  `markable` tinyint(1) DEFAULT '1' COMMENT '是否进行主观题评阅：0-不评;1-评阅',
  `score` double(6,2) DEFAULT NULL COMMENT '整卷得分',
  `kg_score` double(6,2) DEFAULT NULL COMMENT '客观题得分',
  `zg_score` double(6,2) DEFAULT NULL COMMENT '主观题得分',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_examinee_sheet_sheet_id` (`sheet_id`) USING BTREE,
  KEY `x_ps_examinee_sheet_exam_id` (`exam_id`) USING BTREE,
  KEY `x_ps_examinee_sheet_subject_id` (`subject_id`) USING BTREE,
  KEY `x_ps_examinee_sheet_answer_sheet_id` (`answer_sheet_id`) USING BTREE,
  KEY `x_ps_examinee_sheet_batch_id` (`batch_id`) USING BTREE,
  KEY `x_ps_examinee_sheet_sheet_key` (`sheet_key`) USING BTREE,
  KEY `x_ps_examinee_sheet_crypt_code` (`crypt_code`) USING BTREE,
  KEY `x_ps_examinee_sheet_exam_number` (`exam_number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 COMMENT='阅卷-考生的答题卡';

/*Table structure for table `ps_examinee_sheet_page` */

DROP TABLE IF EXISTS `ps_examinee_sheet_page`;

CREATE TABLE `ps_examinee_sheet_page` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sheet_id` varchar(36) NOT NULL COMMENT '答题卡唯一标识',
  `page` tinyint(2) NOT NULL COMMENT '页码',
  `sheet` tinyint(2) NOT NULL COMMENT '卡码',
  `img_url` varchar(128) DEFAULT NULL COMMENT '纸张对应的扫瞄图片地址',
  `roate` smallint(3) DEFAULT NULL COMMENT '旋转角度 -360--360',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_examinee_answer_sheet_page_sheet_id` (`sheet_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=182 DEFAULT CHARSET=utf8 COMMENT='阅卷-科目答题卡纸张';

/*Table structure for table `ps_kg_mark` */

DROP TABLE IF EXISTS `ps_kg_mark`;

CREATE TABLE `ps_kg_mark` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mark_id` varchar(36) NOT NULL COMMENT '考号题唯一标识',
  `exam_id` varchar(36) NOT NULL COMMENT '考试唯一标识',
  `subject_id` varchar(36) NOT NULL COMMENT '科目唯一标识',
  `batch_id` varchar(36) DEFAULT NULL COMMENT '扫描批次唯一标识',
  `mark_item_id` varchar(36) NOT NULL COMMENT '评题唯一标识',
  `examinee_item_id` varchar(36) NOT NULL COMMENT '考生评题唯一标识',
  `required` tinyint(2) DEFAULT NULL COMMENT '必须完成的评阅次数',
  `times` tinyint(2) DEFAULT NULL COMMENT '已完成的评阅次数',
  `fetch_seq` int(8) DEFAULT NULL COMMENT '提取顺序号，每个考试科目都重新排序',
  `arbiter` tinyint(1) DEFAULT NULL COMMENT '是否需要仲裁，0-不需要;1-需要',
  `fetchsign` int(8) DEFAULT NULL COMMENT '正评提取签名',
  `crypt_code` varchar(32) NOT NULL COMMENT '密号',
  `score` double(6,2) DEFAULT '-1.0' COMMENT '得分',
  `scores` varchar(512) DEFAULT NULL COMMENT '每题得分，以;分隔',
  `optional` text COMMENT '每题选项，以;分隔，无选项以＃代替',
  `doubt` tinyint(1) DEFAULT '0' COMMENT '机评时怀疑卷分类：0-无怀疑;1-有怀疑',
  `doubt_done` tinyint(1) DEFAULT '0' COMMENT '机评时怀疑卷处理标志：1-已经处理;0-未处理',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_kg_mark_mark_id` (`mark_id`) USING BTREE,
  KEY `x_ps_kg_mark_exam_id` (`exam_id`) USING BTREE,
  KEY `x_ps_kg_mark_subject_id` (`subject_id`) USING BTREE,
  KEY `x_ps_kg_mark_mark_item_id` (`mark_item_id`) USING BTREE,
  KEY `x_ps_kg_mark_crypt_code` (`crypt_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='阅卷-客观评题';

/*Table structure for table `ps_kg_mark_fetcher` */

DROP TABLE IF EXISTS `ps_kg_mark_fetcher`;

CREATE TABLE `ps_kg_mark_fetcher` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `handler_id` varchar(36) NOT NULL COMMENT '考号处理唯一标识',
  `mark_id` varchar(36) NOT NULL COMMENT '考号题唯一标识',
  `marker_id` varchar(36) NOT NULL COMMENT '评卷员唯一标识，markType=Mechine时值为：Mechine',
  `mark_type` tinyint(1) NOT NULL COMMENT '评卷类类型标识：1-识别(Mechine);2-后台手工处理(Hand);',
  `fetch_times` tinyint(2) DEFAULT NULL COMMENT '取出时的评次',
  `fetch_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '提取时间',
  `submit_time` timestamp NULL DEFAULT NULL COMMENT '提交时间',
  `submit_times` tinyint(2) DEFAULT NULL COMMENT '提交时的评次',
  `optional` text COMMENT '客观题，默认JSON格式{"1":"A","2":"#","3":"ABC"},无选项以＃代替',
  `marker_seq` int(8) DEFAULT NULL COMMENT '评卷人的评卷顺序号',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_kg_mark_fetcher_handler_id` (`handler_id`) USING BTREE,
  KEY `x_ps_kg_mark_fetcher_mark_id` (`mark_id`) USING BTREE,
  KEY `x_ps_kg_mark_fetcher_marker_id` (`marker_id`) USING BTREE,
  KEY `x_ps_kg_mark_fetcher_mark_type` (`mark_type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='阅卷-客观评题提取表';

/*Table structure for table `ps_kg_mark_handler` */

DROP TABLE IF EXISTS `ps_kg_mark_handler`;

CREATE TABLE `ps_kg_mark_handler` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `handler_id` varchar(36) NOT NULL COMMENT '考号处理唯一标识',
  `mark_id` varchar(36) NOT NULL COMMENT '考号题唯一标识',
  `marker_id` varchar(36) NOT NULL COMMENT '评卷员唯一标识，markType=Mechine时值为：Mechine',
  `mark_type` tinyint(1) NOT NULL COMMENT '评卷类类型标识：1-识别(Mechine);2-后台手工处理(Hand);',
  `fetch_times` tinyint(2) DEFAULT NULL COMMENT '取出时的评次',
  `fetch_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '提取时间',
  `submit_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '提交时间',
  `submit_times` tinyint(2) DEFAULT NULL COMMENT '提交时的评次',
  `optional` text COMMENT '客观题，默认JSON格式{"1":"A","2":"#","3":"ABC"},无选项以＃代替',
  `marker_seq` int(8) DEFAULT NULL COMMENT '评卷人的评卷顺序号',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_kg_mark_handler_handler_id` (`handler_id`) USING BTREE,
  KEY `x_ps_kg_mark_handler_mark_id` (`mark_id`) USING BTREE,
  KEY `x_ps_kg_mark_handler_marker_id` (`marker_id`) USING BTREE,
  KEY `x_ps_kg_mark_handler_mark_type` (`mark_type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='阅卷-客观评题处理';

/*Table structure for table `ps_mark_batch_fetch` */

DROP TABLE IF EXISTS `ps_mark_batch_fetch`;

CREATE TABLE `ps_mark_batch_fetch` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mark_batch_fetch_id` varchar(36) NOT NULL COMMENT '评题批次抽取表唯一标识',
  `batch_id` varchar(36) NOT NULL COMMENT '批次唯一标识',
  `mark_item_id` varchar(36) NOT NULL COMMENT '评题唯一标识',
  `marker_id` varchar(36) DEFAULT NULL COMMENT '评卷员唯一标识',
  `human_marking_type` int(2) NOT NULL COMMENT '人工评阅类型.1-正1评;2-正2评;3-正3评;4-仲裁;5-问题卷',
  `finished` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否完成批次.0-否,1-是',
  `version` tinyint(1) NOT NULL DEFAULT '0',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='阅卷-按批次发卷,记录评卷员取到的批次';

/*Table structure for table `ps_mark_item` */

DROP TABLE IF EXISTS `ps_mark_item`;

CREATE TABLE `ps_mark_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mark_item_id` varchar(36) NOT NULL COMMENT '评题唯一标识',
  `answer_sheet_id` varchar(36) NOT NULL COMMENT '所属科答题卡唯一标识',
  `exam_id` varchar(36) NOT NULL COMMENT '考试唯一标识',
  `subject_id` varchar(36) NOT NULL COMMENT '科目唯一标识',
  `name` varchar(36) NOT NULL COMMENT '名称',
  `seq` tinyint(2) NOT NULL COMMENT '顺序号',
  `score` double(6,2) DEFAULT NULL COMMENT '评题分数',
  `purpose` tinyint(1) DEFAULT '1' COMMENT '评题用途：1-主观题;2-考号;3-客观题;4-选做题分题',
  `groups` varchar(10) DEFAULT NULL COMMENT '选做题组编号',
  `required_times` tinyint(1) DEFAULT '1' COMMENT '评次',
  `status` tinyint(1) DEFAULT '0' COMMENT '评卷进行状态，0-未开始;1-进行中;2-暂停;9-完成;',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_mark_item_mark_item_id` (`mark_item_id`) USING BTREE,
  KEY `x_ps_mark_item_answer_sheet_id` (`answer_sheet_id`) USING BTREE,
  KEY `x_ps_mark_item_exam_id` (`exam_id`) USING BTREE,
  KEY `x_ps_mark_item_subject_id` (`subject_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=441 DEFAULT CHARSET=utf8 COMMENT='阅卷-科目评题';

/*Table structure for table `ps_mark_item_finished` */

DROP TABLE IF EXISTS `ps_mark_item_finished`;

CREATE TABLE `ps_mark_item_finished` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `exam_id` varchar(36) NOT NULL COMMENT '考试唯一标识',
  `subject_id` varchar(36) NOT NULL COMMENT '科目唯一标识',
  `mark_item_id` varchar(36) NOT NULL COMMENT '评题唯一标识',
  `exam_name` varchar(128) DEFAULT NULL COMMENT '考试名称',
  `subject_name` varchar(32) DEFAULT NULL COMMENT '考试科目名称',
  `item_name` varchar(32) DEFAULT NULL COMMENT '科目评题名称',
  `expected` int(8) NOT NULL COMMENT '计划评题总数',
  `finished` int(8) NOT NULL COMMENT '完成总数',
  `erros` int(8) NOT NULL DEFAULT '0' COMMENT '问题卷总数',
  `arbites2` int(8) DEFAULT '0' COMMENT '双评仲裁总数',
  `arbites3` int(8) DEFAULT '0' COMMENT '三评仲裁总数',
  `markers` int(8) DEFAULT '0' COMMENT '有效评人员总数',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_mark_item_finished_exam_id` (`exam_id`) USING BTREE,
  KEY `x_ps_mark_item_finished_subject_id` (`subject_id`) USING BTREE,
  KEY `x_ps_mark_item_finished_mark_item_id` (`mark_item_id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='阅卷-考试科目评题完成表，考试科目状态等9时生成统计数据保存到本表';

/*Table structure for table `ps_mark_item_optional` */

DROP TABLE IF EXISTS `ps_mark_item_optional`;

CREATE TABLE `ps_mark_item_optional` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mark_item_id` varchar(36) NOT NULL COMMENT '评题唯一标识',
  `optional_group_id` varchar(36) NOT NULL COMMENT '选做题组唯一标识',
  `optional` varchar(8) DEFAULT NULL COMMENT '选做题选项',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_mark_item_optional_mark_item_id` (`mark_item_id`) USING BTREE,
  KEY `x_ps_mark_item_optional_group_id` (`optional_group_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=210 DEFAULT CHARSET=utf8 COMMENT='阅卷-科目评题选做题选项';

/*Table structure for table `ps_mark_item_optional_group` */

DROP TABLE IF EXISTS `ps_mark_item_optional_group`;

CREATE TABLE `ps_mark_item_optional_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `optional_group_id` varchar(36) NOT NULL COMMENT '选做题组唯一标识',
  `name` varchar(36) NOT NULL COMMENT '名称',
  `total` tinyint(1) DEFAULT '1' COMMENT '选做题总数',
  `required` tinyint(1) DEFAULT '1' COMMENT '选做题必做数',
  `groups` smallint(3) DEFAULT NULL COMMENT '编组号',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_mark_item_optional_group_optional_group_id` (`optional_group_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 COMMENT='阅卷-科目评题选做题分组';

/*Table structure for table `ps_mark_item_score` */

DROP TABLE IF EXISTS `ps_mark_item_score`;

CREATE TABLE `ps_mark_item_score` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mark_item_score_id` varchar(36) NOT NULL COMMENT '评题给分规则唯一标识',
  `mark_item_id` varchar(36) NOT NULL COMMENT '评题唯一标识',
  `parent_score_id` varchar(36) DEFAULT NULL COMMENT '上级评题唯一标识',
  `name` varchar(36) NOT NULL COMMENT '名称',
  `seq` tinyint(2) NOT NULL COMMENT '顺序号',
  `score` double(6,2) DEFAULT '0.0' COMMENT '最大分值，不能超过ps_answer_sheet_item.score',
  `score_limite` varchar(1024) DEFAULT NULL COMMENT '给分规则',
  `score_linear` tinyint(1) DEFAULT '1' COMMENT '是否线性给分：1-线性;0-非线性',
  `error` double(6,2) DEFAULT '-1.0' COMMENT 't_ps_MarkItem.requiredTimes>1时的得分允许的误差',
  `scope` tinyint(1) DEFAULT '1' COMMENT '适用范围，1-通用；0-个人专用，为0时，person_id不能这空',
  `person_id` varchar(36) DEFAULT NULL COMMENT '评卷员自定义给分点',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_mark_item_score_mark_item_id` (`mark_item_id`) USING BTREE,
  KEY `x_ps_mark_item_score_mark_item_score_id` (`mark_item_score_id`) USING BTREE,
  KEY `x_ps_mark_item_score_parent_score_id` (`parent_score_id`) USING BTREE,
  KEY `x_ps_mark_item_score_person_id` (`person_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8 COMMENT='阅卷-科目评题给分规则';

/*Table structure for table `ps_mark_item_slices` */

DROP TABLE IF EXISTS `ps_mark_item_slices`;

CREATE TABLE `ps_mark_item_slices` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mark_item_id` varchar(36) NOT NULL COMMENT '评题唯一标识',
  `sheet_slices_id` varchar(36) NOT NULL COMMENT '答题卡切片唯一标识',
  `x` tinyint(2) DEFAULT '0' COMMENT '评题面板的x轴',
  `y` tinyint(2) DEFAULT '0' COMMENT '评题面板的y轴',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_mark_item_slices_mark_item_id` (`mark_item_id`) USING BTREE,
  KEY `x_ps_mark_item_slices_sheet_slices_id` (`sheet_slices_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=552 DEFAULT CHARSET=utf8 COMMENT='阅卷-组成科目评题的题卡切片';

/*Table structure for table `ps_mark_item_statistics` */

DROP TABLE IF EXISTS `ps_mark_item_statistics`;

CREATE TABLE `ps_mark_item_statistics` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `subject_id` varchar(36) NOT NULL COMMENT '科目唯一标识',
  `exam_id` varchar(36) NOT NULL COMMENT '考试唯一标识',
  `mark_item_id` varchar(36) NOT NULL COMMENT '评题唯一标识',
  `item_name` varchar(36) NOT NULL COMMENT '评题名称',
  `stats_tartget` varchar(36) NOT NULL COMMENT '统计目标,Team;Marker',
  `stats_tartget_id` varchar(36) NOT NULL COMMENT '统计目标唯一标识',
  `stats_method` tinyint(2) NOT NULL COMMENT '统计指标方法，定义在dict表中',
  `stats_method_name` tinyint(2) NOT NULL COMMENT '统计指标方法名称，如完成数量，平均分，平均速度',
  `stats_value` double(6,2) DEFAULT NULL COMMENT '统计指标值',
  `stats_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '统计时间',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_mark_item_statistics_subject_id` (`subject_id`) USING BTREE,
  KEY `x_ps_mark_item_statistics_exam_id` (`exam_id`) USING BTREE,
  KEY `x_ps_mark_item_statistics_mark_item_id` (`mark_item_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='阅卷-评题统计';

/*Table structure for table `ps_mark_item_to_sheet_item` */

DROP TABLE IF EXISTS `ps_mark_item_to_sheet_item`;

CREATE TABLE `ps_mark_item_to_sheet_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mark_item_id` varchar(36) NOT NULL COMMENT '评题唯一标识',
  `answer_sheet_item_id` varchar(36) NOT NULL COMMENT '答题卡题目唯一标识',
  `mark_item_score_id` varchar(36) DEFAULT NULL COMMENT '评题给分规则唯一标识',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_mark_item_to_sheet_item_mark_item_id` (`mark_item_id`) USING BTREE,
  KEY `x_ps_mark_item_to_sheet_item_answer_sheet_item_id` (`answer_sheet_item_id`) USING BTREE,
  KEY `x_ps_mark_item_to_sheet_item_mark_item_score_id` (`mark_item_score_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=323 DEFAULT CHARSET=utf8 COMMENT='阅卷-科目评题答题卡评题的关系';

/*Table structure for table `ps_mark_team` */

DROP TABLE IF EXISTS `ps_mark_team`;

CREATE TABLE `ps_mark_team` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `team_id` varchar(36) NOT NULL COMMENT '评卷组唯一标识',
  `parent_team_id` varchar(36) DEFAULT NULL COMMENT '上级评卷组唯一标识',
  `exam_id` varchar(36) NOT NULL COMMENT '考试唯一标识',
  `subject_id` varchar(36) NOT NULL COMMENT '科目唯一标识',
  `org_id` varchar(36) DEFAULT NULL COMMENT '校考，统考，联考使用评卷老师的学校唯一标识,即ps_tested_org.target_org_id',
  `item_id` varchar(36) NOT NULL COMMENT '评题唯一标识',
  `name` varchar(128) NOT NULL COMMENT '组名',
  `planned` int(8) DEFAULT '-1' COMMENT '组计划评卷量,-1表示无计划量',
  `finished` INT(8) DEFAULT '0' COMMENT '组实际完成评卷量' ,
  `level` tinyint(1) DEFAULT NULL COMMENT '组层次',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_mark_team_exam_id` (`exam_id`) USING BTREE,
  KEY `x_ps_mark_team_subject_id` (`subject_id`) USING BTREE,
  KEY `x_ps_mark_team_team_id` (`team_id`) USING BTREE,
  KEY `x_ps_mark_team_parent_team_id` (`parent_team_id`) USING BTREE,
  KEY `x_ps_mark_team_item_id` (`item_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='阅卷-评卷组';

/*Table structure for table `ps_mark_team_author` */

DROP TABLE IF EXISTS `ps_mark_team_author`;

CREATE TABLE `ps_mark_team_author` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mark_team_id` varchar(36) NOT NULL COMMENT '评卷员机构唯一标识',
  `mark_item_id` varchar(36) NOT NULL COMMENT '评题唯一标识',
  `author_code` varchar(128) NOT NULL COMMENT '授权码',
  `role` varchar(36) NOT NULL COMMENT '组员角色：Leader-组长;Worker-组员',
  `planned` int(8) DEFAULT '-1' COMMENT '计划评卷量,-1表示无计划评卷量',
  `finished` int(8) DEFAULT '0' COMMENT '实际完成评卷量',
  `status` tinyint(1) DEFAULT '1' COMMENT '可用状态,1-占用；0-可用;9-禁用',
  `person_id` varchar(36) DEFAULT NULL COMMENT '使用者，分配时status必须大于0',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_mark_team_author_mark_team_id` (`mark_team_id`) USING BTREE,
  KEY `x_ps_mark_team_author_mark_item_id` (`mark_item_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='阅卷-评卷员来源机构授权,统考、联考时无法直接分配到评卷员时用';

/*Table structure for table `ps_mark_team_member` */

DROP TABLE IF EXISTS `ps_mark_team_member`;

CREATE TABLE `ps_mark_team_member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `team_id` varchar(36) NOT NULL COMMENT '考试唯一标识',
  `mark_item_id` varchar(36) NOT NULL COMMENT '评题唯一标识',
  `marker_id` varchar(36) NOT NULL COMMENT '评卷员唯一标识',
  `role` varchar(36) NOT NULL COMMENT '组员角色：Leader-组长;Worker-组员',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_mark_team_member_team_id` (`team_id`) USING BTREE,
  KEY `x_ps_mark_team_member_mark_item_id` (`mark_item_id`) USING BTREE,
  KEY `x_ps_mark_team_member_marker_id` (`marker_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=916 DEFAULT CHARSET=utf8 COMMENT='阅卷-评卷组成员';

/*Table structure for table `ps_marker` */

DROP TABLE IF EXISTS `ps_marker`;

CREATE TABLE `ps_marker` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `marker_id` varchar(36) NOT NULL COMMENT '评卷员唯一标识',
  `exam_id` varchar(36) NOT NULL COMMENT '考试唯一标识',
  `subject_id` varchar(36) NOT NULL COMMENT '科目唯一标识',
  `mark_item_id` varchar(36) NOT NULL COMMENT '评题唯一标识',
  `person_id` varchar(36) NOT NULL COMMENT '人员唯一标识,源于外部系统',
  `org_id` varchar(36) DEFAULT NULL COMMENT '校考，统考，联考使用评卷老师的学校唯一标识,即ps_tested_org.target_org_id',
  `name` varchar(128) NOT NULL COMMENT '姓名',
  `role` varchar(16) NOT NULL COMMENT '评卷角色：Normal-普通评卷员;Arbiter-多评仲裁人;Broker-问题卷处理人;Sweepers-尾卷处理人;Terminator-抽查人 ',
  `planned` int(8) DEFAULT '-1' COMMENT '计划评卷量,-1表示无计划评卷量',
  `finished` int(8) DEFAULT '0' COMMENT '实际完成评卷量',
  `status` tinyint(1) DEFAULT '1' COMMENT '工作状态：0-待命;1-正评;2-试评;3-暂停',
  `online` tinyint(1) DEFAULT '0' COMMENT '在线状态：0-离线;1-在线',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_marker_exam_id` (`exam_id`) USING BTREE,
  KEY `x_ps_marker_subject_id` (`subject_id`) USING BTREE,
  KEY `x_ps_marker_mark_item_id` (`mark_item_id`) USING BTREE,
  KEY `x_ps_marker_marker_id` (`marker_id`) USING BTREE,
  KEY `x_ps_marker_person_id` (`person_id`) USING BTREE,
  KEY `x_ps_marker_role` (`role`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1023 DEFAULT CHARSET=utf8 COMMENT='阅卷-评卷人';

/*Table structure for table `ps_marking_project` */

DROP TABLE IF EXISTS `ps_marking_project`;

CREATE TABLE `ps_marking_project` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `project_id` varchar(36) NOT NULL COMMENT '阅卷项目唯一标识',
  `creator_id` varchar(36) NOT NULL COMMENT '考试项目创建者标识',
  `manager_ids` text COMMENT '考试项目管理者标识',
  `name` varchar(128) NOT NULL COMMENT '项目名称',
  `status` tinyint(1) DEFAULT '0' COMMENT '状态，1-可用;0-作废;9-完成',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_marking_project_project_id` (`project_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='阅卷-阅卷项目';

/*Table structure for table `ps_out_interface` */

DROP TABLE IF EXISTS `ps_out_interface`;

CREATE TABLE `ps_out_interface` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `interface_id` varchar(128) NOT NULL COMMENT '外部服务唯一标识',
  `server` varchar(256) NOT NULL COMMENT '接口服务域名或者IP及端口，如http://api.exsample.com:9988',
  `url` varchar(256) NOT NULL COMMENT '接口服务地址，如/api/foo/bar/#?p1=#&p2=#,参数用#表示',
  `http_method` varchar(8) DEFAULT NULL COMMENT 'Http Method，为空时支持所有方法：GET,POST,PUT,DELTE,OPTIONAL,TRACE',
  `request_body` text COMMENT '使用POST/PUT时request body json串',
  `version` varchar(8) DEFAULT NULL COMMENT '接口版本',
  `spec` text COMMENT '接口说明',
  `status` tinyint(1) DEFAULT '1' COMMENT '使用状态，0-不可用;1-可用',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_out_interface_interface_id` (`interface_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='阅卷-外部接口';

/*Table structure for table `ps_paper` */

DROP TABLE IF EXISTS `ps_paper`;

CREATE TABLE `ps_paper` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `paper_id` varchar(36) NOT NULL COMMENT '考试科目唯一标识',
  `subject_id` varchar(36) NOT NULL COMMENT '考试科目唯一标识',
  `exam_id` varchar(36) NOT NULL COMMENT '考试唯一标识',
  `target_subject_id` varchar(36) DEFAULT NULL COMMENT '科目唯一标识,外部系统统一编码',
  `person_id` varchar(36) NOT NULL COMMENT '试卷上传者唯一标识',
  `name` varchar(128) NOT NULL COMMENT '试卷名称',
  `address` varchar(256) DEFAULT NULL COMMENT '试卷存储的绝对地址',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_paper_exam_id` (`paper_id`) USING BTREE,
  KEY `x_ps_paper_target_subject_id` (`target_subject_id`) USING BTREE,
  KEY `x_ps_paper_subject_id` (`subject_id`) USING BTREE,
  KEY `x_ps_paper_person_id` (`person_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='阅卷-考试科目用卷';

/*Table structure for table `ps_scan_doubt_statistics` */

DROP TABLE IF EXISTS `ps_scan_doubt_statistics`;

CREATE TABLE `ps_scan_doubt_statistics` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `exam_id` varchar(36) NOT NULL COMMENT '考试唯一标识',
  `subject_id` varchar(36) NOT NULL COMMENT '科目唯一标识',
  `exam_number_doubts` smallint(8) DEFAULT '0' COMMENT '科目考号疑似错误卡数量',
  `kg_doubts` int(8) DEFAULT '0' COMMENT '科目客观题疑似错误卡数量',
  `zg_optional_doubts` int(8) DEFAULT '0' COMMENT '科目选做题疑似错误卡数量',
  `exam_number_doubts_finished` smallint(8) DEFAULT '0' COMMENT '科目考号疑似错误卡处理完成数量',
  `kg_doubts_finished` int(8) DEFAULT '0' COMMENT '科目客观题疑似错误卡处理完成数量',
  `zg_optional_doubts_finished` int(8) DEFAULT '0' COMMENT '科目选做题疑似错误卡处理完成数量',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_scan_statistics_exam_id` (`exam_id`) USING BTREE,
  KEY `x_ps_scan_statistics_subject_id` (`subject_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='阅卷-科目扫描怀疑及处理统计';

/*Table structure for table `ps_scan_statistics` */

DROP TABLE IF EXISTS `ps_scan_statistics`;

CREATE TABLE `ps_scan_statistics` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `exam_id` varchar(36) NOT NULL COMMENT '考试唯一标识',
  `subject_id` varchar(36) NOT NULL COMMENT '科目唯一标识',
  `answer_sheet_id` varchar(36) NOT NULL COMMENT '所属科答题卡唯一标识',
  `statistics_target` varchar(36) NOT NULL COMMENT '扫描统计目标唯一标识,Scanner-personId;School-t_ps_Examinee.schoolId;Clazz-t_ps_Examinee.clazzId;Point-t_ps_Examinee.point;Room-t_ps_Examinee.room',
  `target_type` varchar(8) DEFAULT NULL COMMENT '扫描统计类型:Scanner-扫描员;School-学校;Clazz-班级;Point-考点;Room-考场',
  `target_name` varchar(32) DEFAULT NULL COMMENT '扫描统计类型:Scanner-扫描员;School-学校;Clazz-班级;Point-考点;Room-考场',
  `expected` int(8) DEFAULT NULL COMMENT '计划扫描完成量，-1表示无计划',
  `actual` smallint(3) DEFAULT NULL COMMENT '实际扫描数',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_scan_statistics_exam_id` (`exam_id`) USING BTREE,
  KEY `x_ps_scan_statistics_subject_id` (`subject_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='阅卷-科目扫描统计';

/*Table structure for table `ps_score_calc_state` */

DROP TABLE IF EXISTS `ps_score_calc_state`;

CREATE TABLE `ps_score_calc_state` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `exam_id` varchar(36) NOT NULL COMMENT '考试唯一标识',
  `subject_id` varchar(36) NOT NULL COMMENT '科目唯一标识',
  `type` int(8) DEFAULT NULL COMMENT '算分类型',
  `state` int(8) DEFAULT NULL COMMENT '算分状态',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_score_calc_state_exam_id` (`exam_id`) USING BTREE,
  KEY `x_ps_score_calc_state_subject_id` (`subject_id`) USING BTREE,
  KEY `x_ps_score_calc_state_type` (`type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='算分状态表';

/*Table structure for table `ps_scoring_mark` */

DROP TABLE IF EXISTS `ps_scoring_mark`;

CREATE TABLE `ps_scoring_mark` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mark_id` varchar(36) NOT NULL COMMENT '评分题唯一标识',
  `exam_id` varchar(36) NOT NULL COMMENT '考试唯一标识',
  `subject_id` varchar(36) NOT NULL COMMENT '科目唯一标识',
  `batch_id` varchar(36) DEFAULT NULL COMMENT '扫描批次唯一标识',
  `mark_item_id` varchar(36) NOT NULL COMMENT '评题唯一标识',
  `examinee_item_id` varchar(36) NOT NULL COMMENT '考生评题唯一标识',
  `school_id` varchar(36) DEFAULT NULL COMMENT '考生所在学校唯一标识',
  `clazz_id` varchar(36) DEFAULT NULL COMMENT '考生所在班级唯一标识',
  `purpose` varchar(16) NOT NULL DEFAULT 'Formal' COMMENT '评题用途：Formal-正评;Learnning-试评;Trainning-培训;Monitor-质控',
  `point` varchar(16) DEFAULT NULL COMMENT '考号',
  `room` varchar(16) DEFAULT NULL COMMENT '考场号',
  `crypt_code` varchar(32) NOT NULL COMMENT '密号',
  `group_no` int(4) DEFAULT NULL COMMENT '组号，purpose=Trainning时用',
  `fetch_seq` int(8) DEFAULT NULL COMMENT '提取顺序号，每个考试科目都重新排序',
  `required` tinyint(2) DEFAULT NULL COMMENT '必须完成的评阅次数',
  `times` tinyint(2) DEFAULT NULL COMMENT '已完成的评阅次数',
  `arbiter` tinyint(1) DEFAULT NULL COMMENT '是否需要仲裁，0-不需要;1-需要',
  `fetchsign` int(8) DEFAULT NULL COMMENT '正评提取签名',
  `unabled` tinyint(1) DEFAULT '0' COMMENT '问题卷标记：0-正常;1-有问题',
  `formal_marker_ids` text COMMENT '正评已完成的评卷id,正评提交时更新，多人用;分隔',
  `score` double(6,2) DEFAULT '-1.0' COMMENT '得分',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_scoring_mark_mark_id` (`mark_id`) USING BTREE,
  KEY `x_ps_scoring_mark_exam_id` (`exam_id`) USING BTREE,
  KEY `x_ps_scoring_mark_subject_id` (`subject_id`) USING BTREE,
  KEY `x_ps_scoring_mark_mark_item_id` (`mark_item_id`) USING BTREE,
  KEY `x_ps_scoring_mark_purpose` (`purpose`) USING BTREE,
  KEY `x_ps_scoring_mark_crypt_code` (`crypt_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=214 DEFAULT CHARSET=utf8 COMMENT='阅卷-可评分的评题';

/*Table structure for table `ps_scoring_mark_catagory` */

DROP TABLE IF EXISTS `ps_scoring_mark_catagory`;

CREATE TABLE `ps_scoring_mark_catagory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `handler_id` varchar(36) NOT NULL COMMENT '评分处理唯一标识',
  `mark_id` varchar(36) NOT NULL COMMENT '评分题唯一标识',
  `catagory` tinyint(1) DEFAULT NULL COMMENT '评题分类:1-优秀题(Excellent)，2-样题(Sample)，3-典型题(Classic)',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_scoring_mark_text_handler_id` (`handler_id`) USING BTREE,
  KEY `x_ps_scoring_mark_text_mark_id` (`mark_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='阅卷-主观题评题分类，如优秀题，样题，典型题等';

/*Table structure for table `ps_scoring_mark_error_msg` */

DROP TABLE IF EXISTS `ps_scoring_mark_error_msg`;

CREATE TABLE `ps_scoring_mark_error_msg` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `handler_id` varchar(36) NOT NULL COMMENT '考号处理唯一标识',
  `mark_id` varchar(36) NOT NULL COMMENT '考号题唯一标识',
  `marker_id` varchar(36) NOT NULL COMMENT '评卷员唯一标识',
  `msg` varchar(100) NOT NULL COMMENT '问题卷说明',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_scoring_mark_error_msg_handler_id` (`handler_id`),
  KEY `x_ps_scoring_mark_error_msg_mark_id` (`mark_id`),
  KEY `x_ps_scoring_mark_error_msg_marker_id` (`marker_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='阅卷-问题卷说明表';

/*Table structure for table `ps_scoring_mark_fetcher` */

DROP TABLE IF EXISTS `ps_scoring_mark_fetcher`;

CREATE TABLE `ps_scoring_mark_fetcher` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `handler_id` varchar(36) NOT NULL COMMENT '评分处理唯一标识',
  `mark_id` varchar(36) NOT NULL COMMENT '评分题唯一标识',
  `mark_item_id` varchar(36) NOT NULL COMMENT '评题唯一标识',
  `marker_id` varchar(36) NOT NULL COMMENT '评卷唯一标识',
  `mark_type` tinyint(2) DEFAULT NULL COMMENT '评卷类类型标识：评卷类类型标识：1-识别(Mechine);2-Formal-正评;3-Arbiter-仲裁/抽查给分;4-Error-提问题卷;5-Borker-问题卷处理;6-ReFormal-回评;7-Learnning-试评;8-Self-自评;9-ForceFormal-发回重评;10-Monitor-质控',
  `fetch_times` tinyint(2) DEFAULT NULL COMMENT '正评取出时的评次',
  `fetch_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '提取时间',
  `valid` tinyint(1) DEFAULT NULL COMMENT '评分是否有效',
  `submit_time` timestamp NULL DEFAULT NULL COMMENT '提交时间',
  `submit_times` tinyint(2) DEFAULT NULL COMMENT '正评提交时的评次',
  `score` double(6,2) DEFAULT '-1.0' COMMENT '得分',
  `scores` varchar(128) DEFAULT NULL COMMENT '得分;多个给分点以;分隔',
  `unabled` tinyint(1) DEFAULT '1' COMMENT '问题卷标记：0-有问题;1-正常;标记为问题卷时scores为Null',
  `unabled_catagory` tinyint(1) DEFAULT '1' COMMENT '问题卷分类：1-看不清楚;2-答错位置;3-图片错位;4-图片错科',
  `marker_seq` int(8) DEFAULT NULL COMMENT '评卷人的评卷顺序号',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_scoring_mark_fetcher_handler_id` (`handler_id`) USING BTREE,
  KEY `x_ps_scoring_mark_fetcher_mark_id` (`mark_id`) USING BTREE,
  KEY `x_ps_scoring_mark_fetcher_marker_id` (`marker_id`) USING BTREE,
  KEY `x_ps_scoring_mark_fetcher_mark_type` (`mark_type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='阅卷-主观题评题提取表';

/*Table structure for table `ps_scoring_mark_handler` */

DROP TABLE IF EXISTS `ps_scoring_mark_handler`;

CREATE TABLE `ps_scoring_mark_handler` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `handler_id` varchar(36) NOT NULL COMMENT '评分处理唯一标识',
  `mark_id` varchar(36) NOT NULL COMMENT '评分题唯一标识',
  `mark_item_id` varchar(36) NOT NULL COMMENT '评题唯一标识',
  `marker_id` varchar(36) NOT NULL COMMENT '评卷唯一标识',
  `mark_type` tinyint(2) DEFAULT NULL COMMENT '评卷类类型标识：评卷类类型标识：1-识别(Mechine);2-Formal-正评;3-Arbiter-仲裁/抽查给分;4-Error-提问题卷;5-Borker-问题卷处理;6-ReFormal-回评;7-Learnning-试评;8-Self-自评;9-ForceFormal-发回重评;10-Monitor-质控',
  `fetch_times` tinyint(2) DEFAULT NULL COMMENT '正评取出时的评次',
  `fetch_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '提取时间',
  `valid` tinyint(1) DEFAULT NULL COMMENT '评分是否有效',
  `submit_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '提交时间',
  `submit_times` tinyint(2) DEFAULT NULL COMMENT '正评提交时的评次',
  `score` double(6,2) DEFAULT '-1.0' COMMENT '得分',
  `scores` varchar(128) DEFAULT NULL COMMENT '得分;多个给分点以;分隔',
  `unabled` tinyint(1) DEFAULT '1' COMMENT '问题卷标记：0-有问题;1-正常;标记为问题卷时scores为Null',
  `unabled_catagory` tinyint(1) DEFAULT '1' COMMENT '问题卷分类：1-看不清楚;2-答错位置;3-图片错位;4-图片错科',
  `marker_seq` int(8) DEFAULT NULL COMMENT '评卷人的评卷顺序号',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_scoring_mark_handler_handler_id` (`handler_id`) USING BTREE,
  KEY `x_ps_scoring_mark_handler_mark_id` (`mark_id`) USING BTREE,
  KEY `x_ps_scoring_mark_handler_marker_id` (`marker_id`) USING BTREE,
  KEY `x_ps_scoring_mark_handler_mark_type` (`mark_type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='阅卷-主观题评题处理';

/*Table structure for table `ps_scoring_mark_symbol` */

DROP TABLE IF EXISTS `ps_scoring_mark_symbol`;

CREATE TABLE `ps_scoring_mark_symbol` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `symbol_id` varchar(36) NOT NULL COMMENT '标志表业务主键',
  `handler_id` varchar(36) NOT NULL COMMENT '评分处理唯一标识',
  `mark_id` varchar(36) NOT NULL COMMENT '评分题唯一标识',
  `path_json` text COMMENT 'Json串标记路径',
  `symbol` varchar(16) DEFAULT NULL COMMENT '标志：Tick-勾;X-叉;HalfTick-半勾;Stress-着重线;Circle-圆圈',
  `x` smallint(4) NOT NULL COMMENT '标志框起点X轴坐标',
  `y` smallint(4) NOT NULL COMMENT '标志框起点Y轴坐标',
  `w` smallint(4) NOT NULL COMMENT '标志框宽',
  `h` smallint(4) NOT NULL COMMENT '标志框高',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_scoring_mark_symbol_handler_id` (`handler_id`) USING BTREE,
  KEY `x_ps_scoring_mark_symbol_mark_id` (`mark_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='阅卷-主观题评题标记';

/*Table structure for table `ps_scoring_mark_text` */

DROP TABLE IF EXISTS `ps_scoring_mark_text`;

CREATE TABLE `ps_scoring_mark_text` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `text_id` varchar(36) NOT NULL COMMENT '评语表业务主键',
  `handler_id` varchar(36) NOT NULL COMMENT '评分处理唯一标识',
  `mark_id` varchar(36) NOT NULL COMMENT '评分题唯一标识',
  `comments` text COMMENT '评语',
  `x` smallint(4) NOT NULL COMMENT '评语框起点X轴坐标',
  `y` smallint(4) NOT NULL COMMENT '评语框起点Y轴坐标',
  `w` smallint(4) NOT NULL COMMENT '评语框宽',
  `h` smallint(4) NOT NULL COMMENT '评语框高',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_scoring_mark_text_handler_id` (`handler_id`) USING BTREE,
  KEY `x_ps_scoring_mark_text_mark_id` (`mark_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='阅卷-主观题评题评语';

/*Table structure for table `ps_sheet_scan` */

DROP TABLE IF EXISTS `ps_sheet_scan`;

CREATE TABLE `ps_sheet_scan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sheet_scan_id` varchar(36) NOT NULL COMMENT '扫描卡唯一标识',
  `batch_id` varchar(36) NOT NULL COMMENT '扫描批次唯一标识',
  `file_name` varchar(64) DEFAULT NULL COMMENT '扫描批次名称',
  `sheet` tinyint(2) DEFAULT NULL COMMENT '张码/卡码',
  `page` tinyint(2) DEFAULT NULL COMMENT '页码',
  `features` text COMMENT '其他特征,默认JSON',
  `exam_number_features` text COMMENT '考号信息特征,默认JSON',
  `kg_features` text COMMENT '客观题信息特征,默认JSON',
  `zg_features` text COMMENT '主观题信息特征,默认JSON',
  `zg_optional_features` text COMMENT '选做题信息特征,默认JSON',
  `exam_number_doubt` tinyint(1) DEFAULT '0' COMMENT '考号错误疑似:0-无疑似错误;1-有疑似错误',
  `kg_doubt` tinyint(1) DEFAULT '0' COMMENT '客观错误疑似:0-无疑似错误;1-有疑似错误',
  `zg_optional_doubt` tinyint(1) DEFAULT '0' COMMENT '选择题错误疑似:0-无疑似错误;1-有疑似错误',
  `exam_number_doubt_done` tinyint(1) DEFAULT '0' COMMENT '考号疑似错误是扫描时已经处理过,0-未处理;1-已处理',
  `sheet_key` varchar(36) DEFAULT NULL COMMENT '扫描时生成的唯一标识，相同的sheetKey表示一个考生的答题卡,按pages升序排列',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_sheet_scan_sheet_scan_id` (`sheet_scan_id`) USING BTREE,
  KEY `x_ps_sheet_scan_batch_id` (`batch_id`) USING BTREE,
  KEY `x_ps_sheet_scan_file_name` (`file_name`) USING BTREE,
  KEY `x_ps_sheet_scan_sheet_key` (`sheet_key`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=243 DEFAULT CHARSET=utf8 COMMENT='阅卷-扫描答题卡';

/*Table structure for table `ps_sheet_slices` */

DROP TABLE IF EXISTS `ps_sheet_slices`;

CREATE TABLE `ps_sheet_slices` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sheet_slices_id` varchar(36) NOT NULL COMMENT '答题卡切片唯一标识',
  `answer_sheet_id` varchar(36) NOT NULL COMMENT '所属科答题卡唯一标识',
  `page` tinyint(2) NOT NULL COMMENT '页码，来源于ps_AnswerSheetPage.page',
  `sheet` tinyint(2) NOT NULL COMMENT '张码，来源于ps_AnswerSheetPage.sheet',
  `x` smallint(4) NOT NULL COMMENT '起点X轴坐标',
  `y` smallint(4) NOT NULL COMMENT '起点Y轴坐标',
  `w` smallint(4) NOT NULL COMMENT '切片宽',
  `h` smallint(4) NOT NULL COMMENT '切片高',
  `roate` smallint(3) DEFAULT '0' COMMENT '旋转角度 -360--360，来源于ps_AnswerSheetPage.rote',
  `purpose` tinyint(1) DEFAULT '1' COMMENT '切片的用途 1-主观题;2-考号;3-客观题',
  `repeatable` tinyint(1) DEFAULT '1' COMMENT '切片是否可以重复使用',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_sheet_slices_sheet_slices_id` (`sheet_slices_id`) USING BTREE,
  KEY `x_ps_sheet_slices_answer_sheet_id` (`answer_sheet_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=476 DEFAULT CHARSET=utf8 COMMENT='阅卷-科目答题卡切片';

/*Table structure for table `ps_subject` */

DROP TABLE IF EXISTS `ps_subject`;

CREATE TABLE `ps_subject` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `subject_id` varchar(36) NOT NULL COMMENT '考试科目唯一标识',
  `exam_id` varchar(36) NOT NULL COMMENT '考试唯一标识',
  `target_subject_id` varchar(36) DEFAULT NULL COMMENT '科目唯一标识,外部系统统一编码',
  `target_subject_name` varchar(16) DEFAULT NULL COMMENT '科目唯一标识,外部系统统一编码',
  `creator_id` varchar(36) NOT NULL COMMENT '科目创建者标识',
  `name` varchar(128) NOT NULL COMMENT '科目名称',
  `sheets` tinyint(1) DEFAULT '1' COMMENT '答题卡类型数，1-单卡;2-AB卡',
  `score` double(6,2) DEFAULT NULL COMMENT '满分',
  `kg_score` double(6,2) DEFAULT NULL COMMENT '客观题分',
  `zg_score` double(6,2) DEFAULT NULL COMMENT '主观题分',
  `status` tinyint(2) DEFAULT '0' COMMENT '评卷进行状态，0-初始;1-扫描;2-评卷;9-完成;-1-作废',
  `mark_way` tinyint(1) DEFAULT '1' COMMENT '阅卷方式，1-网阅;2-机器阅卷;3-手阅 ',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `last_operator_id` varchar(36) DEFAULT NULL COMMENT '最后更新者唯一标识',
  `last_operator_name` varchar(64) DEFAULT NULL COMMENT '最后更新者姓名',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_subject_exam_id` (`exam_id`) USING BTREE,
  KEY `x_ps_subject_target_subject_id` (`target_subject_id`) USING BTREE,
  KEY `x_ps_subject_subject_id` (`subject_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='阅卷-考试科目';

/*Table structure for table `ps_subject_finished` */

DROP TABLE IF EXISTS `ps_subject_finished`;

CREATE TABLE `ps_subject_finished` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `exam_id` varchar(36) NOT NULL COMMENT '考试唯一标识',
  `subject_id` varchar(36) NOT NULL COMMENT '科目唯一标识',
  `exam_name` varchar(128) DEFAULT NULL COMMENT '考试名称',
  `subject_name` varchar(32) DEFAULT NULL COMMENT '考试科目名称',
  `registers` int(8) NOT NULL COMMENT '报名考生总数',
  `scans` int(8) NOT NULL COMMENT '扫描总数',
  `absent_examinees` int(8) NOT NULL COMMENT '缺卡总数',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_subject_finished_exam_id` (`exam_id`) USING BTREE,
  KEY `x_ps_subject_finished_subject_id` (`subject_id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='阅卷-考试科目完成表，考试科目状态等9时生成统计数据保存到本表';

/*Table structure for table `ps_subject_maked_list` */

DROP TABLE IF EXISTS `ps_subject_maked_list`;

CREATE TABLE `ps_subject_maked_list` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `exam_id` varchar(36) NOT NULL COMMENT '考试唯一标识',
  `target_subject_id` varchar(36) DEFAULT NULL COMMENT '科目唯一标识,外部系统统一编码',
  `target_subject_name` varchar(16) DEFAULT NULL COMMENT '科目名称,外部系统名称',
  `marker_person_id` varchar(36) DEFAULT NULL COMMENT '评卷人唯一标识，外部系统统一编码',
  `exam` varchar(128) DEFAULT NULL COMMENT '考试名称',
  `exam_date` datetime DEFAULT NULL COMMENT '考试时间',
  `grade` varchar(8) DEFAULT NULL COMMENT '年级',
  `years` varchar(16) DEFAULT NULL COMMENT '学年',
  `term` varchar(32) DEFAULT NULL COMMENT '学期',
  `subject` varchar(32) DEFAULT NULL COMMENT '科目名称',
  `marker` varchar(32) DEFAULT NULL COMMENT '评卷员姓名',
  `item` varchar(32) DEFAULT NULL COMMENT '评题名称',
  `item_score` double(6,2) DEFAULT NULL COMMENT '评题分数',
  `total` int(8) DEFAULT NULL COMMENT '完成数量',
  `arbits` int(8) DEFAULT NULL COMMENT '多评仲裁产生数量',
  `unables` int(8) DEFAULT NULL COMMENT '问题卷提交数量',
  `average` double(6,2) DEFAULT '-1.0' COMMENT '平均分',
  `median` double(6,2) DEFAULT '-1.0' COMMENT '中位分',
  `speed` double(6,2) DEFAULT '-1.0' COMMENT '评卷速率',
  `marked` text COMMENT '评分轨迹,以;分隔,问题卷以#表示',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_subject_maked_list_exam_id` (`exam_id`) USING BTREE,
  KEY `x_ps_subject_maked_list_target_subject_id` (`target_subject_id`) USING BTREE,
  KEY `x_ps_subject_maked_list_marker_person_id` (`marker_person_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='阅卷-评卷人科目可评分评题清单';

/*Table structure for table `ps_subject_manager` */

DROP TABLE IF EXISTS `ps_subject_manager`;

CREATE TABLE `ps_subject_manager` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `exam_id` varchar(36) NOT NULL COMMENT '考试唯一标识',
  `subject_id` varchar(36) NOT NULL COMMENT '考试科目唯一标识',
  `person_id` varchar(36) NOT NULL COMMENT '负责唯一标识,源于外部系统',
  `name` varchar(128) NOT NULL COMMENT '负责人姓名',
  `role` tinyint(2) DEFAULT '1' COMMENT '管理角色：1-管理员;2-评卷人员分配者;3-主客观题参考答案录入者;4-主客观题参考答案审核者;5-答题卡设计者;6-扫描异常处理者',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_subject_manager_exam_id` (`exam_id`) USING BTREE,
  KEY `x_ps_subject_manager_subject_id` (`subject_id`) USING BTREE,
  KEY `x_ps_subject_manager_person_id` (`person_id`) USING BTREE,
  KEY `x_ps_subject_manager_role` (`role`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='阅卷-科目管理类人员';

/*Table structure for table `ps_subject_mark_statistics` */

DROP TABLE IF EXISTS `ps_subject_mark_statistics`;

CREATE TABLE `ps_subject_mark_statistics` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `subject_id` varchar(36) NOT NULL COMMENT '科目唯一标识',
  `exam_id` varchar(36) NOT NULL COMMENT '考试唯一标识',
  `stats_method` tinyint(2) NOT NULL COMMENT '统计指标方法，定义在dict表中',
  `stats_method_name` tinyint(2) NOT NULL COMMENT '统计指标方法名称，如待评量，完成数量，平均分等',
  `stats_value` double(6,2) DEFAULT NULL COMMENT '统计指标值',
  `stats_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '统计时间',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_subject_mark_statistics_subject_id` (`subject_id`) USING BTREE,
  KEY `x_ps_subject_mark_statistics_exam_id` (`exam_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='阅卷-科目评卷统计';

/*Table structure for table `ps_subject_paper_list` */

DROP TABLE IF EXISTS `ps_subject_paper_list`;

CREATE TABLE `ps_subject_paper_list` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `exam_id` varchar(36) NOT NULL COMMENT '考试唯一标识',
  `target_subject_id` varchar(36) DEFAULT NULL COMMENT '科目唯一标识,外部系统统一编码',
  `target_subject_name` varchar(16) DEFAULT NULL COMMENT '科目名称,外部系统名称',
  `exam` varchar(128) DEFAULT NULL COMMENT '考试名称',
  `exam_date` datetime DEFAULT NULL COMMENT '考试时间',
  `grade` varchar(8) DEFAULT NULL COMMENT '年级',
  `years` varchar(16) DEFAULT NULL COMMENT '学年',
  `term` varchar(32) DEFAULT NULL COMMENT '学期',
  `subject` varchar(32) DEFAULT NULL COMMENT '科目名称',
  `score` double(6,2) DEFAULT NULL COMMENT '科目满分',
  `examinees` int(8) DEFAULT NULL COMMENT '有效考生人数',
  `average` double(6,2) DEFAULT '-1.0' COMMENT '考生平均分',
  `median` double(6,2) DEFAULT '-1.0' COMMENT '考生中位分',
  `kg_score` double(6,2) DEFAULT NULL COMMENT '客观题分数',
  `zg_score` double(6,2) DEFAULT NULL COMMENT '主观题分数',
  `papers` varchar(512) DEFAULT NULL COMMENT '所用试卷绝对地址',
  `sheets` varchar(512) DEFAULT NULL COMMENT '所用答题卡图片绝对地址',
  `scores` text COMMENT '有效考生的科目得分,以;分隔',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_subject_paper_list_exam_id` (`exam_id`) USING BTREE,
  KEY `x_ps_subject_paper_list_target_subject_id` (`target_subject_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='阅卷-科目评卷清单';

/*Table structure for table `ps_subject_progress` */

DROP TABLE IF EXISTS `ps_subject_progress`;

CREATE TABLE `ps_subject_progress` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `exam_id` varchar(36) NOT NULL COMMENT '考试唯一标识',
  `subject_id` varchar(36) NOT NULL COMMENT '考试科目唯一标识',
  `name` varchar(128) NOT NULL COMMENT '进程名称',
  `status` tinyint(1) DEFAULT '0' COMMENT '行进状态，0-未开始;1-正在进行;9-完成;',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_subject_exam_id` (`exam_id`) USING BTREE,
  KEY `x_ps_subject_subject_id` (`subject_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='阅卷-考试科目工作进程，科目创建时，通过考试类型及所选择的配置生成数据，在阅卷过程过更新数据';

/*Table structure for table `ps_subject_rigister` */

DROP TABLE IF EXISTS `ps_subject_rigister`;

CREATE TABLE `ps_subject_rigister` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `exam_id` varchar(36) NOT NULL COMMENT '报考考试唯一标识',
  `examinee_id` varchar(36) NOT NULL COMMENT '考生唯一标识',
  `subject_id` varchar(36) NOT NULL COMMENT '报考科目唯一标识',
  `clazz_id` VARCHAR(36)  COMMENT '考生所在教学班级唯一标识；源于ps_TestedOrg.targetOrgId,非走班制为行政班',  
  `clazz_name` VARCHAR(8)  COMMENT '考生所在教学班级名称,非走班制为行政班',    
  `attended` tinyint(1) DEFAULT '2' COMMENT '参与标志：1-参考(YES);2-缺卡(None);3-缺考(No);4-作弊(Cogged),默认缺卡,扫描及考号异常处理时更新',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `last_operator_id` varchar(36) DEFAULT NULL COMMENT '最后更新者唯一标识',
  `last_operator_name` varchar(64) DEFAULT NULL COMMENT '最后更新者姓名',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_subject_rigister_exam_id` (`exam_id`) USING BTREE,
  KEY `x_ps_subject_rigister_subject_id` (`subject_id`) USING BTREE,
  KEY `x_ps_subject_rigister_examinee_id` (`examinee_id`) USING BTREE,
  KEY `x_ps_subject_rigister_attended` (`attended`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='阅卷-考生报考科目';

/*Table structure for table `ps_subject_score_statistics` */

DROP TABLE IF EXISTS `ps_subject_score_statistics`;

CREATE TABLE `ps_subject_score_statistics` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `subject_id` varchar(36) NOT NULL COMMENT '科目唯一标识',
  `exam_id` varchar(36) NOT NULL COMMENT '考试唯一标识',
  `stats_method` tinyint(2) NOT NULL COMMENT '统计指标方法，定义在dict表中',
  `stats_method_name` tinyint(2) NOT NULL COMMENT '统计指标方法名称，如客观题高分主题题低分，客观题空，客观题零分，主题零分',
  `exam_number` varchar(32) NOT NULL COMMENT '考号',
  `kgs` text COMMENT '客观题选项及得分',
  `zgs` text COMMENT '主观题及得分',
  `sheets` text COMMENT '答题卡图片URL',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_subject_score_statistics_subject_id` (`subject_id`) USING BTREE,
  KEY `x_ps_subject_score_statistics_exam_id` (`exam_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='阅卷-科目怀疑异常得分统计';

/*Table structure for table `ps_subject_total_score_list` */

DROP TABLE IF EXISTS `ps_subject_total_score_list`;

CREATE TABLE `ps_subject_total_score_list` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `exam_id` varchar(36) NOT NULL COMMENT '考试唯一标识',
  `subject_id` varchar(36) NOT NULL COMMENT '科目唯一标识',
  `school_id` varchar(36) DEFAULT NULL COMMENT '考生所在学校唯一标识',
  `clazz_id` varchar(36) DEFAULT NULL COMMENT '考生所在班级唯一标识',
  `examinee_person_id` varchar(36) DEFAULT NULL COMMENT '考生唯一标识',
  `exam` varchar(128) DEFAULT NULL COMMENT '考试名称',
  `exam_date` datetime DEFAULT NULL COMMENT '考试时间',
  `school` varchar(128) DEFAULT NULL COMMENT '学校',
  `grade` varchar(8) DEFAULT NULL COMMENT '年级',
  `clazz` varchar(32) DEFAULT NULL COMMENT '班级',
  `years` varchar(16) DEFAULT NULL COMMENT '学年',
  `term` varchar(32) DEFAULT NULL COMMENT '学期',
  `subject` varchar(32) DEFAULT NULL COMMENT '科目名称',
  `crypt_code` varchar(32) DEFAULT NULL COMMENT '考生姓名',
  `examinee` varchar(32) DEFAULT NULL COMMENT '考生姓名',
  `exam_number` varchar(32) DEFAULT NULL COMMENT '考号',
  `attended` varchar(16) DEFAULT '正常' COMMENT '参与：正常;缺卡;缺考;作弊',
  `score` double(6,2) DEFAULT '-1.0' COMMENT '得分',
  `kg_score` double(6,2) DEFAULT '-1.0' COMMENT '主观题得分',
  `zg_score` double(6,2) DEFAULT '-1.0' COMMENT '客观题得分',
  `sheets` text COMMENT '答题卡图片地址绝对地址多图时以;分隔',
  `marked` text COMMENT '评分轨迹,默认JSON数据结构',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_subject_total_score_list_exam_id` (`exam_id`) USING BTREE,
  KEY `x_ps_subject_total_score_list_subject_id` (`subject_id`) USING BTREE,
  KEY `x_ps_subject_total_score_list_school_id` (`school_id`) USING BTREE,
  KEY `x_ps_subject_total_score_list_clazz_id` (`clazz_id`) USING BTREE,
  KEY `x_ps_subject_total_score_list_examinee_person_id` (`examinee_person_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='阅卷-科目总分成绩单';

/*Table structure for table `ps_subject_total_score_list_split` */

DROP TABLE IF EXISTS `ps_subject_total_score_list_split`;

CREATE TABLE `ps_subject_total_score_list_split` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `exam_id` varchar(36) NOT NULL COMMENT '考试唯一标识',
  `subject_id` varchar(36) NOT NULL COMMENT '科目唯一标识',
  `target_subject_id` varchar(36) DEFAULT NULL COMMENT '科目唯一标识,外部系统统一编码',
  `school_id` varchar(36) DEFAULT NULL COMMENT '考生所在学校唯一标识',
  `clazz_id` varchar(36) DEFAULT NULL COMMENT '考生所在班级唯一标识',
  `examinee_person_id` varchar(36) DEFAULT NULL COMMENT '考生唯一标识',
  `exam` varchar(128) DEFAULT NULL COMMENT '考试名称',
  `exam_date` datetime DEFAULT NULL COMMENT '考试时间',
  `school` varchar(128) DEFAULT NULL COMMENT '学校',
  `grade` varchar(8) DEFAULT NULL COMMENT '年级',
  `clazz` varchar(32) DEFAULT NULL COMMENT '班级',
  `years` varchar(16) DEFAULT NULL COMMENT '学年',
  `term` varchar(32) DEFAULT NULL COMMENT '学期',
  `subject` varchar(32) DEFAULT NULL COMMENT '科目名称',
  `target_subject_name` varchar(16) DEFAULT NULL COMMENT '拆分后的科目名称',
  `crypt_code` varchar(32) DEFAULT NULL COMMENT '考生姓名',
  `examinee` varchar(32) DEFAULT NULL COMMENT '考生姓名',
  `exam_number` varchar(32) DEFAULT NULL COMMENT '考号',
  `attended` varchar(16) DEFAULT '正常' COMMENT '参与：正常;缺卡;缺考;作弊',
  `score` double(6,2) DEFAULT '-1.0' COMMENT '得分',
  `kg_score` double(6,2) DEFAULT '-1.0' COMMENT '主观题得分',
  `zg_score` double(6,2) DEFAULT '-1.0' COMMENT '客观题得分',
  `sheets` text COMMENT '答题卡图片地址绝对地址多图时以;分隔',
  `marked` text COMMENT '评分轨迹,默认JSON数据结构',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_subject_total_score_list_split_exam_id` (`exam_id`) USING BTREE,
  KEY `x_ps_subject_total_score_list_split_subject_id` (`subject_id`) USING BTREE,
  KEY `x_ps_subject_total_score_list_split_target_subject_id` (`target_subject_id`) USING BTREE,
  KEY `x_ps_subject_total_score_list_split_school_id` (`school_id`) USING BTREE,
  KEY `x_ps_subject_total_score_list_split_clazz_id` (`clazz_id`) USING BTREE,
  KEY `x_ps_subject_total_score_list_split_examinee_person_id` (`examinee_person_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='阅卷-科目总分成绩单拆分，综合科目时用';

/*Table structure for table `ps_subject_total_score_list_trace` */

DROP TABLE IF EXISTS `ps_subject_total_score_list_trace`;

CREATE TABLE `ps_subject_total_score_list_trace` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `exam_id` varchar(36) NOT NULL COMMENT '考试唯一标识',
  `subject_id` varchar(36) NOT NULL COMMENT '科目唯一标识,外部系统统一编码',
  `marked` text COMMENT '原有评分轨迹,默认JSON数据结构',
  `marked_new` text COMMENT '更新后的评分轨迹,默认JSON数据结构',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `last_operator_id` varchar(36) DEFAULT NULL COMMENT '最后更新者唯一标识',
  `last_operator_name` varchar(64) DEFAULT NULL COMMENT '最后更新者姓名',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_subject_total_score_list_trace_exam_id` (`exam_id`) USING BTREE,
  KEY `x_ps_subject_total_score_list_trace_subject_id` (`subject_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='阅卷-科目总分成绩单变更记录';

/*Table structure for table `ps_tested_org` */

DROP TABLE IF EXISTS `ps_tested_org`;

CREATE TABLE `ps_tested_org` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `exam_id` varchar(36) NOT NULL COMMENT '考试唯一标识',
  `target_org_id` varchar(36) NOT NULL COMMENT '机构唯一标识,源于外部系统',
  `parent_org_id` varchar(36) DEFAULT NULL COMMENT '参考试上级机构标识',
  `name` varchar(128) DEFAULT NULL COMMENT '参考试机构名称',
  `code` varchar(32) DEFAULT NULL COMMENT '参考试机构代码',
  `catagory` tinyint(1) DEFAULT NULL COMMENT '参考试机构分类：1-班级;2-学校',
  `org_seq` tinyint(3) DEFAULT NULL COMMENT '机构序号',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `last_operator_id` varchar(36) DEFAULT NULL COMMENT '最后更新者唯一标识',
  `last_operator_name` varchar(64) DEFAULT NULL COMMENT '最后更新者姓名',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_tested_org_exam_id` (`exam_id`) USING BTREE,
  KEY `x_ps_tested_org_target_org_id` (`target_org_id`) USING BTREE,
  KEY `x_ps_tested_org_parent_org_id` (`parent_org_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='阅卷-参加考试的机构;校考是班级;联考统考是学校';

/*Table structure for table `ps_zg_optional_mark` */

DROP TABLE IF EXISTS `ps_zg_optional_mark`;

CREATE TABLE `ps_zg_optional_mark` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mark_id` varchar(36) NOT NULL COMMENT '选做题唯一标识',
  `exam_id` varchar(36) NOT NULL COMMENT '考试唯一标识',
  `subject_id` varchar(36) NOT NULL COMMENT '科目唯一标识',
  `batch_id` varchar(36) DEFAULT NULL COMMENT '扫描批次唯一标识',
  `mark_item_id` varchar(36) NOT NULL COMMENT '评题唯一标识',
  `examinee_item_id` varchar(36) NOT NULL COMMENT '考生评题唯一标识',
  `required` tinyint(2) DEFAULT NULL COMMENT '必须完成的评阅次数',
  `times` tinyint(2) DEFAULT NULL COMMENT '已完成的评阅次数',
  `fetch_seq` int(8) DEFAULT NULL COMMENT '提取顺序号，每个考试科目都重新排序',
  `arbiter` tinyint(1) DEFAULT NULL COMMENT '是否需要仲裁，0-不需要;1-需要',
  `fetchsign` int(8) DEFAULT NULL COMMENT '正评提取签名',
  `crypt_code` varchar(32) NOT NULL COMMENT '密号',
  `optional` varchar(32) DEFAULT NULL COMMENT '选做题选项，多组时以;，无选项以＃代替',
  `doubt` tinyint(1) DEFAULT '0' COMMENT '机评时怀疑卷分类：0-无怀疑;1-有怀疑',
  `doubt_done` tinyint(1) DEFAULT '0' COMMENT '机评时怀疑卷处理标志：1-已经处理;0-未处理',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_zg_optional_mark_mark_id` (`mark_id`) USING BTREE,
  KEY `x_ps_zg_optional_mark_exam_id` (`exam_id`) USING BTREE,
  KEY `x_ps_zg_optional_mark_subject_id` (`subject_id`) USING BTREE,
  KEY `x_ps_zg_optional_mark_mark_item_id` (`mark_item_id`) USING BTREE,
  KEY `x_ps_zg_optional_mark_crypt_code` (`crypt_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='阅卷-客观题选做分题评题';

/*Table structure for table `ps_zg_optional_mark_fetcher` */

DROP TABLE IF EXISTS `ps_zg_optional_mark_fetcher`;

CREATE TABLE `ps_zg_optional_mark_fetcher` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `handler_id` varchar(36) NOT NULL COMMENT '考号处理唯一标识',
  `mark_id` varchar(36) NOT NULL COMMENT '考号题唯一标识',
  `mark_item_id` varchar(36) NOT NULL COMMENT '评题唯一标识',
  `marker_id` varchar(36) NOT NULL COMMENT '评卷员唯一标识，markType=Mechine时值为：Mechine',
  `mark_type` tinyint(1) NOT NULL COMMENT '评卷类类型标识：1-识别(Mechine);2-后台手工处理(Hand);',
  `fetch_times` tinyint(2) DEFAULT NULL COMMENT '取出时的评次',
  `fetch_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '提取时间',
  `submit_time` timestamp NULL DEFAULT NULL COMMENT '提交时间',
  `submit_times` tinyint(2) DEFAULT NULL COMMENT '提交时的评次',
  `optional` varchar(32) DEFAULT NULL COMMENT '选做题选项，多组时以;，无选项以＃代替',
  `marker_seq` int(8) DEFAULT NULL COMMENT '评卷人的评卷顺序号',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_zg_optional_mark_fetcher_handler_id` (`handler_id`) USING BTREE,
  KEY `x_ps_zg_optional_mark_fetcher_mark_id` (`mark_id`) USING BTREE,
  KEY `x_ps_zg_optional_mark_fetcher_marker_id` (`marker_id`) USING BTREE,
  KEY `x_ps_zg_optional_mark_fetcher_mark_type` (`mark_type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='阅卷-选做分题评题提取表';

/*Table structure for table `ps_zg_optional_mark_handler` */

DROP TABLE IF EXISTS `ps_zg_optional_mark_handler`;

CREATE TABLE `ps_zg_optional_mark_handler` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `handler_id` varchar(36) NOT NULL COMMENT '考号处理唯一标识',
  `mark_id` varchar(36) NOT NULL COMMENT '考号题唯一标识',
  `mark_item_id` varchar(36) NOT NULL COMMENT '评题唯一标识',
  `marker_id` varchar(36) NOT NULL COMMENT '评卷员唯一标识，markType=Mechine时值为：Mechine',
  `mark_type` tinyint(1) NOT NULL COMMENT '评卷类类型标识：1-识别(Mechine);2-后台手工处理(Hand);',
  `fetch_times` tinyint(2) DEFAULT NULL COMMENT '取出时的评次',
  `fetch_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '提取时间',
  `submit_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '提交时间',
  `submit_times` tinyint(2) DEFAULT NULL COMMENT '提交时的评次',
  `optional` varchar(32) DEFAULT NULL COMMENT '选做题选项，多组时以;，无选项以＃代替',
  `marker_seq` int(8) DEFAULT NULL COMMENT '评卷人的评卷顺序号',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除.0-否;1-是',
  PRIMARY KEY (`id`),
  KEY `x_ps_zg_optional_mark_handler_handler_id` (`handler_id`) USING BTREE,
  KEY `x_ps_zg_optional_mark_handler_mark_id` (`mark_id`) USING BTREE,
  KEY `x_ps_zg_optional_mark_handler_marker_id` (`marker_id`) USING BTREE,
  KEY `x_ps_zg_optional_mark_handler_mark_type` (`mark_type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='阅卷-客观题选做分题评题处理';

/*Table structure for table `ps_zg_score` */

DROP TABLE IF EXISTS `ps_zg_score`;

CREATE TABLE `ps_zg_score` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `exam_id` varchar(36) NOT NULL,
  `subject_id` varchar(36) NOT NULL,
  `crypt_code` varchar(32) NOT NULL COMMENT '密号',
  `score` double(6,2) NOT NULL COMMENT '主观题总分',
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
