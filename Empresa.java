package LibClases;

import java.util.*;

public class Empresa implements Cloneable
{
    private Cliente[] t;
    private int n;
    private int nmax;

    public Empresa() { //constructor creado solo para que no de error el main

    }

    private int buscar(Cliente c, int ini)
    {
        for(int i=ini;i<n;i++)
        {
            if(t[i].equals(c))
                return i;
        }
        return -1;
    }

    private int buscar(String nif, int ini)
    {
        for(int i=ini;i<n;i++)
        {
            if(t[i].getNif().equals(nif))
                return i;
        }
        return -1;
    }

    public Empresa(int nmax)
    {
        n=0;
        nmax=5;
        t=new Cliente[nmax];
    }

    public int getN()
    {
        return n;
    }

    public void alta()
    {
        String nif,nombre,nacionalidad;
        int tipo,pos;
        float minutos,precio;
        Fecha fNac,fAlta,ffin;

        Cliente c;

        Scanner sc=new Scanner(System.in);
        System.out.println("Ingrese el DNI del cliente: ");
        nif=sc.nextLine();
        pos=buscar(nif,0);
        if(pos!=-1) {
            System.out.println("Ya existe un cliente con ese dni");

        } else {

            System.out.println("Ingrese el nombre del cliente: ");
            nombre=sc.nextLine();
            System.out.println("Ingrese la fecha de nacimiento del cliente: ");
            fNac=Fecha.pedirFecha();
            System.out.println("Ingrese la fecha de alta del cliente: ");
            fAlta=Fecha.pedirFecha();
            System.out.println("Ingrese la cantidad de minutos que habla al mes: ");
            minutos=sc.nextFloat();
            sc.nextLine();//consumir salto de linea
            System.out.println("Ingrese el tipo de cliente (1-Movil, 2-Tarifa Plana):");
            tipo=sc.nextInt();
            sc.nextLine();//consumir salto de linea

            if(tipo==1||tipo==3)
            {
                System.out.println("Ingrese el precio por minuto: ");
                precio=sc.nextFloat();
                sc.nextLine();//consumir salto de linea
                System.out.println("Ingrese la fecha de fin del permanencia: ");
                ffin=Fecha.pedirFecha();
                c=new ClienteMovil(nif,nombre,fNac,fAlta,ffin,minutos,precio);

            }else{

                System.out.println("Ingrese la nacionalidad: ");
                nacionalidad=sc.next();
                c=new ClienteTarifaPlana(nif,nombre,fNac,fAlta,minutos,nacionalidad);
            }
            alta(c);

        }
        //sc.close();
    }

    public void alta(Cliente c)
    {
       if(buscar(c,0)!=-1)
           return;

       if(n==nmax)
        {
            Cliente[] aux=t;
            nmax+=5;
            t=new Cliente[nmax];
            for(int i=0;i<n;i++)
                t[i]=aux[i];
        }
       t[n]=c;
       n++;
    }

    public void baja()
    {
        int pos;
        String nif,resp;
        Scanner sc=new Scanner(System.in);
        System.out.println("Ingrese el NIF del cliente a dar de baja: ");
        nif=sc.nextLine();
        pos=buscar(nif,0);
        if(pos!=-1){
            t[pos].ver();
            System.out.println("Seguro que desea dar de baja el cliente? (s/n) ");
            resp=sc.nextLine();
            if(resp.equals("s"))
            {

                System.out.println("El cliente " + t[pos].getNif() + " con NIF " + t[pos].getNif() + " ha sido dado de baja");
                baja(nif,pos);

            }else{

                System.out.println("No se ha dado de baja el cliente");
            }
        }
        //sc.close();
    }

    public void baja(int pos)//cambiado de protected a public para que no falle el main
    {
        t[pos]=null;
        for(int i=pos + 1;i<n;i++)
            t[i-1]=t[i];
        n--;
    }


    protected Class<? extends Cliente> returnGetClass(int i)
    {
        return t[i].getClass();
    }

    public void baja(String nif, int pos)
    {
        if(pos==-1)
        {
            for(int i=0;i<n;i++) {
                if (t[i].getNif()==nif) {
                    pos=i;
                    break;
                }
            }
        }
        if(pos != -1)
        {
            for(int j=pos+1;j<n;j++)
            {
                t[j - 1] = t[j];
                n--;
            }
        }
    }

    public void baja(String nif) {
        int pos = buscar(nif, 0);  // Busca el cliente por su NIF en el arreglo

        if (pos != -1) {
            baja(pos);
        }
    }


    @Override
    public String toString()
    {
        String s="";
        for(int i=0;i<n;i++)
            s=s+t[i].toString()+"\n";
        return s;
    }

    public float factura()
    {
        float fac=0;
        for(int i=0;i<n;i++)
        {
            fac += t[i].factura();
        }
        return fac;
    }

    public void descuento(int dto)
    {
        for(int i=0;i<n;i++)
        {
            if(t[i].getClass()==ClienteMovil.class)
            {
                ClienteMovil c=(ClienteMovil) t[i];
                c.setPrecio(c.getPrecio()*dto/100f);
            }
        }
    }

    public int nClienteMovil()
    {
        int n=0;
        for(int i=0;i<n;i++)
        {
            if(t[i].getClass()==ClienteMovil.class)
            {
                n++;
            }
        }
        return n;
    }

    public Object clone()
    {
        Empresa obj=null;

        try
        {
            obj = (Empresa) super.clone();
            obj.t=(Cliente[]) this.t.clone();
            for(int i=0;i<n;i++)
            {
                obj.t[i] = (Cliente) this.t[i].clone();
            }
        } catch (CloneNotSupportedException e) {
             System.out.println("No se puede duplicar");
        }
        return obj;
    }

    @Override
    public boolean equals(Object obj)
    {
        int pos=0;
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Empresa e = (Empresa) obj;

        if(this.n!=e.n)
            return false;

        for(int i=0;i<this.n;i++)
        {
            if(pos==-1|| !e.t[pos].equals(t[i]))
                return false;
        }
        return true;
    }
}
