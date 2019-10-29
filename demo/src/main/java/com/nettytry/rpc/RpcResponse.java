package com.nettytry.rpc;

import java.io.Serializable;

/**
 * Created by adimn on 2019/10/29.
 */
public class RpcResponse implements Serializable{
    private String methodname;
    private String classname;
    private Object reponse;

    public String getMethodname() {
        return methodname;
    }

    public void setMethodname(String methodname) {
        this.methodname = methodname;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public Object getReponse() {
        return reponse;
    }

    public void setReponse(Object reponse) {
        this.reponse = reponse;
    }

    @Override
    public String toString() {
        return "RpcResponse{" +
                "methodname='" + methodname + '\'' +
                ", classname='" + classname + '\'' +
                ", reponse=" + reponse +
                '}';
    }
}
