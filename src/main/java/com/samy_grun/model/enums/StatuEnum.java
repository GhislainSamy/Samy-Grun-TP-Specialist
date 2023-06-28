package com.samy_grun.model.enums;

public enum StatuEnum {
    ENCOUR("En Cour réaprovisionnement"),
    DISPO("Disponible"),
    NODISPO("Non Disponible");

    private final String text;
    StatuEnum(final String text) {this.text = text;}
    @Override
    public String toString() {return this.text;}
}
