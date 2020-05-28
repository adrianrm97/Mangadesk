package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import controlador.MangaController;
import modelo.Manga;

public class UsuarioFrame extends JFrame {

	

	private JPanel contentPane;
	private JTextField textFieldtitulo;
	private JTextField textFieldgeneros;
	private JTextField textFieldvaloracion;
	//private JPanel panelimagen;
	private JLabel image;
	private JLabel lbltitulo;
	private JLabel lblgeneros;
	private JLabel lblvaloracion;
	String rutaimagen = "";
	private JLabel lblimagen;
	private JTextField textFieldmarcapaginas;
	private JButton btnguardar;
	private JTextArea textAreadescripcion;
	private MangaController Escaparate = null;

	/**
	 * Create the frame.
	 * @param nuevo 
	 */
	public UsuarioFrame(Manga nuevo) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1047, 498);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		try {
			Escaparate = new MangaController();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
 
		definirVentana(nuevo);
		definirEventos(nuevo);
		setVisible(true);
	}

	private void definirVentana(Manga nuevo) {
		contentPane.setLayout(null);
		// TODO Auto-generated method stub
		lbltitulo = new JLabel("Titulo:");
		lbltitulo.setBounds(206, 44, 50, 14);
		contentPane.add(lbltitulo);
		
		lblgeneros = new JLabel("Generos:");
		lblgeneros.setBounds(206, 83, 60, 21);
		contentPane.add(lblgeneros);
		
		lblvaloracion = new JLabel("Valoracion:");
		lblvaloracion.setBounds(206, 134, 71, 14);
		contentPane.add(lblvaloracion);
		
		textFieldtitulo = new JTextField();
		textFieldtitulo.setEditable(false);
		textFieldtitulo.setBounds(297, 41, 418, 20);
		contentPane.add(textFieldtitulo);
		textFieldtitulo.setColumns(10);
		
		textFieldgeneros = new JTextField();
		textFieldgeneros.setBounds(297, 83, 418, 20);
		contentPane.add(textFieldgeneros);
		textFieldgeneros.setColumns(10);
		
		textFieldvaloracion = new JTextField();
		textFieldvaloracion.setBounds(297, 131, 50, 20);
		contentPane.add(textFieldvaloracion);
		textFieldvaloracion.setColumns(10);
		
		//imagen
		
		ImageIcon imagen = new ImageIcon(nuevo.getImagen());
		Image ImagenBien = imagen.getImage();
		Image newimg = ImagenBien.getScaledInstance(160, 240, java.awt.Image.SCALE_SMOOTH);
		imagen = new ImageIcon(newimg);
		
		//fin imagen
		lblimagen = new JLabel(imagen);
		lblimagen.setBounds(22, 27, 163, 237);
		contentPane.add(lblimagen);
		
		JLabel lblmarcapaginas = new JLabel("Marcapaginas:");
		lblmarcapaginas.setBounds(206, 177, 84, 14);
		contentPane.add(lblmarcapaginas);
		
		textFieldmarcapaginas = new JTextField();
		textFieldmarcapaginas.setBounds(297, 174, 554, 20);
		contentPane.add(textFieldmarcapaginas);
		textFieldmarcapaginas.setColumns(10);
		
		JLabel lbldescripcion = new JLabel("Descripcion:");
		lbldescripcion.setBounds(206, 231, 79, 14);
		contentPane.add(lbldescripcion);
		
		textAreadescripcion = new JTextArea();
		textAreadescripcion.setLineWrap(true);
		textAreadescripcion.setBounds(297, 226, 429, 126);
		contentPane.add(textAreadescripcion);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Im\u00E1gen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 184, 264);
		contentPane.add(panel);
		
		btnguardar = new JButton("Guardar");
		btnguardar.setBounds(762, 369, 89, 23);
		contentPane.add(btnguardar);
		
		
		
		
		textFieldtitulo.setText(nuevo.getTitulo());
		textFieldgeneros.setText(nuevo.getGeneros());
		textFieldmarcapaginas.setText(nuevo.getMarcapaginas());
		textFieldvaloracion.setText(nuevo.getValoracion()+"");
		textAreadescripcion.setText(nuevo.getDescripcion());
		
	}

	private void definirEventos(Manga nuevo) {
		
		btnguardar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//le mandamos al metodo SubirManga(); todos los campos necesarios
				String titulo = textFieldtitulo.getText();
				String generos = textFieldgeneros.getText();
				String marcapaginas = textFieldmarcapaginas.getText();
				int valoracion = Integer.parseInt(textFieldvaloracion.getText());
				String descripcion = textAreadescripcion.getText();
			
				try {
					Escaparate.abrirConexion();
					System.out.println(titulo);
					Escaparate.GuardarCambios(titulo,generos,marcapaginas,valoracion,descripcion);
					Escaparate.consultaMangasPst("SELECT * FROM manga");
					Escaparate.cerrarConexion();
				} catch (SQLException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				
			}
		});//fin btnguardar
	}
}
