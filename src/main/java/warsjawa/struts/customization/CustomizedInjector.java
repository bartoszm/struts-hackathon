package warsjawa.struts.customization;

import com.google.inject.MembersInjector;
import com.google.inject.Provider;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * User: bartosz.michalik@gmail.com
 * Date: 12.10.13
 * Time: 17:12
 */
public class CustomizedInjector<T> implements MembersInjector<T> {
    private Provider provider;
    private Method m;
    private Field f;


    public CustomizedInjector(Field f, Provider<?> p) {
        this.f = f;
        f.setAccessible(true);
        this.provider = p;
    }

    public CustomizedInjector(Method m, Provider<?> p) {
        this.m = m;
        this.provider = p;
    }

    @Override
    public void injectMembers(T instance) {
        if(provider != null) {
            try {
                tryFieldInjection(instance);
                trySetterInjection(instance);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void trySetterInjection(Object instance) throws InvocationTargetException, IllegalAccessException {
        if(m != null) {
            m.invoke(instance, provider.get());
        }
    }
    private void tryFieldInjection(Object instance) throws IllegalAccessException {
        if(f != null) {
            f.set(instance, provider.get());
        }
    }
}
