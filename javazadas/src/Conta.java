public class Conta {

    private int numero;
    private String titular;
    private double saldo;
    private double limite;

    public Conta(int numero, String titular, double saldo, double limite) {
        this.numero = numero;
        this.titular = titular;
        this.saldo = saldo;
        this.limite = limite;
    }

    public void saque(double valor){
        validaValor(valor);
        validaSaldo(valor);
        validaLimite(valor);
        this.saldo -= valor;
    }

    public void deposito(double valor){
       validaValor(valor);
       this.saldo += valor;
    }

    public void transferencia(double valor, Conta conta){
        validaConta(conta);
        this.saque(valor);
        conta.deposito(valor);
    }

    private void validaValor(double valor){
        if (valor <= 0){
            throw new ValorInvalidoException();
        }
    }
    private void validaSaldo(double valor){
        if (this.saldo < valor){
            throw new SaldoInsuficienteException();
        }
    }
    private void validaLimite(double valor){
        if (this.limite < valor){
            throw new LimiteInsuficienteException();
        }
    }
    private void validaConta(Conta conta){
        validaContaNula(conta);
        validaContaPropria(conta);
    }
    private void validaContaNula(Conta conta){
        if (conta == null){
            throw new ContaInexistenteException();
        }
    }
    private void validaContaPropria(Conta conta){
        if (this == conta){
            throw new PropriaContaException();
        }
    }
}