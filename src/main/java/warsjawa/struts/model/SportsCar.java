package warsjawa.struts.model;

import warsjawa.struts.customization.CustomAnnotation;

/**
 * User: bartosz.michalik@gmail.com
 * Date: 12.10.13
 * Time: 16:37
 */
public class SportsCar implements Car {
    @CustomAnnotation
    private Engine engine;

    @Override
    public void drive() {
        engine.work();
    }
}
