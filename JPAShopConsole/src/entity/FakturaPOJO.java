package entity;

import javax.persistence.Entity; 
import javax.persistence.Table; 
import javax.persistence.Column;
import javax.persistence.Id; 
import javax.persistence.GeneratedValue;
import java.sql.Timestamp;

/**
 *  Klasa przechowuje zmienne faktury i zapisuje je do bazy danych do tabeli Faktura korzystając z pliku hibernate.cfg.xml.
 * 
 */

//Powiązanie klasy FakturaPOJO z tabelą Faktura w bazie danych
@Entity 
@Table( name = "Faktura" )
public class FakturaPOJO { 
    //Zaddeklarowanie zmiennych instancji 
    private String id;
    private Timestamp DataUtworzenia;
    private String NazwaNabywcy;
    private int NIPNabywcy;
    private String AdresNabywcy; 
    
    //Przypisanie zmiennej id do kolumny tabeli NumerFaktury i ustawienie kluczem głównym
    @Id
    @Column (name = "NumerFaktury")
    public String getId() { 
        return id; 
    } 
    
    public void setId(String id) { 
        this.id = id; 
    } 

    //Przypisanie zmiennej DataUtworzenia do kolumny DataUtworzenia
    @Column (name = "DataUtworzenia") 
    public Timestamp getDataUtworzenia() { 
        return DataUtworzenia; 
    } 
    
    public void setDataUtworzenia(Timestamp DataU) { 
        this.DataUtworzenia = DataU; 
    }
    
    @Column (name = "NazwaNabywcy") 
    public String getNazwaNabywcy() { 
        return NazwaNabywcy; 
    } 
    
    public void setNazwaNabywcy(String NazwaN) { 
        this.NazwaNabywcy = NazwaN; 
    } 
    
    @Column (name = "NIPNabywcy") 
    public int getNIPNabywcy() { 
        return NIPNabywcy; 
    } 
    
    public void setNIPNabywcy(int NIP) { 
        this.NIPNabywcy = NIP; 
    } 
    
    @Column (name = "AdresNabywcy") 
    public String getAdresNabywcy() { 
        return AdresNabywcy; 
    } 
    
    public void setAdresNabywcy(String AdresN) { 
        this.AdresNabywcy = AdresN; 
    } 
}


