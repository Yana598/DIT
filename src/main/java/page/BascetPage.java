package page;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.List;


public final class BascetPage  {

    private Page page;

    public BascetPage(Page page){
        this.page=page;
        this.cartPricesTotal=page.locator("//tbody[@id='cart-list']//td[6]");
        this.dellElementBasket=page.querySelectorAll("//i[@class='fa fa-trash-o']");
        this.orderSubTotal=page.locator("//th[@id='orderSubtotal']");
        this.dellElementBasketOne=page.querySelector("//i[@class='fa fa-trash-o']");
    }
    // Получение списка цен товаров в корзине
    private final Locator cartPricesTotal;

    // колеекция кнопок удаления товара с корзины
    private final List dellElementBasket;

    // Кнопока удаления товара с корзины
    private final ElementHandle dellElementBasketOne;

    // Промежуточный итог заказа - цена товара без учера доставки
    private final Locator orderSubTotal;

    @Step("Navigate to the bascetPage")
    public BascetPage open() {
        page.navigate("http://localhost");
        page.locator("xpath=//span[@class='hidden-sm']").click();
        return this;
    }

    @Step("Очистить элементы корзины")
    public void dellElementBasket() {

        for (int i = 0; i < dellElementBasket.size(); i++) {
            dellElementBasketOne.click();

            if (dellElementBasket.size() != 0) {
                dellElementBasketOne.click();
            }
        }
    }

    @Step("Получение Промежуточный итог заказа actualPrise")
    public Double actualPrise() {
        return Double.parseDouble(orderSubTotal.textContent().replaceAll("\\$", ""));
    }

    @Step("Подчет суммы выбранных товаров в колонке Total expectedPrise")
    public double totalColumnPrise() {

        try {
            Thread.sleep(1000); //задержка на 1 секунду
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ArrayList<String> prises = new ArrayList<>();
        List<Double> cartPrises = new ArrayList<>();
        double expectedPrise = 0;

        for (int i = 0; i < cartPricesTotal.all().size(); i++) {
            prises.add(cartPricesTotal.all().get(i).textContent().replaceAll("\\$", ""));
            cartPrises.add(Double.parseDouble(prises.get(i)));

            expectedPrise += cartPrises.get(i);
        }
        return expectedPrise;
    }
}

