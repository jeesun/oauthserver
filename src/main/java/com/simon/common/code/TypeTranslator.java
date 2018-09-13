package com.simon.common.code;

/**
 * 数据表列类型转换为java类型
 *
 * @author simon
 * @create 2018-08-08 21:09
 **/

public class TypeTranslator {
    private static String BIT = "BIT";
    private static String BIT1 = "BIT(1)";
    private static String TINYINT = "TINYINT";
    private static String TINYINT1 = "TINYINT(1)";
    private static String SMALLINT = "SMALLINT";
    private static String MEDIUMINT = "MEDIUMINT";
    private static String INTEGER = "INTEGER";
    private static String INT = "INT";
    private static String YEAR = "YEAR";
    private static String UNSIGNED = "UNSIGNED";
    private static String BIGINT = "BIGINT";
    private static String FLOAT = "FLOAT";
    private static String DOUBLE = "DOUBLE";
    private static String DECIMAL = "DECIMAL";
    private static String NUMERIC = "NUMERIC";
    private static String DATE = "DATE";
    private static String TIME = "TIME";
    private static String DATETIME = "DATETIME";
    private static String TIMESTAMP = "TIMESTAMP";
    private static String VARCHAR = "VARCHAR";
    private static String CHAR = "CHAR";
    private static String TEXT = "TEXT";
    private static String BINARY = "BINARY";
    private static String BLOB = "BLOB";
    private static String SERIAL = "SERIAL";
    private static String BIGSERIAL = "BIGSERIAL";
    private static String CHARACTER = "CHARACTER";
    private static String BOOLEAN = "BOOLEAN";
    private static String BOOL = "BOOL";
    private static String REAL = "REAL";
    private static String BYTEA = "BYTEA";

    /**
     * 参考https://documentation.progress.com/output/DataDirect/DataDirectCloud/index.html#page/queries%2Fmysql-data-types.html%23
     * @param columnType 列类型
     * @param dataType 数据类型
     * @return java数据类型
     */
    public static String translateMySQL(String columnType, String dataType){
        columnType = columnType.toUpperCase();
        dataType = dataType.toUpperCase();
        if(dataType.contains(BIT)){
            if(BIT1.equals(dataType)){
                return "Boolean";
            }else{
                return "Byte[]";
            }
        }
        if(dataType.contains(TINYINT)){
            if(TINYINT1.equals(columnType)){
                return "Boolean";
            }else{
                return "Integer";
            }
        }
        if(BOOLEAN.equals(dataType) || BOOL.equals(dataType)){
            return "Boolean";
        }
        if(SMALLINT.equals(dataType)){
            return "Short";
        }
        if(MEDIUMINT.equals(dataType) || YEAR.equals(dataType) || INT.equals(dataType)){
            return "Integer";
        }
        if(dataType.contains(INTEGER)){
            if(dataType.contains(UNSIGNED)){
                return "Long";
            }else{
                return "Integer";
            }
        }
        if(dataType.contains(BIGINT)){
            if(dataType.contains(UNSIGNED)){
                return "BigInteger";
            }
            return "Long";
        }
        if(FLOAT.equals(dataType)){
            return "Float";
        }
        if(DOUBLE.equals(dataType)){
            return "Double";
        }
        if(DECIMAL.equals(dataType) || NUMERIC.equals(dataType)){
            return "BigDecimal";
        }
        if(DATE.equals(dataType) || DATETIME.equals(dataType) || TIMESTAMP.equals(dataType)){
            return "Date";
        }
        if(TIME.equals(dataType)){
            return "Time";
        }
        if(dataType.contains(CHAR) || TEXT.equals(dataType)){
            return "String";
        }
        if(dataType.contains(BINARY) || BLOB.equals(dataType)){
            return "Byte[]";
        }
        return "String";
    }

    /**
     * 参考https://documentation.progress.com/output/DataDirect/DataDirectCloud/index.html#page/queries/postgresql-data-types.html
     * @param columnType 列类型
     * @param dataType 数据类型
     * @return java数据类型
     */
    public static String translatePostgreSQL(String columnType, String dataType){
        columnType = columnType.toUpperCase();
        dataType = dataType.toUpperCase();

        if(dataType.contains(BIT)){
            if(BIT1.equals(dataType)){
                return "Boolean";
            }else{
                return "Byte[]";
            }
        }
        if(SMALLINT.equals(dataType)){
            return "Short";
        }
        if(INTEGER.equals(dataType) || SERIAL.equals(dataType)){
            return "Integer";
        }
        if(BIGINT.equals(dataType) || BIGSERIAL.equals(dataType)){
            return "Long";
        }
        if(dataType.contains(CHARACTER) || dataType.contains(TEXT)){
            return "String";
        }
        if(BOOLEAN.equals(dataType)){
            return "Boolean";
        }
        if(DECIMAL.equals(dataType) || NUMERIC.equals(dataType)){
            return "BigDecimal";
        }
        if(REAL.equals(dataType)){
            return "Float";
        }
        if(dataType.contains(DOUBLE)){
            return "Double";
        }
        if(dataType.contains(TIME) || DATE.equals(dataType)){
            return "Date";
        }
        if(BYTEA.equals(dataType)){
            return "Byte[]";
        }
        return "String";
    }

    public static String translateOracle(String columnType, String dataType){

        return "String";
    }
}
