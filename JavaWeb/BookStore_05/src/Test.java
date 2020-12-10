import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


class People {

}

class BaseClass<T> {
    BaseClass() {
    }
}

class SubClass extends BaseClass<People> {
    SubClass() {
        System.out.println(this.getClass());
    }
}


public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        Type genericSuperclass = SubClass.class.getGenericSuperclass();
        // 输出class sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl
        System.out.println(genericSuperclass.getClass());
        ParameterizedType superclass = (ParameterizedType) genericSuperclass;
        Type[] actualTypeArguments = superclass.getActualTypeArguments();
        for (Type actualTypeArgument : actualTypeArguments) {
            // 输出 class People
            System.out.println(actualTypeArgument);
        }
    }
}
