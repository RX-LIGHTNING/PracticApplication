package com.example.practicapp;

import com.example.practicapp.objects.Provider;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.IOException;

public class ProviderRequestItemController {
    @FXML
    private Text OrgName;
    @FXML
    private Text raw;
    @FXML
    private Text raw1;
    @FXML
    private Text status;
    MainMenuController parentController;
    Provider provider;
    public void setData(Provider provider, MainMenuController mainMenuController) {
        parentController = mainMenuController;
        this.provider = provider;
        OrgName.setText(provider.getOrgname());
        raw.setText("Соль: "+provider.getSaltprice()+" руб/кг");
        raw1.setText("Пшеничная мука: "+provider.getWheatflourprice()+" руб/кг");
        status.setText("Статус: "+provider.getStatus());
    }
    public void cancelRequest() throws IOException {

    }
}
