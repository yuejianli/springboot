package top.yueshushu.learn.mp.enumtype;

import lombok.Getter;

/**
 * @ClassName:DataBaseTypeEnum
 * @Description 数据库类型信息
 * @Author zk_yjl
 * @Date 2021/11/18 19:22
 * @Version 1.0
 * @Since 1.0
 **/
@Getter
public enum DataBaseTypeEnum {
    /**
        目前只支持 Mysql, Oracle, SqlServer 三种数据库
     */
    DB_MYSQL(1,"Mysql"),
    DB_ORACLEL(2,"Oracle"),
    DB_SQLSERVER(3,"SqlServer");

    private Integer value;
    private String msg;

    DataBaseTypeEnum(Integer value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public boolean equalsByValue(Integer value) {
        return this.value.equals(value);
    }

    public static DataBaseTypeEnum getByValue(Integer value) {

        if (null == value) {
            return null;
        }

        for (DataBaseTypeEnum typeEnum : DataBaseTypeEnum.values()) {
            if (typeEnum.value.equals(value)) {
                return typeEnum;
            }
        }
        return null;
    }
}
