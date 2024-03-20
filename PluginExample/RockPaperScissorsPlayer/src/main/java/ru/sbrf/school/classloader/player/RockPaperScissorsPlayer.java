package ru.sbrf.school.classloader.player;

import ru.sbrf.school.classloader.RockPaperScissorsEnum;
import ru.sbrf.school.classloader.api.PlayableRockPaperScissors;

public class RockPaperScissorsPlayer implements PlayableRockPaperScissors {
    @Override
    public RockPaperScissorsEnum play() {

        var options = RockPaperScissorsEnum.values();

        int optionNumber = getRandomNumber(0, options.length - 1);

        return options[optionNumber];
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
