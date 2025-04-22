/*
 Navicat Premium Data Transfer

 Source Server         : MariaDB
 Source Server Type    : MariaDB
 Source Server Version : 110402
 Source Host           : localhost:3306
 Source Schema         : db_ffchain_pos

 Target Server Type    : MariaDB
 Target Server Version : 110402
 File Encoding         : 65001

 Date: 19/04/2025 09:07:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for items
-- ----------------------------
DROP TABLE IF EXISTS `items`;
CREATE TABLE `items`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_no` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `item_name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `item_description` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `price` double(11, 2) NOT NULL,
  `unit` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `status` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of items
-- ----------------------------
INSERT INTO `items` VALUES (20, 'ITM-1020', 'Chicken or Banana', 'Dance', 145.00, 'servings', 'active');
INSERT INTO `items` VALUES (21, 'ITM-1021', 'Rice Kalo-Kalo', 'Kalo-kalo lang', 50.00, 'serving', 'active');
INSERT INTO `items` VALUES (22, 'ITM-1024', 'Chicken or Banana', 'Sayaw Yan', 95.00, 'servings', 'active');
INSERT INTO `items` VALUES (23, 'ITM-1025', 'Banana Chips', 'Dried and Fried', 15.00, 'pack', 'inactive');
INSERT INTO `items` VALUES (24, 'ITM-1026', 'Apple Pen', 'Apple Pie', 45.00, 'pcs', 'active');
INSERT INTO `items` VALUES (25, 'ITM-1027', 'Crispy Chicke', 'Crispy King', 40.00, 'pcs', 'inactive');

-- ----------------------------
-- Table structure for operations
-- ----------------------------
DROP TABLE IF EXISTS `operations`;
CREATE TABLE `operations`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operationNumber` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `date` datetime(0) NOT NULL,
  `userNumber` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `openAt` datetime(0) NULL DEFAULT NULL,
  `closeAt` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of operations
-- ----------------------------
INSERT INTO `operations` VALUES (12, 'CSH-1009', '2025-04-18 08:36:23', NULL, '2025-04-18 08:36:23', '2025-04-18 10:38:46');
INSERT INTO `operations` VALUES (13, 'CSH-1010', '2025-04-18 10:44:43', NULL, '2025-04-18 10:46:52', '2025-04-18 10:48:46');
INSERT INTO `operations` VALUES (14, 'CSH-1011', '2025-04-18 10:50:50', NULL, '2025-04-18 14:28:52', '2025-04-18 14:29:02');
INSERT INTO `operations` VALUES (15, 'CSH-1012', '2025-04-18 14:29:15', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for order_items
-- ----------------------------
DROP TABLE IF EXISTS `order_items`;
CREATE TABLE `order_items`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `item_no` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `quantity` int(11) NOT NULL,
  `item_total` double(11, 2) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_items
-- ----------------------------
INSERT INTO `order_items` VALUES (12, 'ORD-5', 'ITM-1003', 5, 600.00);
INSERT INTO `order_items` VALUES (13, 'ORD-5', 'ITM-1004', 3, 1081.50);
INSERT INTO `order_items` VALUES (14, 'ORD-5', 'ITM-1005', 1, 450.00);
INSERT INTO `order_items` VALUES (15, 'ORD-6', 'ITM-1003', 5, 600.00);
INSERT INTO `order_items` VALUES (16, 'ORD-6', 'ITM-1004', 3, 1081.50);
INSERT INTO `order_items` VALUES (17, 'ORD-6', 'ITM-1005', 1, 450.00);
INSERT INTO `order_items` VALUES (18, 'ORD-7', 'ITM-1003', 5, 600.00);
INSERT INTO `order_items` VALUES (19, 'ORD-7', 'ITM-1004', 3, 1081.50);
INSERT INTO `order_items` VALUES (20, 'ORD-7', 'ITM-1005', 1, 450.00);
INSERT INTO `order_items` VALUES (21, 'ORD-8', 'ITM-1003', 5, 600.00);
INSERT INTO `order_items` VALUES (22, 'ORD-8', 'ITM-1004', 3, 1081.50);
INSERT INTO `order_items` VALUES (23, 'ORD-8', 'ITM-1005', 1, 450.00);
INSERT INTO `order_items` VALUES (24, 'ORD-9', 'ITM-1020', 5, 725.00);
INSERT INTO `order_items` VALUES (25, 'ORD-9', 'ITM-1021', 3, 150.00);
INSERT INTO `order_items` VALUES (26, 'ORD-9', 'ITM-1024', 1, 95.00);
INSERT INTO `order_items` VALUES (27, 'ORD-10', 'ITM-1020', 2, 290.00);
INSERT INTO `order_items` VALUES (28, 'ORD-10', 'ITM-1021', 1, 50.00);
INSERT INTO `order_items` VALUES (29, 'ORD-10', 'ITM-1024', 1, 95.00);
INSERT INTO `order_items` VALUES (30, 'ORD-11', 'ITM-1020', 2, 290.00);
INSERT INTO `order_items` VALUES (31, 'ORD-11', 'ITM-1021', 1, 50.00);
INSERT INTO `order_items` VALUES (32, 'ORD-11', 'ITM-1024', 1, 95.00);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `cashering_no` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `placeAt` datetime(0) NOT NULL,
  `status` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (10, 'ORD-9', 'CSH-1001', '2025-04-17 16:12:56', 'created');
INSERT INTO `orders` VALUES (11, 'ORD-10', 'CSH-1001', '2025-04-17 16:14:27', 'created');
INSERT INTO `orders` VALUES (12, 'ORD-11', 'CSH-1001', '2025-04-17 16:46:06', 'created');

-- ----------------------------
-- Table structure for payments
-- ----------------------------
DROP TABLE IF EXISTS `payments`;
CREATE TABLE `payments`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `payment_number` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `order_number` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `total` double(11, 0) NOT NULL,
  `createdAt` datetime(0) NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `voidedAt` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of payments
-- ----------------------------
INSERT INTO `payments` VALUES (1, 'PMT-1012', 'ORD-1011', 406, '2025-04-14 15:53:12', 'paid', NULL);
INSERT INTO `payments` VALUES (2, 'PMT-1013', 'ORD-1012', 406, '2025-04-14 15:55:19', 'paid', NULL);
INSERT INTO `payments` VALUES (3, 'PMT-1014', 'ORD-1013', 406, '2025-04-14 16:04:09', 'paid', NULL);

-- ----------------------------
-- Table structure for setup
-- ----------------------------
DROP TABLE IF EXISTS `setup`;
CREATE TABLE `setup`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `identication_name` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `identification_prefix` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `starting_number` int(11) NOT NULL,
  `current_number` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of setup
-- ----------------------------
INSERT INTO `setup` VALUES (1, 'PAYMENT', 'PMT', 1000, 1014);
INSERT INTO `setup` VALUES (2, 'USER', 'USR', 1, 3);
INSERT INTO `setup` VALUES (3, 'ORDER', 'ORD', 1, 11);
INSERT INTO `setup` VALUES (4, 'ITEM', 'ITM', 1000, 1027);
INSERT INTO `setup` VALUES (5, 'CASHERING', 'CSH', 1000, 1012);

-- ----------------------------
-- Table structure for stocks
-- ----------------------------
DROP TABLE IF EXISTS `stocks`;
CREATE TABLE `stocks`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cashering_no` varchar(25) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `item_no` varchar(25) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `quantity` int(11) NULL DEFAULT NULL,
  `items_sold` int(11) NULL DEFAULT NULL,
  `items_remove` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stocks
-- ----------------------------
INSERT INTO `stocks` VALUES (1, 'CSH-1009', 'ITM-1020', 20, NULL, NULL);
INSERT INTO `stocks` VALUES (3, 'CSH-1011', 'ITM-1021', 20, NULL, NULL);
INSERT INTO `stocks` VALUES (4, 'CSH-1011', 'ITM-1024', 10, NULL, NULL);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_no` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `first_name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `last_name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `middle_name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `user_name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `password` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `role` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL DEFAULT 'cashier',
  PRIMARY KEY (`id`, `user_no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'USR-1', 'jon', 'val', 'go', 'jon', '12345', 'cashier');
INSERT INTO `users` VALUES (2, 'USR-2', 'mary', 'public', 'hey', 'mary', '12345', 'cashier');
INSERT INTO `users` VALUES (3, 'USR-3', 'john', 'doe', 'goat', 'john', '12345', 'cashier');

SET FOREIGN_KEY_CHECKS = 1;
