import java.util.ArrayList;
import java.util.List;

public class SistemaAmigo {

    private List<Mensagem> mensagens;
    private List<Amigo> amigos;

    public SistemaAmigo(){
        this.amigos = new ArrayList<>();
        this.mensagens = new ArrayList<>();
    }

    public void cadastraAmigo(String nome, String emailAmigo) throws AmigoJaExisteException{
        Amigo novoAmigo = new Amigo(nome,emailAmigo);
        if(this.amigos.contains(novoAmigo)) {
            throw new AmigoJaExisteException("Amigo já cadastrado!");
        }else{
            this.amigos.add(novoAmigo);
        }
    }
    public Amigo pesquisaAmigo(String emailAmigo) throws AmigoInexistenteException {
        for (Amigo A : this.amigos) {
            if (A.getEmail().equals(emailAmigo)) {
                return A;
            }else {
                throw new AmigoInexistenteException("Amigo Pesquisado não cadastrado");
            }
        }
        return null;
    }
    public void enviarMensagemParaTodos(String email, String emailRemetente, boolean isAnonima){
        Mensagem msgAnonima = new MensagemParaTodos(email,emailRemetente,isAnonima);
        this.mensagens.add(msgAnonima);
        System.out.println(msgAnonima.getTextoCompletoAExibir());
    }
    public void enviarMensagemParaAlguem(String email, String emailRemetente, String emailDestinatario, boolean isAnonima){
        Mensagem msgAnonima = new MensagemParaAlguem(email,emailRemetente,emailDestinatario, isAnonima);
        this.mensagens.add(msgAnonima);
        System.out.println(msgAnonima.getTextoCompletoAExibir());
    }
    public List<Mensagem> pesquisaMensagensAnonimas(){
        List anonimas = new ArrayList<>();
        for(Mensagem m: this.mensagens){
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
        for(Amigo a: this.amigos){
            if(a.getEmail().equals(emailDaPessoa)){
                for(Amigo b: this.amigos) {
                    if(b.getEmail().equals(emailAmigoSorteado)){
                        a.setEmailAmigoSorteado(emailAmigoSorteado);
                    }else{
                        throw new AmigoInexistenteException("Amigo não cadastrado na lista de amigos");
                    }
                }
            }
        }
        throw new AmigoNaoSorteadoException("Não foi possível cadasrar amigo secrede de"+emailDaPessoa);
    }

    public String pesquisaAmigoSecretoDe(String emailDaPessoa) throws AmigoInexistenteException,AmigoNaoSorteadoException{
        for(Amigo a: this.amigos){
            if(a.getEmail().equals(emailDaPessoa)){
                return a.getEmailAmigoSorteado();
            }
        }
        throw new AmigoInexistenteException("Amigo secreto de "+emailDaPessoa+" não encontrado.");
    }
}
