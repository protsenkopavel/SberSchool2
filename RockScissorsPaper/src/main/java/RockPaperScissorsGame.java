import plugins.PlayableRockPaperScissors;
import plugins.RockPaperScissorsEnum;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class RockPaperScissorsGame {
    private static final String PATH = "/home/unknown/study-projects/SberJava2/RockScissorsPaper/src/main/java/plugins";

    public static void main(String[] args) {
        List<PlayableRockPaperScissors> plugins = loadPlugins();
        if (plugins.isEmpty()) {
            System.out.println("Нет доступных плагинов");
            return;
        }

        PlayableRockPaperScissors winner = playGame(plugins);
        System.out.println("Победитель: " + winner.getClass().getSimpleName());
    }

    private static List<PlayableRockPaperScissors> loadPlugins() {
        List<PlayableRockPaperScissors> plugins = new ArrayList<>();
        File pluginFolder = new File(PATH);
        if (!pluginFolder.exists() || !pluginFolder.isDirectory()) {
            System.out.println("Некорректный путь");
            return plugins;
        }

        File[] pluginFiles = pluginFolder.listFiles((dir, name) -> name.endsWith(".jar"));
        if (pluginFiles == null || pluginFiles.length == 0) {
            System.out.println("Нет доступных плагинов.");
            return plugins;
        }

        for (File pluginFile : pluginFiles) {
            try {
                URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{pluginFile.toURI().toURL()});
                Class<?> pluginClass = Class.forName("plugins." +
                        pluginFile.getName().replace(".jar", ""), true, classLoader);
                Object pluginObject = pluginClass.newInstance();
                if (pluginObject instanceof PlayableRockPaperScissors) {
                    plugins.add((PlayableRockPaperScissors) pluginObject);
                    System.out.println("Загружен плагин: " + pluginClass.getSimpleName());
                }
            } catch (Exception e) {
                System.out.println("Ошибка при загрузке плагина: " + pluginFile.getName());
                e.printStackTrace();
            }
        }

        return plugins;
    }

    private static PlayableRockPaperScissors playGame(List<PlayableRockPaperScissors> plugins) {
        PlayableRockPaperScissors winner = null;
        while (plugins.size() > 1) {
            PlayableRockPaperScissors plugin1 = plugins.get(0);
            PlayableRockPaperScissors plugin2 = plugins.get(1);
            RockPaperScissorsEnum move1 = plugin1.play();
            RockPaperScissorsEnum move2 = plugin2.play();
            System.out.println(plugin1.getClass().getSimpleName() + " plays: " + move1);
            System.out.println(plugin2.getClass().getSimpleName() + " plays: " + move2);
            int result = move1.compareTo(move2);
            if (result == 0) {
                System.out.println("Ничья");
            } else if (result == 1) {
                System.out.println(plugin1.getClass().getSimpleName() + " победа!");
                winner = plugin1;
                plugins.remove(1);
            } else {
                System.out.println(plugin2.getClass().getSimpleName() + " победа!");
                winner = plugin2;
                plugins.remove(0);
            }
            System.out.println();
        }
        return winner;
    }
}
