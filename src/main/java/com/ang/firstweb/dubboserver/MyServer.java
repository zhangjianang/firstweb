package com.ang.firstweb.dubboserver;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.ang.firstweb.service.UserService;
import com.ang.firstweb.service.impl.UserServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static java.lang.Integer.MAX_VALUE;

/**
 * Created by adimn on 2019/8/27.
 */
public class MyServer {
    public static void main(String[] args) throws InterruptedException {
//        new MyServer().openServer(-1);


//        以下为通过spring配置启动
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-bean.xml");
        context.start();
        Thread.sleep(MAX_VALUE);
    }

    public void openServer(int port) {


        ServiceConfig<UserService> serviceConfig = new ServiceConfig<>();

        serviceConfig.setApplication(new ApplicationConfig("ang-server"));

//        设置端口和协议
        serviceConfig.setProtocol( new ProtocolConfig("dubbo",port));

//      设置空的配置中心
//        serviceConfig.setRegistry(new RegistryConfig(RegistryConfig.NO_AVAILABLE));
        serviceConfig.setRegistry(new RegistryConfig("multicast://224.1.2.3:11111"));

        serviceConfig.setInterface(UserService.class);

        UserServiceImpl userService = new UserServiceImpl();
        serviceConfig.setRef(userService);
        serviceConfig.export();
        List<URL> exportedUrls = serviceConfig.getExportedUrls();
        System.out.println("服务启动，"+exportedUrls.get(0).getPort());
        userService.setPort(exportedUrls.get(0).getPort());
    }
}
