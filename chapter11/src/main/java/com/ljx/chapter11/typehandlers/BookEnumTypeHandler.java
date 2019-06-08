package com.ljx.chapter11.typehandlers;

import com.ljx.chapter11.enums.BookEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(BookEnum.class)
public class BookEnumTypeHandler extends BaseTypeHandler<BookEnum> {


    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, BookEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i,parameter.getKey());
    }

    @Override
    public BookEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return BookEnum.resolve(rs.getString(columnName));
    }

    @Override
    public BookEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return BookEnum.resolve(rs.getString(columnIndex));
    }

    @Override
    public BookEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return BookEnum.resolve(cs.getString(columnIndex));
    }
}
