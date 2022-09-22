public class MensagemParaTodos extends Mensagem{

    public MensagemParaTodos(String texto,String emailRemetente, boolean anonima){
        super(texto,emailRemetente,anonima);
    }
    public String getTextoCompletoAExibir() {
        if(super.isAnonima()){
            return "Mensagem para todos. Texto: "+super.getTexto();
        }
        return "Mensagem para todos. Texto: "+super.getTexto()+" de Remetente:"+super.getEmailRemetente();

    }
}
