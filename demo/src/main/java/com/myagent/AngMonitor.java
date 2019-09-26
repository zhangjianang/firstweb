package com.myagent;

import javassist.*;

/**
 * Created by adimn on 2019/9/26.
 */
public class AngMonitor {
    public static void main(String[] args) throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException {
        ClassPool pool = new ClassPool(true);
//        pool.insertClassPath(new LoaderClassPath(StringUtils.class.getClassLoader()));

//        不能用make了，我们得用现有的方法。
//        CtClass targetClass = pool.makeClass("com.myagent.dynamic");
        CtClass targetClass = pool.get("com.myagent.StringUtils");

        String mname = "addStringTime";
        CtMethod method = targetClass.getDeclaredMethod(mname);
        CtMethod copymd = CtNewMethod.copy(method, method.getName() + "$target", targetClass, null);

        targetClass.addMethod(copymd);

        String src = "{" +
                "long begin = System.nanoTime();" +
                "Object res="+method.getName()+"$target($$);"+
                "System.out.println(System.nanoTime() - begin);" +
                "return ($r)res;" +
                "}";
        method.setBody(src);
        targetClass.toClass();
        StringUtils stu = new StringUtils();
        stu.addStringTime(100);
//        Thread.currentThread().getContextClassLoader();

    }
}
