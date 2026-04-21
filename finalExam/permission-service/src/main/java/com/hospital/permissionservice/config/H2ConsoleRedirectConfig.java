package com.hospital.permissionservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * H2 is registered at {@code /h2-console/*}; the exact path {@code /h2-console} does not match
 * that servlet mapping, so requests without a trailing slash would 404. Redirect to the slash form.
 */
@Configuration
public class H2ConsoleRedirectConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/h2-console", "/h2-console/");
	}
}
