package com.woliveira1728.service;

public interface IConverteDados {
    <T> T  obterDados(String json, Class<T> classe);
}
