import java.io.Serializable;

public class BeanTickets implements Serializable {

private String tipo;
private String minutos_comprados;
private String geopos;
private String minutos_servidos;
private String canal_compra;
private String pendiente;
private String observaciones;
private String id;
private String fecha_buy;
private String estanque;
private String fecha_riego;
private String nticket;

public String setTipo(String tipo) {
this.tipo=tipo;
}

public String setMinutos_comprados(String minutos_comprados) {
this.minutos_comprados=minutos_comprados;
}

public String setGeopos(String geopos) {
this.geopos=geopos;
}

public String setMinutos_servidos(String minutos_servidos) {
this.minutos_servidos=minutos_servidos;
}

public String setCanal_compra(String canal_compra) {
this.canal_compra=canal_compra;
}

public String setPendiente(String pendiente) {
this.pendiente=pendiente;
}

public String setObservaciones(String observaciones) {
this.observaciones=observaciones;
}

public String setId(String id) {
this.id=id;
}

public String setFecha_buy(String fecha_buy) {
this.fecha_buy=fecha_buy;
}

public String setEstanque(String estanque) {
this.estanque=estanque;
}

public String setFecha_riego(String fecha_riego) {
this.fecha_riego=fecha_riego;
}

public String setNticket(String nticket) {
this.nticket=nticket;
}

public String getTipo() {
return tipo;
}

public String getMinutos_comprados() {
return minutos_comprados;
}

public String getGeopos() {
return geopos;
}

public String getMinutos_servidos() {
return minutos_servidos;
}

public String getCanal_compra() {
return canal_compra;
}

public String getPendiente() {
return pendiente;
}

public String getObservaciones() {
return observaciones;
}

public String getId() {
return id;
}

public String getFecha_buy() {
return fecha_buy;
}

public String getEstanque() {
return estanque;
}

public String getFecha_riego() {
return fecha_riego;
}

public String getNticket() {
return nticket;
}

}
