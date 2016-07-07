package console;

import org.hibernate.cfg.AnnotationConfiguration;
import service.Manager;
import java.util.Scanner;
import java.sql.Timestamp;
import java.util.Date;

/**
 *  Klasa odpowiada za wyświetlanie menu w oknie konsoli i uruchamianie odpowiednich metod z klasy Manager
 * 
 */
public class Menu {
    
    public static void main(String[] args) {
        //Tworzy zmienne instancji
        Menu dane = new Menu();
        Manager manager = new Manager();
        int choice;
        Scanner scanner;
             
        //Tworzy i konfiguruje menedżera sesji Hibernate
        try{
            Manager.factory = new AnnotationConfiguration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Nieudane utworzenie objektu SessionFactory" + ex);
            throw new ExceptionInInitializerError(ex);
        }
        
        //Pętla tworzy główne menu 
        do {
            //Uruchamia metodę pokazMenu
            choice = dane.pokazMenu();   
            
            //Pobiera wynik wyboru z menu i wywołuje przypisane akcje
            switch (choice) {
                //Tworzy fakturę
                case 1:
                    String NumerFaktury;
                    String NazwaNabywcy;
                    int NIPNabywcy;
                    String AdresNabywcy;
                    Scanner input = new Scanner(System.in);

                    System.out.println("");
                    System.out.println("Podaj unikalny numer faktury:");
                    System.out.println("");

                    //Pobiera jako ciąg znaków
                    NumerFaktury = input.next(); 
                
                    System.out.println("");
                    System.out.println("Podaj nazwę nabywcy:");
                    System.out.println("");

                    NazwaNabywcy = input.next(); 
                    
                    System.out.println("");
                    System.out.println("Podaj adres nabywcy:");
                    System.out.println("");

                    AdresNabywcy = input.next(); 
                    
                    System.out.println("");
                    System.out.println("Podaj NIP:");
                    System.out.println("");

                    //Pobiera jako Integer
                    NIPNabywcy = input.nextInt(); 
                    
                    //Uruchamia metodę dodania faktury i przekazuje parametry
                    manager.DodajFakture(NumerFaktury, new Timestamp(new Date().getTime()), NazwaNabywcy, NIPNabywcy, AdresNabywcy);                     
                    
                    //Tworzy podmenu dodania pozycji zamówienia po utworzeniu faktury
                    do {
                        //Wyświetla podmenu
                        choice = dane.pokazPodmenu();
                        
                        //Pobiera wynik wyboru z podmenu i uruchamia akcje
                        switch (choice) {
                            //Dodaje nową pozycję zamówienia
                            case 1: 
                                String NazwaProduktu;
                                Float CenaProduktu;
                                int Ilosc;
                                
                                input = new Scanner(System.in);

                                System.out.println("");
                                System.out.println("Podaj nazwę produktu:");
                                System.out.println("");

                                NazwaProduktu = input.next(); 
                                
                                System.out.println("");
                                System.out.println("Podaj cenę produktu:");
                                System.out.println("");

                                //Pobiera jako liczbe zmiennoprzecnikową float
                                CenaProduktu = input.nextFloat();
                                
                                System.out.println("");
                                System.out.println("Podaj ilość:");
                                System.out.println("");

                                Ilosc = input.nextInt();
                                
                                //Dodaje nową pozycję azamówienia
                                manager.DodajPozycjeZamowienia(NazwaProduktu, CenaProduktu, Ilosc);
                                break;
                            case 2:  
                                //Zatwierdza cała transakcję
                                manager.ZatwierdzTransakcje();
                                break;
                        }
                    //Dopóki użytkownik nie zatwierdzi pętla działa
                    } while (choice != 2);
                    
                    break;
                //Tworzy raport
                case 2:   
                    manager.pokazRaportFaktur();
                    break;
                //Wychodzi z programu
                case 3:
                    System.out.println("");
                    System.exit(0);
            }   
        //Dopóki użytkownik nie zakończy programu pętla działa
        } while (choice != 3);
    }
        
    //Metoda wyświetla menu główne i zwraca wynik wyboru
    public static int pokazMenu() {
        int selection;
        Scanner input = new Scanner(System.in);

        /***************************************************/

        System.out.println("");
        System.out.println("Wybierz z podanych możliwości:");
        System.out.println("-------------------------\n");
        System.out.println("1 - Dodaj nową fakturę");
        System.out.println("2 - Pokaż wszystkie faktury");
        System.out.println("3 - Wyjście");
        System.out.println("");

        selection = input.nextInt();
        return selection;    
    }  
    
    //Metoda wyświetla podmenu dodania pozycji zamówienia i zwraca wynik wyboru
    public static int pokazPodmenu() {
        int selection;
        Scanner input = new Scanner(System.in);

        /***************************************************/

        System.out.println("");
        System.out.println("Wybierz z podanych możliwości:");
        System.out.println("-------------------------\n");
        System.out.println("1 - Dodaj nową pozycję zamówienia");
        System.out.println("2 - Zapisz i zakończ");
        System.out.println("");

        selection = input.nextInt();
        return selection;    
    } 
}
