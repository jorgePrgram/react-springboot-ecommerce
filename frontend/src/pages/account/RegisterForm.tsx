import { useState } from "react";
import styles from './Register.module.scss';


const RegisterForm = () => {
    const [formData, setFormData] = useState({
      username: '',
      email: '',
      password: '',
      confirmPassword: '',
      firstName: '',
      lastName: '',
      gender: '',
      birthDate: '',
      phone: ''
    });
    
    const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
      setFormData({
        ...formData,
        [e.target.name]: e.target.value
      });
    };
    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
    
        // Validar si las contraseñas coinciden
        if (formData.password !== formData.confirmPassword) {
            alert('Las contraseñas no coinciden');
          return;  // Evita que el formulario se envíe si hay un error
        }
    
      

      try {
        const response = await fetch('/api/users/postUsuario', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(formData)
        });
  
        if (!response.ok) {
          throw new Error('Error en el registro');
        }
  
        alert('Registro exitoso');
      } catch (error) {
        console.error('Error:', error);
      }
    };
  
    return (
      <div className={styles.formContainer}>
        <h2 className={styles.formTitle}>Registro de Usuario</h2>
        <form onSubmit={handleSubmit}>
          <div className={styles.formField}>
            <label htmlFor="username">Nombre de usuario</label>
            <input id="username" name="username" placeholder="Nombre de usuario" value={formData.username} onChange={handleChange} required />
          </div>
  
          <div className={styles.formField}>
            <label htmlFor="email">Correo electrónico</label>
            <input id="email" name="email" type="email" placeholder="Correo electrónico" value={formData.email} onChange={handleChange} required />
          </div>
  
          <div className={styles.formField}>
            <label htmlFor="password">Contraseña</label>
            <input id="password" name="password" type="password" placeholder="Contraseña" value={formData.password} onChange={handleChange} required />
          </div>
          <div className={styles.formField}>
          <label htmlFor="confirmPassword">Confirmar Contraseña</label>
          <input id="confirmPassword" name="confirmPassword" type="password" placeholder="Confirmar contraseña" value={formData.confirmPassword} onChange={handleChange} required />
        </div>
  
          <div className={styles.formField}>
            <label htmlFor="firstName">Nombre</label>
            <input id="firstName" name="firstName" placeholder="Nombre" value={formData.firstName} onChange={handleChange} required />
          </div>
  
          <div className={styles.formField}>
            <label htmlFor="lastName">Apellido</label>
            <input id="lastName" name="lastName" placeholder="Apellido" value={formData.lastName} onChange={handleChange} required />
          </div>
  
          <div className={styles.formField}>
            <label htmlFor="gender">Género</label>
            <select id="gender" name="gender" value={formData.gender} onChange={handleChange} required>
              <option value="">Selecciona tu género</option>
              <option value="masculino">Masculino</option>
              <option value="femenino">Femenino</option>
            </select>
          </div>
  
          <div className={styles.formField}>
            <label htmlFor="birthDate">Fecha de nacimiento</label>
            <input id="birthDate" name="birthDate" type="date" value={formData.birthDate} onChange={handleChange} required />
          </div>
  
          <div className={styles.formField}>
            <label htmlFor="phone">Teléfono</label>
            <input id="phone" name="phone" placeholder="Teléfono" value={formData.phone} onChange={handleChange} required />
          </div>
  
          <button className={styles.submitButton} type="submit">Registrarse</button>
        </form>
      </div>
    );
  };
  
  export default RegisterForm;