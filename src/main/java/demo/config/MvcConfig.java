package demo.config;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "demo.web")
public class MvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/webapp/resources/");
	}
	
	@Bean 
	public ViewResolver viewResolver() {
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		
		HashMap<String, String> mediaTypes = new HashMap<String, String>();
		mediaTypes.put("json", "application/json");
		mediaTypes.put("xml", "application/xml");
		resolver.setMediaTypes(mediaTypes);
		
		ArrayList<View> views = new ArrayList<View>();
		views.add(new MappingJacksonJsonView());
		resolver.setDefaultViews(views);
		
		ArrayList<ViewResolver> resolvers = new ArrayList<ViewResolver>();
		resolvers.add(internalResourceViewResolver());
		resolver.setViewResolvers(resolvers);
		
		resolver.setDefaultContentType(MediaType.TEXT_HTML);
		return resolver;
		
	}
	
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
}
