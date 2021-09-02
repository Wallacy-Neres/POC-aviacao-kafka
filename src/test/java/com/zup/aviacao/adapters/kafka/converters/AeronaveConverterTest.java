package com.zup.aviacao.adapters.kafka.converters;

import com.zup.aviacao.DTO.AeronaveRequestDTO;
import com.zup.aviacao.DTO.AeronaveResponseDTO;
import com.zup.aviacao.DTO.AssentoRequestDTO;
import com.zup.aviacao.converter.AeronaveConverter;
import com.zup.aviacao.enums.StatusAssento;
import com.zup.aviacao.model.Aeronave;
import com.zup.aviacao.model.Assento;
import com.zup.aviacao.solicitacao_cadastro_aeronave.AssentosListaElementos;
import com.zup.aviacao.solicitacao_cadastro_aeronave.SolicitarCadastroAeronaveRequest;
import com.zup.aviacao.solicitacao_cadastro_aeronave.SolicitarCadastroAeronaveRequestData;
import com.zup.aviacao.solicitacao_cadastro_aeronave.SolicitarCadastroAeronaveResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(MockitoJUnitRunner.class)
public class AeronaveConverterTest {

    @InjectMocks
    private AeronaveConverter converter;

    @Test
    public void avroToDtoTest(){

        SolicitarCadastroAeronaveRequest avro = avroToDtoBuilder();

        AeronaveRequestDTO dto = converter.avroToDto(avro);

        Assert.assertNotNull(dto);
        Assert.assertEquals("Boing", dto.getMarca());
        Assert.assertEquals("747", dto.getModelo());
        Assert.assertEquals(3, dto.getAssentos().size());
    }

    @Test
    public void aeronaveToResponseDto(){


        AeronaveResponseDTO responseDTO = converter.aeronaveEntityToDto(aeronaveEntityToDtoBuilder());

        Assert.assertNotNull(responseDTO);
        Assert.assertEquals("Boing", responseDTO.getMarca());
        Assert.assertEquals("747", responseDTO.getModelo());
    }

    @Test
    public void requestToEntity(){

        AeronaveRequestDTO requestDTO = requestToEntityBuilder();

        Aeronave aeronave = converter.aeronavetoEntity(requestDTO);

        Assert.assertNotNull(aeronave);
        Assert.assertEquals("Boing", aeronave.getMarca());
        Assert.assertEquals("3004", aeronave.getModelo());
    }

    @Test
    public void dtoResponseToAvro(){

        AeronaveResponseDTO response = dtoResponseToAvroBuilder();

        SolicitarCadastroAeronaveResponse avro = converter.dtoResponseToAvro(response);

        Assert.assertNotNull(avro);
        Assert.assertEquals("Boing", avro.getData().getMarca());
        Assert.assertEquals("747", avro.getData().getModelo());
    }

    private AeronaveResponseDTO dtoResponseToAvroBuilder() {
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

       return AeronaveResponseDTO.builder()
                .marca("Boing")
                .modelo("747")
                .assentos(assentos)
                .build();
    }

    private AeronaveRequestDTO requestToEntityBuilder() {
        List<String> listaAssentos = new ArrayList<String>() {
            {
                add("20");
                add("30");
                add("40");
            }
        };

        List<AssentoRequestDTO> assentos = listaAssentos.stream().map(assento -> AssentoRequestDTO.builder()
                .numeroAssento(assento).build()).collect(Collectors.toList());

        AeronaveRequestDTO aeronave = AeronaveRequestDTO.builder()
                .marca("Boing")
                .modelo("3004")
                .assentos(assentos).build();


        return AeronaveRequestDTO.builder()
                .marca(aeronave.getMarca())
                .modelo(aeronave.getModelo())
                .assentos(aeronave.getAssentos())
                .build();
    }

    private SolicitarCadastroAeronaveRequest avroToDtoBuilder(){

        List<String> listaAssentos = new ArrayList<String>() {
            {
                add("20");
                add("30");
                add("40");
            }
        };

        List<AssentosListaElementos> assentos = listaAssentos.stream().map(assento -> AssentosListaElementos.newBuilder()
                .setNumeroAssento(assento)
                .build()).collect(Collectors.toList());

        return SolicitarCadastroAeronaveRequest.newBuilder()
                .setData(
                        SolicitarCadastroAeronaveRequestData.newBuilder()
                                .setMarca("Boing")
                                .setModelo("747")
                                .setAssentos(assentos)
                                .build()
                ).build();
    }

    private Aeronave aeronaveEntityToDtoBuilder(){
        return Aeronave.builder()
                .marca("Boing")
                .modelo("747")
                .build();
    }
}
