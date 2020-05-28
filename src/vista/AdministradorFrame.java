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

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import controlador.MangaController;

public class AdministradorFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldtitulo;
	private JTextField textFieldgeneros;
	private JTextField textFieldvaloracion;
	private JButton btnimagen;
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
	private JTextField textFieldtipo;
	/**
	 * Create the frame.
	 */
	public AdministradorFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 912, 452);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		try {
			Escaparate = new MangaController();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		definirVentana();
		definirEventos();
		setVisible(true);
	}


	private void definirEventos() {
		// TODO Auto-generated method stub
		btnimagen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser fileChooser = new JFileChooser();
				int seleccion = fileChooser.showOpenDialog(textFieldtitulo);
				if (seleccion == JFileChooser.APPROVE_OPTION) {
					File fichero = fileChooser.getSelectedFile();
					String path = fichero.getAbsolutePath();
					BufferedImage img = null;
					try {
						img = ImageIO.read(new File(path));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					Image dimg = img.getScaledInstance(lblimagen.getWidth(), lblimagen.getHeight(),
					        Image.SCALE_SMOOTH);
					image = new JLabel("",new ImageIcon(path),JLabel.CENTER);
					ImageIcon imageIcon = new ImageIcon(dimg);
					lblimagen.setIcon(imageIcon);
					rutaimagen = path;
				}
			}
		});//fin btnimagen
		
		btnguardar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//le mandamos al metodo SubirManga(); todos los campos necesarios
				String titulo = textFieldtitulo.getText();
				String generos = textFieldgeneros.getText();
				String marcapaginas = textFieldmarcapaginas.getText();
				int valoracion = Integer.parseInt(textFieldvaloracion.getText());
				String tipo = textFieldtipo.getText();
				String descripcion = textAreadescripcion.getText();
				String imagen = rutaimagen;
				try {
					if (Escaparate.subirManga(titulo,generos,marcapaginas,valoracion,descripcion,imagen,tipo)) {
						JOptionPane.showMessageDialog(null, "Manga agregado con éxito");
						textAreadescripcion.setText("");
						textFieldtitulo.setText("");
						textFieldgeneros.setText("");
						textFieldmarcapaginas.setText("");
						textFieldvaloracion.setText("");
						lblimagen.setIcon(null);
						textFieldtipo.setText("");
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});//fin btnguardar
	}


	private void definirVentana() {
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
		
		btnimagen = new JButton("Buscar imagen");
		btnimagen.setBounds(22, 286, 163, 23);
		contentPane.add(btnimagen);
		
		/* ya no uso el panel
		panelimagen = new JPanel();
		panelimagen.setBounds(315, 157, 212, 313);
		contentPane.add(panelimagen);
		panelimagen.setLayout(new BorderLayout(0, 0));
		image = new JLabel("");
		panelimagen.add(image, BorderLayout.NORTH);
		*/
		lblimagen = new JLabel("");
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
		lbldescripcion.setBounds(206, 281, 79, 14);
		contentPane.add(lbldescripcion);
		
		textAreadescripcion = new JTextArea();
		textAreadescripcion.setLineWrap(true);
		textAreadescripcion.setBounds(286, 276, 429, 126);
		contentPane.add(textAreadescripcion);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Im\u00E1gen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 184, 264);
		contentPane.add(panel);
		
		btnguardar = new JButton("Guardar");
		btnguardar.setBounds(776, 364, 89, 23);
		contentPane.add(btnguardar);
		
		JLabel lblNewLabel = new JLabel("Tipo:");
		lblNewLabel.setBounds(220, 229, 46, 14);
		contentPane.add(lblNewLabel);
		
		textFieldtipo = new JTextField();
		textFieldtipo.setBounds(297, 226, 86, 20);
		contentPane.add(textFieldtipo);
		textFieldtipo.setColumns(10);
	}
}
