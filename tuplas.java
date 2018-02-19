import lombok.Data;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@Entity
public class Propiedades implements Serializable {
private String descripcion;
private String horas;
private String codigo;
private String comunero;
private String ordenriego;
private String propietario;
private String unidades;
private String anejo;
private String comunidad;

}
