public class SQLTickets extends PoolConn {

private final String version;

public SQLTickets(String myPool) throws SQLException, NamingException {
super(myPool);
this.version = myPool;
}

public List<TuplasTickets> getListaTickets() throws SQLException {
Connection conn = PGconectar();
List<TuplasTickets> tp = new ArrayList<>();
try {
PreparedStatement st = conn.prepareStatement("SELECT * from tickets");
ResultSet rs = st.executeQuery();
while (rs.next()) {
tp.add( new TuplasTickets.
Builder().
build()
);
}
} catch (SQLException e) {
System.out.println("tickets Connection Failed!");
} finally {
conn.close();
}
return tp;
}

public List<TuplasTickets> getListaTickets(int NumPage, int SizePage, String ) throws SQLException {
Connection conn = PGconectar();
List<TuplasTickets> tp = new ArrayList<>();
try {
int Offset = SizePage * (NumPage-1);
PreparedStatement st = conn.prepareStatement("SELECT * from tickets where estanque = ? order by id desc LIMIT ? OFFSET ?");
st.setInt(1, Integer.parseInt(xEstanque) );
st.setInt(2, SizePage);
st.setInt(3, Offset);
ResultSet rs = st.executeQuery();
while (rs.next()) {
tp.add( new TuplasTickets.
Builder().
build()
);
}
} catch (SQLException e) {
System.out.println("tickets Connection Failed!");
} finally {
conn.close();
}
return tp;
}

}
