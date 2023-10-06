import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//@author Javier Torres
//@version version 3, 05 Octubre 2023
class Pago{
    private float monto;
    private Date fecha;
    //constructor por defecto
    public Pago(float monto) {
        this.monto = monto;
    }
    public float getMonto() {
        return monto;
    }
    public void setMonto(float monto) {
        this.monto = monto;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
class Efectivo extends Pago{
    //constructor por defecto
    public Efectivo(float montoPagado) {
        super(montoPagado);
    }

    public void calcDevolucion(float precioTotal){
        float montoPagado = getMonto();
        if (montoPagado >= precioTotal) {
            float vuelto = montoPagado - precioTotal;
            System.out.println("Vuelto: " + vuelto);
        } else {
            System.out.println("No se alcanza el dinero para comprar.");
        }
    }
}
class Transferencia extends Pago{
    private String banco;
    private String numCuenta;
    //constructor por defecto
    public Transferencia(float monto, Date fecha, String banco, String numCuenta) {
        super(monto);
        this.banco = banco;
        this.numCuenta = numCuenta;
    }
    public String getBanco() {
        return banco;
    }
    public void setBanco(String banco) {
        this.banco = banco;
    }
    public String getNumCuenta() {
        return numCuenta;
    }
    public void setNumCuenta(String numCuenta) {
        this.numCuenta = numCuenta;
    }
}
class Tarjeta extends Pago{
    private String tipo;
    private String numTransaccion;
    //constructor por defecto
    public Tarjeta(float monto, Date fecha, String tipo, String numTransaccion) {
        super(monto);
        this.tipo = tipo;
        this.numTransaccion = numTransaccion;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getNumTransaccion() {
        return numTransaccion;
    }
}
class Direccion{
    private String direccion;
    //constructor por defecto
    public Direccion(String direccion){
        this.direccion=direccion;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    @Override
    public String toString() {
        return "Dirección: " + direccion;
    }
}

class Articulo{
    private String nombre;
    private String descripcion;
    private float peso;
    private float precio;
    //constructor por defecto
    public Articulo(String nombre, String descripcion, float peso, float precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.peso = peso;
        this.precio = precio;
    }
    public String getNombre() {
        return nombre;
    }
    public float getPeso() {
        return peso;
    }
    public float getPrecio() {
        return precio;
    }
    @Override
    public String toString() {
        return "Artículo: " + nombre + "\nDescripción: " + descripcion + "\nPeso: " + peso + "\nPrecio: " + precio;
    }
}

class Cliente {
    private String nombre;
    private String rut;
    private Direccion direccion;
    private List<OrdenDeCompra> ordenesDeCompra = new ArrayList<>();
    //constructor por defecto
    public Cliente(String nombre, String rut, Direccion direccion) {
        this.nombre = nombre;
        this.rut = rut;
        this.direccion=direccion;

    }
    public String getNombre() {
        return nombre;
    }
    public String getRut() {
        return rut;
    }
    public Direccion getDireccion() {
        return direccion;
    }

    public void agregarOrdenDeCompra(OrdenDeCompra ordenDeCompra) {
        ordenesDeCompra.add(ordenDeCompra);
    }
    public List<OrdenDeCompra> getOrdenesDeCompra() {
        return ordenesDeCompra;
    }
    @Override
    public String toString() {
        return "Cliente: " + nombre + "\nRUT: " + rut + "\nDirección: " + direccion.getDireccion();
    }
}

class OrdenDeCompra {
    private Date fecha;
    private String estado;
    private Cliente cliente;
    private DetalleOrden detalle; //!!cambiar a arraylist, estoy haciendo el diamantenegro del UML al reves
    private DocTributario docTributario;
    private Pago pago;
    //constructor por defecto
    public OrdenDeCompra(Date fecha, String estado, Cliente cliente, DocTributario docTributario) {
        this.fecha = fecha;
        this.estado = estado;
        this.cliente = cliente;
        this.docTributario = docTributario;

    }
    public void asignardetalle(DetalleOrden detalle) {
        this.detalle = detalle;
    }
    public void asignarpago(Pago pago) {
        this.pago = pago;
    }
    public void asignarDocTributario(DocTributario docTributario) {
        this.docTributario = docTributario;
    }
    public Date getFecha() {
        return fecha;
    }
    public String getEstado() {
        return estado;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public DetalleOrden getDetalle() {
        return detalle;
    }
    public DocTributario getDocTributario() {
        return docTributario;
    }
    public Pago getPago() {
        return pago;
    }
}

class DetalleOrden {
    private int cantidad; //!!no se está ocupando correctamente
    private ArrayList<Articulo> articulos;  // !!cambiar, no deberia ser una lista (un detalleorden es la cantidad de un articulo particular)
    private float precioTotal;

    //constructor por defecto
    public DetalleOrden(int cantidad) {
        this.articulos = new ArrayList<>();
        this.precioTotal = 0;
        this.cantidad = 0;
    }
    public void agregarArticulo(Articulo articulo) {
        articulos.add(articulo);
        precioTotal += articulo.getPrecio();
    }
    public int getCantidad() {
        return cantidad;
    }
    public float getPrecioSinIva(){
        return calcPrecioSinIva();
    }
    public float getPrecioTotal(){
        return calcPrecioConIva();
    }
    public ArrayList<Articulo> getArticulos() {
        return articulos;
    } ////!!cambiar de ArrayList<Articulo> a Articulo
    public float calcPrecioSinIva(){
        float precioSinIva = 0.0f;
        for (Articulo articulo : articulos) {
            precioSinIva += articulo.getPrecio();
        }
        return precioSinIva;
    }//@descripcion sumar el precio de los articulos
    public float calcPrecioConIva(){
        float precioSinIva = calcPrecioSinIva();
        float precioConIva = precioSinIva * 1.21f;
        return precioConIva;
    }//@descripcion aplicarle el Iva al precio total de los articulos
    public float calcPeso(){
        float pesoTotal = 0.0f;
        for (Articulo articulo : articulos) {
            pesoTotal += articulo.getPeso();
        }
        return pesoTotal;
    }//@descripcion sumar el peso de la todos los articulos añadidos en el detalle
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Detalle de la Orden:\n");
        for (Articulo articulo : articulos) {
            sb.append(" - ").append(articulo).append("\n");
        }
        sb.append("Precio Sin el Iva: ").append(getPrecioSinIva()).append("\n");
        sb.append("Precio Total: ").append(getPrecioTotal());
        return sb.toString();
    }
}


class DocTributario {
    private String rut;
    private Date fecha;
    private Direccion direccion;
    //constructor por defecto
    public DocTributario(String rut, Date fecha, Direccion direccion) {
        this.rut = rut;
        this.fecha = fecha;
        this.direccion = direccion;
    }
    public String getRut() {
        return rut;
    }
    public void setRut(String rut) {
        this.rut = rut;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public Direccion getDireccion() {
        return direccion;
    }
    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
}
class Boleta extends DocTributario{
    //constructor por defecto
    public Boleta(String rut, Date fecha, Direccion direccion) {
        super(rut, fecha, direccion);
    }
}
class Factura extends DocTributario{
    //constructor por defecto
    public Factura(String rut, Date fecha, Direccion direccion) {
        super(rut, fecha, direccion);
    }
}















public class Main {
    public static void main(String[] args) {
        //Creo 2 cliente con sus respectivas direcciones
        Direccion d1 = new Direccion("San Carlos");
        Cliente c1 = new Cliente("Javier", "123456789", d1);
        Direccion d2= new Direccion("Concepcion");
        Cliente c2 = new Cliente("Camila", "987654321", d1);

        // Creo los artículos
        Articulo arti1 = new Articulo("coca cola", "bebestible", 5, 1000);
        Articulo arti2 = new Articulo("chocolate", "dulce", 1, 300);
        Articulo arti3 = new Articulo("galleta", "snack", 3, 650);
        Articulo arti4 = new Articulo("jabón", "articulo de aseo", 3, 1100);
        Articulo arti5 = new Articulo("lapiz", "util escolar", 0.5f, 320);
        Articulo arti6 = new Articulo("taza", "menaje", 6, 1300);
        Articulo arti7 = new Articulo("espejo", "decoración", 10, 12000);
        Articulo arti8 = new Articulo("paracetamol", "remedio", 1, 550);
        Articulo arti9 = new Articulo("ipad", "computacion", 18, 290000);
        Articulo arti10 = new Articulo("café", "bebestible", 5, 1300);


        //Creo Boleta y Factura
        Boleta boleta = null;
        Factura factura= null;

        //Creo los detalles
        DetalleOrden detalle1 = new DetalleOrden(5);
        detalle1.agregarArticulo(arti1);
        detalle1.agregarArticulo(arti2);
        detalle1.agregarArticulo(arti3);
        detalle1.agregarArticulo(arti4);
        detalle1.agregarArticulo(arti5);

        DetalleOrden detalle2 = new DetalleOrden(5);
        detalle2.agregarArticulo(arti6);
        detalle2.agregarArticulo(arti7);
        detalle2.agregarArticulo(arti8);
        detalle2.agregarArticulo(arti9);
        detalle2.agregarArticulo(arti10);

        // Ejemplo1: cliente c1 quiere boleta, pagará con efectivo y hay que darle vuelto(como paga con efectivo hay que definir con cuanto pagará)
        boleta = new Boleta(c1.getRut(), new Date(), c1.getDireccion());
        float montoPagado1 = 5000;
        float montoTotal1 = detalle1.getPrecioTotal();
        Efectivo pagoEfectivo = new Efectivo(montoPagado1);
        // Crear la orden de compra
        OrdenDeCompra ordenDeCompra1 = new OrdenDeCompra(new Date(), "Pendiente", c1, boleta);
        ordenDeCompra1.asignardetalle(detalle1);
        ordenDeCompra1.asignarpago(pagoEfectivo);
        //Impresion de la compra
        System.out.println("Orden de Compra1:");
        System.out.println("Cliente: " + ordenDeCompra1.getCliente().getNombre());
        System.out.println("Fecha: " + ordenDeCompra1.getFecha());
        System.out.println("Estado: " + ordenDeCompra1.getEstado());
        System.out.println("Documento Tributario: " + ordenDeCompra1.getDocTributario().getClass().getSimpleName());
        System.out.println("Detalle de Orden:");
        for (Articulo articulo : detalle1.getArticulos()) {
            System.out.println(" - " + articulo.getNombre());
        }
        System.out.println("Precio Sin el Iva: " + detalle1.getPrecioSinIva());
        System.out.println("Precio Total: " + detalle1.getPrecioTotal());

        if (pagoEfectivo instanceof Efectivo) {
            Efectivo pagoEfectivoC1 = (Efectivo) pagoEfectivo;
            System.out.println("Monto Pagado: " + pagoEfectivoC1.getMonto());
            pagoEfectivoC1.calcDevolucion(detalle1.getPrecioTotal());
        } else {
            System.out.println("Monto Pagado: " + pagoEfectivo.getMonto());
        }
        System.out.println();


        //Ejemplo 2: cliente c2 quiere factura, pagará con tarjeta(como paga con tarjeta hay que dar los detalles de la tarjeta)
        float montoTotal2 = arti1.getPrecio();
        String tipoTarjeta = "Debito";
        String numTransaccion = "111222333";
        Tarjeta tarjeta2 = new Tarjeta(montoTotal2, new Date(), tipoTarjeta, numTransaccion);
        factura = new Factura(c2.getRut(), new Date(), c2.getDireccion());
        // Crear la orden de compra
        OrdenDeCompra ordenDeCompra2 = new OrdenDeCompra(new Date(), "Pendiente", c2, factura);
        ordenDeCompra2.asignardetalle(detalle2);
        ordenDeCompra2.asignarpago(tarjeta2);
        //Impresion de la compra
        System.out.println("Orden de Compra2:");
        System.out.println("Cliente: " + ordenDeCompra2.getCliente().getNombre());
        System.out.println("Fecha: " + ordenDeCompra2.getFecha());
        System.out.println("Estado: " + ordenDeCompra2.getEstado());
        System.out.println("Documento Tributario: " + ordenDeCompra2.getDocTributario().getClass().getSimpleName());
        System.out.println("Detalle de la Orden:");
        for (Articulo articulo : detalle2.getArticulos()) {
            System.out.println(" - " + articulo.getNombre());
        }
        System.out.println("Precio Sin el Iva: " + detalle2.getPrecioSinIva());
        System.out.println("Precio Total: " + detalle2.getPrecioTotal());
        System.out.println("Monto Pagado con Tarjeta: " + detalle2.getPrecioTotal());
        System.out.println("Tipo de Tarjeta: " + tarjeta2.getTipo());
        System.out.println("Número de Transacción: " + tarjeta2.getNumTransaccion());
        System.out.println();


        //Ejemplo 3: cliente c1 quiere boleta, pagará con transferencia el detalle1(como paga con transferencia hay que dar los detalles de la transferencia)
        String banco3= "Banco Estado";
        String numcuenta3= "2244668800";
        Transferencia transferencia3= new Transferencia(montoTotal1,new Date(),banco3,numcuenta3);
        OrdenDeCompra ordenDeCompra3 = new OrdenDeCompra(new Date(), "Pendiente", c1, boleta);
        ordenDeCompra3.asignardetalle(detalle1);
        ordenDeCompra3.asignarpago(transferencia3);

        System.out.println("Orden de Compra3:");
        System.out.println("Cliente: " + ordenDeCompra3.getCliente().getNombre());
        System.out.println("Fecha: " + ordenDeCompra3.getFecha());
        System.out.println("Estado: " + ordenDeCompra3.getEstado());
        System.out.println("Documento Tributario: " + ordenDeCompra3.getDocTributario().getClass().getSimpleName());
        System.out.println("Detalle de Orden:");
        for (Articulo articulo : detalle1.getArticulos()) {
            System.out.println(" - " + articulo.getNombre());
        }
        System.out.println("Precio Sin el Iva: " + detalle1.getPrecioSinIva());
        System.out.println("Precio Total: " + detalle1.getPrecioTotal());
        System.out.println("Monto Pagado con Transferencia: " + detalle1.getPrecioTotal());
        System.out.println("Banco: " + transferencia3.getBanco());
        System.out.println("Número de Cuenta: " + transferencia3.getNumCuenta());
        System.out.println();


        //Ejemplo 4: cliente c2 quiere boleta, pagará con menos de lo que deberia el detalle2
        float montoPagado2=200000;
        pagoEfectivo= new Efectivo(montoPagado2);
        OrdenDeCompra ordenDeCompra4 = new OrdenDeCompra(new Date(), "Pendiente", c2, boleta);
        ordenDeCompra4.asignardetalle(detalle2);
        ordenDeCompra4.asignarpago(pagoEfectivo);

        System.out.println("Orden de Compra4:");
        System.out.println("Cliente: " + ordenDeCompra4.getCliente().getNombre());
        System.out.println("Fecha: " + ordenDeCompra4.getFecha());
        System.out.println("Estado: " + ordenDeCompra4.getEstado());
        System.out.println("Documento Tributario: " + ordenDeCompra4.getDocTributario().getClass().getSimpleName());
        System.out.println("Detalle de Orden:");
        for (Articulo articulo : detalle2.getArticulos()) {
            System.out.println(" - " + articulo.getNombre());
        }
        System.out.println("Precio Sin el Iva: " + detalle2.getPrecioSinIva());
        System.out.println("Precio Total: " + detalle2.getPrecioTotal());

        if (pagoEfectivo instanceof Efectivo) {
            Efectivo pagoEfectivoC2 = (Efectivo) pagoEfectivo;
            System.out.println("Monto Pagado: " + pagoEfectivoC2.getMonto());
            pagoEfectivoC2.calcDevolucion(detalle2.getPrecioTotal());
        } else {
            System.out.println("Monto Pagado: " + pagoEfectivo.getMonto());
        }
        System.out.println();
        //aaaa
        //probar 1 pago con tarjeta, 1 pago con transferencia, 2 pago efectivo(que pague justo, que tenga que dar vuelto o que no alvance el dinero)
        //@autocritic el programa funciona al pie de la letracomo se pedia, tengo unas relaciones mal aplicadas, expectativas para trabajos fututos es hacer el codigo adecuado a lo pedido
    }
}
















