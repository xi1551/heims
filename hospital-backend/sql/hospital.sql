-- =============================================
-- 医院员工信息管理系统 - 数据库初始化脚本
-- =============================================

-- 创建数据库
CREATE DATABASE IF NOT EXISTS hospital_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE hospital_db;

-- 临时禁用外键检查，避免删除顺序问题
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- 用户表
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `username` VARCHAR(50) NOT NULL UNIQUE,
    `password` VARCHAR(100) NOT NULL,
    `role` VARCHAR(20) DEFAULT 'user',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- 科室表
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(100) NOT NULL,
    `phone` VARCHAR(20),
    `location` VARCHAR(200)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- 医生表
-- ----------------------------
DROP TABLE IF EXISTS `doctor`;
CREATE TABLE `doctor` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `job_number` VARCHAR(50) NOT NULL UNIQUE,
    `name` VARCHAR(50) NOT NULL,
    `gender` VARCHAR(10),
    `title` VARCHAR(50),
    `department_id` BIGINT,
    `phone` VARCHAR(20),
    FOREIGN KEY (`department_id`) REFERENCES `department`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- 护士表
-- ----------------------------
DROP TABLE IF EXISTS `nurse`;
CREATE TABLE `nurse` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `job_number` VARCHAR(50) NOT NULL UNIQUE,
    `name` VARCHAR(50) NOT NULL,
    `gender` VARCHAR(10),
    `title` VARCHAR(50),
    `department_id` BIGINT,
    `phone` VARCHAR(20),
    FOREIGN KEY (`department_id`) REFERENCES `department`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 恢复外键检查
SET FOREIGN_KEY_CHECKS = 1;

-- =============================================
-- 插入测试数据
-- =============================================

-- 插入管理员用户 (密码: admin123)
INSERT INTO `user` (`username`, `password`, `role`) VALUES
('admin', 'admin123', 'admin');

-- 插入科室数据
INSERT INTO `department` (`name`, `phone`, `location`) VALUES
('内科',   '010-88001001', '门诊楼3层'),
('外科',   '010-88001002', '门诊楼4层'),
('儿科',   '010-88001003', '门诊楼2层'),
('妇产科', '010-88001004', '住院楼5层'),
('急诊科', '010-88001005', '急诊楼1层');

-- 插入医生数据
INSERT INTO `doctor` (`job_number`, `name`, `gender`, `title`, `department_id`, `phone`) VALUES
('D20240001', '张伟',   '男', '主任医师',   1, '13800001001'),
('D20240002', '李芳',   '女', '副主任医师', 1, '13800001002'),
('D20240003', '王强',   '男', '主治医师',   1, '13800001003'),
('D20240004', '刘洋',   '男', '主任医师',   2, '13800001004'),
('D20240005', '陈静',   '女', '副主任医师', 2, '13800001005'),
('D20240006', '赵磊',   '男', '主治医师',   2, '13800001006'),
('D20240007', '孙丽',   '女', '主任医师',   3, '13800001007'),
('D20240008', '周明',   '男', '副主任医师', 3, '13800001008'),
('D20240009', '吴敏',   '女', '主任医师',   4, '13800001009'),
('D20240010', '郑浩',   '男', '主治医师',   5, '13800001010');

-- 插入护士数据
INSERT INTO `nurse` (`job_number`, `name`, `gender`, `title`, `department_id`, `phone`) VALUES
('N20240001', '王丽华', '女', '主管护师', 1, '13900001001'),
('N20240002', '李秀英', '女', '护师',     1, '13900001002'),
('N20240003', '张美玲', '女', '主管护师', 2, '13900001003'),
('N20240004', '刘晓红', '女', '护师',     2, '13900001004'),
('N20240005', '陈玉兰', '女', '护士长',   3, '13900001005'),
('N20240006', '杨春梅', '女', '护师',     3, '13900001006'),
('N20240007', '赵雪莲', '女', '主管护师', 4, '13900001007'),
('N20240008', '黄丽萍', '女', '护师',     4, '13900001008'),
('N20240009', '周小燕', '女', '护士长',   5, '13900001009'),
('N20240010', '吴雅琴', '女', '护师',     5, '13900001010');
