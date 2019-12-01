package Bean;

public class Producto {
    private int id_prod;
    private String nom_prod;
    private double precio_prod;
    private boolean estado_prod;
        
    public Producto() {
    }

    public Producto(int id_prod, String nom_prod, double precio_prod, boolean estado_prod) {
        this.id_prod = id_prod;
        this.nom_prod = nom_prod;
        this.precio_prod = precio_prod;
        this.estado_prod = estado_prod;
    }

    public int getId_prod() {
        return id_prod;
    }

    public void setId_prod(int id_prod) {
        this.id_prod = id_prod;
    }

    public String getNom_prod() {
        return nom_prod;
    }

    public void setNom_prod(String nom_prod) {
        this.nom_prod = nom_prod;
    }

    public double getPrecio_prod() {
        return precio_prod;
    }

    public void setPrecio_prod(double precio_prod) {
        this.precio_prod = precio_prod;
    }

    public boolean getEstado_prod() {
        return estado_prod;
    }

    public void setEstado_prod(boolean estado_prod) {
        this.estado_prod = estado_prod;
    }

    @Override
    public String toString() {
        return "Producto{" + "id_prod=" + id_prod + ", nom_prod=" + nom_prod + ", precio_prod=" + precio_prod + ", estado_prod=" + estado_prod + '}';
    }    
}
