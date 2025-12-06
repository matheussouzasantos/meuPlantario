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

        do {
            // cadastraPlanta();
            // fechaArqEsc();
            // leRegistro();
            // fechaArqLeit();
            abreArqEscrita();
            System.out.println("\n===== Meu Plantário =====");
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
                    listaPlantas();
                    break;
                case 3:
                    System.out.print("\n===== REMOÇÃO DE CADASTRO ======\n");
                    removePlanta();
                    break;
                case 4:
                    System.out.print("\n===== ALTERAÇÃO DE PLANTAS =====\n");
                    alteraPlanta();
                    break;
                case 5:
                    System.out.print("\n===== REGAR PLANTA =====\n");
                    regaPlanta();
                    break;
                case 6:
                    System.out.print("\n===== ADUBAR PLANTA =====\n");
                    adubaPlanta();
                    break;
                case 7:
                    System.out.print("\n===== LEMBRETES =====\n");
                    abreLembrete();
                    break;
                case 8:
                    System.out.print("\n===== SUGESTÃO DE PLANTAS =====\n");
                    sugestaoPlantas();
                    break;
                default:
                    System.out.print("Encerrando...");
            }
        } while (menu != 9);
        fechaArqEscrita();
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
        int switchRega, switchTipo, intervaloRega, intervaloAdubo;
        String tipoRega = "", tipoAdubo = "", luminosidade = "", nome;

        try {
            System.out.print("Digite o nome da planta: ");
            nome = scCadastro.nextLine().trim();

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
                    luminosidade = "sombra";
                    break;
                case 2:
                    luminosidade = "meia-sombra";
                    break;
                default:
                    luminosidade = "sol";
            }


            System.out.print("Digite o intervalo de rega (dias): ");
            intervaloRega = scCadastro.nextInt();
            while (intervaloRega <= 0) {
                System.out.print("Intervalo inválido. Digite um número positivo: ");
                intervaloRega = scCadastro.nextInt();
            }

            System.out.print("Digite o intervalo de adubagem em dias: ");
            intervaloAdubo = scCadastro.nextInt();
            while (intervaloAdubo <= 0) {
                System.out.print("Intervalo inválido. Digite um número positivo: ");
                intervaloAdubo = scCadastro.nextInt();
            }

            System.out.print("Digite o tipo de adubo: ");
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


            abreArqEscrita();
            arqSaida.format("%s;%s;%d;%d;%s;%s;%n", nome, luminosidade, intervaloRega, intervaloAdubo, tipoRega, tipoAdubo);
            fechaArqEscrita();
            System.out.printf("%nPlanta '%s' foi cadastrada com sucesso!%n", nome);
            fechaArqEscrita();
        } catch (FormatterClosedException formatterClosedException) {
            System.err.println("Erro de escrita no arquivo. Finalizando...");
        } catch (NoSuchElementException elementException) {
            System.err.println("Entrada inválida ou finalizada inesperadamente. Digite novamente.");
            scCadastro.nextLine();
        }
    }

    public static void listaPlantas() {
        Scanner scLista = new Scanner(System.in);
        int switchLista;
        String filtroTipo = "";

        System.out.print(
                "\nOpções de listagem: " +
                        "\n   1 - Listar todas as plantas: " +
                        "\n   2 - Listar plantas de sombra: " +
                        "\n   3 - Listar plantas de meia-sombra: " +
                        "\n   4 - Listar plantas de sol: " +
                        "\nOpção: ");
        switchLista = scLista.nextInt();
        while (switchLista < 1 || switchLista > 4) {
            System.out.print("Opção inexistente. Digite novamente: ");
            switchLista = scLista.nextInt();
        }

        switch (switchLista) {
            case 1:
                filtroTipo = "todas";
                break;
            case 2:
                filtroTipo = "sombra";
                break;
            case 3:
                filtroTipo = "meia-sombra";
                break;
            default:
                filtroTipo = "sol";
        }
        exibeListaPlantas(filtroTipo);
    }

    public static void exibeListaPlantas(String filtro) {
        String[][] matriz = new String[100][6];
        int total = 0;
        abreArqLeitura();
        if (arqEnt == null) {
            System.out.println("Arquivo plantas.txt não encontrado.");
        } else {
            while (arqEnt.hasNextLine() && total < 100) {
                String linha = arqEnt.nextLine().trim();
                if (linha.isEmpty() == false) {
                    String[] partes = linha.split(";");
                    for (int i = 0; i < 6; i++) {
                        if (i < partes.length) {
                            matriz[total][i] = partes[i].trim();
                        }
                    }
                    total++;
                }
            }
            try {
                if (total == 0) {
                    System.out.println("\n=====================================================");
                    if (filtro.equals("todas")) {
                        System.out.println("Nenhuma planta cadastrada ainda.");
                    } else {
                        System.out.printf("Nenhuma planta do tipo '%s' encontrada.\n", filtro);
                    }
                    System.out.println("=====================================================");
                } else {
                    String[][] matrizFiltrada = new String[total][6];
                    int totalFiltrado = 0;
                    System.out.println("\n=====================================================");
                    if (filtro.equals("todas")) {
                        System.out.println("LISTAGEM DE TODAS AS PLANTAS");
                    } else {
                        System.out.println("LISTAGEM DE PLANTAS DE " + filtro.toUpperCase());
                    }
                    System.out.println("==========================================================================================================");
                    System.out.printf("%-22s %-15s %-12s %-15s %-12s %-20s%n",
                            "Nome", "Tipo", "Rega(dias)", "Adubo(dias)", "Tipo Rega", "Tipo Adubo");
                    System.out.println("----------------------------------------------------------------------------------------------------------");

                    for (int i = 0; i < total; i++) {
                        String luminosidade = matriz[i][1]; // coluna 1 = luminosidade
                        if (filtro.equals("todas") || luminosidade.equals(filtro)) {
                            matrizFiltrada[totalFiltrado] = matriz[i];
                            totalFiltrado++;
                        }
                    }

                    for (int i = 0; i < totalFiltrado; i++) {
                        String nome = matrizFiltrada[i][0];
                        String tipoPlanta = matrizFiltrada[i][1];
                        String intervaloRega = matrizFiltrada[i][2];
                        String intervaloAdubo = matrizFiltrada[i][3];
                        String tipoRega = matrizFiltrada[i][4];
                        String tipoAdubo = matrizFiltrada[i][5];

                        System.out.printf("%-22s %-15s %-12s %-15s %-12s %-20s%n",
                                nome, tipoPlanta, intervaloRega, intervaloAdubo,
                                tipoRega, tipoAdubo);
                    }

                    System.out.println("----------------------------------------------------------------------------------------------------------");
                    System.out.println("Total de plantas listadas: " + totalFiltrado);
                    System.out.println("==========================================================================================================");

                }
            } catch (NoSuchElementException elementException) {
                System.err.println("Erro ao ler os dados do arquivo.");
            } catch (IllegalStateException stateException) {
                System.err.println("Erro na leitura do arquivo.");
            } catch (NumberFormatException numberException) {
                System.err.println("Arquivo com formato incorreto.");
            }
        }
        fechaArqLeitura();
    }

    public static void removePlanta() {
        Scanner scRemove = new Scanner(System.in);
        String[][] matriz = new String[100][6];
        int total = 0;

        abreArqLeitura();
        while (arqEnt.hasNextLine() && total < 100) {
            String linha = arqEnt.nextLine().trim();
            if (!linha.isEmpty()) {
                String[] partes = linha.split(";");
                for (int j = 0; j < 6; j++) {
                    if (j < partes.length && partes[j] != null) {
                        matriz[total][j] = partes[j].trim();
                    }
                }
                total++;
            }
        }
        fechaArqLeitura();

        exibePlantasCadastradas();

        if (total > 0) {
            int escolha;
            System.out.print("Digite o NÚMERO da planta para remover: ");
            escolha = scRemove.nextInt();
            while (escolha < 1 || escolha > total) {
                System.out.print("Número inválido. Digite novamente: ");
                escolha = scRemove.nextInt();
            }
            int posicao = escolha - 1;
            String nomeRemovido = matriz[posicao][0];

            matriz[posicao] = null;
            try {
                Formatter arqNovo = new Formatter("plantas.txt");
                for (int i = 0; i < total; i++) {
                    if (matriz[i] != null) {
                        arqNovo.format("%s;%s;%s;%s;%s;%s;%n",
                                matriz[i][0], matriz[i][1], matriz[i][2],
                                matriz[i][3], matriz[i][4], matriz[i][5]
                        );
                    }
                }
                arqNovo.close();
                System.out. println("Planta '" + nomeRemovido + "' removida!");
            } catch (Exception e) {
                System.err.println("Erro ao gravar arquivo.");
            }
        }
    }

    public static void alteraPlanta() {
        Scanner scAltera = new Scanner(System.in);
        String[][] matriz = new String[100][6];
        int total = 0, posicaoPlanta  = -1, plantaEscolhida;

        abreArqLeitura();
        while (arqEnt.hasNextLine() && total < 100) {
            String linha = arqEnt.nextLine().trim();
            if (!linha.isEmpty()) {
                String[] partes = linha.split(";");
                for (int j = 0; j < 6; j++) {
                    if (j < partes.length && partes[j] != null) {
                        matriz[total][j] = partes[j].trim();
                    }
                }
                total++;
            }
        }
        fechaArqLeitura();

        exibePlantasCadastradas();

        if (total == 0) {
            System.out.println("\nPlanta não encontrada.\n");
        } else {
            String nome, luminosidade, tipoRega, tipoAdubo;
            int intervaloRega, intervaloAdubo, switchAltera;
            System.out.print("Digite o NÚMERO da planta que deseja alterar: ");
            plantaEscolhida = scAltera.nextInt();
            scAltera.nextLine();

            while (plantaEscolhida < 1 || plantaEscolhida > total) {
                System.out.println("Número inválido. Digite novamente: ");
                plantaEscolhida = scAltera.nextInt();
            }

            posicaoPlanta = plantaEscolhida - 1;

            nome = matriz[posicaoPlanta][0];
            luminosidade = matriz[posicaoPlanta][1];
            intervaloAdubo = Integer.parseInt(matriz[posicaoPlanta][3]);
            intervaloRega = Integer.parseInt(matriz[posicaoPlanta][2]);
            tipoRega = matriz[posicaoPlanta][4];
            tipoAdubo = matriz[posicaoPlanta][5];

            System.out.println("\nPlanta encontrada:");
            System.out. printf("Nome: %s | Luminosidade: %s | Rega: %d dias | Adubo: %d dias | Tipo Rega: %s | Tipo Adubo: %s%n",
                    nome, luminosidade, intervaloRega, intervaloAdubo, tipoRega, tipoAdubo);

            System.out.println("\nO que deseja alterar?");
            System.out.println("1 - Nome");
            System.out.println("2 - Luminosidade (sol/meia-sombra/sombra)");
            System.out.println("3 - Intervalo de rega (dias)");
            System.out.println("4 - Intervalo de adubo (dias)");
            System.out.println("5 - Tipo de rega (baixa/media/alta)");
            System.out.println("6 - Tipo de adubo");
            System.out.print("Opção: ");
            switchAltera = scAltera.nextInt();
            scAltera.nextLine();
            while (switchAltera < 1 || switchAltera > 6) {
                System.out. print("Opção inválida. Digite novamente: ");
                switchAltera = scAltera.nextInt();
                scAltera.nextLine();
            }

            switch (switchAltera) {
                case 1:
                    System.out.print("Novo nome: ");
                    nome = scAltera.nextLine().trim();
                    break;
                case 2:
                    System.out.println("Nova luminosidade:");
                    System.out.print(
                            "Planta de:" +
                                    "\n   1 - Sombra" +
                                    "\n   2 - Meia-sombra" +
                                    "\n   3 - Sol" +
                                    "\nOpção: ");
                    switchAltera = scAltera.nextInt();
                    scAltera.nextLine();
                    while (switchAltera < 1 || switchAltera > 3) {
                        System.out.print("Tipo de planta inválido. Digite novamente: ");
                        switchAltera = scAltera. nextInt();
                        scAltera.nextLine();
                    }
                    switch (switchAltera) {
                        case 1:
                            luminosidade = "sombra";
                            break;
                        case 2:
                            luminosidade = "meia-sombra";
                            break;
                        default:
                            luminosidade = "sol";
                    }
                    break;
                case 3:
                    System.out.print("Novo intervalo de rega (dias): ");
                    intervaloRega = scAltera.nextInt();
                    scAltera.nextLine();
                    while (intervaloRega <= 0) {
                        System.out.print("Intervalo inválido. Digite um número positivo: ");
                        intervaloRega = scAltera.nextInt();
                        scAltera.nextLine();
                    }
                    break;
                case 4:
                    System.out.print("Novo intervalo de adubo (dias): ");
                    intervaloAdubo = scAltera.nextInt();
                    scAltera. nextLine();
                    while (intervaloAdubo <= 0) {
                        System.out.print("Intervalo inválido.  Digite um número positivo: ");
                        intervaloAdubo = scAltera.nextInt();
                        scAltera.nextLine();
                    }
                    break;
                case 5:
                    System. out.print(
                            "Novo tipo de rega:" +
                                    "\n   1 - Rega baixa" +
                                    "\n   2 - Rega média" +
                                    "\n   3 - Rega alta" +
                                    "\nOpção: ");
                    switchAltera = scAltera.nextInt();
                    scAltera.nextLine();
                    while (switchAltera < 1 || switchAltera > 3) {
                        System. out.print("Tipo de rega inválido. Digite novamente: ");
                        switchAltera = scAltera.nextInt();
                        scAltera.nextLine();
                    }
                    switch (switchAltera) {
                        case 1:
                            tipoRega = "baixa";
                            break;
                        case 2:
                            tipoRega = "media";
                            break;
                        default:
                            tipoRega = "alta";
                    }
                    break;
                case 6:
                    System.out.print("Novo tipo de adubo: ");
                    tipoAdubo = scAltera.nextLine().trim();
                    break;
            }

            matriz[posicaoPlanta][0] = nome;
            matriz[posicaoPlanta][1] = luminosidade;
            matriz[posicaoPlanta][2] = String.valueOf(intervaloRega);
            matriz[posicaoPlanta][3] = String.valueOf(intervaloAdubo);
            matriz[posicaoPlanta][4] = tipoRega;
            matriz[posicaoPlanta][5] = tipoAdubo;

            try {
                Formatter arqNovo = new Formatter("plantas.txt");
                for (int i = 0; i < total; i++) {
                    if (matriz[i] != null) {
                        arqNovo.format("%s;%s;%s;%s;%s;%s;%n",
                                matriz[i][0],  // nome
                                matriz[i][1],  // luminosidade
                                matriz[i][2],  // intervaloRega
                                matriz[i][3],  // intervaloAdubo
                                matriz[i][4],  // tipoRega
                                matriz[i][5]   // tipoAdubo
                        );
                    }
                }
                arqNovo.close();
                System.out.println("Alteração salva com sucesso!");
            } catch (SecurityException securityException) {
                System.err.println("Permissão de Escrita Negada. Fechando.. .");
            } catch (FileNotFoundException fileNotFoundException) {
                System. err.println("Erro ao abrir o arquivo para escrita.");
            } catch (FormatterClosedException formatterClosedException) {
                System.err.println("Erro de escrita no arquivo.");
            }
        }
    }

    public static void exibePlantasCadastradas() {
        String[] linhas = new String[100];
        int total = 0;

        abreArqLeitura();
        while (arqEnt.hasNextLine() && total < linhas.length) {
            linhas[total] = arqEnt.nextLine();
            total++;
        }
        fechaArqLeitura();

        if (total == 0) {
            System.out.println("Nenhuma planta cadastrada.");
        } else {
            System.out.println("Plantas cadastradas: ");
            for (int i = 0; i < total; i++) {
                String[] p = linhas[i].split(";");
                System.out.println((i + 1) + " - " + p[0]);
            }
        }
    }

    public static void regaPlanta() {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        String[][] matriz = new String[100][6];
        String[][] lembretes = new String[100][3];
        Scanner sc = new Scanner(System.in);
        int total = 0, totalLembretes = 0, escolha = 0;
        int diaHoje = cal.get(java. util.Calendar.DAY_OF_YEAR);
        String nome;
        boolean encontrou = false;


        abreArqLeitura();
        while (arqEnt.hasNextLine() && total < 100) {
            String linha = arqEnt.nextLine(). trim();
            if (!linha. isEmpty()) {
                String[] partes = linha.split(";", -1);
                for (int j = 0; j < 6; j++) {
                    if (j < partes.length && partes[j] != null) {
                        matriz[total][j] = partes[j]. trim();
                    } else {
                        matriz[total][j] = "";
                    }
                }
                total++;
            }
        }
        fechaArqLeitura();

        if (total == 0) {
            System.out.println("Nenhuma planta cadastrada.");
            return;
        }

        try {
            Scanner arqLembretes = new Scanner(new File("lembretes.txt"));
            while (arqLembretes.hasNextLine() && totalLembretes < 100) {
                String linha = arqLembretes.nextLine().trim();
                if (!linha.isEmpty()) {
                    String[] partes = linha.split(";", -1);
                    for (int j = 0; j < 3; j++) {
                        if (j < partes.length && partes[j] != null) {
                            lembretes[totalLembretes][j] = partes[j].trim();
                        } else {
                            lembretes[totalLembretes][j] = "";
                        }
                    }
                    totalLembretes++;
                }
            }
            arqLembretes.close();
        } catch (FileNotFoundException e) {
        }

        exibePlantasCadastradas();

        System.out.print("Digite o número da planta que deseja regar: ");
        escolha = sc.nextInt();
        while (escolha < 1 || escolha > total) {
            System.out.print("Número inválido. Digite novamente: ");
            escolha = sc.nextInt();
        }

        nome = matriz[escolha - 1][0];

        for (int i = 0; i < totalLembretes; i++) {
            if (lembretes[i][0].equalsIgnoreCase(nome)) {
                lembretes[i][1] = String.valueOf(diaHoje);
                encontrou = true;
            }
        }

        if (!encontrou) {
            lembretes[totalLembretes][0] = nome;
            lembretes[totalLembretes][1] = String.valueOf(diaHoje);
            lembretes[totalLembretes][2] = "0";
            totalLembretes++;
        }

        try {
            Formatter arqLemb = new Formatter("lembretes.txt");
            for (int i = 0; i < totalLembretes; i++) {
                if (lembretes[i] != null && lembretes[i][0] != null) {
                    arqLemb.format("%s;%s;%s;%n",
                            lembretes[i][0],
                            lembretes[i][1],
                            lembretes[i][2]
                    );
                }
            }
            arqLemb.close();
            System.out.printf("Rega de '%s' registrada com sucesso!  (dia %d do ano)%n", nome, diaHoje);
        } catch (Exception e) {
            System.err.println("Erro ao salvar lembrete.");
        }
    }

    public static void adubaPlanta() {
        java.util.Calendar cal = java.util.Calendar. getInstance();
        String[][] matriz = new String[100][6];
        String[][] lembretes = new String[100][3];
        Scanner sc = new Scanner(System.in);
        int total = 0, totalLembretes = 0, escolha = 0;
        int diaHoje = cal.get(java.util.Calendar.DAY_OF_YEAR);
        String nome;
        boolean encontrou = false;

        abreArqLeitura();
        while (arqEnt.hasNextLine() && total < 100) {
            String linha = arqEnt.nextLine().trim();
            if (!linha.isEmpty()) {
                String[] partes = linha.split(";", -1);
                for (int j = 0; j < 6; j++) {
                    if (j < partes.length && partes[j] != null) {
                        matriz[total][j] = partes[j].trim();
                    } else {
                        matriz[total][j] = "";
                    }
                }
                total++;
            }
        }
        fechaArqLeitura();

        if (total == 0) {
            System. out.println("Nenhuma planta cadastrada.");
            return;
        }

        try {
            Scanner arqLembretes = new Scanner(new File("lembretes.txt"));
            while (arqLembretes.hasNextLine() && totalLembretes < 100) {
                String linha = arqLembretes.nextLine().trim();
                if (!linha.isEmpty()) {
                    String[] partes = linha.split(";");
                    for (int j = 0; j < 3; j++) {
                        if (j < partes.length && partes[j] != null) {
                            lembretes[totalLembretes][j] = partes[j].trim();
                        } else {
                            lembretes[totalLembretes][j] = "";
                        }
                    }
                    totalLembretes++;
                }
            }
            arqLembretes.close();
        } catch (FileNotFoundException e) {
        }

        exibePlantasCadastradas();

        System.out.print("Digite o número da planta que deseja adubar: ");
        escolha = sc.nextInt();
        while (escolha < 1 || escolha > total) {
            System.out.print("Número inválido. Digite novamente: ");
            escolha = sc.nextInt();
        }

        nome = matriz[escolha - 1][0];

        for (int i = 0; i < totalLembretes; i++) {
            if (lembretes[i][0].equalsIgnoreCase(nome)) {
                lembretes[i][2] = String.valueOf(diaHoje);
                encontrou = true;
            }
        }

        if (!encontrou) {
            lembretes[totalLembretes][0] = nome;
            lembretes[totalLembretes][1] = "0";
            lembretes[totalLembretes][2] = String.valueOf(diaHoje);
            totalLembretes++;
        }

        try {
            Formatter arqLemb = new Formatter("lembretes.txt");
            for (int i = 0; i < totalLembretes; i++) {
                if (lembretes[i] != null && lembretes[i][0] != null) {
                    arqLemb.format("%s;%s;%s;%n",
                            lembretes[i][0],
                            lembretes[i][1],
                            lembretes[i][2]
                    );
                }
            }
            arqLemb.close();
            System.out.printf("Adubagem de '%s' registrada com sucesso!  (dia %d do ano)%n", nome, diaHoje);
        } catch (Exception e) {
            System.err. println("Erro ao salvar lembrete.");
        }
    }

    public static void abreLembrete() {
        String[][] matriz = new String[100][6];
        String[][] lembretes = new String[100][3];
        String dataHoje;
        int total = 0;
        int totalLembretes = 0;
        boolean temLembrete = false;

        abreArqLeitura();
        while (arqEnt.hasNextLine() && total < 100) {
            String linha = arqEnt.nextLine(). trim();
            if (! linha.isEmpty()) {
                String[] partes = linha.split(";", -1);
                for (int j = 0; j < 6; j++) {
                    if (j < partes.length && partes[j] != null) {
                        matriz[total][j] = partes[j]. trim();
                    } else {
                        matriz[total][j] = "";
                    }
                }
                total++;
            }
        }
        fechaArqLeitura();

        if (total == 0) {
            System.out.println("Nenhuma planta cadastrada.");
        } else {
            java.util.Calendar cal = java.util.Calendar. getInstance();
            Scanner sc = new Scanner(System.in);
            String nome = "";
            int intervaloRega = 0, intervaloAdubo = 0, diasDesdeRega, diasDesdeAdubo, diaUltimaRega = 0, diaUltimoAdubo = 0;
            int diaHoje = cal.get(java. util.Calendar.DAY_OF_YEAR);
            try {
                Scanner arqLembretes = new Scanner(new File("lembretes.txt"));
                while (arqLembretes.hasNextLine() && totalLembretes < 100) {
                    String linha = arqLembretes.nextLine(). trim();
                    if (!linha. isEmpty()) {
                        String[] partes = linha.split(";", -1);
                        for (int j = 0; j < 3; j++) {
                            if (j < partes.length && partes[j] != null) {
                                lembretes[totalLembretes][j] = partes[j].trim();
                            }
                        }
                        totalLembretes++;
                    }
                }
                arqLembretes. close();
            } catch (FileNotFoundException e) {
                System.out.println("Nenhum histórico de rega/adubo encontrado.");
                System.out.println("Registre a primeira rega/adubo nas opções 5 e 6 do menu.\n");
            }
            System.out.println("Hoje é o dia " + diaHoje + " do ano");
            for (int i = 0; i < total; i++) {
                nome = matriz[i][0];
                intervaloRega = Integer.parseInt(matriz[i][2]);
                intervaloAdubo = Integer.parseInt(matriz[i][3]);
                diaUltimaRega = 0;
                diaUltimoAdubo = 0;

                for (int j = 0; j < totalLembretes; j++) {
                    if (lembretes[j][0].equalsIgnoreCase(nome)) {
                        diaUltimaRega = Integer.parseInt(lembretes[j][1]);
                        diaUltimoAdubo = Integer.parseInt(lembretes[j][2]);
                    }
                }
                diasDesdeRega = diaHoje - diaUltimaRega;
                if (diasDesdeRega < 0) {
                    diasDesdeRega += 365;
                }

                diasDesdeAdubo = diaHoje - diaUltimoAdubo;
                if (diasDesdeAdubo < 0) {
                    diasDesdeAdubo += 365;
                }

                if (diasDesdeRega >= intervaloRega && diaUltimaRega > 0) {
                    System.out.printf("%s precisa ser REGADA! (última rega há %d dias)%n",
                            nome, diasDesdeRega);
                    temLembrete = true;
                }
                if (diasDesdeAdubo >= intervaloAdubo && diaUltimoAdubo > 0) {
                    System.out.printf("%s precisa ser ADUBADA! (última adubo há %d dias)%n",
                            nome, diasDesdeAdubo);
                    temLembrete = true;
                }

            }
            if (!temLembrete) {
                System.out.println("Nenhuma planta precisa de cuidados no momento!");
            }
            System.out.println("================================\n");
        }
    }

    public static void sugestaoPlantas() {
        String[][] sugestoes = new String[100][4];
        Scanner sc = new Scanner(System.in);
        int total = 0, switchLuminosidade, switchPorte, switchCuidado;
        String luminosidade = "", porte = "", cuidado = "";
        boolean encontrou = false;

        try {
            Scanner arqSugestoes = new Scanner(new File("sugestao.txt"));
            while (arqSugestoes.hasNextLine() && total < 100) {
                String linha = arqSugestoes.nextLine().trim();
                if (! linha.isEmpty()) {
                    String[] partes = linha.split(";", -1);
                    for (int j = 0; j < 4; j++) {
                        if (j < partes.length && partes[j] != null) {
                            sugestoes[total][j] = partes[j].trim();
                        }
                    }
                    total++;
                }
            }
            arqSugestoes.close();
        } catch (FileNotFoundException e) {
            System. out.println("Arquivo de sugestões não encontrado.");
        }

        System.out.print(
                "Qual luminosidade você tem disponível?" +
                        "\n   1 - Sol" +
                        "\n   2 - Meia-sombra" +
                        "\n   3 - Sombra" +
                        "\nOpção: ");
        switchLuminosidade = sc.nextInt();
        while (switchLuminosidade < 1 || switchLuminosidade > 3) {
            System.out.print("Opção inválida. Digite novamente: ");
            switchLuminosidade = sc.nextInt();
        }
        switch (switchLuminosidade) {
            case 1:
                luminosidade = "sol";
                break;
            case 2:
                luminosidade = "meia-sombra";
                break;
            case 3:
                luminosidade = "sombra";
                break;
        }

        System.out. print(
                "\nQual o porte desejado da planta?" +
                        "\n   1 - Pequeno" +
                        "\n   2 - Médio" +
                        "\n   3 - Alto" +
                        "\nOpção: ");
        switchPorte = sc.nextInt();
        while (switchPorte < 1 || switchPorte > 3) {
            System.out. print("Opção inválida. Digite novamente: ");
            switchPorte = sc.nextInt();
        }
        switch (switchPorte) {
            case 1:
                porte = "pequeno";
                break;
            case 2:
                porte = "medio";
                break;
            case 3:
                porte = "alto";
                break;
        }

        System.out. print(
                "\nQual o nível de cuidado que você pode dedicar?" +
                        "\n   1 - Baixo (pouca manutenção)" +
                        "\n   2 - Médio" +
                        "\n   3 - Alto (muita atenção)" +
                        "\nOpção: ");
        switchCuidado = sc.nextInt();
        while (switchCuidado < 1 || switchCuidado > 3) {
            System.out.print("Opção inválida. Digite novamente: ");
            switchCuidado = sc.nextInt();
        }
        switch (switchCuidado) {
            case 1:
                cuidado = "baixo";
                break;
            case 2:
                cuidado = "medio";
                break;
            case 3:
                cuidado = "alto";
                break;
        }

        System.out.println("\n===== PLANTAS SUGERIDAS =====");
        for (int i = 0; i < total; i++) {
            if (sugestoes[i][1]. equals(luminosidade) && sugestoes[i][2].equals(porte) && sugestoes[i][3].equals(cuidado)) {
                System.out.println(sugestoes[i][0]);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhuma planta encontrada com essas características.");
            System.out.println("Tente ajustar os critérios!");
        }

        System.out.println("==============================\n");
    }

    public static void fechaArqEscrita() {
        if (arqSaida != null) {
            arqSaida.close();
        }
    }

    public static void fechaArqLeitura() {
        if (arqEnt != null) {
            arqEnt.close();
        }
    }
}