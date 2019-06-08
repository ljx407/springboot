package com.ljx.chapter5.typehandler;

import com.ljx.chapter5.enums.BookTypeEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedJdbcTypes(JdbcType.INTEGER)
@MappedTypes(BookTypeEnum.class)
public class BookTypeTypeHandler extends BaseTypeHandler<BookTypeEnum> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, BookTypeEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i,parameter.getKey());
    }

    @Override
    public BookTypeEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return BookTypeEnum.getByKey(rs.getInt(columnName));
    }

    @Override
    public BookTypeEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return BookTypeEnum.getByKey(rs.getInt(columnIndex));
    }

    @Override
    public BookTypeEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return BookTypeEnum.getByKey(cs.getInt(columnIndex));
    }
}
