package page;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

public  class LoginPage{
    private Page page;
    private Locator btnLogin;

    // Поле имя пользователя
    private Locator inputUserName;

    // Поле пароль пользователя
    private Locator inputPassword;

    // Кнопка регистрации
    private Locator btnRegister;



    public LoginPage(Page page){
        this.page=page;
         this.btnLogin = page.locator("xpath=//li[@id='login']");

        // Поле имя пользователя
        this.inputUserName = page.locator("xpath=//input[@id='username-modal']");

        // Поле пароль пользователя
        this.inputPassword = page.locator("xpath=//input[@id='password-modal']");

        // Кнопка регистрации
        this.btnRegister = page.locator("xpath=//button[@onclick='return login()']");

    }


    @Step("Navigate to  login page")
    public void open() {
        page.navigate("http://localhost");
        page.locator("xpath=//li[@id='register']").click();

    }
    @Step("ввод email")
    public void typeEmail(String email) {
        page.fill("input#register-email-modal.form-control", email);

    }
    @Step("ввод пароля")
    public void typePassword(String password) {
        page.fill("input#register-password-modal.form-control", password);


    }

    @Step("ввод юзернайм")
    public void typeUsername(String username) {
        Locator locator=page.locator("input#register-username-modal.form-control");
        locator.fill(username);


    }

    @Step("ввод firstname")
    public void typeFirstname(String name) {
        page.fill("input#register-first-modal.form-control", name);


    }

    @Step("ввод пароля")
    public void typeLastname(final String name) {
        page.fill("input#register-last-modal.form-control", name);


    }

    @Step("клик на кнопку регистрации")
    public void submitLogin() {
        page.click("xpath=//button[@onclick='return register()']");
    }

    @Step("регистрация в системе")
    public void registerUser(final String username, final String password, final String email,
                             final String firstName, final String lastName) {
        open();
        typeUsername(username);
        typePassword(password);
        typeEmail(email);
        typeFirstname(firstName);
        typeLastname(lastName);
        submitLogin();
    }

    @Step("Авторизация пользователем")
    public void authorizationUser(String userName, String password) {
        btnLogin.click();
        inputUserName.fill(userName);
        inputPassword.fill(password);
        btnRegister.click();
    }
}
