package com.nettytry.rpcv1;

import com.nettytry.rpc.*;

import java.util.UUID;

/**
 * Created by adimn on 2019/10/30.
 */
public class CalculatorProxy implements Calculator {
    private com.nettytry.rpcv1.RpcClient client;

    public CalculatorProxy() {
        this.client = new RpcClient();
        client.init("127.0.0.1",8081);
    }

    @Override
    public double add(double v1, double v2) {
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setMethodname("add");
        rpcRequest.setClassname("com.nettytry.rpc.CalculateImpl");
        rpcRequest.setParam1(1.0);
        rpcRequest.setParam2(2.0);
        rpcRequest.setId(UUID.randomUUID().toString());
        RpcResponse rpcResponse = client.sendRequest(rpcRequest);

        return (double) rpcResponse.getReponse();
    }

    @Override
    public double substraction(double v1, double v2) {
        return 0;
    }

    @Override
    public double multiply(double v1, double v2) {
        return 0;
    }

    public static void main(String[] args) {
        CalculatorProxy proxy = new CalculatorProxy();
        double add = proxy.add(1.0, 2.0);
        System.out.println("结果：" + add);
    }


}
