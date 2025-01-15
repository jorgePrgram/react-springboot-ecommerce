

import { useState } from 'react';
import styles from './Header.module.scss';

interface SearchBarProps{
  onSearch: (query: string)=> void;
}

const SearchBar:React.FC<SearchBarProps> = ({ onSearch })=>{
  const [inputValue, setInputValue] = useState<string>('');

  const handleInputChange=(e: React.ChangeEvent<HTMLInputElement>)=>{
    setInputValue(e.target.value);
  }
  const handleSearchClick=()=>{
    onSearch(inputValue);
  }
return(
    <div className={styles.searchContainer}>
<input
  type="text"
  value={inputValue}
  onChange={handleInputChange}
  className={styles.searchInput}
  placeholder="Buscar producto..."
/>
<button type="button" onClick={handleSearchClick} className={styles.searchButton}>
  Buscar
</button>
</div>
);
}
export default SearchBar;


