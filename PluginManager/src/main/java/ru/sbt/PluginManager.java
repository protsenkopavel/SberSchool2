package ru.sbt;

import com.sun.source.util.Plugin;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class PluginManager {
    private final String pluginRootDirectory;

    public PluginManager(String pluginRootDirectory) {
        this.pluginRootDirectory = pluginRootDirectory;
    }

    public Plugin load(String pluginName, String pluginClassName) throws Exception {
        File pluginPath = new File(pluginRootDirectory + File.separator + pluginName + File.separator);
        URLClassLoader classLoader = new ClassLoader(new URL[]{pluginPath.toURI().toURL()}, ClassLoader.class.getClassLoader());

        Class<?> pluginClass = Class.forName(pluginClassName, true, classLoader);
        Object pluginObject = pluginClass.getDeclaredConstructor().newInstance();

        return (Plugin) pluginObject;
    }
}