package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import modelo.Manga;



public class MangaController {
	
	private final static String drv = "com.mysql.jdbc.Driver";
	private final static String db = "jdbc:mysql://localhost:3306/manga?useSSL=false";
	private final static String userAndPass = "root";
	private static Connection cn; // importamos la libreria de java.sql
	private Statement st; // para realizar las consultas, importamos igualmente java.sql
	private ResultSet rs; // cursor
	private ArrayList<Manga> listamangas;
	private PreparedStatement pst;
	
	public MangaController() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated constructor stub
		abrirConexion();
		listamangas = consultaMangasPst("SELECT * FROM manga");
		
	}
	
	
	//CONSULTA A LA BASE DE DATOS -> COGE TODOS LOS MANGAS Y LOS REUNE EN UNA LISTA.
	public ArrayList<Manga> consultaMangasPst(String consulta) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<Manga> mangas = new ArrayList<Manga>();
		pst = cn.prepareStatement(consulta);
		rs = pst.executeQuery();
		rs.last();// apunta a la ultima fila
		int tam = rs.getRow();
		rs.beforeFirst();// vuelve al principio. No a la primera fila sino delante.
		if (tam>0) {
			while (rs.next()) {
				String titulo = rs.getString("titulo");
				String generos = rs.getString("generos");
				String marcapaginas = rs.getString("marcapaginas");
				String descripcion = rs.getString("descripcion");
				int valoracion = rs.getInt("valoracion");
				String imagen = rs.getString("imagen");
				Manga nuevoManga = new Manga(titulo, generos, marcapaginas, descripcion, valoracion, imagen);
				mangas.add(nuevoManga);
				nuevoManga = null;
			}
		}
		return mangas;
	}
	
	
	
	
	
	public void cerrarConexion() throws SQLException {
		// TODO Auto-generated method stub
		if (rs != null)
			rs.close();
		if (st != null)
			st.close();
		if (pst != null)
			pst.close();
		if (cn != null)
			cn.close();
		System.out.println("Conexión cerrada.");
	}
	
	
	public ArrayList<Manga> getListamangas() {
		return listamangas;
	}
	public void setListamangas(ArrayList<Manga> listamangas) {
		this.listamangas = listamangas;
	}
	
	public void abrirConexion() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Class.forName(drv);
		cn = (Connection) DriverManager.getConnection(db, userAndPass, "");
		System.out.println("La conexión se realizó con éxito.");
	}


	public boolean subirManga(String titulo, String generos, String marcapaginas, int valoracion, String descripcion, String imagen, String tipo) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "insert into manga values(?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = cn.prepareStatement(sql);
		

		preparedStatement.setString(1, titulo);
		preparedStatement.setString(2, generos);
		preparedStatement.setString(3, marcapaginas);
		preparedStatement.setString(4, descripcion);
		preparedStatement.setInt(5, valoracion);
		preparedStatement.setString(6, imagen);
		preparedStatement.setString(7, tipo);
		preparedStatement.executeUpdate();
		preparedStatement = null;
		return true;
	}


	public ArrayList<Manga> muestraFiltrados(String tipo, String generos) {
		// TODO Auto-generated method stub
		return null;
	}


	public int GuardarCambios(String titulo, String generos, String marcapaginas, int valoracion, String descripcion) throws SQLException {
		// TODO Auto-generated method stub
		int rows = 0;
		String sql = "Update manga Set titulo=?, generos=?, marcapaginas=? , valoracion=?, descripcion=? where titulo=?";
		pst = cn.prepareStatement(sql);
		

		pst.setString(1, titulo);
		pst.setString(2, generos);
		pst.setString(3, marcapaginas);
		pst.setInt(4, valoracion);
		pst.setString(5, descripcion);
		pst.setString(6, titulo);
		rows = pst.executeUpdate();
		System.out.println(rows);
		return rows;

	}


	public ArrayList<Manga> buscarTitulo(String campo) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM manga WHERE titulo LIKE ?";
		PreparedStatement preparedStatement = cn.prepareStatement(sql);

		preparedStatement.setString(1,  "%" + campo + "%");
		rs = preparedStatement.executeQuery();
		rs.isLast();
		int tam = rs.getRow();
		rs.beforeFirst();

		if (tam >= 0) {
			listamangas = new ArrayList<Manga>();
			while (rs.next()) {
				
				String titulo = rs.getString("titulo");
				String generos = rs.getString("generos");
				String marcapaginas = rs.getString("marcapaginas");
				String descripcion = rs.getString("descripcion");
				int valoracion = rs.getInt("valoracion");
				String imagen = rs.getString("imagen");
				Manga manga = new Manga(titulo, generos, marcapaginas, descripcion, valoracion, imagen);
				listamangas.add(manga);
			}
		}

		return listamangas;
	}


	


	
}
