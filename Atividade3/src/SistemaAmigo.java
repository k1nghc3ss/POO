import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SistemaAmigo {

    private List<Mensagem> mensagens;
    private Map<String,Amigo> amigos;

    public SistemaAmigo(){
        this.amigos = new HashMap<>();
        this.mensagens = new ArrayList<>();
    }

    public void cadastraAmigo(String nome, String emailAmigo) throws AmigoJaExisteException{
        Amigo novoAmigo = new Amigo(nome,emailAmigo);
        if(this.amigos.containsKey(novoAmigo.getEmail())) {
            throw new AmigoJaExisteException("Amigo já cadastrado!");
        }else{
            this.amigos.put(novoAmigo.getEmail(),novoAmigo);
        }
    }
    public Amigo pesquisaAmigo(String emailAmigo) throws AmigoInexistenteException {
        if(this.amigos.containsKey(emailAmigo)) return this.amigos.get(emailAmigo);
        else {
            throw new AmigoInexistenteException("Amigo Pesquisado não cadastrado");
        }
    }
    public void enviarMensagemParaTodos(String email, String emailRemetente, boolean isAnonima){
        Mensagem msg = new MensagemParaTodos(email,emailRemetente,isAnonima);
        this.mensagens.add(msg);
        System.out.println(msg.getTextoCompletoAExibir());
    }
    public void enviarMensagemParaAlguem(String email, String emailRemetente, String emailDestinatario, boolean isAnonima){
        Mensagem msg = new MensagemParaAlguem(email,emailRemetente,emailDestinatario, isAnonima);
        this.mensagens.add(msg);
        System.out.println(msg.getTextoCompletoAExibir());
    }
    public List<Mensagem> pesquisaMensagensAnonimas(){
        List<Mensagem> anonimas = new ArrayList<>();
        for(Mensagem m: mensagens){
            if(m.isAnonima()){
                anonimas.add(m);
            }
        }
        return anonimas;
    }
    public List<Mensagem> pesquisaTodasAsMensagens(){
        return mensagens;
    }
    public void configuraAmigoSecretoDe(String emailDaPessoa,String emailAmigoSorteado) throws AmigoInexistenteException, AmigoNaoSorteadoException {
       if(this.amigos.containsKey(emailDaPessoa)){
           if(this.amigos.containsKey(emailAmigoSorteado)){
               this.amigos.get(emailDaPessoa).setEmailAmigoSorteado(emailAmigoSorteado);
           }else{
               throw new AmigoInexistenteException(emailAmigoSorteado+" não existe na lista de email.");
           }
       }else{
           throw new AmigoNaoSorteadoException("Não foi possivel configurar amigo secreto de"+emailDaPessoa);
       }
    }

    public String pesquisaAmigoSecretoDe(String emailDaPessoa) throws AmigoInexistenteException,AmigoNaoSorteadoException{
        if(this.amigos.containsKey(emailDaPessoa)){
            if(this.amigos.get(emailDaPessoa).getEmailAmigoSorteado() != null){
                return this.amigos.get(emailDaPessoa).getEmailAmigoSorteado();
            }else{
                throw new AmigoNaoSorteadoException(emailDaPessoa+" não possue amigo secreto");
            }

        }else {
            throw new AmigoInexistenteException("Amigo secreto de "+emailDaPessoa+" não encontrado.");
        }

    }
}
