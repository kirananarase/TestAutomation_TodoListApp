package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.ActivePage;
import pages.CompletedPage;
import pages.HomePage;

public class ActiveStepDef {

    private ActivePage activePage;
    private HomePage homePage;
    private WebDriver driver;

    public ActiveStepDef(Hook hook) throws Exception {
        this.driver = hook.getDriver();
        activePage = new ActivePage(this.driver);
        homePage = new HomePage(this.driver);
    }

    @When("^I click on the \"([^\"]*)\" \\(Complete\\) checkbox for a completed todo$")
    public void iClickOnTheCompleteCheckboxForACompletedTodo(String arg0) throws Throwable {
        activePage.clickOnChkBoxForCompletedTodo();
    }

    @Then("^the todo should be marked as active$")
    public void theTodoShouldBeMarkedAsActive() {
        activePage.validateTodoMarkedAsActive();
    }

    @And("^the todo should appear in the active todo list$")
    public void theTodoShouldAppearInTheActiveTodoList() {
        activePage.clickOnActiveTodoListAndValidateActiveTodoList();
    }

    @When("^I click on the \"([^\"]*)\" filter$")
    public void iClickOnTheFilter(String arg0) throws Throwable {
        activePage.clickOnActiveTodoListFilterButton();
    }

    @Then("^I should see only the active todos in the todo list$")
    public void iShouldSeeOnlyTheActiveTodosInTheTodoList() {
        activePage.validateActiveTodoInList();
    }

    @And("^the number of active todos in the list should be correct$")
    public void theNumberOfActiveTodosInTheListShouldBeCorrect() {
        activePage.validateActiveTodoListShouldBeCorrect();
    }

    @And("^I should see all the added active todos$")
    public void iShouldSeeAllTheAddedActiveTodos() {
        homePage.validateUserAbleToSeeExtraItems();
    }

}