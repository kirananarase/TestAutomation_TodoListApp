package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.CompletedPage;
import pages.DeletePage;
import pages.HomePage;

public class CompletedStepDef {

    private CompletedPage completedPage;
    private HomePage homePage;
    private WebDriver driver;

    public CompletedStepDef(Hook hook) throws Exception {
        this.driver = hook.getDriver();
        completedPage = new CompletedPage(this.driver);
        homePage = new HomePage(this.driver);
    }

    @When("^I click on the \"([^\"]*)\" \\(Complete\\) checkbox for a todo$")
    public void iClickOnTheCompleteCheckboxForATodo(String arg0) throws Throwable {
        completedPage.clickOnTodoCompleteCheckBox();
    }

    @Then("^the todo should be marked as completed$")
    public void theTodoShouldBeMarkedAsCompleted(){
        completedPage.validateTodoMarkedAsCompleted();
    }

    @And("^the todo should appear in the completed todo list$")
    public void theTodoShouldAppearInTheCompletedTodoList() {
        completedPage.validateTodoMarkedAsCompleted();
    }


    @When("^I click on the \"([^\"]*)\" filter tab$")
    public void iClickOnTheFilterTab(String arg0) throws Throwable {
        completedPage.clickOnCompletedTodoListFilterButton();
    }

    @Then("^I should see only the completed todos in the todo list$")
    public void iShouldSeeOnlyTheCompletedTodosInTheTodoList() {
        completedPage.validateCompletedTodoListOnly();
    }

    @And("^the number of completed todos in the list should be correct$")
    public void theNumberOfCompletedTodosInTheListShouldBeCorrect() {
        completedPage.validateCompletedTodoListShouldBeCorrect();
    }

    @When("^I add more than (\\d+) todo items$")
    public void iAddMoreThanTodoItems(int arg0) {
    }

    @And("^I have completed more than (\\d+) todos$")
    public void iHaveCompletedMoreThanTodos(int arg0) {
        completedPage.clickOnMoreThanOneCompletedTodos();
    }

    @And("^I should see all the added completed todos\\.$")
    public void iShouldSeeAllTheAddedCompletedTodos() {
        completedPage.validateUserAbleToSeeCompletedExtraItems();
    }

    @And("^I should be able to see the extra completed items on the next page \"([^\"]*)\"$")
    public void iShouldBeAbleToSeeTheExtraCompletedItemsOnTheNextPage(String arg0) throws Throwable {
        homePage.validateUserAbleToNavigatePage(arg0);
        completedPage.validateUserAbleToSeeCompletedExtraItems();
    }
}