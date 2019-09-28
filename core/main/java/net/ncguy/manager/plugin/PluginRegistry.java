package net.ncguy.manager.plugin;

import net.ncguy.manager.api.IPlugin;
import net.ncguy.manager.api.PluginMetadata;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ServiceLoader;

public class PluginRegistry {

    private static PluginRegistry instance;
    public static PluginRegistry get() {
        if(instance == null) {
            instance = new PluginRegistry();
        }
        return instance;
    }

    private static final boolean separateClassLoaders = true;

    private final List<File> pluginDirectories;
    private final List<IPlugin> loadedPlugins;

    @SuppressWarnings("FieldCanBeLocal")
    private ServiceLoader<IPlugin> internalLoader;
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private final List<ServiceLoader<IPlugin>> externalLoaders;

    private PluginRegistry(File... searchPaths) {
        pluginDirectories = Arrays.asList(searchPaths);
        loadedPlugins = new ArrayList<>();
        externalLoaders = new ArrayList<>();
        load();
    }

    public int getLoadedPluginCount() {
        return getLoadedPlugins().size();
    }

    public List<File> getPluginDirectories() {
        return pluginDirectories;
    }

    public List<IPlugin> getLoadedPlugins() {
        return loadedPlugins;
    }

    private void load() {
        // Find local plugins
        internalLoader = ServiceLoader.load(IPlugin.class);
        internalLoader.stream().map(ServiceLoader.Provider::get).forEach(this::loadPlugin);

        if (getPluginDirectories().isEmpty()) {
            // Nothing on search path, all plugins that could be found have been found
            return;
        }

        buildClassLoaders().stream()
                           .map(ucl -> ServiceLoader.load(IPlugin.class, ucl))
                           .forEach(loader -> {
                               externalLoaders.add(loader);
                               loader.stream()
                                     .map(ServiceLoader.Provider::get)
                                     .forEach(this::loadPlugin);
                           });
    }

    private List<ClassLoader> buildClassLoaders() {
        List<ClassLoader> loaderList = new ArrayList<>();
        if (separateClassLoaders) {
            buildIndividualClassLoaders(loaderList);
        } else {
            buildSingleClassLoader(loaderList);
        }
        return loaderList;
    }

    private List<URL> getUrls() {

        List<URL> urls = new ArrayList<>();
        for (File pluginDirectory : getPluginDirectories()) {
            File[] files = pluginDirectory.listFiles(pathname -> pathname.getPath().toLowerCase().endsWith(".jar"));
            if (files != null) {
                for (File file : files) {
                    try {
                        urls.add(file.toURI().toURL());
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return urls;
    }

    private void buildSingleClassLoader(List<ClassLoader> loaderList) {
        URL[] urls = getUrls().toArray(new URL[0]);
        URLClassLoader ucl = new URLClassLoader(urls, Thread.currentThread().getContextClassLoader());
        loaderList.add(ucl);
    }

    private void buildIndividualClassLoaders(List<ClassLoader> loaderList) {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        getUrls().stream()
                 .map(url -> new URLClassLoader(new URL[]{url}, contextClassLoader))
                 .forEach(loaderList::add);
    }

    private void loadPlugin(IPlugin plugin) {
        PluginMetadata pluginMetadata = plugin.getMetadata();
        System.out.printf("Loading %s [%s]\n", pluginMetadata.displayName, pluginMetadata.uuid.toString());
        loadedPlugins.add(plugin);
    }

}
