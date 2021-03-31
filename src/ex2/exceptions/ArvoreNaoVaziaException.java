package ex2.exceptions;

public class ArvoreNaoVaziaException extends RuntimeException {
    public ArvoreNaoVaziaException(String mensagem) {
        super(mensagem);
    }
}
