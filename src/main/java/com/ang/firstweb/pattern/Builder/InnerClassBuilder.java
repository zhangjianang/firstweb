package com.ang.firstweb.pattern.Builder;

/**
 * Created by adimn on 2019/11/19.
 */
public class InnerClassBuilder {
    private String name;
    private int age;
    private String gender;

    private InnerClassBuilder(Inner inner){
        this.name = inner.iname;
        this.age = inner.iage;
        this.gender = inner.igender;
    }


    @Override
    public String toString() {
        return "InnerClassBuilder{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }

    public static class Inner{

        private String iname;
        private int iage;
        private String igender;


        public Inner name(String n){
            this.iname = n;
            return this;
        }
        public Inner age(int a){
            this.iage = a;
            return this;
        }
        public Inner gender(String g){
            this.igender = g;
            return this;
        }
        public InnerClassBuilder build(){
            return new InnerClassBuilder(this);
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


}

class BuildTest{
    public static void main(String[] args) {
        InnerClassBuilder.Inner inner = new InnerClassBuilder.Inner();
        InnerClassBuilder build = inner.age(10).name("ang").gender("male").build();
        System.out.println(build);
    }
}
