package modelo;

public class Manga {
	private String titulo;
	private String autor;
	private String generos;
	private String marcapaginas;
	private String descripcion;
	private int valoracion;
	private String imagen;
	private String tipo;
	public Manga() {
		// TODO Auto-generated constructor stub
	}

	public Manga(String titulo, String generos, String marcapaginas, String descripcion, int valoracion,
			String imagen) {
		super();
		this.titulo = titulo;
		this.generos = generos;
		this.marcapaginas = marcapaginas;
		this.descripcion = descripcion;
		this.valoracion = valoracion;
		this.imagen = imagen;
	}
	
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Manga(String titulo, String generos, String marcapaginas, String descripcion, int valoracion,
			String imagen, String tipo) {
		super();
		this.titulo = titulo;
		this.generos = generos;
		this.marcapaginas = marcapaginas;
		this.descripcion = descripcion;
		this.valoracion = valoracion;
		this.imagen = imagen;
		this.tipo = tipo;
	}

	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getGeneros() {
		return generos;
	}
	public void setGeneros(String generos) {
		this.generos = generos;
	}
	public String getMarcapaginas() {
		return marcapaginas;
	}
	public void setMarcapaginas(String marcapaginas) {
		this.marcapaginas = marcapaginas;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getValoracion() {
		return valoracion;
	}
	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Manga other = (Manga) obj;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Manga [titulo=" + titulo + ", generos=" + generos + ", marcapaginas=" + marcapaginas + ", descripcion="
				+ descripcion + ", valoracion=" + valoracion + ", imagen=" + imagen + ", tipo=" + tipo + "]";
	}
	
	
	
}
