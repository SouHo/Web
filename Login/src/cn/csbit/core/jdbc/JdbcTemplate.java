package cn.csbit.core.jdbc;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.ResultSetExtractor;


public class JdbcTemplate extends org.springframework.jdbc.core.JdbcTemplate{
	
	public JdbcTemplate(){
		super();
	}
	public JdbcTemplate(DataSource dataSource){
		super(dataSource);
	}
	public JdbcTemplate(DataSource dataSource, boolean isnew){
		super(dataSource, isnew);
	}
	
	/**
	 *原有的方法在查询结果为空时，抛出异常
	 *重写后查询结构为空时，map.size()==0
	 */
	@Override
	public Map<String, Object> queryForMap(String sql, Object... params) {
		
		return this.query(sql, params,
				new ResultSetExtractor<Map<String, Object>>() {

					@Override
					public Map<String, Object> extractData(ResultSet rs)
							throws SQLException {
						Map<String, Object> map = new HashMap<String, Object>();
						if(rs.next()) {
							ResultSetMetaData metaData = rs.getMetaData();
							int columns = metaData.getColumnCount();
							for (int i = 1; i < columns + 1; i++) {
								String columnName = metaData.getColumnLabel(i);
								map.put(columnName, rs.getObject(i));
							}
						}
						return map;
					}

				});
	}
}
