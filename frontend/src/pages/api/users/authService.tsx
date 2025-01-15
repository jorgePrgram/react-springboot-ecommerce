import axios from "axios";

const API_URL='http://localhost:8080/api/usuarios/login'



interface LoginResponse{
    id: string;
    token:string;
    role:string;
    nombreUsuario:string;
}

export const apiLogin = async(nombreUsuario: string, contrasenia: string):Promise<LoginResponse>=>{
    try{
        const response=await axios.post<LoginResponse>(API_URL, {
            nombreUsuario,
            contrasenia,
        });
        console.log("Respuesta del backend:", response.data);
        return response.data;
       
    } catch(error: any){
        throw new Error(error.response?.data?.message || 'Error al iniciar sesión')
    }
};