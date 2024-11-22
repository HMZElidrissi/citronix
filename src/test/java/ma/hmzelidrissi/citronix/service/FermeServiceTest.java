package ma.hmzelidrissi.citronix.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import ma.hmzelidrissi.citronix.criteria.FermeSearchCriteria;
import ma.hmzelidrissi.citronix.criteria.FermeSpecificationBuilder;
import ma.hmzelidrissi.citronix.domain.Ferme;
import ma.hmzelidrissi.citronix.dto.request.FermeRequestDTO;
import ma.hmzelidrissi.citronix.dto.response.FermeResponseDTO;
import ma.hmzelidrissi.citronix.exception.ResourceNotFoundException;
import ma.hmzelidrissi.citronix.mapper.FermeMapper;
import ma.hmzelidrissi.citronix.repository.FermeRepository;
import ma.hmzelidrissi.citronix.service.impl.FermeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * Test class for ma.hmzelidrissi.citronix.service.impl.FermeServiceImpl.
 *
 * @author hmzelidrissi
 * @version 1.0
 * @see ma.hmzelidrissi.citronix.service.impl.FermeServiceImpl why testing an implementation rather
 *     than an interface? see
 *     https://stackoverflow.com/questions/10937763/unit-under-test-impl-or-interface
 */
@ExtendWith(MockitoExtension.class)
class FermeServiceTest {
  @Mock private FermeRepository fermeRepository;
  @Mock private FermeMapper fermeMapper;
  @Mock private FermeSpecificationBuilder specificationBuilder;
  @InjectMocks private FermeServiceImpl fermeService;

  @Test
  void createFerme_Success() {
    // Arrange
    FermeRequestDTO requestDTO =
        new FermeRequestDTO("Ferme 1", "Localisation 1", 10000.0, LocalDate.now());
    Ferme ferme = Ferme.builder().nom("Ferme 1").superficie(10000.0).build();
    FermeResponseDTO expectedResponse =
        FermeResponseDTO.builder().nom("Ferme 1").superficie(10000.0).build();

    when(fermeMapper.toEntity(requestDTO)).thenReturn(ferme);
    when(fermeRepository.save(ferme)).thenReturn(ferme);
    when(fermeMapper.toDTO(ferme)).thenReturn(expectedResponse);

    // Act
    FermeResponseDTO result = fermeService.createFerme(requestDTO);

    // Assert
    assertThat(result).isEqualTo(expectedResponse);
    verify(fermeRepository).save(ferme);
  }

  @Test
  void updateFerme_WhenFermeExists_Success() {
    // Arrange
    Long id = 1L;
    FermeRequestDTO requestDTO =
        new FermeRequestDTO("Updated Ferme", "Localisation 1", 20000.0, LocalDate.now());
    Ferme existingFerme = Ferme.builder().id(id).nom("Old Ferme").superficie(10000.0).build();
    Ferme updatedFerme = Ferme.builder().id(id).nom("Updated Ferme").superficie(20000.0).build();
    FermeResponseDTO expectedResponse =
        FermeResponseDTO.builder().nom("Updated Ferme").superficie(20000.0).build();

    when(fermeRepository.findById(id)).thenReturn(Optional.of(existingFerme));
    when(fermeRepository.save(existingFerme)).thenReturn(updatedFerme);
    when(fermeMapper.toDTO(updatedFerme)).thenReturn(expectedResponse);

    // Act
    FermeResponseDTO result = fermeService.updateFerme(id, requestDTO);

    // Assert
    assertThat(result).isEqualTo(expectedResponse);
    verify(fermeMapper).updateEntityFromDTO(requestDTO, existingFerme);
  }

  @Test
  void updateFerme_WhenFermeNotFound_ThrowsException() {
    // Arrange
    Long id = 1L;
    FermeRequestDTO requestDTO =
        new FermeRequestDTO("Updated Ferme", "Localisation 1", 20000.0, LocalDate.now());
    when(fermeRepository.findById(id)).thenReturn(Optional.empty());

    // Act & Assert
    assertThrows(ResourceNotFoundException.class, () -> fermeService.updateFerme(id, requestDTO));
  }

  @Test
  void deleteFerme_WhenFermeExists_Success() {
    // Arrange
    Long id = 1L;
    when(fermeRepository.existsById(id)).thenReturn(true);

    // Act
    fermeService.deleteFerme(id);

    // Assert
    verify(fermeRepository).deleteById(id);
  }

  @Test
  void deleteFerme_WhenFermeNotFound_ThrowsException() {
    // Arrange
    Long id = 1L;
    when(fermeRepository.existsById(id)).thenReturn(false);

    // Act & Assert
    assertThrows(ResourceNotFoundException.class, () -> fermeService.deleteFerme(id));
  }

  @Test
  void getFermes_Success() {
    // Arrange
    FermeSearchCriteria criteria = new FermeSearchCriteria("Test", null, null, null);
    Pageable pageable = PageRequest.of(0, 10);
    Specification<Ferme> spec = mock(Specification.class);
    List<Ferme> fermes =
        List.of(Ferme.builder().nom("Ferme 1").build(), Ferme.builder().nom("Ferme 2").build());
    Page<Ferme> fermePage = new PageImpl<>(fermes, pageable, fermes.size());
    List<FermeResponseDTO> responseDTOs =
        List.of(
            FermeResponseDTO.builder().nom("Ferme 1").build(),
            FermeResponseDTO.builder().nom("Ferme 2").build());

    when(specificationBuilder.build(criteria)).thenReturn(spec);
    when(fermeRepository.findAll(spec, pageable)).thenReturn(fermePage);
    when(fermeMapper.toDTO(any(Ferme.class))).thenReturn(responseDTOs.get(0), responseDTOs.get(1));

    // Act
    Page<FermeResponseDTO> result = fermeService.getFermes(criteria, pageable);

    // Assert
    assertThat(result.getContent()).hasSize(2);
    assertThat(result.getContent()).containsExactlyElementsOf(responseDTOs);
  }
}
