import Link from "next/link";
import styles from './Header.module.scss';
import Image from "next/image";
import { useCart } from "@/components/CartContext";
import { useEffect, useState } from "react";
import { obtenerUsuarioDelToken, useAuth } from "@/context/AuthProvider";


const UserActions = ()=>{

  const { abrirCarrito,cantidadTotalProductos } = useCart();
  const { user,logout } = useAuth();
  const [isDropdownOpen, setIsDrownOpen] = useState(false);




  const handleLogout = ()=>{
    logout();
    setIsDrownOpen(false);
    
  }


  const toggleDropdown=()=>{
    setIsDrownOpen((prev=> !prev));
  }

  return(
<div className={styles.navbarItemContainer}>
{user ? (
                <div className={styles.navbarUser} onClick={toggleDropdown} >
                    <Image
                        src="/assets/images/user-interface.svg"
                        className={styles.navbarIcon}
                        alt="User"
                        width={50} // Especifica el ancho
                        height={50} // Especifica el alto
                    />
                    <span className={styles.navbarSpan}>{user.nombreUsuario}</span>
                </div>
            ) : (
                <Link href="/account/LoginPage" className={styles.navbarUser}>
                    <Image
                        src="/assets/images/user-interface.svg"
                        className={styles.navbarIcon}
                        alt="User"
                        width={50} // Especifica el ancho
                        height={50} // Especifica el alto
                    />
                    <span className={styles.navbarSpan}>Iniciar sesión</span>
                </Link>
            )}
<div  onClick={abrirCarrito} className={styles.navbarCarrito}>
    <Image src="/assets/images/icon-cart.svg" alt="Carrito" className={styles.navbarIcon}
          width={50}   // Especifica el ancho
          height={50}  // Especifica el alto
    />
    <span className={styles.navbarSpan}>Carrito</span>
    <div id="cartCount" className={styles.cartCount}>{cantidadTotalProductos}</div>
</div>
{isDropdownOpen && (
                <div className={styles.dropdownMenu}>
                    <Link href="/account/HistorialCompras" className={styles.dropdownItem}>
                        Mis Pedidos
                    </Link>
                    <Link href="/legal/ayuda" className={styles.dropdownItem}>
                        Ayuda
                    </Link>
                    <button onClick={handleLogout} className={styles.dropdownItem}>
                        Cerrar sesión
                    </button>
                </div>
            )}
</div>
  )
}
export default UserActions;

