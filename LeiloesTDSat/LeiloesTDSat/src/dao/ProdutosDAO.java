package dao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Adm
 */
import model.ProdutosDTO;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProdutosDAO {

    private Connection conn;
    private ConectaDAO conexao;
    private PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public ProdutosDAO() {
        this.conexao = new ConectaDAO();
        this.conn = conexao.connectDB();
    }

    public void cadastrarProduto(ProdutosDTO produto) {
        String sql = "INSERT INTO produtos(nome, valor, status) VALUES(?,?,?)";
        try {
            prep = this.conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            prep.execute();
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao inserir empresa: " + e.getMessage());
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        String sql = "SELECT * FROM produtos";

        try {
            prep = this.conn.prepareStatement(sql);
            resultset = prep.executeQuery();

            List<ProdutosDTO> listaProdutos = new ArrayList<>();

            while (resultset.next()) { //.next retorna verdadeiro caso exista uma próxima posição dentro do array
                ProdutosDTO p = new ProdutosDTO();
                p.setId(resultset.getInt("id"));
                p.setNome(resultset.getString("nome"));
                p.setValor(resultset.getInt("valor"));
                p.setStatus(resultset.getString("status"));

                listaProdutos.add(p);
            }
            return (ArrayList<ProdutosDTO>) listaProdutos;

            //Se o método entrar no "Catch" quer dizer que não encontrou nenhuma empresa, então damos um "return null"          
        } catch (Exception e) {
            return null;
        }
    }

    public void venderProduto(int id_produto) {
        String sql = "UPDATE produtos SET status = ? WHERE id = ?";

        try {
            prep = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            //Setando os parâmetros
            prep.setString(1, "Vendido");
            prep.setInt(2, id_produto); //pegando o parâmetro passado pelo usuários.
            //criando variável de condição e Executando a query
            int result = prep.executeUpdate();
            if (result > 0) {
                JOptionPane.showMessageDialog(null, "Produto de Código: " + id_produto + ", VENDIDO!");
            } else {
                JOptionPane.showMessageDialog(null, "Produto de Código: " + id_produto + ", não encontrado!");
            }

            //tratando o erro, caso ele ocorra.
        } catch (Exception e) {
            System.out.println("Erro ao vender produto: " + e.getMessage());
        }
    }

}
