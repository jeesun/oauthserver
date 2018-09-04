package com.simon.common.code;

/**
 * 数据表列类型转换为java类型
 *
 * @author simon
 * @create 2018-08-08 21:09
 **/

public class TypeTranslator {
    //https://documentation.progress.com/output/DataDirect/DataDirectCloud/index.html#page/queries%2Fmysql-data-types.html%23
    public static String translateMySQL(String columnType, String dataType){
        columnType = columnType.toUpperCase();
        dataType = dataType.toUpperCase();
        if(dataType.equals("BIT")){
            if(columnType.equals("BIT(1)")){
                return "Boolean";
            }else{
                return "Byte[]";
            }
        }
        if(dataType.equals("TINYINT")){
            if(columnType.equals("TINYINT(1)")){
                return "Boolean";
            }else{
                return "Integer";
            }
        }
        if(dataType.equals("SMALLINT")){
            return "Short";
        }
        if(dataType.equals("MEDIUMINT") || dataType.equals("YEAR") || dataType.equals("INT")){
            return "Integer";
        }
        if(dataType.contains("INTEGER")){
            if(dataType.contains("UNSIGNED")){
                return "Long";
            }else{
                return "Integer";
            }
        }
        if(dataType.contains("BIGINT")){
            if(dataType.contains("UNSIGNED")){
                return "BigInteger";
            }
            return "Long";
        }
        if(dataType.equals("FLOAT")){
            return "Float";
        }
        if(dataType.equals("DOUBLE")){
            return "Double";
        }
        if(dataType.equals("DECIMAL") || dataType.equals("NUMERIC")){
            return "BigDecimal";
        }
        if(dataType.equals("DATE") || dataType.equals("DATETIME") || dataType.equals("TIMESTAMP") || dataType.equals("TIME")){
            return "Date";
        }
        if(dataType.contains("CHAR") || dataType.equals("TEXT")){
            return "String";
        }
        if(dataType.contains("BINARY") || dataType.equals("BLOB")){
            return "Byte[]";
        }
        return "String";
    }

    //https://documentation.progress.com/output/DataDirect/DataDirectCloud/index.html#page/queries/postgresql-data-types.html
    public static String translatePostgreSQL(String columnType, String dataType){
        columnType = columnType.toUpperCase();
        dataType = dataType.toUpperCase();

        if(dataType.equals("BIT")){
            if(columnType.equals("BIT(1)")){
                return "Boolean";
            }else{
                return "Byte[]";
            }
        }
        if(dataType.equals("SMALLINT")){
            return "Short";
        }
        if(dataType.equals("INTEGER") || dataType.equals("SERIAL")){
            return "Integer";
        }
        if(dataType.equals("BIGINT") || dataType.equals("BIGSERIAL")){
            return "Long";
        }
        if(dataType.contains("CHARACTER") || dataType.contains("TEXT")){
            return "String";
        }
        if(dataType.equals("BOOLEAN")){
            return "Boolean";
        }
        if(dataType.equals("DECIMAL") || dataType.equals("NUMERIC")){
            return "BigDecimal";
        }
        if(dataType.equals("REAL")){
            return "Float";
        }
        if(dataType.contains("DOUBLE")){
            return "Double";
        }
        if(dataType.contains("TIME") || dataType.equals("DATE")){
            return "Date";
        }
        if(dataType.equals("BYTEA")){
            return "Byte[]";
        }
        return "String";
    }

    public static String translateOracle(String columnType, String dataType){

        return "String";
    }
}
