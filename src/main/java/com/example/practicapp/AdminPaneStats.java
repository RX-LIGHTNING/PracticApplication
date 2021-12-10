package com.example.practicapp;
import com.example.practicapp.objects.Order;
import com.example.practicapp.objects.Product;
import javafx.fxml.FXML;
import javafx.scene.chart.*;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class AdminPaneStats {
    @FXML
    private BarChart<String, Integer> OrdersBC;

    @FXML
    private PieChart OrdersPieChart;

    @FXML
    private StackedAreaChart<Double, Date> indirectCost;
    AdminPaneController adminPaneController;
    public void setData(AdminPaneController adminPaneController) {
        this.adminPaneController = adminPaneController;
        List<Order> temp= DatabaseController.getAllOrders();
        int count = 0;
        Date recentDate = temp.get(0).getDate();
        XYChart.Series<String, Integer> series1 = new XYChart.Series<>();
        for (int i = 0; i < temp.size(); i++) {
            if(Objects.equals(recentDate, temp.get(i).getDate())){
                count++;
            }
            else {
                count = 1 ;
                recentDate = temp.get(i).getDate();
            }
            series1.setName("2021");
            series1.getData().add(new XYChart.Data<String, Integer>(temp.get(i).getDate().toString(), count));

        }
        OrdersBC.getData().add(series1);
        List<Product> temp2 = DatabaseController.getProducts();

        for (int i = 0; i < temp2.size(); i++) {
            int quantity = 0;
            for (int j = 0; j < temp.size(); j++) {
                if(Objects.equals(temp2.get(i).getName(),temp.get(j).getProduct())){
                    quantity++;
                }
            }
            if(quantity!=0) {
                PieChart.Data slice = new PieChart.Data(temp2.get(i).getName(), quantity);
                OrdersPieChart.getData().add(slice);
            }
        }

    }


}
