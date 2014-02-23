/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entity.Enrollment;
import entity.Guest;
import entity.Person;
import entity.Quarantine;
import entity.User;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import model.*;

/**
 *
 * @author Patrick
 */
public class ReportHandler implements HismHandlerIF {

    private EnrollmentRegister enR;
    private PersonRegister peR;
    private QuarantineRegister quR;
    private UserRegister usR;

    private Font columnFont;
    
    public ReportHandler(EnrollmentRegister enR, PersonRegister peR, QuarantineRegister quR, UserRegister usR) {
        this.enR = enR;
        this.peR = peR;
        this.quR = quR;
        this.usR = usR;
        
        columnFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
        columnFont.setColor(BaseColor.WHITE);
    }

    public void writeTotalEnrolledReport() throws DocumentException, FileNotFoundException {
        Document doc = new Document();

        Calendar c = Calendar.getInstance();
        String file = "" + c.get(Calendar.DAY_OF_MONTH) + "_" + (c.get(Calendar.MONTH) + 1) + "_" + c.get(Calendar.YEAR) + "_T_" + c.get(Calendar.HOUR_OF_DAY) + "_" + c.get(Calendar.MINUTE) + ".pdf";
        PdfWriter.getInstance(doc, new FileOutputStream(new File(his.His.reportEnrolledDir + "/" + file)));

        doc.open();

        PdfPTable totalTable = new PdfPTable(1);
        totalTable.setSpacingAfter(10f);
        PdfPTable tableP = new PdfPTable(4);
        PdfPTable tableGs = new PdfPTable(3);

        PdfPCell tableGsnameCell = new PdfPCell(new Phrase("Navn", columnFont));
        tableGsnameCell.setBackgroundColor(BaseColor.BLACK);
        PdfPCell tableGsbirthCell = new PdfPCell(new Phrase("Fødselsdag", columnFont));
        tableGsbirthCell.setBackgroundColor(BaseColor.BLACK);
        PdfPCell tableGsPersonCell = new PdfPCell(new Phrase("Person navn", columnFont));
        tableGsPersonCell.setBackgroundColor(BaseColor.BLACK);

        tableGs.addCell(tableGsnameCell);
        tableGs.addCell(tableGsbirthCell);
        tableGs.addCell(tableGsPersonCell);

        PdfPCell tablePnameCell = new PdfPCell(new Phrase("Navn", columnFont));
        tablePnameCell.setBackgroundColor(BaseColor.BLACK);
        PdfPCell tablePbirthCell = new PdfPCell(new Phrase("Fødselsdag", columnFont));
        tablePbirthCell.setBackgroundColor(BaseColor.BLACK);
        PdfPCell tablePenrollerCell = new PdfPCell(new Phrase("Indskriver", columnFont));
        tablePenrollerCell.setBackgroundColor(BaseColor.BLACK);
        PdfPCell tablePtotalGuestsCell = new PdfPCell(new Phrase("Total gæster", columnFont));
        tablePtotalGuestsCell.setBackgroundColor(BaseColor.BLACK);

        tableP.addCell(tablePnameCell);
        tableP.addCell(tablePbirthCell);
        tableP.addCell(tablePenrollerCell);
        tableP.addCell(tablePtotalGuestsCell);

        HashSet<Enrollment> enA = enR.getEnrollments();
        int totalCounter = 0;
        int totalCounter_NoHoene = 0;

        Iterator<Enrollment> enI = enA.iterator();

        while (enI.hasNext()) {
            totalCounter++;
            Enrollment en = enI.next();
            Person enP = en.getEnrolledPerson();
            if (!enP.isHoene()) {
                totalCounter_NoHoene++;
            }
            String nameP = enP.getFirstname() + " " + enP.getMiddlename() + " " + enP.getLastname();
            String birthP = enP.getBirthdayDate();

            User enU = en.getEnrolledByUser();
            String nameU = enU.getFirstname() + " " + enU.getMiddlename() + " " + enU.getLastname();

            Set<Guest> enGs = en.getGuests();
            String totalGs = String.valueOf(enGs.size());
            totalCounter += enGs.size();

            PdfPCell namePCell = new PdfPCell(new Phrase(nameP));
            PdfPCell birthPCell = new PdfPCell(new Phrase(birthP));

            PdfPCell nameUCell = new PdfPCell(new Phrase(nameU));

            PdfPCell totalGsCell = new PdfPCell(new Phrase(totalGs));

            tableP.addCell(namePCell);
            tableP.addCell(birthPCell);
            tableP.addCell(nameUCell);
            tableP.addCell(totalGsCell);

            Iterator<Guest> enGsI = enGs.iterator();

            while (enGsI.hasNext()) {
                Guest g = enGsI.next();

                String nameG = g.getFirstname() + " " + g.getMiddlename() + " " + g.getLastname();

                PdfPCell nameGCell = new PdfPCell(new Phrase(nameG));
                PdfPCell birthGCell = new PdfPCell(new Phrase(g.getBirthdayDate()));
                PdfPCell personGCell = new PdfPCell(new Phrase(nameP));

                tableGs.addCell(nameGCell);
                tableGs.addCell(birthGCell);
                tableGs.addCell(personGCell);
            }
        }

        PdfPCell totalCell = new PdfPCell(new Phrase("Total indskrevne: " + totalCounter));
        totalTable.addCell(totalCell);

        PdfPCell totalNoHoeneCell = new PdfPCell(new Phrase("Total indskrevne uden høner: " + totalCounter_NoHoene));
        totalTable.addCell(totalNoHoeneCell);

        doc.add(new Phrase("Personer"));
        doc.add(tableP);
        doc.add(totalTable);
        doc.add(new Phrase("Gæster"));
        doc.add(tableGs);

        doc.close();

    }

