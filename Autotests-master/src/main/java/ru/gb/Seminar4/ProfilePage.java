package ru.gb.Seminar4;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.io.File;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.$x;

public class ProfilePage {
    private SelenideElement additionalInfoName = $x("//div[contains(text(), 'Full name')]/following-sibling::div");
    private SelenideElement underAvatarName = $x("//div[@class='mdc-typography--body2 smui-card__content']");
    private SelenideElement editingButton = $x("//button[@title = 'More options']");
    private SelenideElement newAvatarField = $x("//span[contains(text(), 'New Avatar')]/following-sibling::input");
    private SelenideElement saveButton = $x("//button[@type='submit']");

    public String getAdditionalInfoName() {
        return additionalInfoName.should(Condition.visible).getText();
    }
    public String getUnderAvatarName() {
        return underAvatarName.should(Condition.visible).getText();
    }
    public void editProfile(){
        editingButton.should(Condition.visible).click();
    }
    public void addAvatar(File file){
        newAvatarField.should(Condition.interactable).uploadFile(file);
        Selenide.sleep(1000L);
        saveButton.should(Condition.visible).click();

    }

    public String[] getFileName(){
        return Objects.requireNonNull(newAvatarField.getValue()).substring(12).split("\\.");
    }
}
