package warsjawa.struts.model;

/**
 * User: bartosz.michalik@gmail.com
 * Date: 12.10.13
 * Time: 16:38
 */
public class GasEngine implements Engine {
    public GasEngine() {

    }

    @Override
    public void work() {
        System.out.println("i am gas engine");
    }
}
