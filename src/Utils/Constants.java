package Utils;

public class Constants {
    public static class Directions{
        public static final int UP = 0;
        public static final int DOWN = 1;
        public static final int LEFT = 2;
        public static final int RIGHT = 3;
    }

    public static class PlayerConstants {
        public static final int IDLE=0;
        public static final int RUNNING=1;
        public static final int JUMPING=2;
        public static final int FALLING=3;

        public static int GetSpriteAmount(int playerAction){
            switch (playerAction){
                case IDLE:
                    return 5;
                case RUNNING:
                    return 6;
                case JUMPING:
                    return 3;
                case FALLING:
                    return 1;
                default:
                    return 1;
            }
        }
    }
}
