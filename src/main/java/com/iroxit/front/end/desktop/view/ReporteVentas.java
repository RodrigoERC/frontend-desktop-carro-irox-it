package com.iroxit.front.end.desktop.view;

import com.iroxit.front.end.desktop.model.Venta;
import com.iroxit.front.end.desktop.util.HttpService;
import java.awt.BorderLayout;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ReporteVentas extends JFrame {

    private HttpService httpService;
    private JTable tabla;

    public ReporteVentas() throws Exception {
        httpService = new HttpService();

        setTitle("Reporte de Ventas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        tabla = new JTable();
        String[] columnas
                = {
                    "ID Venta", "ID Producto", "Cantidad Vendida", "Fecha"
                };

        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        tabla.setModel(modelo);

        cargarDatos();
        setVisible(true);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }

    private void cargarDatos() {
        try {
            List<Map<String, Object>> ventas = httpService.obtenerVentas();
            Map<String, Object> producto;

            DefaultTableModel reporte = (DefaultTableModel) tabla.getModel();
            reporte.setRowCount(0);
            int idProductos = 0;

            for (Map<String, Object> venta : ventas) {
                producto = (Map<String, Object>) venta.get("producto");

                if (producto != null) {
                    idProductos = (int) producto.get("idproductos");
                }

                Object[] rowData = {
                    venta.get("idventas"), idProductos,
                    venta.get("cantidadVendida"), venta.get("fecha")
                };
                reporte.addRow(rowData);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
