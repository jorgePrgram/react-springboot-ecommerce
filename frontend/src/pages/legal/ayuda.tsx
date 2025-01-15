import { useState } from 'react';
import styles from './Ayuda.module.scss';

export default function Ayuda() {
  // Estado para manejar el acordeón
  const [openIndex, setOpenIndex] = useState<number | null>(null);

  const toggleFAQ = (index: number) => {
    setOpenIndex(openIndex === index ? null : index);
  };

  // Preguntas frecuentes (FAQs) de ejemplo
  const faqs = [
    {
      pregunta: '¿Cómo puedo realizar un pedido?',
      respuesta: 'Para realizar un pedido, simplemente añade los productos a tu carrito y sigue los pasos para completar la compra.'
    },
    {
      pregunta: '¿Cuáles son los métodos de pago aceptados?',
      respuesta: 'Aceptamos tarjetas de crédito, PayPal y transferencias bancarias.'
    },
    {
      pregunta: '¿Puedo rastrear mi pedido?',
      respuesta: 'Sí, puedes rastrear tu pedido usando el número de seguimiento proporcionado en el correo de confirmación.'
    }
  ];

  return (
    <div className={styles.ayuda}>
      <h1>Centro de Ayuda</h1>
      <div className={styles.faqs}>
        {faqs.map((faq, index) => (
          <div key={index} className={styles.faqItem}>
            <div
              className={styles.faqQuestion}
              onClick={() => toggleFAQ(index)}
            >
              {faq.pregunta}
            </div>
            {openIndex === index && (
              <div className={styles.faqAnswer}>
                {faq.respuesta}
              </div>
            )}
          </div>
        ))}
      </div>
    </div>
  );
}