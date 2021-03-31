package ex5;

public class CoordenadasArvore<T> {
    private int x;
    private int y;
    private String valor;

    public CoordenadasArvore(int x, int y, String valor) {
        this.x = x;
        this.y = y;
        this.valor = valor;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + valor + ")";
    }
}
