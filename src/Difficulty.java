public enum Difficulty{
    //DIFF.settings = { width, height, bombs }
    EASY {
        @Override
        public int[] settings() {
            return new int[]{9, 9, 10};
        }
    },
    MEDIUM {
        @Override
        public int[] settings() {
            return new int[]{ 16, 16, 40 };
        }
    },
    EXPERT {
        @Override
        public int[] settings() {
            return new int[]{ 16, 30, 99 };
        }
    };

    public abstract int[] settings();
}

