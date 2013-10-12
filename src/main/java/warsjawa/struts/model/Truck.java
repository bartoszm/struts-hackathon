package warsjawa.struts.model;

import warsjawa.struts.customization.CustomAnnotation;

/**
 * User: bartosz.michalik@gmail.com
 * Date: 12.10.13
 * Time: 17:49
 */
public class Truck implements Car {
    private Engine engine;

    @Override
    public void drive() {
        System.out.println("working:");
        engine.work();

    }
    @CustomAnnotation
    public void setEngine(Engine e) {
        this.engine = e;
    }
}
