package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.DeletePage;
import pages.HomePage;

public class DeleteStepDef {

    private DeletePage deletePage;
    private WebDriver driver;

    public DeleteStepDef(Hook hook) throws Exception {
        this.driver = hook.getDriver();
        deletePage = new DeletePage(this.driver);
    }

    @When("^I click on the \"([^\"]*)\" \\(Delete\\) button for a todo$")
    public void iClickOnTheDeleteButtonForATodo(String arg0) throws Throwable {
        deletePage.clickOnDeleteTodoItem();
    }

    @Then("^the todo should be deleted from the todo list$")
    public void theTodoShouldBeDeletedFromTheTodoList() {
        deletePage.validateTodoItemDeletedFromList();
    }

    @And("^the number of todos in the list should decrease by one$")
    public void theNumberOfTodosInTheListShouldDecreaseByOne() {
        deletePage.validateTodoItemCount();
    }
}