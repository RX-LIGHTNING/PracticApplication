package com.example.practicapp;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

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
        raw.setText("Соль: "+provider.getSaltprice()+" руб/кг");
        raw1.setText("Пшеничная мука: "+provider.getWheatflourprice()+" руб/кг");
    }
}
