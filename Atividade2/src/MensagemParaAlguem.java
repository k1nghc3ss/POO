public class MensagemParaAlguem extends Mensagem{

    private String emailDestinatario;

    public MensagemParaAlguem(String texto, String emailRemetente,String emailDestinatario, boolean anonima){
        super(texto,emailRemetente,anonima);
        this.emailDestinatario = emailDestinatario;
    }
    public MensagemParaAlguem(){
        this("","","",false);
    }

    public String getEmailDestinatario() {
        return emailDestinatario;
    }

    public void setEmailDestinatario(String emailDestinatario) {
        this.emailDestinatario = emailDestinatario;
    }

    public String getTextoCompletoAExibir() {
        if(isAnonima()){
            return "Mensagem para "+this.emailDestinatario+". Texto: "+super.getTexto();
        }
        return "Mensagem para "+this.emailDestinatario+", de "+super.getEmailRemetente()+" e de Texto: "+super.getTexto();
    }
    
}
