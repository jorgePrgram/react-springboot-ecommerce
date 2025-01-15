import { useState } from 'react';
import styles from './Boton.module.scss';
import { Producto } from '@/interfaces/types';
import agregarAlCarrito from './Cart';

const Boton = ({ producto, agregarAlCarrito}: {producto: Producto, 
    agregarAlCarrito:(producto: Producto, cantidad: number) => void}) =>{

    const [cantidad, setCantidad] = useState(1);

    const btnMinus = () =>{
        setCantidad(prevCantidad => (prevCantidad > 1 ? prevCantidad - 1 : prevCantidad));
        
    }
    const btnMas = () =>{
        setCantidad(prevCantidad => (prevCantidad < producto.stock ? prevCantidad + 1 : prevCantidad));
    }



    return(
        <div>
             <div className={styles.containerCantidad}>
                   <button className={styles.botonMinus} onClick={btnMinus}>-</button>
                    <input 
                        className={styles.inputNumber} 
                        type="text" 
                        value={cantidad}
                        readOnly
                        id={`cantidadInput-${producto.id}`} // id único basado en el id del producto
                        name={`cantidad-${producto.id}`}
                    />
                  <button className={styles.botonMas} onClick={btnMas}>+</button>
               </div>
               <button className={styles.boton} onClick={() => agregarAlCarrito(producto, cantidad)} >
                 <img src="assets/images/icon-cart-white.svg" alt="" width={40} height={40} />
                 <p>AÑADIR AL CARRITO</p>
                </button>



        </div>
       
    )



}



export default Boton;


