public enum urlfindType {

    urlfindimpform3u8(0),
    UrlfindTencent(1),
    Urlfindimp(2),
    urlfindYoukuEmbed(3),
    urlfindDajiang(4),
    UrlfindTencentEmbed(5);

    private int value;
    urlfindType(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}