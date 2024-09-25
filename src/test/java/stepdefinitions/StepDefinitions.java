package test.java.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.ExcelUtils;
import driver.DriverManager;

import java.util.List;
import java.util.Map;

public class StepDefinitions {
    @When("I search for each order in the store")
    public void searchForEachOrder() throws Exception {
        List<Map<String, String>> accountData = ExcelUtils.readAccountDataFromExcel();
        WebDriver driver = DriverManager.getDriver();

        for (Map<String, String> account : accountData) {
            driver.get("https://automationteststore.com/");
            String orderNumber = account.get("orderNumber");
            WebElement searchBox = driver.findElement(By.name("search"));
            searchBox.clear();
            searchBox.sendKeys(orderNumber);
            searchBox.submit();
            Thread.sleep(2000);
        }
    }

    @Then("I should see the correct order information")
    public void verifyOrderInformation() throws Exception {
        List<Map<String, String>> accountData = ExcelUtils.readAccountDataFromExcel();
        WebDriver driver = DriverManager.getDriver();

        for (Map<String, String> account : accountData) {
            String orderNumber = account.get("orderNumber");
            WebElement orderElement = driver.findElement(By.xpath("//div[contains(text(), '" + orderNumber + "')]"));
            Assert.assertNotNull(orderElement, "La información de la orden no es correcta para el número de orden: " + orderNumber);
        }
    }
}
