package com.rocky.entrance;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 *
 * */
public class Main {

    public static final String CONTEXT = "/";

    private static final String DEFAULT_WEBAPP_PATH = "src/main/webapp";

    public static void main(String[] args) {
        try {
            Server server = new Server();
            // 设置在JVM退出时关闭Jetty的钩子。
            server.setStopAtShutdown(true);

            //这是http的连接器
            ServerConnector connector = new ServerConnector(server);
            connector.setPort(8081);
            // 解决Windows下重复启动Jetty居然不报告端口冲突的问题.
            connector.setReuseAddress(false);
            server.setConnectors(new Connector[]{connector});

            WebAppContext webContext = new WebAppContext();
            webContext.setDescriptor(DEFAULT_WEBAPP_PATH + "/WEB-INF/web.xml");
            webContext.setContextPath("/");
            // 设置webapp的位置
            webContext.setResourceBase(DEFAULT_WEBAPP_PATH);
//            webContext.setClassLoader(Thread.currentThread().getContextClassLoader());
            server.setHandler(webContext);
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
