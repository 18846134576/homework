package homework;

import java.lang.annotation.*;
import java.lang.reflect.Field;

@Target({ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Work2 {
    String name() default "";
    int age() default 0;
}

class Name{
    @Work2(name = "张佳鑫")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
class Age{
    @Work2(age = 22)
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
class Main{
    public static void main(String[] args) {
        Class<Name> clazz = Name.class;
        Field[] f = clazz.getDeclaredFields();
        for (Field field : f){
            if (field.isAnnotationPresent(Work2.class)){
                Work2 annotation = field.getAnnotation(Work2.class);
                String value = annotation.name();
                System.out.println(field.getName()+" : "+value);
            }
        }
        Class<Age> clazz2 = Age.class;
        Field[] f1 = clazz2.getDeclaredFields();
        for(Field field : f1){
            if(field.isAnnotationPresent(Work2.class)){
                Work2 annotation = field.getAnnotation(Work2.class);
                int age = annotation.age();
                System.out.println(field.getName()+" : "+age);
            }
        }
    }
}
