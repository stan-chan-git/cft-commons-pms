package cft.commons.pms.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppClassLoader;
import org.eclipse.jetty.webapp.WebAppContext;

import cft.commons.core.constant.Constants;

/**
 *  @author daniel
 *
 */
public class JettyFactory {

	static String DEFAULT_WEBAPP_PATH = "src/main/webapp";
	//static String DEFAULT_WEBAPP_PATH = "WebContent"; //for eclipse web project
	private static final String WINDOWS_WEBDEFAULT_PATH = "cft/commons/showcase/util/webdefault-windows.xml";

	public static Server createServerInSource(int port, String contextPath) {

		Server server = new Server();
		server.setStopAtShutdown(true);

		SelectChannelConnector connector = new SelectChannelConnector();
		connector.setPort(port);
		connector.setReuseAddress(false);
		server.setConnectors(new Connector[] { connector });

		WebAppContext webAppContext = new WebAppContext(DEFAULT_WEBAPP_PATH, contextPath);
		//修改webdefault.xml，解决Windows下Jetty Lock住静态文件的问题.
		//webAppContext.setDefaultsDescriptor(WINDOWS_WEBDEFAULT_PATH);
		server.setHandler(webAppContext);

		return server;
	}

	public static void setTldJarNames(Server server, String... jarNames) {
		WebAppContext context = (WebAppContext) server.getHandler();

		List<String> jarNameExprssions = new ArrayList<String>();
		jarNameExprssions.add(".*/jstl-[^/]*\\.jar$");
		jarNameExprssions.add(".*/.*taglibs[^/]*\\.jar$");

		for (String jarName : jarNames) {
			jarNameExprssions.add(".*/" + jarName + "-[^/]*\\.jar$");
		}

		context.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",
				StringUtils.join(jarNameExprssions, '|'));
	}

	/**
	 * 快速重新启动application，重载build/classes与WebContent/WEB-INF/classes.
	 */
	public static void reloadContext(Server server) throws Exception {
		WebAppContext webAppContext = (WebAppContext) server.getHandler();

		System.out.println(Constants.UTL_LOG + "JettyFactory: Application reloading");
		webAppContext.stop();

		WebAppClassLoader classLoader = new WebAppClassLoader(webAppContext);
		classLoader.addClassPath("target/classes");
		classLoader.addClassPath("target/test-classes");
		//classLoader.addClassPath("build/classes"); //for eclipse web project
		classLoader.addClassPath(DEFAULT_WEBAPP_PATH + "/WEB-INF/classes");
		webAppContext.setClassLoader(classLoader);

		webAppContext.start();

		System.out.println(Constants.UTL_LOG + "JettyFactory: Application reloaded");
	}
}