    public void writeTotalPersonReport() throws DocumentException, FileNotFoundException {
        Document doc = new Document(PageSize.A4.rotate());

        Calendar c = Calendar.getInstance();
        String file = "" + c.get(Calendar.DAY_OF_MONTH) + "_" + (c.get(Calendar.MONTH) + 1) + "_" + c.get(Calendar.YEAR) + "_T_" + c.get(Calendar.HOUR_OF_DAY) + "_" + c.get(Calendar.MINUTE) + ".pdf";
        PdfWriter.getInstance(doc, new FileOutputStream(new File(his.His.reportPersonsDir + "/" + file)));
        doc.open();

        PdfPTable table = new PdfPTable(7);
        table.setWidths(new float[]{10f, 10f, 10f, 10f, 5f, 5f, 5f});
        PdfPTable totalTable = new PdfPTable(1);

        PdfPCell nameCell = new PdfPCell(new Phrase("Navn", columnFont));
        nameCell.setBackgroundColor(BaseColor.BLACK);
        PdfPCell addressCell = new PdfPCell(new Phrase("Adresse", columnFont));
        addressCell.setBackgroundColor(BaseColor.BLACK);
        PdfPCell birthCell = new PdfPCell(new Phrase("Fødselsdag", columnFont));
        birthCell.setBackgroundColor(BaseColor.BLACK);
        PdfPCell createdCell = new PdfPCell(new Phrase("Oprettelsesdato", columnFont));
        createdCell.setBackgroundColor(BaseColor.BLACK);
        PdfPCell hoeneCell = new PdfPCell(new Phrase("Høne", columnFont));
        hoeneCell.setBackgroundColor(BaseColor.BLACK);
        PdfPCell reserveCell = new PdfPCell(new Phrase("Reserve", columnFont));
        reserveCell.setBackgroundColor(BaseColor.BLACK);
        PdfPCell oneOneCell = new PdfPCell(new Phrase("1-1", columnFont));
        oneOneCell.setBackgroundColor(BaseColor.BLACK);

        table.addCell(nameCell);
        table.addCell(addressCell);
        table.addCell(birthCell);
        table.addCell(createdCell);
        table.addCell(hoeneCell);
        table.addCell(reserveCell);
        table.addCell(oneOneCell);

        HashSet<Person> persons = peR.getPersons();
        Iterator<Person> personsI = persons.iterator();

        PdfPCell totalCell = new PdfPCell(new Phrase("Total: " + persons.size()));
        totalTable.addCell(totalCell);

        while (personsI.hasNext()) {
            Person p = personsI.next();

            String name = p.getFirstname() + " " + p.getMiddlename() + " " + p.getLastname();
            String hoene = "Nej";
            if (p.isHoene()) {
                hoene = "Ja";
            }
            String reserve = "Nej";
            if (p.isReserve()) {
                reserve = "Ja";
            }
            String oneOne = "Nej";
            if (p.isOneOne()) {
                oneOne = "Ja";
            }

            PdfPCell nameC = new PdfPCell(new Phrase(name));
            PdfPCell addressC = new PdfPCell(new Phrase(p.getAddress()));
            PdfPCell birthC = new PdfPCell(new Phrase(p.getBirthdayDate()));
            PdfPCell createdC = new PdfPCell(new Phrase(p.getCreationDate()));
            PdfPCell hoeneC = new PdfPCell(new Phrase(hoene));
            PdfPCell reserveC = new PdfPCell(new Phrase(reserve));
            PdfPCell oneOneC = new PdfPCell(new Phrase(oneOne));

            table.addCell(nameC);
            table.addCell(addressC);
            table.addCell(birthC);
            table.addCell(createdC);
            table.addCell(hoeneC);
            table.addCell(reserveC);
            table.addCell(oneOneC);
        }

        doc.add(new Phrase("Personer"));
        doc.add(table);
        doc.add(totalTable);

        doc.close();
    }

