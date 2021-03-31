package ex2.exceptions;

public class ArvoreVaziaException extends RuntimeException {
    public ArvoreVaziaException(String mensagem) {
        super(mensagem);
    }
}
