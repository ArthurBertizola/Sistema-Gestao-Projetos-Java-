package src;
import java.util.List;
import java.util.ArrayList;

public class GerenciadorRelatorios {

    /**
     * Gera um relatório com o status de todas as tarefas de um projeto.
     * @param projeto O projeto para o qual o relatório será gerado.
     * @param tarefas A lista completa de tarefas do sistema.
     */
    public static void gerarRelatorioDeTarefasPorProjeto(Projeto projeto, List<Tarefa> tarefas) {
        System.out.println("--- Relatório de Tarefas do Projeto: '" + projeto.getNome() + "' ---");

        int tarefasPendentes = 0;
        int tarefasEmAndamento = 0;
        int tarefasConcluidas = 0;

        for (Tarefa tarefa : tarefas) {

            if (tarefa.getIdProjeto() == projeto.getId()) {
                System.out.println("- Tarefa: " + tarefa.getDescricao());
                System.out.println("  Status: " + tarefa.getStatus());
                System.out.println("  Responsável: " + tarefa.getResponsavel());
                System.out.println("  Prazo: " + tarefa.getDataDeVencimento() + "\n");

                // Contabiliza os status para o resumo
                switch (tarefa.getStatus()) {
                    case "Pendente":
                        tarefasPendentes++;
                        break;
                    case "Em andamento":
                        tarefasEmAndamento++;
                        break;
                    case "Concluída":
                        tarefasConcluidas++;
                        break;
                }
            }
        }

        System.out.println("--- Resumo Geral do Projeto ---");
        System.out.println("Tarefas Pendentes: " + tarefasPendentes);
        System.out.println("Tarefas Em Andamento: " + tarefasEmAndamento);
        System.out.println("Tarefas Concluídas: " + tarefasConcluidas);
        System.out.println("----------------------------------------");
    }

    /**
     * Gera um relatório de tarefas alocadas para uma equipe específica.
     * @param equipe A equipe para a qual o relatório será gerado.
     * @param tarefas A lista completa de tarefas do sistema.
     */
    public static void gerarRelatorioDeTarefasPorEquipe(Equipe equipe, List<Tarefa> tarefas) {
        System.out.println("--- Relatório de Tarefas da Equipe: '" + equipe.getNome() + "' ---");
        int tarefasEncontradas = 0;

        for (Tarefa tarefa : tarefas) {

            for (Usuario membro : equipe.getMembros()) {
                if (tarefa.getResponsavel().equals(membro.getNomeCompleto())) {
                    System.out.println("- Tarefa: " + tarefa.getDescricao());
                    System.out.println("  Status: " + tarefa.getStatus());
                    System.out.println("  Responsável: " + tarefa.getResponsavel() + "\n");
                    tarefasEncontradas++;
                    break; // Sai do loop interno para não duplicar a tarefa
                }
            }
        }

        if (tarefasEncontradas == 0) {
            System.out.println("Nenhuma tarefa encontrada para esta equipe.");
        }
        System.out.println("----------------------------------------");
    }
}