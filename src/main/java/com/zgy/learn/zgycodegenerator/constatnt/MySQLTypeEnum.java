package com.zgy.learn.zgycodegenerator.constatnt;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zgy
 * @date 2022/7/26
 */
@Slf4j
@Getter
public enum MySQLTypeEnum {
    /* number */
    BIT("bit", "Boolean"),
    TINYINT("tinyint", "Integer"),
    SMALLINT("smallint", "Integer"),
    MEDIUMINT("mediumint", "Integer"),
    INT("int", "Integer"),
    INTEGER("integer", "Integer"),
    BIGINT("bigint", "Long"),
    FLOAT("float", "Float"),
    REAL("real", "Double"),
    DOUBLE("double", "Double"),
    NUMERIC("numeric", "Double"),
    DECIMAL("decimal", "BigDecimal"),

    /* string */
    CHAR("char", "String"),
    VARCHAR("varchar", "String"),
    TEXT("text", "String"),
    TINYTEXT("tinytext", "String"),
    MEDIUMTEXT("mediumtext", "String"),
    LONGTEXT("longtext", "String"),
    // 下面三种都转换成String
    JSON("json", "String"),
    SET("set", "String"),
    ENUM("enum", "String"),

    /* time */
    TIME("time", "String"),
    TIMESTAMP("timestamp", "Date"),
    YEAR("year", "String"),
    DATE("date", "Date"),
    DATETIME("datetime", "Date"),

    /* binary */
    BINARY("binary", "Byte[]"),
    VARBINARY("varbinary", "Byte[]"),

    /* blob */
    BLOB("blob", "Byte[]"),
    TINYBLOB("tinyblob", "Byte[]"),
    MEDIUMBLOB("mediumblob", "Byte[]"),
    LONGBLOB("longblob", "Byte[]");

    // linestring, multilinestring, point, multipoint, polygon, multipolygon, geometry, geometrycollection

    private String mysqlType;
    private String javaType;

    public static String getJavaTypeByMysqlType(String mysqlType) {
        MySQLTypeEnum[] values = MySQLTypeEnum.values();
        List<MySQLTypeEnum> mySQLTypeEnums = Arrays.asList(values);
        List<String> mySQLTypeEnumsList = mySQLTypeEnums.stream().map(MySQLTypeEnum::getMysqlType).collect(Collectors.toList());
        if (!mySQLTypeEnumsList.contains(mysqlType)) {
            log.warn("{}转换为String类型, 请注意", mysqlType);
            return "String";
        }
        return MySQLTypeEnum.valueOf(mysqlType.toUpperCase()).getJavaType();
    }

    MySQLTypeEnum(String mysqlType, String javaType) {
        this.mysqlType = mysqlType;
        this.javaType = javaType;
    }

}
