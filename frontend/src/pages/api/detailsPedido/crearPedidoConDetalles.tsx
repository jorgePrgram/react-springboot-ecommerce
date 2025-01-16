
import { postDetallePedido } from "./postDetailPedido";
import { putProducts } from "../products/putProducts";
import { CarritoItem } from "@/interfaces/types";
import { DetallePedido } from "@/interfaces/types";



export const crearPedidoConDetalles=async(
    pedidoId: number,
    carrito: CarritoItem[],
    token:string

): Promise<void> =>{
    if (!token) {
        throw new Error('Token no encontrado. El usuario debe iniciar sesión.');
    }
   
        try{
            const detallePedidos: DetallePedido[]= carrito.map(item=>({
                producto: { id: item.id },
                cantidad: item.cantidad,
                pedido: {id:pedidoId},
            }))
    
            for (const detallePedido of detallePedidos) {
                console.log('Item antes de enviar:', detallePedido);
              
                await postDetallePedido(pedidoId, detallePedido, token);
    
                // Actualizar el stock del producto
                await putProducts(detallePedido.producto.id, detallePedido.cantidad); 
            }
            console.log('Pedido y detalles creados con exito');
        } catch(error){
            console.error('Error', error);
            throw error;
        }

}

