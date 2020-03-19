import Controller.PolynomialController;
import Model.Polynomial;
import Model.PolynomialCalculator;
import Model.PolynomialModel;
import View.PolynomialView;

import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainClass {
    public static void main(String[] args) {
        PolynomialModel model = new PolynomialModel();
        PolynomialView view = new PolynomialView(model);
        PolynomialController controller = new PolynomialController(model,view);
    }
}
