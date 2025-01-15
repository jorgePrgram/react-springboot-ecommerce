import React from "react";
import { CarritoItem, ProductoEnCarrito } from "@/interfaces/types";

import styles from './Boleta.module.scss';
   

interface BoletaProps{
    carrito:CarritoItem[];
    total:number;
}


const Boleta:React.FC<BoletaProps> = ( { carrito, total }) => {
        
    
        return (
            <div className={styles.boletaContainer}>
            <div className={styles.boletaHeader}>
              <h2 className={styles.boletaTitle}>Boleta de Compra</h2>
            </div>
            <div className={styles.boletaBody}>
              <ul className={styles.boletaItems}>
                {carrito.map((item, index) => (
                  <li className={styles.boletaItem} key={index}>
                    <span className={styles.productoId}>Producto ID: {item.id}</span>
                    <span className={styles.productoNombre}>{item.nombre}</span>
                    <span className={styles.precio}>Precio: S/. {(item.precio ?? 0).toFixed(2)}</span>
                    <span className={styles.cantidad}>Cantidad: {item.cantidad}</span>
                  </li>
                ))}
              </ul>
            </div>
            <div className={styles.boletaFooter}>
              <h3 className={styles.total}>Total: S/. {total.toFixed(2)}</h3>
            </div>
          </div>
  );
};
    
    export default Boleta;