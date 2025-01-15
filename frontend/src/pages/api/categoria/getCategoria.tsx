import axios from "axios"



export const getCategoriaById = async (categoriaId: number)=>{

    try{
        const response =await axios.get(`http://localhost:8080/api/categoria/${categoriaId}`);
        return response.data;
    } catch(error){
        console.error(`Error al obtener la categoría con ID ${categoriaId}:`, error);
    throw new Error('No se pudo obtener la categoría');
    }
}