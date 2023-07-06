import com.microsoft.playwright.Locator;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import page.BascetPage;
import page.CatalogPage;
import page.LoginPage;
import user.User;
import user.UserGenerator;


@Feature("Login Test")
public class LoginE2ETest extends BaseE2ETest {

    @Description(
            "Test that verifies user gets redirected to 'Products' page after submitting correct login credentials")
    @Test
    public void testCorrectRegisterCredentials() {
        //создаем юзера
        User user =new UserGenerator().getFakerUser();
        //регистрируем юзера в магазине
        LoginPage loginPage = new LoginPage(page);
       loginPage.open();
        loginPage.registerUser(user.getUsername(),user.getPassword(),user.getEmail(),user.getFirstname(),user.getLastname());
        //выходим из аккаунта
        page.locator("xpath=//a[@onclick='logout()']").click();
        //авторизумся
        loginPage.authorizationUser(user.getUsername(),user.getPassword());
        //выбираем товары для покупок
        CatalogPage catalogPage=new CatalogPage(page);
        catalogPage.selectS();
        catalogPage.clickCart();
        //открываем корзину, проверяем счетчик
        BascetPage bascetPage=new BascetPage(page);
        System.out.println(bascetPage.totalColumnPrise());
        System.out.println(bascetPage.actualPrise());
        Assertions.assertEquals(bascetPage.totalColumnPrise(), bascetPage.actualPrise(), 0.0001);


    }




}
