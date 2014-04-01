package cft.commons.pms.plugins;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SQLHelp {

	private static final String SELECT_ROW_COUNT_SQL = "SELECT COUNT(1) FROM (%s) ROWS_COUNT";
	private static final String SELECT_PAGES_MYSQL_LIMIT_SQL = "(%s) LIMIT %d,%d";
	private static final Logger log = LoggerFactory.getLogger(SQLHelp.class);

	public static int executeCount(MappedStatement mappedStatement, Object parameterObject,
			BoundSql boundSql, String sql) throws SQLException {
		log.info("=========executeCount==========");
		Connection connection = null;
		PreparedStatement countStmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			String bufferSql = String.format(SELECT_ROW_COUNT_SQL, sql);
			connection = mappedStatement.getConfiguration().getEnvironment().getDataSource()
					.getConnection();
			countStmt = connection.prepareStatement(bufferSql.toString());
			DefaultParameterHandler handler = new DefaultParameterHandler(mappedStatement,
					parameterObject, boundSql);
			handler.setParameters(countStmt);
			rs = countStmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} finally {
				try {
					if (countStmt != null) {
						countStmt.close();
					}
				} finally {
					if (connection != null && !connection.isClosed()) {
						connection.close();
					}
				}
			}
		}
		return count;
	}

	public static MappedStatement executeMySlqPages(MappedStatement mappedStatement, Object parameterObject,
			BoundSql boundSql, String sql, Page<?> page) throws SQLException {
		String formatSql = String.format(SELECT_PAGES_MYSQL_LIMIT_SQL, sql,
				(page.getPageNo()- 1) * page.getPageSize() , page.getPageSize());
		return copyFromNewSql(mappedStatement, boundSql, formatSql);
	}

	private static MappedStatement copyFromNewSql(MappedStatement ms, BoundSql boundSql, String sql) {
		BoundSql newBoundSql = copyFromBoundSql(ms, boundSql, sql);
		return copyFromMappedStatement(ms, new BoundSqlSqlSource(newBoundSql));
	}

	private static BoundSql copyFromBoundSql(MappedStatement ms, BoundSql boundSql, String sql) {
		BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), sql,
				boundSql.getParameterMappings(), boundSql.getParameterObject());
		for (ParameterMapping mapping : boundSql.getParameterMappings()) {
			String prop = mapping.getProperty();
			if (boundSql.hasAdditionalParameter(prop)) {
				newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
			}
		}
		return newBoundSql;
	}

	private static MappedStatement copyFromMappedStatement(MappedStatement ms,
			SqlSource newSqlSource) {
		Builder builder = new Builder(ms.getConfiguration(), ms.getId(), newSqlSource,
				ms.getSqlCommandType());

		builder.resource(ms.getResource());
		builder.fetchSize(ms.getFetchSize());
		builder.statementType(ms.getStatementType());
		builder.keyGenerator(ms.getKeyGenerator());
		if (ms.getKeyProperties() != null && ms.getKeyProperties().length != 0) {
			StringBuffer keyProperties = new StringBuffer();
			for (String keyProperty : ms.getKeyProperties()) {
				keyProperties.append(keyProperty).append(",");
			}
			keyProperties.delete(keyProperties.length() - 1, keyProperties.length());
			builder.keyProperty(keyProperties.toString());
		}

		// setStatementTimeout()
		builder.timeout(ms.getTimeout());

		// setStatementResultMap()
		builder.parameterMap(ms.getParameterMap());

		// setStatementResultMap()
		builder.resultMaps(ms.getResultMaps());
		builder.resultSetType(ms.getResultSetType());

		// setStatementCache()
		builder.cache(ms.getCache());
		builder.flushCacheRequired(ms.isFlushCacheRequired());
		builder.useCache(ms.isUseCache());

		return builder.build();
	}

	public static class BoundSqlSqlSource implements SqlSource {
		BoundSql boundSql;

		public BoundSqlSqlSource(BoundSql boundSql) {
			this.boundSql = boundSql;
		}

		public BoundSql getBoundSql(Object parameterObject) {
			return boundSql;
		}
	}
}
