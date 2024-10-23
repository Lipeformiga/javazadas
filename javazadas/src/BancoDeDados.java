import java.util.ArrayList;
import java.util.List;

public class BancoDeDados {
    private List<Conta> contas = new ArrayList<>();

    public void inserirConta(Conta conta) {
        this.contas.add(conta);
    }
    public void deletarConta(int numero ) {
        Conta conta = buscarConta(numero);
        this.contas.remove(conta);
    }

    public Conta buscarConta(int numero) {
        for (Conta conta : this.contas) {
            if (conta.getNumero() == numero){
                return conta;
            }
        }
        throw new ContaInexistenteException();
    }
    public List<Conta> buscarContas() {
        return this.contas;
    }

    public void atualizarConta(Conta conta) {
        int indice = contas.indexOf(conta);
        if (indice == -1) {
            throw new ContaInexistenteException();
        }
        this.contas.set(indice, conta);
    }
}
