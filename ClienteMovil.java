package LibClases;

public class ClienteMovil extends Cliente implements Proceso, Cloneable
{
    private float minutos;
    private float precio;
    private Fecha fPermanencia;

    public ClienteMovil(String NIF, String nom, Fecha fNac, Fecha fAlta, Fecha fFin, float minutos, float precio)
    {
        super(NIF,nom,fNac,fAlta);
        this.minutos=minutos;
        this.precio=precio;
        fPermanencia=(Fecha) fFin.clone();
    }

    public ClienteMovil(String NIF, String nom, Fecha fNac, float m,float p)
    {
        super(NIF,nom,fNac);
        minutos=m;
        precio=p;
        Fecha f=Cliente.getFechaPorDefecto();
        fPermanencia=new Fecha(f.getDia(), f.getMes(), f.getAnio()+1);
    }

    public ClienteMovil(ClienteMovil c)
    {
        super(c);
        minutos=c.minutos;
        precio=c.precio;
        fPermanencia=new Fecha(c.fPermanencia);
    }

    public float getMinutos(){return minutos;}
    public float getPrecio(){return precio;}
    public Fecha getFPermanencia(){return new Fecha(fPermanencia);}
    public void setMinutos(float m){minutos=m;}
    public void setPrecio(float p){precio=p;}
    public void setFPermanencia(Fecha f){fPermanencia=(Fecha) f.clone();}


    public String toString()
    {
        String s=super.toString();
        s=s+", Minutos: "+minutos+", Precio: "+precio+", Fecha Permanencia: "+fPermanencia;
        return s;
    }

    public float factura()
    {
        return (minutos*precio);
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
        return this.getNif().equals(c.getNif());
    }

    public void ver()
    {
        System.out.println(this);
    }

    @Override
    public Object clone()
    {
        return new ClienteMovil(this);
    }
}
