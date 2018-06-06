import java.io.*;
import java.util.Scanner;

public class Tester
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        
        RastgeleOgrenciler yeni = new RastgeleOgrenciler(500);
        int secim;
        
        do
        {
            System.out.println("1.  Rastgele bir dosya olu�tur \n" +
                               "2.  B�len-Kalan ve Lineer yoklama  \n" +
                               "3.  B�len-Kalan ve Ayr�k Ta�ma \n"+
                               "4.  Katlama ve Lineer yoklama \n"+
                               "5.  Katlama ve Ayr�k Ta�ma \n"+
                               "6.  Kare ortas� ve Lineer yoklama \n"+
                               "7.  Kare ortas� ve Ayr�k Ta�ma \n"+
                               "8.  Lineer yoklama \n"+
                               "9.  Kar��la�t�rma \n"+
                               "10. ��k��");
                              
            
            secim = scan.nextInt();
            
            if(secim == 1)
                yeni = new RastgeleOgrenciler(500);
            else if(secim == 2)
            {
                BKLineer deneme = new BKLineer();
                System.out.println(deneme.toString());
                
                System.out.println(deneme.bul(Integer.parseInt(scan.next())));
            }
            else if(secim == 3)
            {
                BKAyrik deneme2 = new BKAyrik();
                System.out.println(deneme2.toString());
                
                System.out.println(deneme2.bul(Integer.parseInt(scan.next())));
            }
            else if(secim == 4)
            {
                KTLineer deneme3 = new KTLineer();
                System.out.println(deneme3.toString());
                
                System.out.println(deneme3.bul(Integer.parseInt(scan.next())));
            }
            else if(secim == 5)
            {
                KTAyrik deneme4 = new KTAyrik();
                System.out.println(deneme4.toString());
                
                System.out.println(deneme4.bul(Integer.parseInt(scan.next())));
            }
            else if(secim == 6)
            {
                KOLineer deneme5 = new KOLineer();
                System.out.println(deneme5.toString());
                
                System.out.println(deneme5.bul(Integer.parseInt(scan.next())));
            }
            else if(secim == 7)
            {
                KOAyrik deneme6 = new KOAyrik();
                System.out.println(deneme6.toString());
                
                System.out.println(deneme6.bul(Integer.parseInt(scan.next())));
            }
            else if(secim == 8)
            {
                BKLineer deneme7 = new BKLineer();
                System.out.println(deneme7.toString());
                
                System.out.println(deneme7.bul(Integer.parseInt(scan.next())));
            }
            else if(secim == 9)
            {
            }
           
        }while(secim != 10);
        
        System.out.println("Bu program� kulland���n�z i�in te�ekk�rler!");
        
        
      
    }
}