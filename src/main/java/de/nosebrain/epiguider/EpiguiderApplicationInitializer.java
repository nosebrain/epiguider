package de.nosebrain.epiguider;

import de.nosebrain.epiguider.config.EpiguiderConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public final class EpiguiderApplicationInitializer implements WebApplicationInitializer {
  
  public static final String SERVLET_NAME = "epiguider";
  
  @Override
  public void onStartup(final ServletContext servletContext) throws ServletException {
    final WebApplicationContext context = createContext();
    servletContext.addListener(new ContextLoaderListener(context));
    
    // the character encoding filter
    final CharacterEncodingFilter filter = new CharacterEncodingFilter();
    filter.setEncoding("UTF-8");
    servletContext.addFilter("encodingFilter", filter);
    
    // the spring servlet
    final DispatcherServlet servlet = new DispatcherServlet(context);
    servlet.setThrowExceptionIfNoHandlerFound(true);
    final ServletRegistration.Dynamic dispatcher = servletContext.addServlet(SERVLET_NAME, servlet);
    dispatcher.setLoadOnStartup(1);
    dispatcher.setAsyncSupported(true);
    dispatcher.addMapping("/");
  }
  
  private static AnnotationConfigWebApplicationContext createContext() {
    final AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
    context.setConfigLocation(EpiguiderConfig.class.getPackage().getName());
    return context;
  }
}
