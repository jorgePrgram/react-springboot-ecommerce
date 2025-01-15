import styles from './reset-password.module.scss';

const ResetPassword = () => {
  return (
    <div className={styles.resetContainer}>
      <h2>¿OLVIDÓ SU CONTRASEÑA?</h2>
      <p className={styles.instructionText}>
        Por favor, introduzca la dirección de correo electrónico que utilizó para registrarse. 
        Recibirá un enlace temporal para restablecer su contraseña.
      </p>
      <form className={styles.resetForm}>
        <label htmlFor="email">Dirección de correo electrónico</label>
        <input type="email" id="email" className={styles.inputField} />
        <button type="submit" className={styles.resetButton}>
          ENVIAR ENLACE DE RESTABLECIMIENTO DE CONTRASEÑA
        </button>
      </form>
      <a href="/" className={styles.backLink}> &lt; Volver a inicio</a>
    </div>
  );
};

export default ResetPassword;