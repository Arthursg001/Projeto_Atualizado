package dev.samuel.teste.agenda.model;

public class Evento {

    private String nomeEvento;
    private String descricaoEvento;

    public Evento(String nomeEvento, String descricaoEvento) {
        this.nomeEvento = nomeEvento;
        this.descricaoEvento = descricaoEvento;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public String getDescricaoEvento() {
        return descricaoEvento;
    }

    public void setDescricaoEvento(String descricaoEvento) {
        this.descricaoEvento = descricaoEvento;
    }

    @Override
    public String toString() {
        return  nomeEvento  + "\n" + descricaoEvento;
    }
}
