package com.rw.spi;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.util.List;

@Configuration
@ConditionalOnClass(PersonService.class)
public class App implements EnvironmentAware, ApplicationContextAware, BeanDefinitionRegistryPostProcessor {
    private Environment environment;
    private ApplicationContext applicationContext;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        List<PersonService> loadFactories = SpringFactoriesLoader.loadFactories(PersonService.class, this.getClass().getClassLoader());
        loadFactories.forEach(personService -> {
            GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
            beanDefinition.setAbstract(false);
            beanDefinition.setLazyInit(false);
            beanDefinition.setAutowireCandidate(true);
            beanDefinition.setScope(BeanDefinition.SCOPE_SINGLETON);
            // 注入实现类
            beanDefinition.setBeanClass(personService.getClass());
            beanDefinitionRegistry.registerBeanDefinition(personService.getClass().getSimpleName(), beanDefinition);
        });
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
