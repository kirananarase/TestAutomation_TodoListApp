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


public class CompletedPage {

    private WebDriver driver;
    ButtonHelper buttonHelper;
    TextBoxHelper textBoxHelper;
    Utils utils;


    public CompletedPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
        buttonHelper = new ButtonHelper(this.driver);
        textBoxHelper = new TextBoxHelper(this.driver);
        utils = new Utils(this.driver);
    }

    @FindBy(how = How.XPATH, using = "/html/body/div/div[2]")
    private WebElement todoStatus;

    @FindBy(how = How.XPATH, using = "//a[text()='Completed']")
    private WebElement completedTodoListBtn;

    public static By todoCompleteCheckBox = By.xpath("//input[contains(@class,'todo-status')]");

    public static By todoItemNames = By.xpath("//label[contains(@class,'todo-name')]");

    public static By allTodoList = By.xpath("//div[contains(@class, 'todo-item')]");

    public void clickOnTodoCompleteCheckBox()  {
        List<WebElement> todoCompleteCB = utils.getElements(todoCompleteCheckBox);
        todoCompleteCB.get(0).click();
        List<WebElement> todoItemListNames = utils.getElements(todoItemNames);
        Constants.TODO_NAME = todoItemListNames.get(0).getText();

    }

    public void validateTodoMarkedAsCompleted()  {
        Assert.assertEquals(todoStatus.getAttribute("class"),Constants.TODO_MARKED_COMPLETED_CLASS_NAME);
    }

    public void clickOnCompletedTodoListFilterButton(){
        buttonHelper.click(completedTodoListBtn);
        List<WebElement> completedTodoList = utils.getElements(allTodoList);
        for(int i=1; i< completedTodoList.size()-1;i++){
            if(completedTodoList.get(i).getAttribute("class").trim().equals(Constants.TODO_MARKED_COMPLETED_CLASS_NAME)) {
                Constants.COMPLETED_TODO_LIST_COUNT++;
            }
        }
    }

    public void validateCompletedTodoListOnly() {
        List<WebElement> completedTodoList = utils.getElements(allTodoList);
        for(int i=0; i< completedTodoList.size();i++){
            Assert.assertNotEquals(completedTodoList.get(i).getAttribute("class"),Constants.TODO_LIST_ITEMS_CLASS_NAME);
        }
    }

    public void validateCompletedTodoListShouldBeCorrect() {
        List<WebElement> completedTodo = utils.getElements(allTodoList);
        for(int i=1; i< completedTodo.size() -1;i++){
            if(completedTodo.get(i).getAttribute("class").trim().equals(Constants.TODO_MARKED_COMPLETED_CLASS_NAME)){
                Constants.COMPLETED_TODO_LIST_COUNT_ON_COMPLETED_TAB++;

            }
        }
        Assert.assertEquals(Constants.COMPLETED_TODO_LIST_COUNT, Constants.COMPLETED_TODO_LIST_COUNT_ON_COMPLETED_TAB);
    }

    public void clickOnMoreThanOneCompletedTodos() {
        List<WebElement> todoCheckbox = utils.getElements(todoCompleteCheckBox);
        for(int i=0; i< todoCheckbox.size()-1;i++){
            buttonHelper.click(todoCheckbox.get(i));
        }

    }

    public void validateUserAbleToSeeCompletedExtraItems() {
        List<WebElement> todoLst = utils.getElements(allTodoList);
        for(int i=1; i< todoLst.size()-1;i++){
            Assert.assertEquals(todoLst.get(i).getAttribute("class").trim(),Constants.TODO_MARKED_COMPLETED_CLASS_NAME);
        }
    }

//    public void validateUserAbleToSeeCompletedExtraItems() {
//        List<WebElement> todoLst = utils.getElements(allTodoList);
//        fl count = 8/5;
//
//        for(int i=1; i< ;i++){
//            for(int j=0 ; j<5 ; j++){
//                Assert.assertEquals(todoLst.get(i).getAttribute("class").trim(),Constants.TODO_MARKED_COMPLETED_CLASS_NAME);
//            }
//            if(i > 5){
//                next click;
//
//            }
//        }
//    }
}
