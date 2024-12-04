package LibClases;
public class Cliente implements Cloneable
{
    private static int n=0;
    private static Fecha fechaAltaPorDefecto = new Fecha(1,1,2018);

    private final String nif; //dni del cliente (letra incluida) (NO puede cambiar)
    private final int codCliente; //codigo único (y fijo) generado por la aplicación
    private String nombre; //nombre completo del cliente (SI se puede modificar)
    private final Fecha fechaNac; //fecha nacimiento del cliente (NO se puede cambiar)
    private final Fecha fechaAlta; //fecha de alta del cliente (SI se puede modificar)

    public Cliente (String NIF, String nom, Fecha fNac, Fecha fAlta)
    {
        n++;
        codCliente=n;
        nif=NIF;
        nombre=nom;
        fechaNac=new Fecha(fNac);
        fechaAlta=new Fecha(fAlta);
    }

    public Cliente (String NIF, String nom, Fecha fNac) {
        this(NIF,nom,fNac,fechaAltaPorDefecto);
    }

    public static Fecha getFechaPorDefecto() {
        return new Fecha(fechaAltaPorDefecto);
    }

    public static void setFechaPorDefecto(Fecha fnueva) {
        fechaAltaPorDefecto = new Fecha(fnueva);
    }

    public Cliente(Cliente c)
    {
        n++;
        codCliente=n;
        nif=c.nif;
        nombre=c.nombre;
        fechaNac=new Fecha(c.fechaNac);
        fechaAlta=(Fecha) c.fechaAlta.clone();
    }


    public String toString(){ //devuelve una cadena con la información del cliente
        String s=" ";
        s= "Nombre: " + nombre + ", Nif: " + nif + ", CodCliente: " + codCliente + ", FechaNac: " + fechaNac + ", FechaAlta: " + fechaAlta;
        return s;
    }

    public String getNif() {
        return nif;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String Nombre) {
        nombre=Nombre;
    };

    public Fecha getFechaNac() {
        return (Fecha) fechaNac.clone();
    }

    public Fecha getFechaAlta() {
        return new  Fecha(fechaAlta);
    }

    public void setFechaAlta(Fecha fAlta)
    {
        fechaAlta.setFecha(fAlta.getDia(), fAlta.getMes(), fAlta.getAnio());
    }

    public void ver()
    {
        System.out.println(this);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cliente c = (Cliente) obj;
        return (nif.equals(c.nif));
    }

    @Override
    public Object clone()
    {
        return new Cliente(this);
    }


    public float factura()
    {
        return 0;
    }
}

