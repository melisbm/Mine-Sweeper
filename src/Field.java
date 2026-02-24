public class Field {
    int height;
    int width;

    char[][] fieldCells = new char[width][height];

    public Field(String difficulty){
        if(difficulty.equals("easy")){
            this.height = 9;
            this.width = 9;
        }
        else if(difficulty.equals("medium")){
            this.height = 16;
            this.width = 16;
        }
        else{
            this.height = 16;
            this.width = 30;
        }
    }

    public String fieldToString(String difficulty){
        return "";
    }

}
