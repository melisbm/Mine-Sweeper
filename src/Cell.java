public interface Cell {

    public boolean isFlagged();
    public void setFlagged();

    public boolean isRevealed();
    public void setRevealed();

    public default char getFlaggedCharacter(){
        return 'P';
    }

}
