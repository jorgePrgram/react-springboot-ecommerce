import axios from "axios";


export const postDetallePedido = async (pedidoId: number, item: any,  token:string) => {
    try {
 
     
      if (!item || !item.cantidad || !item.producto?.id) {
        throw new Error('Item inválido: debe tener cantidad e id.');
    }
        const detallePedido = {
          
          producto:{
            id:item.producto.id,
          },
          cantidad: item.cantidad,
          pedido:{
            id:pedidoId
          }
        };

        const response = await axios.post('http://localhost:8080/api/detallepedido',detallePedido, {
          headers: {
            'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
        }
    });

      return response.data;
     

    } catch (error) {
      if(axios.isAxiosError(error)){
        const errorMessage=error.response?.data?.message || error.message;
        console.error('Error al crear el detalle del pedido:', errorMessage);
        throw new Error(`Error al crear el detalle del pedido: ${errorMessage}`);
      }else{
        console.error('Error desconocido:', error);
        throw new Error('Error desconocido al crear el detalle del pedido');
      }
    }
};