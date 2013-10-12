package warsjawa.struts;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.matcher.Matchers;
import warsjawa.struts.customization.CustomizedAnnotationListener;
import warsjawa.struts.model.*;

/**
 * User: bartosz.michalik@gmail.com
 * Date: 12.10.13
 * Time: 16:39
 */
public class Showcase extends AbstractModule {


    @Override
    protected void configure() {
        bind(Car.class).to(Truck.class);
        bind(Engine.class).to(GasEngine.class);
        bindListener(Matchers.any(), new CustomizedAnnotationListener());
    }

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new Showcase());
        Car car = injector.getInstance(Car.class);
        car.drive();
        SportsCar car2 = new SportsCar();
        injector.injectMembers(car2);
        car2.drive();

        Truck truck = new Truck();
        injector.injectMembers(truck);
        truck.drive();
    }
}
