import { DetallePedido } from "@/interfaces/types";
import { getUsuarioById } from "../users/getUsuario";
import { getPedidoById } from "./getPedido";
import { getDetallePedido } from "../detailsPedido/getDetallePedido";

const obtenerHistorialPedidos = async (usuarioId: number, token: string) => {
    try {
        const usuario = await getUsuarioById(usuarioId, token);
        if (!usuario || !usuario.id) {
            throw new Error(`Usuario con ID ${usuarioId} no encontrado`);
        }

        const pedidosCompletos = usuario.pedidos ? await Promise.all(
            usuario.pedidos.map(async (pedido) => {
                try {
                    const detallesPedido = await getPedidoById(pedido.id, token);

                    const detallesCompletos = await Promise.all(
                        detallesPedido.detallePedidos.map(async (detalle: { id: number }) => { 
                            try {
                                const detalleId = detalle.id; 
                                const detallePedido: DetallePedido = await getDetallePedido(detalleId, token);

                                if (!detallePedido || !detallePedido.producto || !detallePedido.producto.nombre || detallePedido.producto.precio === undefined) {
                                    console.warn(`Producto no encontrado o inválido para el detalle de pedido con ID ${detallePedido?.pedido?.id}`);
                                    return null;
                                }

                                return {
                                    cantidad: detallePedido.cantidad,
                                    precioUnitario: detallePedido.producto.precio,
                                    nombreProducto: detallePedido.producto.nombre,
                                };
                            } catch (error) {
                                console.error(`Error al obtener el detalle del pedido con ID ${detalle.id}:`, error); 
                                return null;
                            }
                        })
                    );

                    const detallesFiltrados = detallesCompletos.filter((detalle) => detalle !== null);

                    return {
                        id: pedido.id,
                        montoTotal: detallesPedido.montoTotal,
                        detalles: detallesFiltrados,
                    };
                } catch (error) {
                    console.error(`Error al obtener el pedido con ID ${pedido.id}:`, error);
                    return null;
                }
            })
        ) : [];

        const pedidosFiltrados = pedidosCompletos.filter((pedido) => pedido !== null);

        return {
            nombreUsuario: usuario.username,
            pedidos: pedidosFiltrados,
        };
        
    } catch (error) {
        console.error("Error al obtener el historial de pedidos:", error);
        throw new Error('No se pudo obtener el historial de pedidos');
    }
};

export default obtenerHistorialPedidos;