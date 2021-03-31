package ex2.exceptions;

public class NodoInvalidoException extends RuntimeException {
    public NodoInvalidoException(String mensagem) {
        super(mensagem);
    }
}
