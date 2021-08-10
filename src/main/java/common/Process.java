package common;

public enum Process {
    QUIT, ORDER, START;

    public boolean isContinue() {
        return this.equals(ORDER) || this.equals(START);
    }
}
