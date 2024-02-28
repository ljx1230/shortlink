package edu.ustc.shortlink.admin;

/**
 * @Author: ljx
 * @Date: 2024/2/7 17:42
 */
public class Test01 {
    private static final String SQL = "CREATE TABLE `t_link_goto_%d` (\n" +
            "\t`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',\n" +
            "\t`gid` VARCHAR(32) DEFAULT 'default' COMMENT '分组标识',\n" +
            "\t`full_short_url` VARCHAR(128) DEFAULT NULL COMMENT '完整短链接',\n" +
            "\tPRIMARY KEY (`id`)\n" +
            ") ENGINE=INNODB DEFAULT CHARSET=utf8mb4;";
    public static void main(String[] args) {
        for(int i = 0;i < 16 ;i++) {
            System.out.printf((SQL) + "%n",i);
        }
    }
}
