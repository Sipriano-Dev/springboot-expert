package com.sipriano.libraryapi.repository;

import com.sipriano.libraryapi.service.TransacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TransacoesTest {

    @Autowired
    TransacaoService transacaoService;

    /**
     * Commit -> Confirmar as alterações
     * Rollback -> Desfazer as alterações
     */
    @Test
    void transacaoSimples() {
        transacaoService.executar();
    }


}
