package ex3;

public class DiscNode {
    private String name;
    private int kbytes;

    public DiscNode(String name, int kbytes) {
        this.name = name;
        this.kbytes = kbytes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKbytes() {
        return kbytes;
    }

    public void setKbytes(int kbytes) {
        this.kbytes = kbytes;
    }

    @Override
    public String toString() {
        return "'" + getName() + "'";
    }
}
