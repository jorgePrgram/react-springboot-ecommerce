
import styles from './CategoryModal.module.scss'
import Link from 'next/link';

interface CategoryModalProps{
    isOpen: boolean;
    onClose: () => void;
    categorias: {id:number, nombre:string }[] ;
    onSelectCategory: (categoryId: number)=>void;
}

const CategoryModal: React.FC<CategoryModalProps>=({ isOpen, onClose, categorias, onSelectCategory })=> {
    if (!isOpen) return null;


return(
    <div className={styles.modalOverlay} onClick={onClose}>
    <div className={styles.modalContent} onClick={(e) => e.stopPropagation()}>
        <h2>Categorías</h2>
        <ul className={styles.categoryList}>
            {categorias.map((category) => (
                <li key={category.id} className={styles.categoryItem}>
                <span onClick={() => {
                    onSelectCategory((category.id)); // Llama a la función de selección
                    onClose(); // Cierra el modal
                }}>
                    {category.nombre}
                </span>
            </li>
            ))}
        </ul>
        <button onClick={onClose} className={styles.closeButton}>
            Cerrar
        </button>
    </div>
</div>
)

}

export default CategoryModal;