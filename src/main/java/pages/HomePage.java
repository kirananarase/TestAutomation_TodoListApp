package pages;

import helper.ButtonHelper;
import helper.Constants;
import helper.TextBoxHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import resources.ConfigFileReader;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;


public class HomePage {

    private WebDriver driver;
    ButtonHelper buttonHelper;
    TextBoxHelper textBoxHelper;
    Utils utils;
    ConfigFileReader configFileReader = new ConfigFileReader();


    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
        buttonHelper = new ButtonHelper(this.driver);
        textBoxHelper = new TextBoxHelper(this.driver);
        utils = new Utils(this.driver);
    }

    @FindBy(how = How.CLASS_NAME, using = "todo-name")
    private WebElement todoList;

    @FindBy(how = How.ID, using = "task")
    private WebElement addNewTodoItemBtn;

    @FindBy(how = How.XPATH, using = "//h1[contains(text(),'My Todo App')]")
    private WebElement myTodoHomePageTitle;

    public void iAmOnTheHomePage() {
        driver.get(configFileReader.getWebsite());
        Assert.assertEquals(Constants.MY_TODO_APP, myTodoHomePageTitle.getText());
    }

    public void clickOnAddNewButton(){
        buttonHelper.click(addNewTodoItemBtn);
    }

    public void enterTodoItemDetails(String todoDetails, int count){
        if(count > 1){
            for(int i=1;i<= count ; i++){
                Constants.TODO_ITEM_DETAILS = utils.generateRandomString(3) + "_" + todoDetails;
                addNewTodoItemBtn.sendKeys(Constants.TODO_ITEM_DETAILS);
                addNewTodoItemBtn.sendKeys(Keys.ENTER);
            }
        } else {
            Constants.TODO_ITEM_DETAILS = utils.generateRandomString(3) + "_" + todoDetails;
            addNewTodoItemBtn.sendKeys(Constants.TODO_ITEM_DETAILS);
        }

    }

    public void pressEnterToAddTodoItem(){
        addNewTodoItemBtn.sendKeys(Keys.ENTER);
    }

    public void validateUserShouldSavedTodoItem(){
        Assert.assertTrue(addNewTodoItemBtn.isDisplayed());
    }

    public void validateUserShouldSeeTodoItemInList(String todoItem){
        ArrayList<String> arr = new ArrayList<>();
        arr.add(todoList.getText());
        for(int i=0; i< arr.size();i++){
            if(arr.get(i).equalsIgnoreCase(todoItem)){
                Assert.assertEquals(todoItem,todoList.getText());
            }
        }
    }

    public void addMoreThan5TodoItems(String todoDetails, int count){
        enterTodoItemDetails(todoDetails,count);
    }

    public void validateUserAbleToNavigatePage(String navigationValue){
        buttonHelper.click(By.xpath("//a[text()='"+navigationValue+"']"));
    }

    public void validateUserAbleToSeeExtraItems() {
        List<WebElement> extraItemsLst = utils.getElements(CompletedPage.allTodoList);
        for(int i=1; i< extraItemsLst.size()-1;i++){
            Assert.assertEquals(extraItemsLst.get(i).getAttribute("class").trim(),Constants.TODO_LIST_ITEMS_CLASS_NAME);
        }
    }
}
