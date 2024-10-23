public class ContaJaCadastradaException extends ContaException {
    public ContaJaCadastradaException() {
        super(". Já existe uma conta com o número informado");
    }
}
