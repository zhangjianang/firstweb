package com.nettytry.rpc;

import java.io.Serializable;

/**
 * Created by adimn on 2019/10/29.
 */
public class RpcRequest implements Serializable {
    private String classname;
    private String methodname;
    private Object param1;
    private Object param2;
    private String id;

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getMethodname() {
        return methodname;
    }

    public void setMethodname(String methodname) {
        this.methodname = methodname;
    }

    public Object getParam1() {
        return param1;
    }

    public void setParam1(Object param1) {
        this.param1 = param1;
    }

    public Object getParam2() {
        return param2;
    }

    public void setParam2(Object param2) {
        this.param2 = param2;
    }

    @Override
    public String toString() {
        return "RpcRequest{" +
                "classname='" + classname + '\'' +
                ", methodname='" + methodname + '\'' +
                ", param1=" + param1 +
                ", param2=" + param2 +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
