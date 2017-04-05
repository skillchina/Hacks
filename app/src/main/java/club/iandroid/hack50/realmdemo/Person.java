package club.iandroid.hack50.realmdemo;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by gabriel on 2017/4/5.
 */

public class Person extends RealmObject{
    private long id;

    private String name;
    private int age;

    //一对一
    private Dog dog;

    //一对多
    private RealmList<Cat> cats;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public RealmList<Cat> getCats() {
        return cats;
    }

    public void setCats(RealmList<Cat> cats) {
        this.cats = cats;
    }
}
