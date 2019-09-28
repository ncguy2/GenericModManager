package net.ncguy.manager.fxml.controllers.controllers;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import net.ncguy.manager.api.IPlugin;
import net.ncguy.manager.api.PluginMetadata;
import net.ncguy.manager.fxml.controllers.AbstractController;
import net.ncguy.manager.utils.FXMLController;

import java.net.URL;
import java.util.ResourceBundle;

@FXMLController("/fxml/pluginManager/PluginItemSummary.fxml")
public class PluginItemSummaryController extends AbstractController {

    public AnchorPane rootPane;
    public ImageView iconViewer;
    public Text displayNameText;
    public Text summaryText;
    private Property<IPlugin> pluginProperty;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pluginProperty = new SimpleObjectProperty<>();
        pluginProperty.addListener(o -> onPluginUpdate(pluginProperty.getValue()));
    }

    public void setPlugin(IPlugin plugin) {
        pluginProperty.setValue(plugin);

        PluginMetadata metadata = plugin.getMetadata();
        displayNameText.setText(metadata.displayName);
        summaryText.setText(String.valueOf(metadata.summary));
    }

    private void onPluginUpdate(IPlugin newPlugin) {
        System.out.println(newPlugin.getMetadata().displayName);
    }

}
