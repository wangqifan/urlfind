public enum urlfindType {

    urlfindimpform3u8(0),
    urlfindyouku(1),
    UrlfindTencent(2),
    Urlfindimp(3);

    private int value;
    urlfindType(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}