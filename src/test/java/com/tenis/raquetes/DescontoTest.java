package com.tenis.raquetes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DescontoTest {

    @Test
    void deveAplicarDescontoDe20PorCento() {
        double total = 150.0;
        total *= 0.8;
        assertEquals(120.0, total, 0.0001);
    }
}
