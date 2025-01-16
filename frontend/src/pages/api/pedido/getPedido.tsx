import axios from 'axios';

export const getPedidoById= async(pedidoId : number, token:  string)=>{
    try{
        const response=await axios.get(`http://localhost:8080/api/pedidos/${pedidoId}`,{
            headers:{
                'Authorization': `Bearer ${token}`, 
                'Content-Type': 'application/json',
            }
        })
        console.log('Informacion del pedido:', response.data);
        return response.data;
    }
    catch(error){
        console.error('Error al obtener el pedido:', error);
        throw error; 
    }

}