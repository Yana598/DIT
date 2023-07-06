package page;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

public class CatalogPage {

    private Page page;

    public CatalogPage(Page page){
        this.page=page;
        this.catalogue = page.locator("a.dropdown-toggle");//заходим в каталог
    this.s1= page.locator("xpath=//img[@src='/catalogue/images/puma_1.jpeg']");
      this.s2=page.locator("xpath=//*[@id='products']/div[1]/div/div[2]/p[2]/a[2]");
        //this.s3= page.locator("xpath=//*[@id='products']/div[2]/div/div[2]/p[2]/a[2]']");
      this.addToCart= page.locator("xpath=//a[@id='buttonCart']");
        this.cart=page.locator("xpath=//span[@class='hidden-sm']");}

       private Locator catalogue;
       private Locator s1;
       private Locator s2;
       private Locator s3;
       private Locator addToCart;
       private Locator cart;

@Step("выбираем товары из каталога")
        public void selectS(){
            catalogue.click();
            s1.click();
            addToCart.click();
            s2.click();
            addToCart.click();
            //s3.click();
            //addToCart.click();
        }
    @Step("переходим в корзину")
        public void clickCart(){
            cart.click();
        }
}
