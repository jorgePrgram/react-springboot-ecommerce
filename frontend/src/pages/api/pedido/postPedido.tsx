
import axios from "axios";
import { PedidoCreado } from "@/interfaces/types";



export const postPedido = async (
    montoTotal: number,
    usuarioId:number, 
    metodoPagoId: number,
    token:string
): Promise<PedidoCreado> => {

    const pedido = {
        montoTotal,
     
        usuario:{
            id: usuarioId
        },
        metodoPago:{
            id:metodoPagoId
        } 
    };

    console.log('Pedido:', pedido);

    try {
      

        const response = await axios.post<PedidoCreado>('http://localhost:8080/api/pedidos', pedido,{
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            }
        });
       return response.data;

    } catch (error) {
        if(axios.isAxiosError(error)){
            const errorMessage=error.response?.data?.message || error.message;
            console.error(`Error al crear el pedido: ${errorMessage}`);
            throw new Error(`Error al crear el pedido: ${errorMessage}`);
        }else{
            console.error('Error desconocido:', error);
            throw new Error('Error desconocido al crear el pedido');
        }
    }
};