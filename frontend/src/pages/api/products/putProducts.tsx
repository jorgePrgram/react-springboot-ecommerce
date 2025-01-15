import axios from "axios";
import { Producto } from "@/interfaces/types";
import { getProductById } from "./getProducts";

const API_BASE_URL = 'http://localhost:8080/api';

export const putProducts = async (productoId: number, cantidadComprada: number): Promise<Producto> => {
    try {
        // Realizas primero una solicitud GET para obtener el producto actual
        
        const producto=await getProductById(productoId);


        // Restar la cantidad comprada al stock actual
        const nuevoStock = producto.stock - cantidadComprada;

        // Hacer la solicitud PUT para actualizar el stock
        const responsePut = await axios.put<Producto>(`${API_BASE_URL}/productos/${productoId}`, 
            { ...producto, stock: nuevoStock },
            {
                headers: {
                    'Content-Type': 'application/json',
                },
            }
        );

        return responsePut.data;
    } catch (error) {
        if (axios.isAxiosError(error)) {
            throw new Error(error.message);
        } else {
            throw new Error('Error desconocido.');
        }
    }
};