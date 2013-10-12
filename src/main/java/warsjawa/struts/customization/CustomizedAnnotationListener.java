package warsjawa.struts.customization;

import com.google.inject.Provider;
import com.google.inject.TypeLiteral;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * User: bartosz.michalik@gmail.com
 * Date: 12.10.13
 * Time: 17:15
 */
public class CustomizedAnnotationListener implements TypeListener {
    @Override
    public <I> void hear(TypeLiteral<I> type, TypeEncounter<I> encounter) {
        for(Field f : type.getRawType().getDeclaredFields()) {
            if(f.isAnnotationPresent(CustomAnnotation.class)) {
                Provider<?> injector = encounter.getProvider(f.getType());
                encounter.register(new CustomizedInjector<I>(f, injector));
            }
        }

        for(Method m : type.getRawType().getDeclaredMethods()) {
            //find annotated setter ;P
            if(Modifier.isPublic(m.getModifiers()) && m.isAnnotationPresent(CustomAnnotation.class)) {
                Class<?>[] types = m.getParameterTypes();
                if(types.length == 1) {
                    Provider<?> injector = encounter.getProvider(types[0]);
                    encounter.register(new CustomizedInjector<I>(m, injector));
                }
            }
        }
    }
}
