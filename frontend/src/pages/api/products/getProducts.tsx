
import axios from "axios";
import { Producto } from "@/interfaces/types";

const API_BASE_URL = 'http://localhost:8080/api';

export const getProducts = async () => {
    try {
        const response = await axios.get<Producto[]>(`${API_BASE_URL}/productos`);
        return response.data;
      
    } catch (error) {
        if (axios.isAxiosError(error)) {
            throw new Error(error.message);
        } else {
            throw new Error('Unknown error occurred');
        }
    }
};

export const getProductById = async (id: number): Promise<Producto> => {
    try {
        const response = await axios.get<Producto>(`${API_BASE_URL}/productos/${id}`);
        return response.data;
    } catch (error) {
        if (axios.isAxiosError(error)) {
            if (error.response) {
                // Manejar errores de respuesta específicos de la API
                throw new Error(`Error ${error.response.status}: ${error.response.data}`);
            } else if (error.request) {
                // Error con la solicitud pero sin respuesta de la API
                throw new Error('No se recibió respuesta del servidor.');
            }
        }
        // Manejo de errores desconocidos
        throw new Error('Ocurrió un error inesperado al obtener el producto');
    }
};

export const getProductByName = async(nombre: string): Promise<Producto[]>=>{
    try{
        const response = await axios.get<Producto[]>(`${API_BASE_URL}/productos/buscar`,{
            params: { nombre }
        });
    return response.data;
        } catch(error){
            if(axios.isAxiosError(error)){
                throw new Error(error.message);
            }else{
                throw new Error('Unknown error occurred');
            }
        }
}