package stepDefinition;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class MyStepDefinitionBasketball {


    private WebDriver driver;

    public MyStepDefinitionBasketball() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.manage().window().maximize();

    }



    @Given("I am on the registration page")
    public void i_am_on_the_registration_page() {
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
    }


    @When("I fill in all required fields correctly including accepting the terms")
    public void iFillInAllRequiredFieldsCorrectlyIncludingAcceptingTheTerms() {
        String email = generateUniqueEmail();

        driver.findElement(By.id("dp")).sendKeys("26/10/1989");
        driver.findElement(By.id("member_firstname")).sendKeys("ali");
        driver.findElement(By.id("member_lastname")).sendKeys("ali");
        driver.findElement(By.xpath("//input[@id='member_emailaddress']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id='member_confirmemailaddress']")).sendKeys(email);
        driver.findElement(By.id("signupunlicenced_password")).sendKeys("aliali");
        driver.findElement(By.id("signupunlicenced_confirmpassword")).sendKeys("aliali");
        driver.findElement(By.cssSelector(".col-sm-4:nth-child(12) .box")).click();
        driver.findElement(By.cssSelector(".md-checkbox:nth-child(2) > label > .box")).click();
        driver.findElement(By.cssSelector(".md-checkbox > .md-checkbox:nth-child(1) .box")).click();
        driver.findElement(By.cssSelector(".md-checkbox:nth-child(7) .box")).click();
    }



    @When("I click on CONFIRM AND JOIN")
    public void i_click_on_confirm_and_join() {
        driver.findElement(By.xpath("//input[@value=\"CONFIRM AND JOIN\"]")).click();

    }



    @Then("I should be redirected to the account creation confirmation page")
    public void iShouldBeRedirectedToTheAccountCreationConfirmationPage() {
        String currentUrl = driver.getCurrentUrl();
        String expectedUrlPart = "/Account/SignUpConfirmation";
        Assert.assertTrue("Expected URL does not contain the expected part: " + expectedUrlPart,
                currentUrl.contains(expectedUrlPart));

    }

    @Then("a new account should be successfully created")
    public void aNewAccountShouldBeSuccessfullyCreated() {

        Assert.assertTrue(
                driver.findElement(By.xpath("//h2[text()='THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND']"))
                        .isDisplayed());
        driver.quit();

    }

    @When("I fill in all required fields correctly and missing last name")
    public void i_fill_in_all_required_fields_correctly_and_missing_last_name() {

        String email = generateUniqueEmail();

        driver.findElement(By.id("dp")).sendKeys("26/10/1989");
        driver.findElement(By.id("member_firstname")).sendKeys("ali");
        driver.findElement(By.xpath("//input[@id='member_emailaddress']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id='member_confirmemailaddress']")).sendKeys(email);
        driver.findElement(By.id("signupunlicenced_password")).sendKeys("aliali");
        driver.findElement(By.id("signupunlicenced_confirmpassword")).sendKeys("aliali");
        driver.findElement(By.cssSelector(".col-sm-4:nth-child(12) .box")).click();
        driver.findElement(By.cssSelector(".md-checkbox:nth-child(2) > label > .box")).click();
        driver.findElement(By.cssSelector(".md-checkbox > .md-checkbox:nth-child(1) .box")).click();
        driver.findElement(By.cssSelector(".md-checkbox:nth-child(7) .box")).click();

    }

    @Then("I should see an error message that says Last Name is required")
    public void i_should_see_an_error_message_that_says_last_name_is_required() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By lastNameErrorLocator = By.xpath("(//span[@class='warning field-validation-error'])[1]");

        WebElement lastNameErrorElement = wait
                .until(ExpectedConditions.visibilityOfElementLocated(lastNameErrorLocator));

        Assert.assertTrue("Error message for Last Name is required is not displayed",
                lastNameErrorElement.isDisplayed());
        driver.quit();

    }

    @When("I fill in all required fields correctly and miss match the password")
    public void i_fill_in_all_required_fields_correctly_and_miss_match_the_password() {

        String email = generateUniqueEmail();

        driver.findElement(By.id("dp")).sendKeys("26/10/1989");
        driver.findElement(By.id("member_firstname")).sendKeys("ali");
        driver.findElement(By.id("member_lastname")).sendKeys("ali");
        driver.findElement(By.xpath("//input[@id='member_emailaddress']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id='member_confirmemailaddress']")).sendKeys(email);
        driver.findElement(By.id("signupunlicenced_password")).sendKeys("aliali");
        driver.findElement(By.id("signupunlicenced_confirmpassword")).sendKeys("aliali123");
        driver.findElement(By.cssSelector(".col-sm-4:nth-child(12) .box")).click();
        driver.findElement(By.cssSelector(".md-checkbox:nth-child(2) > label > .box")).click();
        driver.findElement(By.cssSelector(".md-checkbox > .md-checkbox:nth-child(1) .box")).click();
        driver.findElement(By.cssSelector(".md-checkbox:nth-child(7) .box")).click();

    }

    @Then("I should see an error message that user password do not match")
    public void i_should_see_an_error_message_that_user_password_do_not_match() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By passwordMismatchErrorLocator = By.xpath("//span[text()='Password did not match']");

        WebElement passwordMismatchErrorElement = wait
                .until(ExpectedConditions.visibilityOfElementLocated(passwordMismatchErrorLocator));

        Assert.assertTrue("Error message for password mismatch is not displayed",
                passwordMismatchErrorElement.isDisplayed());
        driver.quit();

    }

    @When("I fill in all fields correctly but do not check the box to accept the terms")
    public void i_fill_in_all_fields_correctly_but_do_not_check_the_box_to_accept_the_terms() {

        String email = generateUniqueEmail();

        driver.findElement(By.id("dp")).sendKeys("26/10/1989");
        driver.findElement(By.id("member_firstname")).sendKeys("ali");
        driver.findElement(By.id("member_lastname")).sendKeys("ali");
        driver.findElement(By.xpath("//input[@id='member_emailaddress']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id='member_confirmemailaddress']")).sendKeys(email);
        driver.findElement(By.id("signupunlicenced_password")).sendKeys("aliali");
        driver.findElement(By.id("signupunlicenced_confirmpassword")).sendKeys("aliali");
        driver.findElement(By.cssSelector(".col-sm-4:nth-child(12) .box")).click();
        driver.findElement(By.cssSelector(".md-checkbox:nth-child(2) > label > .box")).click();
        driver.findElement(By.cssSelector(".md-checkbox:nth-child(7) .box")).click();

    }

    @Then("I should see an error message that says \"You must confirm that you have read and accepted our Terms and Conditions”")
    public void i_should_see_an_error_message_that_says_you_must_confirm_that_you_have_read_and_accepted_our_terms_and_conditions() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By termsErrorLocator = By
                .xpath("//span[text()='You must confirm that you have read and accepted our Terms and Conditions']");


        WebElement termsErrorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(termsErrorLocator));


        Assert.assertTrue("Error message for Terms and Conditions not confirmed is not displayed",
                termsErrorElement.isDisplayed());
        driver.quit();

    }


    private String generateUniqueEmail() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        String timestamp = dtf.format(now);

        Random rand = new Random();
        int randomNum = rand.nextInt(1000);

        return "user" + timestamp + "_" + randomNum + "@example.com";
    }


}
