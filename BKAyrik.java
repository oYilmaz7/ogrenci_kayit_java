import java.io.*;

public class BKAyrik
{
    private Student[] liste;
    private Student[] ayrikListe;
    private Student ogrenciKýsa;
    private char[] harfDizisi;
    
    private FileReader txtOkuyucu;
    
    private final int GENISLIK = 500;
    
    public BKAyrik()
    {
        liste = new Student[GENISLIK];
        ayrikListe = new Student[GENISLIK / 2];
        harfDizisi = new char[30000];
        
        //Text dosyasýný belirli bir karakter dizisine okuma
        try
        {
            txtOkuyucu = new FileReader("Ogrenciler.txt");
            
            txtOkuyucu.read(harfDizisi);
            
            txtOkuyucu.close();
        }
        
        catch(IOException e)
        {
            System.out.println("hata");
        }
        
        //Kýsa Süreli oluþturulacak öðrenci için ad soyad ve numara belirleme
        int numara = 0;
        String ad = "";
        String soyad = "";
        
        //Karakter dizisinden gerekli deðiþkenleri çekebilmek için sayaç oluþturma
        int sayac = 0;
        int sayacIlk = 0;
        
        //Karakter dizisini sonuna kadar takip ederek öðrencileri oluþturma ve ekleme
        while(sayac < harfDizisi.length - 2)
        {
            //Öðrencinin numarasýný alma
            sayacIlk = sayac;
            
            while( sayac < harfDizisi.length && harfDizisi[sayac] != ' ')
                sayac++;
            for(int i = sayacIlk; i < sayac; i++)
                numara = (numara * 10) + Character.getNumericValue(harfDizisi[i]);
            sayac++;
            
            //Öðrencinin adýný alma
            sayacIlk = sayac;
            
            while( sayac < harfDizisi.length && harfDizisi[sayac] != ' ')
                sayac++;
            
            for(int j = sayacIlk; j < sayac; j++)
                ad = ad + harfDizisi[j];
            sayac++;
            
            //Öðrencinin soyadýný alma
            sayacIlk = sayac;
            
            while( sayac < harfDizisi.length && harfDizisi[sayac] != ' ')
                sayac++;
            
            for(int k = sayacIlk; k < sayac; k++)
                soyad = soyad + harfDizisi[k];
            sayac++;
            
            //Yeni bir kýsa süreli öðrenci oluþturma
            ogrenciKýsa = new Student(numara, ad, soyad);
            
            //Öðrenciyi hash fonksiyonuna göre ekleme
            ekle(ogrenciKýsa);
            
            //Yeni öðrenci için deðiþkenleri sýfýrlama
            numara = 0;
            ad = "";
            soyad = "";
            
        }
    }
    
    //Öðrenciyi numarasýna göre ekleme
    public boolean ekle(Student ogrenci)
    {
        int mod = ogrenci.ogrNoAl() % GENISLIK;
        
        if(liste[mod] != null)
        {
            int j = 0;
            
            while(ayrikListe[j] != null)
            {
                if(j == (GENISLIK/2) - 1 )
                    return false;
                else 
                    j++;
            }
            ayrikListe[j] = ogrenci;
            return true;
        }
        
        liste[mod] = ogrenci;
        return true;
    }
    
    //Öðrenciyi numarasýndan arama
    public String bul(int numara)
    {
        //Hash yapma
        int mod = numara % GENISLIK;
        
        //Aranan nokta boþsa hata verme
        if(liste[mod] == null)
            return "Aradýðýnýz öðrenci bulunmamaktadýr.";
        
        //Doluysa sýrasýyla listeyi kontrol etme
        else if(liste[mod].ogrNoAl() != numara)
        {
            int i = 0;
            
            while(ayrikListe[i].ogrNoAl() != numara)
            {
                if(i == (GENISLIK/2) - 1)
                    return "Aradýðýnýz öðrenci bulunmamaktadýr.";
                else 
                    i++;
            }
            return "Ayrýk Liste : " + (i+1) + ". " + ayrikListe[i].toString();
        }
        //Bulunan noktada öðrenciyi ad soyad olarak döndürme
        return (mod+1) + ". " + liste[mod].toString();
    }
    
    //Öðrenci Listesini sýralý halde döndürme
    public String toString()
    {
        String toReturn = " Liste \n";
        
        for(int i = 0; i < liste.length; i++)
            toReturn = toReturn + " " + (i+1) + ". "  +liste[i] + "\n";
        
        toReturn = toReturn + "\n Ayrýk Liste \n";
        
        for(int i = 0; i < ayrikListe.length; i++)
            toReturn = toReturn + " " + (i+1) + ". "  +ayrikListe[i] + "\n";
        
        return toReturn;
    }
    
}