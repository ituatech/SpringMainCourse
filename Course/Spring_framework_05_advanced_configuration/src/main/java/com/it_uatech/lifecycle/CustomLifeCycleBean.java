package com.it_uatech.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class CustomLifeCycleBean implements InitializingBean, DisposableBean, BeanNameAware, BeanFactoryAware, ApplicationContextAware {

    @Override
    public void setBeanName(String s) {
        System.out.println("Шаг #2: BeanNameAware\n");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("Шаг #3: BeanFactoryAware\n");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("Шаг #4: ApplicationContextAware\n");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("Шаг #6: InitializingBean.afterPropertiesSet\n");
    }

    public void customInitMethod() {
        System.out.println("Шаг #7: CustomLifeCycleBean.customInitMethod\n");
    }

    @Override
    public void destroy() {
        System.out.println("Шаг #9: DisposableBean.destroy\n");
    }

    public void customDestroyMethod() {
        System.out.println("Шаг #10: CustomLifeCycleBean.customDestroyMethod\n");
    }
}
