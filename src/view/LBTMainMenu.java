package src.view;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import javafx.beans.binding.StringBinding;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.image.Image;

public class LBTMainMenu extends FXGLMenu {

    public LBTMainMenu() {
        super(MenuType.MAIN_MENU);

        LBTButton btnNewGame = new LBTButton("/assets/textures/new-game.png", () -> fireNewGame());
        LBTButton btnSettings = new LBTButton("/assets/textures/settings.png", () -> {});
        LBTButton btnExit = new LBTButton("/assets/textures/exit.png", () -> fireExit());

        VBox box = new VBox(15, btnNewGame, btnSettings, btnExit);
        box.setAlignment(Pos.CENTER);
        box.setTranslateX(FXGL.getAppWidth() / 2 - 200 / 2);
        box.setTranslateY(FXGL.getAppHeight() / 2 - 40 / 2);

        getMenuContentRoot().getChildren().addAll(box);
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

        private String name;
        private Runnable action;

        private Rectangle rec;

        public LBTButton(String n, Runnable a) {

            this.name = n;
            this.action = a;

            rec = new Rectangle(300, 60, Color.BLACK);
            Image img = new Image(name);
            rec.setFill(new ImagePattern(img));
            setAlignment(rec, Pos.CENTER);

            setOnMouseClicked(e -> action.run());

            getChildren().addAll(rec);// , imgv);

        }
    }

}