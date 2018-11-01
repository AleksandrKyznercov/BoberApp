package CustomControlObject;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

public class VBoxCell extends VBox {
    public int treatyID;
    Label label1 = new Label();
    Label label2 = new Label();
    Circle circle = new Circle();

    public VBoxCell(int treatyID,String labelText, String labelText2, Color circleColor) {
        super();
        this.treatyID = treatyID;
        label1.setText(labelText);
        label2.setText(labelText2);
        label1.setFont(Font.font(14));
        label2.setFont(Font.font(8));
        label1.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(label1, Priority.ALWAYS);
        HBox.setHgrow(label2, Priority.NEVER);
        HBox.setMargin(label2, new Insets(0,0,0,30));
        circle.setFill(circleColor);
        circle.setRadius(10);
        HBox hBox = new HBox();
        hBox.getChildren().addAll(label1, circle);
        this.getChildren().addAll(hBox, label2);
    }

    public int getTreatyID() {
        return treatyID;
    }

    public void setTreatyID(int treatyID) {
        this.treatyID = treatyID;
    }
}
