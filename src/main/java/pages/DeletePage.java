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
import utils.Utils;

import java.util.ArrayList;
import java.util.List;


public class DeletePage {

    private WebDriver driver;
    ButtonHelper buttonHelper;
    TextBoxHelper textBoxHelper;
    Utils utils;

    public DeletePage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
        buttonHelper = new ButtonHelper(this.driver);
        textBoxHelper = new TextBoxHelper(this.driver);
        utils = new Utils(this.driver);
    }

    @FindBy(how = How.CLASS_NAME, using = "todo-delete")
    private WebElement todoDeleteBtn;

    @FindBy(how = How.CLASS_NAME, using = "todo-count")
    private WebElement todoItemCount;

    @FindBy(how = How.CLASS_NAME, using = "todo-name")
    private WebElement todoList;

    public void clickOnDeleteTodoItem(){
        getTodoItemsCount();
        buttonHelper.click(todoDeleteBtn);
    }

    public void getTodoItemsCount(){
        String todoItemDetails [] = todoItemCount.getText().split(" ");
        Constants.TODO_ITEM_COUNT = Integer.parseInt(todoItemDetails[0]);
    }

    public void validateTodoItemDeletedFromList(){
        ArrayList<String> arr = new ArrayList<>();
        arr.add(todoList.getText());
        Assert.assertTrue(todoList.isDisplayed());
    }

    public void validateTodoItemCount(){
        Assert.assertEquals(Integer.parseInt(todoItemCount.getText().split(" ")[0]),Constants.TODO_ITEM_COUNT - 1);
    }
}
