package com.example.practicapp;

import com.example.practicapp.objects.Provider;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.IOException;

public class ProviderMoreController {
    @FXML
    private Text orgname;

    @FXML
    private Text ryeflourprice;

    @FXML
    private Text saltprice;

    @FXML
    private Text wheatflourprice;

    @FXML
    private Text yeastprice;
    MainMenuController mainMenuController;
    Provider provider;
    public void setData(MainMenuController mainMenuController, Provider provider){
        this.mainMenuController = mainMenuController;
        this.provider=provider;
        orgname.setText(provider.getOrgname());
        saltprice.setText("Цена соли " + provider.getSaltprice());
        ryeflourprice.setText("Цена ржаной муки " + provider.getRyeflourprice());
        wheatflourprice.setText("Цена пшеничной муки " + provider.getWheatflourprice());
        yeastprice.setText("Цена Дрожжей " + (provider.getYeastprice()));
    }
    public void returnToProviders() throws IOException {
        mainMenuController.setProvidersPane();
    }
}
