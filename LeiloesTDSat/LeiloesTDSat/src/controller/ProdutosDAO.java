package controller;

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
    private conectaDAO conexao;
    private PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public ProdutosDAO() {
        this.conexao = new conectaDAO();
        this.conn = conexao.connectDB();
    }
    
    
    public void cadastrarProduto (ProdutosDTO produto){
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
    
    public ArrayList<ProdutosDTO> listarProdutos(){
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
    
    
    
        
}

