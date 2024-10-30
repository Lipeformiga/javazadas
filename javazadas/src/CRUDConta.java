import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CRUDConta {

    private final BancoDeDados banco = new BancoDeDados();

    public void create(Conta conta){
        try {
//           email = "'email@email';DROP DATABASE db_sistema_bancario";
//           Statement stmt = con.createStatement();
//           stmt.execute("SELECT * FROM td_ususario WHERE email = " + email)

            Connection con = banco.getConexao();
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO tb_conta (numero, titular, saldo, limite) values (?, ?, ?, ?)");

            ps.setInt(1, conta.getNumero());
            ps.setString(2, conta.getTitular());
            ps.setDouble(3, conta.getSaldo());
            ps.setDouble(4, conta.getLimite());

            // só execute() booleano fala se deu ou nao certo
            ps.execute();

            // quando precisa mais dados do banco de dados, por exemplo um comando select
            // ps.executeQuery();
            // a quantidade de registros alterados ( retorna um valor inteiro )
            // ps.executeUpdate();
            // mesma coisa que o de cima mas se for um banco de dados muito grande
            // ps.executeLargeUpdate();

        } catch (SQLException ex) {

        }

    }

    public Conta readOne(){

    }

    public List<Conta> readAll(){

    }

    public void update(){

    }

    public void delete(){

    }
}
