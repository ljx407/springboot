package com.ljx.chapter7cache.typehandlers;

import com.ljx.chapter7cache.enums.SexEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 指定 @MappedJdbcTypes 和 @MappedTypes
 * mybatis 在做转换时， javaType和jdbcType 去匹配对应的转换器
 * 比如：查询User时，通过数据库查询 sex = "1" ，resultType 中 sex 为SexEnum
 * 那么mybatis 就回去查询， jdbcType = varche , javaType = SexEnum的typeHandler
 * 查询到了就用该typeHandler 处理
 *
 * 此时的SexEnumHandler并没有标明任何spring的annotation，比如@Component,但是最终spring也能够识别当前类
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(SexEnum.class)
public class SexEnumHandler extends BaseTypeHandler<SexEnum> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, SexEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getKey());
    }

    @Override
    public SexEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return SexEnum.getEnumByKey(rs.getString(columnName));
    }

    @Override
    public SexEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return SexEnum.getEnumByKey(rs.getString(columnIndex));
    }

    @Override
    public SexEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return SexEnum.getEnumByKey(cs.getString(columnIndex));
    }
}
