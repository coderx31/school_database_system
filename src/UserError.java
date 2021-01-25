import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class UserError {
    public static void UserErrorDisplay(String msg) throws FileNotFoundException {
        /*Shadow effects*/
        DropShadow upperDrop = new DropShadow();
        upperDrop.setBlurType(BlurType.THREE_PASS_BOX);
        upperDrop.setColor(Color.rgb(255,255,255,0.05));
        upperDrop.setOffsetX(-5);
        upperDrop.setOffsetY(-5);

        DropShadow belowDrop = new DropShadow();
        belowDrop.setBlurType(BlurType.THREE_PASS_BOX);
        belowDrop.setColor(Color.rgb(0,0,0,0.5));
        belowDrop.setOffsetY(5);
        belowDrop.setOffsetX(5);

        InnerShadow upper = new InnerShadow();
        upper.setBlurType(BlurType.THREE_PASS_BOX);
        upper.setColor(Color.rgb(255,255,255,0.05));
        upper.setOffsetX(-2);
        upper.setOffsetY(-2);

        InnerShadow below = new InnerShadow();
        below.setBlurType(BlurType.THREE_PASS_BOX);
        below.setColor(Color.rgb(0,0,0,0.8));
        below.setOffsetY(2);
        below.setOffsetX(2);


        Stage errorBox = new Stage();
        AnchorPane alertBox = new AnchorPane();
        alertBox.setEffect(upper);
        upper.setInput(below);
        alertBox.setId("alert-box");
        FileInputStream warnFile = new FileInputStream("Images/warn.png");
        Image warnImage = new Image(warnFile);
        Node warnView = new ImageView(warnImage);
        Label errorMsg = new Label(msg,warnView);
        errorMsg.setContentDisplay(ContentDisplay.RIGHT);
        errorMsg.setGraphicTextGap(25);
        errorMsg.setId("error-msg");
        errorMsg.setLayoutX(120);
        errorMsg.setLayoutY(50);
        Button ok = new Button("OK");
        ok.setId("ok-btn");
        ok.setLayoutX(170);
        ok.setLayoutY(120);
        ok.setEffect(upperDrop);
        upperDrop.setInput(belowDrop);
        ok.setOnMousePressed(e ->{
            ok.setEffect(upper);
            upper.setInput(below);
        });
        ok.setOnMouseReleased(e ->{
            ok.setEffect(upperDrop);
            upperDrop.setInput(belowDrop);
        });
        ok.setOnMouseClicked( e -> errorBox.close());
        alertBox.getChildren().addAll(errorMsg,ok);
        Scene errorScene = new Scene(alertBox,500,200);
        errorBox.setScene(errorScene);
        errorScene.getStylesheets().add("/CSS/userErrorStyle.css");
        errorScene.setFill(Color.TRANSPARENT);
        errorBox.initStyle(StageStyle.TRANSPARENT);
        errorBox.initModality(Modality.APPLICATION_MODAL);
        errorBox.showAndWait();

    }


}
