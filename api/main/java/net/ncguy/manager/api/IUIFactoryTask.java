package net.ncguy.manager.api;

import javax.swing.*;

public interface IUIFactoryTask {

    JPanel build();

    interface ITabTask extends IUIFactoryTask {

        String tabTitle();

    }

}
