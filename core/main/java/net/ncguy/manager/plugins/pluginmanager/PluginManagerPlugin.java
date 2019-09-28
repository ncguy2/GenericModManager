package net.ncguy.manager.plugins.pluginmanager;

import javafx.scene.Node;
import net.ncguy.manager.api.IPlugin;
import net.ncguy.manager.api.IUIFactoryTask;
import net.ncguy.manager.api.PluginMetadata;
import net.ncguy.manager.plugin.controllers.PluginListController;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class PluginManagerPlugin implements IPlugin {

    private final PluginMetadata metadata;

    public PluginManagerPlugin() {
        this.metadata = new PluginMetadata();
        this.metadata.displayName = "Plugin Manager";
        this.metadata.uuid = UUID.randomUUID();
    }

    @Override
    public PluginMetadata getMetadata() {
        return this.metadata;
    }

    @Override
    public List<IUIFactoryTask> getUITasks() {
        return Collections.singletonList(
                new IUIFactoryTask.ITabTask() {
                    @Override
                    public String tabTitle() {
                        return "Plugins";
                    }

                    @Override
                    public Node build() {
                        return buildPluginTab();
                    }
                }
        );
    }

    static Node buildPluginTab() {
        return PluginListController.build(PluginListController.class).get().t;
    }
}
