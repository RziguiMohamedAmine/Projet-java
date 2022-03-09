/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.stripe.exception.StripeException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import service.ServicePaymentStripe;

/**
 * FXML Controller class
 *
 * @author Houssem Charef
 */
public class PaymentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    ServicePaymentStripe servicePayment;
    int prix;
    @FXML
    private TextField numCardField;

    private boolean payed = false;
    @FXML
    private TextField expMoisField;
    @FXML
    private TextField expanneeField;
    @FXML
    private TextField cvvField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addTextLimiter(expMoisField, 2);
        addTextLimiter(expanneeField, 4);
        addTextLimiter(cvvField, 3);

    }

    @FXML
    private void Payer(ActionEvent event) {
        String nom = "sddsdsdsds";
        String email = "charef.housdddsem@gmail.com";
        String numCard = numCardField.getText();
        String expMois = expMoisField.getText();
        String exAnnee = expanneeField.getText();
        String cvv = cvvField.getText();
        BilletAjoutModifyController billetAjoutModifyController = null;

        servicePayment = new ServicePaymentStripe(email, nom, prix * 10, numCard, expMois, exAnnee, cvv);

        try {
            servicePayment.payer();

            FXMLLoader fxmlLoader = new FXMLLoader();
            Pane p = fxmlLoader.load(getClass().getResource("BilletAjoutModify.fxml").openStream());
            billetAjoutModifyController = fxmlLoader.getController();
            payed = true;

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("valid payment");
            alert.show();

        } catch (StripeException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("error payment");
            alert.show();

        } catch (IOException ex) {
            Logger.getLogger(PaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean getReturn() {
        return payed; //return what you want controlled by your controller class
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public static void addTextLimiter(final TextField tf, final int maxLength) {
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tf.getText().length() > maxLength) {
                    String s = tf.getText().substring(0, maxLength);
                    tf.setText(s);
                }
            }
        });
    }

}
