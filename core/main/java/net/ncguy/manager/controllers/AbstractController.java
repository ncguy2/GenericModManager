package net.ncguy.manager.controllers;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import net.ncguy.manager.utils.FXMLController;
import net.ncguy.manager.utils.Tuple;
import net.ncguy.manager.utils.UIModelLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.function.Consumer;

public abstract class AbstractController implements Initializable {

    public static <T extends AbstractController> Optional<Tuple<Node, T>> build(Class<T> type) {
        return build(type, null);
    }

    public static <T extends AbstractController> Optional<Tuple<Node, T>> build(Class<T> type, Consumer<T> initTask) {
        if(!type.isAnnotationPresent(FXMLController.class)) {
            return Optional.empty();
        }

        FXMLController ctrlrAnnotation = type.getAnnotation(FXMLController.class);
        String fxmlPath = ctrlrAnnotation.value();

        InputStream resourceAsStream = type.getResourceAsStream(fxmlPath);
        try{
            Tuple<Node, T> load = UIModelLoader.load(resourceAsStream);
            if(load.u != null && initTask != null) {
                initTask.accept(load.u);
            }
            return Optional.of(load);
        }catch (IOException ioe) {
            return Optional.empty();
        }
    }

}
