import { DetallePedido } from "@/interfaces/types";
import axios from "axios";


const API_BASE_URL = 'http://localhost:8080/api';

export const getDetallePedido = async( detallePedidoId: number, token: string ): Promise<DetallePedido>=>{
try{
    const response = await axios.get<DetallePedido>(`${API_BASE_URL}/detallepedido/${detallePedidoId}`, {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        });
        return response.data;
}
catch(error){
    console.error("Error en getDetallePedido:", error);
    throw new Error(error instanceof Error ? error.message : 'Ocurrió un error desconocido al obtener el detalle del pedido');
}
}