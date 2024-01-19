package ru.sbt;

import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;

public class ClassLoader extends URLClassLoader {
    public ClassLoader(URL[] urls, java.lang.ClassLoader parent) {
        super(urls, parent);
    }

    public ClassLoader(URL[] urls) {
        super(urls);
    }

    public ClassLoader(URL[] urls, java.lang.ClassLoader parent, URLStreamHandlerFactory factory) {
        super(urls, parent, factory);
    }

    public ClassLoader(String name, URL[] urls, java.lang.ClassLoader parent) {
        super(name, urls, parent);
    }

    public ClassLoader(String name, URL[] urls, java.lang.ClassLoader parent, URLStreamHandlerFactory factory) {
        super(name, urls, parent, factory);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        try {
            return findClass(name);
        } catch (ClassNotFoundException e) {
            return super.loadClass(name, resolve);
        }
    }
}
