import { NextPage } from 'next';
import ProductGrid from '@/sections/ProductGrid';
import '../styles/home.module.scss';
import ProductSearch from '@/components/ProductSearch';

const Home: NextPage = () => {
  return (
    <div>
     <ProductSearch />
    </div>
  );
};

export default Home;