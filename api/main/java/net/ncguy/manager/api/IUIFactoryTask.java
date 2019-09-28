package net.ncguy.manager.api;

import javafx.scene.Node;

public interface IUIFactoryTask {

    Node build();

    interface ITabTask extends IUIFactoryTask {

        String tabTitle();

    }

}
