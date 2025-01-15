import { ProductoEnCarrito } from '@/interfaces/types';
import styles from './Cart.module.scss';
import { useRef } from 'react';
import { useCart } from './CartContext';
import Link from 'next/link';



const Cart = ()=>{
  

   const { carrito, isOpen, cerrarCarrito, eliminarDelCarrito,calcularTotal }=useCart();
  
    const overlay = useRef<HTMLDivElement | null>(null);
  

    const handleOverlayClick = (e: React.MouseEvent<HTMLDivElement>) => {
        if (overlay.current && e.target === overlay.current) {
            cerrarCarrito();
        }
    }

    if(!isOpen) return null;


    return(
 
        <div ref={overlay} className={styles.overlay} onClick={handleOverlayClick}>
            <div className={styles.ventanaModal}>
                    <button onClick={cerrarCarrito} className={styles.cerrarCarrito}>X</button>
                <h2>Carrito de Compras</h2>
                <ul>
                    {carrito.map((item:ProductoEnCarrito,index)=>(
                        <li key={index}>{item.nombre} - S/. {item.precio} x {item.cantidad} unidades
                         <button onClick={() => eliminarDelCarrito(item.id)}>    T</button>
                        </li>
                    ))}
                </ul>
                       <hr />
                    <p>Total: S/. <span id="carrito-total">{calcularTotal().toFixed(2)}</span></p>
            <Link href="/paymentPage/PaymentPage">
            <button className={styles.botonTramitar}>Tramitar Pedido</button>
            </Link>        
                    
            
            </div>
        </div>
    )
}

export default Cart;


