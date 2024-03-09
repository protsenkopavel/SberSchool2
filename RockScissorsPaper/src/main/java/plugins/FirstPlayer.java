package plugins;

import java.util.Random;

public class FirstPlayer implements PlayableRockPaperScissors{
    @Override
    public RockPaperScissorsEnum play() {
        return RockPaperScissorsEnum.values()
                [new Random().nextInt(RockPaperScissorsEnum.values().length)];
    }
}
