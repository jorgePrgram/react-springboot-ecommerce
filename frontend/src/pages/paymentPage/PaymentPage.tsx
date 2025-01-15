import { useEffect, useState } from 'react';
import { useCart } from '../../components/CartContext'; // Asegúrate de que este hook exista
import { postMetodoPago } from '../api/metodoPago/postMetodoPago';
import styles from './PaymentPage.module.scss';
import Boleta from '@/sections/Payment/Boleta';
import { crearPedidoConDetalles } from '../api/detailsPedido/crearPedidoConDetalles';
import { postPedido } from '../api/pedido/postPedido';
import { ProductoEnCarrito, Usuario } from '@/interfaces/types';
import { CarritoItem, convertirADetallePedido, DetallePedido } from '@/interfaces/types';
import { useAuth } from '@/context/AuthProvider';
const PaymentPage = () => {
    const { carrito, calcularTotal, vaciarCarrito } = useCart();
    const [metodoPago, setMetodoPago] = useState('');
    const [cvv, setCvv] = useState('');
    const [fechaVencimiento, setFechaVencimiento] = useState('');
    const [cuentaBancaria, setCuentaBancaria] = useState('');
    const [mensaje, setMensaje] = useState('');
    const [boletaData, setBoletaData] = useState<{ carrito: CarritoItem[], total: number } | null>(null);
    const { user, token } = useAuth();




    useEffect(() => {
        if (!user) {
            setMensaje('Usuario no autenticado o token inválido');
        }
    }, [user]);

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        if(carrito.length===0){
            setMensaje('El carrito esta vacio');
            return;
        }
        if (!user) {
            setMensaje('Usuario no autenticado. Verifica tus credenciales');
            return;
        }

        const total = calcularTotal();
        const usuarioId = user.id;
        
      
        for(const item of carrito){
            if(!item.id || !item.cantidad){
                setMensaje(`Item inválido: debe tener cantidad e id. Item: ${JSON.stringify(item)}`);
                return; 
            }
        }
       
      

        let metodoPagoData = {
            tipo: metodoPago === '1' ? 'Tarjeta de Crédito' : 'Tarjeta de Débito',
            cvv: cvv,
            fechaVencimiento: fechaVencimiento,
            cuentaBancaria

        };

        try {
                // Verificar que el token no sea null antes de llamar a la función
                if (token === null) {
                    setMensaje('Token no disponible. El usuario no está autenticado.');
                    return;
                }
        
            // 1. Crear método de pago
            const metodoPagoResponse = await postMetodoPago(token, metodoPagoData);
           const metodoPagoId=metodoPagoResponse.id;
           console.log('meotodo de pago id:',metodoPagoId);
           
            // 2. Crear el pedido y obtener el ID
            const pedidoCreadoResponse = await postPedido(total, usuarioId, metodoPagoId,token);
            console.log('Pedido:', pedidoCreadoResponse);
            //const pedidoCreadoResponse= await postPedido(300,4,6);
            
       

            // 3. Crear un array de detalles de pedido a partir del carrito
            const detallePedidos: DetallePedido[] = carrito.map(item => ({
                producto: { id: item.id }, // Ajusta según lo que espera tu API
                cantidad: item.cantidad,
                pedido:{ id : pedidoCreadoResponse.id }
            }));
            console.log('Detallepedido:',detallePedidos);

            const CarritoItems: CarritoItem[]=detallePedidos.map(convertirADetallePedido);
    
            await crearPedidoConDetalles(pedidoCreadoResponse.id, CarritoItems,token);
           
            // 4. Generar la boleta antes de vaciar el carrito
            setBoletaData({
                carrito: [...carrito],  // Clonamos el carrito actual
                total
            });
            vaciarCarrito();
            setMensaje('Pedido realizado con éxito');
    
            
        } catch (error) {
            console.error('Error al realizar el pedido:', error);
            setMensaje('Hubo un problema al realizar el pedido. Inténtalo de nuevo.');
        }
    }

    return (
        <div className={styles.paymentContainer}>
            <h2>Selecciona tu método de pago</h2>
            <form onSubmit={handleSubmit}>
                <div className={styles.formField}>
                    <label htmlFor="metodoPago">Método de Pago</label>
                    <select
                        id="metodoPago"
                        value={metodoPago}
                        onChange={(e) => setMetodoPago(e.target.value)}
                        required
                    >
                        <option value="">Selecciona un método</option>
                        <option value="1">Tarjeta de Crédito</option>
                        <option value="2">Tarjeta de Débito</option>
                    </select>
                </div>
                <div className={styles.formField}>
                    <label htmlFor="cvv">CVV</label>
                    <input
                        type="text"
                        id="cvv"
                        value={cvv}
                        onChange={(e) => setCvv(e.target.value)}
                        required
                    />
                </div>
                <div className={styles.formField}>
                    <label htmlFor="fechaVencimiento">Fecha de Vencimiento</label>
                    <input
                        type="date"
                        id="fechaVencimiento"
                        value={fechaVencimiento}
                        onChange={(e) => setFechaVencimiento(e.target.value)}
                        required
                    />
                </div>
                <div className={styles.formField}>
                    <label htmlFor="cuentaBancaria">Número de Cuenta Bancaria</label>
                    <input
                        type="text"
                        id="cuentaBancaria"
                        value={cuentaBancaria}
                        onChange={(e) => setCuentaBancaria(e.target.value)}
                        required
                    />
                </div>
                <button type="submit">Realizar Compra</button>

                {/* PRUEBAS============================================= */}
                <h2>Carrito</h2>
                <ul>
                    {carrito.map((item, index) => (
                        <li key={index}>
                            Producto: {item.nombre} - Cantidad: {item.cantidad}
                        </li>
                    ))}
                </ul>
                <h2>Monto Total: {calcularTotal()}</h2>
                
                {/* FIN DEPRUEBAS HTML */}
            </form>
            {mensaje && <p>{mensaje}</p>}

                {/* Mostrar el componente Boleta solo si se ha generado */}
                {boletaData && <Boleta carrito={boletaData.carrito} total={boletaData.total} />}

        </div>
    );
};

export default PaymentPage;