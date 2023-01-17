import com.codeborne.selenide.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import java.time.Duration;
import static com.codeborne.selenide.Selenide.*;

public class CardTest {

    Main date = new Main();
    String deleteString = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE;


    @BeforeEach
    void openChrome() {
        open("http://localhost:9999/");
    }

    @Test
    void validValueTest() {
        $("[data-test-id=city] input").setValue("Краснодар");
//        $x("//div[contains(@class, 'menu')]").click();
        $("[data-test-id=date] input").setValue(deleteString);
        $("[data-test-id=date] input").setValue(date.threeDaysAfter());
        $("[data-test-id=name] input").setValue("Иван Петров-Иванов");
        $("[data-test-id=phone] input").setValue("+79998887766");
        $("[data-test-id=agreement]").click();
        $x("//span[contains(text(), 'Забронировать')]").click();
        $("[data-test-id=notification]").should(Condition.appear, Duration.ofMillis(14000));
    }

    @Test
    void invalidCity() {
        $("[data-test-id=city] input").setValue("dfsfd23r23f");
        $("[data-test-id=date] input").setValue(deleteString);
        $("[data-test-id=date] input").setValue(date.threeDaysAfter());
        $("[data-test-id=name] input").setValue("Иван Петров-Иванов");
        $("[data-test-id=phone] input").setValue("+79998887766");
        $("[data-test-id=agreement]").click();
        $x("//span[contains(text(), 'Забронировать')]").click();
        $("[data-test-id=city].input_invalid .input__sub").shouldBe(Condition.exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    void emptyCity() {
        $("[data-test-id=city] input").setValue("");
        $("[data-test-id=date] input").setValue(deleteString);
        $("[data-test-id=date] input").setValue(date.threeDaysAfter());
        $("[data-test-id=name] input").setValue("Иван Петров-Иванов");
        $("[data-test-id=phone] input").setValue("+79998887766");
        $("[data-test-id=agreement]").click();
        $x("//span[contains(text(), 'Забронировать')]").click();
        $("[data-test-id=city].input_invalid .input__sub").shouldBe(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void invalidName() {
        $("[data-test-id=city] input").setValue("Краснодар");
//        $x("//div[contains(@class, 'menu')]").click();
        $("[data-test-id=date] input").setValue(date.threeDaysAfter());
        $("[data-test-id=name] input").setValue("Ivan Petrov");
        $("[data-test-id=phone] input").setValue("+79998887766");
        $("[data-test-id=agreement]").click();
        $x("//span[contains(text(), 'Забронировать')]").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldBe(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void emptyName() {
        $("[data-test-id=city] input").setValue("Краснодар");
//        $x("//div[contains(@class, 'menu')]").click();
        $("[data-test-id=date] input").setValue(date.threeDaysAfter());
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79998887766");
        $("[data-test-id=agreement]").click();
        $x("//span[contains(text(), 'Забронировать')]").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldBe(Condition.exactText("Поле обязательно для заполнения"));
    }


    @Test
    void invalidDate() {
        $("[data-test-id=city] input").setValue("Краснодар");
//        $x("//div[contains(@class, 'menu')]").click();
        $("[data-test-id=name] input").setValue("Иван Петров-Иванов");
        $("[data-test-id=date] input").setValue(deleteString);
        $("[data-test-id=phone] input").setValue("+79998887766");
        $("[data-test-id=agreement]").click();
        $x("//span[contains(text(), 'Забронировать')]").click();
        $("[data-test-id=date] .input_invalid .input__sub").shouldBe(Condition.exactText("Неверно введена дата"));
    }

    @Test
    void invalidDateSecond() {
        $("[data-test-id=city] input").setValue("Краснодар");
//        $x("//div[contains(@class, 'menu')]").click();
        $("[data-test-id=name] input").setValue("Иван Петров-Иванов");
        $("[data-test-id=date] input").setValue(deleteString);
        $("[data-test-id=date] input").setValue("01.01.0001");
        $("[data-test-id=phone] input").setValue("+79998887766");
        $("[data-test-id=agreement]").click();
        $x("//span[contains(text(), 'Забронировать')]").click();
        $("[data-test-id=date] .input_invalid .input__sub").shouldBe(Condition.exactText("Заказ на выбранную дату невозможен"));
    }

    @Test
    void emptyPhone() {
        $("[data-test-id=city] input").setValue("Краснодар");
//        $x("//div[contains(@class, 'menu')]").click();
        $("[data-test-id=name] input").setValue("Иван Петров-Иванов");
        $("[data-test-id=date] input").setValue(deleteString);
        $("[data-test-id=date] input").setValue(date.threeDaysAfter()).pressEscape();
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $x("//span[contains(text(), 'Забронировать')]").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldBe(Condition.exactText("Поле обязательно для заполнения"));
    }

    @Test
    void invalidPhone() {
        $("[data-test-id=city] input").setValue("Краснодар");
//        $x("//div[contains(@class, 'menu')]").click();
        $("[data-test-id=name] input").setValue("Иван Петров-Иванов");
        $("[data-test-id=date] input").setValue(deleteString);
        $("[data-test-id=date] input").setValue(date.threeDaysAfter());
        $("[data-test-id=phone] input").setValue("+7999888776655");
        $("[data-test-id=agreement]").click();
        $x("//span[contains(text(), 'Забронировать')]").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldBe(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void checkboxMissed() {
        $("[data-test-id=city] input").setValue("Краснодар");
//        $x("//div[contains(@class, 'menu')]").click();
        $("[data-test-id=date] input").setValue(deleteString);
        $("[data-test-id=date] input").setValue(date.threeDaysAfter());
        $("[data-test-id=name] input").setValue("Иван Петров-Иванов");
        $("[data-test-id=phone] input").setValue("+79998887766");
        $x("//span[contains(text(), 'Забронировать')]").click();
        $("[data-test-id=agreement].input_invalid").should(Condition.exist);
    }

    @Test
    void notificationXMark() {
        $("[data-test-id=city] input").setValue("Краснодар");
//        $x("//div[contains(@class, 'menu')]").click();
        $("[data-test-id=date] input").setValue(deleteString);
        $("[data-test-id=date] input").setValue(date.threeDaysAfter());
        $("[data-test-id=name] input").setValue("Иван Петров-Иванов");
        $("[data-test-id=phone] input").setValue("+79998887766");
        $("[data-test-id=agreement]").click();
        $x("//span[contains(text(), 'Забронировать')]").click();
        $("[data-test-id=notification]").should(Condition.appear, Duration.ofMillis(14000));
        $("[data-test-id=notification] button .icon-button__content").click();
    }


                                    /*~~~~~~~~~~~~~~~~~~~ЗАДАНИЕ 2~~~~~~~~~~~~~~~*/


    @Test
    void calendarTest() {

        int dayOfNextMonth = 0;
        $("[data-test-id=date] span.icon").click();
        ElementsCollection month = $$x("//table//tr//td").exclude(Condition.empty);
        SelenideElement todayO = $x("//table//tr//td[contains(@class, 'calendar__day_state_today')]");
        int indexToday = month.indexOf(todayO);
        int monthDays = month.size();


        if (indexToday + 7 > monthDays) {
            dayOfNextMonth = indexToday + 7 - monthDays;
            $x("//div[@data-step=1]").click();
            $$x("//table//tr//td").get(dayOfNextMonth).click();
        } else {
            month.get(indexToday + 7).click();
        }

        $("[data-test-id=city] input").setValue("Краснодар");
        $("[data-test-id=name] input").setValue("Иван Петров-Иванов");
        $("[data-test-id=phone] input").setValue("+79998887766");
        $("[data-test-id=agreement]").click();
        $x("//span[contains(text(), 'Забронировать')]").click();
        $("[data-test-id=notification]").should(Condition.appear, Duration.ofMillis(14000));
    }

    @Test
    void calendarTestOnNextMonth() {

        int dayOfNextMonth = 0;
        $("[data-test-id=date] span.icon").click();
        ElementsCollection month = $$x("//table//tr//td").exclude(Condition.empty);
        SelenideElement todayO = $x("//table//tr//td[contains(@class, 'calendar__day_state_today')]");
        int indexToday = month.indexOf(todayO);
        int monthDays = month.size();


        if (30 + 7 > monthDays) {
            dayOfNextMonth = 30 + 7 - monthDays;
            $x("//div[@data-step=1]").click();
            $$x("//table//tr//td").get(dayOfNextMonth).click();
        } else {
            month.get(indexToday + 7).click();
        }

        $("[data-test-id=city] input").setValue("Краснодар");
        $("[data-test-id=name] input").setValue("Иван Петров-Иванов");
        $("[data-test-id=phone] input").setValue("+79998887766");
        $("[data-test-id=agreement]").click();
        $x("//span[contains(text(), 'Забронировать')]").click();
        $("[data-test-id=notification]").should(Condition.appear, Duration.ofMillis(14000));
    }

    @Test
    void fieldCityTestChooseKrasnodar() {

        $("[data-test-id=city] input").setValue("Кр");
        $x("//div//span[contains(text(), 'Краснодар')]").click();
        $("[data-test-id=date] input").setValue(deleteString);
        $("[data-test-id=date] input").setValue(date.threeDaysAfter());
        $("[data-test-id=name] input").setValue("Иван Петров-Иванов");
        $("[data-test-id=phone] input").setValue("+79998887766");
        $("[data-test-id=agreement]").click();
        $x("//span[contains(text(), 'Забронировать')]").click();
        $("[data-test-id=notification]").should(Condition.appear, Duration.ofMillis(14000));

    }

    @Test
    void fieldCityTestChooseKrasnodarSecond() {

        $("[data-test-id=city] input").setValue("Крсд");
        $x("//div//span[contains(text(), 'Краснодар')]").click();
        $("[data-test-id=date] input").setValue(deleteString);
        $("[data-test-id=date] input").setValue(date.threeDaysAfter());
        $("[data-test-id=name] input").setValue("Иван Петров-Иванов");
        $("[data-test-id=phone] input").setValue("+79998887766");
        $("[data-test-id=agreement]").click();
        $x("//span[contains(text(), 'Забронировать')]").click();
        $("[data-test-id=notification]").should(Condition.appear, Duration.ofMillis(14000));

    }
}
