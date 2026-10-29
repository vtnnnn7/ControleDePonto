
package com.controleponto.reports;

import java.util.List;

import com.controleponto.model.Funcionario;
import com.controleponto.model.RegistroPonto;

public class Relatorios {

    public void listarFuncionarios(List<Funcionario> funcionarios) {
        System.out.println("\n=== FUNCIONÁRIOS CADASTRADOS ===");

        for (Funcionario funcionario : funcionarios) {
            System.out.println(
                funcionario.getIdFuncionario() + " - "
                + funcionario.getNome() + " - "
                + funcionario.getCargo()
            );
        }
    }

    public void contarRegistrosPorTipo(List<RegistroPonto> registros) {
        int entradas = 0;
        int saidasIntervalo = 0;
        int retornosIntervalo = 0;
        int saidasFinais = 0;

        for (RegistroPonto registro : registros) {
            switch (registro.getTipo()) {
                case "ENTRADA":
                    entradas++;
                    break;
                case "SAIDA_INTERVALO":
                    saidasIntervalo++;
                    break;
                case "RETORNO_INTERVALO":
                    retornosIntervalo++;
                    break;
                case "SAIDA_FINAL":
                    saidasFinais++;
                    break;
                default:
                    break;
            }
        }

        System.out.println("\n=== REGISTROS POR TIPO ===");
        System.out.println("Entradas: " + entradas);
        System.out.println("Saídas para intervalo: " + saidasIntervalo);
        System.out.println("Retornos do intervalo: " + retornosIntervalo);
        System.out.println("Saídas finais: " + saidasFinais);
    }
}
