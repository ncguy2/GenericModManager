package net.ncguy.manager.api;

import java.util.List;

public interface IPlugin {

    PluginMetadata getMetadata();

    List<IUIFactoryTask> getUITasks();

}
