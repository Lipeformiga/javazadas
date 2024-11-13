import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CRUDCliente {

    BancoDeDados banco = new BancoDeDados();
    List<Cliente> clientes = new ArrayList<>();

    public List<Cliente> readAll() {
        try (Connection con = banco.getConexao()) {

            PreparedStatement ps = con.prepareStatement("SELECT * FROM cliente");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                clientes.add(new Cliente(id,nome,cpf));

            }
            return clientes;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return clientes;
    }


    public Cliente readOne(int idcliente) {
        try( Connection con = banco.getConexao()) {

            PreparedStatement ps = con.prepareStatement("SELECT * FROM cliente WHERE id = ?");

            ps.setInt(1, idcliente);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");

                return new Cliente(id,nome,cpf);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        throw new ContaInexistenteException();
    }
}
