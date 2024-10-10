public class ContaInexistenteException extends ContaException{

    public ContaInexistenteException() {
        super("Não foi informado uma conta válida");
    }
}
