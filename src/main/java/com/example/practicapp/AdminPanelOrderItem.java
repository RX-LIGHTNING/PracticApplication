package com.example.practicapp;

import com.example.practicapp.objects.Order;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import java.awt.Desktop;
import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

public class AdminPanelOrderItem {

    AdminPaneController mainMenuController;
    Order order;
    @FXML
    private Text ProductName;
    @FXML
    private Text StatusBar;
    @FXML
    private Text quantityBar;
    @FXML
    private Text OrgText;
    @FXML
    private Text ContactText;
    public void setData(Order order, AdminPaneController mainMenuController) {
        this.mainMenuController = mainMenuController;
        this.order = order;
        ProductName.setText(order.getProduct());
        StatusBar.setText("Статус: "+order.getStatus());
        quantityBar.setText("Кол-во: "+order.getQuantity()+"руб/шт");
        ContactText.setText("Контакты"+order.getContact());
        OrgText.setText("Организация"+order.getOrganzition());
    }
    public void CancelOrder() throws IOException {
        DatabaseController.OrderCancel(order.getId(),-1);
        mainMenuController.showOrdersPane();
    }
    public void VerifyOrder() throws IOException {
        DatabaseController.OrderCancel(order.getId(),1);
        mainMenuController.showOrdersPane();
    }
    public void FinishOrder() throws IOException {
        DatabaseController.OrderCancel(order.getId(),2);
        mainMenuController.showOrdersPane();
    }
    public void PrintAgreement() throws IOException, InvalidFormatException {
                XWPFDocument document = new XWPFDocument(OPCPackage.open(String.valueOf(Objects.requireNonNull(getClass().getResource("Agreement.docx")).getPath())));
                for (XWPFParagraph paragraph : document.getParagraphs()) {
                    for (XWPFRun run : paragraph.getRuns()) {
                        String text = run.getText(0);
                        text = text.replace("name", order.getOrganzition());
                        text = text.replace("product", order.getProduct());
                        text = text.replace("quantity", String.valueOf(order.getQuantity()));
                        run.setText(text,0);
                    }
                }
                document.write(new FileOutputStream("output.docx"));
                try {
                    if (Desktop.isDesktopSupported()) {
                        Desktop.getDesktop().open(new File("output.docx"));
                    }
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
        }
    }