    public void writeTotalUserReport() throws DocumentException, FileNotFoundException {
        Document doc = new Document(PageSize.A4.rotate());

        Calendar c = Calendar.getInstance();
        String file = "" + c.get(Calendar.DAY_OF_MONTH) + "_" + (c.get(Calendar.MONTH) + 1) + "_" + c.get(Calendar.YEAR) + "_T_" + c.get(Calendar.HOUR_OF_DAY) + "_" + c.get(Calendar.MINUTE) + ".pdf";
        PdfWriter.getInstance(doc, new FileOutputStream(new File(his.His.reportUsersDir + "/" + file)));
        doc.open();

        PdfPTable table = new PdfPTable(5);
        PdfPTable totalTable = new PdfPTable(1);

        PdfPCell colName = new PdfPCell(new Phrase("Navn", columnFont));
        colName.setBackgroundColor(BaseColor.BLACK);
        PdfPCell colBrugernavn = new PdfPCell(new Phrase("Brugernavn", columnFont));
        colBrugernavn.setBackgroundColor(BaseColor.BLACK);
        PdfPCell colCreated = new PdfPCell(new Phrase("Oprettelsesdato", columnFont));
        colCreated.setBackgroundColor(BaseColor.BLACK);
        PdfPCell colReserve = new PdfPCell(new Phrase("Reserve", columnFont));
        colReserve.setBackgroundColor(BaseColor.BLACK);
        PdfPCell colAdmin = new PdfPCell(new Phrase("Administrator", columnFont));
        colAdmin.setBackgroundColor(BaseColor.BLACK);

        table.addCell(colName);
        table.addCell(colBrugernavn);
        table.addCell(colCreated);
        table.addCell(colReserve);
        table.addCell(colAdmin);

        HashSet<User> users = usR.getUsers();
        Iterator<User> usI = users.iterator();

        PdfPCell totalCell = new PdfPCell(new Phrase("Total: " + users.size()));
        totalTable.addCell(totalCell);

        while (usI.hasNext()) {
            User u = usI.next();

            String name = u.getFirstname() + " " + u.getMiddlename() + " " + u.getLastname();
            String reserve = "Nej";
            if (u.isReserve()) {
                reserve = "Ja";
            }
            String admin = "Nej";
            if (u.isAdministrator()) {
                admin = "Ja";
            }

            PdfPCell nameC = new PdfPCell(new Phrase(name));
            PdfPCell usernameC = new PdfPCell(new Phrase(u.getUsername()));
            PdfPCell creationC = new PdfPCell(new Phrase(u.getCreationDate()));
            PdfPCell reserveC = new PdfPCell(new Phrase(reserve));
            PdfPCell adminC = new PdfPCell(new Phrase(admin));

            table.addCell(nameC);
            table.addCell(usernameC);
            table.addCell(creationC);
            table.addCell(reserveC);
            table.addCell(adminC);
        }

        doc.add(new Phrase("Brugere"));
        doc.add(table);
        doc.add(totalTable);

        doc.close();

    }

    public void writeTotalQuarantineReport() throws DocumentException, FileNotFoundException {
        Document doc = new Document(PageSize.A4.rotate());

        Calendar c = Calendar.getInstance();
        String file = "" + c.get(Calendar.DAY_OF_MONTH) + "_" + (c.get(Calendar.MONTH) + 1) + "_" + c.get(Calendar.YEAR) + "_T_" + c.get(Calendar.HOUR_OF_DAY) + "_" + c.get(Calendar.MINUTE) + ".pdf";
        PdfWriter.getInstance(doc, new FileOutputStream(new File(his.His.reportQuarantinesDir + "/" + file)));
        doc.open();

        PdfPTable table = new PdfPTable(2);
        PdfPTable totalTable = new PdfPTable(1);

        PdfPCell colName = new PdfPCell(new Phrase("Navn", columnFont));
        colName.setBackgroundColor(BaseColor.BLACK);
        PdfPCell colBirth = new PdfPCell(new Phrase("Fødselsdag", columnFont));
        colBirth.setBackgroundColor(BaseColor.BLACK);

        table.addCell(colName);
        table.addCell(colBirth);;

        HashSet<Quarantine> quarantines = quR.getQuarantines();
        Iterator<Quarantine> quI = quarantines.iterator();

        PdfPCell totalCell = new PdfPCell(new Phrase("Total: " + quarantines.size()));
        totalTable.addCell(totalCell);

        while (quI.hasNext()) {
            Quarantine qu = quI.next();

            Person p = qu.getPerson();

            String name = p.getFirstname() + " " + p.getMiddlename() + " " + p.getLastname();

            PdfPCell nameCell = new PdfPCell(new Phrase(name));
            PdfPCell birthCell = new PdfPCell(new Phrase(p.getBirthdayDate()));

            table.addCell(nameCell);
            table.addCell(birthCell);
        }

        doc.add(new Phrase("Karantæner"));
        doc.add(table);
        doc.add(totalTable);

        doc.close();
    }

}
