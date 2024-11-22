package ma.hmzelidrissi.citronix.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {
  private final String entity;
  private final Long id;

  public ResourceNotFoundException(String entity, Long id) {
    super(String.format("%s non trouvé avec l'identifiant %d", entity, id));
    this.entity = entity;
    this.id = id;
  }
}
