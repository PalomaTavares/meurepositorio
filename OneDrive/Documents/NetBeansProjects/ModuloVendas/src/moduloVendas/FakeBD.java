/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloVendas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pfsta
 */
public class FakeBD extends FramePrincipal {

    private static Vector<Produto> produtos;
    public static File arquivo = new File("");
    public static String auxLocal = "";

    public static void localArquivo(String local) {
       auxLocal = local;
    }
    String local;

    // leitura das informacoes do arquivo excel
    private static void cargaArquivo() {
        
        arquivo = new File(auxLocal);
        
        // ajuste na criacao de vetor de produtos static
        if (produtos == null) {
            produtos = new Vector<>();
        } else {
            produtos.clear();
        }

        try {
            FileReader marcaLeitura = new FileReader(arquivo);
            BufferedReader bufLeitura = new BufferedReader(marcaLeitura);

            //ler cada linha
            //le primeira linha (cabecalho)---descartar
            bufLeitura.readLine();
            //le primeiro produto
            String linha = bufLeitura.readLine();

            while (linha != null) {
                //linhas seguintes ate o fim do arquivo
                // separar as informacoes 
                String infos[] = linha.split(";");
                int cod = Integer.parseInt(infos[0]);
                String nome = infos[1];
                double preco = Double.parseDouble(infos[2]);
                int quant = Integer.parseInt(infos[3]);

                //adicao dos produtos em um vetor dinamico
                produtos.add(new Produto(cod, nome, preco, quant));
                //le a proxima linha
                linha = bufLeitura.readLine();

            }
            // libera o arquivo para outros processos
            bufLeitura.close();

        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo não existe");
        } catch (IOException e) {
            System.err.println("Arquivo corrompido");
        }
    }

    public static Produto consultaProdutoCod(int cod) {
        //carrega o arquivo caso ele nao tenha sido carregado antes
        if (produtos == null) {
            cargaArquivo();
        }
        for (Produto prod1 : produtos) {
            if (prod1.getCodigo() == cod) {
                return prod1;
            }
        }
        //nao existe produto com o codigo especificado no parametro
        return null;
    }

    public static Vector<Produto> estoque() {
        Vector<Produto> prod = new Vector<>();
        if (produtos == null) {
            cargaArquivo();
        }
        for (Produto prod1 : produtos) {
            prod.add(prod1);
        }
        return prod;
    }

    public static void atualizaArquivo() {
        arquivo = new File(auxLocal);
        try {
            FileWriter escritor = new FileWriter(arquivo);

            BufferedWriter bufEscrita = new BufferedWriter(escritor);

            for (int i = 0; i < produtos.size(); i++) {
                bufEscrita.write(produtos.get(i) + "\n");
            }

            //bufEscrita.flush();//descarrega arquivo
            bufEscrita.close();
        } catch (IOException ex) {
            System.err.println("dispositivo com falha");
        }
    }

}