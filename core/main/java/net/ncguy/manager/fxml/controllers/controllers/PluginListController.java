package net.ncguy.manager.fxml.controllers.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import net.ncguy.manager.api.IPlugin;
import net.ncguy.manager.fxml.controllers.AbstractController;
import net.ncguy.manager.plugin.PluginRegistry;
import net.ncguy.manager.fxml.FXMLController;
import net.ncguy.manager.utils.Tuple;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

@FXMLController("/fxml/pluginManager/PluginList.fxml")
public class PluginListController extends AbstractController {

    @FXML
    public VBox container;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PluginRegistry.get().getLoadedPlugins().forEach(this::addPluginItem);
        PluginRegistry.get().getLoadedPlugins().forEach(this::addPluginItem);
    }

    private void addPluginItem(IPlugin plugin) {

        Optional<Tuple<Node, PluginItemSummaryController>> build = build(PluginItemSummaryController.class, ctrlr -> ctrlr.setPlugin(plugin));

        build.ifPresent(tuple -> container.getChildren()
                                          .add(tuple.t));

    }
}
