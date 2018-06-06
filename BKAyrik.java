import java.io.*;

public class BKAyrik
{
    private Student[] liste;
    private Student[] ayrikListe;
    private Student ogrenciK�sa;
    private char[] harfDizisi;
    
    private FileReader txtOkuyucu;
    
    private final int GENISLIK = 500;
    
    public BKAyrik()
    {
        liste = new Student[GENISLIK];
        ayrikListe = new Student[GENISLIK / 2];
        harfDizisi = new char[30000];
        
        //Text dosyas�n� belirli bir karakter dizisine okuma
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
        
        //K�sa S�reli olu�turulacak ��renci i�in ad soyad ve numara belirleme
        int numara = 0;
        String ad = "";
        String soyad = "";
        
        //Karakter dizisinden gerekli de�i�kenleri �ekebilmek i�in saya� olu�turma
        int sayac = 0;
        int sayacIlk = 0;
        
        //Karakter dizisini sonuna kadar takip ederek ��rencileri olu�turma ve ekleme
        while(sayac < harfDizisi.length - 2)
        {
            //��rencinin numaras�n� alma
            sayacIlk = sayac;
            
            while( sayac < harfDizisi.length && harfDizisi[sayac] != ' ')
                sayac++;
            for(int i = sayacIlk; i < sayac; i++)
                numara = (numara * 10) + Character.getNumericValue(harfDizisi[i]);
            sayac++;
            
            //��rencinin ad�n� alma
            sayacIlk = sayac;
            
            while( sayac < harfDizisi.length && harfDizisi[sayac] != ' ')
                sayac++;
            
            for(int j = sayacIlk; j < sayac; j++)
                ad = ad + harfDizisi[j];
            sayac++;
            
            //��rencinin soyad�n� alma
            sayacIlk = sayac;
            
            while( sayac < harfDizisi.length && harfDizisi[sayac] != ' ')
                sayac++;
            
            for(int k = sayacIlk; k < sayac; k++)
                soyad = soyad + harfDizisi[k];
            sayac++;
            
            //Yeni bir k�sa s�reli ��renci olu�turma
            ogrenciK�sa = new Student(numara, ad, soyad);
            
            //��renciyi hash fonksiyonuna g�re ekleme
            ekle(ogrenciK�sa);
            
            //Yeni ��renci i�in de�i�kenleri s�f�rlama
            numara = 0;
            ad = "";
            soyad = "";
            
        }
    }
    
    //��renciyi numaras�na g�re ekleme
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
    
    //��renciyi numaras�ndan arama
    public String bul(int numara)
    {
        //Hash yapma
        int mod = numara % GENISLIK;
        
        //Aranan nokta bo�sa hata verme
        if(liste[mod] == null)
            return "Arad���n�z ��renci bulunmamaktad�r.";
        
        //Doluysa s�ras�yla listeyi kontrol etme
        else if(liste[mod].ogrNoAl() != numara)
        {
            int i = 0;
            
            while(ayrikListe[i].ogrNoAl() != numara)
            {
                if(i == (GENISLIK/2) - 1)
                    return "Arad���n�z ��renci bulunmamaktad�r.";
                else 
                    i++;
            }
            return "Ayr�k Liste : " + (i+1) + ". " + ayrikListe[i].toString();
        }
        //Bulunan noktada ��renciyi ad soyad olarak d�nd�rme
        return (mod+1) + ". " + liste[mod].toString();
    }
    
    //��renci Listesini s�ral� halde d�nd�rme
    public String toString()
    {
        String toReturn = " Liste \n";
        
        for(int i = 0; i < liste.length; i++)
            toReturn = toReturn + " " + (i+1) + ". "  +liste[i] + "\n";
        
        toReturn = toReturn + "\n Ayr�k Liste \n";
        
        for(int i = 0; i < ayrikListe.length; i++)
            toReturn = toReturn + " " + (i+1) + ". "  +ayrikListe[i] + "\n";
        
        return toReturn;
    }
    
}