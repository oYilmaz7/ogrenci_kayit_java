public class Student
{
    private String ad = "";
    private String soyad = "";
    private int ogrNo = 140;
    
    private final String ALFABE = "abc�defg�h�ijklmno�prs�tu�vyz";
    
    public Student()
    {
        //Rastgele isim olu�turma
        for(int sayac = 0; sayac < 7; sayac++)
        {
            ad = ad + ALFABE.charAt( (int)(Math.random() * ALFABE.length()) );
        }
       
        //Rastgele soyisim olu�turma
        for(int sayac = 0; sayac < 10; sayac++)
        {
            soyad = soyad + ALFABE.charAt( (int)(Math.random() * ALFABE.length()) );
        }
        
        //Rastgele ��renci numaras� olu�turma
        for(int sayac = 0; sayac < 6; sayac++)
        {
            ogrNo = (10 * ogrNo) + ((int)(Math.random() * 10));
        }
        
        //Mutlak de�er alma
        ogrNo = Math.abs(ogrNo);
    }
    
    public Student(int ogrNo, String ad, String soyad)
    {
        this.ogrNo = ogrNo;
        this.ad = ad;
        this.soyad = soyad;
    }
    
    //Numaray� d�nd�rme
    public int ogrNoAl()
    {
        return ogrNo;
    }
    
    //Ad� d�nd�rme
    public String adAl()
    {
        return ad;
    }
    
    //Soyad� d�nd�rme
    public String soyadAl()
    {
        return soyad;
    }
    
    //��rencinin  �zeliklerini yaz� halinde d�nd�rme
    public String toString()
    {
        return "Ogrenci No : " + ogrNo + "   Ad : " + adAl() + "   Soyad : " + soyadAl() + "\n";
    }
}