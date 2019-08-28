package com.ang.dubboclient;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.ang.firstweb.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.concurrent.ExecutionException;


/**
 * Created by adimn on 2019/8/27.
 */
public class MyClient {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
       /*
        String remoteURL = "dubbo://127.0.0.1:8902/com.ang.firstweb.service.UserService";
        UserService userService = runclient(null);


        while (!"exist".equals(cmd=read())){
            System.out.println(userService.getInfo());
        }
*/

//        以下为通过配置启动
        String cmd;
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-bean.xml");
        UserService userService1 = (UserService)context.getBean("userService");
        while (!"exist".equals(cmd=read())){
            long begin = System.currentTimeMillis();

            System.out.println(userService1.getInfo());
//            Future<String> f1 = RpcContext.getContext().getFuture();
//
//            System.out.println(userService1.getInfo());
//            Future<String> f2 = RpcContext.getContext().getFuture();
//
//            System.out.println(userService1.getInfo());
//            Future<String> f3 = RpcContext.getContext().getFuture();
//
//            System.out.println("-----------------------");
//            System.out.println(f1.get());
//            System.out.println(f2.get());
//            System.out.println(f3.get());
            userService1.doNothing();
            System.out.println(System.currentTimeMillis()-begin);
        }
    }

    public static UserService  runclient(String remoteURL){
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("ang-client");

        ReferenceConfig<UserService> referenceConfig = new ReferenceConfig<>();

        referenceConfig.setApplication(applicationConfig);

//        增加了注册中心，就可以不用设置url了
        referenceConfig.setRegistry(new RegistryConfig("multicast://224.1.2.3:11111"));

        referenceConfig.setLoadbalance("roundrobin");

        referenceConfig.setInterface(UserService.class);
        referenceConfig.setUrl(remoteURL);

//        UserService userService = referenceConfig.get();

//        System.out.println(userService.getInfo());

        return referenceConfig.get();
    }

    private static String read() throws IOException {
        byte[] bytes = new byte[1024];
        LineNumberReader reader = new LineNumberReader(new InputStreamReader(System.in));
        return  reader.readLine();
    }
}
