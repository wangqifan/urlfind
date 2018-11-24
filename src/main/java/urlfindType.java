public enum urlfindType {

    urlfindimpform3u8(0),
    urlfindYoukuiframe(1),
    UrlfindTencent(2),
    Urlfindimp(3),
    urlfindYoukuEmbed(4),
    urlfindDajiang(5);

    private int value;
    urlfindType(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}