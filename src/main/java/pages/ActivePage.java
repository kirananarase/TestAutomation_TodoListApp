package pages;

import helper.ButtonHelper;
import helper.Constants;
import helper.TextBoxHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.Utils;

import java.util.List;


public class ActivePage {

    private WebDriver driver;
    ButtonHelper buttonHelper;
    TextBoxHelper textBoxHelper;
    Utils utils;

    public ActivePage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
        buttonHelper = new ButtonHelper(this.driver);
        textBoxHelper = new TextBoxHelper(this.driver);
        utils = new Utils(this.driver);
    }

    @FindBy(how = How.XPATH, using = "//a[text()='Active']")
    private WebElement activeTodoListBtn;

    public static By todoCompleteCheckBox = By.xpath("//input[contains(@class,'todo-status')]");
    public static By todoItemNames = By.xpath("//label[contains(@class,'todo-name')]");

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'todo-item')]")
    private WebElement todoItemsList;

    public void clickOnChkBoxForCompletedTodo(){
        List<WebElement> todoCheckbox = utils.getElements(todoCompleteCheckBox);
        todoCheckbox.get(0).click();
    }

    public void clickOnActiveTodoListAndValidateActiveTodoList(){
        buttonHelper.click(activeTodoListBtn);
        List<WebElement> todoNames = utils.getElements(todoItemNames);
        Assert.assertEquals(todoNames.get(0).getText(),Constants.TODO_NAME);
    }

    public void validateTodoMarkedAsActive() {
        Assert.assertTrue(todoItemsList.isDisplayed());
    }

    public void clickOnActiveTodoListFilterButton(){
        buttonHelper.click(activeTodoListBtn);
        List<WebElement> allActiveTodoList = utils.getElements(CompletedPage.allTodoList);
        for(int i=1; i< allActiveTodoList.size()-1;i++){
            if(allActiveTodoList.get(i).getAttribute("class").trim().equals(Constants.TODO_LIST_ITEMS_CLASS_NAME)) {
                Constants.ACTIVE_TODO_LIST_COUNT++;
            }
        }
    }

    public void validateActiveTodoInList() {
        List<WebElement> activeTodoList = utils.getElements(CompletedPage.allTodoList);
        for(int i=0; i< activeTodoList.size();i++){
            Assert.assertNotEquals(activeTodoList.get(i).getAttribute("class"),Constants.TODO_MARKED_COMPLETED_CLASS_NAME);
        }
    }

    public void validateActiveTodoListShouldBeCorrect() {
        List<WebElement> activeTodoList = utils.getElements(CompletedPage.allTodoList);
        for(int i=1; i< activeTodoList.size() -1;i++){
            if(activeTodoList.get(i).getAttribute("class").trim().equals(Constants.TODO_LIST_ITEMS_CLASS_NAME)){
                Constants.ACTIVE_TODO_LIST_COUNT_ON_ACTIVE_TAB++;

            }
        }
        Assert.assertEquals(Constants.ACTIVE_TODO_LIST_COUNT, Constants.ACTIVE_TODO_LIST_COUNT_ON_ACTIVE_TAB);
    }
}
