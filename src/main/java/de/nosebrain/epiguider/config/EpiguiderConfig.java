package de.nosebrain.epiguider.config;

import de.nosebrain.epiguider.SeriesParser;
import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = SeriesParser.class)
@org.springframework.context.annotation.PropertySource(value = {
  "classpath:epiguider.properties",
  "file:${catalina.home}/conf/epiguider/epiguider.properties"
}, ignoreResourceNotFound = true)
public class EpiguiderConfig extends WebMvcConfigurerAdapter {

  public static final String SERVICE_PROPERTIES = "service_properties";

  @Bean
  public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  }

  @Bean(name = SERVICE_PROPERTIES)
  public static Properties getServiceProperties(final Environment environment) {
    final Properties properties = new Properties();
    for (final PropertySource<?> propertySource : ((ConfigurableEnvironment) environment).getPropertySources()) {
      if (propertySource instanceof EnumerablePropertySource<?>) {
        for (final String key : ((EnumerablePropertySource<?>) propertySource).getPropertyNames()) {
          final Object value = propertySource.getProperty(key);
          if (value instanceof String) {
            properties.setProperty(key, (String) value);
          }
        }
      }
    }
    return properties;
  }

  @Bean
  public Resource mybatisConfigLocation() {
    return new ClassPathResource("de/nosebrain/epiguider/epiguider-database.xml");
  }

  @Bean
  public DataSource getDataSource(@Value("${database.driver}") final String driverClassName,
                                  @Value("${database.url}") final String databaseUrl,
                                  @Value("${database.username}") final String databaseUserName,
                                  @Value("${database.password}") final String databasePassword) {
    final BasicDataSource dataSource = new BasicDataSource();
    dataSource.setDriverClassName(driverClassName);
    dataSource.setUrl(databaseUrl);
    dataSource.setUsername(databaseUserName);
    dataSource.setPassword(databasePassword);

    dataSource.setValidationQuery("select 42");
    dataSource.setTestOnReturn(false);
    dataSource.setTestOnBorrow(true);
    dataSource.setTestWhileIdle(true);
    dataSource.setNumTestsPerEvictionRun(10);
    dataSource.setTimeBetweenEvictionRunsMillis(1800000);
    dataSource.setMinEvictableIdleTimeMillis(3600000);

    return dataSource;
  }

  @Bean
  public SqlSessionFactory sqlSessionFactory(final Resource configLocation, final DataSource dataSource, final Environment environment) throws IOException {
    final Reader reader = new InputStreamReader(configLocation.getInputStream());
    // Null environment causes the configuration to use the default.
    // This will be overwritten below regardless.
    final XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader, null, null);
    final org.apache.ibatis.session.Configuration configuration = xmlConfigBuilder.parse();

    final TransactionFactory transactionFactory = new JdbcTransactionFactory();

    final String profile = "production"; // TODO: make configurable
    final org.apache.ibatis.mapping.Environment env = new org.apache.ibatis.mapping.Environment(profile, transactionFactory, dataSource);
    configuration.setEnvironment(env);

    final SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
    return builder.build(configuration);
  }

  @Bean
  public ViewResolver internalResourceViewResolver() {
    final InternalResourceViewResolver viewResolve = new InternalResourceViewResolver();
    viewResolve.setPrefix("/WEB-INF/views/");
    viewResolve.setSuffix(".jsp");

    return viewResolve;
  }

  @Override
  public void addResourceHandlers(final ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/assets/**").addResourceLocations("/WEB-INF/assets/");
  }
}
