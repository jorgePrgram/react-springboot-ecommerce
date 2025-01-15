
import { useAuth } from '@/context/AuthProvider';
import { useState, useEffect } from 'react';
import obtenerHistorialPedidos from '../api/pedido/obtenerHistorialPedidos';
import styles from './HistorialCompras.module.scss';

const HistorialCompras = ()=>{


    const { user, token } = useAuth();
    const [historial, setHistorial]= useState<any>(null);
    const [cargando,setCargando]=useState(true);
    const[error, setError]=useState<string | null>(null);

    useEffect(() => {
        const fetchHistorial = async () => {
            if (user && user.id && token) { // Verifica que user y user.id existan
                try {
                    const pedidos = await obtenerHistorialPedidos(user.id, token);
                    setHistorial(pedidos);
                } catch (error) {
                    console.error("Error en fetchHistorial:", error);
                    setError('No se pudo obtener el historial de pedidos');
                } finally {
                    setCargando(false);
                }
            }
        };

        fetchHistorial();
    }, [user, token]);




    if (cargando) {
        return <p>Cargando...</p>;
    }

    if (error) {
        return <p>{error}</p>;
    }

    return (
        <div className={styles.historialCompras}>
            <h1>Historial de Compras de {historial.nombreUsuario}</h1>
            <ul>
                {historial.pedidos.map((pedido: any) => (
                    <li key={pedido.id} className={styles.pedido}>
                        <p>ID del Pedido: {pedido.id}</p>
                        <p>Monto Total: {pedido.montoTotal}</p>
                        <p>Detalles:</p>
                        <ul className={styles.detalles}>
                            {pedido.detalles.map((detalle: any) => (
                                <li key={detalle.nombreProducto} className={styles.detalleItem}>
                                    <span>{detalle.cantidad} x {detalle.nombreProducto} </span>
                                    <span>  -- S/. {detalle.precioUnitario} cada uno</span>
                                </li>
                            ))}
                        </ul>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default HistorialCompras;