package moduloVendas;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Paloma Tavares e Rebeka Góes
 */
public class FramePrincipal extends javax.swing.JFrame {

    //barra de rolagem
    private static JScrollPane barraRolagem;

    // auxilia na transicao de paineis
    private static JPanel trocaInformacao;

    // gerencia paineis visiveis
    private static CardLayout paineisLayout;

    // String localTabela;
    public FramePrincipal() {
        initComponents();
        meuInitComponents();
    }

    private void meuInitComponents() {
        // Ocupar toda a area do frame com barra de rolagem
        barraRolagem = new JScrollPane();

        //barra de rolagem ocupara todo o espaco do frame
        this.setLayout(new BorderLayout());
        this.add(barraRolagem);

        // configuracao do painel que recebera os outros paineis 
        paineisLayout = new CardLayout();
        trocaInformacao = new JPanel(paineisLayout);// atua como vetor

        //define que na area que apresenta informacao do usuario 
        //a informacao sera mostrada no painel de troca de informacao
        barraRolagem.setViewportView(this.trocaInformacao);

        //qual o primeiro panel a ser mostrado ao usuario
        trocaInformacao.add(new PainelCompraGUI(), "compra");
        paineisLayout.show(trocaInformacao, "compra");

        //configura o tamanho da tela
        setSize(1000, 700);
        setResizable(false);
        
        perguntaLocalTabela();
        
    }
    //pede o local da tabela excel

    public static void perguntaLocalTabela() {
        String local = JOptionPane.showInputDialog(null, "Informe o local da tabela de produtos", JOptionPane.INFORMATION_MESSAGE);
        FakeBD.localArquivo(local);
    }

    public static void efetuarTransicao(JPanel novoPainel, String nome) {

        trocaInformacao.add(novoPainel, nome);
        paineisLayout.show(trocaInformacao, nome);

        //corrcao tamanho das barras de rolagem
        trocaInformacao.setPreferredSize(novoPainel.getPreferredSize());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1024, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 651, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        FakeBD.atualizaArquivo();
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FramePrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
