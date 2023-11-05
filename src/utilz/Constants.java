package utilz;

public class Constants {
    public static class Directions {
        public static final int LEFT = 0;
        public static final int RIGHT = 1;
        public static final int UP = 2;
        public static final int DOWN = 3;
    }

    public static class PlayerConstants {
        public static final int IDLE = 0;
        public static final int WALKING = 1;
        public static final int JUMP = 3;
        public static final int FALLING = 4;
        public static final int GROUND = 5;
        public static final int HIT = 9;
        public static final int ATTACK_1 = 7;
        public static final int ATTACK_2 = 8;
        public static final int ATTACK_RANGE = 2;

        public static int GetSpriteAmountRedHood(int player_action) {
            switch (player_action) {
                case IDLE:
                    return 18;
                case WALKING:
                    return 25;
                case GROUND:
                    return 5;
                case FALLING:
                    return 6;
                case ATTACK_1:
                    return 26;
                case ATTACK_2:
                    return 43;
                case ATTACK_RANGE:
                    return 9;
                case JUMP:
                    return 9;
                default:
                    return -1;
            }
        }

        public static int GetSpriteAmount(int player_action) {
            switch (player_action) {
                case IDLE:
                    return 8;
                case WALKING:
                    return 8;
                case GROUND:

                case JUMP:
                    return 19;
                default:
                    return -1;
            }
        }
    }

}
