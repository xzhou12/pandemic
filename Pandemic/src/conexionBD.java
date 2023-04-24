import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class conexionBD {

	private static final String USER = "DAW_PNDC22_23_XIAL";
	private static final String PWD = "AX123";
	private static final String URL = "jdbc:oracle:thin:@192.168.3.26:1521:xe";
//	private static final String URL = "jdbc:oracle:thin:@oracle.ilerna.com:1521:xe";

	// Se conecta a la Base de datos
	public static Connection conectarBD() {
		Connection con = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PWD);
			System.out.println("Conectado a la base de datos");
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}

		return con;

	}

	public static boolean comprobarUsuario(String nombre) {
		Connection con = conectarBD();
		int count = 99;
		String sql = "SELECT COUNT(*) FROM JUGADOR WHERE nombre =  '" + nombre + "'";

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				count = rs.getInt("COUNT(*)");
			}

		} catch (SQLException e) {
			System.out.println(e);
		}

		if (count == 0) {
			return true;
		} else {
			return false;
		}

	}

	// Guardar un nuevo usuario
	public static void guardarUsuario(String nombre) {
		Connection con = conectarBD();

		// Sentencia
		String sql = "BEGIN addJugador('" + nombre + "'); END;";

		try {
			Statement st = con.createStatement();
			// Ejecuta la sentencia
			st.execute(sql);
		} catch (SQLException e) {
			System.out.println(e);
		}

	}

	// Guarda la partida terminada
	public static void guardarPartida(int idUsuario, int rondas, String resultado) {
		Connection con = conectarBD();

		String sql = "BEGIN addPartida(" + idUsuario + ", " + rondas + ", '" + resultado + "'); END;";

		try {
			Statement st = con.createStatement();
			st.execute(sql);
		} catch (SQLException e) {
			System.out.println(e);
		}

	}

}
