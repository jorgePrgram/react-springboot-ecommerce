import Link from "next/link";
import styles from './Header.module.scss';
import CategoryModal from "@/components/CategoryModal";
import { useState } from "react";
import { useCart } from "@/components/CartContext";
import { useRouter } from "next/router";

interface NavLinksProps {
    isMenuVisible: boolean;
}

const NavLinks = ({isMenuVisible}:NavLinksProps)=>{

  const[isModalOpen, setIsModalOpen]= useState(false);
  const {setCategoryId, setSearchQuery}=useCart();
  const router=useRouter();



const openModal = () => {
  setIsModalOpen(true);
};

// Función para cerrar el modal
const closeModal = () => {
  setIsModalOpen(false);
};

const handleInicioClick = () => {
  

  setCategoryId(null);
  setSearchQuery("");

  if (router.pathname === '/') {
    router.reload(); // Recarga la página si ya estás en la ruta de inicio
  } else {
    router.push('/'); // Navega a la página de inicio si estás en otra ruta
  }
};

const handleSelectedCategory=(categoryId: number)=>{
  setCategoryId(categoryId);
  console.log(`Categoría seleccionada: ${categoryId}`);
  
}


  const categories=['Tarjetas Graficas', 'Almacenamiento', 'Placa Madre', 'Procesadores', 'Fuentes de Poder',
        'Memoria Ram', 'Case', 'Cooler', 'Estabilizadores', 'Parlantes'];

  const mappedCategories= categories.map((nombre, index)=>({
    id: index + 1,
    nombre,
  }));

    const getNavClassName=()=>{
        return `${styles.mainNav} ${isMenuVisible ? styles.show : ''}`;
    }
    return(
      <div>
<nav className={getNavClassName()} id="main-nav">
  <ul className={styles.mainMenu}>
    <li className={styles.mainMenuItem}>
    <span className={styles.mainMenuLink} onClick={handleInicioClick}>
              Inicio
            </span>
    </li>
    <li className={styles.mainMenuItem}>
      <span className={styles.mainMenuLink} onClick={openModal}>
        Categorias
      </span>
    </li>
    <li className={styles.mainMenuItem}>
      <Link href="/legal/contacto" className={styles.mainMenuLink}>
        Contacto
      </Link>
    </li>
    <li className={styles.mainMenuItem}>
      <Link href="/legal/terms-page" className={styles.mainMenuLink}>
        Términos
      </Link>
    </li>
  </ul>
</nav>
 <CategoryModal isOpen={isModalOpen} onClose={closeModal} categorias={mappedCategories}
        onSelectCategory={handleSelectedCategory}
 />
      </div>

)
}



export default NavLinks;


