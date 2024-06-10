package com.iroxit.front.end.desktop.view;

import com.iroxit.front.end.desktop.model.Producto;
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

public class ReporteProducto extends JFrame {

    private HttpService httpService;
    private JTable tabla;

    public ReporteProducto() throws Exception {
        httpService = new HttpService();

        setTitle("Reporte de Productos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        tabla = new JTable();
        String[] columnas
                = {
                    "ID Producto", "Titulo", "Descripcion", "PrecioUnitario", "Existencias"
                };

        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        tabla.setModel(modelo);

        cargarDatos();
        setVisible(true);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }

    private void cargarDatos() {
        try {
            List<Map<String, Object>> productos = httpService.obtenerProductos();

            DefaultTableModel reporte = (DefaultTableModel) tabla.getModel();
            reporte.setRowCount(0);

            for (Map<String, Object> producto : productos) {
                Object[] rowData = {
                    producto.get("idproductos"), producto.get("titulo"),
                    producto.get("descripcion"), producto.get("precioUnitario"),
                    producto.get("existencias"), 
                };
                reporte.addRow(rowData);

            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
