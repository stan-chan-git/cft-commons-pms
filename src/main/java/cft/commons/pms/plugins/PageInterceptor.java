package cft.commons.pms.plugins;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.ibatis.binding.MapperMethod.ParamMap;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 为MyBatis提供基于方言(Dialect)的分页查询的插件
 * 
 * 将拦截Executor.query()方法实现分页方言的插入.
 * 
 * @author badqiu
 * @author miemiedev
 * 
 */

@Intercepts({ @Signature(type = Executor.class, method = "query", args = { MappedStatement.class,
		Object.class, RowBounds.class, ResultHandler.class }) })
public class PageInterceptor implements Interceptor {

	private static final Logger log = LoggerFactory.getLogger(PageInterceptor.class);
	static ExecutorService POOL = Executors.newCachedThreadPool();;

	@Override
	public Object intercept(final Invocation invocation) throws Throwable {
		// 当前环境 MappedStatement，BoundSql，及sql取得
		final MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
		log.info("======="+mappedStatement.getId()+"=======");
		Object parameter = invocation.getArgs()[1];
		final BoundSql boundSql = mappedStatement.getBoundSql(parameter);
		final String originalSql = boundSql.getSql().trim();
		final Object parameterObject = boundSql.getParameterObject();
		Page<?> page = null;
		if (parameterObject instanceof Page<?>) {
			page = (Page<?>) parameterObject;
		} else if (parameterObject instanceof ParamMap<?>) {
			org.apache.ibatis.binding.MapperMethod.ParamMap<?> a = (ParamMap<?>) parameterObject;
			Iterator<?> i = a.entrySet().iterator();
			while (i.hasNext()) {
				Map.Entry<String, Object> o = (Entry<String, Object>) i.next();
				if (o.getValue() instanceof Page<?>) {
					page = (Page<?>) o.getValue();
					break;
				}
			}
		}
		if (page != null) {
			//查询分页数据
			invocation.getArgs()[0] = SQLHelp.executeMySlqPages(mappedStatement,
					parameterObject, boundSql, originalSql, page);
			Future<List> rowsFuture = POOL.submit(new Callable<List>() {
				@Override
				public List call() throws Exception {
					return (List) invocation.proceed();
				}
			});
			//查询数据总数
			Future<Integer> countFuture = POOL.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					return SQLHelp.executeCount(mappedStatement, parameterObject, boundSql,
							originalSql);
				}
			});
			page.setTotalRecord(countFuture.get());
			return rowsFuture.get();
		}

		return invocation.proceed();
	}


	@Override
	public Object plugin(Object target) {
		// TODO Auto-generated method stub
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub
	}

}
