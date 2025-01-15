import { useContext, useState, createContext, useEffect } from "react";
import { apiLogin } from "@/pages/api/users/authService";
import { jwtDecode } from 'jwt-decode';


export interface User {
    id: number;
    nombreUsuario: string;
    role: string;
}

interface AuthContextType {
    user: User | null;
    token: string | null;
    login: (nombreUsuario: string, contrasenia: string) => Promise<void>;
    logout: () => void;
    isAuthenticate: boolean;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const AuthProvider: React.FC<{ children: React.ReactNode }> = ({ children }) => {
    const [user, setUser] = useState<User | null>(null);
    const [token, setToken] = useState<string | null>(null);
    const [isAuthenticate, setIsAuthenticate] = useState<boolean>(false);

    useEffect(() => {
        const storedToken = localStorage.getItem('token');
        if (storedToken && storedToken.split('.').length === 3) {
            const decodedUser = obtenerUsuarioDelToken(storedToken); // Obtén el usuario decodificado
            if (decodedUser) {
                setUser(decodedUser);
                setIsAuthenticate(true);
            }
        } else {
            console.error("Invalid token:", storedToken);
            localStorage.removeItem('token');
        }
    }, []);

    const login = async (nombreUsuario: string, contrasenia: string) => {
        try {
            const response = await apiLogin(nombreUsuario, contrasenia);
            const { token } = response;

            if (token) {
                setToken(token);
                localStorage.setItem('token', token);
                
                const decodedUser = obtenerUsuarioDelToken(token); // Obtén el usuario directamente del token
                setUser(decodedUser);
                setIsAuthenticate(true);
            } else {
                throw new Error("Token inválido");
            }
        } catch (error: any) {
            console.error("Error en login:", error.message);
            throw new Error(error.message || "Error desconocido al iniciar sesión");
        }
    };

    const logout = () => {
        setUser(null);
        setToken(null);
        localStorage.removeItem('token');
        setIsAuthenticate(false);
    };

    return (
        <AuthContext.Provider value={{ user, login, token, logout, isAuthenticate }}>
            {children}
        </AuthContext.Provider>
    );
};

// Función para obtener el usuario a partir del token
export const obtenerUsuarioDelToken = (token: string): User | null => {
    if (!token) {
        console.error("No se proporcionó token");
        return null; // Retorna null si no hay token
    }

    try {
        const decodedToken = jwtDecode<{ role: string; userId: string; sub: string; }>(token); // Cambiar el tipo aquí si es necesario

        return {
            id: parseInt(decodedToken.userId), 
            nombreUsuario: decodedToken.sub, // Asignar el sub como nombre de usuario
            role: decodedToken.role, // Asignar el rol
        };
    } catch (error) {
        console.error("Error al decodificar el token:", error);
        return null; // Retorna null si hay un error en la decodificación
    }
};

export const useAuth = () => {
    const context = useContext(AuthContext);
    if (!context) {
        throw new Error('useAuth must be used within an AuthProvider');
    }
    return context;
};