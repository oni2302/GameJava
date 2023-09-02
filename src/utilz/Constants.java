package utilz;

public class Constants {
    public static class Directions{
        public static final int LEFT =0;
        public static final int RIGHT =1;
        public static final int UP =2;
        public static final int DOWN =3;
    }
    public static class PlayerConstants {
        public static final int IDLE = 0;
        public static final int WALKING = 1;
        public static final int RUNNING = 2;
        public static final int JUMP = 3;
        public static final int FALLING = 4;
        public static final int GROUND = 5;
        public static final int HIT = 6;
        public static final int ATTACK_1 = 7;
        public static final int ATTACK_2 = 8;
        public static final int ATTACK_1_JUMP = 9;
        public static final int ATTACK_2_JUMP = 10;

        public static int GetSpriteAmount(int player_action) {
            switch (player_action) {
                case RUNNING:
                    return 12;
                case IDLE:
                    return 4;
                case WALKING:
                    return 12;
                case GROUND:

                case JUMP:

                default:
                    return -1;
            }
        }
    }

}
