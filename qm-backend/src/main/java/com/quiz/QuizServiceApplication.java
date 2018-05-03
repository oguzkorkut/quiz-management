package com.quiz;

import java.util.Arrays;
import java.util.LinkedList;

import javax.ws.rs.Path;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.ResourceProvider;
import org.apache.cxf.jaxrs.spring.SpringResourceFactory;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = QuizServiceApplication.class)
@EnableAutoConfiguration
@SpringBootApplication
public class QuizServiceApplication extends SpringBootServletInitializer {

	private static final Logger logger = LogManager.getLogger(QuizServiceApplication.class);

	@Autowired
	private ApplicationContext ctx;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		
		setRegisterErrorPageFilter(false);
		application.sources(QuizServiceApplication.class);
		
		return application.sources(QuizServiceApplication.class);
	}

	public static void main(String[] args) {
		logger.trace("QuizServiceApplication main started.");
		SpringApplication.run(QuizServiceApplication.class, args);
		logger.trace("QuizServiceApplication main completed.");
	}

	@Bean
	public ServletRegistrationBean cxfServletRegistrationBean() {
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(new CXFServlet(), "/services/*");
		registrationBean.setAsyncSupported(true);
		registrationBean.setLoadOnStartup(1);
		registrationBean.setName("CXFServlet");
		return registrationBean;
	}

	@Bean
	public Server jaxRsServer() {
		logger.trace("jaxRsServer started.");
		LinkedList<ResourceProvider> resourceProviders = new LinkedList<>();
		for (String beanName : ctx.getBeanDefinitionNames()) {
			if (ctx.findAnnotationOnBean(beanName, Path.class) != null) {
				SpringResourceFactory factory = new SpringResourceFactory(beanName);
				factory.setApplicationContext(ctx);
				resourceProviders.add(factory);
			}
		}
		if (!resourceProviders.isEmpty()) {
			JAXRSServerFactoryBean factory = new JAXRSServerFactoryBean();
			factory.setBus(ctx.getBean(SpringBus.class));
			factory.setProviders(Arrays.asList(new JacksonJsonProvider()));
			factory.setResourceProviders(resourceProviders);
			return factory.create();
		} else {
			return null;
		}
	}

}