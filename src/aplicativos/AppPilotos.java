package aplicativos;

import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;

public class AppPilotos {
    public static void main(String[] args) throws InterruptedException, IOException {
        final int MAX_ELEMENTOS = 20;
        int opcao, qtdCadastrados = 0;
        Pessoa[] pilotos = new Pessoa[MAX_ELEMENTOS];
        Scanner in = new Scanner(System.in);

        do {
            System.out.println("\n****\nMENU\n****\n");
            System.out.println("1 - Cadastrar piloto");
            System.out.println("2 - Listar pilotos cadastrados");
            System.out.println("3 - Localizar piloto pelo CPF");
            System.out.println("4 - Aumentar espaço de armazenamento");
            System.out.println("0 - Sair");
            System.out.print("Opcao: ");

            opcao = in.nextInt();
            in.nextLine(); // Tira o ENTER que ficou na entrada na instrução anterior

            if (opcao == 1) {
                // Se não tem mais espaço no vetor, caio fora
                if (qtdCadastrados == MAX_ELEMENTOS) {
                    System.out.println("\nNão há espaço para cadastrar novos pilotos.");
                    voltarMenu(in);
                    continue;
                }

                //Cadastre seu piloto aqui  
                System.out.println("Digite o nome do piloto:");
                String nome = in.nextLine();
                System.out.println("Digite o cpf:");
                String cpf = in.nextLine();
                Pessoa p = new Pessoa(nome, cpf);
                pilotos[qtdCadastrados] = p;
                System.out.println("\nPiloto cadastrado com sucesso.");
                qtdCadastrados++;
                voltarMenu(in);


            } else if (opcao == 2) {
                // Se não tem ninguém cadastrado no vetor, caio fora
                if (qtdCadastrados == 0) {
                    System.out.println("\nNão há pilotos cadastrados para exibir.");
                    voltarMenu(in);
                    continue;
                }
                for(int i = 0; i < qtdCadastrados; i++){
                    System.out.println(qtdCadastrados + " pilotos cadastrados.");
                    System.out.println("Piloto: " + pilotos[i].nome + " - Cpf: " + pilotos[i].cpf);
                    voltarMenu(in);
                }
                

            } else if (opcao == 3) {
                System.out.println("Digite o cpf para pesquisar:");
                String c = in.next();
                int rec = 0;
                for(int i = 0; i < qtdCadastrados;i++){
                    if(pilotos[i].cpf.equals(c)){
                        System.out.println("Piloto: " + pilotos[i].nome + " - Cpf: " + pilotos[i].cpf);
                        rec = 1;
                    }
                }
                if(rec==0){System.out.println("Nenhum piloto encontrado com o cpf informado");}
                in.nextLine();
                voltarMenu(in);


            } else if (opcao == 4) {
                System.out.println("Digite a quantidade a aumentar:");
                int size = Integer.parseInt(in.next());
                pilotos = Arrays.copyOf(pilotos, pilotos.length+size);

        
            } else if (opcao != 0) {
                System.out.println("\nOpção inválida!");
            }


        } while (opcao != 0);

        System.out.println("Fim do programa!");

        in.close();
    }

    private static void voltarMenu(Scanner in) throws InterruptedException, IOException {
        System.out.println("\nPressione ENTER para voltar ao menu.");
        in.nextLine();

        // Limpa toda a tela, deixando novamente apenas o menu
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            System.out.print("\033[H\033[2J");
        
        System.out.flush();
    }
}
class Pessoa{
    String nome,cpf;
    int nivel;
    public Pessoa(String nome,String cpf){
        this.nome = nome;
        this.cpf = cpf;     
    }
}
