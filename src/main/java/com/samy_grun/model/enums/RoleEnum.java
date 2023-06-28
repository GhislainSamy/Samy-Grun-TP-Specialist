package com.samy_grun.model.enums;

public enum RoleEnum{
    ADMINISTRATEUR("Administrateur"),
    SPECIALISTE("Specialist"),
    UTILISATEUR("Utilisateur");

    private final String text;
    RoleEnum(final String text) {this.text = text;}
    @Override
    public String toString() {return this.text;}
}