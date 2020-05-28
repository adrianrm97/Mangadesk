package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.ComponentOrientation;

import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;

import controlador.MangaController;

import modelo.Manga;

import javax.swing.border.EtchedBorder;
import javax.swing.JRadioButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.SystemColor;

public class Mangadesk extends JFrame implements ActionListener  {
	private ArrayList<Manga> listaMangas = null;
	private MangaController Escaparate = null;
	private JPanel contentPane;
	private JTextField textFieldBuscar;
	private JButton btnadministrar;
	private JButton btnaplicarfiltros;
	private ButtonGroup tipo = new ButtonGroup();
	private JRadioButton rdbtnmanga;
	private JRadioButton rdbtnmanhwa;
	private JRadioButton rdbtnmanhua;
	ArrayList<JRadioButton> tipos = new ArrayList<JRadioButton>();
	private JCheckBox chckbxaccion;
	private JCheckBox chckbxmagia;
	private JCheckBox chckbxromance;
	private JCheckBox chckbxisekai;
	private JCheckBox chckbxrecuentos;
	private JCheckBox chckbxdrama;
	private JCheckBox chckbxthriller;
	private JCheckBox chckbxsupervivencia;
	private JCheckBox chckbxcrimen;
	private JCheckBox chckbxvidaescolar;
	private JCheckBox chckbxdeportes;
	ArrayList<JCheckBox> checkBoxSeleccionados = new ArrayList<JCheckBox>();
	private JPanel panelCentral;
	private JPanel panelMangas; //Grid con todos los mangas
	private JLabel lblMangaDisplay;
	private JButton mangaBoton;
	private JLabel lblMangaDisplay2;
	private ArrayList<Manga> mangasFiltrados = null;
	//private Manga[] cincoMangas = new Manga[5];
	private JButton btnRefrescar;
	
