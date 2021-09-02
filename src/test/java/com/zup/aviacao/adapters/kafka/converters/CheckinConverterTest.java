package com.zup.aviacao.adapters.kafka.converters;

import com.zup.aviacao.DTO.CheckinRequestDTO;
import com.zup.aviacao.DTO.CheckinResponseDTO;
import com.zup.aviacao.converter.CheckinConverter;
import com.zup.aviacao.enums.StatusAssento;
import com.zup.aviacao.model.*;
import com.zup.aviacao.solicitacao_cadastro_voo.SolicitarCheckinRequest;
import com.zup.aviacao.solicitacao_cadastro_voo.SolicitarCheckinRequestData;
import com.zup.aviacao.solicitacao_cadastro_voo.SolicitarCheckinResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(MockitoJUnitRunner.class)
public class CheckinConverterTest {

    @InjectMocks
    private CheckinConverter converter;

    @Mock
    private EntityManager manager;

    @Test
    public void checkinVooEntityTest() {

        CheckinRequestDTO requestDTO = requestDTOBuilder();

        CheckinVoo checkinVoo = converter.checkinVooEntity(requestDTO, manager);

        Assert.assertNotNull(checkinVoo);
        Assert.assertEquals("123.456.789-05", checkinVoo.getCpf());
    }

    @Test
    public void avroToDtoTest() {
        SolicitarCheckinRequest solicitarCheckinRequest = requestBuilder();

        CheckinRequestDTO requestDTO = converter.avroToDto(solicitarCheckinRequest);

        Assert.assertNotNull(requestDTO);
        Assert.assertEquals("1", requestDTO.getIdCompraVoo().toString());
        Assert.assertEquals("123.456.789-05", requestDTO.getCpf());
    }

    @Test
    public void checkinEntityToDto() {
        CheckinVoo checkinVoo = checkinVooBuilder();

        CheckinResponseDTO responseDTO = converter.checkinEntityToDto(checkinVoo);

        Assert.assertNotNull(responseDTO);
        Assert.assertEquals("varney", responseDTO.getNome());
        Assert.assertEquals("Guarulhos", responseDTO.getOrigem());
    }

    @Test
    public void dtoToAvroTest(){
        CheckinResponseDTO checkinResponseDTO = checkinResponseDTOBuilder();

        SolicitarCheckinResponse response = converter.dtoToAvro(checkinResponseDTO);

        Assert.assertNotNull(response);
        Assert.assertEquals("5", response.getData().getPortao());
        Assert.assertEquals("França", response.getData().getDestino());
    }

    private CheckinResponseDTO checkinResponseDTOBuilder() {
        return CheckinResponseDTO.builder()
                .nome("Varney")
                .assento("20")
                .horaEmbarque(LocalTime.parse("13:00"))
                .fechaPortao(LocalTime.parse("13:40"))
                .portao("5")
                .origem("Guarulhos")
                .destino("França")
                .dataVoo(converteStringTOLocalDateTime("2022-09-20 15:00"))
                .tempoDuracao(LocalTime.parse("08:00"))
                .build();
    }

    private CheckinVoo checkinVooBuilder() {

        return CheckinVoo.builder()
                .dataCheckin(converteStringTOLocalDateTime("2021-09-02 10:00"))
                .idCompraVoo(compraVoo())
                .cpf("123.456.789-05")
                .id(1L)
                .build();
    }

    private CompraVoo compraVoo() {
       return CompraVoo.builder()
                .id(1L)
                .voo(voo())
                .celular("2345678")
                .cpf("123.456.789-05")
                .dataNascimento(converteStringTOLocalDate("1980-05-08"))
                .email("varney@gmail.com")
                .nomeCliente("varney")
                .sexo("Masculino")
                .nacionalidade("Brasileira")
                .build();
    }

    private Voo voo() {
       return Voo.builder()
                .idVoo(1L)
                .dataVoo(converteStringTOLocalDateTime("2021-09-02 10:00"))
                .fechaPortao(LocalTime.parse("09:30"))
                .horaEmbarque(LocalTime.parse("09:00"))
                .tempoDuracao(LocalTime.parse("03:00"))
                .portao("2")
                .origem("Guarulhos")
                .destino("Rio de Janeiro")
                .valor(BigDecimal.valueOf(200.0))
               .aeronave(aeronave())
                .build();
    }

    private Aeronave aeronave(){
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

        return Aeronave.builder()
                .id(1L)
                .assentos(assentos)
                .marca("Boing")
                .modelo("747")
                .build();

    }

    private SolicitarCheckinRequest requestBuilder() {
        return SolicitarCheckinRequest.newBuilder()
                .setData(
                        SolicitarCheckinRequestData.newBuilder()
                                .setIdVoo(1L)
                                .setCpf("123.456.789-05")
                                .build()
                )
                .build();
    }

    private CheckinRequestDTO requestDTOBuilder() {
        return CheckinRequestDTO.builder()
                .cpf("123.456.789-05")
                .idCompraVoo(1L)
                .build();
    }

    public LocalDateTime converteStringTOLocalDateTime(String data) {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDate = LocalDateTime.parse(data, parser);
        return localDate;
    }

    public LocalDate converteStringTOLocalDate(String data) {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(data, parser);
        return localDate;
    }
}
