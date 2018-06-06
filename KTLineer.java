import java.io.*;

public class KTLineer
{
    private Student[] liste;
    private Student ogrenciK�sa;
    private char[] harfDizisi;
    
    private FileReader txtOkuyucu;
    
    private final int GENISLIK = 500;
    
    public KTLineer()
    {
        liste = new Student[GENISLIK];
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
        //��renci numaras�n� al�p 3 e b�lme ve toplam�n� alma
        String numara = "" + ogrenci.ogrNoAl();
        
        String sonUclu = numara.substring( numara.length() - 3 );
        String ortaUclu = numara.substring( numara.length() - 6 , numara.length() - 3);
        String ilkUclu = numara.substring( 0 , numara.length() - 6);
        
        sonUclu = new StringBuffer(sonUclu).reverse().toString();
        //Ara toplam� hesaplama
        int toplam = Integer.parseInt(ilkUclu) + Integer.parseInt(ortaUclu) + Integer.parseInt(sonUclu);
        String toplam2 = "" + toplam;
        
        //Adresi toplam�n 2.hanesinden sonras� olarak e�itleme
        int adres = Integer.parseInt( toplam2.substring(1) ) % GENISLIK;
        int i = adres;
        
        while(liste[i] != null)
        {
            if(i == GENISLIK - 1)
                i = 0;
            else 
                i++;
            if(i == adres)
                return false;
        }
        
        liste[i] = ogrenci;
        return true;
    }
    
    //��renciyi numaras�ndan arama
    public String bul(int no)
    {
        //Hash yaparak numaran�n olmas� gereken yeri bulma
        String numara = "" + no;
        
        String sonUclu = numara.substring( numara.length() - 3 );
        String ortaUclu = numara.substring( numara.length() - 6 , numara.length() - 3);
        String ilkUclu = numara.substring( 0 , numara.length() - 6);
        
        sonUclu = new StringBuffer(sonUclu).reverse().toString();
        
        int toplam = Integer.parseInt(ilkUclu) + Integer.parseInt(ortaUclu) + Integer.parseInt(sonUclu);
        String toplam2 = "" + toplam;
        
        int adres = Integer.parseInt( toplam2.substring(1) ) % GENISLIK;
        int i = adres;
        
        //Aranan nokta bo�sa hata verme
        if(liste[i] == null)
            return "Arad���n�z ��renci bulunmamaktad�r.";
        
        //Doluysa s�ras�yla listeyi kontrol etme
        else
        {
            while(liste[i].ogrNoAl() != no)
            {
                if(i == GENISLIK - 1)
                    i = 0;
                else 
                    i++;
                //Liste ba�a d�nerse hata verme
                if(i == adres)
                    return "Arad���n�z ��renci bulunmamaktad�r.";
            }
        }
        //Bulunan noktada ��renciyi ad soyad olarak d�nd�rme
        return (i+1) + ". " + liste[i].toString();
    }
    
    //��renci Listesini s�ral� halde d�nd�rme
    public String toString()
    {
        String toReturn = "";
        
        for(int i = 0; i < liste.length; i++)
            toReturn = toReturn + " " + (i+1) + ". "  +liste[i] + "\n";
        return toReturn;
    }
}
