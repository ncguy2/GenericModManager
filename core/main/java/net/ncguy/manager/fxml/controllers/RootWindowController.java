package net.ncguy.manager.fxml.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import net.ncguy.manager.api.IPlugin;
import net.ncguy.manager.api.IUIFactoryTask;
import net.ncguy.manager.fxml.FXMLController;
import net.ncguy.manager.plugin.PluginRegistry;

import java.net.URL;
import java.util.ResourceBundle;

@FXMLController("/fxml/core/RootWindow.fxml")
public class RootWindowController extends AbstractController {

    @FXML
    public TabPane rootTabContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Register plugins
        PluginRegistry pluginRegistry = PluginRegistry.get();
        System.out.printf("Loaded plugins: %d\n", pluginRegistry.getLoadedPluginCount());
        for (IPlugin plugin : pluginRegistry.getLoadedPlugins()) {
            System.out.printf("\t%s\n", plugin.getMetadata().displayName);
        }

        pluginRegistry.getLoadedPlugins().forEach(this::buildUI);

    }

    private void buildUI(IPlugin plugin) {
        for (IUIFactoryTask uiTask : plugin.getUITasks()) {
            if(uiTask instanceof IUIFactoryTask.ITabTask) {
                buildTab(plugin, (IUIFactoryTask.ITabTask) uiTask);
            }
        }
    }

    private void buildTab(IPlugin plugin, IUIFactoryTask.ITabTask tabTask) {
//        Node contents = tabTask.build();
//        Tab tab = new Tab(tabTask.tabTitle(), contents);
//        rootTabContainer.getTabs().add(tab);
    }
}
