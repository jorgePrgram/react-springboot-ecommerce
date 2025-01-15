import { Producto } from "@/interfaces/types"
import { getProductByName, getProducts } from "@/pages/api/products/getProducts";
import ProductGrid from "@/sections/ProductGrid";
import { useEffect, useState } from "react"
import { useCart } from "./CartContext";
import { getCategoriaById } from "@/pages/api/categoria/getCategoria";



const ProductSearch =()=>{
    const { searchQuery, categoryId } = useCart();
    const [productos, setProductos]=useState<Producto[]>([]);
    const [loading, setLoading]= useState<boolean>(true);
    const[error, setError]=useState<Error | null>(null);
    

    useEffect(() => {
        const fetchProducts = async () => {
            console.log("categoryId:", categoryId);
            setLoading(true);
            try {
                let data: Producto[];
                if (searchQuery) {
                    data = await getProductByName(searchQuery);
                } else if (categoryId){
                    const response= await getCategoriaById(categoryId);
                    data=response.productos;

                }
                else {
                    data = await getProducts();
                }
                console.log("Productos obtenidos:", data);
                setProductos(data);
            } catch (error) {
                if (error instanceof Error) {
                    setError(error);
                } else {
                    setError(new Error('Unknown error'));
                }
            } finally {
                setLoading(false); 
            }
        };
        fetchProducts();
    }, [searchQuery, categoryId]);


    if (loading) return <p>Loading ...</p>
    if (error) return <p>Error: {error.message} </p>;
    return(
        <div>

            <ProductGrid productos={productos} />
        </div>
    )
}

export default ProductSearch;