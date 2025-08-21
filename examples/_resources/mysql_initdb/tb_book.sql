SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                         `author` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'Unknown',
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

INSERT INTO `book` VALUES (1, 'Spring Boot 入门', '张三');
INSERT INTO `book` VALUES (2, 'Vue.js 快速上手', '李四');
INSERT INTO `book` VALUES (3, 'Redis 实战', '王五');

SET FOREIGN_KEY_CHECKS = 1;
