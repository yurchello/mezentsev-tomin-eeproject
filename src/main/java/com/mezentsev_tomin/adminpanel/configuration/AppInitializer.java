package com.mezentsev_tomin.adminpanel.configuration;

import com.mezentsev_tomin.adminpanel.utils.AAAA;
import com.mezentsev_tomin.adminpanel.utils.BBB;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AppConfig.class };
	}
 
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}
 
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
		registration.setMultipartConfig(getMultipartConfigElement());
        boolean done = registration.setInitParameter("throwExceptionIfNoHandlerFound", "true"); // -> true
        if(!done) throw new RuntimeException();
	}

	private MultipartConfigElement getMultipartConfigElement() {
		MultipartConfigElement multipartConfigElement = new MultipartConfigElement(	LOCATION, MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
		return multipartConfigElement;
	}

	private static final String LOCATION = "C:/aaa/"; // Temporary location where files will be stored

	private static final long MAX_FILE_SIZE = 5242880; // 5MB : Max file size.
	// Beyond that size spring will throw exception.
	private static final long MAX_REQUEST_SIZE = 20971520; // 20MB : Total request size containing Multi part.

	private static final int FILE_SIZE_THRESHOLD = 0; // Size threshold after which files will be written to disk

//	@Override
//	protected Filter[] getServletFilters() {
//		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
//		characterEncodingFilter.setEncoding("UTF-8");
//		return new Filter[]{ characterEncodingFilter };
//	}

//	@Override
//	protected Filter[] getServletFilters() {
//		CharacterEncodingFilter charFilter = new CharacterEncodingFilter();
//		charFilter.setEncoding("UTF-8");
//		charFilter.setForceEncoding(true);
//		return new Filter[] { new HiddenHttpMethodFilter(), charFilter,
//				new HttpPutFormContentFilter() };
//	}

//	@Override
//	public void onStartup(ServletContext servletContext) throws ServletException {
//
//		super.onStartup(servletContext);
//		FilterRegistration.Dynamic fr = servletContext.addFilter("encodingFilter",
//				new CharacterEncodingFilter());
//		fr.setInitParameter("encoding", "UTF-8");
//		fr.setInitParameter("forceEncoding", "true");
//		fr.addMappingForUrlPatterns(null, true, "/*");
//
//
//	}



}