	/**
	 * Create the frame.
	 */
	public Mangadesk() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1177, 804);
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
		
	
			try {
				listaMangas = Escaparate.consultaMangasPst("SELECT * FROM manga");
				Escaparate.cerrarConexion();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		
		definirVentana();
		definirEventos();
		
		setVisible(true);
	}

	private void definirEventos() {
		// TODO Auto-generated method stub
		textFieldBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					System.out.println(textFieldBuscar.getText());
					//String titulo = "";
					//Mandar a metodo BuscarTitulo(titulo);
				}
				
			}
		});//buscar
		
		btnadministrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AdministradorFrame admin = new AdministradorFrame();
			}
		});//administrar
		
		btnaplicarfiltros.addActionListener(new ActionListener() {
			//AQUI ES DONDE VAMOS A MANDAR TODA LA INFORMACIÓN A LOS MÉTODOS QUE BUSCAN POR FILTROS.
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String tipo = "";
				for (JRadioButton jRadioButton : tipos) {
					if (jRadioButton.isSelected()) {
						 tipo = jRadioButton.getText();
					}//FINIF
				}//FINFOR
				String generos = "";
				for (JCheckBox caja : checkBoxSeleccionados) {
					if (caja.isSelected()) {
						generos += ", ";
					}
				}
				mangasFiltrados = Escaparate.muestraFiltrados(tipo,generos);
				
			}//FIN ACTIONPERFORMED
		});//aplicar
		
		
		btnRefrescar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Escaparate.abrirConexion();
					listaMangas = Escaparate.consultaMangasPst("SELECT * FROM manga");
					Escaparate.cerrarConexion();
				} catch (SQLException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}//fin definir eventos

	private void definirVentana() {
		// TODO Auto-generated method stub
		panelMangas = new JPanel();
		panelMangas.setBackground(new Color(245, 245, 245));
		panelMangas.setBounds(172, 169, 957, 409);
		contentPane.add(panelMangas);
		ArrayList<JLabel> labels = new ArrayList<JLabel>();
		panelMangas.setLayout(new GridLayout((labels.size()/5),5,2,2));
		
		//imagen
		
		int cantidadMangas = listaMangas.size();
		
		//FOR IMAGENES DEL OBJETO MANGA
		for (int i = 0; i < listaMangas.size(); i++) {
			ImageIcon imagen = new ImageIcon(listaMangas.get(i).getImagen());
			Image ImagenBien = imagen.getImage();
			Image newimg = ImagenBien.getScaledInstance(200, 250, java.awt.Image.SCALE_SMOOTH);
			imagen = new ImageIcon(newimg);
			//fin imagen
			
			
			mangaBoton = new JButton(imagen);
			mangaBoton.setOpaque(false);
			mangaBoton.setForeground(Color.white);
			mangaBoton.setBorderPainted(false);
			mangaBoton.setFocusPainted(false);
			mangaBoton.setContentAreaFilled(false);
			mangaBoton.putClientProperty("libro", listaMangas.get(i));
			panelMangas.add(mangaBoton);
			
			mangaBoton.addActionListener(this);
			
				
			
			
		}//FIN DEL FOR
		
		
		
		
		//aqui acaba las imagenes en el centro ^
		textFieldBuscar = new JTextField();
		textFieldBuscar.setToolTipText("buscar");
		textFieldBuscar.setBounds(25, 21, 659, 29);
		contentPane.add(textFieldBuscar);
		textFieldBuscar.setColumns(10);
		
		btnaplicarfiltros = new JButton("Aplicar filtros");
		btnaplicarfiltros.setBounds(25, 606, 114, 23);
		contentPane.add(btnaplicarfiltros);
		
		btnadministrar = new JButton("");
		btnadministrar.setIcon(new ImageIcon("C:\\Users\\adrian\\Desktop\\70.jpg"));
		btnadministrar.setBounds(44, 684, 70, 70);
		contentPane.add(btnadministrar);
		
		panelCentral = new JPanel();
		panelCentral.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Tipo:", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panelCentral.setBounds(8, 100, 106, 124);
		contentPane.add(panelCentral);
		panelCentral.setLayout(null);
		
		rdbtnmanga = new JRadioButton("Manga");
		rdbtnmanga.setBounds(6, 21, 81, 23);
		panelCentral.add(rdbtnmanga);
		
		rdbtnmanhwa = new JRadioButton("Manhwa");
		rdbtnmanhwa.setBounds(6, 57, 81, 23);
		panelCentral.add(rdbtnmanhwa);
		
		rdbtnmanhua = new JRadioButton("Manhua");
		rdbtnmanhua.setBounds(6, 94, 81, 23);
		panelCentral.add(rdbtnmanhua);
		tipo.add(rdbtnmanga);tipo.add(rdbtnmanhwa);tipo.add(rdbtnmanhua);
		tipos.add(rdbtnmanga);tipos.add(rdbtnmanhua);tipos.add(rdbtnmanhwa);
JPanel panel_1 = new JPanel();
panel_1.setBorder(new TitledBorder(null, "G\u00E9neros", TitledBorder.LEADING, TitledBorder.TOP, null, null));
panel_1.setBounds(8, 240, 154, 338);
contentPane.add(panel_1);
GridBagLayout gbl_panel_1 = new GridBagLayout();
gbl_panel_1.columnWidths = new int[]{0, 0};
gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
gbl_panel_1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
panel_1.setLayout(gbl_panel_1);
chckbxaccion = new JCheckBox("Accion");
GridBagConstraints gbc_chckbxaccion = new GridBagConstraints();
gbc_chckbxaccion.anchor = GridBagConstraints.WEST;
gbc_chckbxaccion.insets = new Insets(0, 0, 5, 0);
gbc_chckbxaccion.gridx = 0;
gbc_chckbxaccion.gridy = 0;
panel_1.add(chckbxaccion, gbc_chckbxaccion);
JCheckBox chckbxdeportes = new JCheckBox("Deportes");
chckbxdeportes.setHorizontalAlignment(SwingConstants.LEFT);
GridBagConstraints gbc_chckbxdeportes = new GridBagConstraints();
gbc_chckbxdeportes.insets = new Insets(0, 0, 5, 0);
gbc_chckbxdeportes.anchor = GridBagConstraints.WEST;
gbc_chckbxdeportes.gridx = 0;
gbc_chckbxdeportes.gridy = 1;
panel_1.add(chckbxdeportes, gbc_chckbxdeportes);
chckbxmagia = new JCheckBox("Magia");
chckbxmagia.setHorizontalAlignment(SwingConstants.LEFT);
GridBagConstraints gbc_chckbxmagia = new GridBagConstraints();
gbc_chckbxmagia.anchor = GridBagConstraints.WEST;
gbc_chckbxmagia.insets = new Insets(0, 0, 5, 0);
gbc_chckbxmagia.gridx = 0;
gbc_chckbxmagia.gridy = 2;
panel_1.add(chckbxmagia, gbc_chckbxmagia);
chckbxromance = new JCheckBox("Romance");
GridBagConstraints gbc_chckbxromance = new GridBagConstraints();
gbc_chckbxromance.anchor = GridBagConstraints.WEST;
gbc_chckbxromance.insets = new Insets(0, 0, 5, 0);
gbc_chckbxromance.gridx = 0;
gbc_chckbxromance.gridy = 3;
panel_1.add(chckbxromance, gbc_chckbxromance);
chckbxisekai = new JCheckBox("Isekai");
GridBagConstraints gbc_chckbxisekai = new GridBagConstraints();
gbc_chckbxisekai.anchor = GridBagConstraints.WEST;
gbc_chckbxisekai.insets = new Insets(0, 0, 5, 0);
gbc_chckbxisekai.gridx = 0;
gbc_chckbxisekai.gridy = 4;
panel_1.add(chckbxisekai, gbc_chckbxisekai);
chckbxrecuentos = new JCheckBox("Recuentos de la vida");
GridBagConstraints gbc_chckbxrecuentos = new GridBagConstraints();
gbc_chckbxrecuentos.anchor = GridBagConstraints.WEST;
gbc_chckbxrecuentos.insets = new Insets(0, 0, 5, 0);
gbc_chckbxrecuentos.gridx = 0;
gbc_chckbxrecuentos.gridy = 5;
panel_1.add(chckbxrecuentos, gbc_chckbxrecuentos);
chckbxdrama = new JCheckBox("Drama");
GridBagConstraints gbc_chckbxdrama = new GridBagConstraints();
gbc_chckbxdrama.anchor = GridBagConstraints.WEST;
gbc_chckbxdrama.insets = new Insets(0, 0, 5, 0);
gbc_chckbxdrama.gridx = 0;
gbc_chckbxdrama.gridy = 6;
panel_1.add(chckbxdrama, gbc_chckbxdrama);
chckbxthriller = new JCheckBox("Thriller");
GridBagConstraints gbc_chckbxthriller = new GridBagConstraints();
gbc_chckbxthriller.anchor = GridBagConstraints.WEST;
gbc_chckbxthriller.insets = new Insets(0, 0, 5, 0);
gbc_chckbxthriller.gridx = 0;
gbc_chckbxthriller.gridy = 7;
panel_1.add(chckbxthriller, gbc_chckbxthriller);
chckbxsupervivencia = new JCheckBox("Supervivencia");
GridBagConstraints gbc_chckbxsupervivencia = new GridBagConstraints();
gbc_chckbxsupervivencia.anchor = GridBagConstraints.WEST;
gbc_chckbxsupervivencia.insets = new Insets(0, 0, 5, 0);
gbc_chckbxsupervivencia.gridx = 0;
gbc_chckbxsupervivencia.gridy = 8;
panel_1.add(chckbxsupervivencia, gbc_chckbxsupervivencia);
chckbxcrimen = new JCheckBox("Crimen");
GridBagConstraints gbc_chckbxcrimen = new GridBagConstraints();
gbc_chckbxcrimen.anchor = GridBagConstraints.WEST;
gbc_chckbxcrimen.insets = new Insets(0, 0, 5, 0);
gbc_chckbxcrimen.gridx = 0;
gbc_chckbxcrimen.gridy = 9;
panel_1.add(chckbxcrimen, gbc_chckbxcrimen);
chckbxvidaescolar = new JCheckBox("Vida escolar");
GridBagConstraints gbc_chckbxvidaescolar = new GridBagConstraints();
gbc_chckbxvidaescolar.anchor = GridBagConstraints.WEST;
gbc_chckbxvidaescolar.gridx = 0;
gbc_chckbxvidaescolar.gridy = 10;
panel_1.add(chckbxvidaescolar, gbc_chckbxvidaescolar);
	checkBoxSeleccionados.add(chckbxcrimen);checkBoxSeleccionados.add(chckbxdeportes);checkBoxSeleccionados.add(chckbxisekai);
	checkBoxSeleccionados.add(chckbxrecuentos);checkBoxSeleccionados.add(chckbxromance);checkBoxSeleccionados.add(chckbxsupervivencia);
	checkBoxSeleccionados.add(chckbxthriller);checkBoxSeleccionados.add(chckbxvidaescolar);checkBoxSeleccionados.add(chckbxaccion);
	checkBoxSeleccionados.add(chckbxdrama);checkBoxSeleccionados.add(chckbxmagia);
btnRefrescar = new JButton("Refrescar");
btnRefrescar.setBounds(215, 718, 89, 23);
contentPane.add(btnRefrescar);
	//---
	
	
	
	
	
	
	
	
	
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		
		JButton test = (JButton) e.getSource();
		//System.out.println(test.getClientProperty("libro"));
		Manga nuevo = (Manga) test.getClientProperty("libro");
		UsuarioFrame user = new UsuarioFrame(nuevo);
		
	}
}
