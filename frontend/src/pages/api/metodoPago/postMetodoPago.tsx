import axios from "axios";


export const postMetodoPago = async (
    token: string,
    metodoPagoData: { 
    tipo: string; 
    cvv: string; 
    fechaVencimiento: string; 
    cuentaBancaria: string;

}) => {
    
    if (!token) {
        throw new Error('Token no disponible. El usuario no está autenticado.');
    }
  
    try {
        const response = await axios.post('http://localhost:8080/api/metodopago', metodoPagoData, {
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            }
        });

        return response.data;

    }  catch (error) {
        if (axios.isAxiosError(error)) {
            console.error('Error:', error.response?.data || error.message);
            throw new Error('Error al crear el método de pago');
        } else {
            console.error('Error:', error);
            throw new Error('Error desconocido');
        }
    }
};