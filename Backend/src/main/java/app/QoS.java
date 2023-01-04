package app;

public enum QoS {
    LEVEL_0(0),
    LEVEL_1(1),
    LEVEL_2(2);

    private final int value;

    QoS(int value) {

        this.value = value;
    }

    public int getValue() {
        return value;
    }
}