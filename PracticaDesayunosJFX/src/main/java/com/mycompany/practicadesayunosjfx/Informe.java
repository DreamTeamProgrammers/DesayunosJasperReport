package com.mycompany.practicadesayunosjfx;

import java.io.File;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author Antonio y Loren
 */
public class Informe {

    public static void showReport(String tipo) throws JRException, ClassNotFoundException, SQLException {
        String report = "";
        HashMap hm = new HashMap();
//
//        hm.put("tipo", tipo);
        if (tipo.equals("carta")) {
            report = "Carta.jasper";
        } else if (tipo.equals("pedidosHoy")) {
            report = "PedidoHoy.jasper";
        }

        JasperPrint jasperPrint = JasperFillManager.fillReport(
                report,
                hm,
                JdbcUtil.getConnection()
        );

        JRViewer viewer = new JRViewer(jasperPrint);

        JFrame frame = new JFrame("Listado de productos");
        frame.setSize(800, 1000);
        frame.getContentPane().add(viewer);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.pack();
        frame.setVisible(true);

        System.out.print("Done!");
    }

    public static void showReportBetweenDates(LocalDate localDate1, LocalDate localDate2) throws JRException, ClassNotFoundException, SQLException {

        HashMap hm = new HashMap();

        Date date1 = Date.from(localDate1.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date date2 = Date.from(localDate2.atStartOfDay(ZoneId.systemDefault()).toInstant());

        hm.put("Fecha1", date1);
        hm.put("Fecha2", date2);

        String report = "Pedidos.jasper";

        JasperPrint jasperPrint = JasperFillManager.fillReport(
                report,
                hm,
                JdbcUtil.getConnection()
        );

        JRViewer viewer = new JRViewer(jasperPrint);

        JFrame frame = new JFrame("Listado de productos entre" + localDate1 + " y " + localDate2);
        frame.getContentPane().add(viewer);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setSize(800, 1000);
        frame.pack();
        frame.setVisible(true);

        System.out.print("Done!");
    }

    public static void pdfReport(String tipo) throws JRException, ClassNotFoundException, SQLException {
        String report = "";
        HashMap hm = new HashMap();

        //hm.put("tipo", tipo);
        if (tipo.equals("carta")) {
            report = "Carta.jasper";
        } else if (tipo.equals("pedidosHoy")) {
            report = "PedidoHoy.jasper";
        } else if (tipo.equals("pedidos")) {
            report = "Pedidos.jasper";
        }
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    report,
                    hm,
                    JdbcUtil.getConnection()
            );

            JRPdfExporter exp = new JRPdfExporter();
            exp.setExporterInput(new SimpleExporterInput(jasperPrint));
            exp.setExporterOutput(new SimpleOutputStreamExporterOutput(report + ".pdf"));
            SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
            exp.setConfiguration(conf);
            exp.exportReport();

            System.out.print("Done!");
        

    }
}
