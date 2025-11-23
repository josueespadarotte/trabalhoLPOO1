package main;

import classes.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Pessoa.exercicies> listaexercicio = new ArrayList<>();
        List<Pessoa> cadastros = new ArrayList<>();

        Profissional personalDavi = new Profissional(1221, "Dr.Davi Brito", "726394860032", "27999362832",
                "Davibrito_0@gmail.com", "5382", "M", "fisioterapeuta", 4342342);

        Cliente clienteAndrea = new Cliente(213, "Andrea Horta", "123.456.789-00", "1231123554",
                "andrea@email.com", "senha123", "F", 30, 65.5, 1.65,
                "Hipertrofia", "Nenhuma");

        personalDavi.cadastrarCliente(clienteAndrea);

        cadastros.add(personalDavi);
        cadastros.add(clienteAndrea);

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
                    System.out.println("===Tela de Login====");
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
                                    System.out.println("\n===== MENU DO CLIENTE =====");
                                    System.out.println("1. Agendar personal");
                                    System.out.println("2. Visualizar Treinos");
                                    System.out.println("3. Registrar Carga em Exercício");
                                    System.out.println("4. Visualizar Avaliações Físicas");
                                    System.out.println("5. Atualizar Medidas");
                                    System.out.println("6. Atualizar Perfil");
                                    System.out.println("0. Sair");
                                    System.out.print("Escolha uma opção: ");

                                    opcCliente = sc.nextInt();

                                    switch (opcCliente) {
                                        case 2:
                                            try {
                                                System.out.println("=== SEUS TREINOS ===");
                                                alunoLogado.visualizarFichaTreino();
                                            } catch (Exception e) {
                                                System.out.println("Ficha vazia!");
                                            }
                                            break;

                                        case 3:
                                            System.out.println("Digite o nome do exercício:");
                                            String nomeEx = sc.next();
                                            System.out.println("Carga usada (kg):");
                                            double carga = sc.nextDouble();
                                            // Ajuste conforme seu método registrarCarga (se pedir reps, adicione)
                                            alunoLogado.registrarCarga(nomeEx, carga, 0);
                                            System.out.println("Carga registrada!");
                                            break;

                                        case 4:
                                            System.out.println("=== AVALIAÇÕES ===");
                                            alunoLogado.getMedida();
                                            break;

                                        case 5:
                                            System.out.println("Novo peso:");
                                            double peso = sc.nextDouble();
                                            System.out.println("Medida braço:");
                                            double braco = sc.nextDouble();
                                            System.out.println("Medida cintura:");
                                            double cintura = sc.nextDouble();
                                            System.out.println("Altura:");
                                            double altura = sc.nextDouble();
                                            alunoLogado.atualizarMedidas(peso, braco, cintura, altura);
                                            System.out.println("Medidas atualizadas!");
                                            break;

                                        case 6:
                                            alunoLogado.verProgresso();
                                            break;

                                        case 7:
                                            System.out.println(cadastros.toString());
                                            break;

                                        case 0:
                                            System.out.println("Saindo...");
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
                                    System.out.println("1. Ver alunos cadastrados");  // ERRO
                                    System.out.println("2. Criar ficha de treino");  // erro apos digitar o id do aluno
                                    System.out.println("3. Atualizar ficha de treino");
                                    System.out.println("4. Registrar avaliação física"); // erro apos digitar o id do aluno, provavemmente
                                    // pq andrea esta cadastrada na lista de cadastros e nao na lista clientes de davi
                                    System.out.println("5. Visualizar avaliações de um aluno");
                                    System.out.println("6. Atualizar perfil");
                                    System.out.println("7. Gerenciar agenda");
                                    System.out.println("0. Sair");
                                    System.out.print("Escolha uma opção: ");

                                    opcPersonal = sc.nextInt();

                                    switch (opcPersonal) {
                                        case 1:
                                            System.out.println("=== ALUNOS ===");
                                            personalLogado.listarClientes();
                                            break;

                                        case 2:
                                            System.out.println("ID do aluno que deseja criar uma nova ficha:");
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

                                                System.out.println("Ficha criada com sucesso!");
                                            } else {
                                                System.out.println("Aluno não encontrado!");
                                            }
                                            break;

                                        case 3:
                                            System.out.println("Atualizar ficha de qual aluno? Id:");
                                            int idAtualiza = sc.nextInt();
                                            // Correção da lógica de busca aqui
                                            Cliente clienteEncontrado = personalLogado.buscarClienteId(idAtualiza);

                                            if (clienteEncontrado != null) {
                                                int escolha = 10;
                                                do {
                                                    System.out.println("O que gostaria de atualizar?\n1- " +
                                                            "Adicionar exercício\n2- Remover exercício\n3- Voltar ");
                                                    escolha = sc.nextInt();
                                                    try {
                                                        switch (escolha) {
                                                            case 1:
                                                                System.out.print("Digite o nome do exercicio: ");
                                                                String nomeTreino = sc.next();
                                                                System.out.print("Carga usada (kg):");
                                                                double cargaTreino = sc.nextDouble();
                                                                System.out.print("Quantas Repetições:");
                                                                int repeticao = sc.nextInt();
                                                                // Nota: Verifique se registrarCarga existe no Profissional ou se deve ser no Cliente
                                                                personalLogado.registrarCarga(nomeTreino, cargaTreino,
                                                                        repeticao);
                                                                break;
                                                            case 2:
                                                                if (!listaexercicio.isEmpty()) {
                                                                    for (int i = 0; i < listaexercicio.size(); i++) {
                                                                        System.out.println("exercicio #" + i + " "
                                                                                + listaexercicio.get(i).toString());
                                                                    }
                                                                    System.out.println("Qual deseja remover: N°");
                                                                    int escolha2 = sc.nextInt();
                                                                    if(escolha2 >= 0 && escolha2 < listaexercicio.size()) {
                                                                        listaexercicio.remove(escolha2);
                                                                        System.out.println("Removido com sucesso");
                                                                    } else {
                                                                        System.out.println("Índice inválido");
                                                                    }
                                                                } else {
                                                                    System.out.println("lista vazia");
                                                                }
                                                                break;
                                                            case 3:
                                                                break;
                                                        }
                                                    } catch (Exception e) {
                                                        System.out.println("Erro ao atualizar ficha: " + e.getMessage());
                                                    }
                                                } while (escolha != 3); // Alterado para sair no 3 (Voltar)
                                            } else {
                                                System.out.println("Aluno não encontrado na sua lista.");
                                            }
                                            break;

                                        case 4:
                                            System.out.println("==== Avaliar Aluno ====");
                                            System.out.println("ID do Aluno:");
                                            int idBusca = sc.nextInt();
                                            Cliente alunoAval = personalLogado.buscarClienteId(idBusca);

                                            if (alunoAval != null) {
                                                int nota = -1;
                                                boolean notaValida = false;
                                                while (!notaValida) {
                                                    System.out.println("Nota (0 a 5): ");
                                                    if (sc.hasNextInt()) {
                                                        nota = sc.nextInt();
                                                        if (nota >= 0 && nota <= 5) {
                                                            notaValida = true;
                                                        } else {
                                                            System.out.println("Erro: A nota deve ser entre 0 e 5.");
                                                        }
                                                    } else {
                                                        System.out.println("Erro: Digite apenas números inteiros.");
                                                        sc.next();
                                                    }
                                                }

                                                System.out.println("Data (dd/MM/yyyy): ");
                                                String dataTexto = sc.next();

                                                try {
                                                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                                                    java.util.Date dataObjeto = formato.parse(dataTexto);
                                                    Avaliacao novaAvaliacao = new Avaliacao(nota, dataObjeto);
                                                    personalLogado.registrarAvaliacao(alunoAval, novaAvaliacao);
                                                } catch (Exception e) {
                                                    System.out.println("Erro: Data inválida! Use o formato dia/mês/ano" +
                                                            " (ex: 25/12/2023).");
                                                }
                                            } else {
                                                System.out.println("Aluno não encontrado!");
                                            }
                                            break;

                                        case 5:
                                            personalLogado.visualizarAvaliacoesDosAlunos();
                                            break;
                                            /*case 6:

                                            System.out.println("===Dados pessoais===");
                                            System.out.println("qual dado gostaria de alterar:1-Disponibilidade da agenda\n2- Informações pessoais");
                                            int escolha = sc.nextInt();
                                            switch (escolha) {
                                         case 1:
                                            }
                                            System.out.println("Perfil atualizado!");
                                            break;

                                            case 7:
                                            ArrayList<Agendamento> agenda = new ArrayList<>();
                                            System.out.println("1. Agendamento\n2. Cancelar agendamento\n3. Concluir agendamento");
                                            int esc = sc.nextInt();
                                            switch (esc) {
                                            }

                                            */

                                        case 0:
                                            System.out.println("Saindo...");
                                            break;

                                        default:
                                            System.out.println("Opção inválida!");
                                    }
                                } while (opcPersonal != 0);
                            }
                            break;
                        }
                    }
                    if (!loginRealizado) {
                        System.out.println("Login Inválido: Email ou senha incorretos.");
                    }
                    break;

                case 2:
                    System.out.println("===Tela de Cadastro====");
                    System.out.print("1. Aluno\n" +
                            "2. Personal Trainer");
                    System.out.println("\nEscolha a modalidade:");

                    String modalidade = sc.next().toLowerCase();
                    System.out.print("=====Tela de cadastro=====\n" + "Digite seu nome: ");
                    String nome = sc.next();
                    System.out.print("Digite o cpf(apenas números): ");
                    String cpf = sc.next();
                    System.out.println("digite seu Sexo(M/F): ");
                    String sexo = sc.next();
                    System.out.print("Digite seu telefone: ");
                    String telefone = sc.next();
                    System.out.print("Digite seu email: ");
                    String email = sc.next();
                    System.out.print("Digite sua senha: ");
                    String senha = sc.next();

                    int id = (int) (Math.random() * (9999 - 1111 + 1)) + 1111;

                    if (modalidade.charAt(0) == '1') {
                        System.out.println("Digite sua idade");
                        int idade = sc.nextInt();
                        System.out.println("digite seu peso");
                        double peso = sc.nextDouble();
                        System.out.println("digite sua altura");
                        double altura = sc.nextDouble();
                        System.out.println("digite seu objetivo");
                        String objetivo = sc.next();
                        System.out.println("Possui alguma restrição fisica?\n1-SIM\n2-NÃO");
                        int choice = sc.nextInt();

                        if (choice == 1) {
                            System.out.println("Qual:");
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
                                registro));
                    }
                    System.out.println("Cadastrado com sucesso!\nSeja Bem Vindo!");
                    break;

                case 3:
                    System.out.println("Encerrando aplicação...");
                    break;

                default:
                    System.out.println("Opção Inválida!");
            }

        } while (opcao != 3);
    }
}