package de.nosebrain.epiguider.config;

import de.nosebrain.spring.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.Properties;

@Configuration
@EnableWebSecurity
public class EpiguiderSecurityConfig extends WebSecurityConfigurerAdapter {
  public static final String USER_ROLE = "user";
  
  @Autowired
  public void registerGlobalAuthentication(final AuthenticationManagerBuilder auth, final AuthenticationProvider customProvider) throws Exception {
    auth.authenticationProvider(customProvider);
  }
  
  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers("/**").hasAuthority(USER_ROLE).and()
    .httpBasic().realmName("Epiguider");
  }
  
  @Bean
  protected AuthenticationProvider inMemoryUserDetailsManager(@Qualifier(EpiguiderConfig.SERVICE_PROPERTIES) final Properties properties) {
    final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    final InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager(properties, "auth.users.");
    authenticationProvider.setUserDetailsService(userDetailsService);
    return authenticationProvider;
  }

}
