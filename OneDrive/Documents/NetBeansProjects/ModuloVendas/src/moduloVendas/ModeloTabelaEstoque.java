/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloVendas;

import java.util.Collections;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author @author Paloma Tavares e Rebeka Góes
 */
public class ModeloTabelaEstoque extends AbstractTableModel {

    private Vector<Produto> produtos;
    private EstoqueGUI painel;

    public ModeloTabelaEstoque(EstoqueGUI painel) {
        this.produtos = new Vector<>();
        atualizaDadosTabela("");
    }

    @Override
    public int getRowCount() {
        return produtos.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Produto temp = produtos.get(linha);

        switch (coluna) {
            case 0:
                return temp.getCodigo();
            case 1:
                return temp.getNome();
            case 2:
                return temp.getPreco();
            case 3:
                return temp.getQuantidade();
            default:
                return null;
        }
    }

    public void addNovoProduto(Produto novo) {
        this.produtos.add(novo);
    }
    
    public void removerProduto(int indice){
        Produto temp = produtos.remove(indice);
        
        FakeBD.deleteProduto(temp);
    }

    public void atualizaQuant(int cod, Produto atualizado) {
        this.produtos.setElementAt(atualizado, cod);
    }

    public void atualizaDadosTabela(String consulta) {
        produtos = FakeBD.consultaNome(consulta);

        //ordena os produtos que serao exibidos na tabela
        Collections.sort(produtos);
    }

    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "CÓDIGO";
            case 1:
                return "NOME";
            case 2:
                return "PREÇO UNI.";
            case 3:
                return "QUANTIDADE";
            default:
                return null;
        }

    }

    @Override
    public Class<?> getColumnClass(int coluna) {
        switch (coluna) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return Double.class;
            case 3:
                return Integer.class;
            default:
                return null;
        }
    }

    public Vector<Produto> produtos() {
        for (Produto prod1 : produtos) {
            produtos().add(prod1);
        }
        return produtos;
    }

    public void limpaEstoque() {
        this.produtos.clear();
    }

    @Override
    public boolean isCellEditable(int linha, int coluna) {
        if (coluna == 0) {
            return false;
        } else {
            return true;
        }
    }


    @Override
    public void setValueAt(Object novoValor, int linha, int coluna) {
        switch (coluna) {
            case 1: {
                produtos.get(linha).setNome((String) novoValor);
                // efetuar update no BD
            }
            break;
            case 2: {
                produtos.get(linha).setPreco((double) novoValor);
            }
            break;
            case 3:{
                produtos.get(linha).setQuantidade((int)novoValor);
            }
        }
    }
        /*if(coluna == 2){
            //reguisitando senha do gerente para permitir a modicação de quantidades
            String senha = JOptionPane.showInputDialog(null, "Informe a senha do gerente",
                    "Operação restrita", JOptionPane.INFORMATION_MESSAGE);

            if (senha != null && senha.equals("ifmg")) {
                produtos.get(linha).setPreco((double) novoValor);

                // a tabela e o valor são atualizados
                this.painel.atualizaQuantidadesEstoque();
            } 
        }
        if (coluna == 3) {
            //reguisitando senha do gerente para permitir a modicação de quantidades
            String senha = JOptionPane.showInputDialog(null, "Informe a senha do gerente",
                    "Operação restrita", JOptionPane.INFORMATION_MESSAGE);

            if (senha != null && senha.equals("ifmg")) {
                produtos.get(linha).setQuantidade((int) novoValor);

                // a tabela e o valor são atualizados
                this.painel.atualizaQuantidadesEstoque();
            }
        }
        
         */

    }
