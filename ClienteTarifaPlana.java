package LibClases;

public class ClienteTarifaPlana extends Cliente
{
    private static float tarifa=20;
    private static float Limite=300;
    private float minutos;
    private String nacionalidad;

    public ClienteTarifaPlana(String NIF, String nom, Fecha fNac, Fecha fAlta, float minutos, String nacionalidad)
    {
        super(NIF,nom,fNac,fAlta);
        this.minutos=minutos;
        this.nacionalidad=nacionalidad;
    }

    public ClienteTarifaPlana(String NIF, String nom, Fecha fnac,float minutos, String nacion)
    {
        super(NIF,nom,fnac);
        this.minutos=minutos;
        this.nacionalidad=nacion;
    }

    protected ClienteTarifaPlana(ClienteTarifaPlana c)
    {
        super(c);
        minutos=c.minutos;
        nacionalidad=c.nacionalidad;
    }

    public static float getTarifa(){return tarifa;}
    public static float getLimite(){return Limite;}
    public float getMinutos(){return minutos;}
    public String getNacionalidad(){return nacionalidad;}
    public static void setTarifa(float limite,float precio){ClienteTarifaPlana.tarifa=precio;ClienteTarifaPlana.Limite=limite;}
    public void setMinutos(float m){minutos=m;}
    public void setNacionalidad(String n){nacionalidad=n;}


    public String toString()
    {
        String s=super.toString();
        s=s+", Minutos: "+minutos+", Nacionalidad: "+nacionalidad;
        return s;
    }

    public float factura()
    {
        float exceso=minutos-Limite;
        if(exceso<0) exceso=0;
        return tarifa+exceso*0.15f;
    }

    @Override
    public Object clone()
    {
        return new ClienteTarifaPlana(this);
    }

}
