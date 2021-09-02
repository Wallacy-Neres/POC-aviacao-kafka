package com.zup.aviacao.adapters.kafka.converters;

import com.zup.aviacao.DTO.CompraVooRequestDTO;
import com.zup.aviacao.DTO.CompraVooResponseDTO;
import com.zup.aviacao.converter.CompraVooConverter;
import com.zup.aviacao.model.CompraVoo;
import com.zup.aviacao.model.Voo;
import com.zup.aviacao.solicitacao_cadastro_voo.SolicitarCadastroVooRequest;
import com.zup.aviacao.solicitacao_cadastro_voo.SolicitarCompraVooRequest;
import com.zup.aviacao.solicitacao_cadastro_voo.SolicitarCompraVooRequestData;
import com.zup.aviacao.solicitacao_cadastro_voo.SolicitarCompraVooResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Parser;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@RunWith(MockitoJUnitRunner.class)
public class CompraVooConverterTest {

    @InjectMocks
    private CompraVooConverter converter;

    @Mock
    private EntityManager manager;

    @Test
    public void compraVooEntityTest(){

        CompraVooRequestDTO compraVooRequestDTO = compraVooRequestDTOBuilder();

        converter.compraVooEntity(compraVooRequestDTO, manager);

        Assert.assertNotNull(compraVooRequestDTO);
        Assert.assertEquals("2" , compraVooRequestDTO.getIdVoo().toString());
        Assert.assertEquals("varney@gmail.com", compraVooRequestDTO.getEmail());

    }

    private CompraVooRequestDTO compraVooRequestDTOBuilder() {
        return CompraVooRequestDTO.builder()
                .assento("20")
                .email("varney@gmail.com")
                .celular("2345678")
                .cpf("123.654.987-04")
                .dataNascimento(converteStringToLocalDate("1980-06-04"))
                .sexo("Masculino")
                .nomeCliente("Varney")
                .nacionalidade("Br")
                .idVoo(2L)
                .build();
    }

    @Test
    public void avroToDtoTest(){

        SolicitarCompraVooRequest solicitarCompraVooRequest = solicitarCompraVooRequestBuilder();

        CompraVooRequestDTO compraVooRequestDTO = converter.avroToDto(solicitarCompraVooRequest);

        Assert.assertNotNull(compraVooRequestDTO);
        Assert.assertEquals("4", compraVooRequestDTO.getIdVoo().toString());
        Assert.assertEquals("2345678", compraVooRequestDTO.getCelular());
    }

    @Test
    public void compraVooEntityToDtoTest(){
        CompraVoo compraVoo = compraVooBuilder();
        CompraVooResponseDTO compraVooResponseDTO = converter.compraVooEntityToDto(compraVoo);

        Assert.assertNotNull(compraVooResponseDTO);
        Assert.assertEquals("1", compraVooResponseDTO.getIdVoo().toString());
        Assert.assertEquals("Varney", compraVooResponseDTO.getNomeCliente());
    }

    @Test
    public void dtoResponseToAvroTest(){
        CompraVooResponseDTO responseDTO = responseDTOBuilder();
        SolicitarCompraVooResponse solicitarCompraVooResponse = converter.dtoResponseToAvro(responseDTO);

        Assert.assertNotNull(solicitarCompraVooResponse);
        Assert.assertEquals("1", solicitarCompraVooResponse.getData().getIdVoo().toString());
    }

    private CompraVooResponseDTO responseDTOBuilder() {
        return CompraVooResponseDTO.builder()
                .nomeCliente("Varney")
                .cpf("123.456.789-05")
                .idVoo(1L)
                .build();
    }

    private CompraVoo compraVooBuilder() {

        Voo voo = Voo.builder()
                .dataVoo(converteStringTOLocalDate("2021-09-01 10:00"))
                .idVoo(1L)
                .origem("Guarulhos")
                .destino("Canadá")
                .tempoDuracao(LocalTime.parse("03:00"))
                .fechaPortao(LocalTime.parse("05:00"))
                .horaEmbarque(LocalTime.parse("04:00"))
                .portao("2")
                .valor(BigDecimal.valueOf(2000.0))
                .build();



        return CompraVoo.builder()
                .voo(voo)
                .assento("20")
                .sexo("Masculino")
                .nacionalidade("Brasileira")
                .nomeCliente("Varney")
                .cpf("123.456.789-85")
                .dataNascimento(LocalDate.parse("1980-08-04"))
                .celular("2345678")
                .email("varney@gmail.com")
                .id(1L)
                .build();
    }

    private SolicitarCompraVooRequest solicitarCompraVooRequestBuilder() {
        return SolicitarCompraVooRequest.newBuilder()
                .setData(
                        SolicitarCompraVooRequestData.newBuilder()
                                .setIdVoo(4L)
                                .setCelular("2345678")
                                .setNacionalidade("BR")
                                .setAssento("20")
                                .setSexo("Masculino")
                                .setEmail("varney@gmail.com")
                                .setCpf("123.456.789-05")
                                .setDataNascimento("1980-08-15")
                                .setNomeCliente("Varney")
                                .build()
                )
                .build();
    }

    public LocalDateTime converteStringTOLocalDate(String data) {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDate = LocalDateTime.parse(data, parser);
        return localDate;
    }

    public LocalDate converteStringToLocalDate(String data) {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(data, parser);
        return localDate;
    }
}
