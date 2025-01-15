import '../styles/globals.scss';
import Header from '../sections/Header/Header'
import { AppProps } from 'next/app';
import { CartProvider } from '@/components/CartContext';
import { AuthProvider } from '@/context/AuthProvider';

const App = ({ Component, pageProps }: AppProps) => {
  return(
<AuthProvider>
    <CartProvider>
  <div>
    <Header />
    <Component {...pageProps} />
  </div>
  </CartProvider>
  </AuthProvider>
  )
};

export default App;