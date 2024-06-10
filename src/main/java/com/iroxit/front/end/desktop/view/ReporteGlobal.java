package com.iroxit.front.end.desktop.view;

import com.iroxit.front.end.desktop.util.HttpService;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ReporteGlobal extends JFrame {

    private JTable tabla;
    private HttpService httpService;

    public ReporteGlobal() throws Exception {
        httpService = new HttpService();

        setTitle("Reporte Global de Ventas");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        String[] columnas
                = {
                    "ID Productos", "Título", "Existencias", "Estado",
                    "Ventas Por Artículo", "Ingreso Por Artículo",
                    "Ventas Globales", "Artículo Más Vendido"
                };

        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        tabla = new JTable(modelo);
        
        cargarDatos();
        setVisible(true);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }

    private void cargarDatos() {
        try {
            List<Map<String, Object>> ventas = httpService.obtenerVentasGlobales();

            DefaultTableModel reporte = (DefaultTableModel) tabla.getModel();
            reporte.setRowCount(0);

            for (Map<String, Object> venta : ventas) {
                Object[] rowData = {
                    venta.get("IDProductos"), venta.get("Titulo"), venta.get("Existencias"),
                    venta.get("estado"), venta.get("ventasPorArticulo"), venta.get("ingresoPorArticulo"),
                    venta.get("ventasGlobales"), venta.get("articuloMasVendido")
                };
                reporte.addRow(rowData);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
