package com.telenordigital.abacourse.backend;

import java.util.logging.Logger;
import javax.ws.rs.core.Application;
import org.eclipse.jetty.server.ConnectionFactory;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.ForwardedRequestCustomizer;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class BackendServer {

    private static final int PORT = 10001;
    private static final Logger LOGGER = Logger.getLogger(BackendServer.class.getName());

    private final Server server;

    public BackendServer() {
        this.server = new Server(PORT);

    }

    private void start(final Application application) {
        ServletContainer servletContainer
                = new ServletContainer(ResourceConfig.forApplication(application));
        ServletHolder servletHolder = new ServletHolder(servletContainer);
        ServletContextHandler servletContextHandler = new ServletContextHandler(1);
        servletContextHandler.addServlet(servletHolder, "/*");
        ContextHandlerCollection contextHandlerCollection = new ContextHandlerCollection();
        contextHandlerCollection.addHandler(servletContextHandler);
        HandlerCollection handlerCollection = new HandlerCollection();
        handlerCollection.addHandler(contextHandlerCollection);
        this.server.setHandler(handlerCollection);

        HttpConfiguration config = new HttpConfiguration();
        config.addCustomizer(new ForwardedRequestCustomizer());
        config.setSendServerVersion(false);
        HttpConnectionFactory http = new HttpConnectionFactory(config);
        ServerConnector httpConnector
                = new ServerConnector(this.server, new ConnectionFactory[]{http});
        httpConnector.setPort(PORT);
        httpConnector.setHost("0.0.0.0");
        this.server.setConnectors(new Connector[]{httpConnector});
        try {
            this.server.start();
        } catch (Exception var5) {
            throw new RuntimeException(var5);
        }
    }


    public static void main(final String[] args) throws Exception {
        final Application application = createApplication();
        final ResourceConfig config = ResourceConfig.forApplication(application)
                .packages("com.telenordigital.abacourse.backend.rs");
        final BackendServer backendServer = new BackendServer();
        backendServer.start(config);
        LOGGER.fine("Started Backend server on port: " + PORT);
    }

    private static Application createApplication() {
        return new Application() {};
    }
}
