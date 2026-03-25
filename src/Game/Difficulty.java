package Game;

public enum Difficulty{

    EASY {

        private final int rows = 9;
        private final int columns = 9;
        private final int bombs = 10;

        @Override
        public int getRows(){
            return rows;
        }

        @Override
        public int getColumns(){
            return columns;
        }

        @Override
        public int getBombs(){
            return bombs;
        }
    },
    MEDIUM {

        private final int rows = 16;
        private final int columns = 16;
        private final int bombs = 40;

        @Override
        public int getRows(){
            return rows;
        }

        @Override
        public int getColumns(){
            return columns;
        }

        @Override
        public int getBombs(){
            return bombs;
        }
    },
    EXPERT {

        private final int rows = 30;
        private final int columns = 16;
        private final int bombs = 99;

        @Override
        public int getRows(){
            return rows;
        }

        @Override
        public int getColumns(){
            return columns;
        }

        @Override
        public int getBombs(){
            return bombs;
        }
    };

    public abstract int getRows();
    public abstract int getColumns();
    public abstract int getBombs();
}