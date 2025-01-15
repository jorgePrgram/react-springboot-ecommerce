import React, { useState, useEffect } from 'react';
import styles from './LoginPage.module.scss';  
import Link from 'next/link';
import { useAuth } from '@/context/AuthProvider';
import { useRouter } from 'next/router';


const LoginPage = () => {
  
  const [nombreUsuario, setNombreUsuario] = useState('');
  const [contrasenia, setContrasenia] = useState('');
  const [showPassword, setShowPassword] = useState(false);
  const [error, setError]=useState('');
  const { login,user } = useAuth();
  const router= useRouter();


  const handleLogin = async (e: React.FormEvent) => {
    e.preventDefault();
    setError('');
    console.log('Datos enviados a apiLogin:', { nombreUsuario, contrasenia });

  try{

    await login(nombreUsuario, contrasenia);
    router.push('/');
    console.log('Login exitoso');
  
  } catch(error: any){
    setError(error.message ||'Hubo un problema al iniciar sesion');
  }
  };

  useEffect(() => {
    if (user) {
        console.log('Usuario decodificado:', user); // Log del usuario decodificado
    }
}, [user])
  

  const togglePasswordVisibility = () => {
    setShowPassword(!showPassword);
  };


  return (
    <div className={styles.loginContainer}>
      <h1 className={styles.loginTitle}>Iniciar Sesión</h1>
      
      <form onSubmit={handleLogin} className={styles.loginForm}>
        <div className={styles.formGroup}>
          <label htmlFor="username">Usuario</label>
          <input
            type="text"
            id="username"
            value={nombreUsuario}
            onChange={(e) => setNombreUsuario(e.target.value)}
            required
            placeholder="Introduce tu usuario"
          />
        </div>

        <div className={styles.formGroup}>
          <label htmlFor="password">Contraseña</label>
          <div className={styles.passwordContainer}>
          <input
            type={showPassword ? "text" : "password"}  
            id="password"
            value={contrasenia}
            onChange={(e) => setContrasenia(e.target.value)}
            required
            placeholder="Introduce tu contraseña"
          />
           <button
              type="button"
              onClick={togglePasswordVisibility}
              className={styles.togglePasswordButton}
            >
              {showPassword ? "OCULTAR" : "MOSTRAR"} {/* Texto dinámico */}
            </button>
          </div>
      
        </div>
        {error && <p className={styles.error}>{error}</p>} 
        <button type="submit" className={styles.submitButton}>Iniciar Sesión</button>
      </form>

    <div className={styles.olvido}>
      <Link href="/account/reset-password">
           <p>¿Olvidó su contraseña?</p>
      </Link>
    </div>


      <div className={styles.notRegistered}>
        <Link href="/account/RegisterForm">
          <button className={styles.registerButton}>¿No tienes una cuenta? Registrarse</button>
        </Link>
      </div>
    </div>
  );
};

export default LoginPage;