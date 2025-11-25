package main;

import classes.*;
import classes.Pessoa;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import classes.Agendamento;

public class Application {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Pessoa.exercicies> listaexercicio = new ArrayList<>();
        List<Pessoa> cadastros = new ArrayList<>();

        Profissional personal = new Profissional(1221, "Dr.Davi ", "726394860032", "27999362832",
                "2", "3", "M", "personal trainer", 4342342, 180);

        Cliente cliente= new Cliente(213, "Andrea Horta", "123.456.789-00", "1231123554",
                "1", "2", "F", 30, 65.5, 1.65,
                "Hipertrofia", "Nenhuma");

        personal.cadastrarCliente(cliente);

        cadastros.add(personal);
        cadastros.add(cliente);

        System.out.println("===TELA DE LOGIN===");
        int opcao;
        do {
            System.out.println("1.Login" +
                    "\n2.Cadastro" +
                    "\n3.Sair" +
                    "\nOpção:");
            opcao = sc.nextInt();

            boolean loginRealizado = false;

            switch (opcao) {
                case 1:
                    System.out.println("===TELA DE LOGIN====");
                    System.out.println("Digite seu email: ");
                    String emailLogin = sc.next();
                    System.out.println("Digite sua senha: ");
                    String senhaLogin = sc.next();

                    loginRealizado = false;

                    for (Pessoa p : cadastros) {
                        //verifica se as credenciais (email e senha) batem
                        if (p.getEmail().equals(emailLogin) && p.verificarSenha(senhaLogin)) {
                            loginRealizado = true; // Login validado com sucesso

                            // verifica o tipo de utilizador
                            if (p instanceof Cliente) {
                                Cliente alunoLogado = (Cliente) p;
                                System.out.println("Bem-vindo, Aluno(a) " + alunoLogado.getNome());

                                int opcCliente;
                                do {
                                    System.out.println("\n===== MENU DO ALUNO =====");
                                    System.out.println("1. Agendar personal");
                                    System.out.println("2. Visualizar Treinos");
                                    System.out.println("3. Registrar Carga em Exercício");
                                    System.out.println("4. Visualizar Avaliações Físicas");
                                    System.out.println("5. Atualizar Medidas");
                                    System.out.println("6. Visualizar Perfil");
                                    System.out.println("0. Sair");
                                    System.out.print("Escolha uma opção: ");

                                    opcCliente = sc.nextInt();

                                    switch (opcCliente) {
                                        case 1: // Agendar Personal
                                            System.out.println("\n=== AGENDAMENTO ===");

                                            // Listar Profissionais com ID para o usuário saber qual escolher
                                            System.out.println("Personais disponíveis:");
                                            boolean temPersonal = false;
                                            for (Pessoa x : cadastros) {
                                                if (x instanceof Profissional) {
                                                    System.out.println("ID: " + x.getId() + " | Nome: " + x.getNome() + " | Valor da Aula R$: "
                                                            + ((Profissional) x).getValorAula());
                                                    temPersonal = true;
                                                }
                                            }

                                            if (!temPersonal) {
                                                System.out.println("\nNão há personais cadastrados no sistema.\n");
                                                break;
                                            }

                                            // 2. Pedir o ID (Alteração aqui)
                                            System.out.print("\nDigite o ID do personal que deseja agendar: ");
                                            int idBusca = sc.nextInt();
                                            sc.nextLine();

                                            // exibe uma lista com id e nome de personais
                                            Profissional personalEncontrado = null;
                                            for (Pessoa xy : cadastros) {
                                                // Compara o ID digitado com o ID do cadastro
                                                if (xy instanceof Profissional && xy.getId().equals(idBusca)) {
                                                    personalEncontrado = (Profissional) xy;
                                                    break;
                                                }
                                            }
                                            if (personalEncontrado != null) {
                                                System.out.println("Personal " + personalEncontrado.getNome() + " selecionado.");
                                                System.out.print("\nQuantos dias que você pretende treinar: ");
                                                int diasPorSemana = sc.nextInt();
                                                sc.nextLine();

                                                // Consideramos uma média de 4 semanas por mês
                                                int totalAulasMensal = diasPorSemana * 4;

                                                double valorMensal = personalEncontrado.calcularValorAula(totalAulasMensal); //

                                                System.out.printf(">> Estimativa: Para %d dias na semana " +
                                                                "(aprox. %d aulas/mês), o valor mensal será: R$ %.2f\n\n",
                                                        diasPorSemana, totalAulasMensal, valorMensal);


                                                System.out.print("Deseja prosseguir com o agendamento de uma aula agora? (S/N): ");
                                                String resposta = sc.next().toUpperCase();

                                                if (resposta.equalsIgnoreCase("S")) {
                                                    System.out.print("Digite a Data (dd/mm/aaaa): ");
                                                    String data = sc.next();
                                                    // ... restante do código de agendamento que já existia ...
                                                    System.out.print("Digite o Horário (ex: 14:00): "); // gostaria de colocar lista de horario por exemplo 1 - 14:00...
                                                    String horario = sc.next();
                                                    System.out.print("Tipo de Sessão (Treino/Avaliação): ");
                                                    sc.nextLine();
                                                    String tipo = sc.nextLine();

                                                    Agendamento novoAgendamento = new Agendamento(
                                                            data,
                                                            horario,
                                                            alunoLogado,
                                                            personalEncontrado.getNome(),
                                                            tipo
                                                    );
                                                    alunoLogado.solicitarAgendamento(personalEncontrado, novoAgendamento);
                                                } else {
                                                    System.out.println("Agendamento cancelado, voltando ao menu....\n");
                                                }

                                            } else {
                                                System.out.println("ID não encontrado! Tente novamente.\n");
                                            }
                                            break;

                                        case 2:
                                            try {
                                                System.out.println("\n=== SEUS TREINOS ===\n");
                                                alunoLogado.visualizarFichaTreino();
                                            } catch (Exception e) {
                                                System.out.println("Ficha vazia!\n");
                                            }
                                            break;

                                        case 3:
                                            System.out.println("Digite o nome do exercício: ");
                                            String nomeEx = sc.next();
                                            System.out.println("Carga usada (kg): ");
                                            double carga = sc.nextDouble();
                                            System.out.println("Repetições: ");
                                            int reps = sc.nextInt();
                                            // Ajuste conforme seu método registrarCarga (se pedir reps, adicione)
                                            alunoLogado.registrarCarga(nomeEx, carga, reps);
                                            System.out.println("Carga registrada!");
                                            break;

                                        case 4:
                                            System.out.println("\n=== AVALIAÇÕES ===");
                                            alunoLogado.getMedida();
                                            break;

                                        case 5:
                                            System.out.println("Novo peso: ");
                                            double peso = sc.nextDouble();
                                            System.out.println("Medida braço: ");
                                            double braco = sc.nextDouble();
                                            System.out.println("Medida cintura: ");
                                            double cintura = sc.nextDouble();
                                            System.out.println("Altura: ");
                                            double altura = sc.nextDouble();
                                            alunoLogado.atualizarMedidas(peso, braco, cintura, altura);
                                            System.out.println("Medidas atualizadas!\n");
                                            break;

                                        case 6:
                                            alunoLogado.verPerfilAluno();
                                            break;

                                        case 0:
                                            System.out.println("Saindo...\n");
                                            break;

                                        default:
                                            System.out.println("Opção inválida!");
                                    }
                                } while (opcCliente != 0);

                            } else if (p instanceof Profissional) {
                                System.out.println("Bem-vindo, Personal " + p.getNome());
                                Profissional personalLogado = (Profissional) p;

                                int opcPersonal;

                                do {
                                    System.out.println("\n===== MENU DO PERSONAL =====");
                                    System.out.println("1. Ver Alunos Cadastrados");
                                    System.out.println("2. Criar ficha de treino");
                                    System.out.println("3. Atualizar Ficha de Treino");
                                    System.out.println("5. Visualizar Avaliações de um Aluno");
                                    System.out.println("6. Atualizar Perfil");
                                    System.out.println("7. Gerenciar Agenda");
                                    System.out.println("8. Visualizar Perfil");
                                    System.out.println("0. Sair");
                                    System.out.print("\nEscolha uma Opção: \n");

                                    opcPersonal = sc.nextInt();

                                    switch (opcPersonal) {
                                        case 1:
                                            System.out.println("\n=== ALUNOS ===");
                                            personalLogado.listarClientes();
                                            break;

                                        case 2:
                                            System.out.println("ID do aluno que deseja criar uma nova ficha: ");
                                            int idAluno = sc.nextInt();
                                            Cliente alunoFicha = personalLogado.buscarClienteId(idAluno);

                                            if (alunoFicha != null) {
                                                System.out.println("Digite o ID da nova ficha: ");
                                                int idFicha = sc.nextInt();
                                                System.out.println("Digite a descrição dos exercícios:");
                                                sc.nextLine(); // limpar buffer

                                                String exercicios = sc.nextLine();
                                                FichaTreino novaFicha = new FichaTreino(idFicha, exercicios);
                                                personalLogado.criarFichaTreino(alunoFicha, novaFicha);

                                                System.out.println("Ficha criada com sucesso!\n");
                                            } else {
                                                System.out.println("Aluno não encontrado!\n");
                                            }
                                            break;

                                        case 3:
                                            System.out.println("\nAtualizar ficha de qual aluno? Id:");
                                            int idAtualiza = sc.nextInt();
                                            // Correção da lógica de busca aqui
                                            Cliente clienteEncontrado = personalLogado.buscarClienteId(idAtualiza);

                                            if (clienteEncontrado != null) {
                                                int escolha = 10;
                                                do {
                                                    System.out.println("\nO que gostaria de atualizar?\n1- " +
                                                            "Adicionar exercício\n2- Remover exercício\n3- Voltar ");
                                                    escolha = sc.nextInt();
                                                    try {
                                                        switch (escolha) {
                                                            case 1:
                                                                System.out.print("Digite o nome do exercicio: ");
                                                                String nomeTreino = sc.next();
                                                                System.out.print("Carga usada (kg): ");
                                                                double cargaTreino = sc.nextDouble();
                                                                System.out.print("Quantas Repetições: ");
                                                                int repeticao = sc.nextInt();
                                                                // Nota: Verifique se registrarCarga existe no Profissional ou se deve ser no Cliente
                                                                clienteEncontrado.registrarCarga(nomeTreino, cargaTreino,
                                                                        repeticao);
                                                                break;
                                                            case 2:
                                                                clienteEncontrado.listarCargaComIndices(); // O usuário verá a lista numerada

                                                                System.out.print("Digite o NÚMERO (posição) do exercício que deseja remover: ");
                                                                int indiceParaRemover = sc.nextInt();

                                                                clienteEncontrado.removerCarga(indiceParaRemover);
                                                                break;
                                                            case 3:
                                                                break;
                                                        }
                                                    } catch (Exception e) {
                                                        System.out.println("Erro ao atualizar ficha: \n" + e.getMessage());
                                                    }
                                                } while (escolha != 3); // Alterado para sair no 3 (Voltar)
                                            } else {
                                                System.out.println("Aluno não encontrado na sua lista.\n");
                                            }
                                            break;

                                        case 4:
                                            try {
                                                personalLogado.visualizarAvaliacoesDosAlunos();
                                            }catch (Exception e){
                                                System.out.println("Nenhuma avaliação feita\n");
                                            }

                                            break;
                                        case 5: // ATUALIZAR PERFIL
                                            System.out.println("\n=== ATUALIZAR PERFIL ===");
                                            System.out.println("1. Dados Pessoais (Nome, Telefone, Email)");
                                            System.out.println("2. Alterar Senha");
                                            System.out.println("0. Voltar");
                                            System.out.print("Escolha: ");

                                            int subOpc = sc.nextInt();
                                            sc.nextLine(); // Limpa o buffer do teclado

                                            switch (subOpc) {
                                                case 1: // Dados Pessoais
                                                    System.out.println("\n=== ATUALIZAR DADOS PESSOAIS ===");
                                                    System.out.println("(Pressione ENTER para manter o valor atual)");

                                                    System.out.println("Nome atual: " + personal.getNome());
                                                    System.out.print("Novo nome: ");
                                                    String novoNome = sc.nextLine();
                                                    if (novoNome.isEmpty()) novoNome = personal.getNome();

                                                    System.out.println("Email atual: " + personal.getEmail());
                                                    System.out.print("Novo email: ");
                                                    String novoEmail = sc.nextLine();
                                                    if (novoEmail.isEmpty()) novoEmail = personal.getEmail();

                                                    System.out.println("Telefone atual: " + personal.getTelefone());
                                                    System.out.print("Novo telefone: ");
                                                    String novoTelefone = sc.nextLine();
                                                    if (novoTelefone.isEmpty()) novoTelefone = personal.getTelefone();

                                                    // Chama o método da classe pai (Pessoa)
                                                    personal.atualizarDados(novoNome, novoEmail, novoTelefone);
                                                    break;

                                                case 2: // Alterar Senha (agora é a opção 2)
                                                    System.out.println("\n=== ALTERAR SENHA ===");
                                                    System.out.print("Digite sua senha ATUAL: ");
                                                    String senhaAtual = sc.next(); // next() pois senha geralmente não tem espaço

                                                    if (personal.verificarSenha(senhaAtual)) {
                                                        System.out.print("Digite a NOVA senha: ");
                                                        String novaSenha = sc.next();
                                                        personal.alterarSenha(novaSenha);
                                                    } else {
                                                        System.out.println("Senha atual incorreta! Ação cancelada.");
                                                    }
                                                    break;

                                                case 0:
                                                    System.out.println("\nVoltando...\n");
                                                    break;

                                                default:
                                                    System.out.println("Opção inválida.");
                                            }
                                            break;

                                            case 6:
                                                System.out.println("\n=== GERENCIAR AGENDA ===\n");
                                                List<Agendamento> agenda = personal.getAgenda();
                                                System.out.println("1. Visualizar/Confirmar Agendamentos Pendentes");
                                                System.out.println("2. Cancelar Agendamento");
                                                System.out.println("3. Concluir Agendamento");
                                                System.out.println("0. Voltar");
                                                System.out.print("Escolha: ");

                                                int esc = sc.nextInt();
                                                sc.nextLine(); // Limpa buffer
                                                switch (esc) {
                                                    case 1:
                                                        if (agenda.isEmpty()) {
                                                            System.out.println("Agenda vazia.");
                                                        } else {
                                                            System.out.println("\n===LISTA DE AGENDAMENTOS===");
                                                            boolean temPendente = false;
                                                            for (int i = 0; i < agenda.size(); i++) {
                                                                Agendamento ag = agenda.get(i);
                                                                System.out.println("ID [" + i + "] - Data: " + ag.getData() +
                                                                        " | Hora: " + ag.getHorario() +
                                                                        " | Aluno: " + ag.getCliente().getNome() +
                                                                        " | Status: " + ag.getStatus());

                                                                if(ag.getStatus() == Agendamento.Status.AGENDADO) {
                                                                    temPendente = true;
                                                                }
                                                            }

                                                            if (temPendente) {
                                                                System.out.println("\nDeseja confirmar algum agendamento? (Digite o ID ou -1 para sair)");
                                                                int idConfirma = sc.nextInt();
                                                                if (idConfirma >= 0 && idConfirma < agenda.size()) {
                                                                    Agendamento agSelecionado = agenda.get(idConfirma);
                                                                    agSelecionado.confirmarAgendamento();
                                                                    System.out.println("Agendamento confirmado com sucesso!\n");
                                                                }
                                                            }
                                                        }
                                                        break;

                                                    case 2: // CANCELAR
                                                        if (agenda.isEmpty()) {
                                                            System.out.println("Nada para cancelar.\n");
                                                        } else {
                                                            System.out.println("Digite o ID do agendamento para CANCELAR: ");
                                                            for (int i = 0; i < agenda.size(); i++) {
                                                                Agendamento ag = agenda.get(i);
                                                                if (ag.getStatus() != Agendamento.Status.CANCELADO &&
                                                                        ag.getStatus() != Agendamento.Status.CONCLUIDO) {
                                                                    System.out.println("[" + i + "] " + ag.getData() + " - " + ag.getCliente().getNome());
                                                                }
                                                            }

                                                            int idCancel = sc.nextInt();
                                                            if (idCancel >= 0 && idCancel < agenda.size()) {
                                                                agenda.get(idCancel).cancelarAgendamento();
                                                                System.out.println("Agendamento cancelado.\n");
                                                            } else {
                                                                System.out.println("ID inválido.\n");
                                                            }
                                                        }
                                                        break;

                                                    case 3:
                                                        if (agenda.isEmpty()) {
                                                            System.out.println("Nada para concluir.\n");
                                                        } else {
                                                            System.out.println("Digite o ID do agendamento CONCLUÍDO (Aula dada): ");
                                                            for (int i = 0; i < agenda.size(); i++) {
                                                                Agendamento ag = agenda.get(i);
                                                                // Só pode concluir o que está Confirmado
                                                                if (ag.getStatus() == Agendamento.Status.CONFIRMADO) {
                                                                    System.out.println("[" + i + "] " + ag.getData() + " - " + ag.getCliente().getNome());
                                                                }
                                                            }

                                                            int idConcluir = sc.nextInt();
                                                            if (idConcluir >= 0 && idConcluir < agenda.size()) {
                                                                agenda.get(idConcluir).concluirAgendamento();
                                                                System.out.println("Agendamento marcado como concluído!");
                                                            } else {
                                                                System.out.println("ID inválido.\n");
                                                            }
                                                        }
                                                        break;

                                                    case 0:
                                                        break;

                                                    default:
                                                        System.out.println("Opção inválida.\n");
                                                }
                                                break;

                                        case 8:
                                            personalLogado.verPerfilProf();
                                            break;

                                        case 0:
                                            System.out.println("\nSaindo...\n");
                                            break;

                                        default:
                                            System.out.println("Opção inválida!\n");
                                    }
                                } while (opcPersonal != 0);
                            }
                            break;
                        }
                    }
                    if (!loginRealizado) {
                        System.out.println("Login Inválido: Email ou senha incorretos.\n");
                    }
                    break;

                case 2:
                    System.out.println("\n===TELA DE CADASTRO====");
                    System.out.print("1. Aluno\n" +
                            "2. Personal Trainer");
                    System.out.println("\nEscolha a modalidade: ");

                    String modalidade = sc.next().toLowerCase();
                    System.out.print("=====TELA DE CADASTRO=====\n" + "Digite seu nome: ");
                    String nome = sc.next();
                    System.out.print("Digite o cpf(apenas números): ");
                    String cpf = sc.next();
                    System.out.println("digite seu Sexo(M/F): ");
                    String sexo = sc.next();
                    System.out.print("Valor da Hora/Aula: ");
                    double valorAula = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Digite seu telefone: ");
                    String telefone = sc.next();
                    System.out.print("Digite seu email: ");
                    String email = sc.next();
                    System.out.print("Digite sua senha: ");
                    String senha = sc.next();

                    int id = (int) (Math.random() * (9999 - 1111 + 1)) + 1111;

                    if (modalidade.charAt(0) == '1') {
                        System.out.println("Digite sua idade: ");
                        int idade = sc.nextInt();
                        System.out.println("digite seu peso");
                        double peso = sc.nextDouble();
                        System.out.println("digite sua altura: ");
                        double altura = sc.nextDouble();
                        System.out.println("digite seu objetivo:  ");
                        String objetivo = sc.next();
                        System.out.println("Possui alguma restrição fisica?\n1-SIM\n2-NÃO");
                        int choice = sc.nextInt();

                        if (choice == 1) {
                            System.out.println("Qual: ");
                            String restricaoFisica = sc.next();
                            cadastros.add(new Cliente(id, nome, cpf, telefone, email, senha, sexo, idade, peso, altura,
                                    objetivo, restricaoFisica));
                            System.out.println();
                        } else {
                            cadastros.add(new Cliente(id, nome, cpf, telefone, email, senha, sexo, idade, peso, altura,
                                    objetivo));
                        }
                    } else {
                        System.out.print("Especialidade: ");
                        String especialidade = sc.next();
                        sc.nextLine();
                        System.out.print("Número de registro: ");
                        int registro = sc.nextInt();
                        sc.nextLine();
                        cadastros.add(new Profissional(id, nome, cpf, telefone, email, senha, sexo, especialidade,
                                registro, personal.getValorAula()));
                    }
                    System.out.println("Cadastrado com sucesso!\nSeja Bem Vindo!\n");
                    break;

                case 3:
                    System.out.println("Encerrando aplicação...\n");
                    break;

                default:
                    System.out.println("Opção Inválida!\n");
            }

        } while (opcao != 3);
    }
}