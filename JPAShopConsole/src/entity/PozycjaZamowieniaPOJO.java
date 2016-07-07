package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *  Klasa przechowuje zmienne pozycji zamowienia i zapisuje je do bazy danych do tabeli PozycjaZamowienia korzystając z pliku hibernate.cfg.xml.
 * 
 */

//Powiązanie klasy PozycjaZamowieniaPOJO z tabelą PozycjaZamowienia w bazie danych
@Entity
@Table( name = "PozycjaZamowienia" )
public class PozycjaZamowieniaPOJO {   
    private int id; 
    private String Nazwa;
    private float Cena;
    private int Ilosc;

    //Oznaczenie relacji zmienej faktura (klucz obcy) jako wiele do jeden z klasą FakturaPOJO (id - klucz główny) 
    @ManyToOne
    private String faktura;
        
    //Przypisanie zmiennej id do kolumny tabeli ID i ustawienie automatycznie generowanym kluczem głównym
    @Id
    @GeneratedValue
    @Column (name = "ID")
    public int getId() { 
        return id; 
    } 
    
    public void setId(int id) { 
        this.id = id; 
    } 
    
    //Przypisanie zmiennej Nazwa do kolumny Nazwa
    @Column (name = "Nazwa") 
    public String getNazwa() { 
        return Nazwa; 
    } 
    
    public void setNazwa(String Nazwa) { 
        this.Nazwa = Nazwa; 
    }
    
    @Column (name = "Cena") 
    public float getCena() { 
        return Cena; 
    } 
    
    public void setCena(float Cena) { 
        this.Cena = Cena; 
    }
    
    @Column (name = "Ilosc") 
    public int getIlosc() { 
        return Ilosc; 
    } 
    
    public void setIlosc(int Ilosc) { 
        this.Ilosc = Ilosc; 
    }
    
    @Column (name = "NumerFaktury")
    public String getFakturaPOJO() {
       return faktura;
    }

    public void setFakturaPOJO(FakturaPOJO Faktura) {
       this.faktura = Faktura.getId();
    }
}
