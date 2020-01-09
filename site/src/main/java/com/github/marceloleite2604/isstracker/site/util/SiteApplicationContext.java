package com.github.marceloleite2604.isstracker.site.util;

import com.github.marceloleite2604.blimp.Blimp;
import com.github.marceloleite2604.isstracker.site.configuration.BeanNames;
import com.github.marceloleite2604.isstracker.site.exeption.SiteApplicationContextRuntimeException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SiteApplicationContext implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		synchronized (SiteApplicationContext.class) {
			SiteApplicationContext.applicationContext = applicationContext;
		}
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static Blimp getBlimp() {
		return retrieveBean(Blimp.class, BeanNames.BLIMP);
	}

	@SuppressWarnings({ "unchecked" })
	private static <T> T retrieveBean(Class<T> beanClass, String beanName) {
		checkApplicationContextIsReady();

		Object bean;
		if (beanName != null) {
			bean = retrieveBeanFromApplicationContext(beanName);
		} else {
			bean = retrieveBeanFromApplicationContext(beanClass);
		}

		checkBeanType(bean, beanClass);

		return (T) bean;
	}

	private static void checkApplicationContextIsReady() {
		if (applicationContext == null) {
			throw new SiteApplicationContextRuntimeException(
					"Sistem context is not available yet.");
		}
	}

	private static Object retrieveBeanFromApplicationContext(String beanName) {
		try {
			return applicationContext.getBean(beanName);

		} catch (BeansException exception) {
			throw new SiteApplicationContextRuntimeException(
					"Could not locate bean \"" + beanName + "\".", exception);
		}
	}

	private static Object retrieveBeanFromApplicationContext(Class<?> beanClass) {
		try {
			return applicationContext.getBean(beanClass);

		} catch (BeansException exception) {
			throw new SiteApplicationContextRuntimeException(
					"Could not locate a bean of type \"" + beanClass.getName() + "\".", exception);
		}
	}

	private static void checkBeanType(Object bean, Class<?> clazz) {
		if (!clazz.isInstance(bean)) {
			throw new SiteApplicationContextRuntimeException(
					"Bean \"" + bean + "\" is not of type \"" + clazz.getCanonicalName() + "\".");
		}

	}
}
