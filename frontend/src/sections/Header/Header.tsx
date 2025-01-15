import Link from 'next/link';
import styles from './Header.module.scss';
import Image from 'next/image';
import UserActions from './UserActions';
import { useState } from 'react';
import SearchBar from './SearchBar';
import NavLinks from './NavLinks';
import { useCart } from '@/components/CartContext';


const Header = () => {

const {  setSearchQuery } =useCart();


    const[isMenuVisible,setIsMenuVisible]=useState(false);
  

    const toggleMenu=()=>{
        setIsMenuVisible(!isMenuVisible);
    }
    return (
      <header className={styles.header}>
        <div className={styles.containerHeader}>
          <button className={styles.hamburgerMenu} id="hamburger-menu"
          onClick={toggleMenu}>
            &#9776;
          </button>
         
          <Link href="/" className={styles.navbarLogo}>
            <Image src="/assets/images/logoTipo.png" 
            alt="Logo" className={styles.navbarIconLogo} 
            width={150}
            height={40}
            priority={true}
            />

          </Link>
          <SearchBar onSearch={setSearchQuery} />
          <UserActions  />
        </div>
      <NavLinks isMenuVisible={isMenuVisible} /> 
     
      </header>
    );
  };

export default Header;