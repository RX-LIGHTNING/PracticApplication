package com.example.practicapp;

import com.example.practicapp.objects.Provider;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.IOException;

public class ProviderItemController {
    @FXML
    private Text OrgName;
    @FXML
    private Text raw;
    @FXML
    private Text raw1;
    MainMenuController parentController;
    Provider provider;
    public void setData(Provider provider, MainMenuController mainMenuController) {
        parentController = mainMenuController;
        this.provider = provider;
        OrgName.setText(provider.getOrgname());
        raw.setText(provider.getIngredientname()+": "+ provider.getPrice() +"руб/кг");
    }
    public void setProviderMorePane() throws IOException {
        parentController.setProviderMorePane(provider.getId());
    }
}
