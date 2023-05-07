package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.HomePage;

public class HomeStepDef {

    private HomePage homepage;
    private WebDriver driver;

    public HomeStepDef(Hook hook) throws Exception {
        this.driver = hook.getDriver();
        homepage = new HomePage(driver);
    }

    @Given("^I am on \"([^\"]*)\" page$")
    public void iAmOnPage(String arg0) throws Throwable {
        homepage.iAmOnTheHomePage();
    }

    @When("^I click on \"([^\"]*)\" textbox$")
    public void iClickOnTextbox(String arg0) throws Throwable {
        homepage.clickOnAddNewButton();
    }

    @Then("^I should be able to enter the todo details \"([^\"]*)\", \"([^\"]*)\"$")
    public void iShouldBeAbleToEnterTheTodoDetails(String todoDetails, int todoCount) throws Throwable {
        homepage.enterTodoItemDetails(todoDetails,todoCount);
    }

    @When("^I press enter$")
    public void iPressEnter() {
        homepage.pressEnterToAddTodoItem();
    }

    @And("^I should be able to save the todo item$")
    public void iShouldBeAbleToSaveTheTodoItem() {
        homepage.validateUserShouldSavedTodoItem();
    }

    @And("^the todo should appear in the todos list \"([^\"]*)\"$")
    public void theTodoShouldAppearInTheTodosList(String todoDetails) throws Throwable {
        homepage.validateUserShouldSeeTodoItemInList(todoDetails);
    }

    @When("^I add more than (\\d+) todo items \"([^\"]*)\", \"([^\"]*)\"$")
    public void iAddMoreThanTodoItems(int arg0, String todoDetails, int count) throws Throwable {
        homepage.addMoreThan5TodoItems(todoDetails,count);
    }

    @Then("^I should be able to see the extra items on the next page \"([^\"]*)\"$")
    public void iShouldBeAbleToSeeTheExtraItemsOnTheNextPage(String navigationBtn) throws Throwable {
        homepage.validateUserAbleToNavigatePage(navigationBtn);
        homepage.validateUserAbleToSeeExtraItems();
    }
    @And("^I should be able to navigate to the next page \"([^\"]*)\"$")
    public void iShouldBeAbleToNavigateToTheNextPage(String navigationBtn) throws Throwable {
        homepage.validateUserAbleToNavigatePage(navigationBtn);
    }

    @And("^I should be able to navigate back to the previous page \"([^\"]*)\"$")
    public void iShouldBeAbleToNavigateBackToThePreviousPage(String navigationBtn) throws Throwable {
        homepage.validateUserAbleToNavigatePage(navigationBtn);
    }

    @And("^I should see all the added todo items$")
    public void iShouldSeeAllTheAddedTodoItems() {
        homepage.validateUserAbleToSeeExtraItems();
    }

    @And("^I should close the browser$")
    public void iShouldCloseTheBrowser() {
        homepage.closeBrowser();
    }
}