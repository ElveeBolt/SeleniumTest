import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class FirstTest {
    String SERVICE_URL = "https://next.privat24.ua/payments/dashboard";
    String IBAN_NUMBER = "UA333510050000026005325079000";
    String SENDER_FIO = "Панченко Сергій Іванович";
    String RECIPIENT_NAME = "Панченко Світлана Іванівна";
    String RECIPIENT_OKPO = "123456789";
    String SENDER_DEST = "Google";
    String AMOUNT = "0.01";
    String SENDER_CARD_NUMBER = "4552331448138217";
    String SENDER_CARD_EXPIRED = "12/25";
    String SENDER_CARD_CVV = "123";
    String SENDER_CARD_FIRST_NAME = "TARAS";
    String SENDER_CARD_LAST_NAME = "SHEVCHENKO";


    By ibanNumber = By.xpath("//input[@data-qa-node='query']");
    By ibanButton = By.linkText("За реквізитами");
    By senderFio = By.xpath("//textarea[@data-qa-node='FIO']");
    By recipientName = By.xpath("//textarea[@data-qa-node='CUSTOM_COMPANY']");
    By recipientOkpo = By.xpath("//textarea[@data-qa-node='CUSTOM_OKPO']");
    By dest = By.xpath("//textarea[@data-qa-node='DEST']");
    By amount = By.xpath("//input[@data-qa-node='SUM']");
    By senderCardNumber = By.xpath("//input[@data-qa-node='numberdebitSource']");
    By senderCardCVV = By.xpath("//input[@data-qa-node='cvvdebitSource']");
    By senderCardExpired = By.xpath("//input[@data-qa-node='expiredebitSource']");
    By senderCardFirstName = By.xpath("//input[@data-qa-node='firstNamedebitSource']");
    By senderCardLastName = By.xpath("//input[@data-qa-node='lastNamedebitSource']");
    By submit = By.xpath("//form[@action='/']//button[@type='submit']");
    By cartButton = By.xpath("//button[contains(text(), \"Додати в кошик\")]");
    By assertDestDetails = By.xpath("//div[@data-qa-node='details']");
    By assertSum = By.xpath("//div[@data-qa-node='amount']");


    @Test
    public void checkMinPaymentSum(){
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get(SERVICE_URL);

        // Send iban
        driver.findElement(ibanNumber).sendKeys(IBAN_NUMBER);
        driver.findElement(ibanButton).click();

        // Add form data
        driver.findElement(senderFio).sendKeys(SENDER_FIO);
        driver.findElement(recipientName).sendKeys(RECIPIENT_NAME);
        driver.findElement(recipientOkpo).sendKeys(RECIPIENT_OKPO);
        driver.findElement(dest).sendKeys(SENDER_DEST);
        driver.findElement(amount).sendKeys(AMOUNT);
        driver.findElement(senderCardNumber).sendKeys(SENDER_CARD_NUMBER);
        driver.findElement(senderCardCVV).sendKeys(SENDER_CARD_CVV);
        driver.findElement(senderCardExpired).sendKeys(SENDER_CARD_EXPIRED);
        driver.findElement(senderCardFirstName).sendKeys(SENDER_CARD_FIRST_NAME);
        driver.findElement(senderCardLastName).sendKeys(SENDER_CARD_LAST_NAME);
        driver.findElement(submit).submit();

        // Add to cart
        driver.findElement(cartButton).click();

        // Assert
        Assert.assertEquals("Сплата за " + SENDER_DEST, driver.findElement(assertDestDetails).getText());
        Assert.assertEquals(AMOUNT + " UAH", driver.findElement(assertSum).getText());
    }

}