export interface Producto {
    id: number;
    nombre: string;
    precio: number;
    imagen: string;
    stock: number;
   
  }

  export interface ProductoEnCarrito extends Producto{
    cantidad: number;
  }

  export interface CartProps {
    carrito: ProductoEnCarrito[];
    isOpen: boolean;
    cerrarCarrito: ()=>void;
    abrirCarrito: ()=>void;
  }

  export interface CartContextProps {
    carrito: ProductoEnCarrito[];
    agregarAlCarrito: (producto: Producto, cantidad: number) => void;
    eliminarDelCarrito:(productoId:number)=> void;
    actualizarCantidad: (productoId: number, nuevaCantidad: number) => void;
    isOpen: boolean;
    vaciarCarrito: () => void;
    cerrarCarrito: () => void;
    abrirCarrito: () => void;
    setCarrito: React.Dispatch<React.SetStateAction<ProductoEnCarrito[]>>;
    cantidadTotalProductos: number;
    calcularTotal: ()=>number;

    searchQuery: string; // El término de búsqueda
    setSearchQuery: (query: string) => void;

    
    categoryId: number | null;  // ID de la categoría seleccionada
    setCategoryId: (id: number | null) => void
    

  }
  
  export interface DetallePedido{
    id?: number;
      producto:Partial<Producto> & { id: number };
      cantidad: number;
      pedido:{id:number}
  }

  export interface CarritoItem{
      id:number;
      cantidad:number;
      nombre?:string;
      precio?:number;
  }

  export const convertirADetallePedido = (detalle: DetallePedido):CarritoItem=>{
    return{
      id:detalle.producto.id,
      cantidad:detalle.cantidad,
    }
  }





export interface PedidoCreado{
  id:number;
  montoTotal:number;
  usuarioId:number;
  metodoPagoId:number;
}

export interface Usuario {
  id?: number;  
  username: string;
  email: string;
  password: string;
  firstName: string;
  lastName: string;
  gender: string;
  birthDate: string;
  phone: string;
  pedidos?: Pedido[];
}


export interface Pedido {
  id: number;
  montoTotal: number;
  usuario: Usuario; // Incluyendo el usuario que realizó el pedido
  detallePedidos: DetallePedido[]; // Lista de detalles del pedido
}