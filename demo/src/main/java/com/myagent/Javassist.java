package com.myagent;

import javassist.*;

/**
 * Created by adimn on 2019/9/26.
 */
public class Javassist {
    public static void main(String[] args) throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException {
        ClassPool pool =  new ClassPool();
        pool.insertClassPath(new LoaderClassPath(Javassist.class.getClassLoader()));

        CtClass targetclass = pool.makeClass("com.myagent.ahello");
        targetclass.addInterface( pool.get(IHello.class.getName()));

        CtClass returnType = pool.get(void.class.getName());
        String mname = "sayHello";
        CtClass[] params = new CtClass[]{pool.get(String.class.getName())};

        CtMethod ctMethod = new CtMethod(returnType, mname, params,targetclass);
        String src = "{" +
                "System.out.println($0);" +
                "System.out.println($args);" +
                "System.out.println($$);" +
                "System.out.println(\"hello\"+$1);" +
                "}";
        ctMethod.setBody(src);

        targetclass.addMethod(ctMethod);

        Class aClass = targetclass.toClass();
        IHello iHello = (IHello) aClass.newInstance();
        iHello.sayHello("好可爱！");
    }
}

interface IHello{
    void sayHello(String param);
}
