import { NextApiRequest, NextApiResponse } from 'next';
import axios from 'axios';

export default async function registerHandler(req: NextApiRequest, res: NextApiResponse) {
  if (req.method === 'POST') {
    const userData = req.body;

    // Crear el objeto con la estructura que espera el backend
    const requestBody = {
      nombreUsuario: userData.username,
      correoElectronico: userData.email,
      contrasenia: userData.password,
      cliente: {
        nombre: userData.firstName,
        apellido: userData.lastName,
        genero: userData.gender,
        fechaNacimiento: userData.birthDate,
        telefono: userData.phone
      },
      role:'user'
    };

    try {
      // Solicitud al backend de Spring Boot
      const response = await axios.post('http://localhost:8080/api/usuarios', requestBody, {
        headers: {
          'Content-Type': 'application/json'
        }
      });
      res.status(201).json({ message: 'Registro exitoso' });

    
    } catch (error) {
      console.error('Error en el registro:', error);
      res.status(500).json({ message: 'Error en el registro', error });
    }
  } else {
    res.status(405).json({ message: 'Método no permitido' });
  }
}