package com.devsuperior.desafio3.servicos.exceptions;

@SuppressWarnings("serial")
public class DatabaseException extends RuntimeException {

    public DatabaseException(String msg) {
        super(msg);
    }
}
