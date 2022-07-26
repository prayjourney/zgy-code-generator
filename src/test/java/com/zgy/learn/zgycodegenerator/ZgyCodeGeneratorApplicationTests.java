package com.zgy.learn.zgycodegenerator;

import com.zgy.learn.zgycodegenerator.constatnt.MySQLTypeEnum;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ZgyCodeGeneratorApplicationTests {

    @Test
    void getJavaTypeByMysqlType01() {
        System.out.println(MySQLTypeEnum.values());
        System.out.println(MySQLTypeEnum.BIGINT.getMysqlType());
    }

    @Test
    void getJavaTypeByMysqlType02() {
        System.out.println(MySQLTypeEnum.getJavaTypeByMysqlType("int"));
        System.out.println(MySQLTypeEnum.getJavaTypeByMysqlType("hello"));
    }

}
