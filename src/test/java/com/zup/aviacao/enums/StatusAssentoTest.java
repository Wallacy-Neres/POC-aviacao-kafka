package com.zup.aviacao.enums;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StatusAssentoTest {


    @Test
    public void enumTest(){

        Assert.assertNotNull(StatusAssento.DISPONIVEL);
        Assert.assertNotNull(StatusAssento.OCUPADO);

        Assert.assertNotNull(StatusAssento.values());

    }
}
