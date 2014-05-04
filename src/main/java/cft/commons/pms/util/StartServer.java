package cft.commons.pms.util;

import org.eclipse.jetty.server.Server;

import cft.commons.core.constant.Constants;

public class StartServer {
	
	public static final int PORT = 8080;
	public static final String CONTEXT = "/pms";
	public static final String[] TLD_JAR_NAMES = new String[] { "sitemesh", "spring-webmvc", "shiro-web" };

	public static void main(String[] args) throws Exception {

		Server server = JettyFactory.createServerInSource(PORT, CONTEXT);
		JettyFactory.setTldJarNames(server, TLD_JAR_NAMES);

		try {
			server.start();

			System.out.println(Constants.UTL_LOG + "Server running at http://localhost:" + PORT + CONTEXT);
			System.out.println("[HIT] Hit Enter to reload the application quickly");

			// wait user to ENTER to reload application.
			while (true) { 
				char c = (char) System.in.read();
				if (c == '\n') {
					JettyFactory.reloadContext(server);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

}
