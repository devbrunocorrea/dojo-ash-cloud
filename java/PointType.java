public enum PointType {

    CLOUD('*'), AIRPORT('A'), VOID('.');

    private final char value;

    private PointType(char value) {
        this.value = value;
    }
    
    public char getValue(){
        return this.value;
    }
}
