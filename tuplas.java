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

public static class Builder {
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
private String version

public Builder() {
this.version = "1.0"
}
public Builder Tipo(final String tipo) {
this.tipo = tipo;
return this.tipo;
}

public Builder Minutos_comprados(final String minutos_comprados) {
this.minutos_comprados = minutos_comprados;
return this.minutos_comprados;
}

public Builder Geopos(final String geopos) {
this.geopos = geopos;
return this.geopos;
}

public Builder Minutos_servidos(final String minutos_servidos) {
this.minutos_servidos = minutos_servidos;
return this.minutos_servidos;
}

public Builder Canal_compra(final String canal_compra) {
this.canal_compra = canal_compra;
return this.canal_compra;
}

public Builder Pendiente(final String pendiente) {
this.pendiente = pendiente;
return this.pendiente;
}

public Builder Observaciones(final String observaciones) {
this.observaciones = observaciones;
return this.observaciones;
}

public Builder Id(final String id) {
this.id = id;
return this.id;
}

public Builder Fecha_buy(final String fecha_buy) {
this.fecha_buy = fecha_buy;
return this.fecha_buy;
}

public Builder Estanque(final String estanque) {
this.estanque = estanque;
return this.estanque;
}

public Builder Fecha_riego(final String fecha_riego) {
this.fecha_riego = fecha_riego;
return this.fecha_riego;
}

public Builder Nticket(final String nticket) {
this.nticket = nticket;
return this.nticket;
}

public Tickets build() {
return new Tickets(this);
}
}

private Tickets(Builder builder) {
this.tipo=builder.tipo;
this.minutos_comprados=builder.minutos_comprados;
this.geopos=builder.geopos;
this.minutos_servidos=builder.minutos_servidos;
this.canal_compra=builder.canal_compra;
this.pendiente=builder.pendiente;
this.observaciones=builder.observaciones;
this.id=builder.id;
this.fecha_buy=builder.fecha_buy;
this.estanque=builder.estanque;
this.fecha_riego=builder.fecha_riego;
this.nticket=builder.nticket;
}

}
