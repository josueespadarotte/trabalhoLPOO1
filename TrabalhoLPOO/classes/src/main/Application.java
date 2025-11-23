package main;

import classes.Cliente;
import classes.FichaTreino;
import classes.Pessoa;
import classes.Profissional;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<Pessoa> cadastros = new ArrayList<>();
        cadastros.add(new Profissional(1221, "Dr.Davi Brito", "726394860032", "27999362832", "Davibrito_0@gmail.com",
                "5382", "M", "fisioterapeuta", 4342342));
        cadastros.add(new Cliente(213, "Andrea Horta", "123.456.789-00", "1231123554", "andrea@email.com",
                "senha123", "F", 30, 65.5, 1.65, "Hipertrofia", "Nenhuma"));


        System.out.println("===TELA DE LOGIN===");
        int opcao;
        do {
            System.out.println("1.Login" +
                    "\n2.Cadastro" +
                    "\n3.Sair" +
                    "\nOpcção:");
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
                        if (p.getEmail().equals(emailLogin) && p.verificarSenha(senhaLogin)) {
                            loginRealizado = true;

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
                                                break;
                                            }catch (Exception e) {
                                                System.out.println("Ficha vazia!");
                                                break;
                                            }

                                        case 3:
                                            System.out.println("Digite o nome do exercício:");
                                            String nomeEx = sc.next();
                                            System.out.println("Carga usada (kg):");
                                            double carga = sc.nextDouble();

                                            alunoLogado.registrarCarga(nomeEx, carga,);
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
                                            alunoLogado.atualizarMedidas(peso, braco, cintura,altura);
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
                            }
                        } else if (p instanceof Profissional) {
                            System.out.println("Bem-vindo, Personal " + p.getNome());
                            Profissional personalLogado = (Profissional) p;

                            int opcPersonal;

                            do {
                                System.out.println("\n===== MENU DO PERSONAL =====");
                                System.out.println("1. Ver alunos cadastrados");
                                System.out.println("2. Criar ficha de treino");
                                System.out.println("3. Atualizar ficha de treino");
                                System.out.println("4. Registrar avaliação física");
                                System.out.println("5. Visualizar avaliações de um aluno");
                                System.out.println("6. Atualizar perfil");
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
                                            sc.nextLine();

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
                                        for(Pessoa serch : cadastros){
                                            int escolha = 10;
                                            if(serch.getId() == idAtualiza) {
                                                do {
                                                    System.out.println("O que gostaria de atualizar?1- Adicionar exercício\n 2 ");
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
                                                        }
                                                    } catch (Exception e) {
                                                        System.out.println("Erro ao criar ficha!");
                                                    }
                                            }while (escolha != 0);

                                        Cliente alunoAtualiza = personalLogado.buscarClienteId();

                                        if (alunoAtualiza != null) {
                                            personalLogado.atualizarFichaTreino(alunoAtualiza, sc);
                                            System.out.println("Ficha atualizada!");
                                        } else {
                                            System.out.println("Aluno não encontrado!");
                                        }
                                        break;

                                        // avaliação de aluno
                                    case 4:
                                        System.out.println("Avaliar qual aluno? Email:");
                                        String iDAv = sc.next();
                                        Cliente alunoAval = personalLogado.buscarClienteId(iDAv);

                                        if (alunoAval != null) {
                                            personalLogado.registrarAvaliacao(alunoAval, sc);
                                            System.out.println("Avaliação registrada!");
                                        } else {
                                            System.out.println("Aluno não encontrado!");
                                        }
                                        break;

                                    case 5:
                                        System.out.println("Ver avaliações de qual aluno? Email:");
                                        String emailVerAval = sc.next();
                                        Cliente alunoVerAval = personalLogado.buscarAluno(emailVerAval);

                                        if (alunoVerAval != null) {
                                            alunoVerAval.getMedida();
                                        } else {
                                            System.out.println("Aluno não encontrado!");
                                        }
                                        break;

                                    case 6:
                                        personalLogado.atualizarPerfil(sc);
                                        System.out.println("Perfil atualizado!");
                                        break;

                                    case 0:
                                        System.out.println("Saindo...");
                                        break;

                                    default:
                                        System.out.println("Opção inválida!");
                                }

                            }
                        } while (opcPersonal != 0);

                        }

                    }
                    System.out.println("aqui");

                    if (!loginRealizado) {
                        System.out.println("Login Inválido: Email ou senha incorretos.");
                    }
                    break;

                case 2:
                    System.out.println("===Tela de Cadastro====");
                    System.out.println("Escolha a modalidade:");
                    System.out.print("1. Aluno\n" +
                            "2. Personal Trainer");
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
                        } else
                            cadastros.add(new Cliente(id, nome, cpf, telefone, email, senha, sexo, idade, peso, altura, objetivo));
                    } else {
                        System.out.print("Especialidade: ");
                        String especialidade = sc.next();
                        System.out.print("Número de registro: ");
                        int registro = sc.nextInt();
                        cadastros.add(new Profissional(id, nome, cpf, telefone, email, senha, sexo, especialidade, registro));
                    }
                    System.out.println("Cadastrado com sucesso!\nSeja Bem Vindo!");

                case 3:
                    break;
                default:
                    System.out.println("Opcção Inváilida!");
            }
          }  while (opcao != 3) ;
    }
}
