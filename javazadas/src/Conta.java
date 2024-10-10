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
        if ( valor > 0 ){
            if (this.saldo >= valor){
                if (this.limite >= valor){
                    this.saldo -= valor;
                    return;
                }
                throw new LimiteInsuficienteException();
            }
            throw new SaldoInsuficienteException();
        }
        throw new ValorInvalidoException();
    }

    public void deposito(double valor){
        if (valor > 0){
            this.saldo += valor;
            return;
        }
        throw new RuntimeException("Não foi possível realizar esse deposito!");
    }

    public void transferencia(double valor, Conta conta){
        if ( valor > 0 ){
            if ( this.saldo >= valor){
                if (this.limite >= valor){
                    if (conta != null){
                        if (this != conta){
                            this.saldo -= valor;
                            conta.saldo += valor;
                            return;
                        }
                    }
                }
                throw new LimiteInsuficienteException();
            }
            throw new SaldoInsuficienteException();
        }
        throw new ValorInvalidoException();
    }
}