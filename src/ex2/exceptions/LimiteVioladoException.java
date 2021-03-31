package ex2.exceptions;

public class LimiteVioladoException extends RuntimeException {
    public LimiteVioladoException(String mensagem) {
        super(mensagem);
    }
}
