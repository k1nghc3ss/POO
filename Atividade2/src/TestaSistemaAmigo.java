
import javax.swing.*;
import java.util.List;

public class TestaSistemaAmigo {

    public static void main(String [] args) throws AmigoJaExisteException {

        SistemaAmigo sistema = new SistemaAmigo();

        try {
            sistema.cadastraAmigo("João Vitor", "joao.vitor@gmail.com");
            sistema.cadastraAmigo("Aline", "aline.monteiro@hotmail.com");

            sistema.configuraAmigoSecretoDe("joao.vitor@gmail.com", "aline.monteiro@hotmail.com");
            sistema.configuraAmigoSecretoDe("aline.monteiro@hotmail.com", "joao.vitor@gmail.com");

            sistema.enviarMensagemParaAlguem("Oi João", "aline.monteiro@hotmail.com", "joao.vitor@gmail.com", true);
            sistema.enviarMensagemParaTodos("Oi Pessoal", "aline.monteiro@hotmail.com", true);

            List <Mensagem> mensagemsAnonimas = sistema.pesquisaMensagensAnonimas();

            for (Mensagem i : mensagemsAnonimas){
                System.out.println(i.getTextoCompletoAExibir());
            }

            List <Mensagem> todasAsMensagens = sistema.pesquisaTodasAsMensagens();

            for (Mensagem i : todasAsMensagens) {
                System.out.println(i.getTextoCompletoAExibir());
            }

            String teste = sistema.pesquisaAmigoSecretoDe("joao.vitor@gmail.com");

            if(teste.equals("aline.monteiro@hotmail.com")){
                System.out.println("Certo");
            }

        }catch (AmigoJaExisteException | AmigoInexistenteException | AmigoNaoSorteadoException e){
            JOptionPane.showInputDialog(null, "Falha");
            e.printStackTrace();
        }
    }
}
