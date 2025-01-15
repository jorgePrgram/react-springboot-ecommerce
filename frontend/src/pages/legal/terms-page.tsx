import React from 'react';
import styles from './TermsPage.module.scss'; // Importa un archivo de estilos para centrar el contenido

const TermsPage = () => {
    return (
        <div className={styles.termsContainer}>
            <h1>Términos y Condiciones</h1>

            {/* Sección de Restricciones */}
            <div className={styles.section}>
                <h2>Restricciones</h2>
                <p>
                    La empresa maneja sus políticas y restricciones de ventas de acuerdo a las limitaciones en las que se encuentra el mercado de cómputo; esto condiciona a la empresa a realizar la venta de ciertos productos con restricciones, los cuales están mencionados en la página web para el conocimiento de las personas que la visitan. De manera detallada, nuestros asesores informan al momento de contactarlos para concretar la venta, cumpliendo con informar de tales circunstancias de forma clara e inequívoca al consumidor.
                </p>
            </div>

            {/* Sección de Horario de Garantías */}
            <div className={styles.section}>
                <h2>Horario de Garantías</h2>
                <div>
                    <h3>Lunes a Viernes</h3>
                    <p>10:00 am - 4:00 pm</p>
                    <h4>Refrigerio</h4>
                    <p>1:00 pm - 2:00 pm</p>
                </div>
                <div>
                    <h3>Sábados</h3>
                    <p>10:00 am - 1:00 pm</p>
                </div>
            </div>
        </div>
    );
};

export default TermsPage;