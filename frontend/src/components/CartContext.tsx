
import { createContext, useContext, useState, ReactNode } from "react";
import { CartContextProps, Producto, ProductoEnCarrito } from '@/interfaces/types';

export const CartContext = createContext<CartContextProps | undefined>(undefined);

interface CartProviderProps {
    children: ReactNode; // Especificar el tipo de children
  }

export const CartProvider = ({ children }: CartProviderProps) =>{
    const [carrito, setCarrito]=useState<ProductoEnCarrito[]>([]);
    const [isOpen, setIsOpen]= useState(false);

    const [searchQuery, setSearchQuery] = useState<string>('');
    
    const [categoryId, setCategoryId] =useState<number | null>(null);

    const agregarAlCarrito = (producto: Producto, cantidad: number) => {
        const newItem = {...producto, cantidad};
        setCarrito(prevCarrito=> [...prevCarrito,newItem]);
        setIsOpen(true);
    };
    const actualizarCantidad = (productoId: number, nuevaCantidad: number) => {
        setCarrito(prevCarrito =>
          prevCarrito.map(item =>
            item.id === productoId
              ? { ...item, cantidad: nuevaCantidad }
              : item
          )
        );
      };

    const eliminarDelCarrito = (productoId: number)=>{
      setCarrito(prevCarrito=> prevCarrito.filter(item=> item.id !== productoId))
    }
    const vaciarCarrito = () => {
      setCarrito([]);
  }

    const cerrarCarrito=()=>setIsOpen(false);
    const abrirCarrito=()=>setIsOpen(true);
    const cantidadTotalProductos=carrito.reduce((total, item)=>total+item.cantidad,0);
    const calcularTotal =():number=>{
      return carrito.reduce((total, item)=> total + item.precio*item.cantidad,0);
    } 


    const handleSearch=(query:string)=>{
      setSearchQuery(query);
    }

      
    const handleSelectCategory = (id: number | null)=>{
      setCategoryId(id)
    }
      
   
    return(
      <CartContext.Provider value={{ 
        carrito, agregarAlCarrito, isOpen, cerrarCarrito, 
        abrirCarrito, actualizarCantidad,setCarrito,
        eliminarDelCarrito,cantidadTotalProductos,calcularTotal,vaciarCarrito,
        searchQuery, setSearchQuery:handleSearch,  categoryId,
        setCategoryId: handleSelectCategory, 
        
        
        }}>
        {children}
      </CartContext.Provider>
      )


  }


export const useCart = () => {
  const context = useContext(CartContext);
  if (!context) {
      throw new Error('CartContext must be used within a CartProvider');
  }
  return context;
};




