package ru.netology.service;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class BankCardTest {
    @Test
    void shouldSubmitRequest() {
        open("http://localhost:9999");
        SelenideElement form= $(".form");
        form.$("[data-test-id=name] input").setValue("Федор Федор-Федор");
        form.$("[data-test-id=phone] input").setValue("+78787878787");
        form.$("[data-test-id=agreement]").click();
        form.$("[type=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
    @Test
    void shouldNotSubmitRequest() {
        open("http://localhost:9999");
        SelenideElement form= $(".form");
        form.$("[data-test-id=name] input").setValue("Fred");
        form.$("[data-test-id=phone] input").setValue("787878");
        form.$("[data-test-id=agreement]").click();
        form.$("[type=button]").click();
        $("[data-test-id=name] .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }
    @Test
    void shouldNotSubmitEmptyRequest() {
        open("http://localhost:9999");
        SelenideElement form= $(".form");
        form.$("[data-test-id=name] input").setValue("");
        form.$("[data-test-id=phone] input").setValue("");
        form.$("[data-test-id=agreement]").click();
        form.$("[type=button]").click();
        $("[data-test-id=name] .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }
    @Test
    void shouldNotSubmitEmptyCheckboxRequest() {
        open("http://localhost:9999");
        SelenideElement form= $(".form");
        form.$("[data-test-id=name] input").setValue("Федор Федор-Федор");
        form.$("[data-test-id=phone] input").setValue("+78787878787");
        form.$("[type=button]").click();
        $("[data-test-id=agreement] .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }
}
