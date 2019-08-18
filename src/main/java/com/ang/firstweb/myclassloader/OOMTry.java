package com.ang.firstweb.myclassloader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by ang on 2019/8/17.
 */
public class OOMTry {
    public static void main(String[] args) {
        List info = new ArrayList<>();
        int i=0;
        int j=0;
        while (true){
            info.add(new User("name"+i++, new Random().nextLong()));
            new User(j-- +"name",new Random().nextLong());
        }
    }
}

class User{
    private String name;
    private Long uuid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

    public User(String name, Long uuid) {
        this.name = name;
        this.uuid = uuid;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println(name+" 被回收了");
    }
}
