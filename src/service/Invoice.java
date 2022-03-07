package service;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.DashedBorder;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TextAlignment;
import entite.OrderItem;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Invoice {
    public void genereateInvoice(List<OrderItem> orderItemsList) throws FileNotFoundException, MalformedURLException{
        String path="invoice.pdf";
        PdfWriter pdfWriter=new PdfWriter(path);
        PdfDocument pdfDocument=new PdfDocument(pdfWriter);
        //String imgPath="C:\\Users\\bhask\\IdeaProjects\\ItextPDF\\src\\main\\java\\ce_logo_circle_transparent.png";
        //ImageData imagedata= ImageDataFactory.create(imgPath);
        //Image image=new Image(imagedata);
        pdfDocument.setDefaultPageSize(PageSize.A4);
        float x = pdfDocument.getDefaultPageSize().getWidth() / 2;
        float y = pdfDocument.getDefaultPageSize().getHeight() / 2;
        System.out.println("x= "+x);
        System.out.println("y= "+y);
        //image.setFixedPosition(x-150,y-170);
        //image.setOpacity(0.1f);
        Document document=new Document(pdfDocument);
        //document.add(image);
        float threecol=190f;
        float twocol=285f;
        float twocol150=435f;
        float twoColumnWidth[]={twocol150,twocol};
        float threeColumnWidth[]={threecol,threecol,threecol};
        float fullWidth[]={threecol*3};

        Paragraph onesp=new Paragraph("\n");
        Paragraph twosp=new Paragraph("\n\n");
        Table nestedtable=new Table(new float[]{twocol / 2, twocol / 2});
        Table headerTable=new Table(twoColumnWidth);
        Border nb=new SolidBorder(Color.WHITE,1,0);

        headerTable.addCell(new Cell().add("Facture").setBold().setFontSize(20f).setBorder(nb).setTextAlignment(TextAlignment.LEFT).setMarginLeft(5));

        nestedtable.addCell(getHeaderTextCell(" No.:"));
        nestedtable.addCell(getHeaderTextCellValue("#"+orderItemsList.get(0).getOrder().getId()));
        nestedtable.addCell(getHeaderTextCell(" Date:"));
        nestedtable.addCell(getHeaderTextCellValue(""+orderItemsList.get(0).getOrder().getDate()));

        headerTable.addCell(nestedtable.setBorder(nb)).setBorder(nb);
        document.add(headerTable);
        document.add(new Paragraph("\n"));
        Border gb=new SolidBorder(Color.GRAY,2);
        Table tableDivider=new Table(fullWidth);
        document.add(tableDivider.setBorder(gb));
        document.add(onesp);
        Table twoColTable=new Table(twoColumnWidth);
        twoColTable.addCell(getBillingandShippingCell("Détails de facturation"));
        twoColTable.addCell(getBillingandShippingCell("Informations sur la livraison"));
        document.add(twoColTable.setMarginBottom(12f));

        Table twoColTable2=new Table(twoColumnWidth);
        twoColTable2.addCell(getCell10fLeft("Compagnie",true));
        twoColTable2.addCell(getCell10fLeft("Livreur",true));
        twoColTable2.addCell(getCell10fLeft("Federation Tunisienne de Football",false));
        twoColTable2.addCell(getCell10fLeft("Antonio",false));
        document.add(twoColTable2);

        Table twoColTable3=new Table(twoColumnWidth);
        twoColTable3.addCell(getCell10fLeft("Client",true));
        twoColTable3.addCell(getCell10fLeft("",true));
        twoColTable3.addCell(getCell10fLeft(orderItemsList.get(0).getOrder().getUser().getNom(),false));
        twoColTable3.addCell(getCell10fLeft("",false));
        document.add(twoColTable3);
        float oneColoumnwidth[]={twocol150};
        Table oneColTable1=new Table(oneColoumnwidth);
        oneColTable1.addCell(getCell10fLeft("Adresse",true));
        oneColTable1.addCell(getCell10fLeft("Technopole , Ghazela\nTunis  3004",false));
        oneColTable1.addCell(getCell10fLeft("Email",true));
        oneColTable1.addCell(getCell10fLeft(orderItemsList.get(0).getOrder().getUser().getEmail(),false));

        document.add(oneColTable1.setMarginBottom(10f));

        Table tableDivider2=new Table(fullWidth);
        Border dgb=new DashedBorder(Color.GRAY,0.5f);
        document.add(tableDivider2.setBorder(dgb));

        Paragraph producPara=new Paragraph("Votre Commande");

        document.add(producPara.setBold());
        Table threeColTable1=new Table(threeColumnWidth);
        threeColTable1.setBackgroundColor(Color.BLACK,0.7f);

        threeColTable1.addCell(new Cell().add("Articles").setBold().setFontColor(Color.WHITE).setBorder(nb));
        threeColTable1.addCell(new Cell().add("Quantité").setBold().setFontColor(Color.WHITE).setTextAlignment(TextAlignment.CENTER).setBorder(nb));
        threeColTable1.addCell(new Cell().add("Prix").setBold().setFontColor(Color.WHITE).setTextAlignment(TextAlignment.RIGHT).setBorder(nb)).setMarginRight(15f);
        document.add(threeColTable1);




        Table threeColTable2=new Table(threeColumnWidth);

        float totalSum=0;
         float total;
        for (OrderItem orderItem : orderItemsList)
        {
            total= orderItem.getQuantity()*orderItem.getProduct().getPrix();
            totalSum+=total;
            
            threeColTable2.addCell(new Cell().add(orderItem.getProduct().getNom()).setBorder(nb)).setMarginLeft(10f);
            threeColTable2.addCell(new Cell().add(String.valueOf(orderItem.getQuantity())).setTextAlignment(TextAlignment.CENTER).setBorder(nb));
            threeColTable2.addCell(new Cell().add(String.valueOf(total)).setTextAlignment(TextAlignment.RIGHT).setBorder(nb)).setMarginRight(15f);
        }
        
        
        document.add(threeColTable2.setMarginBottom(20f));
        float onetwo[]={threecol+125f,threecol*2};
        Table threeColTable4=new Table(onetwo);
        threeColTable4.addCell(new Cell().add("").setBorder(nb));
        threeColTable4.addCell(tableDivider2).setBorder(nb);
        document.add(threeColTable4);
        Table threeColTable3=new Table(threeColumnWidth);
        threeColTable3.addCell(new Cell().add("").setBorder(nb)).setMarginLeft(10f);
        threeColTable3.addCell(new Cell().add("Total TTC :").setTextAlignment(TextAlignment.CENTER).setBorder(nb));
        threeColTable3.addCell(new Cell().add("TND "+String.valueOf(totalSum)).setTextAlignment(TextAlignment.RIGHT).setBorder(nb)).setMarginRight(15f);

        document.add(threeColTable3);
        document.add(tableDivider2);
        document.add(new Paragraph("\n"));
        document.add(tableDivider.setBorder(new SolidBorder(Color.GRAY,1)).setMarginBottom(15f));
        Table tb=new Table(fullWidth);
        tb.addCell(new Cell().add("TERMES ET CONDITIONS\n").setBold().setBorder(nb));

        List<String> TncList=new ArrayList<>();
        TncList.add("1. Le vendeur ne sera pas responsable envers l'acheteur directement ou indirectement pour toute perte ou dommage subi par l'acheteur.");
        TncList.add("2. Le Vendeur garantit le produit pendant un (1) an à compter de la date d'expédition");
        for(String tnc:TncList)
        {

            tb.addCell(new Cell().add(tnc).setBorder(nb));
        }
        document.add(tb);








        document.close();
        System.out.println("pdf file Created");
    }
    

     static Cell getHeaderTextCell(String textValue)
    {

        return new Cell().add(textValue).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT);
    }

    static Cell getHeaderTextCellValue(String textValue)
    {


        return new Cell().add(textValue).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
    }
    static Cell getBillingandShippingCell(String textValue)
    {

        return new Cell().add(textValue).setFontSize(12f).setBold().setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
    }
    static  Cell getCell10fLeft(String textValue,Boolean isBold){
        Cell myCell=new Cell().add(textValue).setFontSize(10f).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);
       return  isBold ?myCell.setBold():myCell;

    }

}

 
   
