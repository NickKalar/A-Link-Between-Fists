package src.application;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.image.Image;

public class LBTMainMenu extends FXGLMenu {

    public LBTMainMenu() {
        super(MenuType.MAIN_MENU);

        var button = new LBTButton("Start New Game", this::fireNewGame);
        button.setTranslateX(FXGL.getAppWidth() / 2 - 200 / 2);
        button.setTranslateY(FXGL.getAppHeight() / 2 - 40 / 2);

        getMenuContentRoot().getChildren().add(button);
    }

    @Override
    protected Button createActionButton(String arg0, Runnable arg1) {
        return new Button();
    }

    @Override
    protected Button createActionButton(StringBinding arg0, Runnable arg1) {
        return new Button();
    }

    @Override
    protected Node createBackground(double width, double height) {
        return FXGL.texture("mainmenu.png", 24 * 64, 16 * 64);
    }

    @Override
    protected Node createProfileView(String arg0) {
        return new Text();
    }

    @Override
    protected Node createTitleView(String arg0) {
        return new Text();
    }

    @Override
    protected Node createVersionView(String arg0) {
        return new Text();
    }

    private static class LBTButton extends StackPane {
        public  LBTButton(String name, Runnable action) {

            var bg = new Rectangle(200, 40, Color.BLACK);
            // var bg2 = new BackgroundImage(new Image("textures/mainmenu.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
            // BackgroundSize.DEFAULT);
            bg.setStroke(Color.WHITE);

            var text = FXGL.getUIFactory().newText(name, 18);
            
            bg.fillProperty().bind(
                    Bindings.when(hoverProperty()).then(Color.WHITE).otherwise(Color.BLACK)
            );

            text.fillProperty().bind(
                    Bindings.when(hoverProperty()).then(Color.BLACK).otherwise(Color.WHITE)
            );

            setOnMouseClicked(e -> action.run());

            getChildren().addAll(bg, text);

        }
    }

}