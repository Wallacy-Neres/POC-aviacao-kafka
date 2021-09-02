package com.zup.aviacao.adapters.kafka.converters;

import com.zup.aviacao.DTO.VooRequestDTO;
import com.zup.aviacao.DTO.VooResponseDTO;
import com.zup.aviacao.converter.VooConverter;
import com.zup.aviacao.enums.StatusAssento;
import com.zup.aviacao.model.Aeronave;
import com.zup.aviacao.model.Assento;
import com.zup.aviacao.model.Voo;
import com.zup.aviacao.solicitacao_cadastro_aeronave.SolicitarCadastroAeronaveRequest;
import com.zup.aviacao.solicitacao_cadastro_aeronave.SolicitarCadastroAeronaveRequestData;
import com.zup.aviacao.solicitacao_cadastro_voo.SolicitarCadastroVooRequest;
import com.zup.aviacao.solicitacao_cadastro_voo.SolicitarCadastroVooRequestData;
import com.zup.aviacao.solicitacao_cadastro_voo.SolicitarCadastroVooResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(MockitoJUnitRunner.class)
public class VooConverterTest {

    @InjectMocks
    private VooConverter converter;

    @Mock
    EntityManager manager;

    @Test
    public void vooEntityTest() {

        Voo voo = converter.vooEntity(vooEntityBuilder(), manager);

        Assert.assertNotNull(voo);
        Assert.assertEquals("Guarulhos", voo.getOrigem());
    }

    @Test
    public void avroToDtoTest() {

        VooRequestDTO requestDTO = converter.avroToDto(avroToDtoBuilder());

        Assert.assertNotNull(requestDTO);
        Assert.assertEquals("São Paulo", requestDTO.getOrigem());
    }

    @Test
    public void vooEntityToDtoTest() {

        VooResponseDTO vooResponseDTO = converter.vooEntityToDto(entityToDtoBuilder());

        Assert.assertNotNull(vooResponseDTO);
        Assert.assertEquals("Guarulhos", vooResponseDTO.getOrigem());
    }

    private Voo entityToDtoBuilder() {

        List<String> listaAssentos = new ArrayList<String>() {
            {
                add("20");
                add("30");
                add("40");
            }
        };

        List<Assento> assentos = listaAssentos.stream().map(assento -> Assento.builder()
                .assento(assento)
                .statusAssento(StatusAssento.DISPONIVEL)
                .build()).collect(Collectors.toList());

        Aeronave aeronave = Aeronave.builder()
                .id(1L)
                .marca("Boing")
                .modelo("747")
                .assentos(assentos).build();

        return Voo.builder()
                .dataCriacaoVoo(converteStringTOLocalDate("2021-09-01 10:00"))
                .portao("2")
                .origem("Guarulhos")
                .destino("Macapá")
                .aeronave(aeronave)
                .tempoDuracao(LocalTime.parse("09:00"))
                .fechaPortao(LocalTime.parse("09:30"))
                .horaEmbarque(LocalTime.parse("09:00"))
                .valor(BigDecimal.valueOf(200.0))
                .dataVoo(converteStringTOLocalDate("2021-09-01 10:00"))
                .idVoo(1L)
                .build();
    }

    @Test
    public void dtoResponseToAvroTest(){

        SolicitarCadastroVooResponse cadastroVooResponse = converter.dtoResponseToAvro(dtoResponseToAvroBuilder());

        Assert.assertNotNull(cadastroVooResponse);
        Assert.assertEquals("600.0", cadastroVooResponse.getData().getValor());
    }

    private VooResponseDTO dtoResponseToAvroBuilder() {
        return VooResponseDTO.builder()
                .dataVoo(converteStringTOLocalDate("2021-09-01 10:00"))
                .origem("Guarulhos")
                .destino("Recife")
                .tempoDuracao(LocalTime.parse("03:00"))
                .valor(BigDecimal.valueOf(600.0))
                .build();
    }

    private SolicitarCadastroVooRequest avroToDtoBuilder() {

        return SolicitarCadastroVooRequest.newBuilder()
                .setData(
                        SolicitarCadastroVooRequestData.newBuilder()
                                .setOrigem("São Paulo")
                                .setDestino("Belo Horizonte")
                                .setTempoDuracao("03:00")
                                .setDataVoo(converteStringTOLocalDate("2021-09-01 10:00").toString())
                                .setFechaPortao("08:00")
                                .setHoraEmbarque("07:30")
                                .setValor("300.0")
                                .setPortao("2")
                                .setIdAeronave(1L)
                                .build()
                )
                .build();
    }


    private VooRequestDTO vooEntityBuilder() {
        return VooRequestDTO.builder()

                .fechaPortao(LocalTime.parse("10:00"))
                .horaEmbarque(LocalTime.parse("09:00"))
                .tempoDuracao(LocalTime.parse("05:00"))
                .origem("Guarulhos")
                .destino("Rio de Janeiro")
                .valor(BigDecimal.valueOf(200.0))
                .build();
    }

    public LocalDateTime converteStringTOLocalDate(String data) {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDate = LocalDateTime.parse(data, parser);
        return localDate;
    }

}
