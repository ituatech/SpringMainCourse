package com.it_uatech.lifecycle;

import com.it_uatech.services.AbstractGreetingServiceImpl;
import com.it_uatech.services.SingletonGreetingServiceImpl;
import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Field;

public class CustomBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().equals(CustomLifeCycleBean.class)) {
            System.out.println("Шаг #5: BeanPostProcessor.postProcessBeforeInitialization\n");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().equals(CustomLifeCycleBean.class)) {
            System.out.println("Шаг #8: BeanPostProcessor.postProcessAfterInitialization\n");
        }

        if (bean.getClass().equals(SingletonGreetingServiceImpl.class)) {
            try {
                updateGreeting(bean);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return bean;
    }

    @SneakyThrows
    private void updateGreeting(Object bean) throws NoSuchFieldException, IllegalAccessException {
        Class aClass = AbstractGreetingServiceImpl.class;
        Field firstGreetingField = aClass.getDeclaredField("firstGreeting");
        Field reGreetingField = aClass.getDeclaredField("reGreeting");

        firstGreetingField.setAccessible(true);
        reGreetingField.setAccessible(true);
        firstGreetingField.set(bean, "Доброго времени суток господа");
        reGreetingField.set(bean, "Это снова вы! Какой приятный сюрприз!");
    }
}
