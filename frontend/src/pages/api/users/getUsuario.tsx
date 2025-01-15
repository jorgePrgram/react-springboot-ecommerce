import axios from "axios";
import { Usuario } from "@/interfaces/types"; 

export const getUsuarioById = async (usuarioId: number, token:string): Promise<Usuario | null> => {
    try {
        const response = await axios.get<Usuario>(`http://localhost:8080/api/usuarios/${usuarioId}`,{
            headers: {
                Authorization: `Bearer ${token}`, // Incluye el token en el encabezado
            },
    });
        return response.data; // Retorna la data del usuario
    } catch (error) {
        console.error(`Error al obtener el usuario con ID ${usuarioId}:`, error);
        throw new Error('No se pudo obtener el usuario'); // Lanza un error si ocurre
    }
};