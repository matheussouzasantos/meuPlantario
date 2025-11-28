import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MeuPlantario {
    private static Formatter arqSaida;
    private static Scanner arqEnt;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int menu;


        // cadastraPlanta();
        // fechaArqEsc();
        // abreArqLeitura();
        // leRegistro();
        // fechaArqLeit();
        abreArqEscrita();
        System.out.println("\n=== Meu Plantário ===");
        System.out.println("1 - Cadastro de Plantas");
        System.out.println("2 - Listar Plantas");
        System.out.println("3 - Remover Plantas");
        System.out.println("4 - Alterar Cadastro de Plantas");
        System.out.println("5 - Regar");
        System.out.println("6 - Adubar");
        System.out.println("7 - Ver Lembretes");
        System.out.println("8 - Sugestão de plantas");
        System.out.println("9 - Sair");
        System.out.println("---------------------");
        System.out.print("Opção: ");
        menu = sc.nextInt();
        while (menu > 9 && menu < 1) {
            System.out.println("ERRO. digite uma opção válida.");
            menu = sc.nextInt();
        }

        switch (menu) {
            case 1:
                System.out.print("\n===== CADASTRO DE PLANTAS ======\n");
                cadastraPlanta();
                break;
            case 2:
                System.out.print("\n===== LISTAGEM DE PLANTAS ======\n");
                // função
                break;
            case 3:
                System.out.print("\n===== REMOÇÃO DE CADASTRO ======\n");
                // função
                break;
            case 4:
                System.out.print("\n===== ALTERAÇÃO DE PLANTAS =====\n");
                // função
                break;
            case 5:
                System.out.print("\n===== REGAR PLANTA =====\n");
                // função
                break;
            case 6:
                System.out.print("\n===== ADUBAR PLANTA =====\n");
                // função
                break;
            case 7:
                System.out.print("\n===== LEMBRETES =====\n");
                // função
                break;
            case 8:
                System.out.print("\n===== SUGESTÃO DE PLANTAS =====\n");
                // função
                break;
            default:
                System.out.print("Encerrando...");
        }
        sc.close();
    }

    public static void abreArqEscrita() {
        try {
            FileWriter fw = new FileWriter("plantas.txt", true);
            arqSaida = new Formatter(fw);
        } catch (SecurityException securityException) {
            System.err.println("Permissão de Escrita Negada. Fechando...");
            System.exit(1);
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Erro ao abrir o arquivo. Fechando...");
            System.exit(1);
        } catch (IOException ioException) {
            System.err.println("Erro de IO ao abrir/usar arquivo. Fechando...");
            System.exit(1);
        }
    }

    public static void abreArqLeitura() {
        try {
            arqEnt = new Scanner(new File("plantas.txt"));
        } catch (FileNotFoundException fileNotFoundException) {
            System.err.println("Erro na abertura do arquivo para leitura.");
            System.exit(1);
        }
    }

    public static void cadastraPlanta() {
        Scanner scCadastro = new Scanner(System.in);
        int switchRega, switchTipo, intervaloRega, intervaloAdubo, qtdAdubo;
        String tipoRega = "", tipoAdubo = "", tipoPlanta = "";

        try {
            System.out.print("Digite o nome da planta: ");
            String nome = scCadastro.next();
            scCadastro.nextLine();

            System.out.print(
                    "Planta de:" +
                    "\n   1 - Sombra" +
                    "\n   2 - Meia-sombra" +
                    "\n   3 - Sol" +
                    "\nOpção: ");
            switchTipo = scCadastro.nextInt();
            while (switchTipo < 1 || switchTipo > 3) {
                System.out.print("Tipo de planta inválido. Digite novamente: ");
                switchTipo = scCadastro.nextInt();
            }
            switch (switchTipo) {
                case 1:
                    tipoPlanta = "sombra";
                    break;
                case 2:
                    tipoPlanta = "meia-sombra";
                    break;
                default:
                    tipoPlanta = "sol";
            }


            System.out.print("Digite o intervalo de rega (dias): ");
            intervaloRega = scCadastro.nextInt();
            while (intervaloRega <= 0) {
                System.out.print("Intervalo inválido. Digite um número positivo: ");
                intervaloRega = scCadastro.nextInt();
            }

            System.out.print("Digite o intervalo de adubagem (dias): ");
            intervaloAdubo = scCadastro.nextInt();
            while (intervaloAdubo <= 0) {
                System.out.print("Intervalo inválido. Digite um número positivo: ");
                intervaloAdubo = scCadastro.nextInt();
            }

            System.out.println("Digite o tipo de adubo: ");
            scCadastro.nextLine();
            tipoAdubo = scCadastro.nextLine();

            System.out.print(
                    "Qual das regas é feita?" +
                    "\n   1 - Rega baixa" +
                    "\n   2 - Rega média" +
                    "\n   3 - Rega alta" +
                    "\nOpção: ");
            switchRega = scCadastro.nextInt();
            while (switchRega < 1 || switchRega > 3) {
                System.out.print("Tipo de rega inválido. Digite novamente: ");
                switchRega = scCadastro.nextInt();
            }
            switch (switchRega) {
                case 1:
                    tipoRega = "baixa";
                    break;
                case 2:
                    tipoRega = "media";
                    break;
                default:
                    tipoRega = "alta";
            }

            arqSaida.format("%s;%s;%d;%d;%s;%s;%n", nome, tipoPlanta, intervaloRega, intervaloAdubo, tipoRega, tipoAdubo);
            fechaArqEsc();
            System.out.printf("%nPlanta '%s' foi cadastrada com sucesso!%n", nome);

        } catch (FormatterClosedException formatterClosedException) {
            System.err.println("Erro de escrita no arquivo. Finalizando...");
        } catch (NoSuchElementException elementException) {
            System.err.println("Entrada inválida ou finalizada inesperadamente. Digite novamente.");
            scCadastro.nextLine();
        }
    }

    public static void listaPlantas() {
        abreArqLeitura();
    }

    public static void alteraPlanta() {
        Scanner sc = new Scanner(System.in);
        String [] linhas = new String[100];
        String nomeBusca;
        int total = 0;
        boolean encontrado = false;

        leRegistro();
    }

    public static void leRegistro() {
        try {
            System.out.printf("%-12s %-12s %-14s %-6s%n", "Nome", "Rega(dias)", "Aduba(dias)", "Tipo");
            while (arqEnt.hasNext()) {
                String nome = arqEnt.next();
                int reg = arqEnt.nextInt();
                int adu = arqEnt.nextInt();
                int tipo = arqEnt.nextInt();

                System.out.printf("%-12s %-12d %-14d %-6d%n", nome, reg, adu, tipo);
            }
        } catch (NoSuchElementException elementException) {
            System.err.println("Arquivo corrompido.");
            arqEnt.close();
            System.exit(1);
        } catch (IllegalStateException stateException) {
            System.err.println("Erro na leitura do arquivo.");
            System.exit(1);
        }
    }

    public static void fechaArqEsc() {
        if (arqSaida != null) {
            arqSaida.close();
        }
    }

    public static void fechaArqLeit() {
        if (arqEnt != null) {
            arqEnt.close();
        }
    }
}