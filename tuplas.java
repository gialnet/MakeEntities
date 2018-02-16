import lombok.Data;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@Entity
public class Tickets implements Serializable {
private String tipo;
private integer minutos_comprados;
private point geopos;
private integer minutos_servidos;
private String canal_compra;
private String pendiente;
private String observaciones;
private integer id;
private timestamp fecha_buy;
private integer estanque;
private timestamp fecha_riego;
private integer nticket;

}
