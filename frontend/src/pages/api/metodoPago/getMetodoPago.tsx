/*

import axios from "axios";
import { useAuth } from "@/context/AuthProvider";

export const getMetodoPago = async (metodoPagoId: number) => {
    
    
    const{ token }=useAuth();


    try {
        const response = await axios.get(`http://localhost:8080/api/metodopago/${metodoPagoId}`, {
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            }
        });

       return response.data.id;
    } catch (error) {
        console.error('Error:', error);
        throw error;
    }
};

*/