/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sistemagerenciamentoclientes;

import javax.swing.SwingUtilities;
import sistemagerenciamentoclientes.view.TelaInicial;

/**
 *
 * @author joser
 */
public class SistemaGerenciamentoClientes {

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {
        //chamada da tela inicial
        TelaInicial telaInicial = new TelaInicial();
        telaInicial.setVisible(true);
        telaInicial.setTitle("Cadastro de clientes");
    }
}
