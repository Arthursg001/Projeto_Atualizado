package dev.samuel.teste.agenda.controller;

import java.util.ArrayList;
import java.util.List;

import dev.samuel.teste.agenda.model.Evento;

public class EventoDAO {

    private final static List<Evento> eventos = new ArrayList<>();

    public List<Evento> todos() {return  new ArrayList<>(eventos);}

    public void salva(Evento evento){eventos.add(evento);}
}
