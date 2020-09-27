package de.nosebrain.epiguider;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class EpiguiderSecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

  public EpiguiderSecurityWebApplicationInitializer() {
    // do not initialize anything
  }
  
  @Override
  protected String getDispatcherWebApplicationContextSuffix() {
    return EpiguiderApplicationInitializer.SERVLET_NAME;
  }

}
