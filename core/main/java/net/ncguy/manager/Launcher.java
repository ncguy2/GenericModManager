package net.ncguy.manager;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.ncguy.manager.controllers.AbstractController;
import net.ncguy.manager.controllers.RootWindowController;
import net.ncguy.manager.utils.Tuple;

import java.util.Optional;

public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Optional<Tuple<Node, RootWindowController>> build = AbstractController.build(RootWindowController.class);

        if(build.isPresent()) {
            Tuple<Node, RootWindowController> nodeRootWindowControllerTuple = build.get();
            Node t = nodeRootWindowControllerTuple.t;

            Scene scene = new Scene((Parent) t);
            primaryStage.setScene(scene);
            primaryStage.show();
        }


    }
}
