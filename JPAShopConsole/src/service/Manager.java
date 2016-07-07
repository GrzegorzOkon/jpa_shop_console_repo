package service;

import entity.FakturaPOJO;
import entity.PozycjaZamowieniaPOJO;
import java.sql.Timestamp;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SQLQuery;
import org.hibernate.Criteria;
import java.util.List;

/**
 *  Klasa odpowiada za dodawanie faktur i pozycji zamówień do tabel bazy danych.
 * 
 */
public class Manager {
    //Zmienne do utworzenia sesji połączenia do bazy
    public static SessionFactory factory;
    private Session session;
    
    FakturaPOJO faktura;
    
    //Metoda dodaje fakturę z obiektu klasy FakturaPOJO
    public void DodajFakture(String ID, Timestamp DataUtw, String Nabywca, int NIP, String Adres) {
        //Tworzy nową sesję 
        session=factory.openSession();	

        //Tworzy objekt transakcji 
        Transaction t = session.beginTransaction();
        
        //Ustawia zmienne obiektu faktura
        faktura = new FakturaPOJO (); 
        faktura.setId(ID);
        faktura.setDataUtworzenia(DataUtw);
        faktura.setNazwaNabywcy(Nabywca);
        faktura.setNIPNabywcy(NIP);
        faktura.setAdresNabywcy(Adres);
   
        //Zapisuje dane w sesji
        session.persist(faktura);
    }
    
    //Metoda dodaje fakturę z obiektu klasy PozycjaZamowieniaPOJO
    public void DodajPozycjeZamowienia(String Nazwa, float Cena, int Ilosc) {
        //Ustawia zmienne obiektu klasy PozycjaZamowienia
        PozycjaZamowieniaPOJO pozycja = new PozycjaZamowieniaPOJO(); 
        pozycja.setNazwa(Nazwa);
        pozycja.setCena(Cena);
        pozycja.setIlosc(Ilosc);
        pozycja.setFakturaPOJO(faktura);        

        //Zapisuje dane w sesji
        session.persist(pozycja);
    }
    
    //Metoda odpowiada za wyświetlanie raportu z tabel
    public void pokazRaportFaktur() {
        //otwiera nową sesję 
        session=factory.openSession();

        //Stworzenie i wywołanie zapytania SQL do raportu
        String sql = "SELECT Faktura.NumerFaktury, DataUtworzenia, SUM(Cena) 'Cena brutto', SUM(Cena) - SUM(Cena*0.23) 'Cena netto' FROM Faktura, PozycjaZamowienia WHERE Faktura.NumerFaktury = PozycjaZamowienia.NumerFaktury GROUP BY Faktura.NumerFaktury ORDER BY DataUtworzenia DESC";
        SQLQuery query = session.createSQLQuery(sql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List results = query.list();
        
        //Wyświetlenie raportu
        System.out.println("");       
        System.out.println("Raport:");
	for(Object f : results){
            System.out.println("" + f);
	}
        
        //Zamyka sesję
        session.close();
    }

    //Metoda zatwierdza transakcję i zamyka sesję
    public void ZatwierdzTransakcje() {
        session.getTransaction().commit();
        session.close();         
    }
}
