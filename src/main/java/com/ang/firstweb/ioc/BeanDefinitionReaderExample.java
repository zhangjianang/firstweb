package com.ang.firstweb.ioc;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.SimpleBeanDefinitionRegistry;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.util.Arrays;

/**
 * Created by adimn on 2019/9/9.
 */
public class BeanDefinitionReaderExample {
    public static void main(String[] args) {
        //注冊 中心
        BeanDefinitionRegistry beanDefinitionRegistry = new SimpleBeanDefinitionRegistry();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanDefinitionRegistry);

        DefaultResourceLoader loader = new DefaultResourceLoader();

        Resource resource = loader.getResource("application-bean.xml");

        reader.loadBeanDefinitions(resource);

        System.out.println(Arrays.toString(beanDefinitionRegistry.getBeanDefinitionNames()));
    }
}
