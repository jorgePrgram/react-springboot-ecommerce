import React, { useState, useEffect, useContext } from 'react';
import { getProducts } from '../pages/api/products/getProducts'; // Ajusta la ruta según tu estructura
import styles from './ProductGrid.module.scss';
import { Producto } from '@/interfaces/types';
import Cart from '@/components/Cart';
import Boton from '@/components/Boton';
import { useCart } from '@/components/CartContext';

interface ProductGridProps {
    productos: Producto[];
}

const ProductGrid: React.FC<ProductGridProps> = ({ productos }: { productos: Producto[]}) => {  
    const {  agregarAlCarrito } = useCart();
  
    if (productos.length === 0) return <p>No products available</p>;

    return (
        <div>
        <div className={styles.containerItems} id="productos-container">
    {productos.map(producto => (
        <div key={producto.id} className={styles.productItem}>
            <figure >
            <img src={producto.imagen} alt={producto.nombre} className={styles.itemImages} />
            </figure>
            <div className={styles.infoProduct}>
                <h2 className={styles.price}>{producto.nombre}</h2>
                 <p>Precio: S/. {producto.precio}</p>
                  <p>Stock: {producto.stock}</p>
           
            
                    <Boton
                        producto={producto}
                        agregarAlCarrito={agregarAlCarrito}
                    />
                
                    </div>
                 </div>
    ))}
</div>
            <Cart />
</div>
    );
};

export default ProductGrid;