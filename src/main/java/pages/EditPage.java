package pages;

import helper.ButtonHelper;
import helper.Constants;
import helper.TextBoxHelper;
import helper.WaitHelper;
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
import java.util.concurrent.TimeUnit;


public class EditPage {

    private WebDriver driver;
    ButtonHelper buttonHelper;
    TextBoxHelper textBoxHelper;
    Utils utils;

    public EditPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
        buttonHelper = new ButtonHelper(this.driver);
        textBoxHelper = new TextBoxHelper(this.driver);
        utils = new Utils(this.driver);
    }

    @FindBy(how = How.CLASS_NAME, using = "todo-name")
    private WebElement todoItemNameToClick;

    @FindBy(how = How.XPATH, using = "//*[contains(text(),'Edit To do Item')]")
    private WebElement editTodoItemText;

    public void clickOnTodoItemNameToEdit(){
        buttonHelper.click(todoItemNameToClick);
        Assert.assertEquals(editTodoItemText.getText(),"Edit To do Item");
    }

}
