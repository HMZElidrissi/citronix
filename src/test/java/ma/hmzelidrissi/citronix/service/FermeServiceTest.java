package ma.hmzelidrissi.citronix.service;

import ma.hmzelidrissi.citronix.domain.Ferme;
import ma.hmzelidrissi.citronix.dto.request.FermeRequestDTO;
import ma.hmzelidrissi.citronix.dto.response.FermeResponseDTO;
import ma.hmzelidrissi.citronix.exception.ResourceNotFoundException;
import ma.hmzelidrissi.citronix.mapper.FermeMapper;
import ma.hmzelidrissi.citronix.repository.FermeRepository;
import ma.hmzelidrissi.citronix.service.impl.FermeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for ma.hmzelidrissi.citronix.service.impl.FermeServiceImpl.
 *
 * @author hmzelidrissi
 * @version 1.0
 * @see ma.hmzelidrissi.citronix.service.impl.FermeServiceImpl
 */
@ExtendWith(MockitoExtension.class)
class FermeServiceImplTest {

    @Mock
    private FermeRepository fermeRepository;

    @Mock
    private FermeMapper fermeMapper;

    @InjectMocks
    private FermeServiceImpl fermeService;

    private Ferme ferme;
    private FermeRequestDTO requestDTO;
    private FermeResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        ferme = Ferme.builder()
                .id(1L)
                .nom("Test Ferme")
                .localisation("Test Location")
                .superficie(10000.0)
                .dateCreation(LocalDate.now())
                .build();

        requestDTO = new FermeRequestDTO(
                "Test Ferme",
                "Test Location",
                10000.0,
                LocalDate.now()
        );

        responseDTO = FermeResponseDTO.builder()
                .id(1L)
                .nom("Test Ferme")
                .localisation("Test Location")
                .superficie(10000.0)
                .dateCreation(LocalDate.now())
                .build();
    }

    @Test
    void createFerme_Success() {
        when(fermeMapper.toEntity(requestDTO)).thenReturn(ferme);
        when(fermeRepository.save(ferme)).thenReturn(ferme);
        when(fermeMapper.toDTO(ferme)).thenReturn(responseDTO);

        FermeResponseDTO result = fermeService.createFerme(requestDTO);

        assertNotNull(result);
        assertEquals(responseDTO.nom(), result.nom());
        assertEquals(responseDTO.superficie(), result.superficie());

        verify(fermeMapper).toEntity(requestDTO);
        verify(fermeRepository).save(ferme);
        verify(fermeMapper).toDTO(ferme);
    }

    @Test
    void updateFerme_Success() {
        when(fermeRepository.findById(1L)).thenReturn(Optional.of(ferme));
        when(fermeRepository.save(ferme)).thenReturn(ferme);
        when(fermeMapper.toDTO(ferme)).thenReturn(responseDTO);

        FermeResponseDTO result = fermeService.updateFerme(1L, requestDTO);

        assertNotNull(result);
        assertEquals(responseDTO.nom(), result.nom());
        assertEquals(responseDTO.superficie(), result.superficie());

        verify(fermeRepository).findById(1L);
        verify(fermeMapper).updateEntityFromDTO(requestDTO, ferme);
        verify(fermeRepository).save(ferme);
    }

    @Test
    void updateFerme_NotFound() {
        when(fermeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> fermeService.updateFerme(1L, requestDTO));

        verify(fermeRepository).findById(1L);
        verifyNoMoreInteractions(fermeMapper, fermeRepository);
    }

    @Test
    void deleteFerme_Success() {
        when(fermeRepository.existsById(1L)).thenReturn(true);

        fermeService.deleteFerme(1L);

        verify(fermeRepository).existsById(1L);
        verify(fermeRepository).deleteById(1L);
    }

    @Test
    void deleteFerme_NotFound() {
        when(fermeRepository.existsById(1L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class,
                () -> fermeService.deleteFerme(1L));

        verify(fermeRepository).existsById(1L);
        verifyNoMoreInteractions(fermeRepository);
    }

    @Test
    void getFermeById_Success() {
        when(fermeRepository.findById(1L)).thenReturn(Optional.of(ferme));
        when(fermeMapper.toDTO(ferme)).thenReturn(responseDTO);

        FermeResponseDTO result = fermeService.getFermeById(1L);

        assertNotNull(result);
        assertEquals(responseDTO.nom(), result.nom());
        assertEquals(responseDTO.superficie(), result.superficie());

        verify(fermeRepository).findById(1L);
        verify(fermeMapper).toDTO(ferme);
    }

    @Test
    void getFermeById_NotFound() {
        when(fermeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> fermeService.getFermeById(1L));

        verify(fermeRepository).findById(1L);
        verifyNoMoreInteractions(fermeMapper);
    }

    @Test
    void getAllFermes_Success() {
        List<Ferme> fermes = List.of(ferme);
        when(fermeRepository.findAll()).thenReturn(fermes);
        when(fermeMapper.toDTO(ferme)).thenReturn(responseDTO);

        List<FermeResponseDTO> results = fermeService.getAllFermes();

        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals(responseDTO.nom(), results.get(0).nom());

        verify(fermeRepository).findAll();
        verify(fermeMapper).toDTO(ferme);
    }
}
