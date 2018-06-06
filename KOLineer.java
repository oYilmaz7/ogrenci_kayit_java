import java.io.*;

public class KOLineer
{   
    private Student[] liste;
    private Student ogrenciK�sa;
    private char[] harfDizisi;
    
    private FileReader txtOkuyucu;
    
    private final int GENISLIK = 500;
    
    public KOLineer()
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
        //��rencinin karesini alma
        double karesi = ogrenci.ogrNoAl() * ogrenci.ogrNoAl();
        String kare = "" + karesi;
        
        //Kare ortas�ndan 3 hane alarak 500 e g�re mod alarak hash yapma
        int kareOrtas� = Integer.parseInt(kare.substring( (int)( kare.length() / 2 ) ,  (int)( kare.length()/2 ) + 2) ) % GENISLIK;
        int i = kareOrtas�;
        
        //Listeyi kontrol ederek yer varsa ekleme
        while(liste[i] != null)
        {
            if(i == GENISLIK - 1)
                i = 0;
            else 
                i++;
            if(i == kareOrtas�)
                return false;
        }
        
        liste[i] = ogrenci;
        return true;
    }
    
    //��renciyi numaras�ndan arama
    public String bul(int numara)
    {
        //Numaran�n karesini alma
        double karesi = numara * numara;
        String kare = "" + karesi;
        
        ////Kare ortas�ndan 3 hane alarak 500 e g�re mod alarak hash yapma
        int kareOrtas� = Integer.parseInt( kare.substring( (int)( kare.length() / 2 ) ,  (int)( kare.length()/2 ) + 2) ) % GENISLIK;
        int i = kareOrtas�;
        
        //Aranan nokta bo�sa hata verme
        if(liste[i] == null)
            return "Arad���n�z ��renci bulunmamaktad�r.";
        
        //Doluysa s�ras�yla listeyi kontrol etme
        else
        {
            while(liste[i].ogrNoAl() != numara)
            {
                if(i == GENISLIK - 1)
                    i = 0;
                else 
                    i++;
                //Liste ba�a d�nerse hata verme
                if(i == kareOrtas�)
                    return "Arad���n�z ��renci bulunmamaktad�r.";
            }
        }
        //Bulunan noktada ��renciyi ad soyad olarak d�nd�rme
        return (i+1) + ". " + liste[i].toString();
    }
    
    //��renci listesini s�ral� halde d�nd�rme
    public String toString()
    {
        String toReturn = "";
        
        for(int i = 0; i < liste.length; i++)
            toReturn = toReturn + " " + (i+1) + ". " + liste[i] + "\n";
        return toReturn;
    }
    
}