package pers.lqresier.dis.demo.guava;

/**
 * Created with IDEA
 *
 * @author qiujiajin
 * @date 2020/8/22 13:11
 * Description
 */
public class Person {
    private String name;
    private String gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Leg getLeg(){
        return new Leg();
    }

    private class Leg{

        public String ownerName(){
            return name;
        }
    }
}
