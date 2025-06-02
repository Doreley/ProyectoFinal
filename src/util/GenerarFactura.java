/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author chaco
 */
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import Modelo.ModeloDetallePedido;
import Modelo.ModeloPedido;
import Modelo.ModeloCliente;

public class GenerarFactura {
    public void generarFactura(ModeloPedido pedido, List<ModeloDetallePedido> detalles, ModeloCliente cliente, String usuario) {
        String rutaArchivo = "factura_" + pedido.getId() + ".pdf";

        try {
            PdfWriter writer = new PdfWriter(rutaArchivo);
            PdfDocument pdf = new PdfDocument(writer);
            Document doc = new Document(pdf);

            // Encabezado
            doc.add(new Paragraph("Factura Restaurante")
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(20));

            doc.add(new Paragraph("Fecha: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))));
            doc.add(new Paragraph("Cliente: " + cliente.getNombre()));
            doc.add(new Paragraph("Teléfono: " + cliente.getTelefono()));
            doc.add(new Paragraph("Dirección: " + cliente.getDireccion()));
            doc.add(new Paragraph("Email: " + cliente.getEmail()));
            doc.add(new Paragraph(" "));

            
            Table tabla = new Table(UnitValue.createPercentArray(new float[]{2, 4, 2, 2, 2}))
                    .useAllAvailableWidth();
            tabla.addHeaderCell("ID");
            tabla.addHeaderCell("Producto");
            tabla.addHeaderCell("Cantidad");
            tabla.addHeaderCell("Precio U.");
            tabla.addHeaderCell("Subtotal");

            for (ModeloDetallePedido d : detalles) {
                tabla.addCell(String.valueOf(d.getIdProducto()));
                tabla.addCell(d.getNombreProducto());
                tabla.addCell(String.valueOf(d.getCantidad()));
                tabla.addCell(String.valueOf(d.getPrecioUnitario()));
                tabla.addCell(String.valueOf(d.getSubtotal()));
            }

            doc.add(tabla);

            
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("Subtotal: $" + (pedido.getTotal() - pedido.getImpuesto())));
            doc.add(new Paragraph("Impuesto: $" + pedido.getImpuesto()));
            doc.add(new Paragraph("Total: $" + pedido.getTotal()).setBold());

            doc.close();
            System.out.println("Factura generada en: " + rutaArchivo);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
}
