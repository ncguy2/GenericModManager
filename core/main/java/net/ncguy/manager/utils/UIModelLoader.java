package net.ncguy.manager.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;
import java.io.InputStream;

public class UIModelLoader {

    public static <T> Tuple<Node, T> load(InputStream resource) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Object load = loader.load(resource);
        return new Tuple<>((Node) load, loader.getController());
    }

}
