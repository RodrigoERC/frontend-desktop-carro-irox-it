package com.iroxit.front.end.desktop.view;

import com.iroxit.front.end.desktop.util.HttpService;
import java.awt.BorderLayout;
import java.io.IOException;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class NotificacionProveedor extends JFrame {

    private HttpService httpService;

    public NotificacionProveedor() throws IOException, InterruptedException {

        httpService = new HttpService();
        String mensaje = "";

        List<String> existencias = httpService.obtieneExistencias();

        setTitle("Notificación a Proveedor");
        setSize(1200, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        for (String existencia : existencias) {
            mensaje = existencia + "\n";
        }

        JLabel labelExistencias = new JLabel(mensaje, JLabel.CENTER);
        add(labelExistencias, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(
                this, "Notificación enviada al proveedor.",
                "Información", 
                JOptionPane.INFORMATION_MESSAGE
        );

    }
}
